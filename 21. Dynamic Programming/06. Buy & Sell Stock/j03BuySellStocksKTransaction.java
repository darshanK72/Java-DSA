/**
 * LeetCode 188. Best Time to Buy and Sell Stock IV
 * 
 * Problem Statement:
 *     You are given an integer array prices where prices[i] is the price of a
 *     given stock on the i-th day, and an integer k. Find the maximum profit
 *     you can achieve. You may complete at most k transactions.
 *     Note: You may not engage in multiple transactions simultaneously
 *     (i.e., you must sell the stock before you buy again).
 * 
 * Input:
 *     - k (0 <= k <= 10^9)
 *     - prices (0 <= prices.length <= 10^5, 0 <= prices[i] <= 10^5)
 * 
 * Output:
 *     - Maximum achievable profit as an integer with at most k transactions
 * 
 * Example:
 *     Input: k = 2, prices = [3,2,6,5,0,3]
 *     Output: 7
 * 
 *     Explanation:
 *     Buy on day 2 (price=2) and sell on day 3 (price=6), profit = 4.
 *     Then buy on day 5 (price=0) and sell on day 6 (price=3), profit = 3.
 *     Total profit = 7.
 */
import java.util.Arrays;

public class j03BuySellStocksKTransaction {
    /**
     * Approach: DP + Memoization (Top-Down)
     * 
     * Intuition:
     * - Same state-machine formulation as the two-transaction variant, but the
     *   number of allowed sells is now k (parametric). State variables:
     *   (index, holding, kRemaining). We decrement k only on sell to count a
     *   completed buy->sell pair.
     * - When k is very large (k >= n/2), this degenerates to the infinite
     *   transactions case where we can greedily accumulate every rising edge.
     * 
     * Explanation:
     * - Use dp[kRemaining][holding][index] to cache subproblems. Choices are
     *   identical to the 2-transaction problem but kRemaining is general.
     * - Edge handling: null/empty prices or non-positive k yields 0 profit.
     * - Optional optimization: handle k >= n/2 via linear greedy pass to avoid
     *   large DP tables when k is effectively unbounded.
     * 
     * Time Complexity: O(n * 2 * min(k, n)) ≈ O(n * k) unique states
     * Space Complexity: O(n * 2 * min(k, n)) for memo table + recursion stack
     * 
     * @param k         Max number of transactions (sell operations)
     * @param prices    Prices array; null/empty -> 0
     * @return          Maximum profit with at most k transactions
     */
    public static int maxProfit(int k, int[] prices) {
        // Handle edge cases (no transactions or no data -> zero profit)
        if (k <= 0 || prices == null || prices.length == 0) return 0;

        int n = prices.length; // number of days available
        // If k is large (>= n/2), we can treat it as unlimited transactions
        // because at most floor(n/2) disjoint buy-sell pairs fit in n days
        if (k >= n / 2) return maxProfitInfiniteTransactions(prices);

        // dp[remainingSells][holdingFlag][dayIndex]
        int[][][] dp = new int[k + 1][2][n];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i][0], -1); // mark uncomputed (not holding) states
            Arrays.fill(dp[i][1], -1); // mark uncomputed (holding) states
        }
        // Start recursion at day 0, not holding, with k sells available
        return maxProfitMemo(prices, dp, 0, 0, k);
    }

    /**
     * Helper Method:
     * 
     * Intuition:
     * - Top-down memoized recursion that applies buy/sell/skip transitions.
     * 
     * Explanation:
     * - Base cases: end of array or no sells remaining -> 0.
     * - If not holding, choose max of buying vs skipping. If holding, choose
     *   max of selling (consumes one k) vs holding (skip).
     * 
     * Time Complexity: O(1) per unique state after memoization
     * Space Complexity: O(1) auxiliary per call; dp stores results
     * 
     * @param prices    Prices array
     * @param dp        Memo table dp[kRemaining][holding][index]
     * @param index     Current day
     * @param canSell   0 if not holding, 1 if holding
     * @param k         Remaining sells
     * @return          Best profit from this state
     */
    public static int maxProfitMemo(int[] prices, int[][][] dp, int index, int canSell, int k) {
        // Stop if no days left or no sells remaining
        if (index == prices.length || k == 0)
            return 0;
        // Return cached result to avoid recomputation
        if (dp[k][canSell][index] != -1)
            return dp[k][canSell][index];
        int profit = Integer.MIN_VALUE; // best profit from current state
        if (canSell == 0) {
            // Not holding: decide to buy today or skip buying
            int buy = -prices[index] + maxProfitMemo(prices, dp, index + 1, 1, k); // pay price to acquire
            int notBuy = maxProfitMemo(prices, dp, index + 1, 0, k);               // keep cash, wait
            profit = Math.max(buy, notBuy);                                        // select better outcome
        } else {
            // Holding: decide to sell today (consumes one transaction) or hold
            int sell = prices[index] + maxProfitMemo(prices, dp, index + 1, 0, k - 1); // realize gain, free hand
            int notSell = maxProfitMemo(prices, dp, index + 1, 1, k);                  // continue to hold
            profit = Math.max(sell, notSell);                                          // select better outcome
        }
        // Memoize and return result for current state
        return dp[k][canSell][index] = profit;
    }

    /**
     * Approach: DP + Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Iteratively compute the same recurrence by filling a 3D table over
     *   (kRemaining, holding, index) from right to left.
     * 
     * Explanation:
     * - Use dp[K+1][2][n+1] with day n as base (all zeros). For each day and
     *   for k from 1..K, compute not-holding and holding states using next day.
     * - Answer is dp[K][0][0].
     * 
     * Time Complexity: O(n * K)
     * Space Complexity: O(n * K)
     * 
     * @param K         Max transactions
     * @param prices    Prices array; null/empty -> 0
     * @return          Maximum profit with at most K transactions
     */
    public static int maxProfitTabulation(int K,int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length; // total number of days
        if (K <= 0) return 0; // zero transactions allowed
        if (K >= n / 2) return maxProfitInfiniteTransactions(prices); // unlimited case

        // dp[remainingSells][holdingFlag][dayIndex], include sentinel day n
        int[][][] dp = new int[K + 1][2][n + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int holding = 0; holding <= 1; holding++) {
                for (int k = 1; k <= K; k++) {
                    if (holding == 0) {
                        // Not holding: either buy today or skip to next day
                        int buy = -prices[index] + dp[k][1][index + 1];   // move to holding state
                        int skip = dp[k][0][index + 1];                   // remain not holding
                        dp[k][holding][index] = Math.max(buy, skip);      // best choice
                    } else {
                        // Holding: either sell today (consume one) or keep holding
                        int sell = prices[index] + dp[k - 1][0][index + 1]; // realize and free hand
                        int hold = dp[k][1][index + 1];                     // postpone selling
                        dp[k][holding][index] = Math.max(sell, hold);       // best choice
                    }
                }
            }
        }
        return dp[K][0][0];
    }

    /**
     * Approach: Space-Optimized Tabulation
     * 
     * Intuition:
     * - Only next-day values are required, so maintain two layers next/curr of
     *   size 2 x (K+1).
     * 
     * Explanation:
     * - Iterate from right to left, compute curr from next for all k in 1..K
     *   and both holding states, then swap.
     * - Answer is next[0][K] at the end (day -1 theoretical).
     * 
     * Time Complexity: O(n * K)
     * Space Complexity: O(K)
     * 
     * @param K         Max transactions
     * @param prices    Prices array; null/empty -> 0
     * @return          Maximum profit with at most K transactions
     */
    public static int maxProfitTabulationOptimized(int K,int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length; // total number of days
        if (K <= 0) return 0; // zero transactions allowed
        if (K >= n / 2) return maxProfitInfiniteTransactions(prices); // unlimited case

        // Rolling arrays: next = day index+1 layer, curr = current day layer
        int[][] next = new int[2][K + 1];
        int[][] curr = new int[2][K + 1];

        for (int index = n - 1; index >= 0; index--) {
            // Reset current layer for both holding states before recomputation
            for (int h = 0; h <= 1; h++)
                Arrays.fill(curr[h], 0);

            for (int k = 1; k <= K; k++) {
                // Not holding: option to buy today or skip
                int buy = -prices[index] + next[1][k];  // transition to holding
                int skip = next[0][k];                  // remain not holding
                curr[0][k] = Math.max(buy, skip);
                // Holding: option to sell today (consume one) or continue holding
                int sell = prices[index] + next[0][k - 1]; // complete one transaction
                int hold = next[1][k];                     // defer selling
                curr[1][k] = Math.max(sell, hold);
            }
            // Swap layers so the freshly computed curr becomes next for prev day
            int[][] tmp = next;
            next = curr;
            curr = tmp;
        }

        return next[0][K];
    }

    /**
     * Helper Method: Infinite Transactions Profit
     * 
     * Intuition:
     * - If k >= n/2, we can treat it as unlimited transactions; the optimal
     *   profit is simply the sum of all positive day-to-day gains.
     * 
     * Explanation:
     * - Accumulate prices[i] - prices[i-1] for each increasing adjacent pair.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param prices    Prices array
     * @return          Profit with unlimited transactions
     */
    private static int maxProfitInfiniteTransactions(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // Add every positive price difference (capture each upward move)
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        }
        return profit;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("k=2, [3,2,6,5,0,3] -> Expected: 7, Memo: " + maxProfit(2, new int[]{3,2,6,5,0,3})
                + ", Tab: " + maxProfitTabulation(2, new int[]{3,2,6,5,0,3})
                + ", Opt: " + maxProfitTabulationOptimized(2, new int[]{3,2,6,5,0,3}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("k=0, [1,2,3] -> 0: " + maxProfit(0, new int[]{1,2,3}));
        System.out.println("k=2, [] -> 0: " + maxProfit(2, new int[]{}));
        System.out.println("k=2, null -> 0: " + maxProfit(2, null));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("k=1, [1] -> 0: " + maxProfit(1, new int[]{1}));
        System.out.println("k=100, [1,2] -> 1 (unlimited): " + maxProfit(100, new int[]{1,2}));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("k=3, [5,4,3,2,1] -> 0: " + maxProfit(3, new int[]{5,4,3,2,1}));
        System.out.println("k=3, [1,2,3,4,5] -> 4 (unlimited): " + maxProfit(3, new int[]{1,2,3,4,5}));
    }
}
