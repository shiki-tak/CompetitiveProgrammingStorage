package etherjava.domain.service.transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etherjava.domain.model.account.Account;
import etherjava.domain.model.transaction.Transaction;
import etherjava.domain.model.transaction.TransactionHash;
import etherjava.domain.repository.account.AccountRepository;
import etherjava.domain.repository.transaction.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;

	static List<Transaction> transactions = new ArrayList<>();

    /**
     * トランザクションの送信
     * @param to                    受信者のアドレス
     * @param from                  送信者のアドレス
     * @param value                 送信者から受信者へ送られるETHの量
     * @param data(optional field)  メッセージ
     * @param gasLimit              トランザクションの実行にかかる計算のステップ数の最大値
     * @param gasPrice              送信者が支払う、１計算ステップあたりの手数料
     * @return txHash
     *
     * 1 ether = 10^18 wei
     */

	// TODO: data parameterがある場合のexecTransaction
	public String sendTransaction(Account to, Account from, double value, double gasLimit, double gasPrice, int toAccountNonce, int fromAccountNonce) {

		double toBalance = to.getBalance();
		double fromBalance = from.getBalance();

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
		// (!) gasPriceをランダムな値にして同じハッシュ値にならないようにしている
		TransactionHash txHash = Transaction.calcTxHash(to.getAddress(), from.getAddress(), value, gasLimit, gasPrice * Math.random());

		// DBに登録するためのインスタンスを生成
		Transaction newTransaction = new Transaction(txHash, to.getAddress(), from.getAddress(), value, gasLimit, gasPrice, fromAccountNonce);
		// アカウント情報の更新
		try {
			accountRepository.save(to.getAddress().addressToString(), toBalance, toAccountNonce);
			accountRepository.save(from.getAddress().addressToString(), fromBalance, fromAccountNonce + 1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// トランザクションを保存
		transactionRepository.save(newTransaction);

		return txHash.getTxHashAsString();
		// API用のreturn
		// return "{\"status\": \"success\", \"message\": \"" + txHash + "\"}";
	}

	public Transaction getTransactionByHash(String txHash) {
		return transactionRepository.getTransactionByHash(txHash);
	}

}
