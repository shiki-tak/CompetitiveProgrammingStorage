package etherjava.domain.model.blockchain;

import etherjava.trie.BloomFilter;

public class BlockHeader {

	private String parentHash;
	private String blockHash;
	private String merkleRoot;
	private BloomFilter logsBloom;
	private String stateRoot;

	private int nonce;
	private long timeStamp;

	public BlockHeader(String parentHash, String blockHash, String merkleRoot, BloomFilter logsBloom, int nonce, long timeStamp) {
		this.parentHash = parentHash;
		this.blockHash = blockHash;
		this.merkleRoot = merkleRoot;
		this.logsBloom = logsBloom;
		this.nonce = nonce;
		this.timeStamp = timeStamp;
	}

	public BlockHeader(String parentHash, String merkleRoot, BloomFilter logsBloom) {
		this.parentHash = parentHash;
		this.blockHash = null;
		this.merkleRoot = merkleRoot;
		this.logsBloom = logsBloom;
		this.nonce = 0;
		this.timeStamp = 0;
	}
	public BlockHeader(String blockHash, int nonce, long timeStamp) {
		this.blockHash = blockHash;
		this.nonce = nonce;
		this.timeStamp = timeStamp;
	}
	// getter
	public String getParentHash() { return parentHash; }
	public String getBlockHash() { return blockHash; }
	public String getMerkleRoot() { return merkleRoot; }
	public String getStateRoot() { return stateRoot; }
	public BloomFilter getLogsBloom() { return logsBloom; }
	public int getNonce() { return nonce; }
	public long getTimeStamp() { return timeStamp; }

	// setter
	public void setParentHash(String parentHash) { this.parentHash = parentHash; }
	public void setBlockHash(String blockHash) { this.blockHash = blockHash; }
	public void setMerkleRoot(String merkleRoot) { this.merkleRoot = merkleRoot; }
	public void setStateRoot(String stateRoot) { this.stateRoot = stateRoot; }
	public void setLogsBloom(BloomFilter logsBloom) { this.logsBloom = logsBloom; }
	public void setNonce(int nonce) { this.nonce = nonce; }
	public void setTimeStamp(long timeStamp) { this.timeStamp = timeStamp; }
}
