/**
 * GeeksForGeeks. Ways to Tile a Floor II (Generalized tiling problem)
 *
 * Problem Statement:
 *     Given a floor of size 1 x n and tiles of size 1 x 1 and 1 x m, count
 *     the number of ways to tile the floor. You can place either a 1 x 1 tile
 *     or a 1 x m tile at any position, ensuring the entire floor is covered
 *     without overlap and without gaps. Return the result modulo 10^9 + 7.
 *
 * Input:
 *     - n (0 <= n <= 1e5): length of the floor in units.
 *     - m (1 <= m <= n): length of the larger tile (1 x m).
 *
 * Output:
 *     - Integer: number of ways to tile the 1 x n floor modulo 10^9 + 7.
 *
 * Example:
 *     Input: n = 4, m = 2
 *     Output: 5
 *     Explanation:
 *         Ways: (1,1,1,1), (1,1,2), (1,2,1), (2,1,1), (2,2) where 1=1x1
 *         tile, 2=1x2 tile.
 */

import java.util.Arrays;

public class j04WaysToTileAFloorII {
    // Modulo constant for large number arithmetic
    static int MOD = 1000000007;

    /**
     * Approach: Top-Down DP (Memoized Recursion)
     *
     * Intuition:
     * - Let f(n) be the number of ways to tile a 1 x n floor.
     * - Place a 1 x 1 tile: remaining ways = f(n-1).
     * - Or place a 1 x m tile: remaining ways = f(n-m).
     * - Therefore f(n) = f(n-1) + f(n-m) with base f(0)=1.
     * - Use modulo arithmetic to handle large numbers.
     *
     * Explanation:
     * - We use recursion with memoization to compute f(n).
     * - At each step, we have two choices: place 1x1 or 1xm tile.
     * - Base case: f(0) = 1 (empty tiling), f(n<0) = 0 (invalid).
     * - All arithmetic is done modulo 10^9 + 7 to prevent overflow.
     *
     * Time Complexity: O(n)
     *     Each state 0..n computed once.
     * Space Complexity: O(n)
     *     Memo array plus recursion depth up to n.
     *
     * @param n        Length of the floor (units). If n < 0, returns 0.
     * @param m        Length of the larger tile (1 x m). Must be >= 1.
     * @return         Number of tilings modulo 10^9 + 7.
     */
    public static int countWays(int n, int m) {
        // Validate input parameters
        if (n < 0 || m < 1) {
            return 0;
        }

        // Memoization array initialized to -1 to denote uncomputed states
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);

        // Compute and return result modulo MOD
        return (int) (countWaysToTile(n, m, dp) % MOD);
    }

    /**
     * Helper Method: countWaysToTile
     *
     * Intuition:
     * - Implements the recurrence f(n) = f(n-1) + f(n-m) with proper
     *   modulo arithmetic to handle large numbers.
     *
     * Explanation:
     * - If n == 0, one valid arrangement (do nothing) is counted.
     * - If n < 0, overused tiles, so zero ways.
     * - Otherwise, compute both options: place 1x1 tile (n-1) and place
     *   1xm tile (n-m), then memoize the sum modulo MOD.
     *
     * Time Complexity: O(1) amortized per state due to memoization
     * Space Complexity: O(1) extra per call
     *
     * @param n        Remaining length to cover
     * @param m        Length of the larger tile
     * @param dp       Memo array storing f(i) at dp[i], or -1 if unknown
     * @return         Number of tilings for 1 x n modulo MOD
     */
    public static long countWaysToTile(int n, int m, long[] dp) {
        // Base cases
        if (n == 0) return 1;                // One way: no tiles placed
        if (n < 0) return 0;                  // Invalid: overshot length

        // Return cached result if already computed
        if (dp[n] != -1) return dp[n];

        // Option 1: Place one 1 x 1 tile
        long singleTileWays = countWaysToTile(n - 1, m, dp) % MOD;

        // Option 2: Place one 1 x m tile (if n >= m)
        long multiTileWays = countWaysToTile(n - m, m, dp) % MOD;

        // Cache and return total ways modulo MOD
        return dp[n] = (singleTileWays + multiTileWays) % MOD;
    }

    public static void main(String[] args) {
        // Basic/Happy path cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("n=0, m=2, Expected: 1, Output: " + countWays(0, 2));
        System.out.println("n=1, m=2, Expected: 1, Output: " + countWays(1, 2));
        System.out.println("n=2, m=2, Expected: 2, Output: " + countWays(2, 2));
        System.out.println("n=3, m=2, Expected: 3, Output: " + countWays(3, 2));
        System.out.println("n=4, m=2, Expected: 5, Output: " + countWays(4, 2));

        // Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("n=-1, m=2, Expected: 0, Output: " + countWays(-1, 2));
        System.out.println("n=3, m=0, Expected: 0, Output: " + countWays(3, 0));
        System.out.println("n=3, m=4, Expected: 1, Output: " + countWays(3, 4));

        // Boundary/Larger cases
        System.out.println("\nBoundary/Large Cases:");
        System.out.println("n=5, m=2, Expected: 8, Output: " + countWays(5, 2));
        System.out.println("n=10, m=3, Expected: 28, Output: " + countWays(10, 3));
    }
}
