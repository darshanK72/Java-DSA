/**
 * GeeksForGeeks - Unbounded Knapsack Problem (Duplicate Items Allowed)
 * 
 * Problem Statement:
 *     Given weights and values of n items, put these items in a knapsack of 
 *     capacity W to get the maximum total value. Unlike 0-1 knapsack, items 
 *     can be used multiple times (unbounded supply).
 * 
 * Input:
 *     - val[] (int[]): Array of values for each item (val[i] >= 0)
 *     - wt[] (int[]): Array of weights for each item (wt[i] >= 0)
 *     - capacity (int): Maximum capacity of knapsack (capacity > 0)
 *     - Both arrays must have same length n (n >= 0)
 * 
 * Output:
 *     - Maximum value achievable by selecting items (with duplicates allowed)
 * 
 * Example:
 *     Input: val[] = {1, 4, 5, 7}, wt[] = {1, 3, 4, 5}, capacity = 8
 *     Output: 11
 * 
 *     Explanation:
 *         Items: [0: val=1, wt=1], [1: val=4, wt=3], [2: val=5, wt=4], [3: val=7, wt=5]
 *         Optimal solution: Take item 1 twice (val=4*2=8, wt=3*2=6) + item 0 twice (val=1*2=2, wt=1*2=2)
 *         Total: value=10, weight=8 (within capacity)
 *         Alternative: Take item 3 once (val=7, wt=5) + item 0 three times (val=1*3=3, wt=1*3=3)
 *         Total: value=10, weight=8
 */

import java.util.Arrays;

public class j04KnapsackWithDuplicateItems {

    /**
     * Approach: Memoized Dynamic Programming (Top-Down)
     * 
     * Intuition:
     * - Use recursive approach with memoization to avoid redundant calculations
     * - For each item, try taking it 0 to maximum possible times (based on capacity)
     * - Unlike 0-1 knapsack, we can reuse items multiple times
     * - Store intermediate results in DP table to optimize performance
     * 
     * Explanation:
     * - Step 1: Initialize DP table with -1 to mark unvisited states
     * - Step 2: For each item, try taking it 0, 1, 2, ... times until capacity exhausted
     * - Step 3: For each count, calculate remaining capacity and current value
     * - Step 4: Recursively solve for remaining items with remaining capacity
     * - Step 5: Store maximum profit achievable from current state
     * 
     * Time Complexity: O(n * capacity * max_count) where max_count = capacity/min_weight
     * Space Complexity: O(n * capacity) for DP table + O(capacity) for recursion stack
     * 
     * @param val       Array of item values (val[i] >= 0)
     * @param wt        Array of item weights (wt[i] >= 0)
     * @param capacity  Maximum knapsack capacity (capacity > 0)
     * @return          Maximum value achievable with duplicate items allowed
     */
    public static int knapSackMemo(int val[], int wt[], int capacity) {
        // Validate input parameters
        if (val == null || wt == null || val.length != wt.length || capacity <= 0) {
            return 0;
        }
        
        int n = val.length;
        
        // Handle edge case: no items available
        if (n == 0) {
            return 0;
        }
        
        // Initialize DP table with -1 to mark unvisited states
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Start recursive solution from first item with full capacity
        return unboundedKnapsack(dp, val, wt, capacity, 0);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - Recursively explore all possible combinations of item selections
     * - For each item, try all possible quantities (0 to capacity/weight)
     * - Use memoization to avoid recalculating same subproblems
     * - Return maximum value achievable from current state
     * 
     * Explanation:
     * - Base case: if capacity <= 0 or index >= n, return 0
     * - If state already calculated, return memoized result
     * - For current item, try taking it 0, 1, 2, ... times
     * - For each count, calculate remaining capacity and current value
     * - Recursively solve for remaining items and add current value
     * - Store and return maximum profit achievable
     * 
     * Time Complexity: O(capacity/min_weight) for each state
     * Space Complexity: O(capacity) for recursion stack
     * 
     * @param dp        DP table for memoization
     * @param val       Array of item values
     * @param wt        Array of item weights
     * @param capacity  Remaining knapsack capacity
     * @param index     Current item index being considered
     * @return          Maximum value achievable from current state
     */
    public static int unboundedKnapsack(int[][] dp, int[] val, int[] wt, int capacity, int index) {
        // Base case: no capacity left or all items considered
        if (capacity <= 0 || index >= val.length) {
            return 0;
        }
        
        // Return memoized result if already calculated
        if (dp[index][capacity] != -1) {
            return dp[index][capacity];
        }
        
        // Initialize maximum profit for current state
        int maxProfit = 0;
        
        // Try taking current item 0, 1, 2, ... times until capacity exhausted
        for (int count = 0; count * wt[index] <= capacity; count++) {
            // Calculate remaining capacity after taking 'count' items
            int remainingCapacity = capacity - count * wt[index];
            
            // Calculate value of taking 'count' items
            int currValue = count * val[index];
            
            // Recursively solve for remaining items with remaining capacity
            int remainingValue = unboundedKnapsack(dp, val, wt, remainingCapacity, index + 1);
            
            // Update maximum profit achievable
            maxProfit = Math.max(maxProfit, remainingValue + currValue);
        }
        
        // Store and return maximum profit for current state
        return dp[index][capacity] = maxProfit;
    }

    /**
     * Approach 2: Bottom-Up Dynamic Programming (Tabulation)
     * 
     * Intuition:
     * - Build solution iteratively from smaller subproblems to larger ones
     * - For each state (index, capacity), consider all possible quantities of current item
     * - Unlike 0-1 knapsack, we can take multiple copies of same item
     * - Fill DP table systematically to avoid recursion overhead
     * 
     * Explanation:
     * - Step 1: Initialize DP table with base case (0 items = 0 value)
     * - Step 2: For each item and capacity, try taking item 0, 1, 2, ... times
     * - Step 3: For each count, calculate remaining capacity and current value
     * - Step 4: Use previously computed results from smaller subproblems
     * - Step 5: Store maximum value achievable for current state
     * 
     * Time Complexity: O(n * capacity * max_count) where max_count = capacity/min_weight
     * Space Complexity: O(n * capacity) for DP table
     * 
     * @param val       Array of item values (val[i] >= 0)
     * @param wt        Array of item weights (wt[i] >= 0)
     * @param capacity  Maximum knapsack capacity (capacity > 0)
     * @return          Maximum value achievable with duplicate items allowed
     */
    public static int knapsackTabulation(int val[], int wt[], int capacity) {
        // Validate input parameters
        if (val == null || wt == null || val.length != wt.length || capacity <= 0) {
            return 0;
        }
        
        int n = val.length;
        
        // Handle edge case: no items available
        if (n == 0) {
            return 0;
        }
        
        // Initialize DP table with base case (0 items = 0 value)
        int[][] dp = new int[n + 1][capacity + 1];
        
        // Fill DP table using bottom-up approach
        for (int index = 1; index <= n; index++) {
            for (int cap = 1; cap <= capacity; cap++) {
                // Initialize maximum profit for current state
                int maxProfit = 0;
                
                // Try taking current item 0, 1, 2, ... times until capacity exhausted
                for (int count = 0; count * wt[index - 1] <= cap; count++) {
                    // Calculate remaining capacity after taking 'count' items
                    int remainingCapacity = cap - count * wt[index - 1];
                    
                    // Calculate value of taking 'count' items
                    int currValue = count * val[index - 1];
                    
                    // Add value from remaining items using previously computed result
                    int totalValue = dp[index - 1][remainingCapacity] + currValue;
                    
                    // Update maximum profit achievable for current state
                    maxProfit = Math.max(maxProfit, totalValue);
                }
                
                // Store maximum profit for current state
                dp[index][cap] = maxProfit;
            }
        }
        
        // Return optimal solution for full capacity with all items
        return dp[n][capacity];
    }

    /**
     * Approach 3: Alternative Memoized DP (Simplified Recursion)
     * 
     * Intuition:
     * - Use simplified recursive approach with memoization
     * - For each item, make two choices: take or not take
     * - If we take an item, we can take it again (unbounded)
     * - If we don't take, move to next item
     * - More intuitive than counting approach
     * 
     * Explanation:
     * - Step 1: Initialize DP table with -1 to mark unvisited states
     * - Step 2: For each state, try not taking current item (move to next)
     * - Step 3: If capacity allows, try taking current item (stay at same item)
     * - Step 4: Return maximum of take and not take options
     * - Step 5: Use memoization to avoid redundant calculations
     * 
     * Time Complexity: O(n * capacity) - each state calculated once
     * Space Complexity: O(n * capacity) for DP table + O(capacity) for recursion stack
     * 
     * @param val       Array of item values (val[i] >= 0)
     * @param wt        Array of item weights (wt[i] >= 0)
     * @param capacity  Maximum knapsack capacity (capacity > 0)
     * @return          Maximum value achievable with duplicate items allowed
     */
    public static int knapSack2Memo(int val[], int wt[], int capacity) {
        // Validate input parameters
        if (val == null || wt == null || val.length != wt.length || capacity <= 0) {
            return 0;
        }
        
        int n = val.length;
        
        // Handle edge case: no items available
        if (n == 0) {
            return 0;
        }
        
        // Initialize DP table with -1 to mark unvisited states
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // Start recursive solution from first item with full capacity
        return knapsack2MemoHelper(dp, val, wt, capacity, 0);
    }
    
    /**
     * Helper Method: Simplified Recursive DP with Memoization
     * 
     * Intuition:
     * - Recursively explore take/not-take decisions for each item
     * - When taking an item, stay at same index (unbounded supply)
     * - When not taking, move to next index
     * - Use memoization to optimize repeated subproblems
     * 
     * Explanation:
     * - Base case: if index >= n or capacity <= 0, return 0
     * - If state already calculated, return memoized result
     * - Option 1: Don't take current item (move to next item)
     * - Option 2: Take current item if capacity allows (stay at same item)
     * - Return maximum of both options
     * 
     * Time Complexity: O(1) for each state (calculated once)
     * Space Complexity: O(capacity) for recursion stack
     * 
     * @param dp        DP table for memoization
     * @param val       Array of item values
     * @param wt        Array of item weights
     * @param capacity  Remaining knapsack capacity
     * @param index     Current item index being considered
     * @return          Maximum value achievable from current state
     */
    public static int knapsack2MemoHelper(int[][] dp, int[] val, int[] wt, int capacity, int index) {
        // Base case: no items left or no capacity remaining
        if (index >= val.length || capacity <= 0) {
            return 0;
        }
        
        // Return memoized result if already calculated
        if (dp[index][capacity] != -1) {
            return dp[index][capacity];
        }
        
        // Option 1: Don't take current item (move to next item)
        int notTake = knapsack2MemoHelper(dp, val, wt, capacity, index + 1);
        
        // Option 2: Take current item if capacity allows (stay at same item)
        int take = 0;
        if (capacity >= wt[index]) {
            take = Math.max(take, knapsack2MemoHelper(dp, val, wt, capacity - wt[index], index) + val[index]);
        }
        
        // Store and return maximum of take and not take options
        return dp[index][capacity] = Math.max(take, notTake);
    }

    /**
     * Approach 4: Alternative Bottom-Up DP (Simplified Tabulation)
     * 
     * Intuition:
     * - Build solution iteratively using simplified take/not-take logic
     * - For each state, consider not taking current item vs taking it
     * - When taking, use result from same row (unbounded supply)
     * - When not taking, use result from previous row
     * - More efficient than counting approach
     * 
     * Explanation:
     * - Step 1: Initialize DP table with base case (0 items = 0 value)
     * - Step 2: For each item and capacity, consider two options
     * - Step 3: Not take: use result from previous item with same capacity
     * - Step 4: Take: use result from same item with reduced capacity + current value
     * - Step 5: Store maximum of both options
     * 
     * Time Complexity: O(n * capacity) - each cell calculated once
     * Space Complexity: O(n * capacity) for DP table
     * 
     * @param val       Array of item values (val[i] >= 0)
     * @param wt        Array of item weights (wt[i] >= 0)
     * @param capacity  Maximum knapsack capacity (capacity > 0)
     * @return          Maximum value achievable with duplicate items allowed
     */
    public static int knapSack2Tabulation(int val[], int wt[], int capacity) {
        // Validate input parameters
        if (val == null || wt == null || val.length != wt.length || capacity <= 0) {
            return 0;
        }
        
        int n = val.length;
        
        // Handle edge case: no items available
        if (n == 0) {
            return 0;
        }
        
        // Initialize DP table with base case (0 items = 0 value)
        int[][] dp = new int[n + 1][capacity + 1];
        
        // Fill DP table using bottom-up approach
        for (int index = 1; index <= n; index++) {
            for (int cap = 1; cap <= capacity; cap++) {
                // Option 1: Don't take current item (use result from previous item)
                int notTake = dp[index - 1][cap];
                
                // Option 2: Take current item if capacity allows
                int take = 0;
                if (cap >= wt[index - 1]) {
                    // Use result from same item with reduced capacity (unbounded supply)
                    take = dp[index][cap - wt[index - 1]] + val[index - 1];
                }
                
                // Store maximum of take and not take options
                dp[index][cap] = Math.max(take, notTake);
            }
        }
        
        // Return optimal solution for full capacity with all items
        return dp[n][capacity];
    }

    /**
     * Approach 5: Space-Optimized Bottom-Up DP (1D Array)
     * 
     * Intuition:
     * - Use single 1D array instead of 2D array to reduce space complexity
     * - For unbounded knapsack, we can reuse same row (same item multiple times)
     * - When taking an item, use result from same array with reduced capacity
     * - When not taking, use current value in same array
     * - Most space-efficient approach for unbounded knapsack
     * 
     * Explanation:
     * - Step 1: Initialize 1D DP array with size (capacity + 1)
     * - Step 2: For each item, iterate through all capacities
     * - Step 3: Not take: use current value in dp[cap] (no change)
     * - Step 4: Take: use value from dp[cap - wt[index-1]] + current item value
     * - Step 5: Update dp[cap] with maximum of take and not take
     * - Step 6: Since we can reuse items, we update in-place
     * 
     * Time Complexity: O(n * capacity) - each cell calculated once
     * Space Complexity: O(capacity) - only one array needed
     * 
     * @param val       Array of item values (val[i] >= 0)
     * @param wt        Array of item weights (wt[i] >= 0)
     * @param capacity  Maximum knapsack capacity (capacity > 0)
     * @return          Maximum value achievable with duplicate items allowed
     */
    public static int knapSack2SpaceOptimized(int val[], int wt[], int capacity) {
        // Validate input parameters
        if (val == null || wt == null || val.length != wt.length || capacity <= 0) {
            return 0;
        }
        
        int n = val.length;
        
        // Handle edge case: no items available
        if (n == 0) {
            return 0;
        }
        
        // Initialize 1D DP array with base case (0 capacity = 0 value)
        int[] dp = new int[capacity + 1];
        
        // Fill DP array using space-optimized approach
        for (int index = 1; index <= n; index++) {
            for (int cap = 1; cap <= capacity; cap++) {
                // Option 1: Don't take current item (use current value in dp[cap])
                int notTake = dp[cap];
                
                // Option 2: Take current item if capacity allows
                int take = 0;
                if (cap >= wt[index - 1]) {
                    // Use result from same array with reduced capacity (unbounded supply)
                    take = dp[cap - wt[index - 1]] + val[index - 1];
                }
                
                // Update current capacity with maximum of take and not take options
                dp[cap] = Math.max(take, notTake);
            }
        }
        
        // Return optimal solution for full capacity
        return dp[capacity];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Test 1: val=[1,4,5,7], wt=[1,3,4,5], capacity=8");
        System.out.println("Expected: 11, Output: " + knapSackMemo(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 8));
        
        System.out.println("\nTest 2: val=[10, 40, 50, 70], wt=[1, 3, 4, 5], capacity=8");
        System.out.println("Expected: 110, Output: " + knapSackMemo(new int[]{10, 40, 50, 70}, new int[]{1, 3, 4, 5}, 8));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Test 3 - Zero capacity:");
        System.out.println("Expected: 0, Output: " + knapsackTabulation(new int[]{1, 2, 3}, new int[]{1, 2, 3}, 0));
        
        System.out.println("\nTest 4 - No items:");
        System.out.println("Expected: 0, Output: " + knapsackTabulation(new int[]{}, new int[]{}, 10));
        
        System.out.println("\nTest 5 - Single item:");
        System.out.println("Expected: 30, Output: " + knapSackMemo(new int[]{10}, new int[]{2}, 6));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Test 6 - All items too heavy:");
        System.out.println("Expected: 0, Output: " + knapsackTabulation(new int[]{10, 20, 30}, new int[]{5, 6, 7}, 2));
        
        System.out.println("\nTest 7 - All items fit multiple times:");
        System.out.println("Expected: 15, Output: " + knapSackMemo(new int[]{5}, new int[]{1}, 3));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Test 8 - Zero values:");
        System.out.println("Expected: 0, Output: " + knapSackMemo(new int[]{0, 0, 0}, new int[]{1, 2, 3}, 5));
        
        System.out.println("\nTest 9 - Large capacity:");
        System.out.println("Expected: 1000, Output: " + knapsackTabulation(new int[]{10}, new int[]{1}, 100));
        
        System.out.println("\nTest 10 - Optimal solution uses multiple items:");
        System.out.println("Expected: 6, Output: " + knapsackTabulation(new int[]{1, 2}, new int[]{1, 1}, 3));
    }
}