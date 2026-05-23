/*
 * Floyd's slow/fast pointers. Fast moves 2 steps, slow moves 1. If they meet,
 * cycle exists. If fast hits nil, no cycle. Related: to find cycle entry, reset
 * slow to head and advance both one step at a time — they meet at the entry node.
 */
class DetectACycle {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
