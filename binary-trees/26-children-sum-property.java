/*
 * Children Sum Property
 *
 * DFS returns the subtree sum or -1 as sentinel; leaf returns its own value;
 * internal node checks root.data == leftSum + rightSum, propagates -1 if violated.
 */
public class ChildrenSumProperty {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isSumProperty(TreeNode root) {
        return checkSum(root) != -1;
    }

    private int checkSum(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return node.val; // leaf

        int leftSum = checkSum(node.left);
        if (leftSum == -1) return -1;
        int rightSum = checkSum(node.right);
        if (rightSum == -1) return -1;

        if (node.val != leftSum + rightSum) return -1;
        return node.val;
    }
}
