class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        java.util.Arrays.fill(first, n);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<String> result = new ArrayList<>();
        int previousEnd = -1;

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (i != first[c]) {
                continue;
            }

            int end = expand(s, i, first, last);

            if (end == -1) {
                continue;
            }

            if (i > previousEnd) {
                result.add(s.substring(i, end + 1));
                previousEnd = end;
            } else {
                result.set(result.size() - 1, s.substring(i, end + 1));
                previousEnd = end;
            }
        }

        return result;
    }

    private int expand(String s, int start, int[] first, int[] last) {
        int end = last[s.charAt(start) - 'a'];

        for (int i = start; i <= end; i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] < start) {
                return -1;
            }

            end = Math.max(end, last[c]);
        }

        return end;
    }
}