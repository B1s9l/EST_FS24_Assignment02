# Climbing Stairs

# Task 1: Code Coverage

## 1. Understanding Requirements, Input and Output

Clear from description.

## 2. Exploring program

The key of the program is that it remembers the number of possible ways to climb up n-1 and n-2 stairs. To climb up n stairs, you can take one step and climb up n-1 stairs or take two steps and climbs up n-2 stairs.

## 3. Identify Partitions

a) Input

n = 0

n > 0

b) Interactions

The number of ways to climb up all the stairs is computed from 1 to n.

c) Output

The number of ways to climb up n stairs, must be a non-negative integer.

## 4. Boundaries

Input must be a positive integer, meaning 0 is not allowed and the smallest valid input value is 1.

## 5. Devising and Reducing Test Cases

test_one(): Test with input = 1 - hits the edge case

test_three(): Tests the general case

## 6. Automating

Tests result in same output value as expected.

## 7. Augment Test Suite

All tests passed.

## 8. Structural Testing

100% line coverage reached (see screenshot in asset folder).

We achieve 100% code coverage with two tests. The only branching in the program is at line 5. To enter the for-loop, input should be greater than 2, thus we pick input = 3. Both tests passed.

# Task 2

pre-condition: the input must be a positive integer

post-condition: the return value is non-negative

invariant: The variable twoStepsBefore should have a strictly smaller value than oneStepBefore, having more steps to go means more possibilities.

# Task 3

test_two_pre() and test_four_pre() both pass as they are accepted values according to precondition. However, test_negative_pre() and test_zero_pre() also pass because exceptions are thrown when input is invalid. Since the input is always positive and program only uses addition, the return value will never be negative no matter what input is passed. It's impossible to constuct tests that violate the post-condition and invariant.

# Task 4

For property testing, the input space is divided in three parts: non-positive integers, 1 or 2 and integers greater than 3.

The first input space should throw an IllegalArgumentException. This property test passes.

The second input space should return input with input = 1 or 2. This property test passes.

The third input space fulfills the property that for any input n, the result equals the sum of ways to take n-1 steps plus ways to take n-2 steps, since one can either take one or two steps.

A dynamic programming approach is used to simulate this property to increase testing performance. A provider method is implemented for this purpose.
This property test causes an invariant to be broken: at i=91, the long variable overflows and gets interpreted as a negative long. This property test fails. To make the property test pass, the input value range maximum is set to 91. Now this test passes.

The example-based tests test_91_pre() and test_92_pre() are added to show that 91 is the closest input that generates the correct output within the long value range.
Therefore, the correct precondition should be: integer in the range of [0,91].
