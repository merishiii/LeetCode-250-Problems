class Solution {
    public boolean checkDivisibility(int n) {
        int sum = 0, prod = 1;
        for (int x = n; x > 0; x /= 10) {
            int d = x % 10;
            sum += d;
            prod *= d;
        }
        return n % (sum + prod) == 0;
    }
}