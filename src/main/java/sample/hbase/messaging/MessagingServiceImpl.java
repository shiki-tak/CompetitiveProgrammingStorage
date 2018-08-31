package sample.hbase.messaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.types.RawInteger;
import org.apache.hadoop.hbase.types.RawLong;
import org.apache.hadoop.hbase.types.RawString;
import org.apache.hadoop.hbase.types.Struct;
import org.apache.hadoop.hbase.types.StructBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Hash;
import org.apache.hadoop.hbase.util.SimplePositionedByteRange;



public class MessagingServiceImpl implements MessagingService {

	private final Connection connection;
	private final Hash hash;
	private final Struct messageRowSchema;

	// Constructor
	public MessagingServiceImpl() throws IOException {
		// Configurationクラスをインスタンス化し、Configurationクラスのsetメソッドを
		// 用いて、どのZookeeperに接続するかを設定
		Configuration conf = HBaseConfiguration.create();
		conf.set(HConstants.ZOOKEEPER_QUORUM, "localhost");
		// Connectionクラスの初期化
		connection = ConnectionFactory.createConnection(conf);
		// hashをMurmurHash3アルゴリズムで算出するためのインスタンスを取得
		hash = Hash.getInstance(Hash.MURMUR_HASH3);

		// メッセージのRowKeyのためのStructクラスをインスタンス化し、変数に格納している
		// StructBuilderクラスのインスタンスにハッシュ値の型としてRawIntegerクラス、ルームIDの型としてRawLongクラス
		// 送信日時の型としてRawLongクラス、メッセージIDの型としてRawStringクラスのインスタンスを指定している
		messageRowSchema = new StructBuilder().add(new RawInteger())
				.add(new RawLong()).add(new RawLong()).add(RawString.ASCENDING).toStruct();
	}

	/*
	 * (sendMessage)
	 * @see sample.hbase.messaging.MessagingService#sendMessage(long, long, java.lang.String)
	 * ルーム内でのメッセージ送信を行うためのメソッド
	 */
	@Override
	public void sendMessage(long roomId, long userId, String body) throws IOException {
		long postAt = System.currentTimeMillis();
		String messageId = UUID.randomUUID().toString();                                           // メッセージIDをUUIDクラスのrandomUUIDメソッドを用いて生成
		byte[] row = createMessageRow(roomId, postAt, messageId);                                  // createMessageRowメソッドを用いてRowKeyを生成

		Put put = new Put(row);

		put.addColumn(Bytes.toBytes("m"), Bytes.toBytes("messageId"), Bytes.toBytes(messageId));   // メッセージIDのColumnであるm:messageIdを追加
		put.addColumn(Bytes.toBytes("m"), Bytes.toBytes("userId"), Bytes.toBytes(userId));         // ユーザIDのColumnであるm:userIdを追加
		put.addColumn(Bytes.toBytes("m"), Bytes.toBytes("body"), Bytes.toBytes(body));             // メッセージ本文のColumnであるm:bodyを追加

		try (HTable table = (HTable) connection.getTable(TableName.valueOf("ns:message"))) {
			table.put(put);                                                                    // HTableクラスのputメソッドを用いて、m:messageId, m:userId, m:bodyの3つのColumnの追加を反映
		}
	}

	/*
	 * (getInitialMessages)
	 * @see sample.hbase.messaging.MessagingService#getInitialMessages(long, java.util.List)
	 * ルームに初めて訪れた時、他のユーザによってすでにルーム内で交換されているメッセージを取得するためのメソッド
	 */
	@Override
	public List<Message> getInitialMessages(long roomId, List<Long> blockUsers) throws IOException {
		// スキャンの開始RowKeyに<ルームDIのハッシュ>-<ルームID>、スキャンの停止RowKeyに<ルームDIのハッシュ>-<ルームID>+1を
		// 指定することでパーシャルキースキャンを行う
		byte[] startRow = createMessageScanRow(roomId);
		byte[] stopRow = incrementBytes(createMessageScanRow(roomId));
		Scan scan = new Scan(startRow, stopRow);

		// FilterListクラスのインスタンス化
		// FilterListクラスに追加したフィルタの条件を全て満たすもののみ、スキャン結果として取得することができる
		FilterList filterList = new FilterList(Operator.MUST_PASS_ALL);

		if (blockUsers != null) {
			for (Long userId : blockUsers) {
				// ブロックしているユーザをスキャンの対象外として、Rowをスキャン結果に含める
				SingleColumnValueFilter userFilter = new SingleColumnValueFilter(Bytes.toBytes("m"), Bytes.toBytes("userId"), CompareOp.NOT_EQUAL, Bytes.toBytes(userId));
				filterList.addFilter(userFilter);
			}
		}

		scan.setFilter(filterList);

		List<Message> messages = new ArrayList<>();
		try (HTable table = (HTable) connection.getTable(TableName.valueOf("ns:message"));
				ResultScanner scanner = table.getScanner(scan);) {
			int count = 0;
			for (Result result : scanner) {
				messages.add(convertToMessage(result));
				count++;
				if (count >= 50) {
					break;
				}
			}
		}
		return messages;
	}

	@Override
	public List<Message> getOldMessages(long roomId, Message oldestReceivedMessage, List<Long> blockUsers)
			throws IOException {
		return null;
	}

	@Override
	public List<Message> getNewMessages(long roomId, Message latestReceivedMessage, List<Long> blockUsers)
			throws IOException {
		return null;
	}

	/*
	 * <ルームIDのハッシュ>-<ルームID>-<送信日時のリバースタイムスタンプ>-<メッセージID>の形式で
	 * RowKeyを生成する
	 */
	private byte[] createMessageRow(long roomId, long postAt, String messageId) {
		Object[] values = new Object[] { hash.hash(Bytes.toBytes(roomId)), roomId, Long.MAX_VALUE - postAt, messageId };

		// messageRowSchemaのencodeメソッドを用いて、RowKeyを生成する
		// RowKeyの構成要素としてvaluesを指定している
		SimplePositionedByteRange positionedByteRange = new SimplePositionedByteRange (messageRowSchema.encodedLength(values));
		messageRowSchema.encode(positionedByteRange, values);

		return positionedByteRange.getBytes();
	}

	/*
	 * 引数としてルームIDを受け取り、<ルームIDのハッシュ>-<ルームID>の形式でRowKeyを生成する
	 */
	private byte[] createMessageScanRow(long roomId) {
		Object[] values = new Object[] { hash.hash(Bytes.toBytes(roomId)), roomId };

		SimplePositionedByteRange positionedByteRange = new SimplePositionedByteRange (messageRowSchema.encodedLength(values));
		messageRowSchema.encode(positionedByteRange, values);

		return positionedByteRange.getBytes();
	}

	private byte[] incrementBytes(byte[] createMessageScanRow) {
		return null;
	}

	private Message convertToMessage(Result result) {
		return null;
	}
}
