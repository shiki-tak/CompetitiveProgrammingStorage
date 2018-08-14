package core;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

import trie.BloomFilter;

public final class Blockchain {
	private int latestBlockIndex;
	private List<Block> blocks = new ArrayList<>();

	// getter
	public int getLatestBlockIndex() { return this.latestBlockIndex; }
	public Block getLatestBlock() { return this.blocks.get(latestBlockIndex - 1); }

	public void createGenesisBlock(String merkleRoot) {
		Keccak.DigestKeccak kecc = new Keccak.Digest256();
		byte[] digest = kecc.digest("genesis block".getBytes());
		BloomFilter logsBloom = new BloomFilter();
		String blockHash = "0x" + Hex.toHexString(digest);
		Block block = new Block(
				"0x0000000000000000000000000000000000000000000000000000000000000000",
				blockHash,
				merkleRoot,
				logsBloom,
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
