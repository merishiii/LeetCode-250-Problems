class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1, last = -1, minDist = Integer.MAX_VALUE;
        int idx = 1;
        ListNode prev = head, cur = head.next;
        while (cur != null && cur.next != null) {
            idx++;
            if ((cur.val > prev.val && cur.val > cur.next.val)
                    || (cur.val < prev.val && cur.val < cur.next.val)) {
                if (first == -1) {
                    first = idx;
                } else {
                    minDist = Math.min(minDist, idx - last);
                }
                last = idx;
            }
            prev = cur;
            cur = cur.next;
        }
        if (first == -1 || first == last) return new int[]{-1, -1};
        return new int[]{minDist, last - first};
    }
}