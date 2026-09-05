class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] count = new int[26];
        int middle = -1;

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((count[i] & 1) == 1) {
                middle = i;
            }
            count[i] /= 2;
        }

        StringBuilder left = new StringBuilder();
        int remaining = s.length() / 2;

        while (remaining > 0) {
            boolean found = false;

            for (int i = 0; i < 26; i++) {
                if (count[i] == 0) {
                    continue;
                }

                count[i]--;
                long ways = countPermutations(count, k);

                if (ways >= k) {
                    left.append((char) ('a' + i));
                    found = true;
                    break;
                }

                k -= ways;
                count[i]++;
            }

            if (!found) {
                return "";
            }

            remaining--;
        }

        StringBuilder result = new StringBuilder(left);

        if (middle != -1) {
            result.append((char) ('a' + middle));
        }

        result.append(left.reverse());

        return result.toString();
    }

    private long countPermutations(int[] count, int limit) {
        long result = 1;
        int total = 0;

        for (int value : count) {
            for (int i = 1; i <= value; i++) {
                result = result * (total + i) / i;

                if (result >= limit) {
                    return limit;
                }
            }

            total += value;
        }

        return result;
    }
}