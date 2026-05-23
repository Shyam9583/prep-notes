/*
 * Slow/fast pointers. fast starts at head.Next so slow lands on the first
 * middle for even-length lists. If fast starts at head, slow lands on the
 * second middle.
 */
class MiddleOfTheLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
