/*
 * Number of Distinct Substrings
 *
 * Every substring is a prefix of some suffix. Insert all suffixes into a Trie;
 * each new node created corresponds to exactly one unique substring.
 * Answer = total nodes created across all insertions. O(n^2) time and space.
 */

class NumberOfDistinctSubstrings {
    private static class Node {
        Node[] children = new Node[26];
    }

    public static int countSubs(String s) {
        Node root = new Node();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            Node curr = root;
            for (int j = i; j < s.length(); j++) {
                int idx = s.charAt(j) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new Node();
                    count++;
                }
                curr = curr.children[idx];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(countSubs("abc"));  // 6
        System.out.println(countSubs("abab")); // 7
        System.out.println(countSubs("aa"));   // 2
    }
}
