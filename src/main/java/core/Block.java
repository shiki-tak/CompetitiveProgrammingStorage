package core;

public interface Block {
	int height = 0;
	String previousHash = "0x0";
	String blockHash = "0x0";
	String merkleRoot = "0x0";
	int nonce = 0;
	int timeStamp = 0;
}
