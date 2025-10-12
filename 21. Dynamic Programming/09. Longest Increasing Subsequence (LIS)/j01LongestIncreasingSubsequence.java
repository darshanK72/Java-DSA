/**
 * LeetCode 300. Longest Increasing Subsequence
 * 
 * Problem Statement:
 *     Given an integer array nums, return the length of the longest strictly 
 *     increasing subsequence. A subsequence is a sequence that can be derived 
 *     from an array by deleting some or no elements without changing the order 
 *     of the remaining elements.
 * 
 * Input:
 *     - nums (1 <= nums.length <= 2500, -10^4 <= nums[i] <= 10^4)
 * 
 * Output:
 *     - Integer representing the length of longest increasing subsequence
 * 
 * Example:
 *     Input: nums = [10,9,2,5,3,7,101,18]
 *     Output: 4
 * 
 *     Explanation:
 *     The longest increasing subsequence is [2,3,7,18], therefore the length 
 *     is 4.
 * 
 *     Input: nums = [0,1,0,3,2,3]
 *     Output: 4
 * 
 *     Input: nums = [7,7,7,7,7,7,7]
 *     Output: 1
 */

import java.util.Arrays;

public class j01LongestIncreasingSubsequence {

    /**
     * Approach 1: Brute Force Recursion
     * 
     * Intuition:
     * - For each element, we have two choices: either take it in the subsequence 
     *   (if it's greater than previous element) or don't take it
     * - We explore all possible combinations using recursion to find the maximum 
     *   length subsequence
     * - Use index to track current position and prev to track previous element 
     *   index in subsequence
     * 
     * Explanation:
     * - Step 1: Base case - when we reach end of array, return 0
     * - Step 2: For each element, check if we can take it (greater than prev)
     * - Step 3: Recursively explore both take and not-take options
     * - Step 4: Return maximum of both choices
     * 
     * Time Complexity: O(2^n) where n is array length - exponential due to 
     *                  exploring all possible subsequences
     * Space Complexity: O(n) where n is array length - maximum recursion depth
     * 
     * @param nums    Array of integers (1 <= nums.length <= 2500)
     * @return        Length of longest increasing subsequence
     */
    public static int lengthOfLISBruitForce(int[] nums) {
        // Initialize recursive call starting from index 0 with no previous element
        return longestLISBruitForce(nums, 0, -1);
    }

    /**
     * Helper Method: Recursive exploration of all subsequence possibilities
     * 
     * Intuition:
     * - Recursively explore both taking and not taking current element
     * - Maintain prev index to ensure increasing property of subsequence
     * - Return maximum length found through all possible paths
     * 
     * Explanation:
     * - Base case: reached end of array, return 0
     * - Take option: include current element if it maintains increasing order
     * - Not-take option: skip current element and continue with previous state
     * - Return maximum of both options
     * 
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     * 
     * @param nums    Array of integers
     * @param index   Current position in array
     * @param prev    Index of previous element in subsequence (-1 if none)
     * @return        Maximum length of increasing subsequence from current state
     */
    private static int longestLISBruitForce(int[] nums, int index, int prev) {
        // Base case: reached end of array, no more elements to process
        if (index == nums.length) {
            return 0;
        }
        
        // Initialize take option to 0 (default: cannot take)
        int take = 0;
        
        // Check if current element can be taken (first element or greater than prev)
        if (prev == -1 || nums[index] > nums[prev]) {
            // Take current element and recursively find best subsequence from next index
            take = longestLISBruitForce(nums, index + 1, index) + 1;
        }
        
        // Don't take current element and continue with previous state
        int notTake = longestLISBruitForce(nums, index + 1, prev);
        
        // Return maximum length between taking and not taking current element
        return Math.max(notTake, take);
    }

    /**
     * Approach 2: Memoization (Top-Down DP)
     * 
     * Intuition:
     * - Same recursive approach as brute force but with memoization to avoid 
     *   recalculating same subproblems
     * - Use 2D DP array to store results of (index, prev) combinations
     * - Convert prev index to 0-based indexing by adding 1 (since prev can be -1)
     * 
     * Explanation:
     * - Step 1: Initialize DP array with -1 to indicate uncomputed states
     * - Step 2: Before computing, check if result already exists in DP
     * - Step 3: If computed, return cached result; otherwise compute and store
     * - Step 4: Same recursive logic as brute force but with memoization
     * 
     * Time Complexity: O(n^2) where n is array length - each state computed once
     * Space Complexity: O(n^2) where n is array length - DP array storage
     * 
     * @param nums    Array of integers (1 <= nums.length <= 2500)
     * @return        Length of longest increasing subsequence
     */
    public static int lengthOfLISMemoization(int[] nums) {
        // Get array length for DP initialization
        int n = nums.length;
        
        // Initialize 2D DP array: dp[index][prev+1] stores result for (index, prev) state
        int[][] dp = new int[n][n + 1];
        
        // Fill DP array with -1 to indicate uncomputed states
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // Start recursive computation with memoization from index 0, no previous element
        return longestLISMemoHelper(nums, dp, 0, -1);
    }

    /**
     * Helper Method: Memoized recursive computation
     * 
     * Intuition:
     * - Same logic as brute force recursion but with result caching
     * - Check DP array before computation and store results after computation
     * - Use prev+1 as second dimension to handle prev=-1 case
     * 
     * Explanation:
     * - Base case: reached end of array, return 0
     * - Memoization check: return cached result if already computed
     * - Take option: include current element if it maintains increasing order
     * - Not-take option: skip current element and continue with previous state
     * - Store and return maximum of both options
     * 
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     * 
     * @param nums    Array of integers
     * @param dp      Memoization array storing computed results
     * @param index   Current position in array
     * @param prev    Index of previous element in subsequence (-1 if none)
     * @return        Maximum length of increasing subsequence from current state
     */
    private static int longestLISMemoHelper(int[] nums, int[][] dp, int index, int prev) {
        // Base case: reached end of array, no more elements to process
        if (index == nums.length) {
            return 0;
        }
        
        // Check if result already computed and return cached value
        if (dp[index][prev + 1] != -1)
            return dp[index][prev + 1];
        
        // Initialize take option to 0 (default: cannot take)
        int take = 0;
        
        // Check if current element can be taken (first element or greater than prev)
        if (prev == -1 || nums[index] > nums[prev]) {
            // Take current element and recursively find best subsequence from next index
            take = longestLISMemoHelper(nums, dp, index + 1, index) + 1;
        }

        // Don't take current element and continue with previous state
        int notTake = longestLISMemoHelper(nums, dp, index + 1, prev);

        // Store result in DP array and return maximum length between both options
        return dp[index][prev + 1] = Math.max(notTake, take);
    }

    /**
     * Approach 3: Bottom-Up DP (Space Optimized)
     * 
     * Intuition:
     * - dp[i] represents length of longest increasing subsequence ending at index i
     * - For each element, check all previous elements to find maximum LIS that can 
     *   be extended by current element
     * - Track maximum LIS length seen so far across all positions
     * 
     * Explanation:
     * - Step 1: Initialize DP array with 1 (each element is LIS of length 1)
     * - Step 2: For each element, check all previous elements
     * - Step 3: If previous element is smaller, extend its LIS by 1
     * - Step 4: Update maximum LIS length seen so far
     * - Step 5: Return maximum LIS length
     * 
     * Time Complexity: O(n^2) where n is array length - nested loops
     * Space Complexity: O(n) where n is array length - single DP array
     * 
     * @param nums    Array of integers (1 <= nums.length <= 2500)
     * @return        Length of longest increasing subsequence
     */
    public static int lengthOfLISSpaceOptimization(int[] nums) {
        // Get array length for DP initialization
        int n = nums.length;
        
        // Initialize DP array where dp[i] = LIS length ending at index i
        int[] dp = new int[n];
        
        // Fill DP array with 1 since each element forms LIS of length 1
        Arrays.fill(dp, 1);
        
        // Initialize maximum LIS length to 1 (minimum possible)
        int maxLis = 1;
        
        // Iterate through each element starting from second element
        for (int i = 1; i < n; i++) {
            // Check all previous elements to find maximum extendable LIS
            for (int j = 0; j < i; j++) {
                // If previous element is smaller, we can extend its LIS
                if (nums[j] < nums[i]) {
                    // Update LIS ending at i to maximum of current and extended LIS
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update global maximum LIS length
            maxLis = Math.max(maxLis, dp[i]);
        }
        
        // Return maximum LIS length found
        return maxLis;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [10,9,2,5,3,7,101,18], Expected: 4, Output: " + lengthOfLISSpaceOptimization(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("Input: [0,1,0,3,2,3], Expected: 4, Output: " + lengthOfLISSpaceOptimization(new int[]{0,1,0,3,2,3}));
        System.out.println("Input: [7,7,7,7,7], Expected: 1, Output: " + lengthOfLISSpaceOptimization(new int[]{7,7,7,7,7}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [], Expected: 0, Output: " + lengthOfLISSpaceOptimization(new int[]{}));
        System.out.println("Input: [1], Expected: 1, Output: " + lengthOfLISSpaceOptimization(new int[]{1}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [MAX_VALUE], Output: " + lengthOfLISSpaceOptimization(new int[]{Integer.MAX_VALUE}));
        System.out.println("Input: [MIN_VALUE], Output: " + lengthOfLISSpaceOptimization(new int[]{Integer.MIN_VALUE}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [sorted array], Expected: 5, Output: " + lengthOfLISSpaceOptimization(new int[]{1,2,3,4,5}));
        System.out.println("Input: [reverse sorted], Expected: 1, Output: " + lengthOfLISSpaceOptimization(new int[]{5,4,3,2,1}));
        
        // Test Case 5: Compare all approaches
        System.out.println("\nApproach Comparison:");
        int[] testArray = {10,9,2,5,3,7,101,18};
        System.out.println("Brute Force: " + lengthOfLISBruitForce(testArray));
        System.out.println("Memoization: " + lengthOfLISMemoization(testArray));
        System.out.println("Space Optimized: " + lengthOfLISSpaceOptimization(testArray));
    }
}