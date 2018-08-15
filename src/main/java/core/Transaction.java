package core;

import java.util.ArrayList;
import java.util.List;

import accounts.Address;
import trie.BloomFilter;

public class Transaction {

	// Transactionの構造
	private Address to;     // 受信者
	private Address from;   // 送信者
	private int value;      // 送信者から受領人へ送られるETHの量
	private String data;    // オプショナルフィールド
	private int gasLimit;   // トランザクションの実行にかかる計算のステップ数の最大値
	private int gasPrice;   // 送信者が支払う、１計算ステップあたりの手数料
	private int nonce;      // fromが今までに実行したトランザクションの回数

	// getter
	public Address getTo() { return to; }
	public Address getFrom() { return from; }
	public int getValue() { return value; }
	public String getData() { return data; }
	public int getGasLimit() { return gasLimit; }
	public int getGasPrice() { return gasPrice; }
	public int getNonce() { return nonce; }

	// setter
	public void setTo(Address to) { this.to = to; }
	public void setFrom(Address from) { this.from = from; }
	public void setValue(int value) { this.value = value; }
	public void setData(String data) { this.data = data; }
	public void setGasLimit(int gasLimit) { this.gasLimit = gasLimit; }
	public void setGasPrice(int gasPrice) { this.gasPrice = gasPrice; }
	public void setNonce(int nonce) { this.nonce = nonce; }

	// TODO: Transactionクラスの実装
	public static List<String> generateTransaction(int n) {
		List<String> txs = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			txs.add("Tx" + "-" + String.valueOf(n) + "-" + String.valueOf(i));
		}
		return txs;
	}

	public static String searchTransaction(Blockchain blockChain, String tx) {
		String resMessage;

		boolean isTx = false;
		int blockHeight = 0;
		for (; blockHeight < blockChain.getLatestBlockIndex(); blockHeight++) {
			BloomFilter logsBloom = blockChain.getBlock(blockHeight).getBlockHeader().getLogsBloom();
			if (logsBloom.contain(tx)) {
				isTx = true;
				break;
			}
		}
		resMessage = tx + (isTx ? " is included in Block " + String.valueOf(blockHeight) : " is not included anywhere");

		return resMessage;
	}
}
