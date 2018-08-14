package imitatedchain;

import java.util.ArrayList;
import java.util.List;

import consensus.PoW;
import consensus.PoWResult;
import core.Block;
import core.Blockchain;
import trie.BloomFilter;
import trie.MerkleHash;
import trie.MerkleTree;

public class Simulator {

	public static void main(String[] args) {
		String merkleRoot = "0x0";
		Blockchain blockChain = new Blockchain();

		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				BloomFilter logsBloom = new BloomFilter();

				logsBloom.add("L1");
				logsBloom.add("L2");
				logsBloom.add("L3");
				logsBloom.add("L4");

				List<MerkleHash> merkleHashList = new ArrayList<>();
				merkleHashList.add(new MerkleHash("L1"));
				merkleHashList.add(new MerkleHash("L2"));
				merkleHashList.add(new MerkleHash("L3"));
				merkleHashList.add(new MerkleHash("L4"));

				MerkleTree merkleTree = MerkleTree.createMerkleTree(merkleHashList);
				merkleRoot =  merkleTree.getMerkleRoot().getMerkleHash().sha256HexBinary();

				// Genesis blockの作成
				blockChain.createGenesisBlock(merkleRoot, logsBloom);
			} else {
				merkleRoot = String.valueOf(i);
				// blockHashがnullのblockを生成する
				Block block = new Block(
						blockChain.getLatestBlock().getBlockHeader().getBlockHash(),
						merkleRoot
						);
				PoW pow = new PoW(block, merkleRoot);
				PoWResult powResult = pow.exec();
				// PoWに成功したらBlockHeaderをセットする
				block.setBlockHeader(powResult.blockHash, powResult.nonce,powResult.timeStamp);

				blockChain.append(block);
			}

			// 作成したブロックを出力
			System.out.printf("*** Block %d *** %n", blockChain.getLatestBlock().getHeight());
			System.out.printf("TimeStamp: %d%n", blockChain.getLatestBlock().getBlockHeader().getTimeStamp());
			System.out.printf("Hash: %s%n", blockChain.getLatestBlock().getBlockHeader().getBlockHash());
			System.out.printf("Previous Hash: %s%n", blockChain.getLatestBlock().getBlockHeader().getParentHash());
			System.out.printf("Merkle Root: %s%n", blockChain.getLatestBlock().getBlockHeader().getMerkleRoot());
			System.out.printf("Logs Bloom: %s%n", blockChain.getLatestBlock().getBlockHeader().getLogsBloom());
			System.out.printf("Nonce: %d%n", blockChain.getLatestBlock().getBlockHeader().getNonce());
			System.out.println();
		}
	}
}
