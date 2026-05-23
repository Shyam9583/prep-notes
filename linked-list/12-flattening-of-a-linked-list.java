/*
 * Min-heap across all list heads. Seed with all next-chain heads. Each poll
 * gives the smallest node; push its bottom child into the heap. Builds sorted
 * flattened list via bottom pointers.
 */
import java.util.PriorityQueue;

class FlatteningOfALinkedList {

    static class ListNode {
        int val;
        ListNode next;   // horizontal chain (list of lists)
        ListNode bottom; // vertical chain (each sublist)
        ListNode(int val) { this.val = val; }
    }

    public ListNode flatten(ListNode head) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode curr = head;
        while (curr != null) {
            pq.offer(curr);
            curr = curr.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.bottom = node;
            tail = tail.bottom;
            if (node.bottom != null) pq.offer(node.bottom);
        }
        return dummy.bottom;
    }
}
