/*
 * Floyd's phase 2: after slow/fast meet inside the cycle, reset slow to head.
 * Advance both one step at a time — they meet at the cycle entry. Math:
 * distance from head to entry equals distance from meeting point to entry.
 */
class FindTheStartingPointOfTheLoop {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
