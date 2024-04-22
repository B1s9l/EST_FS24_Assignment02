package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SortedArrayToBSTTest {
    private SortedArrayToBST instance;
    @BeforeEach
    public void setUp(){
        instance = new SortedArrayToBST();
    }
    @Test
    public void testWithEmptyArray(){
        int[] nums = new int[0];
        Assertions.assertArrayEquals(nums, instance.levelOrderTraversal(instance.sortedArrayToBST(nums)).stream().mapToInt(x->x).toArray());
    }
    @Test
    public void testSimpleIncompleteArray(){
        int[] nums = new int[]{1,2,3,4,5};
        Assertions.assertArrayEquals(new int[]{3,1,4,2,5}, instance.levelOrderTraversal(instance.sortedArrayToBST(nums)).stream().mapToInt(x->x).toArray());
    }

    @Test
    public void testSimpleAlmostCompleteArray(){
        int[] nums = new int[]{1,2,3,4,5, 6};
        Assertions.assertArrayEquals(new int[]{3,1,5,2,4,6}, instance.levelOrderTraversal(instance.sortedArrayToBST(nums)).stream().mapToInt(x->x).toArray());
    }
    @Test
    public void testCompleteArray(){
        int[] nums = new int[]{1,2,3,4,5, 6, 7};
        Assertions.assertArrayEquals(new int[]{4,2,6,1,3,5,7}, instance.levelOrderTraversal(instance.sortedArrayToBST(nums)).stream().mapToInt(x->x).toArray());
    }
    @Test
    public void testArrayNull(){
        int[] nums = null;
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            instance.sortedArrayToBST(nums);
        });
    }
    @Test
    public void testArrayToLong(){
        int[] nums = new int[10001];
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            instance.sortedArrayToBST(nums);
        });
    }
    @Test
    public void testcontainsDuplicates(){
        int[] nums = new int[]{1,1,2};
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            instance.sortedArrayToBST(nums);
        });
    }
    @Test
    public void testnotSorted(){
        int[] nums = new int[]{2,1,3};
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            instance.sortedArrayToBST(nums);
        });
    }
    @Property
    public void testArbitraryIntArraysOfLength0To10000(
            @ForAll("uniqueSortedIntArray") int[] input
    ){
        instance = new SortedArrayToBST();
        TreeNode actual = instance.sortedArrayToBST(input);
        // 1: must be bst
        assertThat(isBinarySearchTree(actual)).isTrue();
        // 2: must be height balanced
        assertThat(isHeightBalanced(actual)).isTrue();
        // 3: inOrderTraversal must result in sorted Array
        assertThat(inorderTraversalProducesSortedArray(actual)).isTrue();
        // 4: cannot contain duplicate elements
        assertThat(areElementsUnique(actual)).isTrue();
        // 5: amount of elements must match original input elements
        assertThat(countElements(actual)).isEqualTo(input.length);
    }
    @Provide
    Arbitrary<int[]> uniqueSortedIntArray() {
        return Arbitraries.integers().between(0, 100000)
                .list().ofMinSize(0).ofMaxSize(10000).uniqueElements().map(list -> {
                    int[] array = list.stream().mapToInt(Integer::intValue).toArray();
                    Arrays.sort(array);
                    return array;
                });
    }

    private boolean isBinarySearchTree(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode node, int minValue, int maxValue) {
        if (node == null) {
            return true;
        }

        if (node.val <= minValue || node.val >= maxValue) {
            return false;
        }

        return isBST(node.left, minValue, node.val) &&
                isBST(node.right, node.val, maxValue);
    }

    private boolean isHeightBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private boolean inorderTraversalProducesSortedArray(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return isSorted(result);
    }

    private void inorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, result);
        result.add(node.val);
        inorderTraversal(node.right, result);
    }

    private boolean isSorted(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean areElementsUnique(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        return checkUnique(root, set);
    }

    private boolean checkUnique(TreeNode node, Set<Integer> set) {
        if (node == null) {
            return true;
        }
        if (set.contains(node.val)) {
            return false;
        }
        set.add(node.val);
        return checkUnique(node.left, set) && checkUnique(node.right, set);
    }
    private int countElements(TreeNode r){
        if(r == null)return 0;
        return countElements(r.left) + countElements(r.right) + 1;
    }
}