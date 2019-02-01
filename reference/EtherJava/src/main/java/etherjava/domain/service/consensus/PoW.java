package etherjava.domain.service.consensus;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

import etherjava.domain.model.blockchain.Block;

public final class PoW {
	// TODO: targetの計算
	private final String target = "00001000111111111111111111111111111111111111111111111111111111";
	private Block block;
	private String merkleRoot;

	public PoW(Block block, String merkleRoot) {
		this.block = block;
		this.merkleRoot = merkleRoot;
	}

	// TODO: MerkleRootをトランザクション型として受け取る
	public PoWResult exec() {
		String hash = "";
		int nonce = 0;
		long timeStamp = System.currentTimeMillis() / 1000L;

		do {
			nonce++;
			timeStamp = System.currentTimeMillis() / 1000L;
			hash = CalcHash(nonce, timeStamp);
		} while(target.compareTo(hash) <= 0);

		PoWResult result = new PoWResult("0x" + hash, nonce, timeStamp);
		return result;
	}

	// TODO: ユーティリティとしてまとめる
	private String CalcHash(int nonce, long timeStamp) {
		// sha3でハッシュ計算を行う
		String rawData = block.getBlockHeader().getParentHash() +
				merkleRoot +
				nonce +
				timeStamp;
		Keccak.DigestKeccak kecc = new Keccak.Digest256();
		byte[] digest = kecc.digest(rawData.getBytes());

		return Hex.toHexString(digest);
	}
}
