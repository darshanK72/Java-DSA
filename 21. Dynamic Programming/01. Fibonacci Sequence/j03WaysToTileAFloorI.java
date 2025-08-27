/**
 * GeeksForGeeks. Ways to Tile a Floor (Number of ways to tile 2 x n board)
 *
 * Problem Statement:
 *     Given a floor of size 2 x n and tiles of size 2 x 1 (dominoes), count
 *     the number of ways to tile the floor. A tile can be placed either
 *     vertically (covering 2 x 1) or horizontally (covering 1 x 2 using two
 *     tiles stacked), so that the entire 2 x n floor is covered without
 *     overlap and without gaps.
 *
 * Input:
 *     - n (0 <= n <= 1e5): length of the board in columns.
 *
 * Output:
 *     - Integer: number of ways to completely tile the 2 x n floor.
 *
 * Example:
 *     Input: n = 3
 *     Output: 3
 *     Explanation:
 *         Ways: (VVV), (VH|HV), (HV|VH) where V=vertical 2x1, H=pair of
 *         horizontal tiles in a column.
 */

import java.util.Arrays;

public class j03WaysToTileAFloorI {
    /**
     * Approach: Top-Down DP (Memoized Recursion)
     *
     * Intuition:
     * - Let f(n) be the number of ways to tile a 2 x n floor.
     * - Place a vertical tile in the first column: remaining ways = f(n-1).
     * - Or place two horizontal tiles stacked spanning columns 1 and 2:
     *   remaining ways = f(n-2).
     * - Therefore f(n) = f(n-1) + f(n-2) with bases f(0)=1, f(1)=1.
     *
     * Explanation:
     * - We compute f(n) using recursion and cache results to avoid repeated
     *   work. This is the Fibonacci recurrence tailored to tiling.
     *
     * Time Complexity: O(n)
     *     Each state 0..n computed once.
     * Space Complexity: O(n)
     *     Memo array plus recursion depth up to n.
     *
     * @param n        Length of the board (columns). If n < 0, returns 0.
     * @return         Number of tilings of a 2 x n board.
     */
    static int numberOfWays(int n) {
        // Validate input: negative lengths have 0 valid tilings
        if (n < 0) {
            return 0;
        }

        // Memoization array initialized to -1 to denote uncomputed states
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // Compute the number of tilings using top-down recursion
        return countWaysToTile(n, dp);
    }

    /**
     * Helper Method: countWaysToTile
     *
     * Intuition:
     * - Uses the recurrence f(n) = f(n-1) + f(n-2) with base cases
     *   f(0)=1 (empty tiling) and f(n<0)=0 (invalid).
     *
     * Explanation:
     * - If n == 0, one valid arrangement (do nothing) is counted.
     * - If n < 0, overused tiles, so zero ways.
     * - Otherwise, compute both options: place vertical (n-1) and place two
     *   horizontal (n-2), then memoize the sum.
     *
     * Time Complexity: O(1) amortized per state due to memoization
     * Space Complexity: O(1) extra per call
     *
     * @param n        Remaining columns to cover
     * @param dp       Memo array storing f(i) at dp[i], or -1 if unknown
     * @return         Number of tilings for 2 x n
     */
    public static int countWaysToTile(int n, int[] dp) {
        // Base cases
        if (n < 0) return 0;                  // Invalid: overshot length
        if (n == 0) return 1;                // One way: no tiles placed

        // Return cached result if already computed
        if (dp[n] != -1) return dp[n];

        // Option 1: Place one vertical tile in the first column
        int verticalWays = countWaysToTile(n - 1, dp);

        // Option 2: Place two horizontal tiles covering the first two columns
        int horizontalWays = countWaysToTile(n - 2, dp);

        // Cache and return total ways
        return dp[n] = verticalWays + horizontalWays;
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     *
     * Intuition:
     * - The tiling count follows Fibonacci: f(n) = f(n-1) + f(n-2) with
     *   bases f(0)=1 (empty board) and f(1)=1 (one vertical domino).
     * - Build an array iteratively where dp[i] stores the number of tilings
     *   for a 2 x i board, using previously computed results.
     *
     * Explanation:
     * - Handle invalid input n < 0 by returning 0 since no tiling is possible.
     * - Directly return base cases for n == 0 and n == 1.
     * - Initialize dp[0] and dp[1] to 1, then iterate i from 2..n computing
     *   dp[i] = dp[i-1] + dp[i-2]. This corresponds to placing a vertical
     *   domino first (leaving i-1) or two horizontals (leaving i-2).
     *
     * Time Complexity: O(n)
     *     Single pass filling dp[0..n].
     * Space Complexity: O(n)
     *     Array of size n+1 to store subproblem results.
     *
     * @param n        Length of the board (columns). If n < 0, returns 0.
     * @return         Number of tilings of a 2 x n board.
     */
    public static int cointWaysToTileTabulation(int n){
        // Validate input: negative lengths have 0 valid tilings
        if (n < 0) {
            return 0;
        }

        // Handle base cases explicitly to avoid out-of-bounds access
        if (n == 0) {
            return 1; // One way: empty tiling
        }
        if (n == 1) {
            return 1; // One vertical domino
        }

        // dp[i] holds the number of tilings for a 2 x i board
        int[] dp = new int[n + 1];

        // Initialize base cases
        dp[0] = 1; // Empty board
        dp[1] = 1; // Single column: one vertical domino

        // Fill the table from 2..n using the Fibonacci-like recurrence
        for (int i = 2; i <= n; i++) {
            // dp[i-1]: place one vertical domino in the first column
            // dp[i-2]: place two horizontal dominoes covering first two columns
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // The answer for the full board length n
        return dp[n];
    }

    public static void main(String[] args) {
        // Basic/Happy path cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("n=0, Expected: 1, Output: " + numberOfWays(0));
        System.out.println("n=1, Expected: 1, Output: " + numberOfWays(1));
        System.out.println("n=2, Expected: 2, Output: " + numberOfWays(2));
        System.out.println("n=3, Expected: 3, Output: " + numberOfWays(3));
        System.out.println("n=4, Expected: 5, Output: " + numberOfWays(4));

        // Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("n=-1, Expected: 0, Output: " + numberOfWays(-1));

        // Boundary/Larger cases
        System.out.println("\nBoundary/Large Cases:");
        System.out.println("n=5, Expected: 8, Output: " + numberOfWays(5));
        System.out.println("n=10, Expected: 89, Output: " + numberOfWays(10));
    }
}
