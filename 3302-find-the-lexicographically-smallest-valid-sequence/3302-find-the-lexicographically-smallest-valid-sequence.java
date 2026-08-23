class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        
        int[] suffixMatch = new int[n + 1];
        suffixMatch[n] = m;
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                suffixMatch[i] = j;
                j--;
            } else {
                suffixMatch[i] = (i < n) ? suffixMatch[i + 1] : m;
            }
        }
        
        int[] right = new int[n + 1];
        right[n] = m;
        for (int i = n - 1; i >= 0; i--) {
            right[i] = right[i + 1];
            if (right[i] > 0 && word1.charAt(i) == word2.charAt(right[i] - 1)) {
                right[i]--;
            }
        }
        
        int[] result = new int[m];
        boolean usedMismatch = false;
        int wi = 0, wj = 0;
        
        for (; wj < m; ) {
            if (wi >= n) return new int[0];
            
            if (word1.charAt(wi) == word2.charAt(wj)) {
                result[wj] = wi;
                wj++;
                wi++;
            } else if (!usedMismatch) {
                
                if (wj + 1 >= m || right[wi + 1] <= wj + 1) {
                    result[wj] = wi;
                    usedMismatch = true;
                    wj++;
                    wi++;
                } else {
                    wi++;
                }
            } else {
                wi++;
            }
        }
        
        return result;
    }
}