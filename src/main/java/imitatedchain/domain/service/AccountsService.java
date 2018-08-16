package imitatedchain.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imitatedchain.domain.model.Accounts;
import imitatedchain.domain.repository.AccountsRepository;

@Service
@Transactional
public class AccountsService {
	@Autowired
	AccountsRepository accountsRepository;

	public Accounts save(String address, double balance, int nonce) {
		// DBに登録するためのインスタンスを生成
		Accounts newAccount = new Accounts(address, balance, nonce);
		return accountsRepository.save(newAccount);
	}

}
