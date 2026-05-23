/*
 * Maximum Depth of BT
 *
 * Recursive: 1 + max(depth(left), depth(right)); base case null -> 0.
 */
public class MaximumDepthOfBt {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
