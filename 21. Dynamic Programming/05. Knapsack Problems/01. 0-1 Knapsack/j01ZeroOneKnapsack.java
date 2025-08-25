/**
 * GeeksForGeeks. 0-1 Knapsack Problem
 * 
 * Problem Statement:
 *     Given weights and values of n items, put these items in a knapsack of 
 *     capacity W to get the maximum total value in the knapsack. In other 
 *     words, given two integer arrays val[0..n-1] and wt[0..n-1] which 
 *     represent values and weights associated with n items respectively. 
 *     Also given an integer W which represents knapsack capacity, find out 
 *     the maximum value subset of val[] such that sum of the weights of this 
 *     subset is smaller than or equal to W. You cannot break an item, either 
 *     pick the complete item or don't pick it (0-1 property).
 * 
 * Input:
 *     - W (int): Knapsack capacity (1 ≤ W ≤ 1000)
 *     - val[] (int[]): Array of item values (1 ≤ val[i] ≤ 1000)
 *     - wt[] (int[]): Array of item weights (1 ≤ wt[i] ≤ 1000)
 *     - n = val.length = wt.length (1 ≤ n ≤ 1000)
 * 
 * Output:
 *     - Maximum value that can be achieved with given capacity
 * 
 * Example:
 *     Input: W = 4, val[] = {1, 2, 3}, wt[] = {4, 5, 1}
 *     Output: 3
 * 
 *     Explanation:
 *     We can include item with weight 1 and value 3.
 *     Total weight = 1 ≤ 4, Total value = 3
 * 
 *     Input: W = 50, val[] = {60, 100, 120}, wt[] = {10, 20, 30}
 *     Output: 220
 * 
 *     Explanation:
 *     We can include items with weights 10 and 20.
 *     Total weight = 30 ≤ 50, Total value = 60 + 100 = 160
 */

import java.util.*;

public class j01ZeroOneKnapsack {

    /**
     * Approach 1: Dynamic Programming (Top-Down Memoization)
     * 
     * Intuition:
     * - Same recursive structure as bottom-up but with memonization
     * - Avoid recalculating overlapping subproblems
     * - More intuitive recursive approach
     * 
     * Explanation:
     * - Step 1: Create memonization table initialized with -1
     * - Step 2: Use recursive function with memonization
     * - Step 3: For each state (index, remaining capacity), calculate:
     *   * notTake: Skip current item
     *   * take: Include current item if possible
     * - Step 4: Store and return maximum value for current state
     * 
     * Time Complexity: O(n*W) where n is number of items and W is capacity
     * Space Complexity: O(n*W) for memonization table + O(n) recursion stack
     * 
     * @param W    Knapsack capacity (1 ≤ W ≤ 1000)
     * @param val  Array of item values (1 ≤ val[i] ≤ 1000)
     * @param wt   Array of item weights (1 ≤ wt[i] ≤ 1000)
     * @return     Maximum value achievable with given capacity
     */
    public static int knapsackMemoization(int W, int val[], int wt[]) {
        // Validate input parameters
        if (val == null || wt == null || val.length != wt.length || W < 0) {
            return 0;
        }
        
        int n = val.length;
        if (n == 0) return 0; // No items available
        
        // Create Memoization table initialized with -1
        int[][] dp = new int[n][W + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        // Start recursive solution from index 0 with full capacity
        return knapsackHelper(0, W, val, wt, dp);
    }

    /**
     * Helper Method: Recursive knapsack with memonization
     * 
     * Intuition:
     * - Recursively explore all possible combinations of items
     * - Use memonization to avoid recalculating same subproblems
     * - Base case: when we've considered all items or no capacity left
     * 
     * Explanation:
     * - Step 1: Check base cases (index out of bounds or no capacity)
     * - Step 2: Check if current state is already computed
     * - Step 3: Calculate notTake (skip current item)
     * - Step 4: Calculate take (include current item if possible)
     * - Step 5: Store and return maximum value for current state
     * 
     * Time Complexity: O(n*W)
     * Space Complexity: O(n*W) for memonization + O(n) recursion stack
     * 
     * @param index    Current item index (0 ≤ index < n)
     * @param capacity Remaining knapsack capacity (0 ≤ capacity ≤ W)
     * @param val      Array of item values
     * @param wt       Array of item weights
     * @param dp     Memoization table
     * @return         Maximum value achievable from current state
     */
    private static int knapsackHelper(int index, int capacity, int[] val, int[] wt, int[][] dp) {
        // Base case: no more items or no capacity left
        if (index >= val.length || capacity <= 0) {
            return 0;
        }
        
        // Check if current state is already computed
        if (dp[index][capacity] != -1) {
            return dp[index][capacity];
        }
        
        // Option 1: Don't include current item
        int notTake = knapsackHelper(index + 1, capacity, val, wt, dp);
        
        // Option 2: Include current item if capacity allows
        int take = 0;
        if (capacity >= wt[index]) {
            take = val[index] + knapsackHelper(index + 1, capacity - wt[index], val, wt, dp);
        }
        
        // Store maximum value for current state
        dp[index][capacity] = Math.max(take, notTake);
        return dp[index][capacity];
    }

    /*-
     * Approach 2: Dynamic Programming (Bottom-Up Tabulation)
     * 
     * Intuition:
     * - For each item, we have two choices: include it or exclude it
     * - If we include an item, we must have enough capacity remaining
     * - We build a 2D DP table where dp[i][j] represents the maximum value 
     *   achievable using first i items with capacity j
     * - The optimal substructure allows us to build solutions from smaller 
     *   subproblems
     * 
     * Explanation:
     * - Step 1: Create a 2D DP array with dimensions (n+1) x (W+1)
     * - Step 2: Initialize base cases: dp[0][cap] = 0 (no items) and 
     *   dp[index][0] = 0 (no capacity)
     * - Step 3: For each item i and capacity j, calculate:
     *   * notTake: Value without including current item (dp[index-1][cap])
     *   * take: Value including current item if capacity allows 
     *     (val[index-1] + dp[index-1][cap-wt[i-1]])
     * - Step 4: Take maximum of take and notTake for optimal solution
     * 
     * Time Complexity: O(n*W) where n is number of items and W is capacity
     * Space Complexity: O(n*W) for the DP table
     * 
     * @param W    Knapsack capacity (1 ≤ W ≤ 1000)
     * @param val  Array of item values (1 ≤ val[i] ≤ 1000)
     * @param wt   Array of item weights (1 ≤ wt[i] ≤ 1000)
     * @return     Maximum value achievable with given capacity
     */
    public static int knapsackTabulation(int W, int val[], int wt[]) {
        // Validate input parameters
        if (val == null || wt == null || val.length != wt.length || W < 0) {
            return 0;
        }
        
        int n = val.length;
        if (n == 0) return 0; // No items available
        
        // Create DP table with dimensions (n+1) x (W+1)
        int[][] dp = new int[n + 1][W + 1];
        
        // Fill DP table using bottom-up approach
        for (int index = 1; index <= n; index++) {
            for (int cap = 1; cap <= W; cap++) {
                // Option 1: Don't include current item
                int notTake = dp[index - 1][cap];
                
                // Option 2: Include current item if capacity allows
                int take = 0;
                if (cap >= wt[index - 1]) {
                    take = val[index - 1] + dp[index - 1][cap - wt[index - 1]];
                }
                
                // Take maximum of both options for optimal solution
                dp[index][cap] = Math.max(take, notTake);
            }
        }
        
        return dp[n][W];
    }

    /**
     * Approach 3: Dynamic Programming (Space-Optimized 1D Tabulation)
     * 
     * Intuition:
     * - Classic 0-1 knapsack DP can be reduced from a 2D table to a 1D array
     *   because each state dp[capacity] only depends on values from the
     *   previous row (previous item) at capacities <= current capacity.
     * - We simulate the row transition by keeping the previous row in `dp`
     *   and building the current row in `newDp` for each item.
     * - This preserves correctness (no item is counted more than once) while
     *   reducing space from O(n*W) to O(W).
     * 
     * Explanation:
     * - Step 1: Validate inputs; return 0 for invalid or empty cases.
     * - Step 2: Initialize a 1D dp array of size W+1 representing the best
     *   value achievable for each capacity when considering processed items.
     * - Step 3: For each item, build a new row (newDp) where for each capacity:
     *   * notTake = dp[capacity]  (carry forward previous best without item)
     *   * take = value[item] + dp[capacity - weight[item]] if weight fits
     *   Then set newDp[capacity] = max(take, notTake).
     * - Step 4: Replace dp with newDp and continue to next item.
     * - Step 5: Answer is dp[W].
     * 
     * Time Complexity: O(n * W) where n is number of items and W is capacity.
     * Space Complexity: O(W) for the single rolling DP array.
     * 
     * @param W    Knapsack capacity (0 ≤ W)
     * @param val  Array of item values; val.length == wt.length
     * @param wt   Array of item weights; wt[i] ≥ 0
     * @return     Maximum value achievable with capacity W using 0-1 items
     */
    public static int knapsackSpaceOptimized(int W, int val[], int wt[]) {
        // Validate input parameters
        if (val == null || wt == null || val.length != wt.length || W < 0) {
            return 0;
        }
        
        int n = val.length;
        if (n == 0) return 0; // No items available
        
        // Space-optimized DP: only need 1D array since we only need previous row
        int[] dp = new int[W + 1];
        
        // Process each item one by one
        for (int currentItem = 1; currentItem <= n; currentItem++) {
            // Create new DP array for current item
            int[] newDp = new int[W + 1];
            
            // For each possible capacity
            for (int currentCapacity = 1; currentCapacity <= W; currentCapacity++) {
                
                // Option 1: Don't take the current item
                // Use the result from processing previous items with same capacity
                int notTake = dp[currentCapacity];
                
                // Option 2: Take the current item (if capacity allows)
                int take = 0;
                int currentItemWeight = wt[currentItem - 1];
                int currentItemValue = val[currentItem - 1];
                
                if (currentCapacity >= currentItemWeight) {
                    // Add current item's value + best value from remaining capacity
                    int remainingCapacity = currentCapacity - currentItemWeight;
                    take = currentItemValue + dp[remainingCapacity];
                }
                
                // Choose the better option for current state
                newDp[currentCapacity] = Math.max(take, notTake);
            }
            
            // Update dp array for next iteration
            dp = newDp;
        }
        
        return dp[W];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        int[] val1 = {1, 2, 3};
        int[] wt1 = {4, 5, 1};
        System.out.println("Input: W=4, val=[1,2,3], wt=[4,5,1]");
        System.out.println("Expected: 3, Output (TB): " + knapsackTabulation(4, val1, wt1));
        System.out.println("Output (Memo): " + knapsackMemoization(4, val1, wt1));
        
        int[] val2 = {60, 100, 120};
        int[] wt2 = {10, 20, 30};
        System.out.println("\nInput: W=50, val=[60,100,120], wt=[10,20,30]");
        System.out.println("Expected: 220, Output (DP): " + knapsackTabulation(50, val2, wt2));
        System.out.println("Output (Memo): " + knapsackMemoization(50, val2, wt2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Empty arrays - Output (DP): " + knapsackTabulation(10, new int[]{}, new int[]{}));
        System.out.println("Empty arrays - Output (Memo): " + knapsackMemoization(10, new int[]{}, new int[]{}));
        System.out.println("Null arrays - Output (DP): " + knapsackTabulation(10, null, null));
        System.out.println("Null arrays - Output (Memo): " + knapsackMemoization(10, null, null));
        System.out.println("Zero capacity - Output (DP): " + knapsackTabulation(0, val1, wt1));
        System.out.println("Zero capacity - Output (Memo): " + knapsackMemoization(0, val1, wt1));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[] val3 = {1};
        int[] wt3 = {1};
        System.out.println("Single item - Output (DP): " + knapsackTabulation(1, val3, wt3));
        System.out.println("Single item - Output (Memo): " + knapsackMemoization(1, val3, wt3));
        
        int[] val4 = {1000, 1000, 1000};
        int[] wt4 = {1000, 1000, 1000};
        System.out.println("Maximum values - Output (DP): " + knapsackTabulation(1000, val4, wt4));
        System.out.println("Maximum values - Output (Memo): " + knapsackMemoization(1000, val4, wt4));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[] val5 = {1, 1, 1, 1, 1};
        int[] wt5 = {1, 1, 1, 1, 1};
        System.out.println("All same values - Output (DP): " + knapsackTabulation(3, val5, wt5));
        System.out.println("All same values - Output (Memo): " + knapsackMemoization(3, val5, wt5));
        
        int[] val6 = {10, 20, 30, 40, 50};
        int[] wt6 = {1, 2, 3, 4, 5};
        System.out.println("Increasing values - Output (DP): " + knapsackTabulation(10, val6, wt6));
        System.out.println("Increasing values - Output (Memo): " + knapsackMemoization(10, val6, wt6));
    }
}
