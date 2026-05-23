/*
 * BST Iterator → lazy iterative inorder using a stack + curr pointer.
 * Next: push left spine of curr, pop top, set curr = node.right, return value.
 * HasNext: true if stack or curr is non-nil. O(1) amortized, O(h) space.
 */
class BSTIterator {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private TreeNode curr;
    private Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        this.curr = root;
    }

    public int next() {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        TreeNode node = stack.pop();
        curr = node.right;
        return node.val;
    }

    public boolean hasNext() {
        return curr != null || !stack.isEmpty();
    }
}
