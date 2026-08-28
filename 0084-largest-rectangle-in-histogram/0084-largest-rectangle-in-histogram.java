class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = -1;
        int best = 0;
        for (int i = 0; i <= n; i++) {
            int cur = (i == n) ? 0 : heights[i];
            while (top >= 0 && heights[stack[top]] >= cur) {
                int h = heights[stack[top--]];
                int left = (top >= 0) ? stack[top] + 1 : 0;
                int area = h * (i - left);
                if (area > best) best = area;
            }
            stack[++top] = i;
        }
        return best;
    }
}