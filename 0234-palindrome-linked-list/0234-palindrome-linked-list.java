class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        ListNode previous = null;

        while (slow != null) {
            ListNode next = slow.next;
            slow.next = previous;
            previous = slow;
            slow = next;
        }

        ListNode first = head;
        ListNode second = previous;

        while (second != null) {
            if (first.val != second.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }

        return true;
    }
}