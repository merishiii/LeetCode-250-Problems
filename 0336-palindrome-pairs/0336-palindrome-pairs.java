class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> index = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            index.put(words[i], i);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int n = word.length();

            for (int cut = 0; cut <= n; cut++) {
                String left = word.substring(0, cut);
                String right = word.substring(cut);

                if (isPalindrome(left)) {
                    String candidate = new StringBuilder(right).reverse().toString();
                    Integer j = index.get(candidate);

                    if (j != null && j != i) {
                        result.add(Arrays.asList(j, i));
                    }
                }

                if (cut < n && isPalindrome(right)) {
                    String candidate = new StringBuilder(left).reverse().toString();
                    Integer j = index.get(candidate);

                    if (j != null && j != i) {
                        result.add(Arrays.asList(i, j));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}