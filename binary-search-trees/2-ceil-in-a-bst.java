/*
 * Ceil in a BST → mirror of floor: if `curr.data >= x`, it's a ceil candidate
 * — save it and go left (look for something closer); else go right.
 * Return -1 if no candidate found.
 */
class CeilInABST {
    static class Node {
        int data;
        Node left, right;
        Node(int val) { data = val; }
    }

    int findCeil(Node root, int x) {
        int res = Integer.MAX_VALUE;
        Node curr = root;
        while (curr != null) {
            if (curr.data >= x) {
                res = curr.data;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
