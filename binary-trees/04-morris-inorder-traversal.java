/*
 * Morris Inorder Traversal
 *
 * Thread rightmost of left subtree back to curr; findRightMost starts at
 * node.left, stops when curr.right == null or curr.right == node;
 * first visit: create thread and go left; second visit (rightMost.right == curr):
 * disconnect, collect, go right.
 */
import java.util.ArrayList;
import java.util.List;

public class MorrisInorderTraversal {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            } else {
                // find inorder predecessor (rightmost of left subtree)
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    // first visit: create thread
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    // second visit: disconnect thread, collect
                    pred.right = null;
                    result.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return result;
    }
}
