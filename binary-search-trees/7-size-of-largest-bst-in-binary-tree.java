/*
 * Size of Largest BST in Binary Tree → same bottom-up (isValid, min, max, size)
 * pattern as Max Sum BST; return lSize + rSize + 1 when valid, update global max.
 * Empty node: (true, MaxInt, MinInt, 0).
 */
class SizeOfLargestBSTInBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int res = 0;

    public int largestBSTSubtree(TreeNode root) {
        compute(root);
        return res;
    }

    // returns {isValid, min, max, size}
    private int[] compute(TreeNode node) {
        if (node == null) return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};

        int[] left  = compute(node.left);
        int[] right = compute(node.right);

        if (left[0] == 1 && right[0] == 1 && left[2] < node.val && node.val < right[1]) {
            int size = left[3] + right[3] + 1;
            res = Math.max(res, size);
            return new int[]{1, Math.min(left[1], node.val), Math.max(right[2], node.val), size};
        }
        return new int[]{0, 0, 0, 0};
    }
}
