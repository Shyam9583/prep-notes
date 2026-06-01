/*
 * Implement Trie II
 * Extend each TrieNode with countPrefix and countEnd counters. insert
 * increments countPrefix at every node, countEnd at terminal. erase
 * decrements both — no node deletion needed. countWordsEqualTo returns
 * terminal countEnd; countWordsStartingWith returns first-node countPrefix.
 */
class ImplementTrieII {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int countPrefix;
        int countEnd;
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) cur.children[i] = new TrieNode();
            cur = cur.children[i];
            cur.countPrefix++;
        }
        cur.countEnd++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) return 0;
            cur = cur.children[i];
        }
        return cur.countEnd;
    }

    public int countWordsStartingWith(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) return 0;
            cur = cur.children[i];
        }
        return cur.countPrefix;
    }

    public void erase(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            cur = cur.children[i];
            cur.countPrefix--;
        }
        cur.countEnd--;
    }
}
