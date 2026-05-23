/*
 * Rotating right by k = moving last k nodes to front. k %= n to handle k > n.
 * Find split point using two pointers with k-gap (same as remove Nth from end).
 * Cut, attach old tail to old head, return new head.
 */
class RotateALinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) { tail = tail.next; n++; }
        k %= n;
        if (k == 0) return head;
        ListNode prev = head;
        ListNode curr = head;
        for (int i = 0; i < k; i++) curr = curr.next;
        while (curr.next != null) { prev = prev.next; curr = curr.next; }
        ListNode newHead = prev.next;
        prev.next = null;
        curr.next = head;
        return newHead;
    }
}
