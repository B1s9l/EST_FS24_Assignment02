# Documentation.md file for Assignment 2
## Climbing stairs (Paul)
## CourseSchedule (Max)
## Find Duplicate (Basil)
## LongestIncreasingSubsequence (Max)
## MergeKSortedLists (Paul)
## SortedArray2BST (Gianni)
### 1. Code Coverage
For this exercise, I achieve 100% branch and line coverage with two tests. The first one just tests a incomplete binary tree, could also be changed to a complete one(i added all of them but think they might be redundant) and a second test trying to convert an empty list.
### 2. Designing Contracts
As a precondition, as specified the readme, the valid length of the array is within [0,10000], if this does not hold, an exception is thrown. As an additional precondition we have that the input array itself cannot be null, which can be easily verified aswell. The last two preconditions which have to hold are that the input array cannot contain duplicates and it has to be sorted in ascending order.

As an invariant, we can specify that the value of every node to the left must be smaller and every value to the right of a node must be bigger than the node value itself, this should hold throught the entire execution and also at the end. Since I already explicitly evaluate the precondition that the input array is sorted in ascending order, the invariant will always hold since the array is reccursively split up in parts until the middle but not the middle and from the value after the middle, therefor the property of being a BST always holds and verifying this is quite redundant, but done anyway.

As a postcondition, we can specify that the number of nodes within the constructed BST is equal to the length of the input array, additionally it should also be a Binary search tree. Additionally, the resulting tree should differ in hight at most 1. The first postcondition that should always hold is defined within after the BST is constructed, and if its not met, there will be no result. For the third postcondition is redundant to verify aswell, but done anyway. The second post-condition is verified with the invariant.
### 3. Testing Contracts
testArrayNull: verifies that exception is thrown if the input array is null

testArrayToLong: verifies that exception is thrown if input array's length is not  within the range [0,10000]

testcontainsDuplicates: verifies that exception is thrown if the input array does contain duplicates

testnotSorted: verifies that exception is thrown if input array not sorted

As one might realise is that after adding the invariant and postconditions, i cannot achieve 100% branch coverage anymore, since i cannot violate the postconditions and invariants.
### 4. Property-Based Testing
Property 1: I could only identify this property since it can only return a TreeNode, which will always be correct verified by the invariant and pre- and postconditions. The property is that the result is of the correct form and looks as it is supposed to look.
## SumofTwoIntegers (Basil)
## UniquePathsGrid (Gianni)
### 1. Code Coverage
Since the code of uniquePaths does not contain any branches, achieving 100 % line and branch coverage can be done with just one test,
and it does not even matter which numbers one chooses since they are constrained to be >= 1 and <= 100 therefor the loops will always execute, immediately resulting in 100% code coverage. I added 4 tests for this function tho in order to verify that the function also works correctly for bigger numbers(which might be redundant).
### 2. Designing Contracts
As a precondition, as specified in the readme, both m and n have to be within the range [1,100], this can easily be verified with a simple if statement. 

As a postcondition, as stated by the readme, it has to return a number within the range [1,INT_MAX], since having a negative amount of paths does conceptually not make sense. Additionally, since the exercise makes us of the data-type Integer, the largest number which makes sense as a result is INT_MAX, because if it exceeds this number, the result will be wrong due to int overflow, possibly also resulting in a negative number or a positive wrong result, in my example I check this within the invariant, because in the last iteration the invariant is checked aswell and verifies that the result is <= INT_Max.

As an invariant, one can check at the beginning of every iteration, whether the addition of the two numbers results in a number which is less than or equal to INT_MAX, and if it exceeds this number, one could throw an exception indicating that the result cannot be calculated with this function. Changing the data type to long will not fix the problem, because the overflow just starts later. If one does really want to be able to input two numbers which are 100, one could consider changing the type to BigInteger, which will always yield the correct result, but using the BigInteger type results in a huge Performance loss.

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


### (Please create a Documentation.md file for each task.)
### 1. Code Coverage
### 2. Designing Contracts
### 3. Testing Contracts
### 4. Property-Based Testing
