/*
 * Maximum Path Sum
 *
 * DFS returns best single-branch gain (node.val + max(left, right)); clamp
 * negative children to 0; update global max with node.val + left + right
 * (full path through node) at each node.
 */
public class MaximumPathSum {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        gain(root);
        return maxSum;
    }

    private int gain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, gain(node.left));
        int right = Math.max(0, gain(node.right));
        maxSum = Math.max(maxSum, node.val + left + right); // full path through node
        return node.val + Math.max(left, right); // best single branch
    }
}
