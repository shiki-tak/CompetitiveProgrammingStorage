package etherjava.domain.model.blockchain;

import etherjava.utils.trie.BloomFilter;

public class Block {
	private int height;
	private Transaction[] transactions;
	private BlockHeader blockHeader;

	public Block() {}

	public Block(String parentHash, String blockHash, String merkleRoot, BloomFilter logsBloom, int nonce, long timeStamp) {
		BlockHeader blockHeader = new BlockHeader(
				parentHash,
				blockHash,
				merkleRoot,
				logsBloom,
				nonce,
				timeStamp
				);
		this.blockHeader = blockHeader;
	}

	// generate pending block
	public Block(String parentHash, String merkleRoot, BloomFilter logsBloom) {
		BlockHeader blockHeader = new BlockHeader(
				parentHash,
				merkleRoot,
				logsBloom
				);
		this.blockHeader = blockHeader;
	}

	// getter
	public int getHeight() { return height; }
	public BlockHeader getBlockHeader() { return blockHeader; }
	public Transaction[] getTransactions() { return transactions; }

	// setter
	public void setHeight(int height) { this.height = height; }
	public void setBlockHeader(String blockHash, int nonce, long timeStamp) {
		this.blockHeader.setBlockHash(blockHash);
		this.blockHeader.setNonce(nonce);
		this.blockHeader.setTimeStamp(timeStamp);
	}
	public void setTransactions(Transaction[] transactions) { this.transactions = transactions; }
}
