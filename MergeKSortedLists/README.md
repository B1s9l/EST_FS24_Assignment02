# Problem: Merge k Sorted Lists

## Description

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order. Merge all the linked-lists into one sorted linked-list and return it.

### Constraints:

The number of nodes in all lists combined is in the range [0, 10^4].

-10^4 <= Node.val <= 10^4

k, the number of linked lists, is a positive integer and is practically constrained by the input size limit.

The method returns a single sorted linked list that is the result of merging all k input sorted linked lists.

### Example 1:

**Input**: `lists = [[1,4,5],[1,3,4],[2,6]]`
**Output**: `[1,1,2,3,4,4,5,6]`

### Example 2:

**Input**: `lists = []`
**Output**: `[]`


### Example 3:

**Input**: `lists = [[]]`
**Output**: `[]`

