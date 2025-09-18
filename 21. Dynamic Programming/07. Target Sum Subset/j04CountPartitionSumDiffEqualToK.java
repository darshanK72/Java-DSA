/**
 * GeeksForGeeks. Count Partitions with Given Difference K
 * 
 * Problem Statement:
 *     Given an array arr of non-negative integers and an integer k, count the
 *     number of ways to partition the array into two subsets S1 and S2 such
 *     that the absolute difference of their sums is exactly k. Return the
 *     count modulo 1e9+7.
 * 
 * Input:
 *     - n: int (1 <= n <= 10^3 or per problem variant)
 *     - k: int (0 <= k <= 10^4)
 *     - arr: int[] (0 <= arr[i] <= 10^3)
 * 
 * Output:
 *     - int: number of valid partitions modulo 1e9+7
 * 
 * Example:
 *     Input: n = 4, arr = [1,1,2,3], k = 1
 *     Output: 3
 * 
 *     Explanation:
 *     There are three ways to split into two subsets with difference 1.
 */

import java.util.Arrays;

public class j04CountPartitionSumDiffEqualToK {

    /**
     * Approach: Transform to Count Subsets with Target Sum (DP + Memoization)
     * 
     * Intuition:
     * - Let total = sum(arr). We need S1 - S2 = k and S1 + S2 = total.
     *   Solving gives S1 = (total + k) / 2 and S2 = (total - k) / 2.
     * - We can count the number of subsets with sum (total - k)/2, which is
     *   equivalent and avoids overflow for larger totals. This requires that
     *   (total - k) >= 0 and (total - k) is even.
     * 
     * Explanation:
     * - Compute total. Validate feasibility: if (total - k) < 0 or odd, there
     *   are zero ways.
     * - Set target = (total - k) / 2. Count subsets with sum equal to target.
     * - Use top-down memoization dp[index][target] to count ways using
     *   elements from index..end.
     * - For each index, we either take arr[index] if it fits, or skip it.
     * 
     * Time Complexity: O(n * target)
     * Space Complexity: O(n * target) for memo table
     * 
     * @param n       number of elements in arr (must equal arr.length)
     * @param k       required difference between subset sums (>= 0)
     * @param arr     input array of non-negative integers
     * @return        number of valid partitions modulo 1e9+7
     */
    public static int countPartitions(int n, int k, int[] arr) {
        // Validate inputs and handle edge cases
        if (arr == null || n <= 0 || n != arr.length) {
            return 0;                           // Invalid inputs
        }

        long sum = 0;                           // Use long to avoid intermediate overflow
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];                      // Accumulate total sum
        }

        // If (sum - k) is negative or odd, it's infeasible
        if ((sum - k) < 0 || ((sum - k) & 1L) == 1L) {
            return 0;
        }
        int tar = (int) ((sum - k) / 2);       // Target subset sum

        // Initialize memo table with -1 (unknown)
        int[][] dp = new int[n + 1][tar + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Count subsets achieving target sum starting from index 0
        return countPartitionsMemoHelper(arr, dp, tar, 0);
    }

    /**
     * Helper Method: countPartitionsMemoHelper
     * 
     * Intuition:
     * - Count the number of ways to reach 'tar' using elements from 'index'
     *   onward by either taking or skipping each element.
     * 
     * Explanation:
     * - Base case: if index == n, we have one valid way iff tar == 0.
     * - Memoization: return cached result if available.
     * - Transition: ways = ways(skip) + ways(take if possible).
     * 
     * Time Complexity: O(n * tar)
     * Space Complexity: O(n * tar)
     * 
     * @param arr     input array
     * @param dp      memo table: -1 unknown, otherwise count modulo MOD
     * @param tar     remaining target sum
     * @param index   current index in the array
     * @return        number of ways modulo MOD
     */
    public static int countPartitionsMemoHelper(int[] arr, int[][] dp, int tar, int index) {
        final int MOD = 1000000007;             // Modulo constant as per problem

        // If we've considered all elements, success iff remaining target is 0
        if (index == arr.length) {
            return tar == 0 ? 1 : 0;
        }

        // Return memoized result when present
        if (dp[index][tar] != -1) {
            return dp[index][tar];
        }

        // Option 1: do not take current element
        int notTake = countPartitionsMemoHelper(arr, dp, tar, index + 1) % MOD;

        // Option 2: take current element if it does not exceed remaining target
        int take = 0;
        if (tar >= arr[index]) {
            take = countPartitionsMemoHelper(arr, dp, tar - arr[index], index + 1) % MOD;
        }

        // Store result modulo MOD and return
        return dp[index][tar] = (int) ((take + (long) notTake) % MOD);
    }

    public static void main(String[] args) {
        // Basic/Happy path cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("n=4, arr=[1,1,2,3], k=1 -> expected: 3, output: "
                + countPartitions(4, 1, new int[]{1,1,2,3}));
        System.out.println("n=3, arr=[1,2,3], k=0  -> expected: 2, output: "
                + countPartitions(3, 0, new int[]{1,2,3}));

        // Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("n=0, arr=[], k=0     -> expected: 0, output: "
                + countPartitions(0, 0, new int[]{}));
        System.out.println("n=1, arr=[0], k=0    -> expected: 1, output: "
                + countPartitions(1, 0, new int[]{0}));

        // Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("n=2, arr=[0,0], k=0  -> expected: 2, output: "
                + countPartitions(2, 0, new int[]{0,0}));
        System.out.println("n=2, arr=[1000,1000], k=0 -> expected: 2, output: "
                + countPartitions(2, 0, new int[]{1000,1000}));

        // Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("n=5, arr=[5,3,11,19,2], k=1 -> output: "
                + countPartitions(5, 1, new int[]{5,3,11,19,2}));
        System.out.println("n=6, arr=[0,0,0,0,0,0], k=0 -> expected: 64, output: "
                + countPartitions(6, 0, new int[]{0,0,0,0,0,0}));
    }
}

