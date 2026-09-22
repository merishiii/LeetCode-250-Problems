class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int minValue = arrays.get(0).get(0);
        int maxValue = arrays.get(0).get(arrays.get(0).size() - 1);
        int result = 0;

        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> current = arrays.get(i);
            int first = current.get(0);
            int last = current.get(current.size() - 1);

            result = Math.max(result, Math.abs(last - minValue));
            result = Math.max(result, Math.abs(maxValue - first));

            minValue = Math.min(minValue, first);
            maxValue = Math.max(maxValue, last);
        }

        return result;
    }
}