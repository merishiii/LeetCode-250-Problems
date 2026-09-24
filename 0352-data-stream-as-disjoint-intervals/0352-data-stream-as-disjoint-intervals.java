class SummaryRanges {
    private TreeMap<Integer, int[]> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>();
    }

    public void addNum(int value) {
        Map.Entry<Integer, int[]> floor = intervals.floorEntry(value);

        if (floor != null && floor.getValue()[1] >= value) {
            return;
        }

        Map.Entry<Integer, int[]> higher = intervals.higherEntry(value);

        boolean mergeLeft = floor != null && floor.getValue()[1] + 1 == value;
        boolean mergeRight = higher != null && higher.getKey() == value + 1;

        if (mergeLeft && mergeRight) {
            int[] left = floor.getValue();
            left[1] = higher.getValue()[1];
            intervals.remove(higher.getKey());
        } else if (mergeLeft) {
            floor.getValue()[1] = value;
        } else if (mergeRight) {
            int[] right = higher.getValue();
            intervals.remove(higher.getKey());
            intervals.put(value, new int[]{value, right[1]});
        } else {
            intervals.put(value, new int[]{value, value});
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[intervals.size()][2];
        int index = 0;

        for (int[] interval : intervals.values()) {
            result[index++] = interval;
        }

        return result;
    }
}