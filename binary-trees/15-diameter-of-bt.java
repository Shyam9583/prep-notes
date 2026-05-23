/*
 * Diameter of BT
 *
 * Diameter at each node = leftDepth + rightDepth; update a global max during
 * the depth DFS so you don't need a second pass.
 */
public class DiameterOfBt {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDiameter;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        int left = depth(node.left);
        int right = depth(node.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return 1 + Math.max(left, right);
    }
}
