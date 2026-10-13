class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = Math.abs(nums[i]);
        }

        java.util.Arrays.sort(values);

        int negativeCount = n / 2;
        long result = 0;

        for (int i = 0; i < n; i++) {
            long square = (long) values[i] * values[i];

            if (i < negativeCount) {
                result -= square;
            } else {
                result += square;
            }
        }

        return result;
    }
}