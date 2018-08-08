package consensus;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PoW implements IPoW {

	String Target = "0000";
	String BlockHash = "0x0";
	long TimeStamp = 0;
	int Nonce = 0;

	public boolean MatchTargetCondition(String Target, String CalcResult) {
		if (Target == CalcResult.substring(0, Target.length())) {
			return true;
		}
		return false;
	}

	public String CalcHash(String PreviousHash, String MerkleRoot, String Nonce, String TimeStamp) {
		// sha256でハッシュ計算を行う
		String text = PreviousHash + MerkleRoot + Nonce + TimeStamp;
		MessageDigest digest;
		byte[] ResultAsBytes = new byte[]{(byte)0xF8, (byte)0x9F};
		try {
			digest = MessageDigest.getInstance("SHA-256");
			ResultAsBytes = digest.digest(text.getBytes());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return String.format("%040x", new BigInteger(1, ResultAsBytes));
	}


	@Override
	public PoW ExecPoW(String PreviousHash, String MerkleRoot, String Nonce) {

		PoW PoWResult = new PoW();

		while(true) {

			this.TimeStamp = System.currentTimeMillis() / 1000L;
			// ハッシュ値計算を行う
			String CalcResult = CalcHash(PreviousHash, MerkleRoot, String.valueOf(Nonce), String.valueOf(TimeStamp));

			// 計算したハッシュ値とターゲットが一致するかチェックする。一致すれば、trueを返す。
			if (MatchTargetCondition(Target, CalcResult)) {
				this.BlockHash = CalcResult;
				break;
			}
			// 一致しなければ、Nonceを変えて再計算する
			this.Nonce++;
		}
		return PoWResult;
	}



}
