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
    public ListNode partition(ListNode head, int x) {
        ListNode lessHead = new ListNode(0), geHead = new ListNode(0);
        ListNode less = lessHead, ge = geHead;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            if (cur.val < x) {
                less.next = cur;
                less = cur;
            } else {
                ge.next = cur;
                ge = cur;
            }
        }
        ge.next = null;
        less.next = geHead.next;
        return lessHead.next;
    }
}