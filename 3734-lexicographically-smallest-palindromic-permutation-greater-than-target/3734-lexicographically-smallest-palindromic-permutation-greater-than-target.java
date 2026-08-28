class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) cnt[ch - 'a']++;
        int odd = 0;
        char mid = 'a';
        for (int c = 0; c < 26; c++) {
            if (cnt[c] % 2 == 1) {
                odd++;
                mid = (char) ('a' + c);
            }
        }
        if (odd > (n % 2)) return "";

        int m = n / 2;
        int[] half = new int[26];
        for (int c = 0; c < 26; c++) half[c] = cnt[c] / 2;
        String midS = (n % 2 == 1) ? String.valueOf(mid) : "";

        int[] cur = half.clone();
        int k = 0;
        while (k < m && cur[target.charAt(k) - 'a'] > 0) {
            cur[target.charAt(k) - 'a']--;
            k++;
        }

        if (k == m) {
            String h = target.substring(0, m);
            String p = h + midS + new StringBuilder(h).reverse();
            if (p.compareTo(target) > 0) return p;
        }

        int i = Math.min(k, m - 1);
        for (int j = k - 1; j >= i && j >= 0; j--) cur[target.charAt(j) - 'a']++;

        for (; i >= 0; i--) {
            for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                if (cur[c] > 0) {
                    StringBuilder h = new StringBuilder(target.substring(0, i));
                    h.append((char) ('a' + c));
                    cur[c]--;
                    for (int d = 0; d < 26; d++)
                        for (int t = 0; t < cur[d]; t++) h.append((char) ('a' + d));
                    String hs = h.toString();
                    return hs + midS + new StringBuilder(hs).reverse();
                }
            }
            if (i > 0) cur[target.charAt(i - 1) - 'a']++;
        }
        return "";
    }
}