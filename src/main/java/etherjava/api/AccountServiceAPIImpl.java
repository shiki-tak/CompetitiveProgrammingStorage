package etherjava.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import etherjava.domain.model.account.Account;
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
	public String newAccount(String balanceToString) {
		double balance = Double.parseDouble(balanceToString);
		Account account = accountService.createNewAccount(balance);

		return account.getAddress().addressToString();
	}
}
