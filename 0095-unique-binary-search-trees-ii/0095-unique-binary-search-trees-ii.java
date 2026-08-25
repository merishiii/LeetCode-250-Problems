class Solution {
    public List<TreeNode> generateTrees(int n) {
        return build(1, n);
    }

    private List<TreeNode> build(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            trees.add(null);
            return trees;
        }
        for (int rootVal = start; rootVal <= end; rootVal++) {
            List<TreeNode> leftSubtrees = build(start, rootVal - 1);
            List<TreeNode> rightSubtrees = build(rootVal + 1, end);
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    trees.add(new TreeNode(rootVal, left, right));
                }
            }
        }
        return trees;
    }
}