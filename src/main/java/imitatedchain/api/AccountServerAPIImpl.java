package imitatedchain.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import imitatedchain.domain.model.Accounts;
import imitatedchain.domain.service.AccountsService;

@Service
@AutoJsonRpcServiceImpl
public class AccountServerAPIImpl implements JsonRpcServerAPI {
	@Autowired
	AccountsService accountsService;

	@Override
	public String getBalance(String address) {
		Accounts account = accountsService.findOne(address);
		if (account == null) {
			return "{\"status\": \"failure\", \"message\": \"" + address + " does not exist\"}";
		}

		return String.valueOf(account.getBalance());
	}
}
