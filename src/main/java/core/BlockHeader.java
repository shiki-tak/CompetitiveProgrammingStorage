package core;

public class BlockHeader {

	private String previousHash;
	private String blockHash;
	private String merkleRoot;
	private String stateRoot;

	private int nonce;
	private long timeStamp;

	// getter
	public String getPreviousHash() { return previousHash; }
	public String getBlockHash() { return blockHash; }
	public String getMerkleRoot() { return merkleRoot; }
	public String getStateRoot() { return stateRoot; }
	public int getNonce() { return nonce; }
	public long getTimeStamp() { return timeStamp; }

	// setter
	public void setPreviousHash(String previousHash) { this.previousHash = previousHash; }
	public void setBlockHash(String blockHash) { this.blockHash = blockHash; }
	public void setMerkleRoot(String merkleRoot) { this.merkleRoot = merkleRoot; }
	public void setStateRoot(String stateRoot) { this.stateRoot = stateRoot; }
	public void setNonce(int nonce) { this.nonce = nonce; }
	public void setTimeStamp(long timeStamp) { this.timeStamp = timeStamp; }
}
