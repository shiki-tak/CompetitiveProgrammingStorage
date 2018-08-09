package core;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class Blockchain implements IBlockchain {

	private int LatestBlockIndex = 0;

	public List<Block> Blocks = new ArrayList<>();

	@Override
	public Block CreateGenesisBlock(String MerkleRoot) {
		String input = "genesis block";
		String BlockHash = "0x0";

		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] digest = digestSHA3.digest(input.getBytes());

		BlockHash = "0x" + Hex.toHexString(digest);

		return CreateBlock(
				"0x0000000000000000000000000000000000000000000000000000000000000000",
				BlockHash,
				MerkleRoot,
				42,
				System.currentTimeMillis() / 1000L
				);
	}

	@Override
	public Block CreateBlock(String PreviousHash, String BlockHash, String MerkleRoot, int Nonce, long TimeStamp) {

		Block block = new Block();

		block.setHeight(LatestBlockIndex);
		block.setPreviousHash(PreviousHash);
		block.setBlockHash(BlockHash);
		block.setMerkleRoot(MerkleRoot);
		block.setNonce(Nonce);
		block.setTimeStamp(TimeStamp);

		LatestBlockIndex++;

		return block;
	}

	@Override
	public int GetLatestBlockIndex() {
		return this.LatestBlockIndex;
	}


}
