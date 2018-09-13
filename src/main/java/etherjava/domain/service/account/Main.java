package etherjava.domain.service.account;

import etherjava.domain.model.Account;

public class Main {
	public static void main(String[] args) {
		AccountManager.createNewAccount();
		AccountManager.createNewAccount(1000);

		for (Account account : AccountManager.accounts) {
			System.out.println(account.getAddress().addressToString());
			System.out.println(account.getBalance());
			System.out.println(account.getNonce());
		}
	}
}