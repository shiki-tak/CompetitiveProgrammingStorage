package imitatedchain;

import consensus.PoW;
import core.Blockchain;

public class Simulator {

	public static void main(String[] args) {
		String merkleRoot = "0x0";
		Blockchain blockChain = new Blockchain();
		PoW Pow = new PoW();

		for (int i = 0; i < 10; i++) {
			int LatestBlockIndex = blockChain.GetLatestBlockIndex();

			if (i == 0) {
				// Genesis blockの作成
				blockChain.blocks.add(blockChain.CreateGenesisBlock(merkleRoot));
			} else {
				PoW PoWResult = Pow.ExecPoW(blockChain.blocks.get(LatestBlockIndex - 1).getBlockHeader().getBlockHash(), merkleRoot);
				blockChain.blocks.add(
						blockChain.CreateBlock(
								blockChain.blocks.get(LatestBlockIndex - 1).getBlockHeader().getBlockHash(),
								PoWResult.BlockHash,
								merkleRoot,
								PoWResult.Nonce,
								PoWResult.TimeStamp));
			}

			// 作成したブロックを出力
			System.out.printf("*** Block %d *** %n", blockChain.blocks.get(i).getHeight());
			System.out.printf("TimeStamp: %d%n", blockChain.blocks.get(i).getBlockHeader().getTimeStamp());
			System.out.printf("Hash: %s%n", blockChain.blocks.get(i).getBlockHeader().getBlockHash());
			System.out.printf("Previous Hash: %s%n", blockChain.blocks.get(i).getBlockHeader().getPreviousHash());
			System.out.printf("Nonce: %d%n", blockChain.blocks.get(i).getBlockHeader().getNonce());
			System.out.println();
		}
	}
}
