class Solution {
    public int maxDepth(String s) {
        int depth = 0;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                depth++;
                result = Math.max(result, depth);
            } else if (c == ')') {
                depth--;
            }
        }

        return result;
    }
}