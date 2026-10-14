class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if ((long) num * 9 < sum) {
            return "";
        }

        char[] result = new char[num];

        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            result[i] = (char) ('0' + digit);
            sum -= digit;
        }

        return new String(result);
    }
}