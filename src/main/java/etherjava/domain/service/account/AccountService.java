package etherjava.domain.service.account;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etherjava.domain.model.account.Account;
import etherjava.domain.repository.account.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	public Account findOne(String addressToString) {
		return accountRepository.findOne(addressToString);
	}

	public void save(String addressToString, double balance, int nonce) {
		try {
			accountRepository.save(addressToString, balance, nonce);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
