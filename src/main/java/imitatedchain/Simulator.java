package imitatedchain;

import consensus.PoW;
import consensus.PoWResult;
import core.Block;
import core.Blockchain;

public class Simulator {

	public static void main(String[] args) {
		String merkleRoot = "0x0";
		Blockchain blockChain = new Blockchain();

		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				// Genesis blockの作成
				blockChain.createGenesisBlock(merkleRoot);
			} else {
				PoW pow = new PoW(blockChain, merkleRoot);
				PoWResult powResult = pow.exec();
				Block block = new Block(
						blockChain.getLatestBlock().getBlockHeader().getBlockHash(),
						powResult.blockHash,
						merkleRoot,
						powResult.nonce,
						powResult.timeStamp
						);

				blockChain.append(block);
			}

			// 作成したブロックを出力
			System.out.printf("*** Block %d *** %n", blockChain.getLatestBlockIndex());
			System.out.printf("TimeStamp: %d%n", blockChain.getLatestBlock().getBlockHeader().getTimeStamp());
			System.out.printf("Hash: %s%n", blockChain.getLatestBlock().getBlockHeader().getBlockHash());
			System.out.printf("Previous Hash: %s%n", blockChain.getLatestBlock().getBlockHeader().getPreviousHash());
			System.out.printf("Nonce: %d%n", blockChain.getLatestBlock().getBlockHeader().getNonce());
			System.out.println();
		}
	}
}
