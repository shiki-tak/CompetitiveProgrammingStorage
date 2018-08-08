package core;

import java.util.ArrayList;
import java.util.List;

public interface IBlockchain {
	List<IBlock> blocks = new ArrayList<>();

	Block CreateGenesisBlock(String MerkleRoot);

	Block CreateBlock(String PreviousHash, String BlockHash, String MerkleRoot, int Nonce, long TimeStamp);

	int GetLatestBlockIndex();

}
