/*
 * Symmetric Binary Tree
 *
 * Mirror check: recurse with (left.left, right.right) and (left.right, right.left);
 * same-pointer shortcut handles both-null case.
 */
public class SymmetricBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == right) return true; // both null or same pointer
        if (left == null || right == null) return false;
        return left.val == right.val
            && isMirror(left.left, right.right)
            && isMirror(left.right, right.left);
    }
}
