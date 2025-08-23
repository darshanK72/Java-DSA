/**
 * GeeksForGeeks. Friends Pairing Problem
 * 
 * Problem Statement:
 *     Given n friends, each one can remain single or can be paired up with some
 *     other friend. Each friend can be paired only once. Find out the total
 *     number of ways in which friends can remain single or can be paired up.
 * 
 * Input:
 *     - n (1 <= n <= 10^4) - number of friends
 * 
 * Output:
 *     - Long integer representing the number of ways friends can be paired
 * 
 * Example:
 *     Input: n = 3
 *     Output: 4
 * 
 *     Explanation:
 *     Let the friends be numbered 1, 2, 3
 *     Ways to pair them:
 *     1. {1}, {2}, {3} - All single
 *     2. {1,2}, {3} - 1 and 2 paired, 3 single
 *     3. {1,3}, {2} - 1 and 3 paired, 2 single
 *     4. {2,3}, {1} - 2 and 3 paired, 1 single
 *     Total: 4 ways
 */

import java.util.Arrays;

public class j07FriendsPairing {
    
    /**
     * Approach 1: Top-Down DP with Memoization
     * 
     * Intuition:
     * - For each friend, we have two choices: remain single or pair with another
     *   friend who hasn't been paired yet.
     * - If friend remains single: we need to solve for (n-1) friends
     * - If friend pairs with someone: we choose 1 friend from (n-1) remaining
     *   and solve for (n-2) friends
     * - The recurrence relation is: dp[n] = dp[n-1] + (n-1) * dp[n-2]
     * 
     * Explanation:
     * - Base cases: dp[0] = 0, dp[1] = 1, dp[2] = 2
     * - For n > 2: dp[n] = dp[n-1] + (n-1) * dp[n-2]
     *   - dp[n-1]: current friend remains single
     *   - (n-1) * dp[n-2]: current friend pairs with any of (n-1) friends
     * - Use memoization to avoid recalculating subproblems
     * 
     * Time Complexity: O(n) - Each state computed once
     * Space Complexity: O(n) - DP array and recursion stack
     * 
     * @param n    Number of friends (1 <= n <= 10^4)
     * @return     Number of ways to pair friends
     */
    public static long countFriendsPairings(int n) {
        // Initialize DP array for memoization
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        return countPairings(n, dp);
    }
    
    /**
     * Helper Method: countPairings
     * 
     * Intuition:
     * - Recursive DP function that implements the recurrence relation
     * - Uses memoization to store computed results and avoid redundant calculations
     * - Handles the two choices for each friend: single or paired
     * 
     * Explanation:
     * - Base cases handle small inputs directly
     * - Check memo table first to avoid recomputation
     * - Calculate two cases:
     *   - paired: current friend pairs with any of (n-1) friends, solve for (n-2)
     *   - single: current friend stays single, solve for (n-1)
     * - Store result in memo table before returning
     * 
     * Time Complexity: O(1) per state transition, overall O(n)
     * Space Complexity: O(n) due to recursion depth and memo table
     * 
     * @param n    Number of friends remaining
     * @param dp   Memo table where dp[n] stores the answer for n friends
     * @return     Number of ways to pair n friends
     */
    public static long countPairings(int n, long[] dp) {
        // Handle base cases for small inputs
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // Return memoized result if already computed
        if (dp[n] != -1) return dp[n];
        
        // Calculate two cases: friend remains single or pairs with someone
        long paired = countPairings(n - 1, dp);           // Friend stays single
        long single = countPairings(n - 2, dp) * (n - 1); // Friend pairs with any of (n-1) friends
        
        // Store result in memo table and return
        return dp[n] = paired + single;
    }
    
    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     * 
     * Intuition:
     * - Convert the top-down recurrence into an iterative approach
     * - Build the solution from smaller subproblems to larger ones
     * - Eliminates recursion overhead and is more efficient
     * 
     * Explanation:
     * - Initialize base cases: dp[0] = 0, dp[1] = 1, dp[2] = 2
     * - For i from 3 to n: dp[i] = dp[i-1] + (i-1) * dp[i-2]
     * - Return dp[n] as the final result
     * 
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(n) - DP array (can be optimized to O(1))
     * 
     * @param n    Number of friends (1 <= n <= 10^4)
     * @return     Number of ways to pair friends
     */
    public static long countFriendsPairingsTabulation(int n) {
        // Handle edge cases for small inputs
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // Initialize DP array
        long[] dp = new long[n + 1];
        
        // Set base cases
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        
        // Fill DP table from 3 to n using recurrence relation
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
        }
        
        return dp[n];
    }
    
    /**
     * Approach 3: Space-Optimized Bottom-Up DP
     * 
     * Intuition:
     * - Since the recurrence relation only depends on the previous two states
     *   (dp[i-1] and dp[i-2]), we don't need to store the entire DP array
     * - Use just two variables to track the current and previous states
     * - Reduces space complexity from O(n) to O(1)
     * 
     * Explanation:
     * - Initialize two variables: prev (dp[i-2]), current (dp[i-1])
     * - Set base cases: prev = 1, current = 2
     * - For each iteration from 3 to n:
     *   - Calculate next = current + (i-1) * prev
     *   - Update prev = old current
     * - Return current as the final result
     * 
     * Time Complexity: O(n) - Single pass through the range 3 to n
     * Space Complexity: O(1) - Only two variables regardless of input size
     * 
     * @param n    Number of friends (1 <= n <= 10^4)
     * @return     Number of ways to pair friends
     */
    public static long countFriendsPairingsSpaceOptimized(int n) {
        // Handle edge cases for small inputs
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // Initialize two variables to track DP states
        long prev = 1;                    // dp[i-2]: ways for i-2 friends
        long current = 2;                 // dp[i-1]: ways for i-1 friends
        
        // Iterate from 3 to n, updating the two variables
        for (int i = 3; i <= n; i++) {
            // Calculate new current: current + (i-1) * prev
            long next = current + (i - 1) * prev;
            // Update variables for next iteration
            prev = current;
            current = next;
        }
        
        return current;
    }

    public static void main(String[] args) {
        
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=3, Expected: 4, Output: " + countFriendsPairings(3));
        System.out.println("Input: n=4, Expected: 10, Output: " + countFriendsPairings(4));
        System.out.println("Input: n=5, Expected: 26, Output: " + countFriendsPairings(5));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, Expected: 1, Output: " + countFriendsPairings(1));
        System.out.println("Input: n=2, Expected: 2, Output: " + countFriendsPairings(2));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=0, Expected: 0, Output: " + countFriendsPairings(0));
        System.out.println("Input: n=6, Expected: 76, Output: " + countFriendsPairings(6));

        // Test Case 4: Compare all approaches
        System.out.println("\nComparing All Approaches:");
        int n = 7;
        System.out.println("Input: n=" + n);
        System.out.println("Memoization: " + countFriendsPairings(n));
        System.out.println("Tabulation: " + countFriendsPairingsTabulation(n));
        System.out.println("Space Optimized: " + countFriendsPairingsSpaceOptimized(n));

        // Test Case 5: Large input
        System.out.println("\nLarge Input Test:");
        n = 10;
        System.out.println("Input: n=" + n);
        System.out.println("Space Optimized: " + countFriendsPairingsSpaceOptimized(n));
    }
}
