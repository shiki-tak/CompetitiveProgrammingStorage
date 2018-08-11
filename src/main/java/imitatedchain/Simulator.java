package imitatedchain;

import consensus.PoW;
import core.Blockchain;

public class Simulator {

	public static void main(String[] args) {
		String MerkleRoot = "0x0";
		Blockchain BlockChain = new Blockchain();
		PoW Pow = new PoW();

		for (int i = 0; i < 10; i++) {
			int LatestBlockIndex = BlockChain.GetLatestBlockIndex();

			if (i == 0) {
				// Genesis blockの作成
				BlockChain.Blocks.add(BlockChain.CreateGenesisBlock(MerkleRoot));
			} else {
				PoW PoWResult = Pow.ExecPoW(BlockChain.Blocks.get(LatestBlockIndex - 1).getBlockHash(), MerkleRoot);
				BlockChain.Blocks.add(
						BlockChain.CreateBlock(
								BlockChain.Blocks.get(LatestBlockIndex - 1).getBlockHash(),
								PoWResult.BlockHash,
								MerkleRoot,
								PoWResult.Nonce,
								PoWResult.TimeStamp));
			}

			// 作成したブロックを出力
			System.out.printf("*** Block %d *** %n", BlockChain.Blocks.get(i).getHeight());
			System.out.printf("TimeStamp: %d%n", BlockChain.Blocks.get(i).getTimeStamp());
			System.out.printf("Hash: %s%n", BlockChain.Blocks.get(i).getBlockHash());
			System.out.printf("Previous Hash: %s%n", BlockChain.Blocks.get(i).getPreviousHash());
			System.out.printf("Nonce: %d%n", BlockChain.Blocks.get(i).getNonce());
			System.out.println();
		}
	}
}
