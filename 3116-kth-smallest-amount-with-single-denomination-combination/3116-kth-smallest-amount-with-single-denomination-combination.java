class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long left = 1, right = (long) k * Collections.min(Arrays.stream(coins).boxed().collect(Collectors.toList()));

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (count(coins, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(int[] coins, long val) {
        int n = coins.length;
        long result = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    lcm = lcm(lcm, coins[i]);
                    if (lcm > val) break;
                }
            }
            if (bits % 2 == 1) {
                result += val / lcm;
            } else {
                result -= val / lcm;
            }
        }

        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}