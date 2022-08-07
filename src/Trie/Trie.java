package Trie;

import java.util.*;

public class Trie {

    private TrieNode root;

    private class TrieNode {

        HashMap<Character, TrieNode> map;
        boolean isEndOfWord;

        public TrieNode(HashMap<Character, TrieNode> map, boolean isEndOfWord) {
            this.map = map;
            this.isEndOfWord = isEndOfWord;
        }

        public TrieNode() {
            this.map = new HashMap<>();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(this.map);
            builder.append(this.isEndOfWord);
            return builder.toString();
        }

    }

    public Trie() {
        this.root = new TrieNode();
    }

    public boolean add(String o) {
        return this.add(o, root);
    }

    private boolean add(String str, TrieNode node) {
        TrieNode temp = node;
        for (int i = 0; i < str.length(); i++) {
            if (temp.map.containsKey(str.charAt(i))) {
                temp = temp.map.get(str.charAt(i));
            } else {
                temp.map.put(str.charAt(i), new TrieNode());
                temp = temp.map.get(str.charAt(i));
            }
        }
        temp.isEndOfWord = true;
        return true;
    }

    public boolean contains(String o) {
        return this.contains(o, root);
    }

    private boolean contains(String str, TrieNode node) {
        TrieNode temp = node;
        for (int i = 0; i < str.length(); i++) {
            if (!temp.map.containsKey(str.charAt(i))) {
                return false;
            } else {
                temp = temp.map.get(str.charAt(i));
            }
        }
        return temp.isEndOfWord;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        TrieNode node = root;
        builder.append(node);
        return builder.toString();
    }

}
