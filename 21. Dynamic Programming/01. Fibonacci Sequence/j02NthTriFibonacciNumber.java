/**
 * LeetCode 1137. N-th Tribonacci Number
 * 
 * Problem Statement:
 *     The Tribonacci sequence Tn is defined as: T(n) = T(n-1) + T(n-2) + T(n-3)
 *     with T(0) = 0, T(1) = 1, and T(2) = 1.
 *     Given n, return the value of T(n).
 * 
 * Input:
 *     - n: Integer (0 ≤ n ≤ 37)
 * 
 * Output:
 *     - int: The nth Tribonacci number
 * 
 * Example:
 *     Input: n = 4
 *     Output: 4
 * 
 *     Explanation:
 *     T(4) = T(3) + T(2) + T(1)
 *     T(3) = T(2) + T(1) + T(0) = 1 + 1 + 0 = 2
 *     T(2) = 1, T(1) = 1, T(0) = 0
 *     Therefore, T(4) = 2 + 1 + 1 = 4
 */

import java.util.Arrays;

public class j02NthTriFibonacciNumber {
    /**
     * Approach 1: Dynamic Programming (Memoization)
     * 
     * Intuition:
     * - Store computed Tribonacci numbers to avoid recalculation
     * - Use top-down approach with recursion
     * 
     * Explanation:
     * - Step 1: Create DP array to store computed values
     * - Step 2: Use recursive function with memoization
     * - Step 3: Handle base cases (n ≤ 2)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for DP array and recursion stack
     * 
     * @param n Integer (0 ≤ n ≤ 37)
     * @return The nth Tribonacci number
     */
    public static int tribonacci(int n) {
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
     * - Step 1: Handle base cases (n ≤ 2)
     * - Step 2: Return if value already computed
     * - Step 3: Compute sum of previous three numbers
     * 
     * @param n  Current number to compute
     * @param dp Memoization array
     * @return   Tribonacci number at position n
     */
    private static int fibNumber(int n, int[] dp) {
        // Base cases
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        
        // Return if already computed
        if (dp[n] != -1)
            return dp[n];
        
        // Compute and store new value
        return dp[n] = fibNumber(n - 1, dp) + 
                fibNumber(n - 2, dp) + 
                fibNumber(n - 3, dp);
    }

    /**
     * Approach 2: Dynamic Programming (Tabulation)
     * 
     * Intuition:
     * - Build solution bottom-up to avoid recursion
     * - Keep track of last three numbers
     * 
     * Explanation:
     * - Step 1: Handle base cases (n ≤ 2)
     * - Step 2: Use three variables for last three numbers
     * - Step 3: Iteratively compute next number
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int tribonacciTabulation(int n) {
        // Handle base cases
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        
        // Initialize DP array with base cases
        int[] dp = new int[n + 1];
        dp[0] = 0; // T(0)
        dp[1] = 1; // T(1)
        dp[2] = 1; // T(2)
        
        // Fill array iteratively
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n]; // Return the nth Tribonacci number
    }

    public static void main(String[] args) {
        // Test Case 1: Base cases
        System.out.println("\nBase Cases:");
        System.out.println("T(0) = " + tribonacci(0));
        System.out.println("T(1) = " + tribonacci(1));
        System.out.println("T(2) = " + tribonacci(2));
        
        // Test Case 2: Small numbers
        System.out.println("\nSmall Numbers:");
        System.out.println("T(4) = " + tribonacci(4));
        System.out.println("T(5) = " + tribonacci(5));
        
        // Test Case 3: Larger numbers
        System.out.println("\nLarger Numbers:");
        System.out.println("T(20) = " + tribonacci(20));
        System.out.println("T(37) = " + tribonacci(37));
        
        // Compare with Tabulation
        System.out.println("\nTabulation Results:");
        System.out.println("T(20) = " + tribonacciTabulation(20));
        System.out.println("T(37) = " + tribonacciTabulation(37));
    }
}