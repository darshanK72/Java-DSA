/**
 * GeeksForGeeks. Minimum subset sum difference (Partition array with min diff)
 * 
 * Problem Statement:
 *     Given an array of non-negative integers, partition it into two subsets
 *     S1 and S2 such that the absolute difference of their sums is minimized.
 *     Return that minimum possible difference.
 * 
 * Input:
 *     - arr: int[] (0 <= arr[i])
 *     - n:   int   (n == arr.length, n >= 1)
 * 
 * Output:
 *     - int: minimum |sum(S1) - sum(S2)| over all partitions
 * 
 * Example:
 *     Input: arr = [1,6,11,5]
 *     Output: 1
 * 
 *     Explanation:
 *     Partition {1,5,6} and {11} gives sums 12 and 11; difference is 1.
 */

public class j07ArrayPartitionDiffSizeWithMinDifference {

    /**
     * Approach: Subset Sum Reachability up to Total/2 (DP - Tabulation)
     * 
     * Intuition:
     * - Let total = sum(arr). If subset S has sum s, the other subset has
     *   sum total - s, so the difference is |(total - s) - s| = |total - 2s|.
     * - To minimize the difference, we only need to know which sums up to
     *   total/2 are achievable. The best s is the achievable one closest to
     *   total/2.
     * 
     * Explanation:
     * - Build dp where dp[i][s] == 1 iff we can form sum s using first i
     *   elements. Initialize dp[*][0] = 1 (sum 0 via empty subset).
     * - Fill dp iterating elements and sum, standard 0/1 subset DP.
     * - Scan s in [0..total/2]; if reachable, update minimal |total - 2s|.
     * 
     * Time Complexity: O(n * total)
     * Space Complexity: O(n * total) for the DP table provided by caller
     * 
     * @param arr  input array (non-negative integers)
     * @param n    number of elements, must equal arr.length
     * @return     minimum possible subset sum difference
     */
    public static int minSubsetSumDifference(int[] arr, int n) {
        // Validate inputs and handle edge cases
        if (arr == null || n <= 0 || arr.length != n) {
            return 0; // By convention; no valid partition when input invalid
        }

        int sum = 0;                            // Total sum of array
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];                      // Accumulate sum
        }

        int[][] dp = new int[n + 1][sum + 1];  // DP table for reachability
        isSubsetSumTabulation(arr, dp, sum);    // Fill reachability table

        int min = Integer.MAX_VALUE;           // Track minimal difference
        for (int s = 0; s <= sum / 2; s++) {   // Only need to check up to half
            if (dp[n][s] == 1) {               // If sum s is achievable
                min = Math.min(min, Math.abs((sum - s) - s)); // |total - 2s|
            }
        }
        return min;
    }

    /**
     * Helper Method: isSubsetSumTabulation
     * 
     * Intuition:
     * - Compute classic subset-sum reachability using 0/1 knapsack DP.
     * 
     * Explanation:
     * - dp[i][s] = 1 if we can make sum s using first i elements, else 0.
     * - Initialize dp[*][0] = 1, then transition with include/exclude.
     * 
     * Time Complexity: O(n * sum)
     * Space Complexity: O(n * sum)
     * 
     * @param arr  input array
     * @param dp   DP table to be filled
     * @param sum  total sum upper bound for columns
     * @return     true iff sum can be formed using all n elements
     */
    public static boolean isSubsetSumTabulation(int arr[], int[][] dp, int sum) {
        // Validate input parameters
        if (arr == null)
            return sum == 0;                    // null array only makes sum 0
        int n = arr.length;                     // number of elements

        // dp[i][s] = 1 if sum s can be formed using first i elements
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;                       // base: sum 0 via empty subset
        }

        // Fill table row by row over elements and sums
        for (int index = 1; index <= n; index++) {
            for (int s = 1; s <= sum; s++) {
                if (arr[index - 1] <= s) {      // current element can contribute
                    int take = dp[index - 1][s - arr[index - 1]];   // include
                    int notTake = dp[index - 1][s];                  // exclude
                    if (take == 1 || notTake == 1) {
                        dp[index][s] = 1;       // reachable via either path
                    } else {
                        dp[index][s] = 0;       // not reachable
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
        // Basic/Happy path cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("arr=[1,6,11,5] -> expected: 1,  output: "
                + minSubsetSumDifference(new int[]{1,6,11,5}, 4));
        System.out.println("arr=[1,2,3,9]  -> expected: 3,  output: "
                + minSubsetSumDifference(new int[]{1,2,3,9}, 4));

        // Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("arr=[], n=0     -> expected: 0,  output: "
                + minSubsetSumDifference(new int[]{}, 0));
        System.out.println("arr=[0], n=1    -> expected: 0,  output: "
                + minSubsetSumDifference(new int[]{0}, 1));

        // Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("arr=[0,0], n=2  -> expected: 0,  output: "
                + minSubsetSumDifference(new int[]{0,0}, 2));
        System.out.println("arr=[1000,1000], n=2 -> expected: 0, output: "
                + minSubsetSumDifference(new int[]{1000,1000}, 2));

        // Special/Large-ish cases
        System.out.println("\nSpecial Cases:");
        System.out.println("arr=[3,1,4,2,2], n=5 -> expected: 0,  output: "
                + minSubsetSumDifference(new int[]{3,1,4,2,2}, 5));
    }
}
