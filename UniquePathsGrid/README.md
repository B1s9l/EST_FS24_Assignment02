# Problem: Unique Paths in a Grid

## Description

A robot is located in the top-left corner of a m x n grid. The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid. How many possible unique paths are there?

## Constraints:

The grid is represented by its dimensions: m rows and n columns.

Both m and n are integers and will be in the range [1, 100].

The input to the function is two positive integers m and n that represent the number of rows and columns, respectively, in the grid (m > 0, n > 0).

The start point is always at the top-left corner of the grid, and the endpoint is always at the bottom-right corner.

The function returns a non-negative integer that represents the total number of unique paths 


### Example 1:

**Input**: `m = 3, n = 7`
**Output**: `28`

Explanation: There are 28 unique paths for the robot to reach the bottom-right corner of a 3x7 grid.

### Example 2:

**Input**: `m = 2, n = 3`
**Output**: `3`

Explanation: From the top-left corner, there are a total of 3 paths to reach the bottom-right corner:
Right → Right → Down
Right → Down → Right
Down → Right → Right

### Example 3:

**Input**: `m = 3, n = 3`
**Output**: `6`

Explanation: For a 3x3 grid, there are 6 unique paths from the top-left corner to the bottom-right corner:
Right → Right → Down → Down
Right → Down → Right → Down
Right → Down → Down → Right
Down → Right → Right → Down
Down → Right → Down → Right
Down → Down → Right → Right
