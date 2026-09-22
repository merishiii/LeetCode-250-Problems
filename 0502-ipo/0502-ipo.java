class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];

        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        java.util.Arrays.sort(projects, (a, b) -> a[0] - b[0]);

        java.util.PriorityQueue<Integer> heap = new java.util.PriorityQueue<>((a, b) -> b - a);
        int index = 0;

        for (int i = 0; i < k; i++) {
            while (index < n && projects[index][0] <= w) {
                heap.offer(projects[index][1]);
                index++;
            }

            if (heap.isEmpty()) {
                break;
            }

            w += heap.poll();
        }

        return w;
    }
}