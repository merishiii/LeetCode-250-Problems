class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[] positions1 = new int[n * n];
        int[] positions2 = new int[n * n];
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    positions1[count1++] = i * n + j;
                }

                if (img2[i][j] == 1) {
                    positions2[count2++] = i * n + j;
                }
            }
        }

        int offset = n - 1;
        int size = 2 * n - 1;
        int[][] shifts = new int[size][size];
        int result = 0;

        for (int i = 0; i < count1; i++) {
            int row1 = positions1[i] / n;
            int col1 = positions1[i] % n;

            for (int j = 0; j < count2; j++) {
                int row2 = positions2[j] / n;
                int col2 = positions2[j] % n;

                int overlap = ++shifts[row1 - row2 + offset][col1 - col2 + offset];

                if (overlap > result) {
                    result = overlap;
                }
            }
        }

        return result;
    }
}