class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[] keys = new long[n];

        for (int i = 0; i < n; i++) {
            int value = nums[i];
            int sum = 0;

            while (value > 0) {
                sum += value % 10;
                value /= 10;
            }

            keys[i] = ((long) sum << 50) | ((long) nums[i] << 17) | i;
        }

        java.util.Arrays.sort(keys);

        int[] target = new int[n];

        for (int i = 0; i < n; i++) {
            target[i] = (int) (keys[i] & ((1 << 17) - 1));
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int length = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = target[j];
                length++;
            }

            swaps += length - 1;
        }

        return swaps;
    }
}