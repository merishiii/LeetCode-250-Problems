class Solution {
    public int numberOfSets(int n, int k) {
        long mod = 1000000007L;
        int total = n + k - 1;
        long[] factorial = new long[total + 1];
        long[] inverseFactorial = new long[total + 1];

        factorial[0] = 1;

        for (int i = 1; i <= total; i++) {
            factorial[i] = factorial[i - 1] * i % mod;
        }

        inverseFactorial[total] = power(factorial[total], mod - 2, mod);

        for (int i = total; i > 0; i--) {
            inverseFactorial[i - 1] = inverseFactorial[i] * i % mod;
        }

        return (int) (factorial[total] * inverseFactorial[2 * k] % mod
                * inverseFactorial[total - 2 * k] % mod);
    }

    private long power(long base, long exponent, long mod) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % mod;
            }

            base = base * base % mod;
            exponent >>= 1;
        }

        return result;
    }
}