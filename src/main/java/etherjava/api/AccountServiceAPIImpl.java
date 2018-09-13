package etherjava.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import etherjava.domain.model.account.Account;
import etherjava.domain.service.account.AccountManager;
import etherjava.domain.service.account.AccountService;

@Service
@AutoJsonRpcServiceImpl
public class AccountServiceAPIImpl implements AccountServiceAPI {

	@Autowired
	AccountService accountService;

	@Override
	public String getBalance(String address) {
		Account account = accountService.findOne(address);
		if (account == null) {
			return "{\"status\": \"failure\", \"message\": \"" + address + " does not exist\"}";
		}

		return String.valueOf(account.getBalance());
	}

	@Override
	public String newAccount() {
		Account account = AccountManager.createNewAccount(1000);
		try {
			accountService.save(account.getAddress().addressToString(), account.getBalance(), account.getNonce());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return account.getAddress().addressToString();
	}
}
