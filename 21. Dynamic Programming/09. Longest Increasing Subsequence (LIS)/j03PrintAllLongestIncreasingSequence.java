/**
 * GeeksForGeeks. Print All Longest Increasing Subsequences
 * 
 * Problem Statement:
 *     Given an array of integers, find and print all possible longest 
 *     increasing subsequences of maximum length.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 1000, -10^4 <= nums[i] <= 10^4)
 * 
 * Output:
 *     - Print all possible longest increasing subsequences to console
 * 
 * Example:
 *     Input: [10, 9, 2, 5, 3, 7, 101, 18]
 *     Output: 
 *         2 5 7 101
 *         2 5 7 18
 *         2 3 7 101
 *         2 3 7 18
 * 
 *     Explanation:
 *     The longest increasing subsequence length is 4. There are multiple 
 *     subsequences of this maximum length, all of which are printed.
 */

import java.util.*;

public class j03PrintAllLongestIncreasingSequence {

    /**
     * Approach: Dynamic Programming with DFS Backtracking
     * 
     * Intuition:
     * - First, use DP to find the maximum LIS length ending at each position
     * - Identify all positions that end with the maximum LIS length
     * - Use DFS backtracking from these end positions to reconstruct all 
     *   possible sequences of maximum length
     * - The DFS explores all valid previous elements that can form the 
     *   longest sequence ending at current position
     * 
     * Explanation:
     * - Step 1: Build DP array to store LIS length ending at each index
     * - Step 2: Find the maximum LIS length across all positions
     * - Step 3: Identify all end positions with maximum LIS length
     * - Step 4: For each end position, use DFS to backtrack all possible 
     *           sequences leading to that position
     * - Step 5: DFS recursively builds sequences by finding valid previous 
     *           elements that extend to current position
     * 
     * Time Complexity: O(n^2 + k * m) where n is array length, k is number 
     *                  of longest sequences, m is average sequence length
     * Space Complexity: O(n + k * m) where n is for DP array, k*m is for 
     *                   storing and printing all sequences
     * 
     * @param nums    Array of integers to find all LIS from (1 <= length <= 1000)
     */
    public static void printAllLongestIncreasingSequence(int[] nums) {
        // Initialize array length and DP array for LIS lengths
        int n = nums.length;
        int[] dp = new int[n];
        
        // Initialize all positions with minimum LIS length of 1
        Arrays.fill(dp, 1);
        
        // Track the maximum LIS length found
        int maxLis = 1;
        
        // Build DP array to find LIS length ending at each position
        for (int i = 1; i < n; i++) {
            // Check all previous elements for valid extensions
            for (int j = 0; j < i; j++) {
                // Verify if previous element can extend current sequence
                if (nums[j] < nums[i]) {
                    // Update LIS length if extending gives better result
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update global maximum LIS length
            maxLis = Math.max(maxLis, dp[i]);
        }

        // Find all positions that end with maximum LIS length and start DFS
        for (int i = 0; i < n; i++) {
            // Check if current position ends with maximum LIS length
            if (dp[i] == maxLis) {
                // Start DFS backtracking from this end position
                dfs(nums, dp, i, "" + nums[i]);
            }
        }
    }

    /**
     * Helper Method: DFS Backtracking for Sequence Reconstruction
     * 
     * Intuition:
     * - Recursively backtrack from end position to find all valid sequences
     * - At each position, find all previous elements that can extend to 
     *   current position while maintaining the sequence length
     * - Base case: when we reach a position with LIS length 1 (sequence start)
     * - Recursive case: explore all valid previous positions and continue DFS
     * 
     * Explanation:
     * - Step 1: Check if current position is start of sequence (dp[i] == 1)
     * - Step 2: If base case, print the complete sequence and return
     * - Step 3: If not base case, find all previous valid positions
     * - Step 4: A position is valid if: nums[prev] < nums[curr] AND 
     *           dp[prev] == dp[curr] - 1 (maintains sequence length)
     * - Step 5: Recursively call DFS for each valid previous position
     * 
     * Time Complexity: O(k * m) where k is number of sequences, m is sequence length
     * Space Complexity: O(m) for recursion stack depth
     * 
     * @param nums        Original array of integers
     * @param dp          DP array storing LIS lengths at each position
     * @param currIndex   Current position being processed
     * @param out         Current sequence being built (space-separated string)
     */
    public static void dfs(int[] nums, int[] dp, int currIndex, String out) {
        // Base case: reached start of sequence (LIS length = 1)
        if (dp[currIndex] == 1) {
            System.out.println(out);
            return;
        }

        // Find all previous elements that can extend to current element
        for (int prev = currIndex - 1; prev >= 0; prev--) {
            // Check if previous element is valid for extending current sequence
            if (nums[prev] < nums[currIndex] && dp[prev] == dp[currIndex] - 1) {
                // Recursively explore this valid previous position
                dfs(nums, dp, prev, nums[prev] + " " + out);
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Case:");
        System.out.println("Input: [10, 9, 2, 5, 3, 7, 101, 18]");
        System.out.println("All Longest Increasing Subsequences:");
        printAllLongestIncreasingSequence(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 });

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test Case 2: Single sequence case
        System.out.println("Single Sequence Test Case:");
        System.out.println("Input: [1, 2, 3, 4, 5]");
        System.out.println("All Longest Increasing Subsequences:");
        printAllLongestIncreasingSequence(new int[] { 1, 2, 3, 4, 5 });

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test Case 3: Multiple sequences case
        System.out.println("Multiple Sequences Test Case:");
        System.out.println("Input: [0, 1, 0, 3, 2, 3]");
        System.out.println("All Longest Increasing Subsequences:");
        printAllLongestIncreasingSequence(new int[] { 0, 1, 0, 3, 2, 3 });

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test Case 4: Edge cases
        System.out.println("Edge Case - Single Element:");
        System.out.println("Input: [5]");
        System.out.println("All Longest Increasing Subsequences:");
        printAllLongestIncreasingSequence(new int[] { 5 });

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test Case 5: Complex case with many sequences
        System.out.println("Complex Case:");
        System.out.println("Input: [1, 3, 6, 7, 9, 4, 10, 5, 6]");
        System.out.println("All Longest Increasing Subsequences:");
        printAllLongestIncreasingSequence(new int[] { 1, 3, 6, 7, 9, 4, 10, 5, 6 });

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test Case 6: Reverse sorted case
        System.out.println("Reverse Sorted Case:");
        System.out.println("Input: [5, 4, 3, 2, 1]");
        System.out.println("All Longest Increasing Subsequences:");
        printAllLongestIncreasingSequence(new int[] { 5, 4, 3, 2, 1 });
    }
}