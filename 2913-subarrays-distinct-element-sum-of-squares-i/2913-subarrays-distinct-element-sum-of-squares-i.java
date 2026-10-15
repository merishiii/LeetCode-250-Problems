class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int result = 0;

        for (int i = 0; i < n; i++) {
            boolean[] seen = new boolean[101];
            int distinct = 0;

            for (int j = i; j < n; j++) {
                int value = nums.get(j);

                if (!seen[value]) {
                    seen[value] = true;
                    distinct++;
                }

                result += distinct * distinct;
            }
        }

        return result;
    }
}