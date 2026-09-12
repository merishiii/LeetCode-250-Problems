class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] data = new int[n][4];

        for (int i = 0; i < n; i++) {
            data[i][0] = intervals.get(i).get(0);
            data[i][1] = intervals.get(i).get(1);
            data[i][2] = intervals.get(i).get(2);
            data[i][3] = i;
        }

        Arrays.sort(data, (a, b) -> Integer.compare(a[0], b[0]));

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = data[i][0];
        }

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = lowerBound(starts, data[i][1] + 1);
        }

        long[][] best = new long[n + 1][5];
        int[][][] pick = new int[n + 1][5][];

        for (int k = 0; k <= 4; k++) {
            pick[n][k] = new int[0];
        }

        for (int i = n - 1; i >= 0; i--) {
            pick[i][0] = new int[0];

            for (int k = 1; k <= 4; k++) {
                long skipScore = best[i + 1][k];
                int[] skipList = pick[i + 1][k];

                int j = next[i];
                long takeScore = data[i][2] + best[j][k - 1];
                int[] takeList = insert(pick[j][k - 1], data[i][3]);

                if (takeScore > skipScore || (takeScore == skipScore && compare(takeList, skipList) < 0)) {
                    best[i][k] = takeScore;
                    pick[i][k] = takeList;
                } else {
                    best[i][k] = skipScore;
                    pick[i][k] = skipList;
                }
            }
        }

        return pick[0][4];
    }

    private int lowerBound(int[] array, int target) {
        int low = 0;
        int high = array.length;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private int[] insert(int[] array, int value) {
        int[] result = new int[array.length + 1];
        int index = 0;

        while (index < array.length && array[index] < value) {
            result[index] = array[index];
            index++;
        }

        result[index] = value;

        for (int i = index; i < array.length; i++) {
            result[i + 1] = array[i];
        }

        return result;
    }

    private int compare(int[] a, int[] b) {
        int length = Math.min(a.length, b.length);

        for (int i = 0; i < length; i++) {
            if (a[i] != b[i]) {
                return a[i] - b[i];
            }
        }

        return a.length - b.length;
    }
}