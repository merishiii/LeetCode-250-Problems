import java.util.*;

class Solution {
    private int index = 0;
    private HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return create(preorder, 0, inorder.length - 1);
    }

    private TreeNode create(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        int value = preorder[index++];
        TreeNode node = new TreeNode(value);

        int mid = map.get(value);

        node.left = create(preorder, left, mid - 1);
        node.right = create(preorder, mid + 1, right);

        return node;
    }
}