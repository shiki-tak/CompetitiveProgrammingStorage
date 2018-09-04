package messaging.domain.service;

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
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import messaging.domain.model.Message;

@Service
@AutoJsonRpcServiceImpl
public class MessagingServiceImpl implements MessagingService {

	static Configuration conf;
	private final Hash hash;
	private final Struct messageRowSchema;
	private final Connection connection;
	private final HTable hTable;

	public MessagingServiceImpl() throws IOException {
		// instantiate Configuration class
		conf = new Configuration();
		conf = HBaseConfiguration.create(conf);
		conf.set(HConstants.ZOOKEEPER_QUORUM, "localhost");
		// Connectionクラスの初期化
		connection = ConnectionFactory.createConnection(conf);
		// hashの初期化
		hash = Hash.getInstance(Hash.MURMUR_HASH3);
	   // instantiate HTable class
	   hTable = (HTable) connection.getTable(TableName.valueOf("ns:message"));

	   messageRowSchema = new StructBuilder().add(new RawInteger())
			   .add(new RawLong()).add(new RawLong()).add(RawString.ASCENDING).toStruct();
	}


	@Override
	public void sendMessage(String roomIdAsString, String userId, String body) throws IOException {
		long roomId = Long.parseLong(roomIdAsString);
		long postAt = System.currentTimeMillis();
		String messageId = UUID.randomUUID().toString();
		String rowKey = String.valueOf(roomId) + "-" + String.valueOf(postAt) + "-" + messageId;

		System.out.println("postAt: " + postAt);
		System.out.println("messageId: " + messageId);
		System.out.println("RowKey: " + rowKey);

		Put put = new Put(Bytes.toBytes(rowKey));

		put.addColumn(Bytes.toBytes("m"), Bytes.toBytes("messageId"), Bytes.toBytes(messageId));
		put.addColumn(Bytes.toBytes("m"), Bytes.toBytes("userId"), Bytes.toBytes(userId));
		put.addColumn(Bytes.toBytes("m"), Bytes.toBytes("body"), Bytes.toBytes(body));

		hTable.put(put);
	}

	@Override
	public List<Message> getNewMessage(long roomId, Message latestReceivedMessage) throws IOException {
		return null;
	}

}
