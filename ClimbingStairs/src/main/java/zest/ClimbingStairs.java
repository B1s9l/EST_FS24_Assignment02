package zest;

public class ClimbingStairs {
    public long climbStairs(int n) {
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
        }
        return allWays;
    }
}
