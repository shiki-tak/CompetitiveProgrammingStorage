package etherjava.trie;

import java.util.BitSet;

import org.bouncycastle.jcajce.provider.digest.Keccak;

public class BloomFilter {

	private int countOfHashNumCreatePerOneElement; // ハッシュ関数の数
	private final BitSet bitFilter;
	private int numberOfAddedElements;
	private int bitFilterSize;

	// コンストラクタ
	public BloomFilter() {
		long c = 100;
		int n = 1 << 10;
		int k = 3;

		countOfHashNumCreatePerOneElement = k;
		this.bitFilterSize = (int)Math.ceil(c * n); // 要素数 * 要素あたり利用ビット数でFilterのビットサイズを決定する
		this.bitFilter = new BitSet(bitFilterSize);

		numberOfAddedElements = 0;
	}
	// ブルームフィルタにデータを追加
	public void add(String data) {
		int[] hashes = createHashes(data, countOfHashNumCreatePerOneElement);
		for (int hash : hashes) {
			bitFilter.set(Math.abs(hash % bitFilterSize), true);
			numberOfAddedElements++;
		}
	}

    /**
     * ハッシュ化
     * @param data 入力データ
     * @param hashCount 回数
     * @return ハッシュ化して求められた数値（ビット立て位置）
     */
	public static int[] createHashes(String data, int hashCount) {
		int [] result = new int[hashCount];

		for (int k = 0; k < hashCount; k++) {
			// ハッシュ化
			Keccak.DigestKeccak kecc = new Keccak.Digest256();
			byte[] digest = kecc.digest(data.getBytes());
			// 4Byte区切りでint化して配列に詰める
			for (int i = 0; i < digest.length / 4 && k < hashCount; i++) {
				int h = 0;
				for (int j = (i * 4); j < (i * 4) + 4; j++) {
					h <<= 8;
					h |= (digest[j]) & 0xFF;
				}
				result[k] = h;
			}
		}
		return result;
	}

    /**
     * 判定
     * @param bytes 要素のバイト配列
     * @return
     */
	public boolean contain(String data) {
		int[] hashes = createHashes(data, countOfHashNumCreatePerOneElement);
		for (int hash : hashes) {
			if (!bitFilter.get(Math.abs(hash % bitFilterSize))) {
				return false;
			}
		}
		return true;
	}
}
