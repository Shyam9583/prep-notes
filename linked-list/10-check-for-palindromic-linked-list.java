/*
 * Find middle (slow/fast, fast starts at head.Next → lands on first middle).
 * Reverse second half from middle. Compare both halves from head and reversed
 * head simultaneously.
 */
class CheckForPalindromicLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalf = reverse(slow.next);
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        boolean result = true;
        while (p2 != null) {
            if (p1.val != p2.val) { result = false; break; }
            p1 = p1.next;
            p2 = p2.next;
        }
        slow.next = reverse(secondHalf);
        return result;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
