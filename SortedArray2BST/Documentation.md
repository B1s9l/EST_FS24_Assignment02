## SortedArray2BST (Gianni)
### 1. Code Coverage
For this exercise, I achieve 100% branch and line coverage with two tests. The first one just tests a incomplete binary tree, could also be changed to a complete one(i added all of them but think they might be redundant) and a second test trying to convert an empty list.
### 2. Designing Contracts
As a precondition, as specified the readme, the valid length of the array is within [0,10000], if this does not hold, an exception is thrown. As an additional precondition we have that the input array itself cannot be null, which can be easily verified aswell. The last two preconditions which have to hold are that the input array cannot contain duplicates and it has to be sorted in ascending order.

As an invariant, we can specify that the value of every node to the left must be smaller and every value to the right of a node must be bigger than the node value itself, this should hold throught the entire execution and also at the end. Since I already explicitly evaluate the precondition that the input array is sorted in ascending order, the invariant will always hold since the array is reccursively split up in parts until the middle but not the middle and from the value after the middle, therefor the property of being a BST always holds and verifying this is quite redundant, but done anyway.

As a postcondition, we can specify that the number of nodes within the constructed BST is equal to the length of the input array, additionally it should also be a Binary search tree. Additionally, the resulting tree should differ in hight at most 1. The first postcondition that should always hold is defined after the BST is constructed, and if its not met, there will be no result. For the third postcondition is redundant to verify aswell, but done anyway. The second post-condition is verified with the invariant.
### 3. Testing Contracts
testArrayNull: verifies that exception is thrown if the input array is null

testArrayToLong: verifies that exception is thrown if input array's length is not  within the range [0,10000]

testcontainsDuplicates: verifies that exception is thrown if the input array does contain duplicates

testnotSorted: verifies that exception is thrown if input array not sorted

As one might realise is that after adding the invariant and postconditions, i cannot achieve 100% branch coverage anymore, since i cannot violate the postconditions and invariants.
### 4. Property-Based Testing
Every result of this function must have 5 properties, 1: it must be a binary search tree, 2: it cannot contain duplicates, 3: its hight can differ at most one, 4: the inorder traversal of the resulting tree must result in a sorted array and 5: the resulting binary tree must have the same number of elements as the input. By verifying all these properties, we know that the resulted tree must be correct.
