class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                palindrome[i][j] = s.charAt(i) == s.charAt(j)
                        && (j - i < 2 || palindrome[i + 1][j - 1]);
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            int end1 = i + k - 1;
            int end2 = i + k;

            if (end1 < n && palindrome[i][end1]) {
                dp[end1 + 1] = Math.max(dp[end1 + 1], dp[i] + 1);
            }

            if (end2 < n && palindrome[i][end2]) {
                dp[end2 + 1] = Math.max(dp[end2 + 1], dp[i] + 1);
            }
        }

        return dp[n];
    }
}
