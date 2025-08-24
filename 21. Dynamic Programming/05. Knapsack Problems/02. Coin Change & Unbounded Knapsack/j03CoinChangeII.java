/**
 * LeetCode 518. Coin Change II
 * 
 * Problem Statement:
 *     You are given an integer array coins representing coins of different 
 *     denominations and an integer amount representing a total amount of 
 *     money. Return the number of combinations that make up that amount. 
 *     If that amount of money cannot be made up by any combination of the 
 *     coins, return 0. You may assume that you have an infinite number of 
 *     each kind of coin. The answer is guaranteed to fit into a signed 
 *     32-bit integer.
 * 
 * Input:
 *     - amount (int): Target amount to make, 0 <= amount <= 5000
 *     - coins (int[]): Array of coin denominations, 1 <= coins.length <= 300
 * 
 * Output:
 *     - int: Number of different combinations that make up the amount
 * 
 * Example:
 *     Input: amount = 5, coins = [1,2,5]
 *     Output: 4
 * 
 *     Explanation:
 *     There are four ways to make up the amount 5:
 *     - 5 = 5
 *     - 5 = 2 + 2 + 1
 *     - 5 = 2 + 1 + 1 + 1
 *     - 5 = 1 + 1 + 1 + 1 + 1
 * 
 *     Input: amount = 3, coins = [2]
 *     Output: 0
 * 
 *     Explanation:
 *     The amount of 3 cannot be made up just using coins of 2.
 */

import java.util.*;

public class j03CoinChangeII {

    /**
     * Approach: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - Use 2D DP where dp[amount][coinIndex] represents number of ways to 
     *   make the amount using coins from coinIndex onwards
     * - For each coin at current index, try using it and recursively solve 
     *   for remaining amount
     * - Use coinIndex to avoid counting duplicate combinations (order matters)
     * - Sum up all possible ways from current coin onwards
     * 
     * Explanation:
     * - Step 1: Initialize 2D dp array with -1 to mark unvisited states
     * - Step 2: Use recursive function with amount and coinIndex as parameters
     * - Step 3: For current coinIndex, try using current coin and recurse
     * - Step 4: Sum up all possible combinations from current coin onwards
     * - Step 5: Store result in dp array to avoid recalculation
     * - Step 6: Return total number of combinations
     * 
     * Time Complexity: O(amount * len(coins)) - Each state calculated once
     * Space Complexity: O(amount * len(coins)) - For the 2D dp array
     * 
     * @param amount   Target amount to make (0 <= amount <= 5000)
     * @param coins    Array of coin denominations (1 <= len <= 300)
     * @return         Number of different combinations that make up the amount
     */
    public static int change(int amount, int[] coins) {
        // Initialize 2D dp array with -1 to mark unvisited states
        int[][] dp = new int[amount + 1][coins.length + 1];
        for (int i = 0; i <= amount; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // Call recursive function starting from coinIndex 0
        return numberOfCombinations(coins, dp, amount, 0);
    }

    /**
     * Helper Method: Recursive function with memoization
     * 
     * Intuition:
     * - Recursively try using coins from current index onwards
     * - Use coinIndex to ensure we don't count duplicate combinations
     * - For each coin, try using it and recursively solve for remaining amount
     * - Sum up all possible combinations from current coin onwards
     * 
     * Explanation:
     * - Step 1: Check base cases (amount = 0, amount < 0)
     * - Step 2: Check if result already calculated (memoization)
     * - Step 3: Try using each coin from current index onwards
     * - Step 4: Recursively solve for remaining amount with same coin index
     * - Step 5: Sum up all possible combinations and store result
     * 
     * Time Complexity: O(amount * len(coins))
     * Space Complexity: O(amount * len(coins)) - For 2D dp array and recursion stack
     * 
     * @param coins      Array of coin denominations
     * @param dp         2D memoization array to store results
     * @param amount     Current amount to make
     * @param coinIndex  Current coin index to consider
     * @return           Number of combinations for current state
     */
    public static int numberOfCombinations(int[] coins, int[][] dp, int amount, int coinIndex) {
        // Base case: amount = 0 means we found a valid combination
        if (amount == 0) {
            return 1;
        }
        
        // Base case: amount < 0 means invalid combination
        if (amount < 0) {
            return 0;
        }
        
        // Return cached result if already calculated
        if (dp[amount][coinIndex] != -1) {
            return dp[amount][coinIndex];
        }
        
        // Initialize total ways to 0
        int totalWays = 0;
        
        // Try using each coin from current index onwards
        for (int i = coinIndex; i < coins.length; i++) {
            // Recursively calculate combinations for remaining amount
            // Use same coin index to avoid counting duplicates
            totalWays += numberOfCombinations(coins, dp, amount - coins[i], i);
        }
        
        // Store result in dp array and return
        return dp[amount][coinIndex] = totalWays;
    }

    /**
     * Approach 2: Dynamic Programming with Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Use 2D DP where dp[amount][coinIndex] represents number of ways to 
     *   make the amount using coins from index 0 to coinIndex
     * - Build solution iteratively from smaller amounts and fewer coins
     * - For each amount and coin index, either exclude current coin or include it
     * - This approach avoids recursion and builds solution systematically
     * 
     * Explanation:
     * - Step 1: Handle base case (amount = 0 returns 1)
     * - Step 2: Initialize 2D dp array with base cases (dp[0][j] = 1)
     * - Step 3: Iterate through each amount and coin index
     * - Step 4: For each state, either exclude current coin or include it
     * - Step 5: Sum up ways from both choices and store in dp array
     * - Step 6: Return final result dp[amount][n]
     * 
     * Time Complexity: O(amount * len(coins)) - Each state calculated once
     * Space Complexity: O(amount * len(coins)) - For the 2D dp array
     * 
     * @param amount   Target amount to make (0 <= amount <= 5000)
     * @param coins    Array of coin denominations (1 <= len <= 300)
     * @return         Number of different combinations that make up the amount
     */
    public static int changeTabulation(int amount, int[] coins) {
        // Base case: amount = 0 has exactly one way (empty combination)
        if (amount == 0) {
            return 1;
        }

        // Get number of coins
        int n = coins.length;
        
        // Initialize 2D dp array: dp[amount][coinIndex]
        int[][] dp = new int[amount + 1][n + 1];

        // Base case: for amount 0, there's exactly one way with any number of coins
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }

        // Build solution iteratively for each amount and coin index
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= n; j++) {
                // Option 1: Exclude current coin (use coins from 0 to j-1)
                dp[i][j] = dp[i][j - 1];
                
                // Option 2: Include current coin if possible
                if (i - coins[j - 1] >= 0) {
                    // Add ways from remaining amount using coins from 0 to j
                    dp[i][j] += dp[i - coins[j - 1]][j];
                }
            }
        }

        // Return total combinations using all coins for target amount
        return dp[amount][n];
    }

    /**
     * Approach 3: 1D Dynamic Programming with Tabulation (Optimized)
     * 
     * Intuition:
     * - Use 1D DP where dp[amount] represents number of ways to make the amount
     * - Process coins one by one, updating all possible amounts for each coin
     * - For each coin, iterate through all amounts from coin value to target
     * - This approach is more space-efficient and avoids duplicate counting
     * - The order of processing coins ensures combinations are counted correctly
     * 
     * Explanation:
     * - Step 1: Initialize 1D dp array with dp[0] = 1 as base case
     * - Step 2: For each coin, iterate through amounts from coin to target
     * - Step 3: For each amount, add ways from remaining amount (dp[i-coin])
     * - Step 4: This ensures each coin is processed completely before moving to next
     * - Step 5: Return final result dp[amount]
     * 
     * Time Complexity: O(amount * len(coins)) - Each amount updated for each coin
     * Space Complexity: O(amount) - For the 1D dp array only
     * 
     * @param amount   Target amount to make (0 <= amount <= 5000)
     * @param coins    Array of coin denominations (1 <= len <= 300)
     * @return         Number of different combinations that make up the amount
     */
    public static int change1DTabulation(int amount, int[] coins) {
        // Initialize 1D dp array with dp[0] = 1 as base case
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        // Process each coin one by one
        for (int coin : coins) {
            // For each coin, update all amounts from coin value to target
            for (int i = coin; i <= amount; i++) {
                // Add ways from remaining amount to current amount
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        
        // Return total combinations for target amount
        return dp[amount];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: amount=5, coins=[1,2,5]");
        System.out.println("Approach 1 (2D Memoization): Expected: 4, Output: " + 
                          change(5, new int[]{1,2,5}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 4, Output: " + 
                          changeTabulation(5, new int[]{1,2,5}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 4, Output: " + 
                          change1DTabulation(5, new int[]{1,2,5}));
        
        System.out.println("\nInput: amount=3, coins=[2]");
        System.out.println("Approach 1 (2D Memoization): Expected: 0, Output: " + 
                          change(3, new int[]{2}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 0, Output: " + 
                          changeTabulation(3, new int[]{2}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 0, Output: " + 
                          change1DTabulation(3, new int[]{2}));
        
        System.out.println("\nInput: amount=10, coins=[10]");
        System.out.println("Approach 1 (2D Memoization): Expected: 1, Output: " + 
                          change(10, new int[]{10}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 1, Output: " + 
                          changeTabulation(10, new int[]{10}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 1, Output: " + 
                          change1DTabulation(10, new int[]{10}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: amount=0, coins=[1,2,5]");
        System.out.println("Approach 1 (2D Memoization): Expected: 1, Output: " + 
                          change(0, new int[]{1,2,5}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 1, Output: " + 
                          changeTabulation(0, new int[]{1,2,5}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 1, Output: " + 
                          change1DTabulation(0, new int[]{1,2,5}));
        
        System.out.println("\nInput: amount=5, coins=[]");
        System.out.println("Approach 1 (2D Memoization): Expected: 0, Output: " + 
                          change(5, new int[]{}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 0, Output: " + 
                          changeTabulation(5, new int[]{}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 0, Output: " + 
                          change1DTabulation(5, new int[]{}));
        
        System.out.println("\nInput: amount=1, coins=[1]");
        System.out.println("Approach 1 (2D Memoization): Expected: 1, Output: " + 
                          change(1, new int[]{1}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 1, Output: " + 
                          changeTabulation(1, new int[]{1}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 1, Output: " + 
                          change1DTabulation(1, new int[]{1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: amount=5000, coins=[1]");
        System.out.println("Approach 1 (2D Memoization): Expected: 1, Output: " + 
                          change(5000, new int[]{1}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 1, Output: " + 
                          changeTabulation(5000, new int[]{1}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 1, Output: " + 
                          change1DTabulation(5000, new int[]{1}));
        
        System.out.println("\nInput: amount=100, coins=[1,2,5,10]");
        System.out.println("Approach 1 (2D Memoization): Expected: 2156, Output: " + 
                          change(100, new int[]{1,2,5,10}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 2156, Output: " + 
                          changeTabulation(100, new int[]{1,2,5,10}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 2156, Output: " + 
                          change1DTabulation(100, new int[]{1,2,5,10}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: amount=4, coins=[1,2,3]");
        System.out.println("Approach 1 (2D Memoization): Expected: 4, Output: " + 
                          change(4, new int[]{1,2,3}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 4, Output: " + 
                          changeTabulation(4, new int[]{1,2,3}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 4, Output: " + 
                          change1DTabulation(4, new int[]{1,2,3}));
        
        System.out.println("\nInput: amount=6, coins=[1,2,3]");
        System.out.println("Approach 1 (2D Memoization): Expected: 7, Output: " + 
                          change(6, new int[]{1,2,3}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 7, Output: " + 
                          changeTabulation(6, new int[]{1,2,3}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 7, Output: " + 
                          change1DTabulation(6, new int[]{1,2,3}));
        
        System.out.println("\nInput: amount=8, coins=[2,3,5]");
        System.out.println("Approach 1 (2D Memoization): Expected: 3, Output: " + 
                          change(8, new int[]{2,3,5}));
        System.out.println("Approach 2 (2D Tabulation): Expected: 3, Output: " + 
                          changeTabulation(8, new int[]{2,3,5}));
        System.out.println("Approach 3 (1D Tabulation): Expected: 3, Output: " + 
                          change1DTabulation(8, new int[]{2,3,5}));
    }
}
