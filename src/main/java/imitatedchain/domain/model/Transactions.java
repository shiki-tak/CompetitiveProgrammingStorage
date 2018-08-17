package imitatedchain.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transactions {
	@Id
	private String txHash;

	private String toAddress;

	private String fromAddress;

	private double value;

	private String data;

	private double gasLimit;

	private double gasPrice;

	private int nonce;

	public Transactions() {}

	public Transactions(String txHash, String toAddress, String fromAddress, double value, double gasLimit, double gasPrice, int nonce) {
		this.txHash = txHash;
		this.setToAddress(toAddress);
		this.setFromAddress(fromAddress);
		this.setValue(value);
		this.setGasLimit(gasLimit);
		this.setGasPrice(gasPrice);
		this.setNonce(nonce);
	}

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(double gasLimit) {
		this.gasLimit = gasLimit;
	}

	public double getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(double gasPrice) {
		this.gasPrice = gasPrice;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}


}
