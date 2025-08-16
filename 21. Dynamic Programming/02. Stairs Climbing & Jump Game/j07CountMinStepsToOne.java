/**
 * Count Minimum Steps to One
 * 
 * Problem Statement:
 *     Given a positive integer N, find the minimum number of steps to reduce it to 1. 
 *     In each step, you can perform one of the following operations:
 *     1. If N is divisible by 2, divide N by 2
 *     2. If N is divisible by 3, divide N by 3
 *     3. Subtract 1 from N
 *     Return the minimum number of steps required.
 * 
 * Input:
 *     - N: int (1 <= N <= 10^6)
 * 
 * Output:
 *     - The minimum number of steps to reduce N to 1
 * 
 * Example:
 *     Input: N = 10
 *     Output: 3
 * 
 *     Explanation:
 *     Step 1: 10 - 1 = 9
 *     Step 2: 9 / 3 = 3
 *     Step 3: 3 / 3 = 1
 *     Total steps: 3
 * 
 *     Input: N = 6
 *     Output: 2
 * 
 *     Explanation:
 *     Step 1: 6 / 2 = 3
 *     Step 2: 3 / 3 = 1
 *     Total steps: 2
 */

import java.util.Arrays;

public class j07CountMinStepsToOne {

    /**
     * Approach: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - From any number n, we can perform three operations: divide by 2, divide by 3, or subtract 1
     * - We need to find the minimum steps among all possible operations
     * - Let minSteps(n) denote the minimum steps to reduce n to 1
     * - Then: minSteps(n) = 1 + min(minSteps(n/2), minSteps(n/3), minSteps(n-1))
     * - Base case: minSteps(1) = 0 (already at target)
     * - We use memoization to avoid recalculating overlapping subproblems
     * 
     * Explanation:
     * - Step 1: Initialize a DP array with -1 to mark uncomputed states
     * - Step 2: Recursively compute minimum steps starting from N
     * - Step 3: For each number, try all valid operations (divide by 2, 3, or subtract 1)
     * - Step 4: Find the minimum steps among all valid operations
     * - Step 5: Add 1 for the current operation and store result
     * 
     * Time Complexity: O(n) - Each number is computed only once due to memoization
     * Space Complexity: O(n) - DP array and recursion stack depth
     * 
     * @param N    Positive integer to reduce to 1
     * @return     Minimum number of steps to reduce N to 1
     */
    public static int minSteps(int N) {
        // Handle edge case for invalid input
        if (N <= 0) return -1; // Invalid input
        if (N == 1) return 0;  // Already at target
        
        // Initialize DP array with -1 to mark uncomputed states
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);

        // Compute minimum steps starting from N
        return countMinSteps(N, dp);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - From current number n, try all valid operations
     * - Find the minimum steps among all valid operations
     * - Add 1 for the current operation
     * - Use memoization to cache computed results
     * 
     * Explanation:
     * - Step 1: Check if we've reached the target (base case)
     * - Step 2: Check if result is already computed (memoization)
     * - Step 3: Try dividing by 2 if n is divisible by 2
     * - Step 4: Try dividing by 3 if n is divisible by 3
     * - Step 5: Always try subtracting 1
     * - Step 6: Find minimum among all valid operations and add 1
     * 
     * Time Complexity: O(1) per call due to memoization
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param n     Current number to reduce
     * @param dp    Memoization array storing minimum steps for each number
     * @return      Minimum steps to reduce n to 1
     */
    public static int countMinSteps(int n, int[] dp) {
        // Base case: if we've reached 1, no more steps needed
        if (n == 1) {
            return 0;
        }
        
        // Return memoized result if already computed
        if (dp[n] != -1) {
            return dp[n];
        }

        // Initialize minimum steps to maximum possible value
        int ans = Integer.MAX_VALUE;

        // Try dividing by 2 if n is divisible by 2
        if (n % 2 == 0) {
            ans = Math.min(ans, countMinSteps(n / 2, dp));
        }

        // Try dividing by 3 if n is divisible by 3
        if (n % 3 == 0) {
            ans = Math.min(ans, countMinSteps(n / 3, dp));
        }

        // Always try subtracting 1
        ans = Math.min(ans, countMinSteps(n - 1, dp));

        // Add 1 for the current operation and store result
        dp[n] = ans + 1;
        return dp[n];
    }

    /**
     * Approach 2: Dynamic Programming with Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Instead of using recursion with memoization, we use iterative approach
     * - We build the solution from bottom-up, starting from base cases
     * - Each number i is computed using previously computed values from i/2, i/3, and i-1
     * - This eliminates the overhead of recursive function calls
     * - We iterate from 4 to N, ensuring all dependencies are computed before we need them
     * 
     * Explanation:
     * - Step 1: Handle base cases for N=1, N=2, N=3 directly
     * - Step 2: Initialize DP array with size N+1 and fill with -1
     * - Step 3: Set base cases: dp[1] = 0, dp[2] = 1, dp[3] = 1
     * - Step 4: Iterate from 4 to N, computing each number using previous values
     * - Step 5: For each number i, try all valid operations and find minimum
     * - Step 6: Return the final result dp[N]
     * 
     * Time Complexity: O(n) - Single pass through the array from 4 to N
     * Space Complexity: O(n) - DP array to store results for each number
     * 
     * @param N    Positive integer to reduce to 1
     * @return     Minimum number of steps to reduce N to 1
     */
    public static int minStepsTabulation(int N) {
        // Handle base cases directly
        if (N == 1) return 0;  // Already at target
        if (N == 2 || N == 3) return 1;  // One operation needed
        
        // Initialize DP array with size N+1 to store results for numbers 0 to N
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);
        
        // Set base cases for the problem
        dp[1] = 0;  // No steps needed to reach 1 from 1
        dp[2] = 1;  // One step: 2 -> 1 (subtract 1)
        dp[3] = 1;  // One step: 3 -> 1 (divide by 3)
        
        // Iteratively compute minimum steps for each number from 4 to N
        // Each number uses previously computed values (bottom-up approach)
        for (int i = 4; i <= N; i++) {
            int ans = Integer.MAX_VALUE; // Initialize to maximum possible value
            
            // Try dividing by 2 if i is divisible by 2
            if (i % 2 == 0) {
                ans = Math.min(ans, dp[i / 2]);
            }
            
            // Try dividing by 3 if i is divisible by 3
            if (i % 3 == 0) {
                ans = Math.min(ans, dp[i / 3]);
            }
            
            // Always try subtracting 1
            ans = Math.min(ans, dp[i - 1]);
            
            // Add 1 for the current operation and store result
            dp[i] = ans + 1;
        }
        
        // Return the minimum steps needed to reduce N to 1
        return dp[N];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: N=10, Expected: 3, Output: " + minSteps(10));
        System.out.println("Input: N=6, Expected: 2, Output: " + minSteps(6));
        System.out.println("Input: N=15, Expected: 3, Output: " + minSteps(15));
        System.out.println("Input: N=20, Expected: 4, Output: " + minSteps(20));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: N=1, Expected: 0, Output: " + minSteps(1));
        System.out.println("Input: N=2, Expected: 1, Output: " + minSteps(2));
        System.out.println("Input: N=3, Expected: 1, Output: " + minSteps(3));
        System.out.println("Input: N=0, Expected: -1, Output: " + minSteps(0));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: N=100, Expected: 7, Output: " + minSteps(100));
        System.out.println("Input: N=1000, Expected: 10, Output: " + minSteps(1000));
        System.out.println("Input: N=999, Expected: 10, Output: " + minSteps(999));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: N=7, Expected: 3, Output: " + minSteps(7));
        System.out.println("Input: N=11, Expected: 4, Output: " + minSteps(11));
        System.out.println("Input: N=27, Expected: 3, Output: " + minSteps(27));
        System.out.println("Input: N=64, Expected: 6, Output: " + minSteps(64));
        System.out.println("Input: N=81, Expected: 4, Output: " + minSteps(81));
    }
}
