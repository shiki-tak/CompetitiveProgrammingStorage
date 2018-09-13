package etherjava.trie;

import java.nio.ByteBuffer;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

public class MerkleHash {
	private byte[] bytes;

	// バイト配列を設定するコンストラクタ
	public MerkleHash(byte[] bytes) {
		this.bytes = bytes;
	}

	// 文字列をバイト配列にするコンストラクタ
	public MerkleHash(String str) {
		this.bytes = str.getBytes();
	}

	// バイト配列をSHA-256でハッシュ化して16進数文字列にして返す
	public String sha256HexBinary() {
		Keccak.DigestKeccak kecc = new Keccak.Digest256();
		byte[] bytes = kecc.digest(this.bytes);
		// 16進数文字列に変換して返す
		return Hex.toHexString(bytes);
	}

	// ハッシュの結合
	public MerkleHash concatHash(MerkleHash right) {
		ByteBuffer byteBuf = ByteBuffer.allocate(bytes.length + right.bytes.length);
		byteBuf.put(bytes);
		byteBuf.put(right.bytes);
		byte[] newBytes = byteBuf.array();
		return new MerkleHash(newBytes);
	}
}
