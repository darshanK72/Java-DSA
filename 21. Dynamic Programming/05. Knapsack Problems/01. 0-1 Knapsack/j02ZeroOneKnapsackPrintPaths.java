/**
 * GeeksForGeeks - 0-1 Knapsack Problem with Path Printing
 * 
 * Problem Statement:
 *     Given weights and values of n items, put these items in a knapsack of 
 *     capacity W to get the maximum total value in the knapsack. Additionally, 
 *     print all possible paths that lead to the optimal solution.
 * 
 * Input:
 *     - W (int): Maximum capacity of knapsack (W > 0)
 *     - val[] (int[]): Array of values for each item (val[i] >= 0)
 *     - wt[] (int[]): Array of weights for each item (wt[i] >= 0)
 *     - Both arrays must have same length n (n >= 0)
 * 
 * Output:
 *     - Print the maximum value achievable
 *     - Print all possible paths (item indices) that lead to optimal solution
 * 
 * Example:
 *     Input: W = 4, val[] = {1, 2, 3}, wt[] = {4, 5, 1}
 *     Output: 
 *         Final Answer : 3
 *         Path : 2 
 *         Path : 2 1 
 * 
 *     Explanation:
 *         Items: [0: val=1, wt=4], [1: val=2, wt=5], [2: val=3, wt=1]
 *         Optimal solution: Take item 2 (val=3, wt=1) for total value 3
 *         Alternative: Take items 2 and 1 (val=3+2=5, wt=1+5=6) exceeds capacity
 */

import java.util.LinkedList;
import java.util.Queue;

public class j02ZeroOneKnapsackPrintPaths {

    /**
     * Pair class to store state information for BFS traversal
     * Used to track current index, remaining capacity, and path taken
     */
    static class Pair {
        int index;      // Current item index being considered
        int capacity;   // Remaining knapsack capacity
        String path;    // String representation of items taken so far

        Pair(int i, int c, String p) {
            this.index = i;
            this.capacity = c;
            this.path = p;
        }
    }

    /**
     * Approach: Dynamic Programming with BFS Path Reconstruction
     * 
     * Intuition:
     * - Use bottom-up DP to build optimal solution table
     * - For each state (index, capacity), store maximum value achievable
     * - After building DP table, use BFS to trace back all optimal paths
     * - BFS ensures we find all possible combinations that achieve optimal value
     * 
     * Explanation:
     * - Step 1: Build DP table using standard 0-1 knapsack logic
     * - Step 2: For each cell, consider taking/not taking current item
     * - Step 3: Use BFS starting from final state (n, W) to trace paths
     * - Step 4: For each state, check if taking/not taking item leads to optimal
     * - Step 5: Add valid paths to queue and continue until reaching base case
     * 
     * Time Complexity: O(n*W) for DP table + O(2^n) for path reconstruction
     * Space Complexity: O(n*W) for DP table + O(2^n) for BFS queue
     * 
     * @param W      Maximum knapsack capacity (W > 0)
     * @param val    Array of item values (val[i] >= 0)
     * @param wt     Array of item weights (wt[i] >= 0)
     */
    public static void knapsackTabulation(int W, int[] val, int[] wt) {
        // Validate input parameters
        if (W <= 0 || val == null || wt == null || val.length != wt.length) {
            System.out.println("Invalid input parameters");
            return;
        }
        
        int n = val.length;
        
        // Handle edge case: no items available
        if (n == 0) {
            System.out.println("Final Answer : 0");
            System.out.println("Path : (no items available)");
            return;
        }
        
        // Initialize DP table with dimensions (n+1) x (W+1)
        int[][] dp = new int[n + 1][W + 1];
        
        // Fill DP table using bottom-up approach
        for (int index = 1; index <= n; index++) {
            for (int cap = 1; cap <= W; cap++) {
                // Option 1: Don't take current item
                int notTake = dp[index - 1][cap];
                
                // Option 2: Take current item (if capacity allows)
                int take = 0;
                if (cap >= wt[index - 1]) {
                    take = val[index - 1] + dp[index - 1][cap - wt[index - 1]];
                }
                
                // Store maximum of take and notTake options
                dp[index][cap] = Math.max(take, notTake);
            }
        }

        // Extract final optimal value from DP table
        int ans = dp[n][W];
        System.out.println("Final Answer : " + ans);

        // Use BFS to reconstruct all optimal paths
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(n, W, ""));
        
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            
            // Base case: reached beginning or no capacity left
            if (pair.index == 0 || pair.capacity == 0) {
                System.out.println("Path : " + pair.path);
            } else {
                // Check if taking current item leads to optimal solution
                if (pair.capacity >= wt[pair.index - 1]) {
                    int take = dp[pair.index - 1][pair.capacity - wt[pair.index - 1]] + val[pair.index - 1];
                    if (dp[pair.index][pair.capacity] == take) {
                        // Add current item to path and continue BFS
                        queue.add(new Pair(pair.index - 1, pair.capacity - wt[pair.index - 1], 
                                         pair.path + (pair.index - 1) + " "));
                    }
                }
                
                // Check if not taking current item leads to optimal solution
                int notTake = dp[pair.index - 1][pair.capacity];
                if (dp[pair.index][pair.capacity] == notTake) {
                    // Keep current path unchanged and continue BFS
                    queue.add(new Pair(pair.index - 1, pair.capacity, pair.path));
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Test 1:");
        knapsackTabulation(4, new int[]{1, 2, 3}, new int[]{4, 5, 1});
        
        System.out.println("\nTest 2:");
        knapsackTabulation(7, new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5});

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Test 3 - Zero capacity:");
        knapsackTabulation(0, new int[]{1, 2, 3}, new int[]{1, 2, 3});
        
        System.out.println("\nTest 4 - No items:");
        knapsackTabulation(10, new int[]{}, new int[]{});
        
        System.out.println("\nTest 5 - Single item:");
        knapsackTabulation(5, new int[]{10}, new int[]{3});

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Test 6 - All items too heavy:");
        knapsackTabulation(2, new int[]{10, 20, 30}, new int[]{5, 6, 7});
        
        System.out.println("\nTest 7 - All items fit:");
        knapsackTabulation(10, new int[]{1, 2, 3}, new int[]{1, 2, 3});

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Test 8 - Multiple optimal paths:");
        knapsackTabulation(6, new int[]{3, 3, 3}, new int[]{2, 2, 2});
        
        System.out.println("\nTest 9 - Zero values:");
        knapsackTabulation(5, new int[]{0, 0, 0}, new int[]{1, 2, 3});
    }
}
