class Solution {
    private int[] parent;

    public int minScore(int n, int[][] roads) {
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] road : roads) {
            union(road[0], road[1]);
        }

        int root = find(1);
        int result = Integer.MAX_VALUE;

        for (int[] road : roads) {
            if (find(road[0]) == root) {
                result = Math.min(result, road[2]);
            }
        }

        return result;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }

        return x;
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}