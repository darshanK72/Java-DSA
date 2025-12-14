/**
 * LeetCode 413. Arithmetic Slices
 * 
 * Problem Statement:
 *     An integer array is called arithmetic if it consists of at least three 
 *     elements and if the difference between any two consecutive elements is 
 *     the same. For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are 
 *     arithmetic sequences.
 *     Given an integer array nums, return the number of arithmetic subarrays 
 *     of nums. A subarray is a contiguous subsequence of the array.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 5000, -1000 <= nums[i] <= 1000)
 * 
 * Output:
 *     - Integer representing the number of arithmetic subarrays
 * 
 * Example:
 *     Input: nums = [1,2,3,4]
 *     Output: 3
 * 
 *     Explanation:
 *     We have 3 arithmetic slices in nums: [1,2,3], [2,3,4], and [1,2,3,4].
 * 
 *     Input: nums = [1]
 *     Output: 0
 * 
 *     Explanation:
 *     There are no arithmetic slices with less than 3 elements.
 */

public class j13CountArithematicSubarray {

    /**
     * Approach: Dynamic Programming
     * 
     * Intuition:
     * - An arithmetic slice requires at least 3 consecutive elements with the 
     *   same difference
     * - If we have an arithmetic sequence of length k ending at index i, it 
     *   contributes (k-2) new arithmetic slices
     * - For example, [1,2,3,4] ending at index 3:
     *   - Length 4 sequence contributes: [2,3,4] and [1,2,3,4] = 2 slices
     *   - This is (4-2) = 2
     * - We use DP where dp[i] stores the number of NEW arithmetic slices 
     *   ending at index i
     * - If the difference continues from previous position, we extend the 
     *   sequence and add one more slice
     * - Sum all dp[i] to get total count of arithmetic slices
     * 
     * Explanation:
     * - Step 1: Handle edge case
     *   - If array has less than 3 elements, return 0 (no arithmetic slices 
     *     possible)
     * - Step 2: Initialize variables
     *   - Track the current difference between consecutive elements
     *   - Initialize dp array to store count of slices ending at each index
     *   - Start with difference between first two elements
     * - Step 3: Build DP array
     *   - For each index i from 2 to n-1, check if difference continues
     *   - If nums[i] - nums[i-1] == diff, the arithmetic sequence extends
     *   - Update dp[i] = dp[i-1] + 1 (adds one new slice of length (i+1))
     *   - If difference changes, update diff and reset (dp[i] remains 0)
     * - Step 4: Sum all slices
     *   - Sum all values in dp array to get total count
     *   - Each dp[i] represents new slices ending at position i
     * - Step 5: Return total count
     * 
     * Time Complexity: O(n) where n is the length of nums array
     *                  - Single pass through array: O(n)
     *                  - Sum calculation: O(n)
     * 
     * Space Complexity: O(n) where n is the length of nums array
     *                   - DP array of size n
     * 
     * @param nums    Integer array (1 <= nums.length <= 5000, 
     *                -1000 <= nums[i] <= 1000)
     * @return        Number of arithmetic subarrays
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        // Edge case: arithmetic slice requires at least 3 elements
        if (nums.length < 3) {
            return 0;
        }
        
        // Get the length of input array
        int n = nums.length;
        
        // Track the current difference between consecutive elements
        int diff = nums[1] - nums[0];
        
        // DP array where dp[i] stores number of new arithmetic slices ending 
        // at index i
        int[] dp = new int[n];
        
        // Build DP array: check each position for extending arithmetic sequence
        for (int i = 2; i < n; i++) {
            // If current difference matches previous difference, sequence extends
            if (nums[i] - nums[i - 1] == diff) {
                // Extend sequence: add one more slice (of length i+1)
                // dp[i-1] slices can be extended, plus one new slice ending here
                dp[i] = dp[i - 1] + 1;
            } else {
                // Difference changed, update to new difference
                // dp[i] remains 0 (no new slices ending at i)
                diff = nums[i] - nums[i - 1];
            }
        }
        
        // Sum all arithmetic slices found
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // Add count of slices ending at each position
            ans += dp[i];
        }
        
        // Return total number of arithmetic slices
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,2,3,4], Expected: 3, Output: " + 
            numberOfArithmeticSlices(new int[]{1,2,3,4}));
        System.out.println("Input: [1,2,3,4,5], Expected: 6, Output: " + 
            numberOfArithmeticSlices(new int[]{1,2,3,4,5}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], Expected: 0, Output: " + 
            numberOfArithmeticSlices(new int[]{1}));
        System.out.println("Input: [1,2], Expected: 0, Output: " + 
            numberOfArithmeticSlices(new int[]{1,2}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1,2,3], Expected: 1, Output: " + 
            numberOfArithmeticSlices(new int[]{1,2,3}));
        System.out.println("Input: [7,7,7,7], Expected: 3, Output: " + 
            numberOfArithmeticSlices(new int[]{7,7,7,7}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [1,3,5,7,9], Expected: 6, Output: " + 
            numberOfArithmeticSlices(new int[]{1,3,5,7,9}));
        System.out.println("Input: [3,-1,-5,-9], Expected: 3, Output: " + 
            numberOfArithmeticSlices(new int[]{3,-1,-5,-9}));
        System.out.println("Input: [1,2,3,8,9,10], Expected: 2, Output: " + 
            numberOfArithmeticSlices(new int[]{1,2,3,8,9,10}));
    }
}
