/*
 * Vertical Order Traversal
 *
 * BFS tracking (node, row, col); store col -> list of (row, val); sort each
 * column's list by (row, val) — same-column same-row nodes are sorted by value.
 * Use TreeMap on col for left-to-right output.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.TreeMap;

public class VerticalOrderTraversal {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<int[]>> colMap = new TreeMap<>(); // col -> [(row, val)]
        Deque<Object[]> q = new ArrayDeque<>();
        q.add(new Object[]{root, 0, 0}); // node, row, col

        while (!q.isEmpty()) {
            Object[] item = q.poll();
            TreeNode node = (TreeNode) item[0];
            int row = (int) item[1];
            int col = (int) item[2];
            colMap.computeIfAbsent(col, k -> new ArrayList<>()).add(new int[]{row, node.val});
            if (node.left != null) q.add(new Object[]{node.left, row + 1, col - 1});
            if (node.right != null) q.add(new Object[]{node.right, row + 1, col + 1});
        }

        List<List<Integer>> result = new ArrayList<>();
        for (List<int[]> entries : colMap.values()) {
            entries.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
            List<Integer> col = new ArrayList<>();
            for (int[] e : entries) col.add(e[1]);
            result.add(col);
        }
        return result;
    }
}
