package etherjava.domain.service.blockchain;

import java.util.ArrayList;
import java.util.List;

import etherjava.domain.model.blockchain.Block;
import etherjava.domain.service.consensus.PoW;
import etherjava.domain.service.consensus.PoWResult;
import etherjava.trie.BloomFilter;
import etherjava.trie.MerkleHash;
import etherjava.trie.MerkleTree;


public class Simulator {

	public static void main(String[] args) {
		Blockchain blockChain = new Blockchain();

		for (int i = 0; i < 10; i++) {
			List<String> txs = new ArrayList<>();
			BloomFilter logsBloom = new BloomFilter();
			List<MerkleHash> merkleHashList = new ArrayList<>();

			txs.add("transaction-" + String.valueOf(i));

			for (int l = 0; l < txs.size(); l++) {
				logsBloom.add(txs.get(l));
				merkleHashList.add(new MerkleHash(txs.get(l)));
			}

			// Create Merkle Tree
			MerkleTree merkleTree = MerkleTree.createMerkleTree(merkleHashList);
			String merkleRoot =  merkleTree.getMerkleRoot().getMerkleHash().sha256HexBinary();

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
		}

		// 作成したブロックを出力
		for (int i = 0; i < 10; i++) {
			Block block = blockChain.getBlock(i);

			System.out.printf("*** Block %d *** %n", block.getHeight());
			System.out.printf("TimeStamp: %d%n", block.getBlockHeader().getTimeStamp());
			System.out.printf("Hash: %s%n", block.getBlockHeader().getBlockHash());
			System.out.printf("Previous Hash: %s%n", block.getBlockHeader().getParentHash());
			System.out.printf("Merkle Root: %s%n", block.getBlockHeader().getMerkleRoot());
			System.out.printf("Logs Bloom: %s%n", block.getBlockHeader().getLogsBloom());
			System.out.printf("Nonce: %d%n", block.getBlockHeader().getNonce());
			System.out.println();
		}
//		// トランザクションを検索する
//		System.out.println("*** Search Transaction ***");
//		System.out.println(Transaction.searchTransaction(blockChain, "Tx-1-1"));
//		System.out.println(Transaction.searchTransaction(blockChain, "Tx-11-0"));
//		System.out.println(Transaction.searchTransaction(blockChain, "Tx-2-1"));
//		System.out.println(Transaction.searchTransaction(blockChain, "Tx-5-2"));
	}
}
