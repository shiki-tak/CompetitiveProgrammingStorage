package etherjava.domain.service.transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etherjava.domain.model.account.Account;
import etherjava.domain.model.account.Address;
import etherjava.domain.model.blockchain.Transaction;
import etherjava.domain.model.blockchain.TransactionHash;
import etherjava.domain.service.account.AccountService;

@Service
public class TransactionService {
	@Autowired
	AccountService accountService;
	static Configuration conf;
	private final Connection connection;
	private final HTable hTable;

	static List<Transaction> transactions = new ArrayList<>();

	// Constructor
	public TransactionService() throws IOException {
		// instantiate Configuration class
		conf = new Configuration();
		conf = HBaseConfiguration.create(conf);
		conf.set(HConstants.ZOOKEEPER_QUORUM, "localhost");
		// Connectionクラスの初期化
		connection = ConnectionFactory.createConnection(conf);
	   // instantiate HTable class
	   hTable = (HTable) connection.getTable(TableName.valueOf("etherjava:transaction"));
	}

    /**
     * トランザクションの送信
     * @param to                    受信者のアドレス
     * @param from                  送信者のアドレス
     * @param value                 送信者から受信者へ送られるETHの量
     * @param data(optional field)  メッセージ
     * @param gasLimit              トランザクションの実行にかかる計算のステップ数の最大値
     * @param gasPrice              送信者が支払う、１計算ステップあたりの手数料
     * @return txHash
     *
     * 1 ether = 10^18 wei
     */

	// TODO: data parameterがある場合のexecTransaction
	public String sendTransaction(Account to, Account from, double value, double gasLimit, double gasPrice, int toAccountNonce, int fromAccountNonce) {

		double toBalance = to.getBalance();
		double fromBalance = from.getBalance();

		// 手数料を計算
		double txFee = (gasLimit * gasPrice) * Math.pow(10, -18);

		// 送金者の残高が足りていなければエラー
		if (fromBalance < value + txFee) {
			// TODO: メッセージを切り出す
			String errorMessage = "balance not enough";
			return errorMessage;
		}

		// 送金
		toBalance += value;
		fromBalance -= (value + txFee);

		// トランザクションのハッシュ値を計算
		// (!) gasPriceをランダムな値にして同じハッシュ値にならないようにしている
		TransactionHash txHash = Transaction.calcTxHash(to.getAddress(), from.getAddress(), value, gasLimit, gasPrice * Math.random());

		// DBに登録するためのインスタンスを生成
		Transaction newTransaction = new Transaction(txHash, to.getAddress(), from.getAddress(), value, gasLimit, gasPrice, fromAccountNonce);
		// アカウント情報の更新
		try {
			accountService.save(to.getAddress().addressToString(), toBalance, toAccountNonce);
			accountService.save(from.getAddress().addressToString(), fromBalance, fromAccountNonce + 1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// トランザクションを保存
		save(newTransaction);

		return txHash.getTxHashAsString();
		// API用のreturn
		// return "{\"status\": \"success\", \"message\": \"" + txHash + "\"}";
	}

	public Transaction findOne(String txHash) {
		// TODO: 実装
		return null;
	}

    /**
     * トランザクションのハッシュ値からトランザクションを検索
     * @param txHash 検索するトランザクションのハッシュ
     * @return transaction
     */
	public Transaction getTransactionByHash(String txHash) {
		try {
			Result getResult = hTable.get(new Get(Bytes.toBytes(txHash)));
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

	private void save(Transaction transaction) {
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
			hTable.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
