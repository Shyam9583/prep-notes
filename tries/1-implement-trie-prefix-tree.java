/*
 * Implement Trie (Prefix Tree)
 * TrieNode has children[26] and a boolean isEnd. insert walks/creates nodes
 * char by char. search walks and checks isEnd at terminal; startsWith walks
 * and returns true if path exists. All O(word length).
 */
class ImplementTriePrefixTree {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) cur.children[i] = new TrieNode();
            cur = cur.children[i];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) return false;
            cur = cur.children[i];
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) return false;
            cur = cur.children[i];
        }
        return true;
    }
}
