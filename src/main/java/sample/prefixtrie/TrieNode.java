package sample.prefixtrie;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {
    private TrieNode parent;
    private TrieNode[] children;
    private boolean isLeaf;         // childrenがいるかどうか
    private boolean isWord;         // このノードは単語の最後の文字を表すか
    private char character;         // このノードが表す文字

    public TrieNode() {
        setChildren(new TrieNode[26]);
        isLeaf = true;
        setWord(false);
    }

    public TrieNode(char character) {
        this();
        this.character = character;
    }

    /*
    * @param word the word to add
    */
    public void addWord(String word) {
        isLeaf = false;
        int charPos = word.charAt(0) - 'a';

        if (getChildren()[charPos] == null) {
            getChildren()[charPos] = new TrieNode(word.charAt(0));
            getChildren()[charPos].parent = this;
        }

        if (word.length() > 1) {
            getChildren()[charPos].addWord(word.substring(1));
        } else {
            getChildren()[charPos].setWord(true);
        }
    }

    /*
    * 指定されたcharを表す子TrieNodeを返す。ノードが存在しない場合はnullを返す
    * @param c
    * @return
    */
    public TrieNode getNode(char c) {
        return getChildren()[c - 'a'];
    }

    /*
    * このノードが階層構造の下位にあるStringオブジェクトのListを返す。
    * @return
    */
    public List<String> getWords() {
        // Create a list to return
    	List<String> list = new ArrayList<>();

        // このノードが単語を表す場合は、それを追加する
        if (isWord()) {
            list.add(toString());
        }

        if (!isLeaf) {
            for (int i = 0; i < getChildren().length; i++) {
                if (getChildren()[i] != null) {
                    list.addAll(getChildren()[i].getWords());
                }
            }
        }
        return list;
    }

    public String toString() {
        if (parent == null) {
            return "";
        } else {
            return parent.toString() + new String(new char[] {character});
        }
    }

	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}

	public TrieNode[] getChildren() {
		return children;
	}

	public void setChildren(TrieNode[] children) {
		this.children = children;
	}
}