/**
 * LeetCode 746. Min Cost Climbing Stairs
 * 
 * Problem Statement:
 *     You are given an integer array cost where cost[i] is the cost of ith step on 
 *     a staircase. Once you pay the cost, you can either climb one or two steps. 
 *     You can either start from the step with index 0, or the step with index 1. 
 *     Return the minimum cost to reach the top of the floor.
 * 
 * Input:
 *     - cost: int[] (2 <= cost.length <= 1000, 0 <= cost[i] <= 999)
 * 
 * Output:
 *     - The minimum cost to reach the top of the floor
 * 
 * Example:
 *     Input: cost = [10,15,20]
 *     Output: 15
 * 
 *     Explanation:
 *     You will start at index 1. Pay 15 and climb two steps to reach the top. 
 *     The total cost is 15.
 * 
 *     Input: cost = [1,100,1,1,1,100,1,1,100,1]
 *     Output: 6
 * 
 *     Explanation:
 *     You will start at index 0. Pay 1 and climb two steps to reach index 2. 
 *     Pay 1 and climb two steps to reach index 4. Pay 1 and climb two steps to 
 *     reach index 6. Pay 1 and climb one step to reach index 7. Pay 1 and climb 
 *     two steps to reach index 9. Pay 1 and climb one step to reach the top. 
 *     The total cost is 6.
 */

import java.util.Arrays;

public class j06MinCostToClimbStairs {

    /**
     * Approach: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - From any step i, we can climb 1 or 2 steps to reach i+1 or i+2
     * - We need to find the minimum cost path to reach the top
     * - Let minCost(i) denote the minimum cost to reach the top from step i
     * - Then: minCost(i) = cost[i] + min(minCost(i+1), minCost(i+2))
     * - Base case: minCost(n) = 0 (no cost to reach top from top)
     * - We can start from either step 0 or step 1, so return min(minCost(0), minCost(1))
     * 
     * Explanation:
     * - Step 1: Initialize a DP array with -1 to mark uncomputed states
     * - Step 2: Recursively compute minimum cost starting from step 0
     * - Step 3: For each step, try climbing 1 or 2 steps and find minimum cost
     * - Step 4: Add current step cost to the minimum of next steps
     * - Step 5: Return minimum of starting from step 0 or step 1
     * 
     * Time Complexity: O(n) - Each step is computed only once due to memoization
     * Space Complexity: O(n) - DP array and recursion stack depth
     * 
     * @param cost   Array where cost[i] is the cost of ith step
     * @return       Minimum cost to reach the top of the floor
     */
    public int minCostClimbingStairs(int[] cost) {
        // Handle edge case for invalid input
        if (cost == null || cost.length == 0) return 0;
        if (cost.length == 1) return cost[0];
        
        // Initialize DP array with -1 to mark uncomputed states
        int[] dp = new int[cost.length + 1];
        Arrays.fill(dp, -1);
        
        // Compute minimum cost starting from step 0
        minCost(cost, dp, 0);
        
        // Return minimum of starting from step 0 or step 1
        return Math.min(dp[0], dp[1]);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - From current step, try climbing 1 or 2 steps
     * - Find the minimum cost among these two options
     * - Add current step cost to the minimum
     * - Use memoization to cache computed results
     * 
     * Explanation:
     * - Step 1: Check if we've gone beyond the staircase (invalid case)
     * - Step 2: Check if we've reached the top (base case)
     * - Step 3: Check if result is already computed (memoization)
     * - Step 4: Try climbing 1 step and 2 steps
     * - Step 5: Find minimum cost among the two options
     * - Step 6: Add current step cost and store result
     * 
     * Time Complexity: O(1) per call due to memoization
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param cost    Array of step costs
     * @param dp      Memoization array storing minimum costs from each step
     * @param index   Current step index
     * @return        Minimum cost to reach the top from current step
     */
    public int minCost(int[] cost, int[] dp, int index) {
        // Invalid case: if we've gone beyond the staircase
        if (index > cost.length) {
            return Integer.MAX_VALUE;
        }
        
        // Base case: if we've reached the top, no additional cost
        if (index == cost.length) {
            return 0;
        }
        
        // Return memoized result if already computed
        if (dp[index] != -1) {
            return dp[index];
        }
        
        // Try climbing 1 step and 2 steps, find the minimum cost
        int minCost = Math.min(minCost(cost, dp, index + 1), minCost(cost, dp, index + 2));
        
        // Add current step cost to the minimum of next steps
        return dp[index] = minCost + cost[index];
    }

    public static void main(String[] args) {
        j06MinCostToClimbStairs solution = new j06MinCostToClimbStairs();
        
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: [10,15,20], Expected: 15, Output: " + solution.minCostClimbingStairs(new int[]{10,15,20}));
        System.out.println("Input: [1,100,1,1,1,100,1,1,100,1], Expected: 6, Output: " + solution.minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
        System.out.println("Input: [1,2], Expected: 1, Output: " + solution.minCostClimbingStairs(new int[]{1,2}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], Expected: 1, Output: " + solution.minCostClimbingStairs(new int[]{1}));
        System.out.println("Input: [], Expected: 0, Output: " + solution.minCostClimbingStairs(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " + solution.minCostClimbingStairs(null));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [0,0,0,0], Expected: 0, Output: " + solution.minCostClimbingStairs(new int[]{0,0,0,0}));
        System.out.println("Input: [999,999,999], Expected: 999, Output: " + solution.minCostClimbingStairs(new int[]{999,999,999}));
        System.out.println("Input: [1,1,1,1], Expected: 2, Output: " + solution.minCostClimbingStairs(new int[]{1,1,1,1}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [5,10,15,20,25], Expected: 30, Output: " + solution.minCostClimbingStairs(new int[]{5,10,15,20,25}));
        System.out.println("Input: [1,0,0,1], Expected: 0, Output: " + solution.minCostClimbingStairs(new int[]{1,0,0,1}));
        System.out.println("Input: [0,1,2,2], Expected: 2, Output: " + solution.minCostClimbingStairs(new int[]{0,1,2,2}));
        System.out.println("Input: [2,5,3,1,7,3,1], Expected: 9, Output: " + solution.minCostClimbingStairs(new int[]{2,5,3,1,7,3,1}));
    }
}
