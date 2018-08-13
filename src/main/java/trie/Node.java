package trie;

import javax.validation.constraints.NotNull;

public class Node extends MerkleTree {

	public Node(@NotNull MerkleHash merkleHash, @NotNull MerkleTree left, @NotNull MerkleTree right) {
		super(merkleHash);
	}

}
