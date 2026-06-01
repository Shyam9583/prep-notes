/*
 * Maximum XOR of Two Numbers in an Array
 * Binary Trie storing 32-bit representations. For each number, greedily pick
 * the opposite bit at each level while querying — maximises XOR bit by bit.
 * Insert all numbers first, then query each; answer is max over all queries.
 */
class MaximumXorOfTwoNumbersInAnArray {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }

    private final TrieNode root = new TrieNode();

    private void insert(int num) {
        TrieNode cur = root;
        for (int bit = 31; bit >= 0; bit--) {
            int b = (num >> bit) & 1;
            if (cur.children[b] == null) cur.children[b] = new TrieNode();
            cur = cur.children[b];
        }
    }

    private int maxXorWith(int num) {
        TrieNode cur = root;
        int xor = 0;
        for (int bit = 31; bit >= 0; bit--) {
            int b = (num >> bit) & 1;
            int want = 1 - b;
            if (cur.children[want] != null) {
                xor |= (1 << bit);
                cur = cur.children[want];
            } else {
                cur = cur.children[b];
            }
        }
        return xor;
    }

    public int findMaximumXOR(int[] nums) {
        for (int n : nums) insert(n);
        int max = 0;
        for (int n : nums) max = Math.max(max, maxXorWith(n));
        return max;
    }
}
