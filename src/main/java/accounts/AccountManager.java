package accounts;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

public class AccountManager {

	public static String createNewAccount() {
		try {
			KeyPair keyPair = getKeyPair();
			Key[] keys = new Key[] {keyPair.getPrivate(), keyPair.getPublic()};
			String PublicKey = publicKeyToString(keys[1]);
			String Address = createAddress(PublicKey);
			return Address;

		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	private static String publicKeyToString(Key PublicKey) {

		String StringPublicKey = Hex.toHexString(PublicKey.getEncoded()).substring(46, 176);

		return StringPublicKey;
	}

	private static String createAddress(String PublicKey) {
		// 先頭の04を取り除く
		String s = PublicKey.substring(PublicKey.length() - 126, PublicKey.length());
		// 全部小文字にする
		s.toLowerCase();
		Keccak.DigestKeccak kecc = new Keccak.Digest256();
		// SHA256 (Keccak) でハッシュを得る
		byte[] digest = kecc.digest(s.getBytes());
		String digestToString = Hex.toHexString(digest);

		// 後ろの40文字を取り出して、先頭に0xをつけてアドレスにする
		String EthAddress = "0x" + digestToString.substring(digestToString.length() - 40, digestToString.length());

		return EthAddress;
	}

	private static KeyPair getKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
		keyGen.initialize(ecSpec, new SecureRandom());
		return keyGen.generateKeyPair();
	}
}
