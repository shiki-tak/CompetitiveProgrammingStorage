package etherjava.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import etherjava.domain.model.blockchain.Block;
import etherjava.domain.model.transaction.Transaction;
import etherjava.domain.service.blockchain.BlockchainService;
import etherjava.utils.trie.BloomFilter;
import etherjava.utils.trie.MerkleHash;
import etherjava.utils.trie.MerkleTree;

@Service
@AutoJsonRpcServiceImpl
public class BlockchainServiceAPIImpl implements BlockchainServiceAPI {

	@Autowired
	BlockchainService blockChainService;

	@Override
	public String createGenesisBlock() {
		List<String> txs = new ArrayList<>();
		BloomFilter logsBloom = new BloomFilter();
		List<MerkleHash> merkleHashList = new ArrayList<>();

		txs.add("transaction-" + String.valueOf(0));

		for (int l = 0; l < txs.size(); l++) {
			logsBloom.add(txs.get(l));
			merkleHashList.add(new MerkleHash(txs.get(l)));
		}

		// Create Merkle Tree
		MerkleTree merkleTree = MerkleTree.createMerkleTree(merkleHashList);
		String merkleRoot =  merkleTree.getMerkleRoot().getMerkleHash().sha256HexBinary();

		List<Transaction> transactions = new ArrayList<>();

		Keccak.DigestKeccak kecc = new Keccak.Digest256();
		byte[] digest = kecc.digest("genesis block".getBytes());

		String previousHash = "0x0000000000000000000000000000000000000000000000000000000000000000";
		String blockHash = "0x" + Hex.toHexString(digest);
		int nonce = 42;
		long timeStamp = System.currentTimeMillis() / 1000L;

		String logsBloomToString = logsBloom.getBitFilterToString();

		// ヘッダ情報とトランザクション情報を合わせたブロックサイズ（バイト単位）
		// TODO: 真面目に計算（transactionsを含めていない）
		long blockSize = blockChainService.calcBlockSize(previousHash, blockHash, merkleRoot, logsBloomToString, nonce, timeStamp);

		blockChainService.createGenesisBlock(blockSize, previousHash, merkleRoot, blockHash, logsBloomToString, nonce, timeStamp, transactions);

		return blockHash;
	}

	@Override
	public String getBlockByHeight(String heightToString) {
		Block block;
		try {
			block = blockChainService.getBlockByHeight(Integer.parseInt(heightToString));
			return "{\"blockSize\": \"" + block.getBlockSize() + "\", "
			+ "\"height\": \"" + block.getHeight()
			+ "\", \"parentHash\": \"" + block.getBlockHeader().getParentHash()
			+ "\", \"blockHash\": \"" + block.getBlockHeader().getBlockHash()
			+ "\", \"merkleRoot\": \"" + block.getBlockHeader().getMerkleRoot()
			+ "\", \"logsBloom\": \"" + block.getBlockHeader().getLogsBloom()
			+ "\", \"nonce\": \"" + block.getBlockHeader().getNonce()
			+ "\", \"timeStamp\": \"" + block.getBlockHeader().getTimeStamp() + "\"}";

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getBlockByHash(String blockHash) {
		try {
			Block block = blockChainService.getBlockByHash(blockHash);

			System.out.println("getBlockByHash result: " + block);

			// TODO: json形式でreturn
			return "{\"blockSize\": \"" + block.getBlockSize() + "\", "
					+ "\"height\": \"" + block.getHeight()
					+ "\", \"parentHash\": \"" + block.getBlockHeader().getParentHash()
					+ "\", \"blockHash\": \"" + block.getBlockHeader().getBlockHash()
					+ "\", \"merkleRoot\": \"" + block.getBlockHeader().getMerkleRoot()
					+ "\", \"logsBloom\": \"" + block.getBlockHeader().getLogsBloom()
					+ "\", \"nonce\": \"" + block.getBlockHeader().getNonce()
					+ "\", \"timeStamp\": \"" + block.getBlockHeader().getTimeStamp() + "\"}";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
