/*
 * Zigzag/Spiral Level Order
 *
 * Standard BFS but pre-allocate level[size] and write at level[i] or
 * level[size-i-1] based on a leftToRight flag; toggle flag each level.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigzagSpiralLevelOrder {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            Integer[] level = new Integer[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                int pos = leftToRight ? i : (size - 1 - i);
                level[pos] = node.val;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            result.add(List.of(level));
            leftToRight = !leftToRight;
        }
        return result;
    }
}
