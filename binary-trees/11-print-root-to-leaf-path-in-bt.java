/*
 * Print Root to Leaf Path in BT
 *
 * DFS backtracking: add root.data to acc, recurse left and right, remove last
 * on return. Add snapshot to result only at leaves (left == null && right == null),
 * not at null nodes — otherwise each leaf adds the path twice.
 */
import java.util.ArrayList;
import java.util.List;

public class PrintRootToLeafPathInBt {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<List<Integer>> pathsFromRoot(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path)); // leaf: take snapshot
        } else {
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }
        path.remove(path.size() - 1); // backtrack
    }
}
