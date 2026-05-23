/*
 * Flatten Binary Tree to Linked List
 *
 * Post-order DFS returns tail of flattened subtree; wire leftTail.right = root.right,
 * move left to right, null the left; return rightTail ?? leftTail ?? root.
 */
public class FlattenBinaryTreeToLinkedList {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public void flatten(TreeNode root) {
        flattenAndGetTail(root);
    }

    private TreeNode flattenAndGetTail(TreeNode node) {
        if (node == null) return null;
        TreeNode leftTail = flattenAndGetTail(node.left);
        TreeNode rightTail = flattenAndGetTail(node.right);

        if (leftTail != null) {
            leftTail.right = node.right; // wire left tail to existing right subtree
            node.right = node.left;
            node.left = null;
        }

        // return rightmost tail
        if (rightTail != null) return rightTail;
        if (leftTail != null) return leftTail;
        return node;
    }
}
