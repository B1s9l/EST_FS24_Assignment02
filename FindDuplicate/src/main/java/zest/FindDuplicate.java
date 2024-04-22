package zest;

import java.util.Arrays;

public class FindDuplicate {
    public static int findDuplicate(int[] nums) {
        assert nums != null : "Array can not be null";
        assert nums.length >= 2 : "Array length can not be less than 2 elements";

        int initialLength = nums.length;
        int[] copy = nums.clone();

        int tortoise = nums[0];
        int hare = nums[0];
        // Phase 1: Finding the intersection point of the two runners.
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Phase 2: Finding the "entrance" to the cycle.
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        assert nums.length == initialLength : "Array length is unchanged";
        assert Arrays.equals(nums, copy) : "Array is not altered";

        return hare;
    }
}

