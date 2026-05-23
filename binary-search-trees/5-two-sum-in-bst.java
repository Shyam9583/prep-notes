/*
 * Two Sum in BST → inorder DFS with a HashSet; before inserting node.val,
 * check if k - node.val already exists. Short-circuit as soon as a pair is found.
 */
class TwoSumInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return inorder(root, k, set);
    }

    private boolean inorder(TreeNode node, int k, Set<Integer> set) {
        if (node == null) return false;
        if (inorder(node.left, k, set)) return true;
        if (set.contains(k - node.val)) return true;
        set.add(node.val);
        return inorder(node.right, k, set);
    }
}
