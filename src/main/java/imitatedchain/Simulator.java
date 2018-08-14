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

	// TODO: Transactionクラスの実装
	public static List<String> generateTransaction(int n) {
		List<String> txs = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			txs.add("Tx" + "-" + String.valueOf(n) + "-" + String.valueOf(i));
		}
		return txs;
	}

	public static String searchTransaction(Blockchain blockChain, String tx) {
		String resMessage;

		boolean isTx = false;
		int blockHeight = 0;
		for (; blockHeight < blockChain.getLatestBlockIndex(); blockHeight++) {
			BloomFilter logsBloom = blockChain.getBlock(blockHeight).getBlockHeader().getLogsBloom();
			if (logsBloom.contain(tx)) {
				isTx = true;
				break;
			}
		}
		resMessage = (isTx ? "Block " + String.valueOf(blockHeight) + " contain " : "not contain ") + tx;

		return resMessage;
	}

	public static void main(String[] args) {
		String merkleRoot = "0x0";
		Blockchain blockChain = new Blockchain();

		for (int i = 0; i < 10; i++) {
			List<String> txs = generateTransaction(i);
			BloomFilter logsBloom = new BloomFilter();
			List<MerkleHash> merkleHashList = new ArrayList<>();

			for (int l = 0; l < txs.size(); l++) {
				logsBloom.add(txs.get(l));
				merkleHashList.add(new MerkleHash(txs.get(l)));
			}

			// Create Merkle Tree
			MerkleTree merkleTree = MerkleTree.createMerkleTree(merkleHashList);
			merkleRoot =  merkleTree.getMerkleRoot().getMerkleHash().sha256HexBinary();

			if (i == 0) {
				// Genesis blockの作成
				blockChain.createGenesisBlock(merkleRoot, logsBloom);
			} else {
				// blockHashがnullのblockを生成する
				Block block = new Block(
						blockChain.getLatestBlock().getBlockHeader().getBlockHash(),
						merkleRoot,
						logsBloom
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
		// トランザクションを検索する
		System.out.println("*** Search Transaction ***");
		System.out.println(searchTransaction(blockChain, "Tx-1-1"));
		System.out.println(searchTransaction(blockChain, "Tx-11-0"));
		System.out.println(searchTransaction(blockChain, "Tx-2-1"));
		System.out.println(searchTransaction(blockChain, "Tx-5-2"));
	}
}
