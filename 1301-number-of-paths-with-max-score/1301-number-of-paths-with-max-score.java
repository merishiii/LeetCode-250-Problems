class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        final int MOD = 1000000007;
        int n = board.size();
        int[][] best = new int[n + 1][n + 1];
        int[][] ways = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(best[i], -1);
        }

        best[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == n - 1 && j == n - 1) {
                    continue;
                }

                char cell = board.get(i).charAt(j);

                if (cell == 'X') {
                    continue;
                }

                int maxValue = -1;
                long count = 0;

                int[][] options = {{i + 1, j}, {i, j + 1}, {i + 1, j + 1}};

                for (int[] option : options) {
                    int r = option[0];
                    int c = option[1];

                    if (r >= n || c >= n || best[r][c] < 0) {
                        continue;
                    }

                    if (best[r][c] > maxValue) {
                        maxValue = best[r][c];
                        count = ways[r][c];
                    } else if (best[r][c] == maxValue) {
                        count = (count + ways[r][c]) % MOD;
                    }
                }

                if (maxValue < 0) {
                    continue;
                }

                int value = cell == 'E' ? 0 : cell - '0';
                best[i][j] = maxValue + value;
                ways[i][j] = (int) (count % MOD);
            }
        }

        if (best[0][0] < 0) {
            return new int[]{0, 0};
        }

        return new int[]{best[0][0], ways[0][0]};
    }
}