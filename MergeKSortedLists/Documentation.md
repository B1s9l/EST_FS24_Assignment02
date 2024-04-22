# MergeKSortedLists

# Task 1: Code Coverage

## 1. Understanding Requirements, Input and Output

Everything is straight forward in the task description, except one statement: "k, the number of linked lists, is a positive integer and is practically constrained by the input size limit." We assume that the number of nodes in all k linked lists combined cannot exceed 10000.

## 2. Exploring program

A ListNode is defined to hold a value and a next ListNode object.

mergeKLists() takes a list of sorted linked list, add the head node of each linked list to a queue and picks the smallest element out of the queue to append it to the end of result linked list. The head of the result linked list is returned.

## 3. Identify Partitions

a) Input

Empty list

List with empty ListNode

List with one ListNode

List with several ListNode

b) Interactions

All the ListNodes passed to the method are expanded and combined together to a single linked list.

c) Output

The output is the head ListNode of a linked list.

## 4. Boundaries

Assume the input list parameter is called l:

l == null: null input\
l.length == 0: empty list\
l.length == 1, l[0] is empty: one empty linked list\
l.length > 1, l[0], l[1] ... are empty: several empty linked lists\
l.length > 1, l[0]. l[1], ... not empty: several non-empty linked lists\
l.length > 1, l[0], l[1], ... empty or not empty: list of linked lists that are empty or non-empty\
l.length > 1, l contains duplicates: list with duplicates
For all ListNodes in l[0], ... the value must be in range [-10000, 10000]: ListNode.value in valid range

## 5. Devising and Reducing Test Cases

Devise one test case for null input, empty input list, input list with one empty linked list, input list with one linked list, the example input in task description and input list of non-empty ListNodes with one empty ListNode mixed in.

test_example_linked_list() tests with the example input given in the README, test_empty_linked_list(), test_empty_node() and test_empty_node_in_normal_list() make sure empty ListNodes or empty lists are handled correctly. To enhence code robustness at boundary, an additional test case test_one() is added to make sure one single input linked list works as well.

## 6. Automating

Manually compute the expected result linked list and compare to the program output.

## 7. Augment Test Suite

To cover boundary cases and adhere to precondition, more cases are identified. See section Task 3 for more detail on the augmented test suite.

## 8. Structural Testing

100% line coverage reached (see screenshot in asset folder)

We achieve 100% code coverage with the first four tests. The missing 8% branch coverage comes from the fact that (node != null) will always evaluate to true in Java.

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

The correctness of output should be tested on cases where input linked lists have variable lengths, to test this property, the test case property_variable_size_lists() is devised.

To explore all possible combinations of number of linked lists and size of linked lists, the input space is divided into 3 subspaces:

- input list has upto 10 linked lists each with upto 1000 elements
- input list has upto 100 linked lists each with upto 100 elements
- input list has upto 1000 linked lists each with upto 10 elements
- In all cases, the .val attribute should hold value in the range of [-10^4, 10^4].

One @Property test case is added to test suite for each input subspace.

Two @Provider functions are defined to support testing and generate random linked lists of provided length.

The property_sortedness_10lists() asks jqwik to generate random input sizes, with size being the number of linked list and length is the number of element in one linked list.\
Given these two random inputs, it calls the two @Provider functions to generate a list of sorted linked lists.
It passes the list to call mergeKLists().\
To compare the result, it iterates through every node in the list of linked lists, and add each node.val to a list of integers.\
The list of integers is sorted and compared with each node of the return linked list from mergeKLists().
The sortedness relies on the java built-in Collections.sort() method.\
To make sure the length of List<Integer> and the return linked list are indeed same, assert statements are added after element-wise comparison. This test passes.

The other two @Property sortedness tests have identical code, just different input range. Both tests pass.

To assure input with more than 10000 nodes in total throws an exception, another @Property test is added to test suite.
This test uses the two @Providers to generate a list of linked lists that contain more than 10000 nodes and checks if calling mergeKLists() with this list as input does indeed throw an exception.
The @Property test receives a relatively small set of input that should cause an exception, because unbounded input range will significantly increase the running time to perform the testing.
Even with a maximal input size of 40000 nodes, the test case passes with a running time of over 6 minutes on the first time.
To balance the tradeoff of testing input size and performance, [10001, 40000] is chosen to represent the invalid input range.
