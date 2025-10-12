/**
 * LeetCode 673. Number of Longest Increasing Subsequence
 * 
 * Problem Statement:
 *     Given an integer array nums, return the number of longest increasing 
 *     subsequences. Notice that the sequence has to be strictly increasing.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 2000, -10^6 <= nums[i] <= 10^6)
 * 
 * Output:
 *     - Return the number of longest increasing subsequences
 * 
 * Example:
 *     Input: [1,3,5,4,7]
 *     Output: 2
 * 
 *     Explanation:
 *     The two longest increasing subsequences are [1,3,4,7] and [1,3,5,7].
 */

import java.util.*;

public class j05NumberOfLongestIncreasingSubsequences {

    /**
     * Approach: Dynamic Programming with Count Tracking
     * 
     * Intuition:
     * - Use two arrays: dp[i] stores the length of LIS ending at index i, and 
     *   count[i] stores the number of LIS of that length ending at index i
     * - For each element, check all previous elements to build the LIS
     * - When extending an existing LIS, update both length and count
     * - If multiple paths lead to same length, accumulate the counts
     * 
     * Explanation:
     * - Step 1: Initialize dp and count arrays with base case (length 1)
     * - Step 2: For each element, check all previous smaller elements
     * - Step 3: If extending creates longer LIS, update dp and reset count
     * - Step 4: If extending maintains same length, accumulate count
     * - Step 5: Track maximum LIS length throughout the process
     * - Step 6: Sum all counts for sequences of maximum length
     * 
     * Time Complexity: O(n^2) where n is array length - nested loop checking 
     *                  all previous elements for each element
     * Space Complexity: O(n) for dp and count arrays
     * 
     * @param nums    Array of integers to find number of LIS for
     * @return        Number of longest increasing subsequences
     */
    public static int findNumberOfLIS(int[] nums) {
        // Handle edge case of empty or null array
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        // dp[i] stores the length of LIS ending at index i
        int[] dp = new int[n];
        // count[i] stores the number of LIS of length dp[i] ending at index i
        int[] count = new int[n];
        
        // Initialize base case: each element forms LIS of length 1
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        
        // Track the maximum LIS length found so far
        int maxLis = 1;
        
        // Build LIS for each ending position
        for (int i = 0; i < n; i++) {
            // Check all previous elements to extend existing LIS
            for (int j = 0; j < i; j++) {
                // Only consider elements that can extend increasing sequence
                if (nums[j] < nums[i]) {
                    // If extending creates a longer LIS
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; // Reset count to new sequence count
                    } else if (dp[i] == dp[j] + 1) {
                        // If extending maintains same length, accumulate counts
                        count[i] += count[j];
                    }
                }
            }
            // Update maximum LIS length seen so far
            maxLis = Math.max(maxLis, dp[i]);
        }
        
        // Sum all counts for sequences of maximum length
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLis) {
                ans += count[i];
            }
        }
        
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,3,5,4,7], Expected: 2, Output: " + 
            findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println("Input: [2,2,2,2,2], Expected: 5, Output: " + 
            findNumberOfLIS(new int[]{2,2,2,2,2}));
        System.out.println("Input: [1,2,4,3,5,4,7,2], Expected: 3, Output: " + 
            findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [], Expected: 0, Output: " + 
            findNumberOfLIS(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: " + 
            findNumberOfLIS(null));
        System.out.println("Input: [1], Expected: 1, Output: " + 
            findNumberOfLIS(new int[]{1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1,2,3,4,5], Expected: 1, Output: " + 
            findNumberOfLIS(new int[]{1,2,3,4,5}));
        System.out.println("Input: [5,4,3,2,1], Expected: 5, Output: " + 
            findNumberOfLIS(new int[]{5,4,3,2,1}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [1,3,2,4], Expected: 2, Output: " + 
            findNumberOfLIS(new int[]{1,3,2,4}));
        System.out.println("Input: [3,1,2], Expected: 1, Output: " + 
            findNumberOfLIS(new int[]{3,1,2}));
        System.out.println("Input: [1,1,1,2,2,2,3,3,3], Expected: 27, Output: " + 
            findNumberOfLIS(new int[]{1,1,1,2,2,2,3,3,3}));
    }
}
