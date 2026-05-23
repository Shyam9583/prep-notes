/*
 * Two-pass with HashMap original → clone. Pass 1: create all cloned nodes.
 * Pass 2: wire next and random using the map. cloned[nil] returns nil in Go,
 * so no nil-guards needed (use map.getOrDefault(null, null) equivalent in Java).
 */
import java.util.HashMap;
import java.util.Map;

class CopyListWithRandomPointer {

    static class Node {
        int val;
        Node next;
        Node random;
        Node(int val) { this.val = val; }
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }
}
