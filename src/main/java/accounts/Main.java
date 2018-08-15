package accounts;

public class Main {
	public static void main(String[] args) {
		Account account = AccountManager.createNewAccount();
		System.out.println(account.getAddress().addressToString());
		System.out.println(account.getBalance());
	}
}
