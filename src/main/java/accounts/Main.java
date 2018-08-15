package accounts;

public class Main {
	public static void main(String[] args) {
		Account account = AccountManager.createNewAccount();
		System.out.println(account.getAddress().addressToString());
		System.out.println(account.getBalance());
		System.out.println(account.getNonce());

		Account account2 = AccountManager.createNewAccount(1000);
		System.out.println(account2.getAddress().addressToString());
		System.out.println(account2.getBalance());
		System.out.println(account2.getNonce());
	}
}
