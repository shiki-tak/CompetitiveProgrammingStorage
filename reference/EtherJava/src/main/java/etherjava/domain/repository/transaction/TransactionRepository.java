package etherjava.domain.repository.transaction;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import etherjava.domain.model.account.Address;
import etherjava.domain.model.transaction.Transaction;
import etherjava.domain.model.transaction.TransactionHash;

@Repository
public class TransactionRepository {

	static Configuration conf;
	private final Connection connection;
	private final HTable hTableTransaction;

	public TransactionRepository() throws IOException {
		// instantiate Configuration class
		conf = new Configuration();
		conf = HBaseConfiguration.create(conf);
		conf.set(HConstants.ZOOKEEPER_QUORUM, "localhost");
		// Connectionクラスの初期化
		connection = ConnectionFactory.createConnection(conf);
	   // instantiate HTable class
	   hTableTransaction = (HTable) connection.getTable(TableName.valueOf("etherjava:transaction"));
	}

    /**
     * トランザクションのハッシュ値からトランザクションを検索
     * @param txHash 検索するトランザクションのハッシュ
     * @return transaction
     */
	public Transaction getTransactionByHash(String txHash) {
		try {
			Result getResult = hTableTransaction.get(new Get(Bytes.toBytes(txHash)));
			String to = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("to")));
			String from = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("from")));
			String value = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("value")));
			String gasLimit = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("gasLimit")));
			String gasPrice = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("gasPrice")));
			String nonce = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("nonce")));
			Transaction transaction = new Transaction(new TransactionHash(txHash), new Address(to), new Address(from), Double.parseDouble(value), Double.parseDouble(gasLimit), Double.parseDouble(gasPrice), Integer.parseInt(nonce));
			return transaction;
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO: ターゲットとしたtransactionが存在しない場合の処理
		return null;
	}

	public void save(Transaction transaction) {
		Put put = new Put(Bytes.toBytes(transaction.getTxHash().getTxHashAsString()));

		String to = transaction.getTo().addressToString();
		String from = transaction.getFrom().addressToString();
		double value = transaction.getValue();
		double gasLimit = transaction.getGasLimit();
		double gasPrice = transaction.getGasPrice();
		int nonce = transaction.getNonce();


		System.out.println("+++ Generate Trasaction +++");
		System.out.println("to: " + to);
		System.out.println("from: " + from);
		System.out.println("value: " + value);
		System.out.println("gasLimit: " + gasLimit);
		System.out.println("gasPrice: " + gasPrice);
		System.out.println("nonce: " + nonce);

		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("to"), Bytes.toBytes(String.valueOf(to)));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("from"), Bytes.toBytes(String.valueOf(from)));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("value"), Bytes.toBytes(String.valueOf(value)));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("gasLimit"), Bytes.toBytes(String.valueOf(gasLimit)));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("gasPrice"), Bytes.toBytes(String.valueOf(gasPrice)));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("nonce"), Bytes.toBytes(String.valueOf(nonce)));

		try {
			hTableTransaction.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
