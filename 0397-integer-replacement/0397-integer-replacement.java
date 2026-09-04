class Solution {
    public int integerReplacement(int n) {
        long value = n;
        int steps = 0;

        while (value != 1) {
            if ((value & 1) == 0) {
                value /= 2;
            } else if (value == 3 || (value & 3) == 1) {
                value--;
            } else {
                value++;
            }

            steps++;
        }

        return steps;
    }
}