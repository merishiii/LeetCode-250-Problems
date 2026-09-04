class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        int length = 0;
        boolean hasOdd = false;

        for (int value : count) {
            length += value / 2 * 2;

            if ((value & 1) == 1) {
                hasOdd = true;
            }
        }

        return hasOdd ? length + 1 : length;
    }
}