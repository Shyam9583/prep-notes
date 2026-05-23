/*
 * Maximum Width of BT
 *
 * BFS with index tracking: left child of node at index i gets 2*i, right gets
 * 2*i+1. Width per level = lastIndex - firstIndex + 1. Use long to avoid
 * overflow on deep trees.
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumWidthOfBt {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = 0;
        Deque<Object[]> q = new ArrayDeque<>();
        q.add(new Object[]{root, 0L});

        while (!q.isEmpty()) {
            int size = q.size();
            long firstIdx = (long) ((Object[]) q.peekFirst())[1];
            long lastIdx = firstIdx;

            for (int i = 0; i < size; i++) {
                Object[] item = q.poll();
                TreeNode node = (TreeNode) item[0];
                long idx = (long) item[1] - firstIdx; // normalize to prevent overflow
                lastIdx = idx;
                if (node.left != null) q.add(new Object[]{node.left, 2 * idx});
                if (node.right != null) q.add(new Object[]{node.right, 2 * idx + 1});
            }
            maxWidth = (int) Math.max(maxWidth, lastIdx - 0 + 1);
        }
        return maxWidth;
    }
}
