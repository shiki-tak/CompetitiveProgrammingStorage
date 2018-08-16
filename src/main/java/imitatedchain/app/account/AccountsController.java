package imitatedchain.app.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import accounts.Account;
import accounts.AccountManager;
import imitatedchain.domain.model.Accounts;
import imitatedchain.domain.service.AccountsService;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

	@Autowired
	AccountsService accountsService;

	@RequestMapping(method = RequestMethod.POST)
	public Accounts createNewAccount() {
		Account account = AccountManager.createNewAccount();

		Accounts registeredAccount = new Accounts();
		registeredAccount.setAddress(account.getAddress().addressToString());
		registeredAccount.setBalance(account.getBalance());
		registeredAccount.setNonce(account.getNonce());

		return accountsService.save(registeredAccount);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}

}
