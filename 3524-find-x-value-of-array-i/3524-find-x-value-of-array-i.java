class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];
            int value = num % k;

            next[value]++;

            for (int remainder = 0; remainder < k; remainder++) {
                int newRemainder = (int) ((long) remainder * value % k);
                next[newRemainder] += dp[remainder];
            }

            for (int remainder = 0; remainder < k; remainder++) {
                result[remainder] += next[remainder];
            }

            dp = next;
        }

        return result;
    }
}