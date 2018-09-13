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
		// 2のべき乗でなければ、trueになるまで、allLeavesHashesに要素を追加する
		while(!isPow2(allLeavesHashes.size())) {
			allLeavesHashes.add(allLeavesHashes.get(allLeavesHashes.size() - 1));
		}

		List<MerkleNode> result = allLeavesHashes.stream().map(new Function<MerkleHash, MerkleNode>() {
			@Override
			public MerkleNode apply(MerkleHash merkleHash) {
				return new MerkleNode(merkleHash);
			}
		}).collect(Collectors.toList());
		return buildMerkleTree(result);
	}

	private static MerkleTree buildMerkleTree(List<MerkleNode> nodesList) {
		// 最後のハッシュ
		if (nodesList.size() == 1) {
			MerkleTree merkleTree = new MerkleTree(nodesList.get(0));
			return merkleTree;
		} else {
			int nodeCount = nodesList.size();
			List<MerkleNode> newNodeList = new ArrayList<>();

			int nodeIndex = 0;
			// 新しいハッシュとノード作成
			while(nodeCount - 2 >= nodeIndex) {
				MerkleNode left = nodesList.get(nodeIndex);
				MerkleNode right = nodesList.get(nodeIndex + 1);
				// 結合
				MerkleHash newHash = left.getMerkleHash().concatHash(right.getMerkleHash());
				newNodeList.add(new MerkleNode(newHash, left, right));
				nodeIndex += 2;
			}
			return buildMerkleTree(newNodeList);
		}
	}

	// ビット演算で2のべき乗か確認
	private static boolean isPow2(int num) { return (num & (num - 1)) == 0; }
}
