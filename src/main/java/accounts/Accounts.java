package accounts;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class Accounts {

	public static void main(String[] args) {
		Accounts account = new Accounts();

		try {
				KeyPair keyPair = account.getKeyPair();

				Key[] keys = new Key[] {keyPair.getPrivate(), keyPair.getPublic()};
				String privateKey = DatatypeConverter.printHexBinary(keys[0].getEncoded()).substring(64, 128);
				String publicKey = DatatypeConverter.printHexBinary(keys[1].getEncoded()).substring(46, 176);

				System.out.print("private key: ");
				System.out.println(privateKey);
				System.out.print("public key: ");
				System.out.println(keys[1]);
				System.out.println(publicKey);

				String EthAddress = createNewAccount(publicKey);
				System.out.println("EthAddress: ");
				System.out.println(EthAddress);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}

	private static String createNewAccount(String publicKey) {
		// 先頭の04を取り除く
		String s = publicKey.substring(2, 128);
		// 全部小文字にする
		s.toLowerCase();
		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
		// SHA256 (Keccak) でハッシュを得る
		byte[] digest = digestSHA3.digest(s.getBytes());

		// 後ろの40文字を取り出して、先頭に0xをつけてアドレスにする
		String EthAddress = "0x" + Hex.toHexString(digest).substring(24, 64);

		return EthAddress;
	}

	public KeyPair getKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
		keyGen.initialize(ecSpec, new SecureRandom());
		return keyGen.generateKeyPair();
	}
}
