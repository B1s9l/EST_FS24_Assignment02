package zest;

class UniquePaths {
    public int uniquePaths(int m, int n) throws IllegalArgumentException{
        if(m < 1 || m > 100 || n < 1 || n > 100)throw new IllegalArgumentException("Both numbers should be larger than 0 and smaller than 101");
        int[][] dp = new int[m][n];

        // Initialize the first row and column to 1 since there's only one way to reach any cell in the first row or column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(!invariant(dp[i-1][j], dp[i][j-1]))throw new IllegalArgumentException("Int overflow occured, please provide smaller numbers or change the implementation to BigInteger or long as desired(long will also overflow at a certain point)");
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // The number of paths to the current cell is the sum of the paths to the cell above and to the left
            }
        }

        return dp[m - 1][n - 1]; // The bottom-right cell contains the total number of unique paths
    }
    private boolean invariant(long a, long b){
        return a + b <= Integer.MAX_VALUE;
    }
}
