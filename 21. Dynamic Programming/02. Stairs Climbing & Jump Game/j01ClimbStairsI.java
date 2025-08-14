/**
 * LeetCode 70. Climbing Stairs
 * 
 * Problem Statement:
 *     You are climbing a staircase. It takes n steps to reach the top. Each time 
 *     you can either climb 1 or 2 steps. In how many distinct ways can you climb 
 *     to the top?
 * 
 * Input:
 *     - n (1 <= n <= 45): Number of steps to reach the top
 * 
 * Output:
 *     - Return the number of distinct ways to climb to the top
 * 
 * Example:
 *     Input: n = 2
 *     Output: 2
 * 
 *     Explanation:
 *     There are two ways to climb to the top:
 *     1. 1 step + 1 step
 *     2. 2 steps
 * 
 *     Input: n = 3
 *     Output: 3
 * 
 *     Explanation:
 *     There are three ways to climb to the top:
 *     1. 1 step + 1 step + 1 step
 *     2. 1 step + 2 steps
 *     3. 2 steps + 1 step
 */

import java.util.Arrays;

public class j01ClimbStairsI {

    /**
     * Approach: Dynamic Programming with Memoization
     * 
     * Intuition:
     * - This is a classic Fibonacci sequence problem in disguise
     * - To reach step n, we can come from step (n-1) by taking 1 step OR from 
     *   step (n-2) by taking 2 steps
     * - Therefore, ways(n) = ways(n-1) + ways(n-2)
     * - Base cases: ways(1) = 1, ways(2) = 2
     * - We use memoization to avoid recalculating subproblems
     * 
     * Explanation:
     * - Step 1: Initialize a DP array with size n+1 to store results for each step
     * - Step 2: Fill array with -1 to mark uncomputed values
     * - Step 3: Set base cases: dp[0] = 0, dp[1] = 1, dp[2] = 2
     * - Step 4: Use recursive helper with memoization to compute ways(n)
     * - Step 5: For each recursive call, check if result is already computed
     * - Step 6: If not computed, recursively calculate ways(n-1) + ways(n-2)
     * 
     * Time Complexity: O(n) - Each step is computed only once due to memoization
     * Space Complexity: O(n) - DP array to store results for each step
     * 
     * @param n    Number of steps to climb (1 <= n <= 45)
     * @return     Number of distinct ways to climb to the top
     */
    public static int climbStairs(int n) {
        // Handle edge case for invalid input
        if (n <= 0) return 0;
        
        // Initialize DP array with size n+1 to store results for steps 0 to n
        int[] dp = new int[n + 1];
        
        // Fill array with -1 to mark uncomputed values
        Arrays.fill(dp, -1);
        
        // Set base cases for the problem
        dp[0] = 0;  // No ways to climb 0 steps
        dp[1] = 1;  // Only 1 way to climb 1 step
        
        // Set base case for 2 steps if n >= 2
        if (n > 1) {
            dp[2] = 2;  // 2 ways: (1+1) or (2)
        }

        // Call recursive helper with memoization
        return climbStairsDP(n, dp);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - This helper method implements the recursive solution. with memoization
     * - It checks if the result for current step is already computed
     * - If not, it recursively calculates by adding ways from previous steps
     * - The result is stored in DP array to avoid recalculation
     * 
     * Explanation:
     * - Step 1: Check if n <= 0 (invalid case)
     * - Step 2: Check if result for step n is already computed (dp[n] != -1)
     * - Step 3: If computed, return the stored result
     * - Step 4: If not computed, recursively calculate ways(n-1) + ways(n-2)
     * - Step 5: Store the result in dp[n] and return it
     * 
     * Time Complexity: O(1) per call due to memoization
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param n    Current step number to compute ways for
     * @param dp   DP array to store computed results
     * @return     Number of ways to reach step n
     */
    private static int climbStairsDP(int n, int[] dp) {
        // Handle base case for invalid step number
        if (n <= 0) {
            return 0;
        }
        
        // Return precomputed result if available (memoization check)
        if (dp[n] != -1) {
            return dp[n];
        }
        
        // Recursively calculate ways by adding ways from previous steps
        // ways(n) = ways(n-1) + ways(n-2)
        return dp[n] = climbStairsDP(n - 1, dp) + climbStairsDP(n - 2, dp);
    }

    /*-
     * Approach 2: Dynamic Programming with Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Instead of using recursion with memoization, we use iterative approach
     * - We build the solution from bottom-up, starting from base cases
     * - Each step i is computed using previously computed values (i-1) and (i-2)
     * - This eliminates the overhead of recursive function calls
     * - The solution follows the same Fibonacci pattern: ways(i) = ways(i-1) + ways(i-2)
     * 
     * Explanation:
     * - Step 1: Initialize DP array with size n+1 to store results for each step
     * - Step 2: Fill array with -1 to mark uncomputed values (though not used in tabulation)
     * - Step 3: Set base cases: dp[0] = 0, dp[1] = 1, dp[2] = 2
     * - Step 4: Iterate from step 3 to n, computing each step using previous values
     * - Step 5: For each step i, compute dp[i] = dp[i-1] + dp[i-2]
     * - Step 6: Return the final result dp[n]
     * 
     * Time Complexity: O(n) - Single pass through the array from 3 to n
     * Space Complexity: O(n) - DP array to store results for each step
     * 
     * @param n    Number of steps to climb (1 <= n <= 45)
     * @return     Number of distinct ways to climb to the top
     */
    public static int climbStairsTabulation(int n) {
        // Handle edge case for invalid input
        if (n <= 0) return 0;
        
        // Initialize DP array with size n+1 to store results for steps 0 to n
        int[] dp = new int[n + 1];
        
        // Fill array with -1 (though not strictly necessary for tabulation)
        Arrays.fill(dp, -1);
        
        // Set base cases for the problem
        dp[0] = 0;  // No ways to climb 0 steps
        dp[1] = 1;  // Only 1 way to climb 1 step
        
        // Set base case for 2 steps if n >= 2
        if (n > 1) {
            dp[2] = 2;  // 2 ways: (1+1) or (2)
        }

        // Iteratively compute ways for each step from 3 to n
        // Each step uses previously computed values (bottom-up approach)
        for (int i = 3; i <= n; i++) {
            // ways(i) = ways(i-1) + ways(i-2) - Fibonacci pattern
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return the final result for n steps
        return dp[n];
    }

    public static void main(String[] args) {
        
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: n=1, Expected: 1, Output: " + climbStairs(1));
        System.out.println("Input: n=2, Expected: 2, Output: " + climbStairs(2));
        System.out.println("Input: n=3, Expected: 3, Output: " + climbStairs(3));
        System.out.println("Input: n=4, Expected: 5, Output: " + climbStairs(4));
        System.out.println("Input: n=5, Expected: 8, Output: " + climbStairs(5));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=0, Expected: 0, Output: " + climbStairs(0));
        System.out.println("Input: n=-1, Expected: 0, Output: " + climbStairs(-1));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=10, Expected: 89, Output: " + climbStairs(10));
        System.out.println("Input: n=20, Expected: 10946, Output: " + climbStairs(20));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=6, Expected: 13, Output: " + climbStairs(6));
        System.out.println("Input: n=7, Expected: 21, Output: " + climbStairs(7));
        System.out.println("Input: n=8, Expected: 34, Output: " + climbStairs(8));
    }
}
