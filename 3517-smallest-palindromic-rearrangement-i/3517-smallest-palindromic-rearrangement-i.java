class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((count[i] & 1) == 1) {
                middle = (char) ('a' + i);
            }

            for (int j = 0; j < count[i] / 2; j++) {
                left.append((char) ('a' + i));
            }
        }

        StringBuilder result = new StringBuilder(left);

        if (middle != 0) {
            result.append(middle);
        }

        result.append(left.reverse());

        return result.toString();
    }
}