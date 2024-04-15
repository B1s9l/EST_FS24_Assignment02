package zest;

public class ClimbingStairs {

    /**
     * Given a number of steps n, return the number of possible ways to walk down n stairs by taking one or two steps each time
     *
     * @param n the number of steps to walk down. Must be a positive integer.
     *
     * @return the calculated number of ways to walk the steps. The value is always non-negative.
     */
    public long climbStairs(int n) {

        // Task 2 pre-condition
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer");
        }

        if (n <= 2) {
            return n;
        }
        long oneStepBefore = 2;
        long twoStepsBefore = 1;
        long allWays = 0;

        for (int i = 2; i < n; i++) {
            allWays = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = allWays;
            // Task 2 - invariant
            if (twoStepsBefore > oneStepBefore){
                throw new RuntimeException("One step before should have more possible ways, one step before: " + Long.toString(oneStepBefore) + "; two steps before: " + Long.toString(twoStepsBefore));
            }

        }

        // Task 2 post-condition
        if (allWays < 0){
            throw new RuntimeException("The result should be non-negative");
        }

        return allWays;
    }
}
