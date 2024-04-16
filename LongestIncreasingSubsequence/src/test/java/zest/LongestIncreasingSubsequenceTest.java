//Test
package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;





class LongestIncreasingSubsequenceTest {

    LongestIncreasingSubsequence longestIncreasingSubsequence;

    @BeforeEach
    public void init(){
        longestIncreasingSubsequence = new LongestIncreasingSubsequence();
    }

    //T1
    @Test
    public void emptyArray() {
        int[] emptyArray = new int[0];
        assertEquals(0, longestIncreasingSubsequence.lengthOfLIS(emptyArray), "Empty Array test failed");

    }

    //T2
    @Test
    void nullArray() {
        int[] nullArray = null;
        assertThrows(NullPointerException.class, () -> {
            longestIncreasingSubsequence.lengthOfLIS(nullArray);
        });
    }

    //T3
    @Test
    void lengthOneArray() {
        int[] arrayLengthOne = {1};
        assertEquals(1, longestIncreasingSubsequence.lengthOfLIS(arrayLengthOne), "Array Length One test failed");
    }

    //T4
    @Test
    void lengthTwoArrayAscending() {
        int[] arrayLengthTwoAscend = new int[]{0, 1};
        assertEquals(2, longestIncreasingSubsequence.lengthOfLIS(arrayLengthTwoAscend), "Array Length Two Test Ascending Order failed");
    }

    //T5
    @Test
    void lengthTwoArrayDescending() {
        int[] arrayLengthTwoDescend = new int[]{1, 0};
        assertEquals(1, longestIncreasingSubsequence.lengthOfLIS(arrayLengthTwoDescend), "Array Length Two Test Descending Order failed");
    }

    //T6
    @Test
    void additionalValue() {
        int[] arrayAdditionalValue = new int[]{0, 1, 5, 4, 2, 6, 0, 8};
        assertEquals(5, longestIncreasingSubsequence.lengthOfLIS(arrayAdditionalValue), "Additional Value Test failed");
    }

    //T7
    @Test
    void onlyDuplicates() {
        int[] arrayOnlyDuplicates = new int[]{0, 0, 0, 0};
        assertEquals(1, longestIncreasingSubsequence.lengthOfLIS(arrayOnlyDuplicates), "Only Duplicates Test failed");
    }

    //T8
    @Test
    void negativeAndPositive() {
        int[] arrayNegativeAndPositive = new int[]{12, 3, -10, -12, -2, 2, 1, 5};
        assertEquals(4, longestIncreasingSubsequence.lengthOfLIS(arrayNegativeAndPositive), "Array Length Negative and Positive failed");
    }



    @Report(Reporting.GENERATED)
    @Property
    public void propertyTest(@ForAll("listToCheck") int[] nums){
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int solutionToCheck = longestIncreasingSubsequence.lengthOfLIS(nums);
        int alternativeMethodSolution = lis(nums, nums.length);
        assertEquals(alternativeMethodSolution, solutionToCheck);

    }

    @Provide
    Arbitrary<int[]> listToCheck() {
        return Arbitraries.integers().between(-100,100).array(int[].class).ofMinSize(0).ofMaxSize(100);
    }

    // Alternatve Solution from geeksforgeeks.org:
    static int lis(int arr[], int n)
    {
        int lis[] = new int[n];
        int i, j, max = 0;

        /* Initialize LIS values for all indexes */
        for (i = 0; i < n; i++)
            lis[i] = 1;

        /* Compute optimized LIS values in bottom up manner */
        for (i = 1; i < n; i++)
            for (j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

        /* Pick maximum of all LIS values */
        for (i = 0; i < n; i++)
            if (max < lis[i])
                max = lis[i];

        return max;
    }
}







