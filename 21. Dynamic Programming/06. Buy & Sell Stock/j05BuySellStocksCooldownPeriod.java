/**
 * LeetCode 309. Best Time to Buy and Sell Stock with Cooldown
 * 
 * Problem Statement:
 *     You are given an array prices where prices[i] is the price of a given stock 
 *     on the ith day. Find the maximum profit you can achieve. You may complete as 
 *     many transactions as you like, but you cannot buy on the next day after you 
 *     sell your stock. In other words, after you sell your stock, you cannot buy 
 *     stock on the next day (cooldown period of 1 day).
 * 
 * Input:
 *     - prices (1 <= prices.length <= 5000, 0 <= prices[i] <= 1000)
 * 
 * Output:
 *     - Maximum profit from unlimited transactions with 1-day cooldown after selling
 * 
 * Example:
 *     Input: prices = [1,2,3,0,2]
 *     Output: 3
 * 
 *     Explanation:
 *     Buy on day 1 (price = 1) and sell on day 2 (price = 2), profit = 2-1 = 1
 *     Cooldown on day 3 (cannot buy)
 *     Buy on day 4 (price = 0) and sell on day 5 (price = 2), profit = 2-0 = 2
 *     Total profit is 1 + 2 = 3
 */

public class j05BuySellStocksCooldownPeriod {

    /**
     * Approach: Dynamic Programming with State Machine and Cooldown
     * 
     * Intuition:
     * - Use a state machine approach tracking two states: holding stock and not 
     *   holding stock
     * - Key constraint: after selling, there's a 1-day cooldown before buying again
     * - dp[0][i] represents maximum profit when holding stock at day i
     * - dp[1][i] represents maximum profit when not holding stock at day i
     * - Cooldown constraint: when buying, we can only use profit from dp[1][i-2] 
     *   (skipping day i-1 due to cooldown)
     * 
     * Explanation:
     * - Step 1: Initialize DP table with two states (hold/not hold)
     * - Step 2: Set initial state: hold stock on day 0 costs prices[0]
     * - Step 3: Handle day 1 separately as base case for cooldown logic
     * - Step 4: For each day i >= 2, apply cooldown constraint in buy decision
     * - Step 5: Holding state: max of (keep holding, buy from day i-2 due to cooldown)
     * - Step 6: Not-holding state: max of (keep not-holding, sell from holding)
     * - Step 7: Return final not-holding state as optimal result
     * 
     * Time Complexity: O(n) where n is the length of prices array
     * Space Complexity: O(n) for the DP table storage
     * 
     * @param prices    Array of stock prices for each day (1 <= prices.length <= 5000)
     * @return         Maximum profit from unlimited transactions with cooldown
     */
    public static int maxProfit(int[] prices) {
        // Get array length for DP table initialization
        int n = prices.length;
        
        // Initialize DP table: dp[0] = holding state, dp[1] = not holding state
        int[][] dp = new int[2][n];
        
        // Base case: holding stock on day 0 costs the price of day 0
        dp[0][0] = 0 - prices[0];
        
        // Base case: not holding stock on day 0 gives 0 profit
        dp[1][0] = 0;
        
        // Handle day 1 separately as special case for cooldown logic
        if (n > 1) {
            // Day 1 holding: max of keeping day 0 stock or buying on day 1
            dp[0][1] = Math.max(-prices[0], -prices[1]);
            
            // Day 1 not holding: max of keeping day 0 state or selling day 0 stock
            dp[1][1] = Math.max(0, -prices[0] + prices[1]);
        }
        
        // Apply cooldown constraint for days 2 onwards
        for (int i = 2; i < n; i++) {
            // Update holding state: max of keeping stock or buying from day i-2 (cooldown)
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 2] - prices[i]);
            
            // Update not-holding state: max of staying out or selling from holding
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] + prices[i]);
        }

        // Return maximum profit (not-holding state contains the optimal result)
        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases with cooldown period
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,2,3,0,2], Expected: 3, Output: " + 
            maxProfit(new int[]{1,2,3,0,2}));
        System.out.println("Input: [1,2,4], Expected: 3, Output: " + 
            maxProfit(new int[]{1,2,4}));
        System.out.println("Input: [3,3,5,0,0,3,1,4], Expected: 6, Output: " + 
            maxProfit(new int[]{3,3,5,0,0,3,1,4}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], Expected: 0, Output: " + 
            maxProfit(new int[]{1}));
        System.out.println("Input: [1,2], Expected: 1, Output: " + 
            maxProfit(new int[]{1,2}));
        System.out.println("Input: [1,1,1,1], Expected: 0, Output: " + 
            maxProfit(new int[]{1,1,1,1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0,1,0,1], Expected: 2, Output: " + 
            maxProfit(new int[]{0,1,0,1}));
        System.out.println("Input: [1000,1,1000], Expected: 999, Output: " + 
            maxProfit(new int[]{1000,1,1000}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [2,4,1,7], Expected: 6, Output: " + 
            maxProfit(new int[]{2,4,1,7}));
        System.out.println("Input: [7,6,4,3,1], Expected: 0, Output: " + 
            maxProfit(new int[]{7,6,4,3,1}));
        System.out.println("Input: [1,2,3,4,5], Expected: 4, Output: " + 
            maxProfit(new int[]{1,2,3,4,5}));
    }
}
