/**
 * LeetCode 509. Fibonacci Number
 * 
 * Problem Statement:
 *     Given n, calculate F(n), where F(n) is the nth Fibonacci number.
 *     The Fibonacci sequence is defined as: F(n) = F(n-1) + F(n-2)
 *     with F(0) = 0 and F(1) = 1
 * 
 * Input:
 *     - n: Integer (0 ≤ n ≤ 30)
 * 
 * Output:
 *     - int: The nth Fibonacci number
 * 
 * Example:
 *     Input: n = 4
 *     Output: 3
 * 
 *     Explanation:
 *     F(4) = F(3) + F(2)
 *     F(3) = F(2) + F(1)
 *     F(2) = F(1) + F(0)
 *     F(1) = 1, F(0) = 0
 *     Therefore, F(4) = 3
 */

import java.util.Arrays;

public class j01FibonacciNumber {
    /**
     * Approach 1: Dynamic Programming (Memoization)
     * 
     * Intuition:
     * - Store computed Fibonacci numbers to avoid recalculation
     * - Use top-down approach with recursion
     * 
     * Explanation:
     * - Step 1: Create DP array to store computed values
     * - Step 2: Use recursive function with memoization
     * - Step 3: Check DP array before computation
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for DP array and recursion stack
     * 
     * @param n Integer (0 ≤ n ≤ 30)
     * @return The nth Fibonacci number
     */
    public static int fibDynamicProgramming(int n) {
        // Initialize DP array with -1 (uncomputed values)
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return fibNumber(n, dp);
    }

    /**
     * Helper Method: Recursive Memoization
     * 
     * Intuition:
     * - Check DP array before computing
     * - Store result after computation
     * 
     * Explanation:
     * - Step 1: Handle base cases (n ≤ 1)
     * - Step 2: Return if value already computed
     * - Step 3: Compute and store new value
     * 
     * @param n  Current number to compute
     * @param dp Memoization array
     * @return   Fibonacci number at position n
     */
    private static int fibNumber(int n, int[] dp) {
        // Base cases
        if(n <= 0) return 0;
        if(n == 1) return 1;
            
        // Return if already computed
        if (dp[n] != -1)
            return dp[n];
            
        // Compute and store new value
        dp[n] = fibNumber(n - 1, dp) + fibNumber(n - 2, dp);
        return dp[n];
    }

    /**
     * Approach 2: Dynamic Programming (Tabulation)
     * 
     * Intuition:
     * - Build solution bottom-up to avoid recursion
     * - Use array to store intermediate results
     * 
     * Explanation:
     * - Step 1: Create DP array with base cases
     * - Step 2: Fill array iteratively
     * - Step 3: Each position is sum of previous two
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param n Integer (0 ≤ n ≤ 30)
     * @return The nth Fibonacci number
     */
    public static int fibTabulation(int n) {
        // Handle base cases
        if (n <= 1)
            return n;
            
        // Initialize DP array with base cases
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        // Fill DP array iteratively
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        // Test Case 1: Base cases
        System.out.println("\nBase Cases:");
        System.out.println("F(0) = " + fibDynamicProgramming(0));
        System.out.println("F(1) = " + fibDynamicProgramming(1));
        
        // Test Case 2: Small numbers
        System.out.println("\nSmall Numbers:");
        System.out.println("F(5) = " + fibDynamicProgramming(5));
        System.out.println("F(7) = " + fibDynamicProgramming(7));
        
        // Test Case 3: Larger numbers
        System.out.println("\nLarger Numbers:");
        System.out.println("F(20) = " + fibDynamicProgramming(20));
        System.out.println("F(30) = " + fibDynamicProgramming(30));
        
        // Compare with Tabulation
        System.out.println("\nTabulation Results:");
        System.out.println("F(20) = " + fibTabulation(20));
        System.out.println("F(30) = " + fibTabulation(30));
    }
}