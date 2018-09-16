package etherjava.domain.service.blockchain;

import java.util.ArrayList;
import java.util.List;

import etherjava.domain.model.blockchain.Block;
import etherjava.domain.model.transaction.Transaction;
import etherjava.utils.trie.BloomFilter;


public final class BlockchainService {
	private int latestBlockIndex;
	private List<Block> blocks = new ArrayList<>();

	// getter
	public int getLatestBlockIndex() { return this.latestBlockIndex; }
	public Block getLatestBlock() { return this.blocks.get(latestBlockIndex - 1); }

	public Block getBlock(int blockHeight) { return this.blocks.get(blockHeight); }

	public void createGenesisBlock(long blockSize, String previousHash, String merkleRoot, String blockHash, BloomFilter logsBloom, int nonce, long timeStamp, List<Transaction> transactions) {

		Block block = new Block(
				blockSize,
				previousHash,
				blockHash,
				merkleRoot,
				logsBloom,
				nonce,
				timeStamp,
				transactions
				);

		append(block);
	}

	public void append(Block block) {
		block.setHeight(latestBlockIndex);
		blocks.add(block);
		latestBlockIndex++;
	}

	/**
	 * @param previousHash
	 * @param blockHash
	 * @param merkleRoot
	 * @param logsBloom
	 * @param nonce
	 * @param timeStamp
	 * @param transactions
	 */
	public long calcBlockSize(
			String previousHash,
			String blockHash,
			String merkleRoot,
			String logsBloomToString,
			int nonce,
			long timeStamp,
			List<Transaction> transactions
			) {
		String sizeFactor = previousHash + blockHash + merkleRoot + logsBloomToString + String.valueOf(nonce) + String.valueOf(timeStamp);
		long blockSize = sizeFactor.length();

		return blockSize;
	}
}
