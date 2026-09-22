class Solution {
    private int k;
    private int size;
    private int[] product;
    private long[][] count;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;

        size = 1;
        while (size < n) {
            size <<= 1;
        }

        product = new int[size << 1];
        count = new long[size << 1][k];

        for (int i = 0; i < size; i++) {
            product[size + i] = 1 % k;
        }

        for (int i = 0; i < n; i++) {
            setLeaf(size + i, nums[i]);
        }

        for (int i = size - 1; i > 0; i--) {
            pull(i);
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            update(queries[i][0], queries[i][1]);
            Node node = query(1, 0, size - 1, queries[i][2], n - 1);
            result[i] = (int) node.count[queries[i][3]];
        }

        return result;
    }

    private void setLeaf(int index, int value) {
        int remainder = value % k;
        product[index] = remainder;
        count[index][remainder] = 1;
    }

    private void update(int index, int value) {
        int position = size + index;
        java.util.Arrays.fill(count[position], 0);
        setLeaf(position, value);

        position >>= 1;

        while (position > 0) {
            pull(position);
            position >>= 1;
        }
    }

    private void pull(int index) {
        int left = index << 1;
        int right = left | 1;

        product[index] = (int) ((long) product[left] * product[right] % k);
        java.util.Arrays.fill(count[index], 0);

        for (int i = 0; i < k; i++) {
            count[index][i] = count[left][i];
        }

        for (int i = 0; i < k; i++) {
            int remainder = (int) ((long) product[left] * i % k);
            count[index][remainder] += count[right][i];
        }
    }

    private Node query(int index, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft <= left && right <= queryRight) {
            return new Node(product[index], count[index].clone());
        }

        int mid = (left + right) >>> 1;

        if (queryRight <= mid) {
            return query(index << 1, left, mid, queryLeft, queryRight);
        }

        if (queryLeft > mid) {
            return query(index << 1 | 1, mid + 1, right, queryLeft, queryRight);
        }

        Node first = query(index << 1, left, mid, queryLeft, queryRight);
        Node second = query(index << 1 | 1, mid + 1, right, queryLeft, queryRight);

        return merge(first, second);
    }

    private Node merge(Node first, Node second) {
        long[] mergedCount = new long[k];

        for (int i = 0; i < k; i++) {
            mergedCount[i] = first.count[i];
        }

        for (int i = 0; i < k; i++) {
            int remainder = (int) ((long) first.product * i % k);
            mergedCount[remainder] += second.count[i];
        }

        return new Node((int) ((long) first.product * second.product % k), mergedCount);
    }

    private static class Node {
        int product;
        long[] count;

        Node(int product, long[] count) {
            this.product = product;
            this.count = count;
        }
    }
}