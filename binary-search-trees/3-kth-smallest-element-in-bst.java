/*
 * K-th Smallest Element in BST → inorder traversal (left→root→right) visits
 * nodes in sorted order; increment a counter on each visit and capture
 * node.val when counter hits k.
 */
class KthSmallestElementInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int res = -1;
    private int idx = 0;

    public int kthSmallest(TreeNode root, int k) {
        find(root, k);
        return res;
    }

    private void find(TreeNode node, int k) {
        if (node == null) return;
        find(node.left, k);
        if (++idx == k) {
            res = node.val;
            return;
        }
        find(node.right, k);
    }
}
