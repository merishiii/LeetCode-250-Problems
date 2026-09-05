class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n < 3) {
            return n;
        }

        int power = 1;

        while (power <= n) {
            power <<= 1;
        }

        return power;
    }
}