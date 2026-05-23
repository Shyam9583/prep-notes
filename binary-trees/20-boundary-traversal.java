/*
 * Boundary Traversal
 *
 * Three separate passes: left boundary top-down (skip leaves, prefer left child),
 * all leaves left-to-right, right boundary bottom-up (skip leaves, prefer right
 * child); add root separately upfront.
 */
import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversal {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        result.add(root.val);
        addLeftBoundary(root.left, result);
        addLeaves(root.left, result);
        addLeaves(root.right, result);
        addRightBoundary(root.right, result);
        return result;
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private void addLeftBoundary(TreeNode node, List<Integer> result) {
        if (node == null || isLeaf(node)) return;
        result.add(node.val);
        if (node.left != null) addLeftBoundary(node.left, result);
        else addLeftBoundary(node.right, result);
    }

    private void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) return;
        if (isLeaf(node)) { result.add(node.val); return; }
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    private void addRightBoundary(TreeNode node, List<Integer> result) {
        if (node == null || isLeaf(node)) return;
        if (node.right != null) addRightBoundary(node.right, result);
        else addRightBoundary(node.left, result);
        result.add(node.val); // add after recursion = bottom-up
    }
}
