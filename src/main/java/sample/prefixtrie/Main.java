package sample.prefixtrie;


public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.addWord("cat");
        trie.addWord("dog");
        trie.addWord("doing");
        trie.addWord("decentrailze");
        trie.addWord("desk");


        System.out.println(trie.getWords("d"));
        System.out.println(trie.getWords("de"));
        System.out.println(trie.getWords("do"));

        System.out.println(trie.getWords("c"));

        if (trie.search("dog")) {
            System.out.println("dog" + " exit");

        } else {
        	System.out.println("dog" + " not exit");
        }

        if (trie.search("cats")) {
            System.out.println("cats" + " exit");

        } else {
        	System.out.println("cats" + " not exit");
        }
    }

    /*
     * Output Result
     * [decentrailze, desk, dog, doing]
     * [decentrailze, desk]
     * [dog, doing]
     * [cat]
     * dog exit
     * cats not exit
     */
}