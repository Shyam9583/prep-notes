/*
 * Two pointers with n-gap: advance last by n steps, then move prev/curr/last
 * together until last is nil. curr is then the node to delete. Dummy head
 * handles removing the actual head node.
 */
class RemoveNthNodeFromEnd {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode last = head;
        for (int i = 0; i < n; i++) {
            last = last.next;
        }
        while (last != null) {
            prev = curr;
            curr = curr.next;
            last = last.next;
        }
        prev.next = curr.next;
        return dummy.next;
    }
}
