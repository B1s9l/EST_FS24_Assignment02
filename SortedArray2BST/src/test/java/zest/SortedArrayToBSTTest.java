package zest;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.ListArbitrary;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        int[] expected = instance.levelOrderTraversal(instance.sortedArrayToBST(input)).stream().mapToInt(x->x).toArray();
        int mid = input.length % 2 == 0 ? input.length / 2 -1 : input.length / 2;
        for(int i = 0; i < input.length; i++){
            assertThat(expected[i]).isEqualTo(input[mid]);
    }}
    @Provide
    Arbitrary<int[]> uniqueSortedIntArray() {
        return Arbitraries.integers().between(0, 10000)
                .list().uniqueElements().map(list -> {
                    int[] array = list.stream().mapToInt(Integer::intValue).toArray();
                    Arrays.sort(array);
                    return array;
                });
    }
}