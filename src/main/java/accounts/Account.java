package accounts;

public class Account {

	private Address address;	// アドレス
	private int balance;		// 残高

	public Account(Address address) {
		this.address = address;
		balance = 0;
	}
	// getter
	public Address getAddress() { return address; }
	public int getBalance() { return balance; }

	// setter
	public void setAddress(Address address) { this.address = address; }
	public void setBalance(int balance) { this.balance = balance; }

}
