/**
 * GeeksForGeeks - Perfect Sum Problem (Count Subsets with Given Sum)
 * 
 * Problem Statement:
 *     Given an array arr[] of non-negative integers and an integer sum, 
 *     the task is to count all subsets of the given array with a sum 
 *     equal to a given sum. Each element can be used at most once.
 * 
 * Input:
 *     - nums[] (int[]): Array of non-negative integers (nums[i] >= 0)
 *     - target (int): Target sum to achieve (target >= 0)
 *     - Array length n (n >= 0)
 * 
 * Output:
 *     - Number of subsets that sum up to the target value
 * 
 * Example:
 *     Input: nums[] = {2, 3, 5, 6, 8, 10}, target = 10
 *     Output: 3
 * 
 *     Explanation:
 *         Subsets that sum to 10:
 *         1. {2, 3, 5} → 2 + 3 + 5 = 10
 *         2. {2, 8} → 2 + 8 = 10
 *         3. {10} → 10 = 10
 *         Total count: 3 subsets
 */

import java.util.Arrays;

public class j06PerfectSumProblem {

    /**
     * Approach: Memoized Dynamic Programming (Top-Down)
     * 
     * Intuition:
     * - Use recursive approach with memoization to count all valid subsets
     * - For each element, make two choices: include or exclude in subset
     * - Track remaining target sum and current element index
     * - Count all paths that lead to target sum = 0
     * - Store intermediate results to avoid redundant calculations
     * 
     * Explanation:
     * - Step 1: Initialize DP table with -1 to mark unvisited states
     * - Step 2: For each element, try including or excluding it
     * - Step 3: If including, reduce target by element value
     * - Step 4: If excluding, keep target unchanged
     * - Step 5: Count all valid combinations that reach target = 0
     * 
     * Time Complexity: O(n * target) - each state calculated once
     * Space Complexity: O(n * target) for DP table + O(n) for recursion stack
     * 
     * @param nums    Array of non-negative integers (nums[i] >= 0)
     * @param target  Target sum to achieve (target >= 0)
     * @return        Number of subsets that sum up to target
     */
    public static int perfectSum(int[] nums, int target) {
        // Validate input parameters
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }
        
        if (target < 0) {
            return 0;
        }
        
        // Initialize DP table with -1 to mark unvisited states
        int[][] dp = new int[nums.length + 1][target + 1];
        for (int i = 0; i <= nums.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // Start recursive solution from first element with full target
        return countPerfectSum(dp, nums, target, 0);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - Recursively explore include/exclude decisions for each element
     * - When target becomes 0, we found a valid subset
     * - When index reaches end, check if target is 0
     * - Use memoization to optimize repeated subproblems
     * 
     * Explanation:
     * - Base case: if index == nums.length, return 1 if target == 0, else 0
     * - If state already calculated, return memoized result
     * - Option 1: Don't take current element (exclude from subset)
     * - Option 2: Take current element if target allows (include in subset)
     * - Return sum of both options (count all valid paths)
     * 
     * Time Complexity: O(1) for each state (calculated once)
     * Space Complexity: O(n) for recursion stack
     * 
     * @param dp      DP table for memoization
     * @param nums    Array of non-negative integers
     * @param target  Remaining target sum to achieve
     * @param index   Current element index being considered
     * @return        Number of valid subsets from current state
     */
    public static int countPerfectSum(int[][] dp, int[] nums, int target, int index) {
        // Base case: reached end of array
        if (index == nums.length) {
            // Return 1 if target is achieved, 0 otherwise
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        
        // Return memoized result if already calculated
        if (dp[index][target] != -1) {
            return dp[index][target];
        }
        
        // Option 1: Don't take current element (exclude from subset)
        int notTake = countPerfectSum(dp, nums, target, index + 1);
        
        // Option 2: Take current element if target allows (include in subset)
        int take = 0;
        if (target >= nums[index]) {
            take = countPerfectSum(dp, nums, target - nums[index], index + 1);
        }
        
        // Store and return sum of both options (count all valid paths)
        return dp[index][target] = take + notTake;
    }

    /**
     * Approach 2: Tabulation Dynamic Programming (Bottom-Up)
     * 
     * Intuition:
     * - Build solution iteratively from smaller subproblems to larger ones
     * - For each element and target, consider including or excluding current element
     * - Use previously calculated results to build current solution
     * - Fill DP table in bottom-up manner to avoid recursion
     * 
     * Explanation:
     * - Step 1: Initialize DP table with base case (target = 0 has 1 way)
     * - Step 2: For each element and target, consider two options:
     *   * Exclude current element: use result from previous element
     *   * Include current element: add result from (target - current element)
     * - Step 3: Sum both options to get total count for current state
     * 
     * Time Complexity: O(n * target) - each cell calculated once
     * Space Complexity: O(n * target) for DP table
     * 
     * @param nums    Array of non-negative integers (nums[i] >= 0)
     * @param target  Target sum to achieve (target >= 0)
     * @return        Number of subsets that sum up to target
     */
    public static int perfectSumTabulation(int[] nums, int target) {
        // Validate input parameters
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }
        
        if (target < 0) {
            return 0;
        }
        
        int n = nums.length;
        
        // Initialize DP table with base case (target = 0 has 1 way)
        int[][] dp = new int[n + 1][target + 1];
        
        // Base case: empty subset sums to 0 (1 way)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        
        // Fill DP table using bottom-up approach
        for (int index = 1; index <= n; index++) {
            for (int tar = 1; tar <= target; tar++) {
                // Option 1: Don't take current element (exclude from subset)
                dp[index][tar] = dp[index - 1][tar];
                
                // Option 2: Take current element if target allows (include in subset)
                if (tar >= nums[index - 1]) {
                    dp[index][tar] += dp[index - 1][tar - nums[index - 1]];
                }
            }
        }
        
        return dp[n][target];
    }

    /**
     * Approach 3: Optimized Tabulation Dynamic Programming (Bottom-Up)
     * 
     * Intuition:
     * - Build solution iteratively from smaller subproblems to larger ones
     * - For each element and target, consider including or excluding current element
     * - Use previously calculated results to build current solution
     * - Fill DP table in bottom-up manner to avoid recursion
     * - Handle edge cases like zero elements and empty subsets properly
     * 
     * Explanation:
     * - Step 1: Initialize DP table with base case (target = 0 has 1 way)
     * - Step 2: For each element and target, consider two options:
     *   * Exclude current element: use result from previous element
     *   * Include current element: add result from (target - current element)
     * - Step 3: Sum both options to get total count for current state
     * - Step 4: Handle special case where current element is 0 (can be included/excluded)
     * 
     * Time Complexity: O(n * target) - each cell calculated once
     * Space Complexity: O(n * target) for DP table
     * 
     * @param nums    Array of non-negative integers (nums[i] >= 0)
     * @param target  Target sum to achieve (target >= 0)
     * @return        Number of subsets that sum up to target
     */
    public static int perfectSumTabulationOptimized(int[] nums, int target) {
        // Validate input parameters
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }
        
        if (target < 0) {
            return 0;
        }
        
        int n = nums.length;
        
        // Initialize DP table with base case (target = 0 has 1 way)
        int[][] dp = new int[n + 1][target + 1];
        
        // Base case: empty subset sums to 0 (1 way)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        
        // Fill DP table using bottom-up approach
        for (int index = 1; index <= n; index++) {
            for (int tar = 0; tar <= target; tar++) {
                // Option 1: Don't take current element (exclude from subset)
                dp[index][tar] = dp[index - 1][tar];
                
                // Option 2: Take current element if target allows (include in subset)
                if (tar >= nums[index - 1]) {
                    dp[index][tar] += dp[index - 1][tar - nums[index - 1]];
                }
            }
        }
        
        return dp[n][target];
    }

    /**
     * Approach 4: Space Optimized Tabulation (1D DP)
     * 
     * Intuition:
     * - Use only 1D array instead of 2D to save space
     * - Fill array from right to left to avoid overwriting needed values
     * - Each cell represents count of subsets for current target
     * - Update values based on previous state and current element
     * 
     * Explanation:
     * - Step 1: Initialize 1D array with base case (target = 0 has 1 way)
     * - Step 2: For each element, update all targets from right to left
     * - Step 3: For each target, add count from (target - current element)
     * - Step 4: This avoids using 2D array while maintaining correctness
     * 
     * Time Complexity: O(n * target) - each cell calculated once
     * Space Complexity: O(target) for 1D DP array
     * 
     * @param nums    Array of non-negative integers (nums[i] >= 0)
     * @param target  Target sum to achieve (target >= 0)
     * @return        Number of subsets that sum up to target
     */
    public static int perfectSumTabulationSpaceOptimized(int[] nums, int target) {
        // Validate input parameters
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }
        
        if (target < 0) {
            return 0;
        }
        
        // Initialize 1D DP array with base case
        int[] dp = new int[target + 1];
        dp[0] = 1; // Empty subset sums to 0 (1 way)
        
        // Fill DP array using bottom-up approach
        for (int num : nums) {
            // Update from right to left to avoid overwriting needed values
            for (int tar = target; tar >= num; tar--) {
                dp[tar] += dp[tar - num];
            }
        }
        
        return dp[target];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Test 1: nums=[2,3,5,6,8,10], target=10");
        System.out.println("Expected: 3, Output: " + perfectSum(new int[]{2, 3, 5, 6, 8, 10}, 10));
        
        System.out.println("\nTest 2: nums=[1,2,3,4,5], target=7");
        System.out.println("Expected: 3, Output: " + perfectSum(new int[]{1, 2, 3, 4, 5}, 7));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Test 3 - Empty array, target=0:");
        System.out.println("Expected: 1, Output: " + perfectSum(new int[]{}, 0));
        
        System.out.println("\nTest 4 - Empty array, target=5:");
        System.out.println("Expected: 0, Output: " + perfectSum(new int[]{}, 5));
        
        System.out.println("\nTest 5 - Single element, target matches:");
        System.out.println("Expected: 1, Output: " + perfectSum(new int[]{5}, 5));
        
        System.out.println("\nTest 6 - Single element, target doesn't match:");
        System.out.println("Expected: 0, Output: " + perfectSum(new int[]{5}, 3));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Test 7 - Target = 0:");
        System.out.println("Expected: 1, Output: " + perfectSum(new int[]{1, 2, 3}, 0));
        
        System.out.println("\nTest 8 - All elements sum to target:");
        System.out.println("Expected: 1, Output: " + perfectSum(new int[]{1, 2, 3}, 6));
        
        System.out.println("\nTest 9 - No valid subsets:");
        System.out.println("Expected: 0, Output: " + perfectSum(new int[]{1, 2, 3}, 10));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Test 10 - All zeros:");
        System.out.println("Expected: 8, Output: " + perfectSum(new int[]{0, 0, 0}, 0));
        
        System.out.println("Test 11 - Large target:");
        System.out.println("Expected: 1, Output: " + perfectSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 55));
        
        System.out.println("Test 12 - Multiple valid subsets:");
        System.out.println("Expected: 4, Output: " + perfectSum(new int[]{1, 1, 2, 2}, 4));
        
        System.out.println("Test 13 - Negative target (should return 0):");
        System.out.println("Expected: 0, Output: " + perfectSum(new int[]{1, 2, 3}, -5));
        
        // Test Case 5: Specific failing test case
        System.out.println("\nTest 14 - Failing Test Case:");
        System.out.println("Input: nums=[28,4,3,27,0,24,26], target=24");
        System.out.println("Expected: 2, Output: " + perfectSum(new int[]{28, 4, 3, 27, 0, 24, 26}, 24));
        System.out.println("Tabulation Output: " + perfectSumTabulation(new int[]{28, 4, 3, 27, 0, 24, 26}, 24));
        System.out.println("Optimized Tabulation Output: " + perfectSumTabulationOptimized(new int[]{28, 4, 3, 27, 0, 24, 26}, 24));
        System.out.println("Space Optimized Tabulation Output: " + perfectSumTabulationSpaceOptimized(new int[]{28, 4, 3, 27, 0, 24, 26}, 24));

        // Test Case 6: Compare all approaches
        System.out.println("\nTest 15 - Compare All Approaches:");
        int[] testNums = {2, 3, 5, 6, 8, 10};
        int testTarget = 10;
        System.out.println("Input: nums=[2,3,5,6,8,10], target=10");
        System.out.println("Memoization: " + perfectSum(testNums, testTarget));
        System.out.println("Tabulation: " + perfectSumTabulation(testNums, testTarget));
        System.out.println("Optimized Tabulation: " + perfectSumTabulationOptimized(testNums, testTarget));
        System.out.println("Space Optimized Tabulation: " + perfectSumTabulationSpaceOptimized(testNums, testTarget));
    }
}
