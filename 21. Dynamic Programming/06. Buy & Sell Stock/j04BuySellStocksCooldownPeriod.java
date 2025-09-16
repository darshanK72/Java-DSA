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

import java.util.Arrays;

public class j04BuySellStocksCooldownPeriod {


    /**
     * Approach 1: Top-Down DP (Memoization) with 1-Day Cooldown
     * 
     * Intuition:
     * - At each day, we are either allowed to buy (not holding) or must decide to
     *   sell/skip (holding). Selling enforces a cooldown of one day before the
     *   next buy. Memoize results for (day, canBuy) to avoid recomputation.
     * 
     * Explanation:
     * - State: (index, canBuy) where canBuy=1 means we can buy; canBuy=0 means we
     *   are holding and can sell/skip.
     * - Transition:
     *   - If canBuy=1: max(buy → -price[i] + f(i+1, 0), skip → f(i+1, 1)).
     *   - If canBuy=0: max(sell → +price[i] + f(i+2, 1), skip → f(i+1, 0)).
     * - Base: index>=n → 0.
     * 
     * Time Complexity: O(n) states × O(1) per state = O(n).
     * Space Complexity: O(n) for memo and recursion stack.
     * 
     * @param prices    Daily prices (may be null/empty)
     * @return          Maximum profit with 1-day cooldown
     */
    public static int maxProfitMemo(int[] prices) {
        // Handle edge cases: null or length <= 1 yields zero profit
        if (prices == null || prices.length <= 1) return 0;

        int n = prices.length; // number of days
        int[][] dp = new int[2][n]; // dp[canBuy][day]
        Arrays.fill(dp[0], -1); // mark as uncomputed
        Arrays.fill(dp[1], -1);
        // Start from day 0 with ability to buy (not holding)
        return maxProfitMemoHelper(prices, dp, 0, 1);
    }

    /**
     * Helper Method
     * 
     * Intuition:
     * - Explore both choices for current state and memoize best outcome.
     * 
     * Explanation:
     * - If index beyond last day, no profit can be made.
     * - Use memo table to avoid recomputing overlapping subproblems.
     * 
     * Time Complexity: O(1) work per unique state; total O(n).
     * Space Complexity: O(n) recursion depth and memo table.
     * 
     * @param prices    Price array
     * @param dp        Memo table dp[canBuy][index]
     * @param index     Current day index
     * @param canBuy    1 if we may buy; 0 if we are holding
     * @return          Best profit from this state onward
     */
    public static int maxProfitMemoHelper(int[] prices,int[][] dp,int index,int canBuy){
        // Base: no more days to act
        if (index >= prices.length) return 0;
        // Use memoized value if present
        if (dp[canBuy][index] != -1) return dp[canBuy][index];

        int profit;
        if (canBuy == 1) {
            // Option 1: buy today and move to holding state next day
            int buy = -prices[index] + maxProfitMemoHelper(prices, dp, index + 1, 0);
            // Option 2: skip buying today
            int notBuy = maxProfitMemoHelper(prices, dp, index + 1, 1);
            profit = Math.max(buy, notBuy);
        } else {
            // Option 1: sell today and skip one day due to cooldown
            int sell = prices[index] + maxProfitMemoHelper(prices, dp, index + 2, 1);
            // Option 2: do not sell today
            int notSell = maxProfitMemoHelper(prices, dp, index + 1, 0);
            profit = Math.max(sell, notSell);
        }
        return dp[canBuy][index] = profit; // memoize
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation) with Cooldown
     * 
     * Intuition:
     * - Build from the last day backwards. dp[holding][i] is best profit starting
     *   from day i given whether we are holding.
     * - Cooldown is modeled via accessing dp[..][i+2] after a sell.
     * 
     * Explanation:
     * - Use dp[2][n+2] to safely access i+1 and i+2.
     * - For each day from n-1 to 0, compute both holding and not-holding states.
     * - Answer is dp[0][0] (start not holding, can buy).
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param prices    Daily prices (may be null/empty)
     * @return          Maximum profit with 1-day cooldown
     */
    public static int maxProfitTabulation1(int[] prices) {
        // Handle edge cases: null or length <= 1 yields zero profit
        if (prices == null || prices.length <= 1) return 0;

        int n = prices.length;
        int[][] dp = new int[2][n+2];
        for(int index = n - 1; index >= 0; index--){
            for(int holding = 0; holding <= 1; holding++){
                if(holding == 0){
                    int buy = -prices[index] + dp[1][index + 1];
                    int notBuy = dp[0][index + 1];
                    dp[holding][index] = Math.max(buy,notBuy);
                }else{
                    int sell = prices[index] + dp[0][index + 2];
                    int notSell = dp[1][index + 1];
                    dp[holding][index] = Math.max(sell,notSell);
                }
            }
        }

        return dp[0][0];
    }


    /**
     * Approach 3: Dynamic Programming Tabulation with State Machine and Cooldown
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
    public static int maxProfitTabulation2(int[] prices) {
        // Handle edge cases: null or length <= 1 yields zero profit
        if (prices == null || prices.length <= 1) return 0;

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
        maxProfitMemo(new int[]{1,2,3,0,2}));
        System.out.println("Input: [1,2,4], Expected: 3, Output: " + 
        maxProfitTabulation1(new int[]{1,2,4}));
        System.out.println("Input: [3,3,5,0,0,3,1,4], Expected: 6, Output: " + 
        maxProfitTabulation2(new int[]{3,3,5,0,0,3,1,4}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null, Expected: 0, Output: " + 
        maxProfitMemo(null));
        System.out.println("Input: [], Expected: 0, Output: " + 
        maxProfitTabulation1(new int[]{}));
        System.out.println("Input: [1], Expected: 0, Output: " + 
        maxProfitMemo(new int[]{1}));
        System.out.println("Input: [1,2], Expected: 1, Output: " + 
        maxProfitTabulation1(new int[]{1,2}));
        System.out.println("Input: [1,1,1,1], Expected: 0, Output: " + 
        maxProfitTabulation2(new int[]{1,1,1,1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0,1,0,1], Expected: 2, Output: " + 
        maxProfitMemo(new int[]{0,1,0,1}));
        System.out.println("Input: [1000,1,1000], Expected: 999, Output: " + 
        maxProfitTabulation1(new int[]{1000,1,1000}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [2,4,1,7], Expected: 6, Output: " + 
        maxProfitTabulation1(new int[]{2,4,1,7}));
        System.out.println("Input: [7,6,4,3,1], Expected: 0, Output: " + 
        maxProfitTabulation1(new int[]{7,6,4,3,1}));
        System.out.println("Input: [1,2,3,4,5], Expected: 4, Output: " + 
        maxProfitTabulation2(new int[]{1,2,3,4,5}));
    }
}
