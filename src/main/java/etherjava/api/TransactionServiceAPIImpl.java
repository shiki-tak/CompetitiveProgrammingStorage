package etherjava.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import etherjava.domain.model.account.Account;
import etherjava.domain.model.blockchain.Transaction;
import etherjava.domain.service.account.AccountService;
import etherjava.domain.service.transaction.TransactionService;

@Service
@AutoJsonRpcServiceImpl
public class TransactionServiceAPIImpl implements TransactionServiceAPI {

	@Autowired
	AccountService accountService;
	@Autowired
	TransactionService transactionService;

	@Override
	public String sendTransaction(String toAddress, String fromAddress, double value, double gasLimit, double gasPrice) {

		// 別サービスを直接呼び出すのってspringの設計的にあり...？
		Account toAccount;
		Account fromAccount;
		toAccount = accountService.findOne(toAddress);

		if (toAccount == null) {
			return toAddress + " does not exist";
		}

		fromAccount = accountService.findOne(fromAddress);
		if (fromAccount == null) {
			return fromAddress + " does not exist";
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
