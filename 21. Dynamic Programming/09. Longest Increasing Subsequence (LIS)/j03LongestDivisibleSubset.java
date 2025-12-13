/**
 * LeetCode 368. Largest Divisible Subset
 * 
 * Problem Statement:
 *     Given a set of distinct positive integers nums, return the largest 
 *     subset answer such that every pair (answer[i], answer[j]) of elements 
 *     in this subset satisfies:
 *     - answer[i] % answer[j] == 0, or
 *     - answer[j] % answer[i] == 0
 *     If there are multiple solutions, return any of them.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 1000, 1 <= nums[i] <= 2 * 10^9, all 
 *       integers are unique)
 * 
 * Output:
 *     - List of integers representing the largest divisible subset
 * 
 * Example:
 *     Input: nums = [1,2,3]
 *     Output: [1,2]
 * 
 *     Explanation:
 *     [1,3] is also accepted. Both [1,2] and [1,3] satisfy the divisibility 
 *     condition.
 * 
 *     Input: nums = [1,2,4,8]
 *     Output: [1,2,4,8]
 * 
 *     Explanation:
 *     The entire array forms a valid divisible subset where each element 
 *     divides the next.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class j03LongestDivisibleSubset {

    /**
     * Approach: Dynamic Programming with Backtracking
     * 
     * Intuition:
     * - This problem is similar to Longest Increasing Subsequence (LIS), but 
     *   instead of checking if elements are in order, we check divisibility
     * - Sorting the array is crucial: if a divides b, then a <= b. After 
     *   sorting, we only need to check if smaller elements divide larger ones
     * - For each position, we find the longest divisible subset ending at that 
     *   position
     * - We use a prev[] array to track the previous element in the subset, 
     *   allowing us to reconstruct the entire subset later
     * - The key insight: if nums[i] % nums[j] == 0 and we have a subset 
     *   ending at j, we can extend it with nums[i]
     * 
     * Explanation:
     * - Step 1: Sort the input array
     *   - Ensures that if a divides b, then a comes before b in sorted order
     *   - This allows us to only check previous elements when building subsets
     * - Step 2: Initialize DP arrays
     *   - dp[i] stores the length of longest divisible subset ending at index i
     *   - prev[i] stores the index of previous element in the subset (-1 if 
     *     none)
     *   - Initialize all dp[i] = 1 (each element is a subset of length 1)
     *   - Initialize all prev[i] = -1 (no previous element initially)
     * - Step 3: Build DP array
     *   - For each element at index i, check all previous elements j < i
     *   - If nums[i] % nums[j] == 0, we can extend the subset ending at j
     *   - Update dp[i] = max(dp[i], dp[j] + 1) if extending gives longer subset
     *   - Update prev[i] = j to track the chain
     * - Step 4: Find the maximum subset
     *   - Track the index with maximum dp value
     *   - This index represents the end of the longest divisible subset
     * - Step 5: Reconstruct the subset
     *   - Start from the index with maximum dp value
     *   - Use prev[] array to backtrack and collect all elements
     *   - Build the result list by following the chain backwards
     * 
     * Time Complexity: O(n^2) where n is the length of nums array
     *                  - Sorting: O(n log n)
     *                  - Nested loops for DP: O(n^2)
     *                  - Reconstruction: O(n)
     * 
     * Space Complexity: O(n) where n is the length of nums array
     *                   - dp[] array: O(n)
     *                   - prev[] array: O(n)
     *                   - Result list: O(n) in worst case
     * 
     * @param nums    Integer array (1 <= nums.length <= 1000, 
     *                1 <= nums[i] <= 2 * 10^9, all unique)
     * @return        List of integers representing largest divisible subset
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        // Get the length of input array
        int n = nums.length;
        
        // Sort array to ensure divisibility condition: if a divides b, then 
        // a <= b, so a comes before b in sorted order
        Arrays.sort(nums);

        // dp[i] stores the length of longest divisible subset ending at index i
        int[] dp = new int[n];
        // prev[i] stores the index of previous element in the subset chain
        // (-1 indicates no previous element)
        int[] prev = new int[n];
        // Initialize all previous indices to -1 (no predecessor initially)
        Arrays.fill(prev, -1);
        
        // Track the maximum subset length and its ending index
        int maxLength = 0;
        int index = 0;
        
        // Build DP array: for each element, find longest subset ending at it
        for (int i = 0; i < n; i++) {
            // Check all previous elements to find valid divisibility pairs
            for (int j = i - 1; j >= 0; j--) {
                // If current element is divisible by previous element
                if (nums[i] % nums[j] == 0) {
                    // If extending subset from j gives longer subset than 
                    // current best
                    if (dp[j] + 1 > dp[i]) {
                        // Update subset length ending at i
                        dp[i] = dp[j] + 1;
                        // Record j as previous element in the chain
                        prev[i] = j;
                    }
                }
            }
            
            // Update maximum subset length and track its ending index
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                index = i;
            }
        }

        // Reconstruct the largest divisible subset by backtracking
        ArrayList<Integer> out = new ArrayList<>();
        // Start from the index with maximum subset length
        while (index != -1) {
            // Add current element to result
            out.add(nums[index]);
            // Move to previous element in the chain
            index = prev[index];
        }
        
        // Return the reconstructed subset (already in correct order since we 
        // backtracked from end to beginning)
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1,2,3], Expected: [1,2] or [1,3], Output: " + 
            largestDivisibleSubset(new int[]{1,2,3}));
        System.out.println("Input: [1,2,4,8], Expected: [1,2,4,8], Output: " + 
            largestDivisibleSubset(new int[]{1,2,4,8}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1], Expected: [1], Output: " + 
            largestDivisibleSubset(new int[]{1}));
        System.out.println("Input: [2,3,5,7], Expected: [2] or [3] or [5] or [7], Output: " + 
            largestDivisibleSubset(new int[]{2,3,5,7}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1,2,3,4,5,6], Expected: [1,2,4] or [1,2,6] or [1,3,6], Output: " + 
            largestDivisibleSubset(new int[]{1,2,3,4,5,6}));
        System.out.println("Input: [4,8,10,240], Expected: [4,8,240], Output: " + 
            largestDivisibleSubset(new int[]{4,8,10,240}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [3,4,16,8], Expected: [4,8,16], Output: " + 
            largestDivisibleSubset(new int[]{3,4,16,8}));
        System.out.println("Input: [1,2,3,4,6,8,12,24], Expected: [1,2,4,8,24] or [1,2,6,12,24], Output: " + 
            largestDivisibleSubset(new int[]{1,2,3,4,6,8,12,24}));
    }
}
