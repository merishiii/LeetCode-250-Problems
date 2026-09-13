class Solution {
    private byte[] memo;
    private int max;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) {
            return true;
        }

        int total = maxChoosableInteger * (maxChoosableInteger + 1) / 2;

        if (total < desiredTotal) {
            return false;
        }

        max = maxChoosableInteger;
        memo = new byte[1 << max];

        return canWin(0, desiredTotal);
    }

    private boolean canWin(int used, int remaining) {
        if (memo[used] != 0) {
            return memo[used] == 1;
        }

        for (int i = max - 1; i >= 0; i--) {
            int bit = 1 << i;

            if ((used & bit) == 0) {
                int value = i + 1;

                if (value >= remaining || !canWin(used | bit, remaining - value)) {
                    memo[used] = 1;
                    return true;
                }
            }
        }

        memo[used] = -1;
        return false;
    }
}