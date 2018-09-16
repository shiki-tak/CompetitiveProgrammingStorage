package etherjava.domain.service.blockchain;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

import etherjava.domain.model.blockchain.Block;
import etherjava.domain.model.transaction.Transaction;
import etherjava.domain.service.consensus.PoW;
import etherjava.domain.service.consensus.PoWResult;
import etherjava.utils.trie.BloomFilter;
import etherjava.utils.trie.MerkleHash;
import etherjava.utils.trie.MerkleTree;


public class Simulator {

	public static void main(String[] args) {
		BlockchainService blockChain = new BlockchainService();

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

			List<Transaction> transactions = new ArrayList<>();

			if (i == 0) {
				Keccak.DigestKeccak kecc = new Keccak.Digest256();
				byte[] digest = kecc.digest("genesis block".getBytes());

				String previousHash = "0x0000000000000000000000000000000000000000000000000000000000000000";
				String blockHash = "0x" + Hex.toHexString(digest);
				int nonce = 42;
				long timeStamp = System.currentTimeMillis() / 1000L;

				String logsBloomToString = logsBloom.getBitFilterToString();

				// ヘッダ情報とトランザクション情報を合わせたブロックサイズ（バイト単位）
				// TODO: 真面目に計算
				long blockSize = blockChain.calcBlockSize(previousHash, blockHash, merkleRoot, logsBloomToString, nonce, timeStamp, transactions);

				// Genesis blockの作成
				blockChain.createGenesisBlock(blockSize, previousHash, blockHash, merkleRoot, logsBloom, nonce, timeStamp, transactions);
			} else {
				// blockHashがnullのblockを生成する
				Block block = new Block(
						blockChain.getLatestBlock().getBlockHeader().getBlockHash(),
						merkleRoot,
						logsBloom,
						transactions
						);
				PoW pow = new PoW(block, merkleRoot);
				PoWResult powResult = pow.exec();
				// PoWに成功したらBlockHeaderをセットする
				block.setBlockHeader(powResult.blockHash, powResult.nonce,powResult.timeStamp);

				long blockSize = blockChain.calcBlockSize(
						block.getBlockHeader().getParentHash(),
						block.getBlockHeader().getBlockHash(),
						block.getBlockHeader().getMerkleRoot(),
						block.getBlockHeader().getLogsBloom().getBitFilterToString(),
						block.getBlockHeader().getNonce(),
						block.getBlockHeader().getTimeStamp(),
						block.getTransactions());

				block.setBlockSize(blockSize);

				blockChain.append(block);
			}
		}

		// 作成したブロックを出力
		for (int i = 0; i < 10; i++) {
			Block block = blockChain.getBlock(i);

			System.out.printf("*** Block %d *** %n", block.getHeight());
			System.out.printf("BlockSize: %d%n", block.getBlockSize());
			System.out.printf("TimeStamp: %d%n", block.getBlockHeader().getTimeStamp());
			System.out.printf("Hash: %s%n", block.getBlockHeader().getBlockHash());
			System.out.printf("Previous Hash: %s%n", block.getBlockHeader().getParentHash());
			System.out.printf("Merkle Root: %s%n", block.getBlockHeader().getMerkleRoot());
			System.out.printf("Logs Bloom: %s%n", block.getBlockHeader().getLogsBloom());
			System.out.printf("Nonce: %d%n", block.getBlockHeader().getNonce());
			System.out.printf("Transactions: %s%n", block.getTransactions().toString());
			System.out.println();
		}
	}
}
