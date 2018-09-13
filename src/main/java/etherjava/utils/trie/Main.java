package etherjava.utils.trie;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<MerkleHash> merkleHashList = new ArrayList<>();
		merkleHashList.add(new MerkleHash("L1"));
		merkleHashList.add(new MerkleHash("L2"));
		merkleHashList.add(new MerkleHash("L3"));
		merkleHashList.add(new MerkleHash("L4"));
		merkleHashList.add(new MerkleHash("L5"));

		MerkleTree merkleTree = MerkleTree.createMerkleTree(merkleHashList);

		System.out.println("root: " + merkleTree.getMerkleRoot().getMerkleHash().sha256HexBinary());
		// L1〜L4のとき： 68b8942f1eceb4034a6d5ac9043d0b6f11fd1878ff45ddd70c8ea149d26e44cf
	}
}
