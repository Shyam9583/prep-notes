/*
 * Morris Preorder Traversal
 *
 * Same threading as Morris inorder but collect on first visit (before going
 * left); on second visit just disconnect and move right without collecting.
 */
import java.util.ArrayList;
import java.util.List;

public class MorrisPreorderTraversal {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            } else {
                // find inorder predecessor
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    // first visit: collect, create thread, go left
                    result.add(curr.val);
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    // second visit: disconnect, move right (no collect)
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }
        return result;
    }
}
