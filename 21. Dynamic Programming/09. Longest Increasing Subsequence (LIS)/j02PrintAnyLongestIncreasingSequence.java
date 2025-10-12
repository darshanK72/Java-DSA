/**
 * GeeksForGeeks. Print Longest Increasing Subsequence
 * 
 * Problem Statement:
 *     Given an array of integers, find and return the actual longest 
 *     increasing subsequence (not just its length).
 * 
 * Input:
 *     - nums (1 <= nums.length <= 1000, -10^4 <= nums[i] <= 10^4)
 * 
 * Output:
 *     - ArrayList<Integer> containing the longest increasing subsequence
 * 
 * Example:
 *     Input: [10, 9, 2, 5, 3, 7, 101, 18]
 *     Output: [2, 5, 7, 18]
 * 
 *     Explanation:
 *     The longest increasing subsequence is [2, 5, 7, 18] with length 4.
 *     Other possible subsequences include [2, 3, 7, 18] and [2, 5, 7, 101].
 */

import java.util.*;

public class j02PrintAnyLongestIncreasingSequence {

    /**
     * Approach: Dynamic Programming with Sequence Tracking
     * 
     * Intuition:
     * - Use DP array where dp[i] stores the actual LIS ending at index i
     * - For each element, check all previous elements to find the longest 
     *   valid subsequence that can be extended
     * - Maintain actual sequences instead of just lengths to reconstruct 
     *   the final answer
     * - Track the index of the longest sequence found so far
     * 
     * Explanation:
     * - Step 1: Initialize DP array where each element stores its own sequence
     * - Step 2: For each element, iterate through all previous elements
     * - Step 3: If previous element is smaller, check if extending that 
     *           sequence gives a longer subsequence
     * - Step 4: Update current sequence if a better extension is found
     * - Step 5: Track the index of the longest sequence overall
     * - Step 6: Return the sequence at the tracked index
     * 
     * Time Complexity: O(n^2) where n is array length - nested loops check 
     *                  all previous elements for each position
     * Space Complexity: O(n^2) where n is array length - DP array stores 
     *                   sequences which can be up to O(n) in worst case
     * 
     * @param nums    Array of integers to find LIS from (1 <= length <= 1000)
     * @return        ArrayList containing the longest increasing subsequence
     */
    public static ArrayList<Integer> printAnyLongestIncreasingSequence(int[] nums) {
        // Initialize array length and DP array to store sequences
        int n = nums.length;
        ArrayList<Integer>[] dp = new ArrayList[n];

        // Track maximum LIS length and its ending index
        int maxLis = 1;
        int maxLisIndex = 0;

        // Process each element as potential end of increasing subsequence
        for (int i = 0; i < n; i++) {
            // Initialize DP entry with current element as single-element sequence
            dp[i] = new ArrayList<>();
            dp[i].add(nums[i]);

            // Check all previous elements for valid extensions
            for (int j = 0; j < i; j++) {
                // Verify if previous element can extend current sequence
                if (nums[j] < nums[i]) {
                    // Check if extending previous sequence gives longer result
                    if (dp[j].size() + 1 > dp[i].size()) {
                        // Copy previous sequence and add current element
                        dp[i] = new ArrayList<>(dp[j]);
                        dp[i].add(nums[i]);
                    }
                }
            }

            // Update global maximum LIS tracking if current is longer
            if (dp[i].size() > maxLis) {
                maxLis = dp[i].size();
                maxLisIndex = i;
            }
        }

        // Return the actual longest increasing subsequence
        return dp[maxLisIndex];
    }

    /**
     * Approach 2: Space-Efficient Dynamic Programming with Parent Tracking
     * 
     * Intuition:
     * - Use 2D DP array where dp[i][0] stores LIS length ending at index i
     * - Use dp[i][1] to store parent index for backtracking the actual sequence
     * - Instead of storing full sequences, only store lengths and parent links
     * - Reconstruct the sequence by following parent pointers from the end
     * - Significantly reduces space complexity compared to storing full sequences
     * 
     * Explanation:
     * - Step 1: Initialize DP array with length 1 and parent -1 for each element
     * - Step 2: For each element, check all previous elements for valid extensions
     * - Step 3: Update length and parent if extending previous sequence is better
     * - Step 4: Track the index of the longest sequence found overall
     * - Step 5: Backtrack from the end index using parent pointers
     * - Step 6: Reverse the backtracked sequence to get correct order
     * 
     * Time Complexity: O(n^2) where n is array length - same nested loop structure
     *                  as approach 1, but with O(n) reconstruction phase
     * Space Complexity: O(n) where n is array length - only stores lengths and 
     *                   parent indices instead of full sequences
     * 
     * @param nums    Array of integers to find LIS from (1 <= length <= 1000)
     * @return        ArrayList containing the longest increasing subsequence
     */
    public static ArrayList<Integer> printAnyLongestIncreasingSequenceEfficient(int nums[]) {
        // Initialize array length and DP array for length and parent tracking
        int n = nums.length;
        int[][] dp = new int[n][2];
        
        // Track maximum LIS length and its ending index
        int maxLis = 1;
        int maxLisIndex = 0;
        
        // Process each element as potential end of increasing subsequence
        for (int i = 0; i < n; i++) {
            // Initialize current element with length 1 and no parent
            dp[i][0] = 1;
            dp[i][1] = -1;
            
            // Check all previous elements for valid extensions
            for (int j = 0; j < i; j++) {
                // Verify if previous element can extend current sequence
                if (nums[j] < nums[i]) {
                    // Check if extending previous sequence gives longer result
                    if (dp[j][0] + 1 > dp[i][0]) {
                        // Update length and set parent index for backtracking
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = j;
                    }
                }
            }
            
            // Update global maximum LIS tracking if current is longer
            if (dp[i][0] > maxLis) {
                maxLis = dp[i][0];
                maxLisIndex = i;
            }
        }

        // Reconstruct sequence by following parent pointers backwards
        ArrayList<Integer> out = new ArrayList<>();
        int i = maxLisIndex;
        
        // Backtrack from end index using parent pointers
        while (i >= 0) {
            out.add(nums[i]);
            i = dp[i][1];
        }

        // Reverse the sequence to get correct forward order
        Collections.reverse(out);
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        ArrayList<Integer> result1 = printAnyLongestIncreasingSequence(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 });
        System.out.println("Input: [10, 9, 2, 5, 3, 7, 101, 18], Expected: [2, 5, 7, 18], Output: " + result1);

        ArrayList<Integer> result2 = printAnyLongestIncreasingSequence(new int[] { 0, 1, 0, 3, 2, 3 });
        System.out.println("Input: [0, 1, 0, 3, 2, 3], Expected: [0, 1, 3] or [0, 2, 3], Output: " + result2);

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        ArrayList<Integer> result3 = printAnyLongestIncreasingSequence(new int[] {});
        System.out.println("Input: [], Expected: [], Output: " + result3);

        ArrayList<Integer> result4 = printAnyLongestIncreasingSequence(new int[] { 5 });
        System.out.println("Input: [5], Expected: [5], Output: " + result4);

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        ArrayList<Integer> result5 = printAnyLongestIncreasingSequence(new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE });
        System.out.println("Input: [MAX_VALUE, MIN_VALUE], Expected: [MIN_VALUE], Output: " + result5);

        ArrayList<Integer> result6 = printAnyLongestIncreasingSequence(new int[] { 1, 1, 1, 1 });
        System.out.println("Input: [1, 1, 1, 1], Expected: [1], Output: " + result6);

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        ArrayList<Integer> result7 = printAnyLongestIncreasingSequence(new int[] { 1, 2, 3, 4, 5 });
        System.out.println("Input: [1, 2, 3, 4, 5] (sorted), Expected: [1, 2, 3, 4, 5], Output: " + result7);

        ArrayList<Integer> result8 = printAnyLongestIncreasingSequence(new int[] { 5, 4, 3, 2, 1 });
        System.out.println("Input: [5, 4, 3, 2, 1] (reverse sorted), Expected: [1], Output: " + result8);

        // Test Case 5: Efficient Method Comparison
        System.out.println("\nEfficient Method Test Cases:");
        ArrayList<Integer> efficient1 = printAnyLongestIncreasingSequenceEfficient(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 });
        System.out.println("Efficient - Input: [10, 9, 2, 5, 3, 7, 101, 18], Expected: [2, 5, 7, 18], Output: " + efficient1);
        
        ArrayList<Integer> efficient2 = printAnyLongestIncreasingSequenceEfficient(new int[] { 0, 1, 0, 3, 2, 3 });
        System.out.println("Efficient - Input: [0, 1, 0, 3, 2, 3], Expected: [0, 1, 3] or [0, 2, 3], Output: " + efficient2);
        
        ArrayList<Integer> efficient3 = printAnyLongestIncreasingSequenceEfficient(new int[] { 1, 3, 6, 7, 9, 4, 10, 5, 6 });
        System.out.println("Efficient - Input: [1, 3, 6, 7, 9, 4, 10, 5, 6], Expected: [1, 3, 4, 5, 6], Output: " + efficient3);
        
        ArrayList<Integer> efficient4 = printAnyLongestIncreasingSequenceEfficient(new int[] { 3, 1, 4, 1, 5, 9, 2, 6 });
        System.out.println("Efficient - Input: [3, 1, 4, 1, 5, 9, 2, 6], Expected: [1, 4, 5, 6] or [1, 2, 6], Output: " + efficient4);

        // Test Case 6: Method Comparison on Same Input
        System.out.println("\nMethod Comparison:");
        int[] testArray = new int[] { 6, 2, 5, 1, 7, 4, 8, 3 };
        ArrayList<Integer> standard = printAnyLongestIncreasingSequence(testArray);
        ArrayList<Integer> efficient = printAnyLongestIncreasingSequenceEfficient(testArray);
        System.out.println("Input: [6, 2, 5, 1, 7, 4, 8, 3]");
        System.out.println("Standard Method Output: " + standard);
        System.out.println("Efficient Method Output: " + efficient);
        System.out.println("Results Match: " + standard.equals(efficient));
    }
}
