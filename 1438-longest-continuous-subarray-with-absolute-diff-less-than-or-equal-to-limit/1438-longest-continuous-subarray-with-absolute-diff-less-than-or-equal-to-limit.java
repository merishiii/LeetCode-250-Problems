class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int[] maxDeque = new int[n];
        int[] minDeque = new int[n];
        int maxHead = 0, maxTail = 0;
        int minHead = 0, minTail = 0;
        int left = 0;
        int result = 0;

        for (int right = 0; right < n; right++) {
            while (maxTail > maxHead && nums[maxDeque[maxTail - 1]] <= nums[right]) {
                maxTail--;
            }
            maxDeque[maxTail++] = right;

            while (minTail > minHead && nums[minDeque[minTail - 1]] >= nums[right]) {
                minTail--;
            }
            minDeque[minTail++] = right;

            while (nums[maxDeque[maxHead]] - nums[minDeque[minHead]] > limit) {
                if (maxDeque[maxHead] == left) {
                    maxHead++;
                }

                if (minDeque[minHead] == left) {
                    minHead++;
                }

                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}