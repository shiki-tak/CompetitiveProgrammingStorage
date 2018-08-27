package imitatedchain.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import accounts.Address;
import core.Transaction;
import imitatedchain.domain.model.Accounts;
import imitatedchain.domain.model.Transactions;
import imitatedchain.domain.repository.TransactionsRepository;

@Service
@Transactional
public class TransactionsService {

	@Autowired
	TransactionsRepository transactionsRepository;
	@Autowired
	AccountsService accountsService;

	// TODO: data parameterがある場合のexecTransaction
	public String execTransaction(Accounts toAccount, Accounts fromAccount, double value, double gasLimit, double gasPrice, int toAccountNonce, int fromAccountNonce) {

		double toBalance = toAccount.getBalance();
		double fromBalance = fromAccount.getBalance();

		// 手数料を計算
		double txFee = (gasLimit * gasPrice) * Math.pow(10, -18);

		// 送金者の残高が足りていなければエラー
		if (fromBalance < value + txFee) {
			// TODO: メッセージを切り出す
			String errorMessage = "balance not enough";
			return errorMessage;
		}

		// 送金
		toBalance += value;
		fromBalance -= (value + txFee);

		// トランザクションのハッシュ値を計算
		Address to = new Address(toAccount.getAddress());
		Address from = new Address(fromAccount.getAddress());
		// (!) gasPriceをランダムな値にして同じハッシュ値にならないようにしている
		String txHash = Transaction.calcTxHash(to, from, value, gasLimit, gasPrice * Math.random()).getTxHashAsString();

		// DBに登録するためのインスタンスを生成
		Transactions newTransaction = new Transactions(txHash, toAccount.getAddress(), fromAccount.getAddress(), value, gasLimit, gasPrice, fromAccountNonce);
		// アカウント情報の更新
		accountsService.save(toAccount.getAddress(), toBalance, toAccountNonce);
		accountsService.save(fromAccount.getAddress(), fromBalance, fromAccountNonce + 1);

		// トランザクションを保存
		transactionsRepository.save(newTransaction);

		return txHash;
		// API用のreturn
		// return "{\"status\": \"success\", \"message\": \"" + txHash + "\"}";
	}

	public Transactions findOne(String txHash) {
		return transactionsRepository.findByTxHash(txHash);
	}
}
