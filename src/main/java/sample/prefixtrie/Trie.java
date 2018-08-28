package sample.prefixtrie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    // Constructor
    public Trie() {
        root = new TrieNode();
    }

    /*
    * Add a word to the Trie
    * @param word
    */
    public void addWord(String word) {
        root.addWord(word.toLowerCase());
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if(p==null){
            return false;
        }else{
            if(p.isWord())
                return true;
        }

        return false;
    }

    public TrieNode searchNode(String s){
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(p.getChildren()[index]!=null){
                p = p.getChildren()[index];
            }else{
                return null;
            }
        }

        if(p==root)
            return null;

        return p;
    }
    /*
    * Get the words in the Trie with the given prefix
    * @param prefix
    * @return a List containing String objects containing the words in the Trie with the given prefix.
    */
    public List<String> getWords(String prefix) {
        TrieNode lastNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getNode(prefix.charAt(i));

            // if no node matches, then no words exit, return empty list
            if (lastNode == null) {
                return new ArrayList();
            }
        }

        return lastNode.getWords();
    }
}