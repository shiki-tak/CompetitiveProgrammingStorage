package imitatedchain;

import consensus.PoW;
import core.Block;
import core.Blockchain;

public class Simulator {

	public static void main(String[] args) {
		String merkleRoot = "0x0";
		Blockchain blockChain = new Blockchain();
		PoW Pow = new PoW();

		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				// Genesis blockの作成
				blockChain.createGenesisBlock(merkleRoot);
			} else {
				PoW PoWResult = Pow.ExecPoW(blockChain.getLatestBlock().getBlockHeader().getBlockHash(), merkleRoot);
				Block block = new Block(
						blockChain.getLatestBlock().getBlockHeader().getBlockHash(),
						PoWResult.BlockHash,
						merkleRoot,
						PoWResult.Nonce,
						PoWResult.TimeStamp
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
