/*
 * Longest String with All Prefixes (Longest Valid Word with All Prefixes)
 *
 * Insert all words into Trie. Sort words lexicographically first — then a
 * simple length > check suffices for tie-breaking (smallest of equal length
 * is already seen first). isValidPrefix walks the Trie checking isWord at
 * every prefix node, not just the terminal.
 */

import java.util.Arrays;

class LongestStringWithAllPrefixes {
    public String longestValidWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        String longest = "";
        Arrays.sort(words);

        for (String word : words) {
            if (trie.isValidPrefix(word) && word.length() > longest.length()) {
                longest = word;
            }
        }

        return longest;
    }

    private static class Node {
        Node[] children = new Node[26];
        boolean isWord = false;

        Node getChild(char ch) {
            return children[ch - 'a'];
        }

        void putChild(char ch, Node node) {
            children[ch - 'a'] = node;
        }
    }

    private static class Trie {
        private Node root = new Node();

        void insert(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                Node next = curr.getChild(ch);
                if (next == null) {
                    next = new Node();
                    curr.putChild(ch, next);
                }
                curr = next;
            }
            curr.isWord = true;
        }

        boolean isValidPrefix(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                Node next = curr.getChild(ch);
                if (next == null || !next.isWord) {
                    return false;
                }
                curr = next;
            }
            return true;
        }
    }
}
