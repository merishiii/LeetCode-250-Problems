class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            String mappedWord = charToWord.get(c);
            Character mappedChar = wordToChar.get(word);

            if (mappedWord == null && mappedChar == null) {
                charToWord.put(c, word);
                wordToChar.put(word, c);
            } else if (!word.equals(mappedWord) || mappedChar == null || mappedChar != c) {
                return false;
            }
        }

        return true;
    }
}