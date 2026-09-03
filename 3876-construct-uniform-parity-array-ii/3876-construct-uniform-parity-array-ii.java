class Solution {
    public boolean uniformArray(int[] nums1) {
        long minOdd = Long.MAX_VALUE;
        boolean allEven = true;

        for (int num : nums1) {
            if ((num & 1) == 1) {
                allEven = false;
                minOdd = Math.min(minOdd, num);
            }
        }

        if (allEven) {
            return true;
        }

        for (int num : nums1) {
            if ((num & 1) == 0 && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}