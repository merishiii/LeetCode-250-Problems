/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(0, head), beforeLeft = dummy;
        for (int i = 1; i < left; i++) beforeLeft = beforeLeft.next;

        ListNode tail = beforeLeft.next, cur = tail.next;
        for (int i = left; i < right; i++) {
            tail.next = cur.next;
            cur.next = beforeLeft.next;
            beforeLeft.next = cur;
            cur = tail.next;
        }
        return dummy.next;
    }
}