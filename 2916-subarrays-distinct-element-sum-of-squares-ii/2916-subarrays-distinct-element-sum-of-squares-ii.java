class Solution {
    private long[] tree1;
    private long[] tree2;
    private int size;

    public int sumCounts(int[] nums) {
        final long MOD = 1000000007L;
        size = nums.length;
        tree1 = new long[size + 2];
        tree2 = new long[size + 2];
        int[] last = new int[100001];

        long current = 0;
        long result = 0;

        for (int j = 1; j <= size; j++) {
            int value = nums[j - 1];
            int p = last[value];

            long rangeSum = prefix(j) - prefix(p);
            current = (current + 2 * (rangeSum % MOD) + (j - p)) % MOD;

            rangeAdd(p + 1, j, 1);
            last[value] = j;

            result = (result + current) % MOD;
        }

        return (int) result;
    }

    private void update(long[] tree, int index, long value) {
        for (; index <= size; index += index & -index) {
            tree[index] += value;
        }
    }

    private long query(long[] tree, int index) {
        long sum = 0;

        for (; index > 0; index -= index & -index) {
            sum += tree[index];
        }

        return sum;
    }

    private void rangeAdd(int left, int right, long value) {
        update(tree1, left, value);
        update(tree1, right + 1, -value);
        update(tree2, left, value * (left - 1));
        update(tree2, right + 1, -value * right);
    }

    private long prefix(int index) {
        return query(tree1, index) * index - query(tree2, index);
    }
}