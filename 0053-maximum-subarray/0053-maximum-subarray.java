class Solution {
    public int maxSubArray(int[] nums) {
        int best = nums[0];
        int running = nums[0];

        for (int i = 1; i < nums.length; i++) {
            running = Math.max(nums[i], running + nums[i]);
            best = Math.max(best, running);
        }

        return best;
    }
}