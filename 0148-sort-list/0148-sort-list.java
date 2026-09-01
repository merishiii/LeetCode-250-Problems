class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = slow.next;
        slow.next = null;

        ListNode left = sortList(head);

        return merge(left, sortList(right));
    }

    private ListNode merge(ListNode first, ListNode second) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (first != null && second != null) {
            if (first.val <= second.val) {
                current.next = first;
                first = first.next;
            } else {
                current.next = second;
                second = second.next;
            }
            current = current.next;
        }

        current.next = first != null ? first : second;

        return dummy.next;
    }
}