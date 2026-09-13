class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] outDegree = new int[m][n];
        int[] queue = new int[m * n];
        int head = 0;
        int tail = 0;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int value = matrix[row][col];

                if (row > 0 && matrix[row - 1][col] > value) {
                    outDegree[row][col]++;
                }
                if (row + 1 < m && matrix[row + 1][col] > value) {
                    outDegree[row][col]++;
                }
                if (col > 0 && matrix[row][col - 1] > value) {
                    outDegree[row][col]++;
                }
                if (col + 1 < n && matrix[row][col + 1] > value) {
                    outDegree[row][col]++;
                }

                if (outDegree[row][col] == 0) {
                    queue[tail++] = row * n + col;
                }
            }
        }

        int length = 0;

        while (head < tail) {
            int levelSize = tail - head;
            length++;

            for (int i = 0; i < levelSize; i++) {
                int cell = queue[head++];
                int row = cell / n;
                int col = cell % n;
                int value = matrix[row][col];

                if (row > 0 && matrix[row - 1][col] < value && --outDegree[row - 1][col] == 0) {
                    queue[tail++] = (row - 1) * n + col;
                }
                if (row + 1 < m && matrix[row + 1][col] < value && --outDegree[row + 1][col] == 0) {
                    queue[tail++] = (row + 1) * n + col;
                }
                if (col > 0 && matrix[row][col - 1] < value && --outDegree[row][col - 1] == 0) {
                    queue[tail++] = row * n + col - 1;
                }
                if (col + 1 < n && matrix[row][col + 1] < value && --outDegree[row][col + 1] == 0) {
                    queue[tail++] = row * n + col + 1;
                }
            }
        }

        return length;
    }
}