/*
 * Bottom View of BT
 *
 * BFS with horizontal distance (col); for each level overwrite col -> node.val
 * in a TreeMap — last write per col is the deepest node. DFS fails because it
 * doesn't guarantee deepest-wins per column.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BottomViewOfBt {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> bottomView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, Integer> colMap = new TreeMap<>();
        Deque<int[]> q = new ArrayDeque<>(); // [node_index_in_queue, col]
        // use a separate queue for nodes
        Deque<TreeNode> nodeQ = new ArrayDeque<>();
        nodeQ.add(root);
        q.add(new int[]{0});

        // combined queue with (node, col)
        Deque<Object[]> combined = new ArrayDeque<>();
        combined.add(new Object[]{root, 0});

        while (!combined.isEmpty()) {
            Object[] pair = combined.poll();
            TreeNode node = (TreeNode) pair[0];
            int col = (int) pair[1];
            colMap.put(col, node.val); // overwrite: last BFS visit = deepest
            if (node.left != null) combined.add(new Object[]{node.left, col - 1});
            if (node.right != null) combined.add(new Object[]{node.right, col + 1});
        }

        result.addAll(colMap.values());
        return result;
    }
}
