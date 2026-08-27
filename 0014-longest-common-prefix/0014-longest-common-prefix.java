class Solution {
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            int limit = Math.min(prefix.length(), strs[i].length());
            while (j < limit && prefix.charAt(j) == strs[i].charAt(j)) j++;
            prefix = prefix.substring(0, j);
            if (prefix.isEmpty()) return "";
        }
        return prefix;
    }
}