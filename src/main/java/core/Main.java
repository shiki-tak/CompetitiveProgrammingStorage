package core;

import accounts.Account;
import accounts.AccountManager;

// SendTransaction Test
public class Main {
	public static void main(String[] args) {

		Account account1 = AccountManager.createNewAccount();
		System.out.println("Before Transfer");
		System.out.println("account 1");
		System.out.println(account1.getAddress().addressToString());
		System.out.println(account1.getBalance());
		System.out.println(account1.getNonce());

		System.out.println("account 2");
		Account account2 = AccountManager.createNewAccount(1000);
		System.out.println(account2.getAddress().addressToString());
		System.out.println(account2.getBalance());
		System.out.println(account2.getNonce());

		System.out.println("Transfer 1 ether from account 2 to account 1");
		double value = 1;
		double gasLimit = 21000;
		double gasPrice = 5 * Math.pow(10, 6);

		TransactionHash txHash = Transaction.sendTransaction(account1.getAddress(), account2.getAddress(), value, gasLimit, gasPrice);

		System.out.println("Transaction Hash " + Transaction.getTransaction(txHash).getTxHash().getTxHashAsString());
		System.out.println("After Transfer");

		System.out.println("account 1");
		System.out.println(account1.getAddress().addressToString());
		System.out.println(account1.getBalance());
		System.out.println(account1.getNonce());

		System.out.println("account 2");
		System.out.println(account2.getAddress().addressToString());
		System.out.println(account2.getBalance());
		System.out.println(account2.getNonce());

		System.out.println("*** Transaction Information ***");
		Transaction tx = Transaction.getTransaction(txHash);
		System.out.println("Transaction Hash: " + tx.getTxHash().getTxHashAsString());
		System.out.println("To: " + tx.getTo().addressToString());
		System.out.println("From: " + tx.getFrom().addressToString());
		System.out.println("Value: " + String.valueOf(tx.getValue()));
		System.out.println("GasLimit: " + String.valueOf(tx.getGasLimit()));
		System.out.println("GasPrice: " + String.valueOf(tx.getGasPrice()));
	}
}
