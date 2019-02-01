package etherjava.domain.service.account;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etherjava.domain.model.account.Account;
import etherjava.domain.model.account.Address;
import etherjava.domain.repository.account.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	// テスト用メソッド（初期残高ありでアカウントを作る）
	public Account createNewAccount(double initBalance) {
		try {
			KeyPair keyPair = getKeyPair();
			Key[] keys = new Key[] {keyPair.getPrivate(), keyPair.getPublic()};
			String publicKey = publicKeyToString(keys[1]);
			Address address = generateAddress(publicKey);
			Account account = new Account(address, initBalance);
			save(account.getAddress().addressToString(), account.getBalance(), account.getNonce());

			return account;

		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Account findOne(String addressToString) {
		return accountRepository.findOne(addressToString);
	}

	private String publicKeyToString(Key PublicKey) {
		String StringPublicKey = Hex.toHexString(PublicKey.getEncoded()).substring(46, 176);
		return StringPublicKey;
	}

	private Address generateAddress(String PublicKey) {
		// 先頭の04を取り除く
		String s = PublicKey.substring(PublicKey.length() - 126, PublicKey.length());
		// 全部小文字にする
		s.toLowerCase();
		Keccak.DigestKeccak kecc = new Keccak.Digest256();
		// SHA256 (Keccak) でハッシュを得る
		byte[] digest = kecc.digest(s.getBytes());
		String digestToString = Hex.toHexString(digest);

		// 後ろの40文字を取り出して、先頭に0xをつけてアドレスにする
		Address ethAddress = new Address("0x" + digestToString.substring(digestToString.length() - 40, digestToString.length()));

		return ethAddress;
	}

	private KeyPair getKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
		keyGen.initialize(ecSpec, new SecureRandom());
		return keyGen.generateKeyPair();
	}

	public void save(String addressToString, double balance, int nonce) {
		try {
			accountRepository.save(addressToString, balance, nonce);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
