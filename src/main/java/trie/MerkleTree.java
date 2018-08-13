package trie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

public class MerkleTree {
	private MerkleNode merkleRoot;

	MerkleTree(MerkleNode merkleRoot) {
		this.merkleRoot = merkleRoot;
	}

	// getter
	public MerkleNode getMerkleRoot() { return merkleRoot; }

	public static MerkleTree createMerkleTree(@NotNull List<MerkleHash> allLeavesHashes) {
		if (allLeavesHashes.isEmpty()) {
			throw new IllegalArgumentException("Cannot calculate Merkle root on empty hash list.");
		}
		// 2のべき乗かどうか確認する
		if (!isPow2(allLeavesHashes.size())) {
			throw new IllegalArgumentException("allLeavesHashes must be Pow of 2.");
		}
		List<MerkleNode> result = allLeavesHashes.stream().map(new Function<MerkleHash, MerkleNode>() {
			@Override
			public MerkleNode apply(MerkleHash merkleHash) {
				return new MerkleNode(merkleHash);
			}
		}).collect(Collectors.toList());
		return buildMerkleTree(result);
	}


	private static boolean isPow2(int num) {
		// ビット演算で2のべき乗か確認
		return (num & (num - 1)) == 0;
	}

	public static MerkleTree buildMerkleTree(List<MerkleNode> lastNodesList) {
		// 最後のハッシュ
		if (lastNodesList.size() == 1) {
			MerkleTree merkleTree = new MerkleTree(lastNodesList.get(0));
			return merkleTree;
		} else {
			int n = lastNodesList.size();
			List<MerkleNode> newLevelHashes = new ArrayList<>();

			int i = 0;
			// 新しいハッシュとノード作成
			while(n - 2 >= i) {
				MerkleNode left = lastNodesList.get(i);
				MerkleNode right = lastNodesList.get(i + 1);
				// 結合
				MerkleHash newHash = left.getMerkleHash().hashConcat(right.getMerkleHash());
				newLevelHashes.add(new MerkleNode(newHash, left, right));
				i += 2;
			}
			return buildMerkleTree(newLevelHashes);
		}
	}
}
