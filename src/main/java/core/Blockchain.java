package core;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class Blockchain implements IBlockchain {

	private int latestBlockIndex = 0;

	public List<Block> blocks = new ArrayList<>();

	@Override
	public Block CreateGenesisBlock(String merkleRoot) {
		String input = "genesis block";

		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] digest = digestSHA3.digest(input.getBytes());

		String blockHash = "0x" + Hex.toHexString(digest);

		return CreateBlock(
				"0x0000000000000000000000000000000000000000000000000000000000000000",
				blockHash,
				merkleRoot,
				42,
				System.currentTimeMillis() / 1000L
				);
	}

	@Override
	public Block CreateBlock(String previousHash, String blockHash, String merkleRoot, int nonce, long timeStamp) {

		Block block = new Block(previousHash, blockHash, merkleRoot, nonce, timeStamp);
		// TODO: blockのインスタンスを作った後にblockのheightをセットするのはイマイチ...
		block.setHeight(latestBlockIndex);

		latestBlockIndex++;

		return block;
	}

	@Override
	public int GetLatestBlockIndex() {
		return this.latestBlockIndex;
	}
}
