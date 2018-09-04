package messaging.domain.service;

import java.io.IOException;
import java.util.List;

public interface MessagingService {
	// メッセージを送信する
	void sendMessage(long roomId, String userId, String body) throws IOException;

	// 最新のメッセージを取得する
	List<Message> getNewMessage(long roomId, Message latestReceivedMessage) throws IOException;
}
