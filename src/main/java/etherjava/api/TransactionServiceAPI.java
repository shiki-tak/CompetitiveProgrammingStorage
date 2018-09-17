package etherjava.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/transaction")
public interface TransactionServiceAPI {
    /**
     * トランザクションの送信
     * @param toAddress             受信者のアドレス
     * @param fromAddress           送信者のアドレス
     * @param value                 送信者から受信者へ送られるETHの量
     * @param data(optional field)  メッセージ
     * @param gasLimit              トランザクションの実行にかかる計算のステップ数の最大値
     * @param gasPrice              送信者が支払う、１計算ステップあたりの手数料
     * @return txHash
     */
	String sendTransaction(@JsonRpcParam(value = "toAddress") String toAddress,
						   @JsonRpcParam(value = "fromAddress") String fromAddress,
						   @JsonRpcParam(value = "value") String value,
						   @JsonRpcParam(value = "gasLimit") String gasLimit,
						   @JsonRpcParam(value = "gasPrice") String gasPrice);

	String getTransactionByHash(@JsonRpcParam(value = "txHash") String txHash);
}
