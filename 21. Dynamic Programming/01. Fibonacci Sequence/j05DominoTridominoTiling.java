/**
 * LeetCode 790. Domino and Tromino Tiling
 *
 * Problem Statement:
 *     You have two types of tiles: a 2 x 1 domino shape and a tromino shape.
 *     Given an integer n, return the number of ways to tile a 2 x n board.
 *     Since the answer may be very large, return it modulo 10^9 + 7.
 *
 * Input:
 *     - n (1 <= n <= 1000): length of the board in columns.
 *
 * Output:
 *     - Integer: number of ways to tile the 2 x n board modulo 10^9 + 7.
 *
 * Example:
 *     Input: n = 3
 *     Output: 5
 *     Explanation:
 *         The five different ways are shown in the diagram above.
 */

import java.util.Arrays;

public class j05DominoTridominoTiling {
    // Modulo constant for large number arithmetic
    static int MOD = 1000000007;

    /**
    * DERIVATION AND REASONING:
    * 
    * Let f(n) be the number of ways to tile a 2 x n board.
    * 
    * Base Cases:
    * - f(1) = 1: Only one vertical domino fits
    * - f(2) = 2: Two vertical dominos OR two horizontal dominos
    * - f(3) = 5: Can be verified by enumeration
    * 
    * For n >= 4, we can derive the recurrence:
    * 
    * Consider the rightmost column(s) of a valid tiling:
    * 
    * Case 1: Rightmost column has a vertical domino
    * - This leaves a 2 x (n-1) board to tile
    * - Number of ways: f(n-1)
    * 
    * Case 2: Rightmost two columns are filled with horizontal dominos
    * - This leaves a 2 x (n-2) board to tile  
    * - Number of ways: f(n-2)
    * 
    * Case 3: Rightmost column has a tromino (L-shape)
    * - The tromino must be paired with another tromino in the next column
    * - This creates a 2 x 2 block that leaves a 2 x (n-2) board
    * - Number of ways: f(n-2)
    * 
    * However, this double-counts Case 2! The tromino case is actually:
    * - Rightmost column has a tromino
    * - Next column has the matching tromino
    * - This leaves a 2 x (n-2) board
    * - But we can also have the tromino pair starting from position n-1
    * - So total tromino contribution: 2 * f(n-2)
    * 
    * Wait, let me reconsider...
    * 
    * Actually, the correct recurrence is more complex. Let me analyze:
    * 
    * For n >= 4:
    * f(n) = f(n-1) + f(n-2) + 2 * f(n-3) + 2 * f(n-4) + ... + 2 * f(0)
    * 
    * But this can be simplified! Let's look at the pattern:
    * f(1) = 1
    * f(2) = 2  
    * f(3) = 5
    * f(4) = 11
    * f(5) = 24
    * 
    * Notice: f(n) = 2 * f(n-1) + f(n-3) for n >= 4
    * 
    * Why? Because:
    * - We can extend any valid tiling of length n-1 by adding a vertical domino: 2 * f(n-1)
    * - We can extend any valid tiling of length n-3 by adding a tromino pair: f(n-3)
    * 
    * The factor of 2 comes from the fact that when we add a vertical domino to a tiling
    * of length n-1, we can place it in two different orientations.
    */

    /**
     * Approach: Top-Down DP (Memoized Recursion)
     *
     * Intuition:
     * - The recurrence f(n) = 2*f(n-1) + f(n-3) captures all valid tilings.
     * - Base cases: f(1)=1, f(2)=2, f(3)=5.
     * - Use memoization to avoid recomputation of subproblems.
     *
     * Explanation:
     * - For n >= 4, any valid tiling can be constructed by either:
     *   1. Taking a valid tiling of length n-1 and adding a vertical domino
     *      (2 ways due to orientation)
     *   2. Taking a valid tiling of length n-3 and adding a tromino pair
     * - The recurrence naturally handles all possible configurations.
     *
     * Time Complexity: O(n)
     *     Each state 1..n computed once.
     * Space Complexity: O(n)
     *     Memo array plus recursion depth up to n.
     *
     * @param n        Length of the board (columns). Must be >= 1.
     * @return         Number of tilings modulo 10^9 + 7.
     */
    public int numTilings(int n) {
        // Validate input parameter
        if (n < 1) {
            return 0;
        }

        // Memoization array initialized to -1 to denote uncomputed states
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // Compute the number of tilings using top-down recursion
        return countWaysOfTiling(n, dp);
    }

    /**
     * Helper Method: countWaysOfTiling
     *
     * Intuition:
     * - Implements the recurrence f(n) = 2*f(n-1) + f(n-3) with base cases.
     * - Uses memoization to store computed results.
     *
     * Explanation:
     * - Base cases: f(1)=1, f(2)=2, f(3)=5 (verified by enumeration).
     * - For n >= 4: apply the recurrence with proper modulo arithmetic.
     * - The factor of 2 in 2*f(n-1) accounts for two orientations of vertical domino.
     *
     * Time Complexity: O(1) amortized per state due to memoization
     * Space Complexity: O(1) extra per call
     *
     * @param n        Remaining length to tile
     * @param dp       Memo array storing f(i) at dp[i], or -1 if unknown
     * @return         Number of tilings for 2 x n modulo MOD
     */
    public int countWaysOfTiling(int n, int[] dp) {
        // Base cases (verified by enumeration)
        if (n == 1) return 1;                // One vertical domino
        if (n == 2) return 2;                // Two vertical OR two horizontal dominos
        if (n == 3) return 5;                // Five different configurations

        // Return cached result if already computed
        if (dp[n] != -1) return dp[n];

        // Apply recurrence: f(n) = 2*f(n-1) + f(n-3)
        // Factor of 2 accounts for two orientations of vertical domino
        int verticalExtension = (2 * countWaysOfTiling(n - 1, dp)) % MOD;
        int trominoExtension = countWaysOfTiling(n - 3, dp) % MOD;

        // Cache and return total ways modulo MOD
        return dp[n] = (verticalExtension + trominoExtension) % MOD;
    }

    public static void main(String[] args) {
        j05DominoTridominoTiling solution = new j05DominoTridominoTiling();

        // Basic/Happy path cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("n=1, Expected: 1, Output: " + solution.numTilings(1));
        System.out.println("n=2, Expected: 2, Output: " + solution.numTilings(2));
        System.out.println("n=3, Expected: 5, Output: " + solution.numTilings(3));
        System.out.println("n=4, Expected: 11, Output: " + solution.numTilings(4));
        System.out.println("n=5, Expected: 24, Output: " + solution.numTilings(5));

        // Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("n=0, Expected: 0, Output: " + solution.numTilings(0));
        System.out.println("n=-1, Expected: 0, Output: " + solution.numTilings(-1));

        // Boundary/Larger cases
        System.out.println("\nBoundary/Large Cases:");
        System.out.println("n=6, Expected: 53, Output: " + solution.numTilings(6));
        System.out.println("n=7, Expected: 117, Output: " + solution.numTilings(7));
        System.out.println("n=10, Expected: 1255, Output: " + solution.numTilings(10));
    }
}
