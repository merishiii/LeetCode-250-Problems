class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] distance = new int[n][n];
        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    distance[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    distance[i][j] = -1;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || distance[nr][nc] != -1) {
                    continue;
                }

                distance[nr][nc] = distance[current[0]][current[1]] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }

        java.util.PriorityQueue<int[]> heap = new java.util.PriorityQueue<>((a, b) -> b[2] - a[2]);
        boolean[][] visited = new boolean[n][n];

        heap.offer(new int[]{0, 0, distance[0][0]});
        visited[0][0] = true;

        while (!heap.isEmpty()) {
            int[] current = heap.poll();

            if (current[0] == n - 1 && current[1] == n - 1) {
                return current[2];
            }

            for (int d = 0; d < 4; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                heap.offer(new int[]{nr, nc, Math.min(current[2], distance[nr][nc])});
            }
        }

        return 0;
    }
}