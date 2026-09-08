class Solution {
    public int countCommas(int n) {
        int result = 0;

        if (n >= 1000) {
            result += n - 999;
        }

        if (n >= 1000000) {
            result += n - 999999;
        }

        if (n >= 1000000000) {
            result += n - 999999999;
        }

        return result;
    }
}