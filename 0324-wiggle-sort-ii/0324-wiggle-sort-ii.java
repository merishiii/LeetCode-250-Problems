class Solution {
    public void wiggleSort(int[] nums) {
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        int left = (nums.length - 1) / 2;
        int right = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                nums[i] = sorted[left--];
            } else {
                nums[i] = sorted[right--];
            }
        }
    }
}