package etherjava.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import etherjava.domain.model.account.Account;
import etherjava.domain.model.transaction.Transaction;
import etherjava.domain.repository.account.AccountRepository;
import etherjava.domain.service.transaction.TransactionService;

@Service
@AutoJsonRpcServiceImpl
public class TransactionServiceAPIImpl implements TransactionServiceAPI {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionService transactionService;

	@Override
	public String sendTransaction(String to, String from, String valueToString, String gasLimitToString, String gasPriceToString) {

		double value = Double.parseDouble(valueToString);
		double gasLimit = Double.parseDouble(gasLimitToString);
		double gasPrice = Double.parseDouble(gasPriceToString);

		// 別サービスを直接呼び出すのってspringの設計的にあり...？
		Account toAccount;
		Account fromAccount;
		toAccount = accountRepository.findOne(to);

		if (toAccount == null) {
			return to + " does not exist";
		}

		fromAccount = accountRepository.findOne(from);
		if (fromAccount == null) {
			return from + " does not exist";
		}

		int fromAccountNonce = fromAccount.getNonce();
		int toAccountNonce = toAccount.getNonce();

		// TODO: メッセージを切り出す
		return transactionService.sendTransaction(toAccount, fromAccount, value, gasLimit, gasPrice, toAccountNonce, fromAccountNonce);
	}

	@Override
	public String getTransactionByHash(String txHash) {
		if (txHash == null) {
			return null;
		} else {
			Transaction transaction = transactionService.getTransactionByHash(txHash);
			// TODO: json形式でreturn
			return "{\"hash\": \"" + txHash + "\", "
					+ "\"to\": \"" + transaction.getTo().addressToString()
					+ "\", \"from\": \"" + transaction.getFrom().addressToString()
					+ "\", \"value\": \"" + transaction.getValue()
					+ "\", \"gasLimit\": \"" + transaction.getGasLimit()
					+ "\", \"gasPrice\": \"" + transaction.getGasPrice()
					+ "\", \"nonce\": \"" + transaction.getNonce() + "\"}";
		}
	}
}
