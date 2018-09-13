package sample.hbase.messaging;

public class Message {

	private String messageId;

	private long userId;

	private String userName;

	private String body;

	private long postAt;

	// getter
	public String getMessageId() { return messageId; }
	public long getUserId() { return userId; }
	public String getUserName() { return userName; }
	public String getBody() { return body; }
	public long getPostAt() { return postAt; }

	// setter
	public void setMessageId(String messageId) { this.messageId = messageId; }
	public void setUserId(long userId) { this.userId = userId; }
	public void setUserName(String userName) { this.userName = userName; }
	public void setBody(String body) { this.body = body; }
	public void setPostAt(long postAt) { this.postAt = postAt; }
}
