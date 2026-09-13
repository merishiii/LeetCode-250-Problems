class Solution {
    private String expression;
    private List<Integer>[][] memo;

    public List<Integer> diffWaysToCompute(String expression) {
        this.expression = expression;
        int n = expression.length();
        memo = new List[n][n];
        return solve(0, n - 1);
    }

    private List<Integer> solve(int left, int right) {
        if (memo[left][right] != null) {
            return memo[left][right];
        }

        List<Integer> result = new ArrayList<>();
        boolean hasOperator = false;

        for (int i = left; i <= right; i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                hasOperator = true;
                List<Integer> first = solve(left, i - 1);
                List<Integer> second = solve(i + 1, right);

                for (int a : first) {
                    for (int b : second) {
                        if (c == '+') {
                            result.add(a + b);
                        } else if (c == '-') {
                            result.add(a - b);
                        } else {
                            result.add(a * b);
                        }
                    }
                }
            }
        }

        if (!hasOperator) {
            int value = 0;

            for (int i = left; i <= right; i++) {
                value = value * 10 + expression.charAt(i) - '0';
            }

            result.add(value);
        }

        memo[left][right] = result;
        return result;
    }
}