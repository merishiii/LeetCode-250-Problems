class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1000000007L;
        int[] bitCount = new int[31];

        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if (((num >> b) & 1) == 1) {
                    bitCount[b]++;
                }
            }
        }

        long result = 0;

        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int b = 0; b < 31; b++) {
                if (bitCount[b] > 0) {
                    value |= 1L << b;
                    bitCount[b]--;
                }
            }

            value %= MOD;
            result = (result + value * value) % MOD;
        }

        return (int) result;
    }
}