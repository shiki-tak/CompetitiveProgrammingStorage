package trie;

import javax.validation.constraints.NotNull;

public class Leaf extends MerkleTree {

	public Leaf(@NotNull MerkleHash merkleHash) {
		super(merkleHash);
	}

}
