package core;

public class BlockHeader {

	private String PreviousHash = "0x0";
	private String BlockHash = "0x0";
	private String MerkleRoot = "0x0";
	private String StateRoot = "0x0";
	private int Nonce = 0;
	private long TimeStamp = 0;

	public String getPreviousHash() {
		return PreviousHash;
	}

	public void setPreviousHash(String previousHash) {
		PreviousHash = previousHash;
	}

	public String getBlockHash() {
		return BlockHash;
	}

	public void setBlockHash(String blockHash) {
		BlockHash = blockHash;
	}

	public String getMerkleRoot() {
		return MerkleRoot;
	}

	public void setMerkleRoot(String merkleRoot) {
		MerkleRoot = merkleRoot;
	}

	public int getNonce() {
		return Nonce;
	}

	public void setNonce(int nonce) {
		Nonce = nonce;
	}

	public long getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		TimeStamp = timeStamp;
	}

	public String getStateRoot() {
		return StateRoot;
	}

	public void setStateRoot(String stateRoot) {
		StateRoot = stateRoot;
	}
}
