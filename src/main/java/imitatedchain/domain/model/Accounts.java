package imitatedchain.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Accounts {
	@Id
	private String address;

	private double balance;

	private int nonce;

	// getter
	public String getAddress() { return address; }
	public double getBalance() { return balance; }
	public int getNonce() { return nonce; }

	// setter
	public void setAddress(String address) { this.address = address; }
	public void setBalance(double balance) { this.balance = balance; }
	public void setNonce(int nonce) { this.nonce = nonce; }
}
