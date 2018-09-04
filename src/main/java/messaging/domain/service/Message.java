package messaging;

public class Message {

	private String messageId;  // メッセージID
	private String userId;     // ユーザID
	private String body;       // メッセージ本体
	private long postAt;       // 送信日時

	// getter
	public String getMessageId() { return messageId; }
	public String getUserId() { return userId; }
	public String getBody() { return body; }
	public long getPostAt() { return postAt; }

	// setter
	public void setMessageId(String messageId) { this.messageId = messageId; }
	public void setUserId(String userId) { this.userId = userId; }
	public void setBody(String body) { this.body = body; }
	public void setPostAt(long postAt) { this.postAt = postAt; }
}
