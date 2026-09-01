class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startRow = 0;
        int startCol = 0;
        int litterCount = 0;
        int[][] litterIndex = new int[m][n];

        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(litterIndex[i], -1);
            for (int j = 0; j < n; j++) {
                char cell = classroom[i].charAt(j);
                if (cell == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (cell == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }

        int targetMask = (1 << litterCount) - 1;
        int[][][] bestEnergy = new int[1 << litterCount][m][n];

        for (int mask = 0; mask <= targetMask; mask++) {
            for (int i = 0; i < m; i++) {
                java.util.Arrays.fill(bestEnergy[mask][i], -1);
            }
        }

        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol, 0, energy, 0});
        bestEnergy[0][startRow][startCol] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int mask = current[2];
            int remainingEnergy = current[3];
            int moves = current[4];

            if (mask == targetMask) {
                return moves;
            }

            if (remainingEnergy == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nextRow = row + dr[d];
                int nextCol = col + dc[d];

                if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                    continue;
                }

                char cell = classroom[nextRow].charAt(nextCol);

                if (cell == 'X') {
                    continue;
                }

                int nextMask = mask;
                int nextEnergy = remainingEnergy - 1;

                if (cell == 'L') {
                    nextMask |= 1 << litterIndex[nextRow][nextCol];
                }

                if (cell == 'R') {
                    nextEnergy = energy;
                }

                if (bestEnergy[nextMask][nextRow][nextCol] >= nextEnergy) {
                    continue;
                }

                bestEnergy[nextMask][nextRow][nextCol] = nextEnergy;
                queue.offer(new int[]{nextRow, nextCol, nextMask, nextEnergy, moves + 1});
            }
        }

        return -1;
    }
}