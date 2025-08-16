/**
 * Frog Jump - Minimum Energy Required
 * 
 * Problem Statement:
 *     There is a frog on the 1st step of an N stairs long staircase. The frog 
 *     wants to reach the Nth stair. HEIGHT[i] is the height of the (i+1)th stair. 
 *     If Frog jumps from ith to jth stair, the energy lost in the jump is given 
 *     by |HEIGHT[i-1] - HEIGHT[j-1]|. In the Frog is on ith staircase, he can 
 *     jump either to (i+1)th stair or to (i+2)th stair. Your task is to find 
 *     the minimum total energy used by the frog to reach from 1st stair to Nth stair.
 * 
 * Input:
 *     - n: int (1 <= n <= 10^5)
 *     - heights: int[] (length n, 0 <= heights[i] <= 10^4)
 * 
 * Output:
 *     - Minimum energy required to reach the nth stair
 * 
 * Example:
 *     Input: n = 4, heights = [10,20,30,10]
 *     Output: 20
 * 
 *     Explanation:
 *     The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost).
 *     Then a jump from 2nd stair to the last stair (|10-20| = 10 energy lost).
 *     So, the total energy lost is 20.
 */

import java.util.Arrays;

public class j09FrogJumpMinimumEnergyRequired {

    /**
     * Approach 1: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - From any stair i, the frog can jump to stair i+1 or i+2
     * - We need to find the minimum energy path to reach the nth stair
     * - Let dp[i] denote the minimum energy to reach stair n from stair i
     * - Then: dp[i] = min(dp[i+1] + |heights[i+1] - heights[i]|, dp[i+2] + |heights[i+2] - heights[i]|)
     * - Base case: dp[n-1] = 0 (no energy needed from last stair to last stair)
     * - We use memoization to avoid recalculating overlapping subproblems
     * 
     * Explanation:
     * - Step 1: Initialize a DP array with -1 to mark uncomputed states
     * - Step 2: Recursively compute minimum energy starting from stair 0
     * - Step 3: For each stair, try jumping to i+1 and i+2
     * - Step 4: Calculate energy for each jump and find minimum
     * - Step 5: Store computed results in DP array to avoid recalculation
     * 
     * Time Complexity: O(n) - Each stair is computed only once due to memoization
     * Space Complexity: O(n) - DP array and recursion stack depth
     * 
     * @param n        Number of stairs
     * @param heights  Array where heights[i] is the height of (i+1)th stair
     * @return         Minimum energy required to reach the nth stair
     */
    public static int frogJump(int n, int heights[]) {
        // Handle edge cases
        if (heights == null || heights.length == 0) return 0;
        if (n == 1) return 0;  // Already at destination
        
        // Initialize DP array with -1 to mark uncomputed states
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // Compute minimum energy starting from first stair
        return findMinCost(heights, dp, 0);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - From current stair, try jumping to next stair or stair after next
     * - Find the minimum energy among these two options
     * - Use memoization to cache computed results
     * 
     * Explanation:
     * - Step 1: Check if we've reached the destination (base case)
     * - Step 2: Check if result is already computed (memoization)
     * - Step 3: Try jumping to next stair (i+1) if within bounds
     * - Step 4: Try jumping to stair after next (i+2) if within bounds
     * - Step 5: Find minimum energy among valid jumps
     * - Step 6: Store and return the computed result
     * 
     * Time Complexity: O(1) per call due to memoization
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param heights  Array of stair heights
     * @param dp       Memoization array storing minimum energy from each stair
     * @param index    Current stair index
     * @return         Minimum energy to reach the end from current stair
     */
    public static int findMinCost(int[] heights, int[] dp, int index) {
        // Base case: if we've reached the last stair, no energy needed
        if (index == heights.length - 1) {
            return 0;
        }

        // Return memoized result if already computed
        if (dp[index] != -1) {
            return dp[index];
        }

        // Try jumping to next stair (i+1) if within bounds
        int ans1 = (index + 1) < heights.length
                ? findMinCost(heights, dp, index + 1) + Math.abs(heights[index + 1] - heights[index])
                : Integer.MAX_VALUE;
        
        // Try jumping to stair after next (i+2) if within bounds
        int ans2 = (index + 2) < heights.length
                ? findMinCost(heights, dp, index + 2) + Math.abs(heights[index + 2] - heights[index])
                : Integer.MAX_VALUE;

        // Find minimum energy among the two options and store result
        dp[index] = Math.min(ans1, ans2);

        return dp[index];
    }

    /**
     * Approach 2: Dynamic Programming with Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Instead of using recursion with memoization, we use iterative approach
     * - We build the solution from bottom-up, starting from the last stair
     * - Each stair i is computed using previously computed values from i+1 and i+2
     * - This eliminates the overhead of recursive function calls
     * 
     * Explanation:
     * - Step 1: Initialize DP array with -1 and set base case
     * - Step 2: Iterate backwards from n-2 to 0
     * - Step 3: For each stair, try jumping to i+1 and i+2
     * - Step 4: Calculate energy for each jump and find minimum
     * - Step 5: Return dp[0] (minimum energy from first stair)
     * 
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(n) - DP array
     * 
     * @param n        Number of stairs
     * @param heights  Array where heights[i] is the height of (i+1)th stair
     * @return         Minimum energy required to reach the nth stair
     */
    public static int frogJumpTabulation(int n, int heights[]) {
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
            
            // Try jumping to next stair (i+1)
            ans = Math.min(ans, dp[i + 1] + Math.abs(heights[i + 1] - heights[i]));
            
            // Try jumping to stair after next (i+2) if within bounds
            if (i + 2 < n) {
                ans = Math.min(ans, dp[i + 2] + Math.abs(heights[i + 2] - heights[i]));
            }

            // Store the minimum energy for current stair
            dp[i] = ans;
        }

        // Return minimum energy required from first stair
        return dp[0];
    }

    /**
     * Approach 3: Space Optimized Dynamic Programming (Bottom-Up)
     * 
     * Intuition:
     * - Since we only need the previous two values (dp[i+1] and dp[i+2]) to compute dp[i]
     * - We can use just two variables instead of storing the entire DP array
     * - This reduces space complexity from O(n) to O(1)
     * - The logic remains the same as tabulation approach
     * 
     * Explanation:
     * - Step 1: Initialize two variables next1 and next2 to store dp[i+1] and dp[i+2]
     * - Step 2: Iterate backwards from n-2 to 0
     * - Step 3: For each stair, compute current energy using next1 and next2
     * - Step 4: Update next2 = next1, next1 = current for next iteration
     * - Step 5: Return next1 (which contains the result for first stair)
     * 
     * Time Complexity: O(n) - Single pass through the array
     * Space Complexity: O(1) - Only two variables used
     * 
     * @param n        Number of stairs
     * @param heights  Array where heights[i] is the height of (i+1)th stair
     * @return         Minimum energy required to reach the nth stair
     */
    public static int frogJumpSpaceOptimized(int n, int heights[]) {
        // Handle edge cases
        if (heights == null || heights.length == 0) return 0;
        if (n == 1) return 0;  // Already at destination
        
        // Initialize variables to store dp[i+1] and dp[i+2]
        // next1 represents dp[i+1], next2 represents dp[i+2]
        int next1 = 0;  // dp[n-1] = 0 (base case)
        int next2 = 0;  // dp[n] = 0 (beyond array, no energy needed)
        
        // Iterate backwards from second-to-last stair to first stair
        for (int i = n - 2; i >= 0; i--) {
            int curr = Integer.MAX_VALUE; // Initialize current energy to maximum
            
            // Try jumping to next stair (i+1) - use next1
            curr = Math.min(curr, next1 + Math.abs(heights[i + 1] - heights[i]));
            
            // Try jumping to stair after next (i+2) if within bounds - use next2
            if (i + 2 < n) {
                curr = Math.min(curr, next2 + Math.abs(heights[i + 2] - heights[i]));
            }
            
            // Update variables for next iteration
            // next2 becomes next1, next1 becomes current
            next2 = next1;
            next1 = curr;
        }

        // Return the minimum energy required from first stair
        return next1;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: n=4, heights=[10,20,30,10], Expected: 20");
        System.out.println("Memoization Output: " + frogJump(4, new int[]{10,20,30,10}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(4, new int[]{10,20,30,10}));
        
        System.out.println("\nInput: n=3, heights=[10,50,10], Expected: 40");
        System.out.println("Memoization Output: " + frogJump(3, new int[]{10,50,10}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(3, new int[]{10,50,10}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, heights=[10], Expected: 0");
        System.out.println("Memoization Output: " + frogJump(1, new int[]{10}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(1, new int[]{10}));
        
        System.out.println("Input: n=2, heights=[10,20], Expected: 10");
        System.out.println("Memoization Output: " + frogJump(2, new int[]{10,20}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(2, new int[]{10,20}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=5, heights=[10,20,30,40,50], Expected: 30");
        System.out.println("Memoization Output: " + frogJump(5, new int[]{10,20,30,40,50}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(5, new int[]{10,20,30,40,50}));
        
        System.out.println("Input: n=6, heights=[30,10,60,10,60,50], Expected: 40");
        System.out.println("Memoization Output: " + frogJump(6, new int[]{30,10,60,10,60,50}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(6, new int[]{30,10,60,10,60,50}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=4, heights=[0,0,0,0], Expected: 0");
        System.out.println("Memoization Output: " + frogJump(4, new int[]{0,0,0,0}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(4, new int[]{0,0,0,0}));
        
        System.out.println("Input: n=5, heights=[100,50,100,50,100], Expected: 100");
        System.out.println("Memoization Output: " + frogJump(5, new int[]{100,50,100,50,100}));
        System.out.println("Tabulation Output: " + frogJumpTabulation(5, new int[]{100,50,100,50,100}));
    }
}
