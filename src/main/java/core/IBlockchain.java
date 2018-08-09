package core;

public interface IBlockchain {

	Block CreateGenesisBlock(String MerkleRoot);

	Block CreateBlock(String PreviousHash, String BlockHash, String MerkleRoot, int Nonce, long TimeStamp);

	int GetLatestBlockIndex();

}
