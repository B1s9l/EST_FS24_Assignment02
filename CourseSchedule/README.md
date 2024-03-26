# Problem: Course Schedule

## Description

Imagine you are a student planning your class schedule. You have a list of courses you need to take, numbered from 0 to **numCourses - 1**. However, some courses require you to complete other specific courses before you can enrol in them. These prerequisite requirements are represented as pairs. For instance, if you need to complete course 1 before starting course 0, this requirement is expressed as the pair [0,1].

Your task is to determine if you can successfully enrol in and complete all your courses, given the prerequisite conditions. The main challenge is to check for any circular prerequisites, which create a deadlock scenario. This is similar to a "chicken and egg" problem where, for example, you need to take Course A before Course B, but Course B is also required before you can start Course A. If such loops exist in the prerequisites, it becomes impossible to fulfil all course requirements.


### Constraints:
Prerequisites are represented as a list of edge pairs in a directed graph. This means each pair [a, b] indicates a one-way requirement from b to a — you must finish course b before starting course a.

numCourses represents the total number of courses and is a positive integer.

No course can require itself directly as a prerequisite (a cannot equal b in the pair [a, b]).

Each prerequisite pair must refer to valid courses, i.e., if a pair is [a, b], then 0 ≤ a, b < numCourses.

The method returns a boolean value: true if it is possible to finish all courses without encountering prerequisite cycles, and false otherwise.


### Example 1:

**Input**: `numCourses = 2, prerequisites = [[1,0]]`
**Output**: `true`

Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.


### Example 2:

**Input**: `numCourses = 2, prerequisites = [[1,0],[0,1]]`
**Output**: `false`

Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. Hence, it is impossible.

