/**
 * GeeksForGeeks / LeetCode Variant. Subset Sum Problem
 * 
 * Problem Statement:
 *     Given an array of non-negative integers `arr` and an integer `sum`,
 *     determine whether there exists a subset of the array whose elements
 *     add up exactly to `sum`. This is the classic decision version of the
 *     Subset Sum problem.
 * 
 * Input:
 *     - arr (int[]): Array of non-negative integers (size N, 0 <= arr[i])
 *     - sum (int): Target sum (0 <= sum)
 * 
 * Output:
 *     - boolean: true if at least one subset sums to `sum`, else false
 * 
 * Example:
 *     Input: arr = [3, 34, 4, 12, 5, 2], sum = 9
 *     Output: true
 * 
 *     Explanation:
 *     Subset [4,5] sums to 9. Other valid subsets may exist as well.
 */

import java.util.Arrays;

public class j01CheckTargetSubsetSum {

    /**
     * Approach 1: Top-Down DP (Memoization)
     * 
     * Intuition:
     * - At each index we decide to include the current element in the subset
     *   or skip it. This forms an overlapping-subproblems structure with
     *   state defined by (index, remainingSum).
     * - We memoize results for a given state to avoid recomputation.
     * 
     * Explanation:
     * - Use a 2D cache `dp[index][sum]` storing 1 (true), 0 (false), -1 (unknown).
     * - Recurse over choices: take current element if it does not exceed `sum`,
     *   or skip it. If any path returns true, cache and return true.
     * - Base cases: sum == 0 => true; sum < 0 or index beyond range => false.
     * 
     * Time Complexity: O(N * sum)
     * Space Complexity: O(N * sum) for memo table and recursion stack.
     * 
     * @param arr    Input array of non-negative integers (size N)
     * @param sum    Target sum (0 <= sum)
     * @return       True if a subset totals to `sum`, else false
     */
    public static boolean isSubsetSumMemo(int arr[], int sum) {
        // Validate input parameters
        if (arr == null) return sum == 0; // null array can only make sum 0
        int n = arr.length;               // number of elements

        // Initialize memo table with -1 meaning "unknown"
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);      // mark row as uncomputed
        }

        // Start recursion from index 0 and target `sum`
        return isSubsetSumMemoHelper(arr, dp, sum, 0);
    }

    /**
     * Helper Method:
     * 
     * Intuition:
     * - Explore the decision tree of including/excluding the current element
     *   while caching results per (index, remainingSum) to avoid repeated work.
     * 
     * Explanation:
     * - If `sum` is 0, we found a valid subset; return true.
     * - If out of bounds or `sum` negative, return false.
     * - If cached, return memoized boolean.
     * - Otherwise, try including current element and excluding it.
     * 
     * Time Complexity: O(N * sum)
     * Space Complexity: O(N * sum)
     * 
     * @param arr     Input array
     * @param dp      Memo table storing 1/0/-1 for (index,sum)
     * @param sum     Remaining sum to achieve
     * @param index   Current index in `arr`
     * @return        True if possible from this state, else false
     */
    public static boolean isSubsetSumMemoHelper(int[] arr, int[][] dp, int sum, int index) {
        // Success base: sum has been achieved
        if (sum == 0)
            return true;                 // empty subset works for sum 0
        // Guard: if index crosses the end, no elements left to choose
        if (index == arr.length)
            return false;                // cannot form further subsets
        // Failure base: negative sum cannot be formed by non-negatives
        if (sum < 0)
            return false;                // invalid path due to overshoot
        // Memoized result check
        if (dp[index][sum] != -1) {
            if (dp[index][sum] == 1)
                return true;             // previously computed as true
            else
                return false;            // previously computed as false
        }
        // Choice 1: take current number (move to next index and reduce sum)
        boolean take = isSubsetSumMemoHelper(arr, dp, sum - arr[index], index + 1);
        // Choice 2: skip current number (move to next index, sum unchanged)
        boolean notTake = isSubsetSumMemoHelper(arr, dp, sum, index + 1);

        // Cache result: 1 if either choice works, else 0
        if (take || notTake) {
            dp[index][sum] = 1;          // mark as achievable
            return true;
        } else {
            dp[index][sum] = 0;          // mark as not achievable
            return false;
        }
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     * 
     * Intuition:
     * - Build a table `dp[i][s]` meaning whether sum `s` can be formed using
     *   the first `i` elements. Transition mirrors the memo recurrence.
     * 
     * Explanation:
     * - Initialize `dp[i][0] = 1` for all i (sum 0 is always achievable).
     * - For each element and target sum, inherit possibility from excluding
     *   the element and, if viable, from including it.
     * 
     * Time Complexity: O(N * sum)
     * Space Complexity: O(N * sum)
     * 
     * @param arr    Input array of non-negative integers (size N)
     * @param sum    Target sum (0 <= sum)
     * @return       True if a subset totals to `sum`, else false
     */
    public static boolean isSubsetSumTabulation(int arr[], int sum) {
        // Validate input parameters
        if (arr == null) return sum == 0; // null array only makes sum 0
        int n = arr.length;               // number of elements

        // dp[i][s] = 1 if sum s can be formed using first i elements
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;                // base: sum 0 achievable with empty subset
        }

        // Fill table row by row over elements and sums
        for (int index = 1; index <= n; index++) {
            for (int s = 1; s <= sum; s++) {
                if (arr[index - 1] <= s) { // current element can contribute
                    int take = dp[index - 1][s - arr[index - 1]]; // include
                    int notTake = dp[index - 1][s];               // exclude
                    if (take == 1 || notTake == 1) {
                        dp[index][s] = 1;  // reachable via either path
                    } else {
                        dp[index][s] = 0;  // not reachable
                    }
                } else {
                    dp[index][s] = dp[index - 1][s]; // cannot include current
                }
            }
        }

        // Final answer whether sum can be formed using all N elements
        return dp[n][sum] == 1;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[] arr1 = {3, 34, 4, 12, 5, 2};
        System.out.println("Memo  [arr1, sum=9] Expected: true  Output: " +
                isSubsetSumMemo(arr1, 9));
        System.out.println("Tabul [arr1, sum=9] Expected: true  Output: " +
                isSubsetSumTabulation(arr1, 9));

        int[] arr2 = {1, 2, 3};
        System.out.println("Memo  [arr2, sum=7] Expected: false Output: " +
                isSubsetSumMemo(arr2, 7));
        System.out.println("Tabul [arr2, sum=7] Expected: false Output: " +
                isSubsetSumTabulation(arr2, 7));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[] empty = {};
        System.out.println("Memo  [empty, sum=0] Expected: true  Output: " +
                isSubsetSumMemo(empty, 0));
        System.out.println("Tabul [empty, sum=0] Expected: true  Output: " +
                isSubsetSumTabulation(empty, 0));
        System.out.println("Memo  [null, sum=0] Expected: true  Output: " +
                isSubsetSumMemo(null, 0));
        System.out.println("Tabul [null, sum=0] Expected: true  Output: " +
                isSubsetSumTabulation(null, 0));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[] singleMax = {Integer.MAX_VALUE};
        System.out.println("Memo  [singleMax, sum=0] Expected: true  Output: " +
                isSubsetSumMemo(singleMax, 0));
        System.out.println("Tabul [singleMax, sum=0] Expected: true  Output: " +
                isSubsetSumTabulation(singleMax, 0));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[] sorted = {1, 2, 3, 4, 5};
        System.out.println("Memo  [sorted, sum=10] Expected: true  Output: " +
                isSubsetSumMemo(sorted, 10)); // 5+4+1
        System.out.println("Tabul [sorted, sum=10] Expected: true  Output: " +
                isSubsetSumTabulation(sorted, 10));
        int[] revSorted = {5, 4, 3, 2, 1};
        System.out.println("Memo  [revSorted, sum=11] Expected: true  Output: " +
                isSubsetSumMemo(revSorted, 11)); // 5+4+2
        System.out.println("Tabul [revSorted, sum=11] Expected: true  Output: " +
                isSubsetSumTabulation(revSorted, 11));
    }
}
