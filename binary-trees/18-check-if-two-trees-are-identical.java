/*
 * Check if Two Trees are Identical
 *
 * p == q handles both-null and same-pointer cases; then check one-null mismatch,
 * then recurse on both subtrees and compare values.
 */
public class CheckIfTwoTreesAreIdentical {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) return true; // both null or same reference
        if (p == null || q == null) return false;
        return p.val == q.val
            && isSameTree(p.left, q.left)
            && isSameTree(p.right, q.right);
    }
}
