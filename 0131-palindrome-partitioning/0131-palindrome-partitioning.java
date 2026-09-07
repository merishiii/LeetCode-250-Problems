class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];
        String[][] parts = new String[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || palindrome[i + 1][j - 1])) {
                    palindrome[i][j] = true;
                    parts[i][j] = s.substring(i, j + 1);
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        dfs(0, n, palindrome, parts, new ArrayList<>(), result);
        return result;
    }

    private void dfs(int start, int n, boolean[][] palindrome, String[][] parts, List<String> path, List<List<String>> result) {
        if (start == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < n; end++) {
            if (palindrome[start][end]) {
                path.add(parts[start][end]);
                dfs(end + 1, n, palindrome, parts, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}