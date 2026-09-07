class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        findPaths(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void findPaths(TreeNode node, int remaining, List<Integer> path, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        path.add(node.val);

        if (node.left == null && node.right == null && remaining == node.val) {
            result.add(new ArrayList<>(path));
        } else {
            findPaths(node.left, remaining - node.val, path, result);
            findPaths(node.right, remaining - node.val, path, result);
        }

        path.remove(path.size() - 1);
    }
}