class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] result = new int[k];

        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] candidate = merge(maxSubsequence(nums1, i), maxSubsequence(nums2, k - i));
            if (compare(candidate, 0, result, 0) > 0) {
                result = candidate;
            }
        }

        return result;
    }

    private int[] maxSubsequence(int[] nums, int k) {
        int[] stack = new int[k];
        int top = -1;
        int drop = nums.length - k;

        for (int num : nums) {
            while (top >= 0 && stack[top] < num && drop > 0) {
                top--;
                drop--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                drop--;
            }
        }

        return stack;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int i = 0, j = 0, idx = 0;

        while (i < nums1.length && j < nums2.length) {
            if (compare(nums1, i, nums2, j) >= 0) {
                result[idx++] = nums1[i++];
            } else {
                result[idx++] = nums2[j++];
            }
        }

        while (i < nums1.length) result[idx++] = nums1[i++];
        while (j < nums2.length) result[idx++] = nums2[j++];

        return result;
    }

    private int compare(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] != nums2[j]) {
                return nums1[i] - nums2[j];
            }
            i++;
            j++;
        }
        return (nums1.length - i) - (nums2.length - j);
    }
}