/**
 * LeetCode 377. Combination Sum IV
 * 
 * Problem Statement:
 *     Given an array of distinct integers nums and a target integer target, 
 *     return the number of possible combinations that add up to target. The 
 *     test cases are generated so that the answer can fit in a 32-bit integer.
 *     Note: Different sequences are counted as different combinations.
 * 
 * Input:
 *     - nums (int[]): Array of distinct integers, 1 <= nums.length <= 200
 *     - target (int): Target sum to achieve, 1 <= target <= 1000
 * 
 * Output:
 *     - int: Number of different sequences that sum to target
 * 
 * Example:
 *     Input: nums = [1,2,3], target = 4
 *     Output: 7
 * 
 *     Explanation:
 *     The possible combination ways are:
 *     - (1, 1, 1, 1)
 *     - (1, 1, 2)
 *     - (1, 2, 1)
 *     - (1, 3)
 *     - (2, 1, 1)
 *     - (2, 2)
 *     - (3, 1)
 *     Note that different sequences are counted as different combinations.
 * 
 *     Input: nums = [9], target = 3
 *     Output: 0
 * 
 *     Explanation:
 *     There are no combinations that sum to 3 using only the number 9.
 */

import java.util.*;

public class j02CombinationSumIV {

    /**
     * Approach: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - Use 1D DP where dp[target] represents number of ways to reach target
     * - For each target, try all possible numbers from nums array
     * - Recursively solve for remaining target (target - num)
     * - Sum up all possible ways to reach the target
     * - This counts permutations (order matters) unlike Coin Change II
     * 
     * Explanation:
     * - Step 1: Initialize dp array with -1 to mark unvisited states
     * - Step 2: Use recursive function with target as parameter
     * - Step 3: For each target, try all numbers from nums array
     * - Step 4: Recursively solve for remaining target
     * - Step 5: Sum up all possible ways and store in dp array
     * - Step 6: Return total number of permutations
     * 
     * Time Complexity: O(target * len(nums)) - Each target calculated once
     * Space Complexity: O(target) - For the dp array and recursion stack
     * 
     * @param nums    Array of distinct integers (1 <= len <= 200)
     * @param target  Target sum to achieve (1 <= target <= 1000)
     * @return        Number of different sequences that sum to target
     */
    public static int combinationSum4(int[] nums, int target) {
        // Initialize dp array with -1 to mark unvisited states
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        
        // Call recursive function to find number of permutations
        return numberOfPermutations(nums, dp, target);
    }

    /**
     * Helper Method: Recursive function with memoization
     * 
     * Intuition:
     * - Recursively try all numbers from nums array for current target
     * - Use memoization to store already calculated results
     * - For each number, recursively solve for remaining target
     * - Sum up all possible ways to reach the target
     * - This approach counts permutations (order matters)
     * 
     * Explanation:
     * - Step 1: Check base cases (target = 0, target < 0)
     * - Step 2: Check if result already calculated (memoization)
     * - Step 3: Try each number from nums array
     * - Step 4: Recursively solve for remaining target
     * - Step 5: Sum up all possible ways and store result
     * 
     * Time Complexity: O(target * len(nums))
     * Space Complexity: O(target) - For dp array and recursion stack
     * 
     * @param nums    Array of distinct integers
     * @param dp      Memoization array to store results
     * @param target  Current target sum to achieve
     * @return        Number of permutations for current target
     */
    public static int numberOfPermutations(int[] nums, int[] dp, int target) {
        // Base case: target = 0 means we found a valid permutation
        if (target == 0) {
            return 1;
        }
        
        // Base case: target < 0 means invalid permutation
        if (target < 0) {
            return 0;
        }
        
        // Return cached result if already calculated
        if (dp[target] != -1) {
            return dp[target];
        }
        
        // Initialize total ways to 0
        int totalWays = 0;
        
        // Try each number from nums array
        for (int num : nums) {
            // Recursively calculate permutations for remaining target
            totalWays += numberOfPermutations(nums, dp, target - num);
        }
        
        // Store result in dp array and return
        return dp[target] = totalWays;
    }

    /**
     * Approach 2: Dynamic Programming with Tabulation (Bottom-Up)
     * 
     * Intuition:
     * - Build solution iteratively from smaller targets to target sum
     * - For each target i, try all numbers from nums array
     * - Use previously calculated results for smaller targets (dp[i-num])
     * - This approach avoids recursion and builds solution systematically
     * - Each dp[i] represents total permutations to reach target i
     * 
     * Explanation:
     * - Step 1: Initialize dp array with dp[0] = 1 as base case
     * - Step 2: Iterate through each target from 1 to target sum
     * - Step 3: For each target, try all numbers from nums array
     * - Step 4: If number can be used (i-num >= 0), add dp[i-num] to dp[i]
     * - Step 5: Store total permutations for current target in dp array
     * - Step 6: Return final result dp[target]
     * 
     * Time Complexity: O(target * len(nums)) - Each target processed once
     * Space Complexity: O(target) - For the dp array only
     * 
     * @param nums    Array of distinct integers (1 <= len <= 200)
     * @param target  Target sum to achieve (1 <= target <= 1000)
     * @return        Number of different sequences that sum to target
     */
    public static int combinationSum4Tabulation(int[] nums, int target) {
        // Initialize dp array with dp[0] = 1 as base case
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        // Build solution iteratively for each target from 1 to target sum
        for (int i = 1; i <= target; i++) {
            // Try each number from nums array for current target
            for (int num : nums) {
                // Check if current number can be used (remaining target >= 0)
                if (i - num >= 0) {
                    // Add permutations from smaller target to current target
                    dp[i] += dp[i - num];
                }
            }
        }
        
        // Return total permutations for target sum
        return dp[target];
    }

    public static void main(String[] args) {
        
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: nums=[1,2,3], target=4");
        System.out.println("Approach 1 (Memoization): Expected: 7, Output: " + 
                          combinationSum4(new int[]{1,2,3}, 4));
        System.out.println("Approach 2 (Tabulation): Expected: 7, Output: " + 
                          combinationSum4Tabulation(new int[]{1,2,3}, 4));
        
        System.out.println("\nInput: nums=[9], target=3");
        System.out.println("Approach 1 (Memoization): Expected: 0, Output: " + 
                          combinationSum4(new int[]{9}, 3));
        System.out.println("Approach 2 (Tabulation): Expected: 0, Output: " + 
                          combinationSum4Tabulation(new int[]{9}, 3));
        
        System.out.println("\nInput: nums=[1,2], target=3");
        System.out.println("Approach 1 (Memoization): Expected: 3, Output: " + 
                          combinationSum4(new int[]{1,2}, 3));
        System.out.println("Approach 2 (Tabulation): Expected: 3, Output: " + 
                          combinationSum4Tabulation(new int[]{1,2}, 3));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: nums=[1], target=1");
        System.out.println("Approach 1 (Memoization): Expected: 1, Output: " + 
                          combinationSum4(new int[]{1}, 1));
        System.out.println("Approach 2 (Tabulation): Expected: 1, Output: " + 
                          combinationSum4Tabulation(new int[]{1}, 1));
        
        System.out.println("\nInput: nums=[2], target=1");
        System.out.println("Approach 1 (Memoization): Expected: 0, Output: " + 
                          combinationSum4(new int[]{2}, 1));
        System.out.println("Approach 2 (Tabulation): Expected: 0, Output: " + 
                          combinationSum4Tabulation(new int[]{2}, 1));
        
        System.out.println("\nInput: nums=[1,2,3], target=0");
        System.out.println("Approach 1 (Memoization): Expected: 1, Output: " + 
                          combinationSum4(new int[]{1,2,3}, 0));
        System.out.println("Approach 2 (Tabulation): Expected: 1, Output: " + 
                          combinationSum4Tabulation(new int[]{1,2,3}, 0));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: nums=[1], target=1000");
        System.out.println("Approach 1 (Memoization): Expected: 1, Output: " + 
                          combinationSum4(new int[]{1}, 1000));
        System.out.println("Approach 2 (Tabulation): Expected: 1, Output: " + 
                          combinationSum4Tabulation(new int[]{1}, 1000));
        
        System.out.println("\nInput: nums=[1,2,3,4,5], target=10");
        System.out.println("Approach 1 (Memoization): Expected: 464, Output: " + 
                          combinationSum4(new int[]{1,2,3,4,5}, 10));
        System.out.println("Approach 2 (Tabulation): Expected: 464, Output: " + 
                          combinationSum4Tabulation(new int[]{1,2,3,4,5}, 10));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: nums=[1,2,3], target=5");
        System.out.println("Approach 1 (Memoization): Expected: 13, Output: " + 
                          combinationSum4(new int[]{1,2,3}, 5));
        System.out.println("Approach 2 (Tabulation): Expected: 13, Output: " + 
                          combinationSum4Tabulation(new int[]{1,2,3}, 5));
        
        System.out.println("\nInput: nums=[2,3,6,7], target=7");
        System.out.println("Approach 1 (Memoization): Expected: 2, Output: " + 
                          combinationSum4(new int[]{2,3,6,7}, 7));
        System.out.println("Approach 2 (Tabulation): Expected: 2, Output: " + 
                          combinationSum4Tabulation(new int[]{2,3,6,7}, 7));
        
        System.out.println("\nInput: nums=[1,2,3], target=6");
        System.out.println("Approach 1 (Memoization): Expected: 24, Output: " + 
                          combinationSum4(new int[]{1,2,3}, 6));
        System.out.println("Approach 2 (Tabulation): Expected: 24, Output: " + 
                          combinationSum4Tabulation(new int[]{1,2,3}, 6));
    }
}
