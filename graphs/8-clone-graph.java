/*
DFS with a Map<Node, Node> memo: create the clone before recursing into neighbors
so cycles terminate. On revisit, return the already-cloned node from the map.
*/
import java.util.*;

class CloneGraph {
    private Map<Node, Node> clones = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (clones.containsKey(node)) return clones.get(node);
        Node clone = new Node(node.val);
        clones.put(node, clone);
        for (Node next : node.neighbors) {
            clone.neighbors.add(cloneGraph(next));
        }
        return clone;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() { val = 0; neighbors = new ArrayList<>(); }
        public Node(int _val) { val = _val; neighbors = new ArrayList<>(); }
        public Node(int _val, ArrayList<Node> _neighbors) { val = _val; neighbors = _neighbors; }
    }
}
