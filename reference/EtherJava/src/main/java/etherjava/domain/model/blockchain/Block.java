package etherjava.domain.model.blockchain;

import java.util.List;

import etherjava.domain.model.transaction.Transaction;

public class Block {
	private int height;
	private long blockSize;               // ブロックヘッダとトランザクションを合わせたブロックサイズ
	private BlockHeader blockHeader;

	public Block() {}

	public Block(
			long blockSize,
			int height,
			String parentHash,
			String blockHash,
			String merkleRoot,
			String logsBloom,
			int nonce,
			long timeStamp
			) {
		this.blockSize = blockSize;
		this.height = height;
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
	public Block(String parentHash, String merkleRoot, String logsBloom, List<Transaction> transactions) {
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
	public long getBlockSize() { return blockSize; }

	// setter
	public void setHeight(int height) { this.height = height; }
	public void setBlockHeader(String blockHash, int nonce, long timeStamp) {
		this.blockHeader.setBlockHash(blockHash);
		this.blockHeader.setNonce(nonce);
		this.blockHeader.setTimeStamp(timeStamp);
	}
	public void setBlockSize(long blockSize) { this.blockSize = blockSize; }
}
