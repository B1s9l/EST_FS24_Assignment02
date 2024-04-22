# Task 1: Code Coverage

## 1. Understanding Requirements, Input and Output
I understood from description.
## 2. Exploring program
I tried some lists of integers and tried to think about different cases.
## 3. Identify Partitions
a) Basic functionality:
- Single duplication: 1 numbers appears 2 times
- Odd occurrences: 1 numbers appears more than 2 times, an odd number of times
- Even occurrences: 1 number appears more than 2 times, an even number of times
b) Basic array configurations:
- Random order with duplicates
- Sorted order with duplicates
c) Special array configurations:
- Number is either at the very start or very end
- Even array with duplicate numbers in the middle
d) Minimum and Maximum array sizes:
- The array has length 2
- Every element is duplicate
## 4. Boundaries
a) Given by the README:
- Array size n+1: Array length must be >= 2
- Integer in [1,n]: Array contains numbers >= 1
b) Not explicitely given by README:
- Integer at boundary: 1 and 1 or n and n
- Multiple duplicates of the same number: 1,1,1 or similar
## 5. Devising and Reducing Test Cases
- TEST 1: SINGLE DUPLICATES: [1,1,...]
- TEST 2 & 3: TESTING MULTIPLE DUPLICATES: [1,1,1,...]
- TEST 4 - 7: TESTING DIFFERENT LOCATION OF DUPLICATES IN ARRAY: [1,1,..], [...1,1], [..,1,1,...], [1,..,1,..]
- TEST 8 & 9: TESTING RANDOM/SORTED ORDER: [1,2,3,..], [2,1,3,..]
- TEST 10 - 12: TESTING DIFFERENT ARRAY SIZES: [1,1], [1,...10000], [1,1,1,1,1]
Partitions:
  a) Basic functionality:
- Single duplication: 1 numbers appears 2 times (TEST 1)
- Odd occurrences: 1 numbers appears more than 2 times, an odd number of times (TEST 2)
- Even occurrences: 1 number appears more than 2 times, an even number of times (TEST 3)
  b) Basic array configurations:
- Random order with duplicates (TEST 8)
- Sorted order with duplicates (TEST 9)
  c) Special array configurations:
- Number is either at the very start or very end (TEST 4 & 5)
- Even array with duplicate numbers in the middle (TEST 6)
  d) Minimum and Maximum array sizes:
- The array has length 2 (TEST 10)
- Every element is duplicate (TEST 11)
## 6. Automating
Built the test suite and implemented the tests.
## 7. Augment Test Suite
Contracts and pre-/post-conditions tested in T2 and T3
Added TEST 12: Large arrays
## 8. Structural Testing
100% line coverage reached (see screenshot in asset folder)

# Task 2: Designing Contracts

## Pre-conditions:
1. The array contains at least 2 elements
2. The array can not be null

## Post-conditions:
1. The return value must be an integer that appears at least twice in the input

## Invariants
1. The array is not altered (length and elements remain the same)

# Task 3: Test Contracts

With the created tests, we validate normal operation when pre-conditions are met. 
We confirm that the correct exceptions are thrown when they are violated. 
We add some test to ensure that post-condition holds, by validating it under different conditions: 
The complete test suite contains:
- TEST 1-12 and 15-16: confirm that under normal operational conditions, the function behaves as expected, 
returning correct values and not modifying the array.
- TEST 13-14: specifically check for violations of pre-conditions (null input and insufficient array length).
- TEST 16: checks the invariant that the array should not be altered.

See coverage report in assets folder, the only line missing regards the post-condition:
 `assert nums.length == initialLength : "Array length is unchanged";
assert Arrays.equals(nums, copy) : "Array is not altered";`
TEST 16: Covers those lines anyways and to get full coverage we would need to alter the original code,
in a way that breaks it.

# Task 4: Property-based Testing

Properties:
Returns true if prerequisites have no cycle. Returns false if prerequisites have a cycle.
Parameter: integer array
We create two property tests for both properties. We provide the property test with prerequisites that are valid and contain no cycle and vice versa. We use Jgraph, a well known library for graph theory to check if the provided list has a cycle and filter out the arbitrary lists that have a cycle for T15 and the lists that have no cycle for T16. Like this, we can provide the tests the necessary params and know if the method under test should return true or false.
	•	T15: For property 1: we create prerequisites that are represented as acyclic graph
	•	T16: For property 2: we create prerequisites that are represented as cyclic graph
