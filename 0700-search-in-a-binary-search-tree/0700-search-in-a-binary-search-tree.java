class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode current = root;
        while (current != null && current.val != val) {
            current = val < current.val ? current.left : current.right;
        }
        return current;
    }
}