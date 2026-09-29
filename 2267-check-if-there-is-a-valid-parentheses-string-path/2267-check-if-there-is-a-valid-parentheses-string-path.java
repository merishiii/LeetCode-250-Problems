class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (((m + n - 1) & 1) == 1 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxBalance = (m + n) / 2 + 1;
        boolean[][][] visited = new boolean[m][n][maxBalance + 1];

        return dfs(grid, 0, 0, 0, visited, maxBalance);
    }

    private boolean dfs(char[][] grid, int row, int col, int balance, boolean[][][] visited, int maxBalance) {
        int m = grid.length;
        int n = grid[0].length;

        balance += grid[row][col] == '(' ? 1 : -1;

        if (balance < 0 || balance > maxBalance) {
            return false;
        }

        if (row == m - 1 && col == n - 1) {
            return balance == 0;
        }

        if (visited[row][col][balance]) {
            return false;
        }

        visited[row][col][balance] = true;

        if (row + 1 < m && dfs(grid, row + 1, col, balance, visited, maxBalance)) {
            return true;
        }

        if (col + 1 < n && dfs(grid, row, col + 1, balance, visited, maxBalance)) {
            return true;
        }

        return false;
    }
}