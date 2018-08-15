package core;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

import accounts.Account;
import accounts.AccountManager;
import accounts.Address;
import rlp.RlpEncoder;
import rlp.RlpList;
import rlp.RlpString;
import trie.BloomFilter;

// TransactionHash型を定義
class TransactionHash {
	private String txHashAsString;

	TransactionHash(String txHashAsString) {
		this.txHashAsString = txHashAsString;
	}

	// getter
	public String getTxHashAsString() { return this.txHashAsString; }

	// setter
	public void setTxHashAsString(String txHashAsString) { this.txHashAsString = txHashAsString; }
}

public class Transaction {

	static List<Transaction> transactions = new ArrayList<>();

	// Transactionの構造
	// TODO: ADD Sign
	private TransactionHash txHash; // トランザクションのハッシュ値
	private Address to;             // 受信者
	private Address from;           // 送信者
	private double value;           // 送信者から受信者へ送られるETHの量
	private String data;            // オプショナルフィールド
	private double gasLimit;        // トランザクションの実行にかかる計算のステップ数の最大値
	private double gasPrice;        // 送信者が支払う、１計算ステップあたりの手数料
	private int nonce;              // fromが今までに実行したトランザクションの回数

	// コンストラクタ
	public Transaction(TransactionHash txHash,
					   Address from,
					   Address to,
					   double value,
					   double gasLimit,
					   double gasPrice) {
		this.txHash = txHash;
		this.from = from;
		this.to = to;
		this.value = value;
		this.gasLimit = gasLimit;
		this.gasPrice = gasPrice;
	}

	// getter
	public TransactionHash getTxHash() { return txHash; }
	public Address getTo() { return to; }
	public Address getFrom() { return from; }
	public double getValue() { return value; }
	public String getData() { return data; }
	public double getGasLimit() { return gasLimit; }
	public double getGasPrice() { return gasPrice; }
	public int getNonce() { return nonce; }

	// トランザクションのハッシュ値からトランザクションを検索
	public static Transaction getTransaction(TransactionHash txHash) {
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).getTxHash() == txHash) {
				return transactions.get(i);
			}
		}
		// TODO: ターゲットとしたtransactionが存在しない場合の処理
		return null;

	}

	// setter
	public void setTxHash(TransactionHash txHash) { this.txHash = txHash; }
	public void setTo(Address to) { this.to = to; }
	public void setFrom(Address from) { this.from = from; }
	public void setValue(double value) { this.value = value; }
	public void setData(String data) { this.data = data; }
	public void setGasLimit(double gasLimit) { this.gasLimit = gasLimit; }
	public void setGasPrice(double gasPrice) { this.gasPrice = gasPrice; }
	public void setNonce(int nonce) { this.nonce = nonce; }

	// TODO: Transactionクラスの実装
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
	public static TransactionHash sendTransaction(Address to, Address from, double value, double gasLimit, double gasPrice) {
		RlpString rlpTo = new RlpString(to.addressToString().getBytes());
		RlpString rlpFrom = new RlpString(from.addressToString().getBytes());
		RlpString rlpValue = new RlpString(String.valueOf(value).getBytes());
		RlpString rlpGasLimit = new RlpString(String.valueOf(gasLimit).getBytes());
		RlpString rlpGasPrice = new RlpString(String.valueOf(gasPrice).getBytes());

		RlpList rlpList = new RlpList(rlpTo, rlpFrom, rlpValue, rlpGasLimit, rlpGasPrice);

        // rlpListのRLPエンコーディング
        byte[] resRlpListEncoding = RlpEncoder.encode(rlpList);

        // keccak 256でハッシュ化
        // 本来はここで取得したハッシュ値を秘密鍵でsignし、v(27 or 28), r, sを計算
        // 求めたv, r, sと上記のパラメータを使って、RLPエンコーディングしたものから求めたハッシュ値がトランザクションハッシュとなる
		Keccak.DigestKeccak kecc = new Keccak.Digest256();
		byte[] txAsBytes = kecc.digest(resRlpListEncoding);

		// トランザクション手数料の計算
		double txFee = (gasLimit * gasPrice) * Math.pow(10, -18);

		// 送金処理
		Account toAccount = AccountManager.getAccount(to);
		Account fromAccount = AccountManager.getAccount(from);

		double toAfterBalance = toAccount.getBalance() + value;
		double fromAfterBalance = fromAccount.getBalance() - (value + txFee);
		toAccount.setBalance(toAfterBalance);
		fromAccount.setBalance(fromAfterBalance);

		// fromのnonceを+1する
		fromAccount.setNonce(fromAccount.getNonce() + 1);

		// TODO: EIP-155

		TransactionHash txHash = new TransactionHash(Hex.toHexString(txAsBytes));

		Transaction tx = new Transaction(txHash, from, to, value, gasLimit, gasPrice);
		transactions.add(tx);

		return txHash;
	}

	public static List<String> generateTransaction(int n) {
		List<String> txs = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			txs.add("Tx" + "-" + String.valueOf(n) + "-" + String.valueOf(i));
		}
		return txs;
	}

    /**
     * トランザクションの検索
     * @param blockChain ブロックチェーン
     * @param txHash 検索するトランザクションのハッシュ
     * @return
     */
	public static String searchTransaction(Blockchain blockChain, String txHash) {
		String resMessage;

		boolean isTx = false;
		int blockHeight = 0;
		for (; blockHeight < blockChain.getLatestBlockIndex(); blockHeight++) {
			BloomFilter logsBloom = blockChain.getBlock(blockHeight).getBlockHeader().getLogsBloom();
			if (logsBloom.contain(txHash)) {
				isTx = true;
				break;
			}
		}
		resMessage = txHash + (isTx ? " is included in Block " + String.valueOf(blockHeight) : " is not included anywhere");

		return resMessage;
	}
}
