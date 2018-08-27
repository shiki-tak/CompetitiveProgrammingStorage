package imitatedchain.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/jsonrpc/transaction")
public interface TransactionServerAPI {
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
						   @JsonRpcParam(value = "value") int value,
						   @JsonRpcParam(value = "data") String data,
						   @JsonRpcParam(value = "gasLimit") double gasLimit,
						   @JsonRpcParam(value = "gasPrice") double gasPrice);
}
