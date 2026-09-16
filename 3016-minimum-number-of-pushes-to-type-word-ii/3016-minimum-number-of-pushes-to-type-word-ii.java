class Solution {
    public int minimumPushes(String word) {
        int[] frequency = new int[26];

        for (int i = 0; i < word.length(); i++) {
            frequency[word.charAt(i) - 'a']++;
        }

        java.util.Arrays.sort(frequency);

        int pushes = 0;

        for (int i = 25; i >= 0; i--) {
            pushes += frequency[i] * ((25 - i) / 8 + 1);
        }

        return pushes;
    }
}