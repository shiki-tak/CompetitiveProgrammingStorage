package core;

import java.util.ArrayList;
import java.util.List;

import trie.BloomFilter;

public class Transaction {
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
		resMessage = tx + (isTx ? " is included in Block " + String.valueOf(blockHeight) : " is not included anywhere");

		return resMessage;
	}
}
