/**
 * LeetCode 55. Jump Game I
 * 
 * Problem Statement:
 *     You are given an integer array nums. You are initially positioned at the 
 *     array's first index, and each element in the array represents your maximum 
 *     jump length at that position. Return true if you can reach the last index, 
 *     or false otherwise.
 * 
 * Input:
 *     - nums: int[] (1 <= nums.length <= 10^4, 0 <= nums[i] <= 10^5)
 * 
 * Output:
 *     - true if you can reach the last index, false otherwise
 * 
 * Example:
 *     Input: nums = [2,3,1,1,4]
 *     Output: true
 * 
 *     Explanation:
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 *     Input: nums = [3,2,1,0,4]
 *     Output: false
 * 
 *     Explanation:
 *     You will always arrive at index 3 no matter what. Its maximum jump length 
 *     is 0, which makes it impossible to reach the last index.
 */

import java.util.Arrays;

public class j04JumpGameI {

    /**
     * Approach: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - From any index i, we can jump to any index from i+1 to i+nums[i]
     * - We need to check if any of these jumps can lead to the destination
     * - Let canJump(i) denote whether we can reach the end from index i
     * - Then: canJump(i) = OR(canJump(i+1), canJump(i+2), ..., canJump(i+nums[i]))
     * - Base case: canJump(n-1) = true (already at destination)
     * - We use memoization to avoid recalculating overlapping subproblems
     * 
     * Explanation:
     * - Step 1: Initialize a DP array with -1 to mark uncomputed states
     * - Step 2: Recursively check if we can reach the end starting from index 0
     * - Step 3: For each position, try all possible jump lengths
     * - Step 4: If any jump leads to success, return true
     * - Step 5: Store computed results in DP array to avoid recalculation
     * 
     * Time Complexity: O(n^2) - For each index i, we may explore up to nums[i] next positions
     * Space Complexity: O(n) - DP array and recursion stack depth
     * 
     * @param nums   Array where nums[i] is the maximum jump length from index i
     * @return       true if we can reach the last index, false otherwise
     */
    public boolean canJump(int[] nums) {
        // Handle edge case for invalid input
        if (nums == null || nums.length <= 1) return true;
        
        // Initialize DP array with -1 to mark uncomputed states
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        
        // Check if we can reach the end starting from index 0
        return canJumpDP(nums, dp, 0) == 1;
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - From current index, try all possible jump lengths
     * - If any jump leads to success, return success
     * - Use memoization to cache computed results
     * 
     * Explanation:
     * - Step 1: Check if we've reached the destination (base case)
     * - Step 2: Check if result is already computed (memoization)
     * - Step 3: Try all possible jump lengths from 1 to nums[index]
     * - Step 4: For each valid jump, recursively check if we can reach the end
     * - Step 5: If any jump leads to success, return success
     * - Step 6: Store and return the computed result
     * 
     * Time Complexity: O(outdegree(index)) per uncached state
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param nums    Array of maximum jump lengths
     * @param dp      Memoization array storing results from each index
     * @param index   Current position in the array
     * @return        1 if we can reach the end from current index, 0 otherwise
     */
    public int canJumpDP(int[] nums, int[] dp, int index) {
        // Base case: if we're at the destination, we can reach it
        if (index == nums.length - 1) {
            return 1;  // Success - we've reached the destination
        }
        
        // Return memoized result if already computed
        if (dp[index] != -1) {
            return dp[index];
        }
        
        // Try all possible jump lengths from 1 to nums[index]
        for (int i = 1; i <= nums[index]; i++) {
            int jump = index + i; // Next position after jumping i steps
            
            // Ensure next position is within bounds
            if (jump < nums.length) {
                // Recursively check if we can reach the end from next position
                if (canJumpDP(nums, dp, jump) == 1) {
                    // If any jump leads to success, return success immediately
                    return dp[index] = 1;
                }
            }
        }

        // If no jump leads to success, return failure
        return dp[index] = 0;
    }

    public static void main(String[] args) {
        j04JumpGameI solution = new j04JumpGameI();
        
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: [2,3,1,1,4], Expected: true, Output: " + solution.canJump(new int[]{2,3,1,1,4}));
        System.out.println("Input: [3,2,1,0,4], Expected: false, Output: " + solution.canJump(new int[]{3,2,1,0,4}));
        System.out.println("Input: [1,2,3], Expected: true, Output: " + solution.canJump(new int[]{1,2,3}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], Expected: true, Output: " + solution.canJump(new int[]{1}));
        System.out.println("Input: [0], Expected: true, Output: " + solution.canJump(new int[]{0}));
        System.out.println("Input: null, Expected: true, Output: " + solution.canJump(null));
        System.out.println("Input: [], Expected: true, Output: " + solution.canJump(new int[]{}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1,1,1,1], Expected: true, Output: " + solution.canJump(new int[]{1,1,1,1}));
        System.out.println("Input: [0,1,2,3], Expected: false, Output: " + solution.canJump(new int[]{0,1,2,3}));
        System.out.println("Input: [2,0,0], Expected: true, Output: " + solution.canJump(new int[]{2,0,0}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [5,9,3,2,1,0,2,3,3,1,0,0], Expected: true, Output: " + solution.canJump(new int[]{5,9,3,2,1,0,2,3,3,1,0,0}));
        System.out.println("Input: [2,0], Expected: true, Output: " + solution.canJump(new int[]{2,0}));
        System.out.println("Input: [1,0,1,0], Expected: false, Output: " + solution.canJump(new int[]{1,0,1,0}));
        System.out.println("Input: [3,0,8,2,0,0,1], Expected: true, Output: " + solution.canJump(new int[]{3,0,8,2,0,0,1}));
    }
}
