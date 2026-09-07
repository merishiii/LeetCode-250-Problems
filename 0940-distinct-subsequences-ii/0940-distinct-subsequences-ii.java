class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1000000007;
        long total = 1;
        long[] last = new long[26];

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            long next = (total * 2 - last[index] + mod) % mod;
            last[index] = total;
            total = next;
        }

        return (int) ((total - 1 + mod) % mod);
    }
}