class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int[][] res = new int[n + 1][2];
        int idx = 0, i = 0;
        int start = newInterval[0], end = newInterval[1];
        while (i < n && intervals[i][1] < start) {
            res[idx++] = intervals[i++];
        }
        while (i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        res[idx++] = new int[]{start, end};
        while (i < n) {
            res[idx++] = intervals[i++];
        }
        return java.util.Arrays.copyOf(res, idx);
    }
}