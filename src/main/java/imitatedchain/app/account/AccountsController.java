package imitatedchain.app.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import accounts.Account;
import accounts.AccountManager;
import imitatedchain.domain.model.Accounts;
import imitatedchain.domain.service.AccountsService;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

	@Autowired
	AccountsService accountsService;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@RequestMapping(method = RequestMethod.POST)
	public Accounts createNewAccount() {
		Account account = AccountManager.createNewAccount();

		return accountsService.save(account.getAddress().addressToString(), account.getBalance(), account.getNonce());
	}

	@RequestMapping(value = "/{address}", method = RequestMethod.GET)
	@ResponseBody
	public String getAccountInfo(@PathVariable String address) throws JsonProcessingException {
		Accounts account = accountsService.findOne(address);
		String accountAsJSON = objectMapper.writeValueAsString(account);
		return accountAsJSON;
	}
}
