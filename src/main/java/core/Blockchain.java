package core;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public final class Blockchain {
	private int latestBlockIndex;
	private List<Block> blocks = new ArrayList<>();

	// getter
	public int getLatestBlockIndex() { return this.latestBlockIndex; }
	public Block getLatestBlock() { return this.blocks.get(latestBlockIndex - 1); }

	public void createGenesisBlock(String merkleRoot) {
		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] digest = digestSHA3.digest("genesis block".getBytes());
		String blockHash = "0x" + Hex.toHexString(digest);
		Block block = new Block(
				"0x0000000000000000000000000000000000000000000000000000000000000000",
				blockHash,
				merkleRoot,
				42,
				System.currentTimeMillis() / 1000L
				);
		append(block);
	}

	public void append(Block block) {
		block.setHeight(latestBlockIndex);
		blocks.add(block);
		latestBlockIndex++;
	}

}
