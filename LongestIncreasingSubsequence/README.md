# Problem: Longest Increasing Subsequence 

## Description

Given an unsorted array of integers nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. 


### Constraints:

The input is an array of integers, possibly containing duplicates.

The input array, nums, is not null.

Each element in the input array is an integer that can be positive, negative, or zero.

The method returns a non-negative integer representing the length of the longest increasing subsequence.


### Example 1:

**Input**: `nums = [10, 9, 2, 5, 3, 7, 101, 18]`
**Output**: `4`

Explanation: The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.

### Example 2:

**Input**: `nums = [0, 1, 0, 3, 2, 3]`
**Output**: `4`

Explanation: The longest increasing subsequence is [0, 1, 2, 3], therefore the length is 4.


### Example 3:

**Input**: `nums = [7, 7, 7, 7, 7, 7, 7]`
**Output**: `1`

Explanation: The longest increasing subsequence is any single value, therefore the length is 1.
