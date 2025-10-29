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
     * Approach 3: Tabulation (Bottom-Up DP over index and prev-index state)
     *
     * Intuition:
     * - We convert the memoized state (index, prevIndex) into a bottom-up table.
     * - Use 2D DP where dp[i][p] stores the best LIS length starting from i
     *   when the previous chosen element's index is (p - 1). We shift by +1 so
     *   that p = 0 represents prev = -1 (no element chosen yet).
     * - Transition mirrors the recursive choices: take current element (if it
     *   maintains increasing order), or skip it.
     *
     * Explanation:
     * - Step 1: Define dp with dimensions (n+1) x (n+1). Row n is the base row
     *   of 0s representing no elements left to process.
     * - Step 2: Iterate i from n-1 down to 0 and prev index j from i-1 down to
     *   -1 (encoded as column j+1). For each state, compute:
     *     * take = 1 + dp[i+1][i+1] if j == -1 or nums[i] > nums[j]
     *     * notTake = dp[i+1][j+1]
     * - Step 3: dp[i][j+1] = max(take, notTake). Answer is dp[0][0]
     *   (start at i=0 with prev=-1 encoded as 0).
     *
     * Time Complexity: O(n^2) due to the nested loops over i and j.
     * Space Complexity: O(n^2) for the dp table.
     *
     * @param nums	Array of integers (1 <= nums.length <= 2500)
     * @return		Length of longest strictly increasing subsequence
     */
    public static int lengthOfLISTabulation(int[] nums) {
        // Get array length and allocate DP table where dp[i][p] represents
        // best LIS starting at index i given previous index encoded as p=j+1
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];

        // Fill from bottom (i = n-1 -> 0). Base case dp[n][*] is already 0.
        for (int i = n - 1; i >= 0; i--) {
            // Iterate prev index j from i-1 down to -1, mapped to column j+1
            for (int j = i - 1; j >= -1; j--) {
                // Option 1: take current nums[i] if it keeps sequence increasing
                int take = -1; // initialize to -1 so max with notTake works
                if (j == -1 || nums[i] > nums[j]) {
                    // If we take nums[i], next state is (i+1, prev=i) => column i+1
                    take = dp[i + 1][i + 1] + 1;
                }

                // Option 2: skip current element, keep prev as j
                int notTake = dp[i + 1][j + 1];

                // Choose the better of taking or skipping
                dp[i][j + 1] = Math.max(take, notTake);
            }
        }

        // Start from index 0 with prev = -1 (encoded as 0)
        return dp[0][0];
    }
    

    /**
     * Approach 4: Tabulation (Space Optimized over prev-index state)
     *
     * Intuition:
     * - This is the 2D tabulation reformulated to use only two 1D arrays.
     * - We still encode prev index as column (j + 1). For each i, we build a
     *   fresh row `next` from the row below `dp` (which represents i+1).
     * - Transitions mirror the 2D version: either take nums[i] if it keeps the
     *   sequence increasing, or skip it and carry forward the best.
     *
     * Explanation:
     * - Step 1: Maintain `dp` as the row for i+1 and compute `next` for i.
     * - Step 2: For each prev j in [i-1..-1], compute:
     *     * take = 1 + dp[i+1] when j == -1 or nums[i] > nums[j]
     *     * notTake = dp[j+1]
     * - Step 3: next[j+1] = max(take, notTake). After finishing j-loop, set
     *   dp = next. The answer is dp[0] (i=0, prev=-1 encoded as 0).
     *
     * Time Complexity: O(n^2) due to nested loops over i and j.
     * Space Complexity: O(n) for the two 1D arrays used per row.
     *
     * @param nums    Array of integers (1 <= nums.length <= 2500)
     * @return        Length of longest strictly increasing subsequence
     */
    public static int lengthOfLISTabulationSpaceOptimized(int[] nums) {
        // Length of the input sequence
        int n = nums.length;
        // `dp` holds the row for i+1 (encoded prev indices as columns)
        int[] dp = new int[n + 1];

        // Build rows from bottom to top: i = n-1 down to 0
        for (int i = n - 1; i >= 0; i--) {
            // `next` is the row for current i that we'll compute from `dp`
            int[] next = new int[n + 1];
            // Iterate prev index j from i-1 down to -1, mapped to column j+1
            for (int j = i - 1; j >= -1; j--) {
                // Option 1: take nums[i] if it keeps sequence strictly increasing
                int take = -1;
                if (j == -1 || nums[i] > nums[j]) {
                    // If we take nums[i], next state is (i+1, prev=i) => column i+1
                    take = dp[i + 1] + 1;
                }
                // Option 2: skip current element, keep prev as j
                int notTake = dp[j + 1];

                // Best of taking or skipping for state (i, j)
                next[j + 1] = Math.max(take, notTake);
            }
            // Move up one row: i becomes i+1 for the next iteration
            dp = next;
        }

        // Start from index 0 with prev = -1 (encoded as 0)
        return dp[0];
    }

    /**
     * Approach 5: Bottom-Up DP (Space Optimized)
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