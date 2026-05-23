/*
 * No access to prev. Copy next node's value into current, then skip next node.
 * Effectively deletes node by overwriting it with its successor.
 */
class DeleteNodeInALinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
