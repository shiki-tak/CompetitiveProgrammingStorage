package core;

//TransactionHash型を定義
public class TransactionHash {
	private String txHashAsString;

	TransactionHash(String txHashAsString) {
		this.txHashAsString = txHashAsString;
	}

	// getter
	public String getTxHashAsString() { return this.txHashAsString; }

	// setter
	public void setTxHashAsString(String txHashAsString) { this.txHashAsString = txHashAsString; }
}
