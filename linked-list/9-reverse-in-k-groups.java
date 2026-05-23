/*
 * Advance tail k steps (return as-is if fewer than k nodes remain). Reverse
 * [head, tail), then recursively process from tail. After reversal, head
 * becomes the tail of this group — connect it to the result of the recursive call.
 */
class ReverseInKGroups {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) return head;
            tail = tail.next;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != tail) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head.next = reverseKGroup(tail, k);
        return prev;
    }
}
