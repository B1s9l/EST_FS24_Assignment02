## UniquePathsGrid
### 1. Code Coverage
#### 1. Understanding Requirements
From the task descriptions the requirements are clear.
#### 2. Exploring the program
Given two arbitrary integers both in range [1,100], the program should output the correct number of unique paths within this grid.
Everything seems to work fine except for large numbers I do not get the right answer.
#### 3. Identify partitions
a) Input with Integers which are negative
function returns weird results, maybe boundary conditions should be introduced.
b) Input with Integers both exceeding boundary
result is sometimes positive and sometimes negative, should maybe be validated also.
c) Output:
Should always be a result greater than 0, which is not the case.
#### 4. Boundaries
if input is negative, should notify user that he is not allowed to do that.
Numbers exceeding 100, should notify user that he is not allowed to do that.
Both numbers within range [1,100], should output non-negative correct number of unique paths.
#### 5. Devising test cases
Should atleast devise one test case which verifies that if both numbers are within the range of the specified input, should output a correct result.
Since this function does not contain any branches, 2 or 3 cases which verify this might be beneficial since refactoring could introduce an if statement possibly catching this one test case.
Not necessarily needed. 1 input where n is smaller than one and one where n is larger, should return an error.
#### 6. Automating
Manually compute the expected result and compare to the program output.
#### 7. Augment Test Suite

To cover boundary cases and adhere to precondition, more cases are identified. See section Task 3 for more detail on the augmented test suite.

#### 8. Structural Testing

100% line coverage and branch coverage reached (see screenshot in asset folder)

Only 1 test would suffice to achieve 100% coverage, I added 3 more to also cover future changes which might catch the one test if only one was present. Can be seen as redundant.

### 2. Designing Contracts
As a precondition, as specified in the readme, both m and n have to be within the range [1,100], this can easily be verified with a simple if statement.

As a postcondition, as stated by the readme, it has to return a number within the range [1,INT_MAX], since having a negative amount of paths does conceptually not make sense. Additionally, since the exercise makes us of the data-type Integer, the largest number which makes sense as a result is INT_MAX, because if it exceeds this number, the result will be wrong due to int overflow, possibly also resulting in a negative number or a positive wrong result, in my example I check this within the invariant, because in the last iteration the invariant is checked aswell and verifies that the result is <= INT_Max.

As an invariant, one can check at the beginning of every iteration, whether the addition of the two numbers results in a number which is less than or equal to INT_MAX, and if it exceeds this number, one could throw an exception indicating that the result cannot be calculated with this function. Changing the data type to long will not fix the problem, because the overflow just starts later. If one does really want to be able to input two numbers which are 100, one could consider changing the type to BigInteger, which will always yield the correct result, but using the BigInteger class results in a huge Performance loss.

### 3. Testing Contracts

testmToSmallInput: verifies that the function throws an exception if m is < 1

testnToSmallInput: verifies that the function throws an exception if n is < 1

testmToBigInput: verifies that the function throws an exception if m is > 100

testnToBigInput: verifies that the function throws an exception if n is > 100

testOverallInputOverflows: verifies that the function throws an exception as soon as the calculated result would be > INT_MAX

### 4. Property-Based Testing
Property 1: returns correct number of unique paths if it does not overflow. Note how i dynamically calculate n based on the value of m, since there is no way to guarantee no overflow otherwise. Since i only provide a range from 1-100, i added a placeholder variable k in order to generate 1000 samples, rather than just 100.

Property 2: if either m or n is not in the range [1,100], an exception is thrown. This is tested by the function preConditionNotMetThrowsException, and it will test all 4 parts of the if equally since i designed a smart property.

Property 3: if the addition of the two partial results of the cells results in a number greater than INT_MAX, an exception is thrown. I had to figure out the boundaries where exceptions are thrown, then i just generate a random number for m, and then decide on the smallest possible n value for which the result overflows. Note that here jqwik only generates 83 tests, which is fine in my opinion since all bigger values for n would result in an overflow aswell

