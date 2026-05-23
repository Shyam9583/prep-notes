/*
 * Top View of BT
 *
 * BFS with horizontal distance; store col -> node.val only if col not yet seen
 * (putIfAbsent). First BFS visit per col is the topmost node.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TopViewOfBt {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, Integer> colMap = new TreeMap<>();
        Deque<Object[]> q = new ArrayDeque<>();
        q.add(new Object[]{root, 0});

        while (!q.isEmpty()) {
            Object[] pair = q.poll();
            TreeNode node = (TreeNode) pair[0];
            int col = (int) pair[1];
            colMap.putIfAbsent(col, node.val); // first visit per col = topmost
            if (node.left != null) q.add(new Object[]{node.left, col - 1});
            if (node.right != null) q.add(new Object[]{node.right, col + 1});
        }

        result.addAll(colMap.values());
        return result;
    }
}
