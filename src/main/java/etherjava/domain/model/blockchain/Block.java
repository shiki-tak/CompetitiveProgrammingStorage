package etherjava.domain.model.blockchain;

import java.util.List;

import etherjava.domain.model.transaction.Transaction;
import etherjava.utils.trie.BloomFilter;

public class Block {
	private int height;
	private long blockSize;               // ブロックヘッダとトランザクションを合わせたブロックサイズ
	private List<Transaction> transactions;
	private BlockHeader blockHeader;

	public Block() {}

	public Block(long blockSize,
			String parentHash,
			String blockHash,
			String merkleRoot,
			BloomFilter logsBloom,
			int nonce,
			long timeStamp,
			List<Transaction> transactions) {
		this.blockSize = blockSize;
		BlockHeader blockHeader = new BlockHeader(
				parentHash,
				blockHash,
				merkleRoot,
				logsBloom,
				nonce,
				timeStamp
				);
		this.blockHeader = blockHeader;
		this.transactions = transactions;
	}

	// generate pending block
	public Block(String parentHash, String merkleRoot, BloomFilter logsBloom, List<Transaction> transactions) {
		BlockHeader blockHeader = new BlockHeader(
				parentHash,
				merkleRoot,
				logsBloom
				);
		this.blockHeader = blockHeader;
		this.transactions = transactions;
	}

	// getter
	public int getHeight() { return height; }
	public BlockHeader getBlockHeader() { return blockHeader; }
	public List<Transaction> getTransactions() { return transactions; }
	public long getBlockSize() { return blockSize; }

	// setter
	public void setHeight(int height) { this.height = height; }
	public void setBlockHeader(String blockHash, int nonce, long timeStamp) {
		this.blockHeader.setBlockHash(blockHash);
		this.blockHeader.setNonce(nonce);
		this.blockHeader.setTimeStamp(timeStamp);
	}
	public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
	public void setBlockSize(long blockSize) { this.blockSize = blockSize; }
}
