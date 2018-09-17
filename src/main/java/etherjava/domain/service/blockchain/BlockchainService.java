package etherjava.domain.service.blockchain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etherjava.domain.model.blockchain.Block;
import etherjava.domain.model.transaction.Transaction;
import etherjava.domain.repository.blockchain.BlockchainRepository;

@Service
public final class BlockchainService {

	@Autowired
	BlockchainRepository blockChainRepository;

	private int latestBlockIndex;
	private List<Block> blocks = new ArrayList<>();

	// getter
	public int getLatestBlockIndex() { return this.latestBlockIndex; }
	// TODO: HBaseから取得
	public Block getLatestBlock() throws IOException {
		Block block = blockChainRepository.findOne(latestBlockIndex);
		return block;
	}
	public Block getBlock(int blockHeight) { return this.blocks.get(blockHeight); }

	public void createGenesisBlock(long blockSize, String previousHash, String merkleRoot, String blockHash, String logsBloom, int nonce, long timeStamp, List<Transaction> transactions) {

		Block block = new Block(
				blockSize,
				latestBlockIndex,
				previousHash,
				blockHash,
				merkleRoot,
				logsBloom,
				nonce,
				timeStamp
				);

		append(block);
	}

	public void append(Block block) {
		block.setHeight(latestBlockIndex);
		// TODO: HBaseに保存
		// blocks.add(block);
		try {
			blockChainRepository.save(block);
			latestBlockIndex++;
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			long timeStamp
			) {
		String sizeFactor = previousHash + blockHash + merkleRoot + logsBloomToString + String.valueOf(nonce) + String.valueOf(timeStamp);
		long blockSize = sizeFactor.length();

		return blockSize;
	}
}
