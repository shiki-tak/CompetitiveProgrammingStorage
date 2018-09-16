package etherjava.domain.model.transaction;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

import etherjava.domain.model.account.Address;
import etherjava.utils.rlp.RlpEncoder;
import etherjava.utils.rlp.RlpList;
import etherjava.utils.rlp.RlpString;

public class Transaction {

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
					   double gasPrice,
					   int nonce) {
		this.txHash = txHash;
		this.from = from;
		this.to = to;
		this.value = value;
		this.gasLimit = gasLimit;
		this.gasPrice = gasPrice;
		this.nonce = nonce;
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

	// setter
	public void setTxHash(TransactionHash txHash) { this.txHash = txHash; }
	public void setTo(Address to) { this.to = to; }
	public void setFrom(Address from) { this.from = from; }
	public void setValue(double value) { this.value = value; }
	public void setData(String data) { this.data = data; }
	public void setGasLimit(double gasLimit) { this.gasLimit = gasLimit; }
	public void setGasPrice(double gasPrice) { this.gasPrice = gasPrice; }
	public void setNonce(int nonce) { this.nonce = nonce; }

	public static TransactionHash calcTxHash(Address to, Address from, double value, double gasLimit, double gasPrice) {
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
		TransactionHash txHash = new TransactionHash(Hex.toHexString(txAsBytes));

		return txHash;
	}
}
