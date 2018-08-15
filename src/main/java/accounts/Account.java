package accounts;

public class Account {

	private Address address;    // アドレス
	private double balance;        // 残高
	private int nonce;          // トランザクションを実行した回数

	public Account(Address address) {
		this.address = address;
	}

	// 初期残高ありのテスト用コンストラクタ
	public Account(Address address, int balance) {
		this(address);
		this.balance = balance;
	}
	// getter
	public Address getAddress() { return address; }
	public double getBalance() { return balance; }
	public int getNonce() { return nonce; }

	// setter
	public void setAddress(Address address) { this.address = address; }
	public void setBalance(double balance) { this.balance = balance; }
	public void setNonce(int nonce) { this.nonce = nonce; }
}
