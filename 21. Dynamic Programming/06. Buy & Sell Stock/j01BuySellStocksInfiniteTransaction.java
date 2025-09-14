/**
 * LeetCode 122. Best Time to Buy and Sell Stock II
 * 
 * Problem Statement:
 *     You are given an integer array prices where prices[i] is the price of a 
 *     given stock on the ith day. On each day, you may decide to buy and/or 
 *     sell the stock. You can only hold at most one share of the stock at any 
 *     time. However, you can buy it then immediately sell it on the same day. 
 *     Find and return the maximum profit you can achieve.
 * 
 * Input:
 *     - prices (1 <= prices.length <= 3*10^4, 0 <= prices[i] <= 10^4)
 * 
 * Output:
 *     - Maximum profit from unlimited buy and sell transactions
 * 
 * Example:
 *     Input: prices = [7,1,5,3,6,4]
 *     Output: 7
 * 
 *     Explanation:
 *     Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4
 *     Buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3
 *     Total profit is 4 + 3 = 7
 */

public class j01BuySellStocksInfiniteTransaction {

    /**
     * Approach: Dynamic Programming with State Machine
     * 
     * Intuition:
     * - Use a state machine approach where we track two states: holding stock 
     *   and not holding stock
     * - At each day, we can either buy, sell, or do nothing
     * - dp[0][i] represents maximum profit when holding stock at day i
     * - dp[1][i] represents maximum profit when not holding stock at day i
     * - The key insight is that we can make unlimited transactions, so we 
     *   capture every profitable price increase
     * 
     * Explanation:
     * - Step 1: Initialize DP table with two states (hold/not hold)
     * - Step 2: Set initial state: hold stock on day 0 costs prices[0]
     * - Step 3: For each day, update both states based on previous day's states
     * - Step 4: Holding state: max of (keep holding, buy from not-holding)
     * - Step 5: Not-holding state: max of (keep not-holding, sell from holding)
     * - Step 6: Return maximum profit from both final states
     * 
     * Time Complexity: O(n) where n is the length of prices array
     * Space Complexity: O(n) for the DP table storage
     * 
     * @param prices    Array of stock prices for each day (1 <= prices.length <= 3*10^4)
     * @return         Maximum profit from unlimited buy and sell transactions
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
        
        // Fill DP table by considering all possible state transitions
        for (int i = 1; i < n; i++) {
            // Update holding state: max of keeping stock or buying from not-holding
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] - prices[i]);
            
            // Update not-holding state: max of staying out or selling from holding
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] + prices[i]);
        }

        // Return maximum profit from both possible final states
        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }

    /*-
     * Approach 2: Space-Optimized Dynamic Programming
     * 
     * Intuition:
     * - Instead of maintaining a 2D DP table, we only need two variables to track 
     *   the current state
     * - We observe that at each step, we only need the previous day's values
     * - `buy` represents maximum profit when holding stock at current day
     * - `sell` represents maximum profit when not holding stock at current day
     * - This optimization reduces space complexity from O(n) to O(1)
     * 
     * Explanation:
     * - Step 1: Initialize buy state to negative price of first day (cost to buy)
     * - Step 2: Initialize sell state to 0 (no profit initially)
     * - Step 3: For each day, update buy state: max of keeping previous buy or 
     *   buying from previous sell state
     * - Step 4: Update sell state: max of keeping previous sell or selling from 
     *   current buy state
     * - Step 5: Return final sell state as it represents maximum profit
     * 
     * Time Complexity: O(n) where n is the length of prices array
     * Space Complexity: O(1) using only two variables instead of DP table
     * 
     * @param prices    Array of stock prices for each day (1 <= prices.length <= 3*10^4)
     * @return         Maximum profit from unlimited buy and sell transactions
     */
    public static int maxProfitSpaceOptimized(int[] prices) {
        // Get array length for iteration bounds
        int n = prices.length;
        
        // Initialize buy state: cost to buy stock on first day
        int buy = 0 - prices[0];
        
        // Initialize sell state: no profit when not holding stock initially
        int sell = 0;
        
        // Update states for each subsequent day
        for(int i = 1; i < n; i++){
            // Update buy state: max of keeping previous buy or buying from sell state
            buy = Math.max(buy, sell - prices[i]);
            
            // Update sell state: max of keeping previous sell or selling current buy
            sell = Math.max(sell, buy + prices[i]);
        }

        // Return maximum profit (sell state contains the optimal result)
        return sell;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases with multiple profitable transactions
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [7,1,5,3,6,4], Expected: 7, Output: " + 
            maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println("Input: [1,2,3,4,5], Expected: 4, Output: " + 
            maxProfit(new int[]{1,2,3,4,5}));
        System.out.println("Input: [5,4,3,2,1], Expected: 0, Output: " + 
            maxProfit(new int[]{5,4,3,2,1}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], Expected: 0, Output: " + 
            maxProfit(new int[]{1}));
        System.out.println("Input: [1,1,1,1], Expected: 0, Output: " + 
            maxProfit(new int[]{1,1,1,1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0,1,0,1], Expected: 2, Output: " + 
            maxProfit(new int[]{0,1,0,1}));
        System.out.println("Input: [10000,1,10000], Expected: 9999, Output: " + 
            maxProfit(new int[]{10000,1,10000}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [2,4,1,7], Expected: 8, Output: " + 
            maxProfit(new int[]{2,4,1,7}));
        System.out.println("Input: [3,3,5,0,0,3,1,4], Expected: 8, Output: " + 
            maxProfit(new int[]{3,3,5,0,0,3,1,4}));
    }
}
