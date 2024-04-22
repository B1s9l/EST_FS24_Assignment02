package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class FindDuplicateTest {
    private FindDuplicate instance;;

    @BeforeEach
    public void init(){
        instance = new FindDuplicate();

    }

   //TEST 1
   @Test
   public void testSingleDuplicates() {
        assertEquals(1, instance.findDuplicate(new int[]{1, 1, 2}));
   }

    //TEST 2
    @Test
    public void testOddDuplicates() {
        assertEquals(1, instance.findDuplicate(new int[]{1, 1, 1, 3, 3}));
    }

    //TEST 3
    @Test
    public void testEvenDuplicates() {
        assertEquals(1, instance.findDuplicate(new int[]{1, 1, 1, 1, 2, 3}));
    }

    //TEST 4
    @Test
    public void testDuplicatesStart() {
        assertEquals(1, instance.findDuplicate(new int[]{1, 1, 2, 3, 4, 5}));
    }

    //TEST 5
    @Test
    public void testDuplicatesEnd() {
        assertEquals(5, instance.findDuplicate(new int[]{1, 2, 3, 4, 5, 5}));
    }

    //TEST 6
    @Test
    public void testDuplicatesMiddle() {
        assertEquals(3, instance.findDuplicate(new int[]{1, 2, 3, 3, 4, 5}));
    }

    //TEST 7
    @Test
    public void testDuplicatesSpread() {
        assertEquals(1, instance.findDuplicate(new int[]{1, 2, 3, 4, 1, 5}));
    }

    //TEST 8
    @Test
    public void testRandomOrder() {
        assertEquals(3, instance.findDuplicate(new int[]{5, 3, 2, 3, 4, 1}));
    }

    //TEST 9
    @Test
    public void testSortedOrder() {
        assertEquals(3, instance.findDuplicate(new int[]{1, 2, 3, 3, 4, 5}));
    }

    //TEST 10
    @Test
    public void testMinimumArraySize() {
        assertEquals(1, instance.findDuplicate(new int[]{1, 1}));
    }

    //TEST 11
    @Test
    public void testAllDuplicates() {
        assertEquals(1, instance.findDuplicate(new int[]{1, 1, 1, 1, 1}));
    }

    //TEST 12
    @Test
    public void testLargeArray() {
        int[] largeArray = new int[10001];

        for (int i = 0; i < 10000; i++) {
            largeArray[i] = i + 1;
        }

        largeArray[10000] = 5000;
        assertEquals(5000, instance.findDuplicate(largeArray));
    }

    // TASK 3 TESTS

    //TEST 13
    @Test
    public void testArraySize() {
        AssertionError error = assertThrows(AssertionError.class, () -> {
            instance.findDuplicate(new int[]{1});
        });
        assertTrue(error.getMessage().contains("length can not be less than 2 elements"));
    }

    //TEST 14
    @Test
    public void testNotNull(){
        AssertionError error = assertThrows(AssertionError.class, () -> {
            instance.findDuplicate(null);
        });
        assertTrue(error.getMessage().contains("can not be null"));
    }
    //TEST 15
    @Test
    public void testValidInput() {
        assertEquals(2, instance.findDuplicate(new int[]{1, 2, 2, 3, 4}));
    }

    //TEST 16
    @Test
    public void testArrayModification() {
        int[] original = new int[]{1, 3, 4, 2, 2};
        instance.findDuplicate(original);
        assertArrayEquals(new int[]{1, 3, 4, 2, 2}, original, "not altered");
    }

    // PROPERTY TESTS
    @Property
    public void testArbitraryIntArraysOfLength0To10000(
            @ForAll("uniqueIntArray") int[] input,
            @ForAll @IntRange(max = 1000) int val

    ){
        instance = new FindDuplicate();
        int randomNum = (int) ((Math.random() * (input.length-1)));
        input[(randomNum + 1) % input.length] =  input[randomNum];
        assertThat(instance.findDuplicate(input)).isEqualTo(input[randomNum]);

    }

    @Provide
    public Arbitrary<int[]> uniqueIntArray() {
        return Arbitraries.create(() -> {
            int n = (int) ((Math.random() * 10000));
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i < n-1; i++) {
                numbers.add(i);
            }
            int[] array = numbers.stream().mapToInt(x -> x).toArray();
            return array;
        });
    }


}
