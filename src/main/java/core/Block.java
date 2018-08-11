package core;

public class Block implements IBlock {

	private int Height = 0;

	private Transaction[] transactions;
	BlockHeader blockHeader = new BlockHeader();

	Block(String previousHash, String blockHash, String merkleRoot, int nonce, long timeStamp) {

		this.blockHeader.setPreviousHash(previousHash);
		this.blockHeader.setBlockHash(blockHash);
		this.blockHeader.setMerkleRoot(merkleRoot);
		this.blockHeader.setNonce(nonce);
		this.blockHeader.setTimeStamp(timeStamp);
	}

	public int getHeight() {
	return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}


	public BlockHeader getBlockHeader() {
		return blockHeader;
	}

	public void setBlockHeader(BlockHeader blockHeader) {
		this.blockHeader = blockHeader;
	}

	public Transaction[] getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction[] transactions) {
		this.transactions = transactions;
	}


}
