class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }

        return left.val == right.val
                && isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
    }
}