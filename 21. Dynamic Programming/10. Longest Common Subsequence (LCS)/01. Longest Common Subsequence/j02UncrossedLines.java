/**
 * LeetCode 1035. Uncrossed Lines
 *
 * Problem Statement:
 *     You are given two integer arrays nums1 and nums2. We write the
 *     integers of nums1 and nums2 (in order) on two separate horizontal
 *     lines. We may draw straight lines connecting equal numbers from
 *     nums1 and nums2. Lines must connect matching values and cannot
 *     cross. Return the maximum number of connecting lines we can draw.
 *
 * Input:
 *     - nums1: int array (0 <= nums1.length <= 500, values 0..2000)
 *     - nums2: int array (0 <= nums2.length <= 500, values 0..2000)
 *
 * Output:
 *     - Single integer: maximum number of non-crossing lines that can be
 *       drawn between equal numbers of the two arrays.
 *
 * Example:
 *     Input:
 *         nums1 = [1,4,2]
 *         nums2 = [1,2,4]
 *     Output:
 *         2
 *
 *     Explanation:
 *         Optimal non-crossing connections: (1 with 1) and (4 with 4),
 *         yielding 2 lines.
 */

public class j02UncrossedLines {

    /**
     * Approach: Longest Common Subsequence (Bottom-Up Tabulation)
     *
     * Intuition:
     * - Uncrossed lines correspond exactly to a longest common subsequence
     *   between nums1 and nums2 because choosing elements in order avoids
     *   crossings.
     * - Therefore, the answer is the LCS length of the two arrays.
     *
     * Explanation:
     * - Step 1: Create a 2D DP table dp[m + 1][n + 1] where dp[i][j]
     *   represents the LCS length for suffixes nums1[i..] and nums2[j..].
     * - Step 2: Iterate i from m - 1 down to 0 and j from n - 1 down to 0:
     *       * If nums1[i] == nums2[j], we connect them and add 1 to
     *         dp[i + 1][j + 1].
     *       * Otherwise, we take the better of skipping nums1[i] or
     *         skipping nums2[j], i.e., max(dp[i + 1][j], dp[i][j + 1]).
     * - Step 3: dp[0][0] holds the LCS length for the full arrays, which
     *   equals the maximum number of uncrossed lines.
     *
     * Time Complexity: O(m * n) where m = nums1.length, n = nums2.length,
     *     because each state (i, j) is processed once.
     *
     * Space Complexity: O(m * n) for the DP table.
     *
     * @param nums1  First integer array (non-null, length >= 0).
     * @param nums2  Second integer array (non-null, length >= 0).
     * @return       Maximum number of uncrossed lines between the arrays.
     */
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;                  // Length of first array
        int n = nums2.length;                  // Length of second array

        // DP table where dp[i][j] = LCS length of nums1[i..] and nums2[j..]
        int[][] dp = new int[m + 1][n + 1];

        // Fill table from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // If elements match, connect them and add 1 to diagonal
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    // Otherwise take the best of skipping from either array
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // Final answer for the full arrays
        return dp[0][0];
    }

    public static void main(String[] args) {
        // Basic / Happy path cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("nums1 = [1,4,2], nums2 = [1,2,4], "
                + "Expected: 2, Output: "
                + maxUncrossedLines(new int[]{1, 4, 2},
                                    new int[]{1, 2, 4}));
        System.out.println("nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2], "
                + "Expected: 3, Output: "
                + maxUncrossedLines(new int[]{2, 5, 1, 2, 5},
                                    new int[]{10, 5, 2, 1, 5, 2}));

        // Edge cases: one or both arrays empty
        System.out.println("\nEdge Cases:");
        System.out.println("nums1 = [], nums2 = [1,2,3], "
                + "Expected: 0, Output: "
                + maxUncrossedLines(new int[]{}, new int[]{1, 2, 3}));
        System.out.println("nums1 = [1,2,3], nums2 = [], "
                + "Expected: 0, Output: "
                + maxUncrossedLines(new int[]{1, 2, 3}, new int[]{}));
        System.out.println("nums1 = [], nums2 = [], "
                + "Expected: 0, Output: "
                + maxUncrossedLines(new int[]{}, new int[]{}));

        // Boundary cases: repeated values
        System.out.println("\nBoundary Cases:");
        System.out.println("nums1 = [1,1,1], nums2 = [1,1], "
                + "Expected: 2, Output: "
                + maxUncrossedLines(new int[]{1, 1, 1},
                                    new int[]{1, 1}));
        System.out.println("nums1 = [1,2,1,2], nums2 = [1,1,2,2], "
                + "Expected: 3, Output: "
                + maxUncrossedLines(new int[]{1, 2, 1, 2},
                                    new int[]{1, 1, 2, 2}));

        // Complex / Larger style cases
        System.out.println("\nComplex / Larger Cases:");
        System.out.println("nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1], "
                + "Expected: 2, Output: "
                + maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5},
                                    new int[]{1, 9, 2, 5, 1}));
        System.out.println("nums1 = [1,2,3,4,5,6], nums2 = [6,5,4,3,2,1], "
                + "Expected: 1, Output: "
                + maxUncrossedLines(new int[]{1, 2, 3, 4, 5, 6},
                                    new int[]{6, 5, 4, 3, 2, 1}));
    }
}
