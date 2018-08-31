package sample.hbase.messaging;

import java.io.IOException;
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

	@Override
	public List<Message> getInitialMessages(long roomId, List<Long> blockUsers) throws IOException {

		return null;
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

}
