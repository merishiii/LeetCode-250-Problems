class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] total = new int[26];
        for (char c : s.toCharArray()) total[c - 'a']++;

        int[] avail = total.clone();
        int bestPos = -1;
        for (int i = 0; i < n; i++) {
            int t = target.charAt(i) - 'a';
            for (int c = t + 1; c < 26; c++) {
                if (avail[c] > 0) {
                    bestPos = i;
                    break;
                }
            }
            if (avail[t] == 0) break;
            avail[t]--;
        }

        if (bestPos < 0) return "";

        int[] rem = total.clone();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bestPos; i++) {
            char ch = target.charAt(i);
            rem[ch - 'a']--;
            sb.append(ch);
        }
        int t = target.charAt(bestPos) - 'a';
        for (int c = t + 1; c < 26; c++) {
            if (rem[c] > 0) {
                rem[c]--;
                sb.append((char) ('a' + c));
                break;
            }
        }
        for (int c = 0; c < 26; c++) {
            for (int k = 0; k < rem[c]; k++) sb.append((char) ('a' + c));
        }
        return sb.toString();
    }
}