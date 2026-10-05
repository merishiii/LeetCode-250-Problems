class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int best = Integer.MIN_VALUE;
            int taken = 0;

            for (int j = 0; j < 3 && i + j < n; j++) {
                taken += stoneValue[i + j];
                best = Math.max(best, taken - dp[i + j + 1]);
            }

            dp[i] = best;
        }

        if (dp[0] > 0) {
            return "Alice";
        }

        if (dp[0] < 0) {
            return "Bob";
        }

        return "Tie";
    }
}