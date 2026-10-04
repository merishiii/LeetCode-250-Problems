class Solution {
    public int scheduleCourse(int[][] courses) {
        java.util.Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        java.util.PriorityQueue<Integer> heap = new java.util.PriorityQueue<>((a, b) -> b - a);
        int time = 0;

        for (int[] course : courses) {
            time += course[0];
            heap.offer(course[0]);

            if (time > course[1]) {
                time -= heap.poll();
            }
        }

        return heap.size();
    }
}