package imitatedchain.app.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import imitatedchain.domain.model.Accounts;
import imitatedchain.domain.model.Transactions;
import imitatedchain.domain.service.AccountsService;
import imitatedchain.domain.service.TransactionsService;

@Controller
@RequestMapping("/api/transactions")
public class TransactionsController {

	@Autowired
	AccountsService accountsService;
	@Autowired
	TransactionsService transactionsService;

	private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * トランザクションの送信
     * @param to                    受信者のアドレス
     * @param from                  送信者のアドレス
     * @param value                 送信者から受信者へ送られるETHの量
     * @param data(optional field)  メッセージ
     * @param gasLimit              トランザクションの実行にかかる計算のステップ数の最大値
     * @param gasPrice              送信者が支払う、１計算ステップあたりの手数料
     * @return txHash
     */
	@RequestMapping(method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public String sendTransaction(@RequestBody Transactions sendTransactionParams) {

		// 別サービスを直接呼び出すのってspringの設計的にあり...？
		Accounts toAccount;
		Accounts fromAccount;
		toAccount = accountsService.findOne(sendTransactionParams.getToAddress());

		if (toAccount == null) {
			return "{\"status\": \"failure\", \"message\": \"" + sendTransactionParams.getToAddress() + " does not exist\"}";
		}

		fromAccount = accountsService.findOne(sendTransactionParams.getFromAddress());
		if (fromAccount == null) {
			return "{\"status\": \"failure\", \"message\": \"" + sendTransactionParams.getFromAddress() + " does not exist\"}";
		}


		double value = sendTransactionParams.getValue();

		double gasLimit = sendTransactionParams.getGasLimit();
		double gasPrice = sendTransactionParams.getGasPrice();

		int fromAccountNonce = fromAccount.getNonce();
		int toAccountNonce = toAccount.getNonce();

		// TODO: メッセージを切り出す
		return transactionsService.execTransaction(toAccount, fromAccount, value, gasLimit, gasPrice, toAccountNonce, fromAccountNonce);
	}

	@RequestMapping(value = "/{txHash}", method = RequestMethod.GET)
	@ResponseBody
	public String getTransactionInfo(@PathVariable String txHash) throws JsonProcessingException {
		Transactions transaction = transactionsService.findOne(txHash);
		String transactionAsJSON = objectMapper.writeValueAsString(transaction);
		return transactionAsJSON;
	}
}
