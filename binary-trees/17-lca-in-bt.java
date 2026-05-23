/*
 * LCA in BT
 *
 * Return root when it equals p or q; if both subtrees return non-null, current
 * node is the LCA; otherwise bubble up whichever side is non-null.
 */
public class LcaInBt {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root; // p and q in different subtrees
        return left != null ? left : right;
    }
}
