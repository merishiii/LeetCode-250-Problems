class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] mapST = new int[128], mapTS = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (mapST[a] == 0 && mapTS[b] == 0) {
                mapST[a] = b;
                mapTS[b] = a;
            } else if (mapST[a] != b || mapTS[b] != a) {
                return false;
            }
        }
        return true;
    }
}