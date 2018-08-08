package core;

import java.util.*;

public interface Blockchain {
	List<Block> blocks = new ArrayList<>();

	Block createGenesisBlock(String merkleRoot);

	Block createBlock(String previousHash, String blockHash, String merkleRoot, int nonce, int timeStamp);

}
