package zest;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
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
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // The number of paths to the current cell is the sum of the paths to the cell above and to the left
            }
        }

        return dp[m - 1][n - 1]; // The bottom-right cell contains the total number of unique paths
    }
}
