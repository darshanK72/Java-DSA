/**
 * LeetCode 123. Best Time to Buy and Sell Stock III
 * 
 * Problem Statement:
 *     You are given an integer array prices where prices[i] is the price of a
 *     given stock on the i-th day. Find the maximum profit you can achieve.
 *     You may complete at most two transactions.
 *     Note: You may not engage in multiple transactions simultaneously
 *     (i.e., you must sell the stock before you buy again).
 * 
 * Input:
 *     - prices (0 <= prices.length <= 10^5, 0 <= prices[i] <= 10^5)
 * 
 * Output:
 *     - Maximum profit as an integer with at most two transactions
 * 
 * Example:
 *     Input: prices = [3,3,5,0,0,3,1,4]
 *     Output: 6
 * 
 *     Explanation:
 *     Buy on day 3 (price=0) and sell on day 6 (price=3), profit = 3.
 *     Then buy on day 7 (price=1) and sell on day 8 (price=4), profit = 3.
 *     Total profit = 6.
 */
import java.util.Arrays;

public class j02BuySellStocksTwoTransaction {

    /**
     * Approach 1: Two-Pass Split DP (Prefix + Suffix Best Profits)
     * 
     * Intuition:
     * - The best total profit with at most two transactions can be realized by
     *   splitting the timeline at some index i: make at most one transaction
     *   in the prefix [0..i] and at most one in the suffix [i..n-1]. The
     *   optimal answer is the maximum over all i of prefixBest[i] + suffixBest[i].
     * - We can compute these two arrays in linear time: a forward pass tracks
     *   the minimum price so far to compute the best single-transaction
     *   profit up to each day; a backward pass tracks the maximum future price
     *   to compute the best single-transaction profit from each day onward.
     * 
     * Explanation:
     * - Forward pass builds buy[i]: best profit achievable within [0..i].
     *   Maintain running min of prices; potential profit at day i is
     *   prices[i] - minSoFar. Take max with previous best.
     * - Backward pass builds sell[i]: best profit achievable within [i..n-1].
     *   Maintain running max to the right; potential profit buying at i and
     *   selling later is maxRight - prices[i]. Take max with previous best.
     * - Combine: answer is max over i of buy[i] + sell[i]. This allows the two
     *   transactions to reside entirely in the prefix, entirely in the suffix,
     *   or one on each side of i, without overlap.
     * 
     * Time Complexity: O(n) — two linear passes plus a final combine pass.
     * Space Complexity: O(n) — arrays buy and sell of length n.
     * 
     * @param prices    Array of daily prices (null/empty -> 0 profit)
     * @return          Maximum profit with at most two transactions
     */
    public int maxProfit(int[] prices) {
        // Handle edge cases: null or fewer than 2 days yields zero profit
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        if (n == 1) return 0;

        int[] buy = new int[n];  // buy[i] = best profit in [0..i] with one transaction
        int[] sell = new int[n]; // sell[i] = best profit in [i..n-1] with one transaction

        int min = prices[0]; // track minimum price seen so far for prefix computations
        int ans = 0;         // reuse as running best profit
        buy[0] = 0;          // with only one day, profit is zero
        for (int i = 1; i < n; i++) {
            // Candidate profit: sell today vs. keep previous best
            ans = Math.max(prices[i] - min, ans);
            // Update minimum buying price so far
            min = Math.min(prices[i], min);
            // Store best prefix profit up to index i
            buy[i] = ans;
        }

        sell[n - 1] = 0;      // single day suffix has zero profit
        ans = 0;               // reset to accumulate suffix bests
        int max = prices[n - 1]; // track maximum future price for suffix computations
        for (int i = n - 2; i >= 0; i--) {
            // Candidate profit: buy today and sell later at max vs. keep best
            ans = Math.max(max - prices[i], ans);
            // Update maximum selling price to the right
            max = Math.max(prices[i], max);
            // Store best suffix profit starting at index i
            sell[i] = ans;
        }

        // Combine prefix and suffix bests to simulate at most two transactions
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, buy[i] + sell[i]);
        }
        return ans;
    }

    /**
     * Approach 2: DP + Memoization (Top-Down)
     * 
     * Intuition:
     * - Treat the trading process as a finite state machine driven by three
     *   variables: day index, whether we are currently holding a share, and how
     *   many sales (transactions) we can still make. A transaction is defined
     *   as a buy followed by a sell; to avoid double-counting, we decrement the
     *   transaction counter only on a sell. This convention ensures that a
     *   partially executed transaction (after a buy) does not prematurely
     *   reduce the budget for future opportunities.
     * - Optimal substructure: the best profit from any state depends only on
     *   the best profits of the next day’s states. Greedy choices (always buy
     *   or sell when local condition holds) fail because two transactions may
     *   interact across non-adjacent days; DP captures these dependencies.
     * - Overlapping subproblems: many states recur (e.g., the same remaining
     *   k and holding flag at different paths). Memoization avoids exponential
     *   recomputation and collapses the search to linear in time.
     * 
     * Explanation:
     * - Define state f(index, holding, k) as the maximum achievable profit
     *   from day index ... n-1 given whether we currently hold a share and how
     *   many sells remain. Transitions:
     *     * holding == 0: max( buy today -> -price[index] + f(index+1,1,k),
     *                       skip today -> f(index+1,0,k) )
     *     * holding == 1: max( sell today -> +price[index] + f(index+1,0,k-1),
     *                       hold today -> f(index+1,1,k) )
     * - Base cases: if index == n (no more days) or k == 0 (no sells left),
     *   profit is 0 because we cannot realize further gains.
     * - Memoize results in dp[k][holding][index] to ensure each state is
     *   computed once. This yields linear time in the number of days.
     * 
     * Time Complexity: O(n * 2 * 3) states, O(1) work per state -> O(n)
     * Space Complexity: O(n * 2 * 3) for memo + O(n) recursion stack depth
     * 
     * @param prices    Non-null array of prices; empty treated as zero profit
     * @return          Maximum profit with at most two transactions
     */
    public static int maxProfitMemo(int[] prices) {
        // Handle edge cases (null or empty array yields zero profit)
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length; // number of days
        int[][][] dp = new int[3][2][n]; // dp[k][holding][index]
        for (int i = 0; i <= 2; i++) {
            Arrays.fill(dp[i][0], -1); // mark uncomputed states for not holding
            Arrays.fill(dp[i][1], -1); // mark uncomputed states for holding
        }
        // Start from day 0, not holding, with k = 2 transactions available
        return maxProfitMemoHelper(prices, dp, 0, 0, 2);
    }

    /**
     * Helper Method:
     * 
     * Intuition:
     * - Implements the state transition logic for the top-down DP. The method
     *   directly mirrors the recurrence and is memoized to ensure each state
     *   is evaluated once.
     * 
     * Explanation:
     * - If we run out of days or allowed sells, return 0 (no more profit).
     *   Otherwise, decide between acting (buy/sell) or waiting (skip/hold)
     *   depending on the holding flag. Selling reduces k because only fully
     *   completed transactions should count toward the limit of two.
     * 
     * Time Complexity: O(1) amortized per unique state due to memoization
     * Space Complexity: O(1) auxiliary per call; shared dp stores results
     * 
     * @param prices    Prices array
     * @param dp        Memoization table dp[k][holding][index]
     * @param index     Current day index
     * @param holding   0 if not holding stock, 1 if holding
     * @param k         Remaining transactions (0..2)
     * @return          Max profit from this state onward
     */
    public static int maxProfitMemoHelper(int[] prices, int[][][] dp, int index, int holding, int k) {
        // Base case: reached end or no transactions left
        if (index == prices.length || k == 0) return 0;

        // Return memoized result if available
        if (dp[k][holding][index] != -1) return dp[k][holding][index];

        int profit = Integer.MIN_VALUE; // track best profit for this state
        if (holding == 0) {
            // Option 1: buy today -> pay prices[index], move to holding state
            int buy = -prices[index] + maxProfitMemoHelper(prices, dp, index + 1, 1, k);
            // Option 2: skip buying today -> remain not holding
            int notBuy = maxProfitMemoHelper(prices, dp, index + 1, 0, k);
            profit = Math.max(buy, notBuy);
        } else {
            // Option 1: sell today -> gain prices[index], consume one transaction
            int sell = prices[index] + maxProfitMemoHelper(prices, dp, index + 1, 0, k - 1);
            // Option 2: hold without selling
            int notSell = maxProfitMemoHelper(prices, dp, index + 1, 1, k);
            profit = Math.max(sell, notSell);
        }
        // Memoize and return
        return dp[k][holding][index] = profit;
    }

    /**
     * Approach 3: DP + Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Unroll the recursive formulation into an iterative DP that fills a
     *   table from the last day to the first. The dimensions reflect the same
     *   state variables (k, holding, index). Because decisions on day index
     *   depend only on index+1, a backward pass naturally respects data
     *   dependencies and avoids recursion.
     * 
     * Explanation:
     * - Initialize an extra sentinel day n with zeros (no time left => 0).
     *   For each index from n-1 down to 0 and for each (holding, k>0), compute
     *   the max of acting vs. not acting using already filled values at index+1.
     *   Selling references k-1 because a sale completes one transaction.
     * - The answer we seek is dp[2][0][0]: at day 0, not holding, with two
     *   sells remaining.
     * 
     * Time Complexity: O(n * 2 * 3) = O(n)
     * Space Complexity: O(n * 2 * 3)
     * 
     * @param prices    Prices array; null/empty -> 0 profit
     * @return          Maximum profit with at most two transactions
     */
    public static int maxProfitTabulation(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length;
        int[][][] dp = new int[3][2][n + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int holding = 0; holding <= 1; holding++) {
                for (int k = 1; k <= 2; k++) {
                    if (holding == 0) {
                        int buy = -prices[index] + dp[k][1][index + 1]; // buy today
                        int skip = dp[k][0][index + 1]; // skip buying
                        dp[k][holding][index] = Math.max(buy, skip);
                    } else {
                        int sell = prices[index] + dp[k - 1][0][index + 1]; // sell today
                        int hold = dp[k][1][index + 1]; // keep holding
                        dp[k][holding][index] = Math.max(sell, hold);
                    }
                }
            }
        }
        return dp[2][0][0];
    }

    /**
     * Approach 4: Space-Optimized Tabulation
     * 
     * Intuition:
     * - Observing that transitions use only index+1 states, we can retain only
     *   two 2x3 layers: one for the next day and one for the current day. This
     *   rolling-array technique reduces space from O(n) layers to O(1) while
     *   preserving correctness because reads always come from the "next"
     *   snapshot.
     * 
     * Explanation:
     * - Iterate days from right to left. For each k in [1..2], compute the
     *   not-holding and holding results for the current day using the next
     *   layer. After finishing a day, swap references so next becomes curr.
     *   The transitions are identical to the full tabulation version.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) additional (fixed-size 2x3 arrays)
     * 
     * @param prices    Prices array; null/empty -> 0 profit
     * @return          Maximum profit with at most two transactions
     */
    public static int maxProfitTabulationOptimized(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length;
        int[][] next = new int[2][3];
        int[][] curr = new int[2][3];

        for (int index = n - 1; index >= 0; index--) {
            for (int h = 0; h <= 1; h++)
                Arrays.fill(curr[h], 0); // reset curr row for both holding states

            for (int k = 1; k <= 2; k++) {
                int buy = -prices[index] + next[1][k]; // buy today
                int skip = next[0][k]; // skip buying
                curr[0][k] = Math.max(buy, skip);
                int sell = prices[index] + next[0][k - 1]; // sell today
                int hold = next[1][k]; // keep holding
                curr[1][k] = Math.max(sell, hold);
            }
            int[][] tmp = next; // swap buffers for next iteration
            next = curr;
            curr = tmp;
        }

        return next[0][2];
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [3,3,5,0,0,3,1,4], Expected: 6, Memo: " + maxProfitMemo(new int[]{3,3,5,0,0,3,1,4})
                + ", Tab: " + maxProfitTabulation(new int[]{3,3,5,0,0,3,1,4})
                + ", Opt: " + maxProfitTabulationOptimized(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println("Input: [1,2,3,4,5], Expected: 4, ...: " + maxProfitTabulationOptimized(new int[]{1,2,3,4,5}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [], Expected: 0, Memo: " + maxProfitMemo(new int[]{}));
        System.out.println("Input: null, Expected: 0, Memo: " + maxProfitMemo(null));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0], Expected: 0, Opt: " + maxProfitTabulationOptimized(new int[]{0}));
        System.out.println("Input: [100000], Expected: 0, Opt: " + maxProfitTabulationOptimized(new int[]{100000}));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [5,4,3,2,1], Expected: 0, Opt: " + maxProfitTabulationOptimized(new int[]{5,4,3,2,1}));
        System.out.println("Input: [2,1,2,0,1], Expected: 2, Opt: " + maxProfitTabulationOptimized(new int[]{2,1,2,0,1}));
    }
}
