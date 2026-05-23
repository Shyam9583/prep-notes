/*
 * Pre, Post, Inorder in One Traversal
 *
 * Single iterative DFS with a state counter per node: push (node, 1).
 * State 1: collect for preorder, push (node, 2), go left.
 * State 2: collect for inorder, push (node, 3), go right.
 * State 3: collect for postorder, done.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrePostInorderInOneTraversal {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public int[][] allTraversals(TreeNode root) {
        List<Integer> pre = new ArrayList<>(), in = new ArrayList<>(), post = new ArrayList<>();
        if (root == null) return new int[3][0];

        Stack<int[]> stack = new Stack<>(); // [nodeRef_encoded, state] — use index trick below
        // Use Object stack instead
        Stack<Object[]> st = new Stack<>();
        st.push(new Object[]{root, 1});

        while (!st.isEmpty()) {
            Object[] top = st.pop();
            TreeNode node = (TreeNode) top[0];
            int state = (int) top[1];

            if (state == 1) {
                pre.add(node.val);
                st.push(new Object[]{node, 2});
                if (node.left != null) st.push(new Object[]{node.left, 1});
            } else if (state == 2) {
                in.add(node.val);
                st.push(new Object[]{node, 3});
                if (node.right != null) st.push(new Object[]{node.right, 1});
            } else {
                post.add(node.val);
            }
        }

        return new int[][]{
            pre.stream().mapToInt(i -> i).toArray(),
            in.stream().mapToInt(i -> i).toArray(),
            post.stream().mapToInt(i -> i).toArray()
        };
    }
}
