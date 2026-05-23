/*
 * Two pointers traverse both lists: on reaching nil, redirect to the other
 * list's head. They meet at the intersection after traversing lenA + lenB
 * steps combined — equalises the offset difference.
 */
class IntersectionOfTwoLinkedLists {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }
}
