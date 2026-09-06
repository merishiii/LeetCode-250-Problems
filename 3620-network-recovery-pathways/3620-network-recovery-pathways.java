class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        int m = edges.length;
        int[] head = new int[n];
        int[] next = new int[m];
        int[] to = new int[m];
        int[] weight = new int[m];
        java.util.Arrays.fill(head, -1);

        int[] indegree = new int[n];

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            to[i] = edges[i][1];
            weight[i] = edges[i][2];
            next[i] = head[u];
            head[u] = i;
            indegree[edges[i][1]]++;
        }

        int[] order = new int[n];
        int size = 0;
        java.util.ArrayDeque<Integer> queue = new java.util.ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] degree = indegree.clone();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[size++] = node;

            for (int e = head[node]; e != -1; e = next[e]) {
                if (--degree[to[e]] == 0) {
                    queue.offer(to[e]);
                }
            }
        }

        int[] costs = new int[m + 1];
        costs[0] = 0;

        for (int i = 0; i < m; i++) {
            costs[i + 1] = edges[i][2];
        }

        java.util.Arrays.sort(costs);

        int low = 0;
        int high = costs.length - 1;
        int answer = -1;
        long[] dist = new long[n];

        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (feasible(costs[mid], n, size, order, head, next, to, weight, online, k, dist)) {
                answer = costs[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return answer;
    }

    private boolean feasible(int threshold, int n, int size, int[] order, int[] head, int[] next,
                             int[] to, int[] weight, boolean[] online, long k, long[] dist) {
        final long INF = Long.MAX_VALUE / 4;
        java.util.Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int i = 0; i < size; i++) {
            int u = order[i];

            if (dist[u] == INF) {
                continue;
            }

            if (u != 0 && u != n - 1 && !online[u]) {
                continue;
            }

            if (u == n - 1) {
                continue;
            }

            for (int e = head[u]; e != -1; e = next[e]) {
                if (weight[e] < threshold) {
                    continue;
                }

                int v = to[e];

                if (v != n - 1 && !online[v]) {
                    continue;
                }

                long candidate = dist[u] + weight[e];

                if (candidate <= k && candidate < dist[v]) {
                    dist[v] = candidate;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}