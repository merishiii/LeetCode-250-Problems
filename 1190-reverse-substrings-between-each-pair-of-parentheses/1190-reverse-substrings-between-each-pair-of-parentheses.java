class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int open = stack.pop();
                pair[open] = i;
                pair[i] = open;
            }
        }

        StringBuilder result = new StringBuilder();
        int index = 0;
        int direction = 1;

        while (index < n) {
            char c = s.charAt(index);

            if (c == '(' || c == ')') {
                index = pair[index];
                direction = -direction;
            } else {
                result.append(c);
            }

            index += direction;
        }

        return result.toString();
    }
}