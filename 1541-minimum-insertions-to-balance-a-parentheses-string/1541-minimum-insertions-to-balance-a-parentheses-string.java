class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int needed = 0;
        int n = s.length();
        int i = 0;

        while (i < n) {
            if (s.charAt(i) == '(') {
                needed++;
                i++;
            } else {
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2;
                } else {
                    insertions++;
                    i++;
                }

                if (needed > 0) {
                    needed--;
                } else {
                    insertions++;
                }
            }
        }

        return insertions + needed * 2;
    }
}