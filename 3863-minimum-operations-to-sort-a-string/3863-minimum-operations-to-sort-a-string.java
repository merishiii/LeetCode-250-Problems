class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;
        char minChar = 'z';
        char maxChar = 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (i > 0 && s.charAt(i - 1) > c) {
                sorted = false;
            }

            if (c < minChar) {
                minChar = c;
            }

            if (c > maxChar) {
                maxChar = c;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n <= 2) {
            return -1;
        }

        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        boolean minInPrefix = false;
        boolean maxInSuffix = false;

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) {
                minInPrefix = true;
                break;
            }
        }

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) {
                maxInSuffix = true;
                break;
            }
        }

        if (minInPrefix || maxInSuffix) {
            return 2;
        }

        return 3;
    }
}