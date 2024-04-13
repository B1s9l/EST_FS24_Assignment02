# MergeKSortedLists

## Task 1

We achieve 100% code coverage with four tests: test_example_linked_list() tests with the example input given in the README, test_empty_linked_list(), test_empty_node() and test_null_node_in_normal_list() make sure empty ListNodes or empty lists are handled correctly. The missing 8% branch coverage comes from the fact that (node != null) will always evaluate to true in Java.

## Task 2

pre-condition: The values in the linked lists must be between -10^4 and 10^4. The linked lists can be empty. The input variable lists can contain up to 10000 elements (linked lists). The total number of values in variable lists must not exceed 10000.
post-condition: The program returns a single sorted linked list with all input values merged in it.
invariant: The return linked list saved at dummy is sorted at any time.

## Task 3

- test_list_with_too_many_linked_lists(): makes sure pre-condition that at most 10^4 linked lists are passed as input.
- test_list_with_too_many_values(): makes sure pre-condition that at most 10^4 values are passed as input.
- test_node_value_outOfBounds(): invalid values ( larger than 10^4 or smaller than -10^4) makes program throw exception.
- test_values_are_sorted(): negative values are also sorted in ascending order in the return linked list.
- test_sorted_with_many_duplicates(): makes sure sortedness is upheld when there are many duplicates in different linked lists.

## Task 4

mergeKLists() takes a list of ListNode as input, each ListNode object represents a linked list.
In total, the number of ListNode objects passed to mergeKLists() mustn't exceed 10000.

Given valid input, the property that the function returns a single sorted linked list should be fulfilled.

To explore all possible combinations, the input space is divided into 3 subspaces:
- input list has upto 10 linked lists each with upto 1000 elements
- input list has upto 100 linked lists each with upto 100 elements
- input list has upto 1000 linked lists each with upto 10 elements
- In all cases, the .val attribute should hold value in the range of [-10^4, 10^4].
One @Property test case is added to test suite for each input subspace.

Two @Provider functions are defined to support testing and generate random linked lists of provided length.

The property_sortedness_10lists() asks jqwik to generate random input sizes, with size being the number of linked list and length is the number of element in one linked list.
Given these two random inputs, it calls the two @Provider functions to generate a list of sorted linked lists.
It passes the list to call mergeKLists(). To compare the result, it iterates through every node in the list of linked lists, and add each node.val to a list of integers.
The list of integers is sorted and compared with each node of the return linked list from mergeKLists().
The sortedness relies on the java built-in Collections.sort() method. 
To make sure the length of List<Integer> and the return linked list are indeed same, assert statements are added after element-wise comparison.
This test passes.

The other two @Property sortedness tests have identical code, just different input range. Both tests pass.

To assure input with more than 10000 nodes in total throws an exception, another @Property test is added to test suite.
This test uses the two @Providers to generate a list of linked lists that contain more than 10000 nodes and checks if calling mergeKLists() with this list as input does indeed throw an exception.
The @Property test receives a relatively small set of input that should cause an exception, because unbounded input range will significantly increase the running time to perform the testing.
Even with a maximal input size of 40000 nodes, the test case passes with a running time of over 7 minutes.
To balance the tradeoff of testing input size and performance, [10001, 40000] is chosen to represent the invalid input range.