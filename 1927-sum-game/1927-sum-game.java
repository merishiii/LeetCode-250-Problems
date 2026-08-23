class Solution {

    public boolean sumGame(String num) {
        int mid = num.length() / 2;

        int[] left = scan(num, 0, mid);                
        int[] right = scan(num, mid, num.length());

        int digitGap = left[0] - right[0];
        int blankGap = right[1] - left[1];

        return !bobCanBalance(digitGap, blankGap);
    }
    private int[] scan(String s, int from, int to) {
        int sum = 0, blanks = 0;
        for (int i = from; i < to; i++) {
            char c = s.charAt(i);
            if (c == '?') blanks++;
            else sum += c - '0';
        }
        return new int[]{sum, blanks};
    }

    private boolean bobCanBalance(int digitGap, int blankGap) {
        return 2 * digitGap == 9 * blankGap;
    }
}