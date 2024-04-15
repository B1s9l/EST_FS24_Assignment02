package zest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    zest.TreeNode left;
    zest.TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SortedArrayToBST {
    private boolean checkArraySorted(int[] arr){
        if(arr.length < 2) return true;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < arr[i -1])return false;
        }return true;
    }
    private boolean checkArrayUnique(int[] arr){
        if(arr.length < 2) return true;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] == arr[i -1])return false;
        }return true;
    }
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    private boolean isBSTConstructed(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val < min || root.val > max) {
            return false;
        }
        return isBST(root.left, min, root.val - 1) && isBST(root.right, root.val + 1, max);
    }
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
    public zest.TreeNode sortedArrayToBST(int[] nums) throws IllegalArgumentException {
        if(nums == null) throw new IllegalArgumentException("input array cannot be null");
        if(nums.length > 10000)throw new IllegalArgumentException("input array is to long to convert");
        if(!checkArrayUnique(nums))throw new IllegalArgumentException("integers within array must be unique");
        if(!checkArraySorted(nums))throw new IllegalArgumentException("input array must be sorted");
        TreeNode res =  constructBSTRecursive(nums, 0, nums.length - 1);
        if(!isBalanced(res))throw new IllegalArgumentException("resulting tree not balanced!!");
        if(countNodes(res) != nums.length)throw new IllegalArgumentException("Resulting binary search tree does not contain same amount of nodes as input array");
        return res;
    }

    private zest.TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        zest.TreeNode node = new zest.TreeNode(nums[mid]);
        if(!isBSTConstructed(node))throw new IllegalArgumentException("tree not actual bst anymore");
        node.left = constructBSTRecursive(nums, left, mid-1);
        node.right = constructBSTRecursive(nums, mid + 1, right);
        if (!isBSTConstructed(node)) {
            throw new IllegalStateException("Invariant violated: Tree is not balanced after subtree construction");
        }
        return node;
    }

    public List<Integer> levelOrderTraversal(zest.TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<zest.TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            zest.TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }
}
