package etherjava.domain.service.blockchain;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

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

	public void createGenesisBlock(String merkleRoot, BloomFilter logsBloom, Transaction[] transactions) {
		Keccak.DigestKeccak kecc = new Keccak.Digest256();
		byte[] digest = kecc.digest("genesis block".getBytes());

		String previousHash = "0x0000000000000000000000000000000000000000000000000000000000000000";
		String blockHash = "0x" + Hex.toHexString(digest);
		int nonce = 42;
		long timeStamp = System.currentTimeMillis() / 1000L;
		String logsBloomToString = logsBloom.getBitFilterToString();

		// ヘッダ情報とトランザクション情報を合わせたブロックサイズ（バイト単位）
		// TODO: 真面目に計算
		long blockSize = calcBlockSize(previousHash, blockHash, merkleRoot, logsBloomToString, nonce, timeStamp, transactions);

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
	private long calcBlockSize(
			String previousHash,
			String blockHash,
			String merkleRoot,
			String logsBloomToString,
			int nonce,
			long timeStamp,
			Transaction[] transactions
			) {
		String sizeFactor = previousHash + blockHash + merkleRoot + logsBloomToString + String.valueOf(nonce) + String.valueOf(timeStamp);
		long blockSize = sizeFactor.length();

		return blockSize;
	}
}
