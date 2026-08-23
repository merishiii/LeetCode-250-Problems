class Solution {
    int[] pref, suf, best;
    char[] arr;
    int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        arr = s.toCharArray();
        pref = new int[4 * n];
        suf = new int[4 * n];
        best = new int[4 * n];
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            arr[queryIndices[i]] = queryCharacters.charAt(i);
            update(1, 0, n - 1, queryIndices[i]);
            result[i] = best[1];
        }
        return result;
    }

    private void build(int node, int lo, int hi) {
        if (lo == hi) {
            pref[node] = suf[node] = best[node] = 1;
            return;
        }
        int mid = (lo + hi) / 2;
        build(2 * node, lo, mid);
        build(2 * node + 1, mid + 1, hi);
        merge(node, lo, hi, mid);
    }

    private void update(int node, int lo, int hi, int idx) {
        if (lo == hi) {
            pref[node] = suf[node] = best[node] = 1;
            return;
        }
        int mid = (lo + hi) / 2;
        if (idx <= mid) update(2 * node, lo, mid, idx);
        else update(2 * node + 1, mid + 1, hi, idx);
        merge(node, lo, hi, mid);
    }

    private void merge(int node, int lo, int hi, int mid) {
        int left = 2 * node, right = 2 * node + 1;
        int leftLen = mid - lo + 1, rightLen = hi - mid;

        pref[node] = pref[left];
        if (pref[left] == leftLen && arr[mid] == arr[mid + 1])
            pref[node] = leftLen + pref[right];

        suf[node] = suf[right];
        if (suf[right] == rightLen && arr[mid] == arr[mid + 1])
            suf[node] = rightLen + suf[left];

        int cross = 0;
        if (arr[mid] == arr[mid + 1])
            cross = suf[left] + pref[right];

        best[node] = Math.max(Math.max(best[left], best[right]), cross);
    }
}