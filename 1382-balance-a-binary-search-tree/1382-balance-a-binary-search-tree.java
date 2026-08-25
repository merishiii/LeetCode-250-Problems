class Solution {
    private final List<Integer> values = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        inorder(root);
        return build(0, values.size() - 1);
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        values.add(node.val);
        inorder(node.right);
    }

    private TreeNode build(int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(values.get(mid));
        node.left = build(lo, mid - 1);
        node.right = build(mid + 1, hi);
        return node;
    }
}