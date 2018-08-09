package consensus;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class PoW implements IPoW {

	// TODO: Targetの計算
	public String Target = "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc";
	public String BlockHash = "0x0";
	public long TimeStamp = 0;
	public int Nonce = 0;

	// TODO: ユーティリティとしてまとめる
	public String CalcHash(String PreviousHash, String MerkleRoot, String Nonce, String TimeStamp) {
		// sha3でハッシュ計算を行う
		String input = PreviousHash + MerkleRoot + Nonce + TimeStamp;
		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] digest = digestSHA3.digest(input.getBytes());

//		return String.format("%040x", new BigInteger(1, digest));
		return Hex.toHexString(digest);
	}


	@Override
	public PoW ExecPoW(String PreviousHash, String MerkleRoot) {

		PoW PoWResult = new PoW();

		while(true) {
			long TimeStamp = System.currentTimeMillis() / 1000L;
			// ハッシュ値計算を行う
			String CalcResult = CalcHash(PreviousHash, MerkleRoot, String.valueOf(Nonce), String.valueOf(TimeStamp));

			if (PoWResult.Target.compareTo(CalcResult) > 0) {
				PoWResult.BlockHash = "0x" + CalcResult;
				break;
			}
			PoWResult.Nonce++;
		}
		return PoWResult;
	}
}
