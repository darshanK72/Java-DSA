/**
 * LeetCode 276. Paint Fence
 * 
 * Problem Statement:
 *     You are painting a fence of n posts with k different colors. You must paint
 *     the posts following these rules:
 *     1. Every post must be painted exactly one color
 *     2. At most 2 adjacent posts can have the same color
 *     Return the number of ways you can paint the fence.
 * 
 * Input:
 *     - n (0 <= n <= 10^5) - number of fence posts
 *     - k (1 <= k <= 10^5) - number of different colors
 * 
 * Output:
 *     - Integer representing the number of ways to paint the fence
 * 
 * Example:
 *     Input: n = 3, k = 2
 *     Output: 6
 * 
 *     Explanation:
 *     All the possibilities are shown in the image:
 *     Post 1: Red, Post 2: Red, Post 3: Blue
 *     Post 1: Red, Post 2: Blue, Post 3: Red
 *     Post 1: Red, Post 2: Blue, Post 3: Blue
 *     Post 1: Blue, Post 2: Red, Post 3: Red
 *     Post 1: Blue, Post 2: Red, Post 3: Blue
 *     Post 1: Blue, Post 2: Blue, Post 3: Red
 */

import java.util.Arrays;

public class j06PaintFenceI {
    /**
     * Approach 1: Optimized 1D DP Memoization
     * 
     * Intuition:
     * - Since the number of ways depends only on the number of posts (n) and not
     *   on specific color combinations, we only need a 1D DP array.
     * - The recurrence relation is: dp[n] = (dp[n-1] + dp[n-2]) * (k-1)
     * - This is much more efficient in both time and space complexity.
     * 
     * Explanation:
     * - Use dp[n] where n = number of posts
     * - Base cases: dp[0] = 0, dp[1] = k, dp[2] = k*k
     * - For n>2: dp[n] = (dp[n-1] + dp[n-2]) * (k-1)
     * - This captures both cases: different color and same color
     * 
     * Time Complexity: O(n) - Each state computed once
     * Space Complexity: O(n) - DP array and recursion stack
     * 
     * @param n    Number of fence posts (0 <= n <= 10^5)
     * @param k    Number of different colors (1 <= k <= 10^5)
     * @return     Number of ways to paint the fence
     */
    public static int countWaysMemoization(int n, int k) {
        // Initialize 1D DP array (much more efficient)
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return countWaysMemoizationHelper(n, k, dp);
    }

    /**
     * Helper Method: countWaysOptimizedHelper (1D DP)
     * 
     * Intuition:
     * - Recursive DP function using 1D memoization.
     * - Much more efficient than 2D approach since we don't need to track
     *   specific color combinations.
     * 
     * Explanation:
     * - Base cases: dp[0] = 0, dp[1] = k, dp[2] = k*k
     * - For n>2: dp[n] = (dp[n-1] + dp[n-2]) * (k-1)
     * - This formula captures:
     *   - dp[n-1] * (k-1): paint current post different from previous
     *   - dp[n-2] * (k-1): paint current post same as previous (but not 3 in a row)
     * 
     * Time Complexity: O(1) per state transition, overall O(n)
     * Space Complexity: O(n) due to recursion depth and memo table
     * 
     * @param n    Number of fence posts
     * @param k    Number of different colors
     * @param dp   Memo table where dp[n] stores the answer
     * @return     Number of ways to paint n posts with k colors
     */
    private static int countWaysMemoizationHelper(int n, int k, int[] dp) {

        // Base cases
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;

        // Return memoized answer if this state was computed before
        if (dp[n] != -1) return dp[n];

        // Recurrence relation: dp[n] = (dp[n-1] + dp[n-2]) * (k-1)
        int lastDifferent = countWaysMemoizationHelper(n - 1, k, dp) * (k - 1);
        int lastSame = countWaysMemoizationHelper(n - 2, k, dp) * (k - 1);
        
        // Memoize and return the result
        return dp[n] = lastDifferent + lastSame;
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation) - Most Efficient
     * 
     * Intuition:
     * - Convert the top-down recurrence into an iterative DP.
     * - Build the solution from smaller subproblems to larger ones.
     * - This eliminates recursion overhead and is the most efficient approach.
     * 
     * Explanation:
     * - Initialize base cases: dp[0] = 0, dp[1] = k, dp[2] = k*k
     * - For i from 3 to n: dp[i] = (dp[i-1] + dp[i-2]) * (k-1)
     * - Return dp[n]
     * 
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(n) - DP array (can be optimized to O(1))
     * 
     * @param n    Number of fence posts (0 <= n <= 10^5)
     * @param k    Number of different colors (1 <= k <= 10^5)
     * @return     Number of ways to paint the fence
     */
    public static int countWaysTabulation(int n, int k) {
        // Handle edge cases
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;
        
        // Initialize DP array
        int[] dp = new int[n + 1];
        
        // Set base cases
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k * k;
        
        // Fill DP table from 3 to n
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
        }
        
        return dp[n];
    }

    /**
     * Approach 3: Space-Optimized Bottom-Up DP
     * 
     * Intuition:
     * - Since the recurrence relation only depends on the previous two states
     *   (dp[i-1] and dp[i-2]), we don't need to store the entire DP array.
     * - We can use just three variables to track the current state and the
     *   two previous states, reducing space complexity from O(n) to O(1).
     * - This is the most memory-efficient approach while maintaining the same
     *   time complexity as tabulation.
     * 
     * Explanation:
     * - Initialize three variables: prev2 (dp[i-2]), prev1 (dp[i-1]), current (dp[i])
     * - Set base cases: prev2 = k, prev1 = k*(k-1), current = prev2 + prev1
     * - For each iteration from 3 to n:
     *   - Update prev2 = prev1 (shift window)
     *   - Update prev1 = current * (k-1) (different color case)
     *   - Update current = prev2 + prev1 (combine both cases)
     * - Return current as the final result
     * 
     * Time Complexity: O(n) - Single pass through the range 3 to n
     * Space Complexity: O(1) - Only three variables regardless of input size
     * 
     * @param n    Number of fence posts (0 <= n <= 10^5)
     * @param k    Number of different colors (1 <= k <= 10^5)
     * @return     Number of ways to paint the fence
     */
    public static int countWaysSpaceOptimized(int n, int k) {
        // Handle edge cases for small inputs
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;
        
        // Initialize three variables to track DP states
        int prev2 = k;                    // dp[i-2]: ways for i-2 posts
        int prev1 = k * (k - 1);          // dp[i-1]: ways for i-1 posts  
        int current = prev2 + prev1;      // dp[i]: ways for current i posts
        
        // Iterate from 3 to n, updating the three variables
        for (int i = 3; i <= n; i++) {
            // Shift the window: prev2 becomes prev1, prev1 becomes current
            prev2 = prev1;
            // Calculate new prev1: current ways * (k-1) for different color
            prev1 = current * (k - 1);
            // Calculate new current: combine prev2 and prev1 cases
            current = prev2 + prev1;
        }
        
        return current;         
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=3, k=2, Expected: 6, Output: " + countWaysSpaceOptimized(3, 2));
        System.out.println("Input: n=1, k=3, Expected: 3, Output: " + countWaysSpaceOptimized(1, 3));
        System.out.println("Input: n=2, k=2, Expected: 4, Output: " + countWaysSpaceOptimized(2, 2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=0, k=2, Expected: 0, Output: " + countWaysSpaceOptimized(0, 2));
        System.out.println("Input: n=1, k=1, Expected: 1, Output: " + countWaysSpaceOptimized(1, 1));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=4, k=1, Expected: 0, Output: " + countWaysSpaceOptimized(4, 1));
        System.out.println("Input: n=5, k=3, Expected: 180, Output: " + countWaysSpaceOptimized(5, 3));

        // Test Case 4: Compare all approaches
        System.out.println("\nComparing All Approaches:");
        int n = 7, k = 2;
        System.out.println("Input: n=" + n + ", k=" + k);
        System.out.println("Memoization: " + countWaysMemoization(n, k));
        System.out.println("Tabulation: " + countWaysTabulation(n, k));
        System.out.println("Space Optimized: " + countWaysSpaceOptimized(n, k));

        // Test Case 5: Large input
        System.out.println("\nLarge Input Test:");
        n = 10; k = 5;
        System.out.println("Input: n=" + n + ", k=" + k);
        System.out.println("Space Optimized: " + countWaysSpaceOptimized(n, k));
    }
}
