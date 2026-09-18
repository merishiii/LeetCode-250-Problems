class Solution {
    private static class Node {
        Node[] next = new Node[26];
        String term = null;
        int childCount = 0;
    }

    private char[][] grid;
    private int rows, cols;
    private List<String> found;

    public List<String> findWords(char[][] board, String[] words) {
        grid = board;
        rows = board.length;
        cols = board[0].length;
        found = new ArrayList<>();

        Node root = new Node();
        for (String w : words) {
            Node cur = root;
            for (int i = 0; i < w.length(); i++) {
                int idx = w.charAt(i) - 'a';
                if (cur.next[idx] == null) {
                    cur.next[idx] = new Node();
                    cur.childCount++;
                }
                cur = cur.next[idx];
            }
            cur.term = w;
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int idx = grid[r][c] - 'a';
                if (root.next[idx] != null) {
                    explore(r, c, root);
                }
            }
        }
        return found;
    }

    private void explore(int r, int c, Node parent) {
        char letter = grid[r][c];
        int idx = letter - 'a';
        Node cur = parent.next[idx];

        if (cur.term != null) {
            found.add(cur.term);
            cur.term = null;
        }

        grid[r][c] = '#';

        if (r > 0 && grid[r - 1][c] != '#' && cur.next[grid[r - 1][c] - 'a'] != null) explore(r - 1, c, cur);
        if (r + 1 < rows && grid[r + 1][c] != '#' && cur.next[grid[r + 1][c] - 'a'] != null) explore(r + 1, c, cur);
        if (c > 0 && grid[r][c - 1] != '#' && cur.next[grid[r][c - 1] - 'a'] != null) explore(r, c - 1, cur);
        if (c + 1 < cols && grid[r][c + 1] != '#' && cur.next[grid[r][c + 1] - 'a'] != null) explore(r, c + 1, cur);

        grid[r][c] = letter;

        if (cur.childCount == 0 && cur.term == null) {
            parent.next[idx] = null;
            parent.childCount--;
        }
    }
}