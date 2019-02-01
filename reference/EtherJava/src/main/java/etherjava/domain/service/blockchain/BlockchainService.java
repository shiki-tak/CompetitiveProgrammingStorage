package etherjava.domain.service.blockchain;

import java.io.IOException;
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

	private int blockIndex;

	// getter
	public int getblockIndex() { return this.blockIndex; }
	// TODO: HBaseから取得
	public Block getLatestBlock() throws IOException {
		Block block = blockChainRepository.findOne(blockIndex - 1);
		return block;
	}

	public Block getBlockByHeight(int height) throws IOException {
		 return blockChainRepository.findOne(height);
	}
	public Block getBlockByHash(String blockHash) throws IOException {
		int index = 0;
		// 一致するblockHashが見つかるまでループ
		while (index < blockIndex) {
			Block block = blockChainRepository.findOne(index);
			if (blockHash.equals(block.getBlockHeader().getBlockHash())) {
				return block;
			} else {
				index++;
			}
		}
		return null;
	}

	public void createGenesisBlock(long blockSize, String previousHash, String merkleRoot, String blockHash, String logsBloom, int nonce, long timeStamp, List<Transaction> transactions) {

		Block block = new Block(
				blockSize,
				blockIndex,
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
		block.setHeight(blockIndex);
		// TODO: HBaseに保存
		// blocks.add(block);
		try {
			blockChainRepository.save(block);
			blockIndex++;
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
