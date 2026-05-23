/*
 * Floor in a BST → iterative BST traversal: if `curr.data <= k`, it's a floor
 * candidate — save it and go right (look for something closer); else go left.
 * Return -1 if no candidate found.
 */
class FloorInABST {
    static class Node {
        int data;
        Node left, right;
        Node(int val) { data = val; }
    }

    public int findMaxFork(Node root, int k) {
        int res = Integer.MIN_VALUE;
        Node curr = root;
        while (curr != null) {
            if (curr.data <= k) {
                res = curr.data;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return res == Integer.MIN_VALUE ? -1 : res;
    }
}
