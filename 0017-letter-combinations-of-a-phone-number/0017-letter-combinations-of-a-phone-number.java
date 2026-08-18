import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.length() == 0) {
            return result;
        }

        String[] map = {
            "", "", "abc", "def", "ghi",
            "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        generate(digits, 0, new StringBuilder(), map, result);

        return result;
    }

    private void generate(String digits, int index, StringBuilder current, String[] map, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));
            generate(digits, index + 1, current, map, result);
            current.deleteCharAt(current.length() - 1);
        }
    }
}