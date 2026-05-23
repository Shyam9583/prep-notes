/*
 * Construct BT from Postorder and Inorder
 *
 * postorder[last] is root; find it in inorder to get m = leftSize;
 * left postorder is postorder[:m], right is postorder[m:n-1]; recurse.
 */
import java.util.HashMap;
import java.util.Map;

public class ConstructBtFromPostorderAndInorder {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderIndex.put(inorder[i], i);
        return build(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inorderIndex);
    }

    private TreeNode build(int[] post, int postL, int postR,
                            int[] in, int inL, int inR,
                            Map<Integer, Integer> inIdx) {
        if (postL > postR) return null;
        TreeNode root = new TreeNode(post[postR]);
        int m = inIdx.get(post[postR]) - inL; // left subtree size
        root.left = build(post, postL, postL + m - 1, in, inL, inL + m - 1, inIdx);
        root.right = build(post, postL + m, postR - 1, in, inL + m + 1, inR, inIdx);
        return root;
    }
}
