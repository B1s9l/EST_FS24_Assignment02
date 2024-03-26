package zest;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return constructBSTRecursive(nums, 0, nums.length - 1);
    }

    private TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // Middle element to maintain balance
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        // Recursively construct the left and right subtrees
        node.left = constructBSTRecursive(nums, left, mid - 1);
        node.right = constructBSTRecursive(nums, mid + 1, right);

        return node;
    }
}
