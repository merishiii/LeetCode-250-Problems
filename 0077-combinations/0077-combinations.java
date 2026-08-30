import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int[] cur = new int[k];
        backtrack(1, 0, n, k, cur, res);
        return res;
    }

    private void backtrack(int start, int depth, int n, int k, int[] cur, List<List<Integer>> res) {
        if (depth == k) {
            List<Integer> list = new ArrayList<>(k);
            for (int v : cur) list.add(v);
            res.add(list);
            return;
        }
        for (int i = start; i <= n - (k - depth) + 1; i++) {
            cur[depth] = i;
            backtrack(i + 1, depth + 1, n, k, cur, res);
        }
    }
}