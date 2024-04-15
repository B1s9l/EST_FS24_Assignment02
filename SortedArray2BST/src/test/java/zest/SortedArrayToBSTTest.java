package zest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
}