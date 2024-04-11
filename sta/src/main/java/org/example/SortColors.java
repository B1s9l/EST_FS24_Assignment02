package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SortColors {
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        int temp;

        while (mid <= high) {
            switch (nums[mid]) {
                case 0: {
                    // Swap nums[low] and nums[mid]
                    temp = nums[low];
                    nums[low] = nums[mid];
                    nums[mid] = temp;
                    low++;
                    mid++;
                    break;
                }
                case 1:
                    mid++;
                    break;
                case 2: {
                    // Swap nums[mid] and nums[high]
                    temp = nums[mid];
                    nums[mid] = nums[high];
                    nums[high] = temp;
                    high--;
                    break;
                }
            }
        }
    }}