class Solution {
    public int minPatches(int[] nums, int n) {
        long coverage = 1;
        int index = 0;
        int patches = 0;

        while (coverage <= n) {
            if (index < nums.length && nums[index] <= coverage) {
                coverage += nums[index];
                index++;
            } else {
                coverage += coverage;
                patches++;
            }
        }

        return patches;
    }
}