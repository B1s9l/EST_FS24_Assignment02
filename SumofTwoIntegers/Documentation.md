# Task 1: Code Coverage

## 1. Understanding Requirements, Input and Output
I understood from description.
## 2. Exploring program
I tried some lists of integers and tried to think about different cases.
## 3. Identify Partitions
- Positive Integers: Both inputs are positive.
- Negative Integers: Both inputs are negative.
- Mixed Signs: One input is positive and the other is negative.
- Zero Values: One or both inputs are zero.
- Boundary Values: Inputs are near the maximum or minimum value of the integer range.
## 4. Boundaries
Zero: Tests involving 0 since it's a unique additive identity.
Minimum Integer: Integer.MIN_VALUE.
Maximum Integer: Integer.MAX_VALUE.
## 5. Devising and Reducing Test Cases
- Testing if one or both values can be zero both includes the partition and boundary I found.
## 6. Automating
Built the test suite and implemented the tests. 
## 7. Augment Test Suite
I couldn't find any further tests for Task 1.
## 8. Structural Testing
100% line coverage reached (see screenshot in asset folder)

# Task 2: Designing Contracts

## Pre-conditions:
1. Both inputs must be integer numbers 

## Post-conditions:
1. The output must be an integer
2. The output must be the sum of the two inputs

## Invariants
1. The inputs can not be altered

# Task 3: Test Contracts

With the created tests, we validate normal operation when pre-conditions are met.
We confirm that the correct exceptions are thrown when they are violated.
We add some test to ensure that post-condition holds, by validating it under different conditions:
The complete test suite contains:
- TEST 1-6: confirm that under normal operational conditions, the function behaves as expected,
  returning correct values and not modifying the inputs.
- TEST 7-9: check the pre- and post conditions as well as invariants.

See coverage report in assets folder, the lines missing:
`        assert Integer.MIN_VALUE <= a && a <= Integer.MAX_VALUE : "a is out of range";
assert Integer.MIN_VALUE <= b && b <= Integer.MAX_VALUE : "b is out of range";`
and 
`assert Integer.MIN_VALUE <= res && res <= Integer.MAX_VALUE : "res is not an integer";`
Integers that are out of range will automatically be caught. We do not need to specifically test for those.
In a real world application we would use input validation so those numbers can't be input where an integer is expected.

Also `assert res == A + B : "sum is incorrect";` is missing from coverage, this is assured by test 1-6 anyways.

# Task 4: Property-based Testing

Properties:
Returns true if prerequisites have no cycle. Returns false if prerequisites have a cycle.
Parameter: integer array
We create two property tests for both properties. We provide the property test with prerequisites that are valid and contain no cycle and vice versa. We use Jgraph, a well known library for graph theory to check if the provided list has a cycle and filter out the arbitrary lists that have a cycle for T15 and the lists that have no cycle for T16. Like this, we can provide the tests the necessary params and know if the method under test should return true or false.
•	T15: For property 1: we create prerequisites that are represented as acyclic graph
•	T16: For property 2: we create prerequisites that are represented as cyclic graph
