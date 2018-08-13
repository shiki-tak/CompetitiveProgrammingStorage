package core;

public class Block {
	private int height;
	private Transaction[] transactions;
	private BlockHeader blockHeader;

	public Block(String parentHash, String blockHash, String merkleRoot, int nonce, long timeStamp) {
		BlockHeader blockHeader = new BlockHeader(
				parentHash,
				blockHash,
				merkleRoot,
				nonce,
				timeStamp
				);
		this.blockHeader = blockHeader;
	}

	// getter
	public int getHeight() { return height; }
	public BlockHeader getBlockHeader() { return blockHeader; }
	public Transaction[] getTransactions() { return transactions; }

	// setter
	public void setHeight(int height) { this.height = height; }
	public void setBlockHeader(BlockHeader blockHeader) { this.blockHeader = blockHeader; }
	public void setTransactions(Transaction[] transactions) { this.transactions = transactions; }
}
