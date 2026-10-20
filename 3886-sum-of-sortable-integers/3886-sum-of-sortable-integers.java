class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        int result = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int previousMax = Integer.MIN_VALUE;

            for (int start = 0; start < n && valid; start += k) {
                int end = start + k - 1;
                int descents = prefix[end] - prefix[start];

                if (nums[end] > nums[start]) {
                    descents++;
                }

                if (descents > 1) {
                    valid = false;
                    break;
                }

                int minValue = Integer.MAX_VALUE;
                int maxValue = Integer.MIN_VALUE;

                for (int i = start; i <= end; i++) {
                    if (nums[i] < minValue) {
                        minValue = nums[i];
                    }
                    if (nums[i] > maxValue) {
                        maxValue = nums[i];
                    }
                }

                if (minValue < previousMax) {
                    valid = false;
                    break;
                }

                previousMax = maxValue;
            }

            if (valid) {
                result += k;
            }
        }

        return result;
    }
}