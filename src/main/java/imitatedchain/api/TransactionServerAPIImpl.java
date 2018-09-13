package imitatedchain.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import imitatedchain.domain.model.Accounts;
import imitatedchain.domain.service.AccountsService;
import imitatedchain.domain.service.TransactionsService;

@Service
@AutoJsonRpcServiceImpl
public class TransactionServerAPIImpl implements TransactionServerAPI {

	@Autowired
	AccountsService accountsService;
	@Autowired
	TransactionsService transactionsService;

	@Override
	public String sendTransaction(String toAddress, String fromAddress, int value, String data, double gasLimit,
			double gasPrice) {

		// 別サービスを直接呼び出すのってspringの設計的にあり...？
		Accounts toAccount;
		Accounts fromAccount;
		toAccount = accountsService.findOne(toAddress);

		if (toAccount == null) {
			return toAddress + " does not exist";
		}

		fromAccount = accountsService.findOne(fromAddress);
		if (fromAccount == null) {
			return fromAddress + " does not exist";
		}

		int fromAccountNonce = fromAccount.getNonce();
		int toAccountNonce = toAccount.getNonce();

		// TODO: メッセージを切り出す
		return transactionsService.execTransaction(toAccount, fromAccount, value, gasLimit, gasPrice, toAccountNonce, fromAccountNonce);
	}
}
