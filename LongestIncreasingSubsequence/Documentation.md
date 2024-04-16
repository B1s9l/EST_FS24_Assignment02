### Task 1: Code Coverage
**1. Understanding Requirements, Input and Output**

Clear from description.

**2. Exploring program**

Exploring Happy cases and if program behaves as it should in these cases.

**3. Identify Partitions**

a) Input
- Null Array
- Empty array
- Array with length 1
- Array with length > 1

b) Interactions

No Interactions as we have only one input.

c) Output

An Integer >= 0. Note special cases: When array is null or empty return 0. When Array has length one return 1.

**4. Boundaries**
- Off point: Empty array
- On-Point (and Off point): Array with length one
- On-Point: Array with length 2

**5. Devising and Reducing Test Cases**

I devise Test cases for the mentioned partitions. For Length Two, I devised two cases for descending and ascending order to check if the program behaves correctly no matter the order of the values.
The following cases were devised and documented like this: Ti: Input, expected return value
- T1: Empty Array, 0 
- T2: Null Array, 0
- T3: Array with Length One, 1 
- T4: Array with length two in ascending order, 2
- T5: Array with length two in descending order, 1

**6. Automating**

All 5 tests pass, so no bug found at this point. Check log.txt and assets folder for test report.

**7. Augment Test Suite**

As exceptional cases and on and off points are tested I just test some additional values.
Further, I test an array that contains only duplicate values and check if it works with negative and positive values.
So I add the following tests:
- T6: [0, 1, 5, 4, 2, 6, 0, 8], 5
- T7: [0, 0, 0, 0], 1
- T8: [12, 3, -10, -12, -2, 2, 1, 5], 4

All tests pass. Happy with the test suite so I move to structural testing.

**8. Structural Testing**

I reached 100% line coverage. See screenshots in asset folder

### Task 2: Designing Contracts

**Contract**

- Pre-conditions: non-null array. 
- Post-conditions: integer >= 0
- Invariants: none

I add Pre-Condition check at Line 6.
As we check null array in our pre-condition we can delete the statement "nums == null" at line 9. No bug per se, but unnecessary code as we handle that case with our pre-condition and thus know it won't affect the method's behavior in a wrong way if it is passed to it.

Added postcondition at line 28.

### Task 3: Test Contracts

Our Test suite already verifies that the contracts are correctly enforced. It validates normal operation (especially T4 - T8).
It confirms that exception is thrown when pre-condition is violated (with T2).
It ensures post-condition holds under various conditions (all tests).
See in asset folders the coverage. The only line not checked is the post-condition check.
However, shouldn't be a big deal and doesn't make sense to test,  because we would have to change the code.
Further, by returning the correct values with our test suite, we test the post-condition implicitly as it never is called. Therefore, we know it is not violated.

### Task 4: Property-based Testing

**Property**: 

Returns longest subsequence as integer

**Parameter**: 

integer array


The main property to test is, that the solution indeed returns an integer that corresponds to the longest increasing subsequence.
The only parameter we need is a random array with integer values. Therefore, I created an arbitrary that provides us with random arrays with integers between -100 and 100
as values. We max the size at 100, as we don't want to create arrays too big.
I then calculate the value of the longest increasing we would expect with an alternative method found on geeksforgeeks.org. I have to assume that the implementation of this alternative method
is correct. However, as it is a well known site, we can assume this to be true.
When running the tests, they all pass, thus reassuring us of the correctnes of the solution and confirming our previous tests.






