/**
 * LeetCode 322. Coin Change
 * 
 * Problem Statement:
 *     You are given an integer array coins representing coins of different 
 *     denominations and an integer amount representing a total amount of 
 *     money. Return the fewest number of coins that you need to make up 
 *     that amount. If that amount of money cannot be made up by any 
 *     combination of the coins, return -1. You may assume that you have 
 *     an infinite number of each kind of coin.
 * 
 * Input:
 *     - coins (int[]): Array of coin denominations, 1 <= coins.length <= 12
 *     - amount (int): Target amount to make, 0 <= amount <= 10^4
 * 
 * Output:
 *     - int: Minimum number of coins needed to make the amount, or -1 if 
 *       impossible
 * 
 * Example:
 *     Input: coins = [1,2,5], amount = 11
 *     Output: 3
 * 
 *     Explanation:
 *     Amount 11 can be made using:
 *     - 5 + 5 + 1 = 11 (3 coins)
 *     - 5 + 2 + 2 + 2 = 11 (4 coins)
 *     - 2 + 2 + 2 + 2 + 2 + 1 = 11 (6 coins)
 *     Minimum is 3 coins.
 * 
 *     Input: coins = [2], amount = 3
 *     Output: -1
 * 
 *     Explanation:
 *     Amount 3 cannot be made using only coin of denomination 2.
 */

import java.util.*;

public class j01CoinChangeI {

    /**
     * Approach 1: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - For each amount, we try all possible coin denominations
     * - Use memoization to avoid recalculating subproblems
     * - The minimum coins for amount = min(coins for amount - coin[i]) + 1
     * - Base case: amount = 0 requires 0 coins, amount < 0 is impossible
     * 
     * Explanation:
     * - Step 1: Initialize dp array with -1 to mark unvisited states
     * - Step 2: Use recursive function with memoization to find minimum coins
     * - Step 3: For each amount, try all coin denominations recursively
     * - Step 4: Store result in dp array to avoid recalculation
     * - Step 5: Return -1 if no solution exists (result = Integer.MAX_VALUE)
     * 
     * Time Complexity: O(amount * len(coins)) - Each amount is calculated once
     * Space Complexity: O(amount) - For the dp array and recursion stack
     * 
     * @param coins    Array of coin denominations (1 <= len <= 12)
     * @param amount   Target amount to make (0 <= amount <= 10^4)
     * @return         Minimum coins needed or -1 if impossible
     */
    public static int coinChange(int[] coins, int amount) {
        // Initialize dp array with -1 to mark unvisited states
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        
        // Call recursive function to find minimum coins
        int result = minCoinsRequiredMemoization(coins, amount, dp);
        
        // Return -1 if no solution exists, otherwise return the result
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    /**
     * Helper Method: Recursive function with memoization
     * 
     * Intuition:
     * - Recursively try all coin denominations for the current amount
     * - Use memoization to store already calculated results
     * - Return minimum coins needed from all possible choices
     * 
     * Explanation:
     * - Step 1: Check base cases (amount = 0, amount < 0)
     * - Step 2: Check if result already calculated (memoization)
     * - Step 3: Try each coin denomination recursively
     * - Step 4: Find minimum coins from all valid choices
     * - Step 5: Store and return result with +1 for current coin
     * 
     * Time Complexity: O(amount * len(coins))
     * Space Complexity: O(amount) - For recursion stack and dp array
     * 
     * @param coins    Array of coin denominations
     * @param amount   Current amount to make
     * @param dp       Memoization array to store results
     * @return         Minimum coins needed for current amount
     */
    public static int minCoinsRequiredMemoization(int[] coins, int amount, int[] dp) {
        // Base case: amount = 0 requires 0 coins
        if (amount == 0) {
            return 0;
        }
        
        // Base case: amount < 0 is impossible, return MAX_VALUE
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        
        // Return cached result if already calculated
        if (dp[amount] != -1) {
            return dp[amount];
        }
        
        // Initialize minimum coins to MAX_VALUE - 1 to avoid overflow
        int minCoins = Integer.MAX_VALUE - 1;
        
        // Try each coin denomination
        for (int i = 0; i < coins.length; i++) {
            // Recursively calculate coins needed for remaining amount
            int coinsNeeded = minCoinsRequiredMemoization(coins, amount - coins[i], dp);
            // Update minimum if this choice gives fewer coins
            minCoins = Math.min(minCoins, coinsNeeded);
        }
        
        // Store result in dp array and return (add 1 for current coin)
        return dp[amount] = minCoins + 1;
    }

    /**
     * Approach 2: 2D Dynamic Programming with Memoization (Index-based)
     * 
     * Intuition:
     * - Use 2D DP where dp[index][amount] represents minimum coins needed
     *   using coins from index onwards to make the amount
     * - For each coin at current index, try using 0 to maximum possible coins
     * - Move to next coin index and recursively solve for remaining amount
     * - This approach gives more control over coin selection order
     * 
     * Explanation:
     * - Step 1: Initialize 2D dp array with -1 to mark unvisited states
     * - Step 2: Use recursive function with index and amount as parameters
     * - Step 3: For current coin, try using 0 to maximum possible coins
     * - Step 4: Recursively solve for remaining amount with next coin index
     * - Step 5: Return minimum coins from all possible combinations
     * 
     * Time Complexity: O(amount * len(coins)) - Each state calculated once
     * Space Complexity: O(len(coins) * amount) - For the 2D dp array
     * 
     * @param coins    Array of coin denominations (1 <= len <= 12)
     * @param amount   Target amount to make (0 <= amount <= 10^4)
     * @return         Minimum coins needed or -1 if impossible
     */
    public static int coinChange2D(int[] coins, int amount) {
        // Initialize 2D dp array with -1 to mark unvisited states
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // Call recursive function starting from index 0
        int result = minCoinsRequired2D(coins, 0, amount, dp);
        
        // Return -1 if no solution exists, otherwise return the result
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    /**
     * Helper Method: 2D recursive function with memoization
     * 
     * Intuition:
     * - Recursively try different quantities of current coin
     * - Move to next coin index and solve for remaining amount
     * - Use 2D memoization to store results for each (index, amount) pair
     * - Return minimum coins needed from all possible combinations
     * 
     * Explanation:
     * - Step 1: Check base cases (amount = 0, index out of bounds)
     * - Step 2: Check if result already calculated (memoization)
     * - Step 3: Try using 0 to maximum possible coins of current denomination
     * - Step 4: Recursively solve for remaining amount with next coin
     * - Step 5: Add current coin count and find minimum from all choices
     * 
     * Time Complexity: O(amount * len(coins))
     * Space Complexity: O(len(coins) * amount) - For 2D dp array and recursion stack
     * 
     * @param coins    Array of coin denominations
     * @param index    Current coin index to consider
     * @param amount   Current amount to make
     * @param dp       2D memoization array to store results
     * @return         Minimum coins needed for current state
     */
    public static int minCoinsRequired2D(int[] coins, int index, int amount, int[][] dp) {
        // Base case: amount = 0 requires 0 coins
        if (amount == 0) {
            return 0;
        }
        
        // Base case: no more coins available, return MAX_VALUE
        if (index == coins.length) {
            return Integer.MAX_VALUE;
        }
        
        // Return cached result if already calculated
        if (dp[index][amount] != -1) {
            return dp[index][amount];
        }
        
        // Initialize minimum coins to MAX_VALUE
        int minCoins = Integer.MAX_VALUE;
        
        // Try using 0 to maximum possible coins of current denomination
        for (int coinCount = 0; amount >= coins[index] * coinCount; coinCount++) {
            // Recursively calculate coins needed for remaining amount with next coin
            int coinsNeeded = minCoinsRequired2D(coins, index + 1, amount - coins[index] * coinCount, dp);
            
            // If solution exists, add current coin count
            if (coinsNeeded < Integer.MAX_VALUE) {
                coinsNeeded += coinCount;
            }
            
            // Update minimum if this choice gives fewer coins
            minCoins = Math.min(minCoins, coinsNeeded);
        }
        
        // Store result in dp array and return
        return dp[index][amount] = minCoins;
    }

    /**
     * Approach 3: Dynamic Programming with Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Build solution iteratively from smaller amounts to target amount
     * - For each amount i, try all coin denominations to find minimum coins
     * - Use previously calculated results for smaller amounts (dp[i-coin])
     * - This approach avoids recursion and builds solution systematically
     * 
     * Explanation:
     * - Step 1: Initialize dp array with -1 and set dp[0] = 0 as base case
     * - Step 2: Iterate through each amount from 1 to target amount
     * - Step 3: For each amount, try all coin denominations
     * - Step 4: If coin can be used (i-coin >= 0), update minimum coins
     * - Step 5: Store minimum coins needed for current amount in dp array
     * - Step 6: Return final result or -1 if no solution exists
     * 
     * Time Complexity: O(amount * len(coins)) - Each amount processed once
     * Space Complexity: O(amount) - For the dp array only
     * 
     * @param coins    Array of coin denominations (1 <= len <= 12)
     * @param amount   Target amount to make (0 <= amount <= 10^4)
     * @return         Minimum coins needed or -1 if impossible
     */
    public static int coinChangeTabulation(int[] coins, int amount) {
        // Initialize dp array with -1 to mark unvisited states
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        
        // Base case: amount 0 requires 0 coins
        dp[0] = 0;

        // Build solution iteratively for each amount from 1 to target
        for (int i = 1; i <= amount; i++) {
            // Initialize minimum coins to MAX_VALUE - 1 to avoid overflow
            int minCoins = Integer.MAX_VALUE - 1;
            
            // Try each coin denomination for current amount
            for (int coin : coins) {
                // Check if current coin can be used (remaining amount >= 0)
                if (i - coin >= 0) {
                    // Update minimum coins using previously calculated result
                    minCoins = Math.min(minCoins, dp[i - coin] + 1);
                }
            }
            
            // Store minimum coins needed for current amount
            dp[i] = minCoins;
        }

        // Return result or -1 if no solution exists
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: coins=[1,2,5], amount=11");
        System.out.println("Approach 1 (1D Memoization): Expected: 3, Output: " + 
                          coinChange(new int[]{1,2,5}, 11));
        System.out.println("Approach 2 (2D Memoization): Expected: 3, Output: " + 
                          coinChange2D(new int[]{1,2,5}, 11));
        System.out.println("Approach 3 (Tabulation): Expected: 3, Output: " + 
                          coinChangeTabulation(new int[]{1,2,5}, 11));
        
        System.out.println("\nInput: coins=[2], amount=3");
        System.out.println("Approach 1 (1D Memoization): Expected: -1, Output: " + 
                          coinChange(new int[]{2}, 3));
        System.out.println("Approach 2 (2D Memoization): Expected: -1, Output: " + 
                          coinChange2D(new int[]{2}, 3));
        System.out.println("Approach 3 (Tabulation): Expected: -1, Output: " + 
                          coinChangeTabulation(new int[]{2}, 3));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: coins=[1], amount=0");
        System.out.println("Approach 1 (1D Memoization): Expected: 0, Output: " + 
                          coinChange(new int[]{1}, 0));
        System.out.println("Approach 2 (2D Memoization): Expected: 0, Output: " + 
                          coinChange2D(new int[]{1}, 0));
        System.out.println("Approach 3 (Tabulation): Expected: 0, Output: " + 
                          coinChangeTabulation(new int[]{1}, 0));
        
        System.out.println("\nInput: coins=[], amount=5");
        System.out.println("Approach 1 (1D Memoization): Expected: -1, Output: " + 
                          coinChange(new int[]{}, 5));
        System.out.println("Approach 2 (2D Memoization): Expected: -1, Output: " + 
                          coinChange2D(new int[]{}, 5));
        System.out.println("Approach 3 (Tabulation): Expected: -1, Output: " + 
                          coinChangeTabulation(new int[]{}, 5));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: coins=[1], amount=10000");
        System.out.println("Approach 1 (1D Memoization): Expected: 10000, Output: " + 
                          coinChange(new int[]{1}, 10000));
        System.out.println("Approach 2 (2D Memoization): Expected: 10000, Output: " + 
                          coinChange2D(new int[]{1}, 10000));
        System.out.println("Approach 3 (Tabulation): Expected: 10000, Output: " + 
                          coinChangeTabulation(new int[]{1}, 10000));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: coins=[2,5,10], amount=13");
        System.out.println("Approach 1 (1D Memoization): Expected: 3, Output: " + 
                          coinChange(new int[]{2,5,10}, 13));
        System.out.println("Approach 2 (2D Memoization): Expected: 3, Output: " + 
                          coinChange2D(new int[]{2,5,10}, 13));
        System.out.println("Approach 3 (Tabulation): Expected: 3, Output: " + 
                          coinChangeTabulation(new int[]{2,5,10}, 13));
        
        System.out.println("\nInput: coins=[1,3,4], amount=6");
        System.out.println("Approach 1 (1D Memoization): Expected: 2, Output: " + 
                          coinChange(new int[]{1,3,4}, 6));
        System.out.println("Approach 2 (2D Memoization): Expected: 2, Output: " + 
                          coinChange2D(new int[]{1,3,4}, 6));
        System.out.println("Approach 3 (Tabulation): Expected: 2, Output: " + 
                          coinChangeTabulation(new int[]{1,3,4}, 6));
    }
}
