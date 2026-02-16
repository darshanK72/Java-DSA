/*-
 * LeetCode 718. Maximum Length of Repeated Subarray
 * 
 * Problem Statement:
 *     Given two integer arrays nums1 and nums2, return the length of
 *     their longest common subarray (not subsequence, but contiguous
 *     elements in the same order). A subarray is a contiguous part of
 *     an array.
 * 
 * Input:
 *     - nums1: int array (0 <= nums1.length <= 1000, values 0..100)
 *     - nums2: int array (0 <= nums2.length <= 1000, values 0..100)
 * 
 * Output:
 *     - Single integer: length of the longest common subarray between
 *       the two arrays
 * 
 * Example:
 *     Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 *     Output: 3
 *     
 *     Explanation:
 *     The longest common subarray is [3,2,1] with length 3.
 */

public class j04MaxLengthRepeatedArray {

    /*-
     * Approach: Dynamic Programming (Bottom-Up Tabulation)
     * 
     * Intuition:
     * - Unlike longest common subsequence (LCS), we need contiguous elements
     *   to match. This means if elements don't match, the streak resets to 0
     * - Use dp[i][j] to store the length of common subarray ending at
     *   indices i (in nums1) and j (in nums2)
     * - If nums1[i] == nums2[j], extend the previous subarray length by 1
     * - If they don't match, the length becomes 0 (streak breaks)
     * - Track the maximum length found throughout the computation
     * 
     * Explanation:
     * - Step 1: Create a 2D DP table dp[m+1][n+1] initialized to 0, where
     *   dp[i][j] = length of common subarray ending exactly at positions
     *   i-1 in nums1 and j-1 in nums2
     * - Step 2: Iterate from right to left (from m-1 to 0, n-1 to 0):
     *     If nums1[i] == nums2[j], set dp[i][j] = 1 + dp[i+1][j+1]
     *     This extends the common subarray from the next positions
     * - Step 3: After each comparison, update maxLength with the maximum
     *   value seen so far
     * - Step 4: Return maxLength as the final answer
     * 
     * Time Complexity: O(m * n) where m = nums1.length, n = nums2.length
     *     Each cell (i, j) is processed exactly once
     * 
     * Space Complexity: O(m * n) for the DP table
     * 
     * @param nums1    First integer array (non-null, length >= 0)
     * @param nums2    Second integer array (non-null, length >= 0)
     * @return         Length of the longest common subarray
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;                  // Length of first array
        int n = nums2.length;                  // Length of second array
        
        // DP table where dp[i][j] = length of common subarray ending at
        // indices i-1 in nums1 and j-1 in nums2
        int[][] dp = new int[m + 1][n + 1];
        
        // Track the maximum length found
        int maxLength = 0;
        
        // Fill DP table from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // If elements match, extend the subarray length
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                // Update maximum length seen so far
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        
        // Return the longest common subarray length
        return maxLength;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7], "
                + "Expected: 3, Output: "
                + findLength(new int[]{1, 2, 3, 2, 1},
                             new int[]{3, 2, 1, 4, 7}));
        System.out.println("nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0], "
                + "Expected: 5, Output: "
                + findLength(new int[]{0, 0, 0, 0, 0},
                             new int[]{0, 0, 0, 0, 0}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("nums1 = [], nums2 = [1,2,3], "
                + "Expected: 0, Output: "
                + findLength(new int[]{}, new int[]{1, 2, 3}));
        System.out.println("nums1 = [1,2,3], nums2 = [], "
                + "Expected: 0, Output: "
                + findLength(new int[]{1, 2, 3}, new int[]{}));
        System.out.println("nums1 = [], nums2 = [], "
                + "Expected: 0, Output: "
                + findLength(new int[]{}, new int[]{}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("nums1 = [1], nums2 = [1], "
                + "Expected: 1, Output: "
                + findLength(new int[]{1}, new int[]{1}));
        System.out.println("nums1 = [1], nums2 = [2], "
                + "Expected: 0, Output: "
                + findLength(new int[]{1}, new int[]{2}));
        System.out.println("nums1 = [1,2], nums2 = [2,1], "
                + "Expected: 1, Output: "
                + findLength(new int[]{1, 2}, new int[]{2, 1}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("nums1 = [1,2,3,4,5], nums2 = [1,2,3,4,5], "
                + "Expected: 5, Output: "
                + findLength(new int[]{1, 2, 3, 4, 5},
                             new int[]{1, 2, 3, 4, 5}));
        System.out.println("nums1 = [1,2,3,4,5], nums2 = [9,8,7,6,5], "
                + "Expected: 1, Output: "
                + findLength(new int[]{1, 2, 3, 4, 5},
                             new int[]{9, 8, 7, 6, 5}));
    }
}
