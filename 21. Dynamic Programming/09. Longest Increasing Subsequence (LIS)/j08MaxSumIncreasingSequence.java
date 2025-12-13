/**
 * GeeksForGeeks. Maximum Sum Increasing Subsequence (MSIS)
 *
 * Problem Statement:
 *     Given an array of integers, find the maximum sum of an increasing
 *     subsequence. A subsequence is increasing if each element is strictly
 *     greater than the previous one. The elements do not need to be
 *     contiguous in the original array.
 *
 * Input:
 *     - arr (int[]): array of integers; size can be 0 or more; values can be
 *       negative, zero, or positive.
 *
 * Output:
 *     - int: the maximum possible sum of an increasing subsequence.
 *
 * Example:
 *     Input: [1, 101, 2, 3, 100, 4, 5]
 *     Output: 106
 *
 *     Explanation:
 *     One optimal increasing subsequence is [1, 2, 3, 100] with sum 106.
 */

public class j08MaxSumIncreasingSequence {

    /**
     * Approach: Dynamic Programming (LIS variant accumulating sums)
     *
     * Intuition:
     * - For each index i, consider the best (maximum-sum) increasing subsequence
     *   that ends exactly at i. If we know the best sums for all positions j < i
     *   with arr[j] < arr[i], we can extend those subsequences by arr[i].
     *
     * Explanation:
     * - Let dp[i] be the maximum sum of an increasing subsequence that ends at i.
     * - Initialize dp[i] = arr[i] since the subsequence containing only arr[i]
     *   is valid and has sum arr[i].
     * - For each pair (j, i) with j < i:
     *     If arr[j] < arr[i], then we can extend the subsequence ending at j by
     *     arr[i]. Update dp[i] = max(dp[i], dp[j] + arr[i]).
     * - The answer is the maximum value among all dp[i].
     *
     * Time Complexity: O(n^2) due to the nested loops over indices.
     * Space Complexity: O(n) for the dp array storing best sums per index.
     *
     * @param arr    Input array of integers; may be null or empty.
     * @return       Maximum sum of an increasing subsequence; 0 for null/empty.
     */
    public static int maxSumIS(int arr[]) {
        // Handle edge cases: null or empty array yields sum 0 by definition
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // n: number of elements in the input array
        int n = arr.length;

        // dp[i]: maximum sum of an increasing subsequence that ends at index i
        int[] dp = new int[n];

        // maxSum: tracks the best sum across all endpoints
        int maxSum = Integer.MIN_VALUE;

        // Iterate over all indices as potential subsequence endpoints
        for (int i = 0; i < n; i++) {
            // Base subsequence containing only arr[i]
            dp[i] = arr[i];

            // Try extending from all previous indices j < i where arr[j] < arr[i]
            for (int j = 0; j < i; j++) {
                // Ensure strictly increasing condition before extension
                if (arr[j] < arr[i]) {
                    // Choose better between current dp[i] and extension via j
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            // Maintain global best sum observed so far
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [1, 2, 3], Expected: 6, Output: "
                + maxSumIS(new int[]{1, 2, 3}));
        System.out.println("Input: [3, 2, 1], Expected: 3, Output: "
                + maxSumIS(new int[]{3, 2, 1}));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [], Expected: 0, Output: "
                + maxSumIS(new int[]{}));
        System.out.println("Input: null, Expected: 0, Output: "
                + maxSumIS(null));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [Integer.MAX_VALUE], Output: "
                + maxSumIS(new int[]{Integer.MAX_VALUE}));
        System.out.println("Input: [Integer.MIN_VALUE], Output: "
                + maxSumIS(new int[]{Integer.MIN_VALUE}));

        // Special / Mixed Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [1, 101, 2, 3, 100, 4, 5], Expected: 106, Output: "
                + maxSumIS(new int[]{1, 101, 2, 3, 100, 4, 5}));
        System.out.println("Input: [10, 5, 4, 3], Expected: 10, Output: "
                + maxSumIS(new int[]{10, 5, 4, 3}));
        System.out.println("Input: [-1, -2, -3], Expected: -1, Output: "
                + maxSumIS(new int[]{-1, -2, -3}));
        System.out.println("Input: [8, 12, 2, 3, 15, 5, 7], Expected: 35, Output: "
                + maxSumIS(new int[]{8, 12, 2, 3, 15, 5, 7}));
    }
}