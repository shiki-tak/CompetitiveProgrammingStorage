package trie;

public class MerkleNode {
	private MerkleHash merkleHash;
	private MerkleNode left;
	private MerkleNode right;

	public MerkleNode(MerkleHash merkleHash, MerkleNode left, MerkleNode right) {
		this.merkleHash = merkleHash;
		this.left = left;
		this.right = right;
	}

	public MerkleNode(MerkleHash merkleHash) {
		this.merkleHash = merkleHash;
		left = null;
		right = null;
	}

	// MerkleHashを取得
	public MerkleHash getMerkleHash() { return merkleHash; }
}
