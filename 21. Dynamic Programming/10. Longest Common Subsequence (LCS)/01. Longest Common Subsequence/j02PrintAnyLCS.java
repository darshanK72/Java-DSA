/*-
 * GFG / LeetCode style: Print Any One LCS (Longest Common Subsequence)
 * 
 * Problem Statement:
 *     Given two strings s1 and s2, find and return any one valid longest
 *     common subsequence. A subsequence maintains the relative order of
 *     elements but doesn't need to be contiguous. If multiple LCS exist,
 *     return any one of them.
 * 
 * Input:
 *     - n: Length of first string s1 (1 <= n <= 100)
 *     - m: Length of second string s2 (1 <= m <= 100)
 *     - s1: First string (characters a-z)
 *     - s2: Second string (characters a-z)
 * 
 * Output:
 *     - String: any one valid longest common subsequence of s1 and s2
 * 
 * Example:
 *     Input: n = 5, m = 7, s1 = "abacd", s2 = "acbaced"
 *     Output: "aacd" (or "abcd" or "acad" - any valid LCS)
 *     
 *     Explanation:
 *     The length of LCS is 4. Multiple LCS strings exist; we return any one.
 */

public class j02PrintAnyLCS {

    /*-
     * Approach: LCS Length Computation + Backtracking Reconstruction
     * 
     * Intuition:
     * - First compute the LCS length using bottom-up DP where dp[i][j]
     *   represents the LCS length of suffixes s1[i..] and s2[j..]
     * - Then backtrack from position (0, 0) using the DP table to
     *   reconstruct any valid LCS string by following the path that
     *   maintains the computed LCS length
     * - When characters match, append to the result and move diagonally
     * - When they don't match, choose the direction (down or right) that
     *   preserves the DP value
     * 
     * Explanation:
     * - Step 1: Create a 2D DP table dp[n+1][m+1] and fill it bottom-up:
     *     If s1[i] == s2[j], dp[i][j] = 1 + dp[i+1][j+1]
     *     Else dp[i][j] = max(dp[i+1][j], dp[i][j+1])
     * - Step 2: Initialize pointers i=0, j=0, and lcs=dp[0][0] (LCS length)
     * - Step 3: Backtrack to reconstruct the LCS:
     *     While characters are added < lcs:
     *       If s1[i] == s2[j], append character and increment i, j, k
     *       Else if dp[i+1][j] == dp[i][j], move down (skip s1[i])
     *       Else move right (skip s2[j])
     * - Step 4: Return the constructed LCS string
     * 
     * Time Complexity: O(n * m) for DP computation + O(n + m) for
     *     backtracking = O(n * m)
     * 
     * Space Complexity: O(n * m) for the DP table + O(lcs) for storing
     *     the result string
     * 
     * @param n     Length of first string (1 <= n <= 100)
     * @param m     Length of second string (1 <= m <= 100)
     * @param s1    First string (non-null, characters a-z)
     * @param s2    Second string (non-null, characters a-z)
     * @return      Any one valid longest common subsequence string
     */
    public static String findLCS(int n, int m, String s1, String s2) {
        // DP table where dp[i][j] = LCS length of s1[i..] and s2[j..]
        int[][] dp = new int[n + 1][m + 1];
        
        // Fill DP table from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // If characters match, extend the diagonal by 1
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    // Otherwise take the maximum of skipping either character
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        // Initialize pointers and result string builder
        int i = 0;                             // Pointer for s1
        int j = 0;                             // Pointer for s2
        int k = 0;                             // Counter for characters added
        int lcs = dp[i][j];                    // LCS length from DP table
        StringBuilder out = new StringBuilder();
        
        // Backtrack to reconstruct any valid LCS
        while (k < lcs) {
            // If characters match, add to result and move diagonally
            if (s1.charAt(i) == s2.charAt(j)) {
                out.append(s1.charAt(i));
                i++;
                j++;
                k++;
            } else {
                // Characters don't match, follow path that maintains LCS length
                // If dp[i+1][j] == dp[i][j], we can skip from s1
                if (dp[i][j] == dp[i + 1][j]) {
                    i++;
                } else {
                    // Otherwise skip from s2
                    j++;
                }
            }
        }
        
        return out.toString();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("n=5, m=7, s1=\"abacd\", s2=\"acbaced\", Output: "
                + findLCS(5, 7, "abacd", "acbaced"));
        System.out.println("n=5, m=6, s1=\"oxcpqr\", s2=\"sxyspmq\", Output: "
                + findLCS(5, 6, "oxcpqr", "sxyspmq"));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("n=1, m=3, s1=\"a\", s2=\"abc\", Output: "
                + findLCS(1, 3, "a", "abc"));
        System.out.println("n=3, m=1, s1=\"abc\", s2=\"a\", Output: "
                + findLCS(3, 1, "abc", "a"));
        System.out.println("n=1, m=1, s1=\"a\", s2=\"b\", Output: "
                + findLCS(1, 1, "a", "b"));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("n=1, m=1, s1=\"a\", s2=\"a\", Output: "
                + findLCS(1, 1, "a", "a"));
        System.out.println("n=3, m=3, s1=\"abc\", s2=\"abc\", Output: "
                + findLCS(3, 3, "abc", "abc"));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("n=5, m=5, s1=\"abcde\", s2=\"edcba\", Output: "
                + findLCS(5, 5, "abcde", "edcba"));
        System.out.println("n=4, m=6, s1=\"aggtab\", s2=\"gxtxayb\", Output: "
                + findLCS(4, 6, "aggt", "gxtxayb"));
    }
}
