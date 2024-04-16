### Task 1: Code Coverage
**1. Understanding Requirements, Input and Output**

Clear from description.

**2. Exploring program**

Exploring Happy cases and if program behaves as it should in these cases.

**3. Identify Partitions**

a) Input
- numCourses = 0
- numCourses > 0
- prerequisites is null --> true, because no prerequisites
- prerequisites length 0 --> true, because no prerequisites
- prerequisites length 1 --> true, because no cycle possible
- prerequisites > 1 no cycle --> true
- prerequisites > 1 has cycle --> false

b) Interactions

The prerequisites have to be <numCourses.

c) Output
- true, if no cycle is present in prerequisites
- false, if cycle is present in prerequisites

**4. Boundaries**

When prerequisites.length > 1: has cycle (on-point), has no cycle(off-point). 

**5. Devising and Reducing Test Cases**

T1 - T3 are exceptional cases which we test only once. We test the partitions by testing their boundaries as specified above.
- T1: numCourses = 0
- T2: prerequisites = [] 
- T3: prerequisites = null
- T4: prerequisites = [[0,1]], numCourses = 2
- T5: prerequisites = [[0,1], [0,2]], numCourses = 3 --> no cycle --> true
- T6: prerequisites = [[0,1], [1,0]], numCourses = 2 --> has cycle --> false

Partitions:
- numcourses = 0 is checked with T1
- prerequisites = [] is checked with T2
- prerequisites = null is checked with T3
- prerequisites length 1 is checked with T4
- prerequisites length > 1, no cycle is checked wth T5 
- prerequisites length > 1, has cycle is checked wth T6 
- Boundaries are checked with T5 and T6

**6. Automating**

Found a bug with T2, when prerequisites is null. I would expect true to be returned because there are no prerequisites. 
I add an if statement at Line 10 that checks for null Array and returns true if prerequisites is null. The test passes now.

**7. Augment Test Suite**

Additional values:
- T6: numCourses = 4, prerequisites has cycle --> false
- T7: numCourses = 4, prerequisites has no cycle --> true

There are additional inputs we didn't consider yet. Particularly if prerequisite contains a value that is > numCourses and if prerequisites contains an entry [[a,b]] where a = b.
I intentionally didn't test these values as we will handle them with contracts at a later point.

**8. Structural Testing**

100% line coverage reached (see screenshot in asset folder)


### Task 2: Designing Contracts

Pre-conditions:

1) each value in prerequisites is <numCourses
2) No pair of prerequisites contains duplicates
3) numCourses is > 0 --> need to change T1 from specification-based testing to expect an exception to be thrown.
4) Pre-Requisites can't be null --> we need to change T2 from specification-based testing to expect an exception to be thrown.

Post-conditions:

1) The return value is either true or false

### Task 3: Test Contracts

With the created tests, we validate normal operation when pre-conditions are met. We confirm that the correct exceptions are thrown when they are violated.
We add some test to ensure that post-condition holds, by validating it under different conditions:
The complete test suite contains:

- T8: validates normal operation, numCourses = 4, prerequisites = [[0,1], [0,2], [1,2], [1,0]]
- T9: Checks if pre-condition 1 works, numCourses = 1, prerequisites = [[0,1]]
- T10: Checks if pre-condition 2 works, numCourses = 1, prerequisites = [[0,0]]
- T11: Checks if pre-condition 3 works, numCourses = 0, prerequisites = [[0,1]]
- T12: Checks if pre-condition 4 works, numCourses = 0, prerequisites = null
- T13: Validates Post-condition when cycle present, numCourses = 4, prerequisites = [[0,1], [0,2], [1,0]]
- T14: Validates Post-condition when no cycle present, numCourses = 4, prerequisites = [[0,1], [0,2], [1,3]]

See coverage report in assets folder, the only line missing regards the post-condition.
Wth T13 and T14 however, we test that it is met. It doesn't make sense to test it because we would have to change the code in a way that it behaves incorrectly.
The fact that we never reach our post-condition is good, as therefore the method under test doesn't produce invalid output.

### Task 4: Property-based Testing

**Properties**:

Returns true if prerequisites have no cycle. Returns false if prerequisites have a cycle.

**Parameter**: integer array

We create two property tests for both properties. We provide the property test with prerequisites that are valid and contain no cycle and vice versa.
We use Jgraph, a well known library for graph theory to check if the provided list has a cycle and filter out the arbitrary lists that have a cycle for T15 and the lists that have no cycle for T16.
Like this, we can provide the tests the necessary params and know if the method under test should return true or false.

- T15: For property 1: we create prerequisites that are represented as acyclic graph
- T16: For property 2: we create prerequisites that are represented as cyclic graph


