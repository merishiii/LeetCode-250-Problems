class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) seen.add(num);
        int candidate = k;
        while (seen.contains(candidate)) candidate += k;
        return candidate;
    }
}