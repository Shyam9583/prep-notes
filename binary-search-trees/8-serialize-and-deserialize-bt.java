/*
 * Serialize and Deserialize BT → preorder DFS: serialize nulls as "N", join
 * with ",". Deserialize with a ptr index: if token is "N" advance ptr and
 * return null, else parse value, advance ptr, recurse for left then right.
 */
class SerializeAndDeserializeBT {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) { sb.append("N,"); return; }
        sb.append(node.val).append(',');
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    private String[] tokens;
    private int ptr;

    public TreeNode deserialize(String data) {
        tokens = data.split(",");
        ptr = 0;
        return build();
    }

    private TreeNode build() {
        if (tokens[ptr].equals("N")) { ptr++; return null; }
        TreeNode node = new TreeNode(Integer.parseInt(tokens[ptr++]));
        node.left  = build();
        node.right = build();
        return node;
    }
}
