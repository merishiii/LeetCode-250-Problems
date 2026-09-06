class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] cost = new int[m][n];

        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        java.util.ArrayDeque<int[]> deque = new java.util.ArrayDeque<>();
        cost[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!deque.isEmpty()) {
            int[] current = deque.pollFirst();
            int row = current[0];
            int col = current[1];

            for (int d = 0; d < 4; d++) {
                int nr = row + dr[d];
                int nc = col + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                int weight = grid.get(nr).get(nc);

                if (cost[row][col] + weight < cost[nr][nc]) {
                    cost[nr][nc] = cost[row][col] + weight;

                    if (weight == 0) {
                        deque.offerFirst(new int[]{nr, nc});
                    } else {
                        deque.offerLast(new int[]{nr, nc});
                    }
                }
            }
        }

        return health - cost[m - 1][n - 1] >= 1;
    }
}