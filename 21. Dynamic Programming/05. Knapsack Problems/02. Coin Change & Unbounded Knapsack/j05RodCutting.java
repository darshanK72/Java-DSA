/**
 * GeeksForGeeks - Rod Cutting Problem
 * 
 * Problem Statement:
 *     Given a rod of length n inches and an array of prices that contains 
 *     prices of all pieces of size smaller than n. Determine the maximum 
 *     value obtainable by cutting up the rod and selling the pieces.
 * 
 * Input:
 *     - price[] (int[]): Array of prices for pieces of different lengths
 *     - price[i] represents the price of a piece of length (i+1) inches
 *     - Array length n represents the maximum rod length (n >= 0)
 * 
 * Output:
 *     - Maximum value obtainable by cutting the rod optimally
 * 
 * Example:
 *     Input: price[] = {1, 5, 8, 9, 10, 17, 17, 20}
 *     Output: 22
 * 
 *     Explanation:
 *         Lengths: [1, 2, 3, 4, 5, 6, 7, 8] inches
 *         Prices:  [1, 5, 8, 9, 10, 17, 17, 20]
 *         Optimal solution: Cut into pieces of length 2 and 6
 *         Value: price[1] + price[5] = 5 + 17 = 22
 *         Alternative: Cut into pieces of length 1, 1, and 6
 *         Value: price[0] + price[0] + price[5] = 1 + 1 + 17 = 19
 */

import java.util.Arrays;

public class j05RodCutting {

    /**
     * Approach: Memoized Dynamic Programming (Top-Down)
     * 
     * Intuition:
     * - Use recursive approach with memoization to avoid redundant calculations
     * - For each rod length, try cutting it at all possible positions
     * - Each cut creates two pieces: left piece (sold) and right piece (recursively cut)
     * - Store intermediate results in DP table to optimize performance
     * 
     * Explanation:
     * - Step 1: Initialize DP table with -1 to mark unvisited states
     * - Step 2: For each rod length, try cutting at positions 1 to size
     * - Step 3: For each cut, calculate value of left piece + recursive value of right piece
     * - Step 4: Find maximum value among all possible cuts
     * - Step 5: Store maximum value achievable for current rod length
     * 
     * Time Complexity: O(n²) - each length tries all possible cuts
     * Space Complexity: O(n) for DP table + O(n) for recursion stack
     * 
     * @param price    Array of prices for different rod lengths (price[i] >= 0)
     * @return         Maximum value obtainable by cutting the rod optimally
     */
    public static int cutRod(int[] price) {
        // Validate input parameters
        if (price == null || price.length == 0) {
            return 0;
        }
        
        int n = price.length;
        
        // Initialize DP table with -1 to mark unvisited states
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        
        // Start recursive solution with full rod length
        return curRodMemo(dp, price, n);
    }
    
    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - Recursively explore all possible cutting positions for current rod length
     * - For each cut position, calculate value of cut piece + remaining rod
     * - Use memoization to avoid recalculating same subproblems
     * - Return maximum value achievable from current rod length
     * 
     * Explanation:
     * - Base case: if size <= 0, return 0 (no rod to cut)
     * - If state already calculated, return memoized result
     * - For each possible cut length (1 to size):
     *   * Calculate value of cut piece using price array
     *   * Recursively solve for remaining rod length (size - i)
     *   * Add cut piece value to recursive result
     * - Return maximum value among all possible cuts
     * 
     * Time Complexity: O(n) for each state (tries all cuts)
     * Space Complexity: O(n) for recursion stack
     * 
     * @param dp       DP table for memoization
     * @param price    Array of prices for different rod lengths
     * @param size     Current rod length to cut
     * @return         Maximum value achievable from current rod length
     */
    public static int curRodMemo(int[] dp, int[] price, int size) {
        // Base case: no rod to cut
        if (size <= 0) {
            return 0;
        }
        
        // Return memoized result if already calculated
        if (dp[size] != -1) {
            return dp[size];
        }
        
        // Initialize maximum value for current rod length
        int maxValue = 0;
        
        // Try cutting rod at all possible positions (1 to size)
        for (int i = 1; i <= size; i++) {
            // Calculate value of cut piece (length i) using price array
            int cutValue = price[i - 1];
            
            // Recursively solve for remaining rod length (size - i)
            int remainingValue = curRodMemo(dp, price, size - i);
            
            // Update maximum value achievable
            maxValue = Math.max(maxValue, cutValue + remainingValue);
        }
        
        // Store and return maximum value for current rod length
        return dp[size] = maxValue;
    }

    /**
     * Approach: Tabulation Dynamic Programming (Bottom-Up)
     * 
     * Intuition:
     * - Build solution iteratively from smaller rod lengths to larger ones
     * - For each rod length, try all possible cut positions
     * - Use previously calculated values to build current optimal solution
     * - Fill DP table in bottom-up manner to avoid recursion
     * 
     * Explanation:
     * - Step 1: Initialize DP table with 0 (base case: no rod = 0 value)
     * - Step 2: For each rod length from 1 to n:
     *   * Try cutting at all possible positions (1 to current length)
     *   * For each cut, calculate value = cut piece value + remaining rod value
     *   * Take maximum value among all possible cuts
     * - Step 3: Return optimal value for full rod length
     * 
     * Time Complexity: O(n²) - each length tries all possible cuts
     * Space Complexity: O(n) for DP table
     * 
     * @param price    Array of prices for different rod lengths (price[i] >= 0)
     * @return         Maximum value obtainable by cutting the rod optimally
     */
    public static int cutRodTabulation(int[] price) {
        // Validate input parameters
        if (price == null || price.length == 0) {
            return 0;
        }
        
        int n = price.length;
        
        // Initialize DP table with 0 (base case: no rod = 0 value)
        int[] dp = new int[n + 1];
        
        // Build solution for each rod length from 1 to n
        for (int size = 1; size <= n; size++) {
            // Try cutting rod at all possible positions (1 to size)
            for (int i = 1; i <= size; i++) {
                // Calculate value of current cut: cut piece + remaining rod
                int currentValue = dp[size - i] + price[i - 1];
                
                // Take maximum value among all possible cuts for current length
                dp[size] = Math.max(dp[size], currentValue);
            }
        }
        
        return dp[n];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("=== MEMOIZATION APPROACH ===");
        System.out.println("Basic Test Cases:");
        System.out.println("Test 1: price=[1, 5, 8, 9, 10, 17, 17, 20]");
        System.out.println("Expected: 22, Output: " + cutRod(new int[]{1, 5, 8, 9, 10, 17, 17, 20}));
        
        System.out.println("\nTest 2: price=[1, 2, 3, 4, 5, 6, 7, 8]");
        System.out.println("Expected: 8, Output: " + cutRodTabulation(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));

        System.out.println("\n=== TABULATION APPROACH ===");
        System.out.println("Basic Test Cases:");
        System.out.println("Test 1: price=[1, 5, 8, 9, 10, 17, 17, 20]");
        System.out.println("Expected: 22, Output: " + cutRod(new int[]{1, 5, 8, 9, 10, 17, 17, 20}));
        
        System.out.println("\nTest 2: price=[1, 2, 3, 4, 5, 6, 7, 8]");
        System.out.println("Expected: 8, Output: " + cutRodTabulation(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Test 3 - Empty array:");
        System.out.println("Expected: 0, Output: " + cutRod(new int[]{}));
        
        System.out.println("\nTest 4 - Single element:");
        System.out.println("Expected: 5, Output: " + cutRodTabulation(new int[]{5}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Test 6 - All same prices:");
        System.out.println("Expected: 8, Output: " + cutRod(new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
        
        System.out.println("\nTest 7 - Increasing prices:");
        System.out.println("Expected: 8, Output: " + cutRodTabulation(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        
        System.out.println("\nTest 8 - Decreasing prices:");
        System.out.println("Expected: 8, Output: " + cutRod(new int[]{8, 7, 6, 5, 4, 3, 2, 1}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Test 9 - Zero prices:");
        System.out.println("Expected: 0, Output: " + cutRod(new int[]{0, 0, 0, 0}));
        
        System.out.println("Test 10 - Large values:");
        System.out.println("Expected: 100, Output: " + cutRodTabulation(new int[]{10, 20, 30, 40, 50, 60, 70, 100}));
        
        System.out.println("Test 11 - Optimal solution uses multiple cuts:");
        System.out.println("Expected: 15, Output: " + cutRodTabulation(new int[]{1, 5, 8, 9, 10, 15}));
    }
}
