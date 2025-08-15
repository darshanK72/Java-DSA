/**
 * LeetCode 45. Jump Game II
 * 
 * Problem Statement:
 *     Given an array of non-negative integers nums, you are initially positioned 
 *     at the first index of the array. Each element in the array represents your 
 *     maximum jump length at that position. Your goal is to reach the last index 
 *     in the minimum number of jumps. You can assume that you can always reach 
 *     the last index.
 * 
 * Input:
 *     - nums: int[] (1 <= nums.length <= 10^4, 0 <= nums[i] <= 1000)
 * 
 * Output:
 *     - The minimum number of jumps to reach the last index
 * 
 * Example:
 *     Input: nums = [2,3,1,1,4]
 *     Output: 2
 * 
 *     Explanation:
 *     The minimum number of jumps to reach the last index is 2. Jump 1 step from 
 *     index 0 to 1, then 3 steps to the last index.
 * 
 *     Input: nums = [2,3,0,1,4]
 *     Output: 2
 * 
 *     Explanation:
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */

import java.util.Arrays;

public class j05JumpGameII {

    /**
     * Approach: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - From any index i, we can jump to any index from i+1 to i+nums[i]
     * - We need to find the minimum jumps among all possible next positions
     * - Let minJumps(i) denote the minimum jumps needed from index i to reach the end
     * - Then: minJumps(i) = 1 + min(minJumps(i+1), minJumps(i+2), ..., minJumps(i+nums[i]))
     * - Base case: minJumps(n-1) = 0 (no jumps needed from destination)
     * - We use memoization to avoid recalculating overlapping subproblems
     * 
     * Explanation:
     * - Step 1: Initialize a DP array with -1 to mark uncomputed states
     * - Step 2: Use long[] to handle potential overflow in worst cases
     * - Step 3: Recursively compute minimum jumps starting from index 0
     * - Step 4: For each position, try all possible jump lengths and find minimum
     * - Step 5: Store computed results in DP array to avoid recalculation
     * 
     * Time Complexity: O(n^2) - For each index i, we may explore up to nums[i] next positions
     * Space Complexity: O(n) - DP array and recursion stack depth
     * 
     * @param nums   Array where nums[i] is the maximum jump length from index i
     * @return       Minimum number of jumps to reach the last index
     */
    public static int jump(int[] nums) {
        // Handle edge case for invalid input
        if (nums == null || nums.length <= 1) return 0;
        
        // Initialize DP array with -1 to mark uncomputed states
        // Use long[] to handle potential overflow in worst cases
        long[] dp = new long[nums.length + 1];
        Arrays.fill(dp, -1);
        
        // Compute minimum jumps starting from index 0
        return (int) minJumps(nums, dp, 0);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - From current index, try all possible jump lengths and find the minimum
     * - Use memoization to cache computed results and avoid recalculation
     * - Return 0 if we're already at the destination
     * 
     * Explanation:
     * - Step 1: Check if we've reached the destination (base case)
     * - Step 2: Check if result is already computed (memoization)
     * - Step 3: Try all possible jump lengths from 1 to nums[index]
     * - Step 4: For each valid jump, recursively compute minimum jumps from next position
     * - Step 5: Find the minimum among all possible jumps and add 1 for current jump
     * - Step 6: Store and return the computed result
     * 
     * Time Complexity: O(outdegree(index)) per uncached state
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param nums    Array of maximum jump lengths
     * @param dp      Memoization array storing minimum jumps from each index
     * @param index   Current position in the array
     * @return        Minimum jumps needed to reach the end from current index
     */
    public static long minJumps(int[] nums, long[] dp, int index) {
        // Base case: if we're at the destination, no jumps needed
        if (index == nums.length - 1) {
            return 0;
        }
        
        // Return memoized result if already computed
        if (dp[index] != -1) {
            return dp[index];
        }
        
        // Initialize minimum jumps to maximum possible value
        long minJump = Integer.MAX_VALUE;
        
        // Try all possible jump lengths from 1 to nums[index]
        for (int i = 1; i <= nums[index]; i++) {
            int jump = index + i; // Next position after jumping i steps
            
            // Ensure next position is within bounds
            if (jump < nums.length) {
                // Recursively compute minimum jumps from next position
                // Add 1 for the current jump
                minJump = Math.min(minJumps(nums, dp, jump) + 1, minJump);
            }
        }

        // Store computed result in DP array and return
        return dp[index] = minJump;
    }

    public static void main(String[] args) {
       
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: [2,3,1,1,4], Expected: 2, Output: " + jump(new int[]{2,3,1,1,4}));
        System.out.println("Input: [2,3,0,1,4], Expected: 2, Output: " + jump(new int[]{2,3,0,1,4}));
        System.out.println("Input: [1,2,3], Expected: 2, Output: " + jump(new int[]{1,2,3}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], Expected: 0, Output: " + jump(new int[]{1}));
        System.out.println("Input: [0], Expected: 0, Output: " + jump(new int[]{0}));
        System.out.println("Input: null, Expected: 0, Output: " + jump(null));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1,1,1,1], Expected: 3, Output: " + jump(new int[]{1,1,1,1}));
        System.out.println("Input: [3,2,1,0,4], Expected: 2, Output: " + jump(new int[]{3,2,1,0,4}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [5,9,3,2,1,0,2,3,3,1,0,0], Expected: 3, Output: " + jump(new int[]{5,9,3,2,1,0,2,3,3,1,0,0}));
        System.out.println("Input: [2,1], Expected: 1, Output: " + jump(new int[]{2,1}));
        System.out.println("Input: [1,2,1,1,1], Expected: 3, Output: " + jump(new int[]{1,2,1,1,1}));
    }
}
