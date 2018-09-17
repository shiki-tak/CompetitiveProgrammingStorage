package etherjava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import etherjava.domain.model.blockchain.Block;
import etherjava.domain.model.transaction.Transaction;
import etherjava.domain.service.blockchain.BlockchainService;
import etherjava.domain.service.consensus.PoW;
import etherjava.domain.service.consensus.PoWResult;
import etherjava.utils.trie.BloomFilter;
import etherjava.utils.trie.MerkleHash;
import etherjava.utils.trie.MerkleTree;

@Service
public class SimulateBlockchain {
	int i = 0;
	@Autowired
	BlockchainService blockChainService;

	@Scheduled(initialDelay = 60000, fixedRate = 5000)
	public void createBlock() throws IOException {
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
		// blockHashがnullのblockを生成する
		Block block = new Block(
				blockChainService.getLatestBlock().getBlockHeader().getBlockHash(),
				merkleRoot,
				logsBloom.getBitFilterToString(),
				transactions
				);
		PoW pow = new PoW(block, merkleRoot);
		PoWResult powResult = pow.exec();
		// PoWに成功したらBlockHeaderをセットする
		block.setBlockHeader(powResult.blockHash, powResult.nonce,powResult.timeStamp);

		long blockSize = blockChainService.calcBlockSize(
				block.getBlockHeader().getParentHash(),
				block.getBlockHeader().getBlockHash(),
				block.getBlockHeader().getMerkleRoot(),
				block.getBlockHeader().getLogsBloom(),
				block.getBlockHeader().getNonce(),
				block.getBlockHeader().getTimeStamp());

		block.setBlockSize(blockSize);
		blockChainService.append(block);

		Block latestBlock = blockChainService.getLatestBlock();
		display(latestBlock);
		i++;
	}

	// TODO: BlockchainServiceAPIから呼び出す
	private void display(Block block) {
		System.out.printf("*** Block %d *** %n", block.getHeight());
		System.out.printf("BlockSize: %d%n", block.getBlockSize());
		System.out.printf("TimeStamp: %d%n", block.getBlockHeader().getTimeStamp());
		System.out.printf("Hash: %s%n", block.getBlockHeader().getBlockHash());
		System.out.printf("Previous Hash: %s%n", block.getBlockHeader().getParentHash());
		System.out.printf("Merkle Root: %s%n", block.getBlockHeader().getMerkleRoot());
		System.out.printf("Logs Bloom: %s%n", block.getBlockHeader().getLogsBloom());
		System.out.printf("Nonce: %d%n", block.getBlockHeader().getNonce());
		System.out.println();
	}
}
