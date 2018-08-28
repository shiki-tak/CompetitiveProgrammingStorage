package sample;

import java.util.ArrayList;
import java.util.HashSet;

public class PatriciaTrieNode {

	/*
	 * label: ノードによって表される圧縮パス（任意の接頭辞の部分文字列）
	 * children: 葉
	 * items: 接頭辞によって表される文字列のリスト
	 */
	private String label;
	private ArrayList<PatriciaTrieNode> children = new ArrayList<>();
	private HashSet<String> items = new HashSet<>();

	// Constructor
	public PatriciaTrieNode() {
	}

	public PatriciaTrieNode search(String s) {
		int l = 0, r = children.size() - 1, pos = 0;
		char tmp_c, c;
		try {
			// 検索対象が空文字の場合はreturn
			c = s.charAt(0);
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
		// 文字列の最初の文字と子供のラベルのBinary search
		while (l <= r) {
			pos = (l + r) / 2;
			PatriciaTrieNode child = children.get(pos);
			String label = child.label;
			int l_len = label.length();
			tmp_c = label.charAt(0);
			if (tmp_c == c) {
				int i = 1;
				int s_len = s.length();

				int n = Math.min(s_len, l_len);
				for (; i < n; i++) {
					if (s.charAt(i) != label.charAt(i)) {
						break;
					}
				}
				if (i == s_len) {
					return child;
				} else if (i == l_len) {
					return child.search(s.substring(i));
				} else {
					return null;
				}
			} else if (tmp_c < c) {
				l = pos + 1;
			} else {
				r = pos - 1;
			}
		}
		return null;
	}

	public void insertChild(String s, String item) {
	}

}
