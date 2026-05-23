/*
 * Construct BT from Preorder and Inorder
 *
 * preorder[0] is always root; find it in inorder to split left/right subtrees;
 * left subtree has m = inorderPos nodes, so left preorder is preorder[1:m+1];
 * recurse.
 */
import java.util.HashMap;
import java.util.Map;

public class ConstructBtFromPreorderAndInorder {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderIndex.put(inorder[i], i);
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderIndex);
    }

    private TreeNode build(int[] pre, int preL, int preR,
                           int[] in, int inL, int inR,
                           Map<Integer, Integer> inIdx) {
        if (preL > preR) return null;
        TreeNode root = new TreeNode(pre[preL]);
        int m = inIdx.get(pre[preL]) - inL; // left subtree size
        root.left = build(pre, preL + 1, preL + m, in, inL, inL + m - 1, inIdx);
        root.right = build(pre, preL + m + 1, preR, in, inL + m + 1, inR, inIdx);
        return root;
    }
}
