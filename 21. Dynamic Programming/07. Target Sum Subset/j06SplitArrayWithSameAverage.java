/**
 * LeetCode 805. Split Array With Same Average
 * 
 * Problem Statement:
 *     You are given an integer array nums. You should partition the array into
 *     two non-empty subsets such that the average of elements in both subsets
 *     is equal. Return true if you can partition, otherwise return false.
 * 
 * Input:
 *     - nums: int[] (1 <= nums.length <= 30, 0 <= nums[i] <= 10^4)
 * 
 * Output:
 *     - boolean: true if there exists a split into two non-empty subsets with
 *       the same average, false otherwise
 * 
 * Example:
 *     Input: nums = [1,2,3,4,5,6,7,8]
 *     Output: true
 * 
 *     Explanation:
 *     Subset A = [1,4,7], Subset B = remaining. Both have average 4.
 */

import java.util.Arrays;

public class j06SplitArrayWithSameAverage {

    /**
     * Approach: Target Sum Subset by Size (DP + Memoization)
     * 
     * Intuition:
     * - If we split nums into subsets A and B with equal average, then
     *   sum(A)/|A| = sum(B)/|B| = sum(nums)/n.
     * - For any subset size k (1..n-1), the subset sum must be
     *   targetSum = sum(nums) * k / n which must be an integer.
     * - So we try each k and ask: does there exist a subset of size k whose
     *   sum equals targetSum? If yes, we can split.
     * 
     * Explanation:
     * - Compute total sum and n.
     * - Iterate k from 1 to n-1. If (sum * k) % n != 0, skip because the
     *   target sum is not integral for this k.
     * - Otherwise, set targetSum = (sum * k) / n. Use a 3D DP memo:
     *   dp[index][target][size] = -1 unknown, 0 false, 1 true
     *   meaning: with elements from index..end, can we pick exactly 'size'
     *   elements to get 'target' sum?
     * - Recurse with two choices: take current element or skip it.
     * - If any k returns true, answer is true.
     * 
     * Time Complexity: O(n * sum * n) in the worst case over feasible sizes,
     *   but practically constrained by target sums that are checked.
     * Space Complexity: O(n * sum * n) for the memo table for a given k.
     * 
     * @param nums    Input array of non-negative integers
     * @return        True if the array can be split into two non-empty subsets
     *                with the same average; otherwise false
     */
    public static boolean splitArraySameAverage(int[] nums) {
        // Validate input parameters and handle edge cases
        if (nums == null || nums.length < 2) {
            // Need two non-empty subsets; arrays of size < 2 cannot be split
            return false;
        }

        int n = nums.length;                 // Number of elements
        int sum = 0;                          // Total sum of the array
        for (int i = 0; i < n; i++) {
            sum += nums[i];                   // Accumulate total sum
        }

        // Try each possible subset size k from 1 to n-1
        for (int size = 1; size < n; size++) {
            // The target sum must be integral for this size to be feasible
            if ((sum * size) % n != 0) {
                continue;                     // Skip infeasible sizes
            }
            int s1 = (sum * size) / n;        // Target sum for this subset size

            // 3D DP memo: dp[index][target][size]
            int[][][] dp = new int[n + 1][s1 + 1][size + 1];
            for (int i = 0; i <= n; i++) {
                for (int s = 0; s <= s1; s++) {
                    Arrays.fill(dp[i][s], -1); // Initialize as unknown
                }
            }

            // If there exists a subset of 'size' elements summing to 's1'
            if (canPartitionWithSizeAndTargetMemo(nums, dp, s1, 0, size)) {
                return true;                   // Found a valid split
            }
        }

        return false;                          // No valid split found
    }

    /**
     * Helper Method: canPartitionWithSizeAndTargetMemo
     * 
     * Intuition:
     * - Decide for each index whether to include nums[index] in the subset,
     *   tracking how many elements we still need (size) and remaining target.
     * - Use memoization to avoid recomputation across states.
     * 
     * Explanation:
     * - Base cases:
     *   * If size == 0, we succeed only when target == 0.
     *   * If index == n or target < 0, no valid selection is possible.
     * - Transition:
     *   * take = pick nums[index] (target - nums[index], size - 1)
     *   * notTake = skip nums[index]
     * - Memoize result as 1 or 0.
     * 
     * Time Complexity: O(n * target * size) states for fixed parameters.
     * Space Complexity: O(n * target * size) for the memo table.
     * 
     * @param nums    Input array
     * @param dp      3D memo table: -1 unknown, 0 false, 1 true
     * @param target  Remaining sum to achieve
     * @param index   Current index in nums
     * @param size    Remaining elements to pick
     * @return        True if feasible from this state; otherwise false
     */
    public static boolean canPartitionWithSizeAndTargetMemo(int[] nums, int[][][] dp, int target, int index, int size) {
        // If required size is picked, success iff target is also satisfied
        if (size == 0) {
            return target == 0;
        }
        // If no more elements or invalid target, fail
        if (index == nums.length) {
            return false;
        }
        if (target < 0) {
            return false;
        }

        // Return memoized result if available
        if (dp[index][target][size] != -1) {
            return dp[index][target][size] == 1;
        }

        // Choice 1: take current element
        boolean take = canPartitionWithSizeAndTargetMemo(nums, dp, target - nums[index], index + 1, size - 1);
        // Choice 2: do not take current element
        boolean notTake = canPartitionWithSizeAndTargetMemo(nums, dp, target, index + 1, size);

        // Memoize and return
        if (take || notTake) {
            dp[index][target][size] = 1;
            return true;
        } else {
            dp[index][target][size] = 0;
            return false;
        }
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("[1,2,3,4,5,6,7,8] -> expected: true,  output: "
                + splitArraySameAverage(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println("[3,1]             -> expected: false, output: "
                + splitArraySameAverage(new int[]{3,1}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("[]                -> expected: false, output: "
                + splitArraySameAverage(new int[]{}));
        System.out.println("null              -> expected: false, output: "
                + splitArraySameAverage(null));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("[0,0]            -> expected: true,  output: "
                + splitArraySameAverage(new int[]{0,0}));
        System.out.println("[10000,10000]    -> expected: true,  output: "
                + splitArraySameAverage(new int[]{10000,10000}));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("[0,0,0,1,1,1]    -> expected: true,  output: "
                + splitArraySameAverage(new int[]{0,0,0,1,1,1}));
        System.out.println("[5,3,11,19,2]    -> expected: false, output: "
                + splitArraySameAverage(new int[]{5,3,11,19,2}));
    }
}


