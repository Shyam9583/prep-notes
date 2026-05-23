/*
 * K-th Largest Element in BST → reverse inorder (right→root→left) visits nodes
 * in descending order; same counter trick as K-th Smallest.
 */
class KthLargestElementInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int res = -1;
    private int idx = 0;

    public int kthLargest(TreeNode root, int k) {
        find(root, k);
        return res;
    }

    private void find(TreeNode node, int k) {
        if (node == null) return;
        find(node.right, k);
        if (++idx == k) {
            res = node.val;
            return;
        }
        find(node.left, k);
    }
}
