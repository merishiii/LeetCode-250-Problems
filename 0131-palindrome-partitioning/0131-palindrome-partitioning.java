class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        boolean[][] palindrome = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            palindrome[i][i] = true;

            for (int j = i + 1; j < n; j++) {
                palindrome[i][j] = chars[i] == chars[j]
                        && (j - i == 1 || palindrome[i + 1][j - 1]);
            }
        }

        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtrack(s, 0, palindrome, path, result);

        return result;
    }

    private void backtrack(String s, int start, boolean[][] palindrome,
                           List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (palindrome[start][end]) {
                path.add(s.substring(start, end + 1));
                backtrack(s, end + 1, palindrome, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}