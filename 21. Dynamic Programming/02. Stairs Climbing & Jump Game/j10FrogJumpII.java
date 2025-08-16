/**
 * Frog Jump II - Minimum Energy Required with Variable Jumps
 * 
 * Problem Statement:
 *     There is a frog on the 1st step of an N stairs long staircase. The frog 
 *     wants to reach the Nth stair. HEIGHT[i] is the height of the (i+1)th stair. 
 *     If Frog jumps from ith to jth stair, the energy lost in the jump is given 
 *     by |HEIGHT[i-1] - HEIGHT[j-1]|. The frog can jump at most K steps at a time.
 *     Your task is to find the minimum total energy used by the frog to reach 
 *     from 1st stair to Nth stair.
 * 
 * Input:
 *     - n: int (1 <= n <= 10^5)
 *     - k: int (1 <= k <= n) - maximum jump length
 *     - heights: int[] (length n, 0 <= heights[i] <= 10^4)
 * 
 * Output:
 *     - Minimum energy required to reach the nth stair
 * 
 * Example:
 *     Input: n = 4, k = 2, heights = [10,20,30,10]
 *     Output: 20
 * 
 *     Explanation:
 *     The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost).
 *     Then a jump from 2nd stair to the last stair (|10-20| = 10 energy lost).
 *     So, the total energy lost is 20.
 * 
 *     Input: n = 5, k = 3, heights = [10,20,30,40,50]
 *     Output: 30
 * 
 *     Explanation:
 *     The frog can jump from 1st stair to 4th stair (|40-10| = 30 energy lost).
 *     Then a jump from 4th stair to the last stair (|50-40| = 10 energy lost).
 *     Total energy = 30 + 10 = 40, but there's a better path.
 *     Better path: 1->2 (10) + 2->5 (30) = 40, or 1->3 (20) + 3->5 (10) = 30.
 */

import java.util.Arrays;

public class j10FrogJumpII {

    /**
     * Approach 1: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - From any stair i, the frog can jump to any stair from i+1 to i+k
     * - We need to find the minimum energy path to reach the nth stair
     * - Let dp[i] denote the minimum energy to reach stair n from stair i
     * - Then: dp[i] = min(dp[i+1] + |heights[i+1] - heights[i]|, 
     *                     dp[i+2] + |heights[i+2] - heights[i]|, ..., 
     *                     dp[i+k] + |heights[i+k] - heights[i]|)
     * - Base case: dp[n-1] = 0 (no energy needed from last stair to last stair)
     * - We use memoization to avoid recalculating overlapping subproblems
     * 
     * Explanation:
     * - Step 1: Initialize a DP array with -1 to mark uncomputed states
     * - Step 2: Recursively compute minimum energy starting from stair 0
     * - Step 3: For each stair, try jumping to all possible stairs within k steps
     * - Step 4: Calculate energy for each jump and find minimum
     * - Step 5: Store computed results in DP array to avoid recalculation
     * 
     * Time Complexity: O(n * k) - Each stair is computed once, but each computation
     *                  involves checking k possible jumps
     * Space Complexity: O(n) - DP array and recursion stack depth
     * 
     * @param n        Number of stairs
     * @param k        Maximum jump length allowed
     * @param heights  Array where heights[i] is the height of (i+1)th stair
     * @return         Minimum energy required to reach the nth stair
     */
    public static int frogJump(int n, int k, int heights[]) {
        // Handle edge cases
        if (heights == null || heights.length == 0)
            return 0;
        if (n == 1)
            return 0; // Already at destination

        // Initialize DP array with -1 to mark uncomputed states
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // Compute minimum energy starting from first stair
        return findMinCost(heights, dp, k, 0);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - From current stair, try jumping to all possible stairs within k steps
     * - Find the minimum energy among all these options
     * - Use memoization to cache computed results
     * 
     * Explanation:
     * - Step 1: Check if we've reached the destination (base case)
     * - Step 2: Check if result is already computed (memoization)
     * - Step 3: Try jumping to all stairs from i+1 to i+k if within bounds
     * - Step 4: Calculate energy for each jump and find minimum
     * - Step 5: Store and return the computed result
     * 
     * Time Complexity: O(k) per call due to memoization
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param heights  Array of stair heights
     * @param dp       Memoization array storing minimum energy from each stair
     * @param k        Maximum jump length allowed
     * @param index    Current stair index
     * @return         Minimum energy to reach the end from current stair
     */
    public static int findMinCost(int[] heights, int[] dp, int k, int index) {
        // Base case: if we've reached the last stair, no energy needed
        if (index == heights.length - 1)
            return 0;
        
        // Return memoized result if already computed
        if (dp[index] != -1)
            return dp[index];
        
        // Initialize answer to maximum possible value
        int ans = Integer.MAX_VALUE;
        
        // Try jumping to all possible stairs within k steps
        for (int i = 1; i <= k; i++) {
            // Check if the jump is within bounds
            if (index + i < heights.length) {
                // Calculate energy for this jump and recursively find minimum from next stair
                ans = Math.min(ans, findMinCost(heights, dp, k, index + i) + 
                              Math.abs(heights[index + i] - heights[index]));
            }
        }
        
        // Store and return the computed result
        return dp[index] = ans;
    }

    /**
     * Approach 2: Dynamic Programming with Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Instead of using recursion with memoization, we use iterative approach
     * - We build the solution from bottom-up, starting from the last stair
     * - Each stair i is computed using previously computed values from i+1 to i+k
     * - This eliminates the overhead of recursive function calls
     * 
     * Explanation:
     * - Step 1: Initialize DP array with -1 and set base case
     * - Step 2: Iterate backwards from n-2 to 0
     * - Step 3: For each stair, try jumping to all stairs from i+1 to i+k
     * - Step 4: Calculate energy for each jump and find minimum
     * - Step 5: Return dp[0] (minimum energy from first stair)
     * 
     * Time Complexity: O(n * k) - Each stair is computed once, but each computation
     *                  involves checking k possible jumps
     * Space Complexity: O(n) - DP array
     * 
     * @param n        Number of stairs
     * @param k        Maximum jump length allowed
     * @param heights  Array where heights[i] is the height of (i+1)th stair
     * @return         Minimum energy required to reach the nth stair
     */
    public static int frogJumpTabulation(int n, int k, int heights[]) {
        // Handle edge cases
        if (heights == null || heights.length == 0) return 0;
        if (n == 1) return 0;  // Already at destination
        
        // Initialize DP array with -1 to mark uncomputed states
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        
        // Base case: no energy needed from last stair to last stair
        dp[n - 1] = 0;
        
        // Iterate backwards from second-to-last stair to first stair
        for (int i = n - 2; i >= 0; i--) {
            int ans = Integer.MAX_VALUE; // Initialize to maximum possible value
            
            // Try jumping to all possible stairs from i+1 to i+k
            for (int j = 1; j <= k; j++) {
                // Check if the jump is within bounds
                if (i + j < n) {
                    // Calculate energy for this jump and add to previously computed result
                    ans = Math.min(ans, dp[i + j] + Math.abs(heights[i + j] - heights[i]));
                }
            }
            
            // Store the minimum energy for current stair
            dp[i] = ans;
        }
        
        // Return minimum energy required from first stair
        return dp[0];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: n=4, k=2, heights=[10,20,30,10], Expected: 20");
        System.out.println("Memoization Output: " + frogJump(4, 2, new int[]{10,20,30,10}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(4, 2, new int[]{10,20,30,10}));
        
        System.out.println("\nInput: n=5, k=3, heights=[10,20,30,40,50], Expected: 30");
        System.out.println("Memoization Output: " + frogJump(5, 3, new int[]{10,20,30,40,50}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(5, 3, new int[]{10,20,30,40,50}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, k=1, heights=[10], Expected: 0");
        System.out.println("Memoization Output: " + frogJump(1, 1, new int[]{10}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(1, 1, new int[]{10}));
        
        System.out.println("Input: n=2, k=1, heights=[10,20], Expected: 10");
        System.out.println("Memoization Output: " + frogJump(2, 1, new int[]{10,20}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(2, 1, new int[]{10,20}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=6, k=2, heights=[30,10,60,10,60,50], Expected: 40");
        System.out.println("Memoization Output: " + frogJump(6, 2, new int[]{30,10,60,10,60,50}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(6, 2, new int[]{30,10,60,10,60,50}));
        
        System.out.println("Input: n=4, k=3, heights=[10,20,30,10], Expected: 20");
        System.out.println("Memoization Output: " + frogJump(4, 3, new int[]{10,20,30,10}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(4, 3, new int[]{10,20,30,10}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=4, k=1, heights=[0,0,0,0], Expected: 0");
        System.out.println("Memoization Output: " + frogJump(4, 1, new int[]{0,0,0,0}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(4, 1, new int[]{0,0,0,0}));
        
        System.out.println("Input: n=5, k=4, heights=[100,50,100,50,100], Expected: 100");
        System.out.println("Memoization Output: " + frogJump(5, 4, new int[]{100,50,100,50,100}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(5, 4, new int[]{100,50,100,50,100}));
        
        // Test Case 5: Large k values
        System.out.println("\nLarge k Values:");
        System.out.println("Input: n=6, k=5, heights=[10,20,30,40,50,60], Expected: 50");
        System.out.println("Memoization Output: " + frogJump(6, 5, new int[]{10,20,30,40,50,60}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(6, 5, new int[]{10,20,30,40,50,60}));
    }
}
