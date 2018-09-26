package messaging.domain.service;

import java.io.IOException;
import java.util.List;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

import messaging.domain.model.Message;

@JsonRpcService("/jsonrpc/message")
public interface MessagingService {
	// メッセージを送信する
	void sendMessage(@JsonRpcParam(value = "roomId") String roomId,
					@JsonRpcParam(value = "userId") String userId,
					@JsonRpcParam(value = "body") String body) throws IOException;

	// 最新のメッセージを取得する
	List<Message> getNewMessage(long roomId, Message latestReceivedMessage) throws IOException;
}
