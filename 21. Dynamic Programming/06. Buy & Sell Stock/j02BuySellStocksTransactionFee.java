/**
 * LeetCode 714. Best Time to Buy and Sell Stock with Transaction Fee
 * 
 * Problem Statement:
 *     You are given an array prices where prices[i] is the price of a given stock 
 *     on the ith day, and an integer fee representing a transaction fee. You may 
 *     complete as many transactions as you like, but you need to pay the 
 *     transaction fee for each transaction. You can only hold at most one share 
 *     of the stock at any time. Return the maximum profit you can achieve.
 * 
 * Input:
 *     - prices (1 <= prices.length <= 5*10^4, 0 <= prices[i] <= 5*10^4)
 *     - fee (0 <= fee <= 5*10^4)
 * 
 * Output:
 *     - Maximum profit from unlimited buy and sell transactions with fees
 * 
 * Example:
 *     Input: prices = [1,3,2,8,4,9], fee = 2
 *     Output: 8
 * 
 *     Explanation:
 *     Buy on day 1 (price = 1) and sell on day 4 (price = 8), profit = 8-1-2 = 5
 *     Buy on day 5 (price = 4) and sell on day 6 (price = 9), profit = 9-4-2 = 3
 *     Total profit is 5 + 3 = 8
 */

public class j02BuySellStocksTransactionFee {

    /**
     * Approach 1: Dynamic Programming with State Machine
     * 
     * Intuition:
     * - Similar to infinite transactions problem, but now we account for 
     *   transaction fees when selling
     * - Use a state machine approach tracking two states: holding stock 
     *   and not holding stock
     * - dp[0][i] represents maximum profit when holding stock at day i
     * - dp[1][i] represents maximum profit when not holding stock at day i
     * - Key difference: subtract fee when transitioning from holding to not-holding
     * 
     * Explanation:
     * - Step 1: Initialize DP table with two states (hold/not hold)
     * - Step 2: Set initial state: hold stock on day 0 costs prices[0]
     * - Step 3: For each day, update both states based on previous day's states
     * - Step 4: Holding state: max of (keep holding, buy from not-holding)
     * - Step 5: Not-holding state: max of (keep not-holding, sell from holding - fee)
     * - Step 6: Return final not-holding state as optimal result
     * 
     * Time Complexity: O(n) where n is the length of prices array
     * Space Complexity: O(n) for the DP table storage
     * 
     * @param prices    Array of stock prices for each day (1 <= prices.length <= 5*10^4)
     * @param fee       Transaction fee for each sell operation (0 <= fee <= 5*10^4)
     * @return         Maximum profit from unlimited transactions with fees
     */
    public static int maxProfit(int[] prices, int fee) {
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
            
            // Update not-holding state: max of staying out or selling from holding (minus fee)
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] + prices[i] - fee);
        }

        // Return maximum profit (not-holding state contains the optimal result)
        return dp[1][n - 1];
    }

    /**
     * Approach 2: Space-Optimized Dynamic Programming
     * 
     * Intuition:
     * - Apply space optimization to the DP solution by using only two variables
     * - We only need the previous day's values to compute current day's states
     * - `buy` represents maximum profit when holding stock at current day
     * - `sell` represents maximum profit when not holding stock at current day
     * - Same logic as Approach 1 but with O(1) space complexity
     * 
     * Explanation:
     * - Step 1: Initialize buy state to negative price of first day (cost to buy)
     * - Step 2: Initialize sell state to 0 (no profit initially)
     * - Step 3: For each day, update buy state: max of keeping previous buy or 
     *   buying from previous sell state
     * - Step 4: Update sell state: max of keeping previous sell or selling from 
     *   current buy state (minus transaction fee)
     * - Step 5: Return final sell state as it represents maximum profit
     * 
     * Time Complexity: O(n) where n is the length of prices array
     * Space Complexity: O(1) using only two variables instead of DP table
     * 
     * @param prices    Array of stock prices for each day (1 <= prices.length <= 5*10^4)
     * @param fee       Transaction fee for each sell operation (0 <= fee <= 5*10^4)
     * @return         Maximum profit from unlimited transactions with fees
     */
    public static int maxProfitSpaceOptimized(int[] prices, int fee) {
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
            
            // Update sell state: max of keeping previous sell or selling current buy (minus fee)
            sell = Math.max(sell, buy + prices[i] - fee);
        }

        // Return maximum profit (sell state contains the optimal result)
        return sell;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases with transaction fees
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,3,2,8,4,9], fee=2, Expected: 8, Output: " + 
            maxProfit(new int[]{1,3,2,8,4,9}, 2));
        System.out.println("Input: [1,3,2,8,4,9], fee=2, Expected: 8, Output: " + 
            maxProfitSpaceOptimized(new int[]{1,3,2,8,4,9}, 2));
        System.out.println("Input: [1,2,3,4,5], fee=2, Expected: 2, Output: " + 
            maxProfit(new int[]{1,2,3,4,5}, 2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], fee=1, Expected: 0, Output: " + 
            maxProfit(new int[]{1}, 1));
        System.out.println("Input: [1,1,1,1], fee=0, Expected: 0, Output: " + 
            maxProfit(new int[]{1,1,1,1}, 0));
        System.out.println("Input: [1,1,1,1], fee=1, Expected: 0, Output: " + 
            maxProfit(new int[]{1,1,1,1}, 1));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0,1,0,1], fee=0, Expected: 2, Output: " + 
            maxProfit(new int[]{0,1,0,1}, 0));
        System.out.println("Input: [10000,1,10000], fee=5000, Expected: 4999, Output: " + 
            maxProfit(new int[]{10000,1,10000}, 5000));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [2,4,1,7], fee=1, Expected: 5, Output: " + 
            maxProfit(new int[]{2,4,1,7}, 1));
        System.out.println("Input: [3,3,5,0,0,3,1,4], fee=2, Expected: 6, Output: " + 
            maxProfit(new int[]{3,3,5,0,0,3,1,4}, 2));
    }
}
