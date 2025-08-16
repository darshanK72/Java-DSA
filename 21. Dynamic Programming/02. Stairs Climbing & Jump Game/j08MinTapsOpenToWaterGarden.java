/**
 * LeetCode 1326. Minimum Number of Taps to Open to Water a Garden
 * 
 * Problem Statement:
 *     There is a one-dimensional garden on the x-axis. The garden starts at the 
 *     point 0 and ends at the point n. (i.e., the length of the garden is n).
 *     There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 *     Given an integer n and an integer array ranges of length n + 1 where 
 *     ranges[i] (0-indexed) means the i-th tap can water the area 
 *     [i - ranges[i], i + ranges[i]] if it was open.
 *     Return the minimum number of taps that should be open to water the whole 
 *     garden, If the garden cannot be watered return -1.
 * 
 * Input:
 *     - n: int (1 <= n <= 10^4)
 *     - ranges: int[] (length n+1, 0 <= ranges[i] <= 100)
 * 
 * Output:
 *     - Minimum number of taps to open, or -1 if impossible
 * 
 * Example:
 *     Input: n = 5, ranges = [3,4,1,1,0,0]
 *     Output: 1
 * 
 *     Explanation:
 *     The tap at point 0 can cover the interval [-3,3]
 *     The tap at point 1 can cover the interval [-3,5]
 *     The tap at point 2 can cover the interval [1,3]
 *     The tap at point 3 can cover the interval [2,4]
 *     The tap at point 4 can cover the interval [4,4]
 *     The tap at point 5 can cover the interval [5,5]
 *     Opening Only the second tap will water the whole garden [0,5]
 */

import java.util.Arrays;

public class j08MinTapsOpenToWaterGarden {

    /**
     * Approach: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - Convert the tap coverage problem to a jump game problem
     * - Create a jumps array where jumps[i] represents the maximum reachable position from i
     * - Use DP with memoization to find minimum jumps to reach the end
     * - Each tap contributes to the coverage range, and we need to find optimal combination
     * 
     * Explanation:
     * - Step 1: Create jumps array by processing each tap's coverage range
     * - Step 2: For each tap, update the maximum reachable position in its range
     * - Step 3: Use recursive DP with memoization to find minimum jumps
     * - Step 4: Handle impossible cases by checking for Integer.MAX_VALUE
     * 
     * Time Complexity: O(n^2) - For each position, we may explore up to n next positions
     * Space Complexity: O(n) - DP array and recursion stack depth
     * 
     * @param n       Length of the garden
     * @param ranges  Array where ranges[i] is the range of i-th tap
     * @return        Minimum number of taps to open, or -1 if impossible
     */
    public int minTaps(int n, int[] ranges) {
        // Create jumps array to represent maximum reachable position from each point
        int[] jumps = new int[n + 1];
        
        // For each tap, update the maximum reachable position in its range
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);      // Left boundary of tap range
            int right = Math.min(n, i + ranges[i]);     // Right boundary of tap range
            
            // Update the maximum reachable position from left boundary
            // Store the range length (right - left) as the jump distance
            jumps[left] = Math.max(jumps[left], right - left);
        }

        // Initialize DP array with -1 to mark uncomputed states
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        
        // Compute minimum jumps starting from position 0
        int ans = (int) minJumps(jumps, dp, 0);

        // Check if it's impossible to reach the end
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - From current position, try all possible jump lengths
     * - Find the minimum jumps among all valid jumps
     * - Use memoization to cache computed results
     * 
     * Explanation:
     * - Step 1: Check if we've reached the destination (base case)
     * - Step 2: Check if result is already computed (memoization)
     * - Step 3: Try all possible jump lengths from 1 to jumps[index]
     * - Step 4: For each valid jump, recursively compute minimum jumps
     * - Step 5: Find minimum among all valid jumps and add 1
     * 
     * Time Complexity: O(outdegree(index)) per uncached state
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param nums    Array where nums[i] is the maximum jump length from position i
     * @param dp      Memoization array storing minimum jumps from each position
     * @param index   Current position in the garden
     * @return        Minimum jumps to reach the end from current position
     */
    public long minJumps(int[] nums, long[] dp, int index) {
        // Base case: if we've reached the destination, no more jumps needed
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
        j08MinTapsOpenToWaterGarden solution = new j08MinTapsOpenToWaterGarden();
        
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: n=5, ranges=[3,4,1,1,0,0], Expected: 1, Output: " + solution.minTaps(5, new int[]{3,4,1,1,0,0}));
        System.out.println("Input: n=3, ranges=[0,0,0,0], Expected: -1, Output: " + solution.minTaps(3, new int[]{0,0,0,0}));
        System.out.println("Input: n=7, ranges=[1,2,1,0,2,1,0,1], Expected: 3, Output: " + solution.minTaps(7, new int[]{1,2,1,0,2,1,0,1}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, ranges=[2,1], Expected: 1, Output: " + solution.minTaps(1, new int[]{2,1}));
        System.out.println("Input: n=0, ranges=[0], Expected: 0, Output: " + solution.minTaps(0, new int[]{0}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=8, ranges=[4,0,0,0,0,0,0,0,4], Expected: 2, Output: " + solution.minTaps(8, new int[]{4,0,0,0,0,0,0,0,4}));
        System.out.println("Input: n=9, ranges=[0,5,0,3,3,3,1,4,0,4], Expected: 2, Output: " + solution.minTaps(9, new int[]{0,5,0,3,3,3,1,4,0,4}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=4, ranges=[1,2,1,0,2], Expected: 2, Output: " + solution.minTaps(4, new int[]{1,2,1,0,2}));
        System.out.println("Input: n=6, ranges=[3,0,1,2,0,0,2], Expected: 1, Output: " + solution.minTaps(6, new int[]{3,0,1,2,0,0,2}));
        System.out.println("Input: n=10, ranges=[2,1,2,1,2,1,2,1,2,1,2], Expected: 6, Output: " + solution.minTaps(10, new int[]{2,1,2,1,2,1,2,1,2,1,2}));
    }
}
