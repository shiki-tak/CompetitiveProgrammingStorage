package core;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blockchain implements IBlockchain {

	private int LatestBlockIndex = 0;

	@Override
	public Block CreateGenesisBlock(String MerkleRoot) {
		String text = "genesis block";
		String BlockHash = "0x0";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] BlockHashAsBytes = digest.digest(text.getBytes());

			BlockHash = String.format("%040x", new BigInteger(1, BlockHashAsBytes));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
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

		block.Height = LatestBlockIndex;
		block.PreviousHash = PreviousHash;
		block.BlockHash = BlockHash;
		block.MerkleRoot = MerkleRoot;
		block.Nonce = Nonce;
		block.TimeStamp = TimeStamp;

		LatestBlockIndex++;

		return block;
	}

	@Override
	public int GetLatestBlockIndex() {
		return this.LatestBlockIndex;
	}


}
