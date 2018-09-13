package etherjava.domain.service.account;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	static Configuration conf;
	private final Connection connection;
	private final HTable hTable;

	public AccountService() throws IOException {
		// instantiate Configuration class
		conf = new Configuration();
		conf = HBaseConfiguration.create(conf);
		conf.set(HConstants.ZOOKEEPER_QUORUM, "localhost");
		// Connectionクラスの初期化
		connection = ConnectionFactory.createConnection(conf);
	   // instantiate HTable class
	   hTable = (HTable) connection.getTable(TableName.valueOf("etherjava:account"));
	}

	public void save(String addressToString, double balance, int nonce) throws IOException {

		Put put = new Put(Bytes.toBytes(addressToString));

		System.out.println("+++ Generate New Account +++");
		System.out.println("address: " + addressToString);
		System.out.println("balance: " + balance);
		System.out.println("nonce: " + nonce);

		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("balance"), Bytes.toBytes(String.valueOf(balance)));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("nonce"), Bytes.toBytes(String.valueOf(nonce)));

		hTable.put(put);
	}
}
