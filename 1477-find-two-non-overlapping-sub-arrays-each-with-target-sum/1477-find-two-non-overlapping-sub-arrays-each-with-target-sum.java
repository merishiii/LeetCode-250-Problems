class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        int left = 0;
        int sum = 0;
        int shortest = Integer.MAX_VALUE;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int length = right - left + 1;

                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(answer, length + best[left - 1]);
                }

                shortest = Math.min(shortest, length);
            }

            best[right] = shortest;
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}