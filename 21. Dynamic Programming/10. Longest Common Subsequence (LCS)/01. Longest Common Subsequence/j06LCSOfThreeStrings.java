/*-
 * GFG / LeetCode style: LCS of Three Strings
 *
 * Problem Statement:
 *     Given three strings s1, s2 and s3, find the length of their
 *     longest common subsequence (LCS) that appears in all three strings.
 *
 * Input:
 *     - s1: First string (length >= 0)
 *     - s2: Second string (length >= 0)
 *     - s3: Third string (length >= 0)
 *
 * Output:
 *     - Single integer: length of the longest common subsequence present
 *       in all three strings
 *
 * Example:
 *     Input: s1 = "abcdef", s2 = "acbcf", s3 = "abcf"
 *     Output: 3
 *
 *     Explanation:
 *     One possible common subsequence of length 3 is "acf".
 */

public class j06LCSOfThreeStrings {

    /*-
     * Approach: 3D Dynamic Programming (Bottom-Up Tabulation)
     *
     * Intuition:
     * - Extend the classic 2-string LCS DP to three dimensions where
     *   dp[i][j][k] represents LCS length of suffixes s1[i..], s2[j..],
     *   and s3[k..].
     * - When all three characters match at positions i,j,k, extend the
     *   diagonal by 1; otherwise take the maximum of advancing in any
     *   one string (skip a character) to preserve subsequence order.
     *
     * Explanation:
     * - Step 1: Initialize a 3D DP array of size (l1+1) x (l2+1) x (l3+1)
     *   filled with zeros. The extra row/column/depth handle empty suffixes.
     * - Step 2: Iterate i from l1-1..0, j from l2-1..0, k from l3-1..0:
     *     If s1[i] == s2[j] == s3[k], dp[i][j][k] = 1 + dp[i+1][j+1][k+1]
     *     Else dp[i][j][k] = max(dp[i+1][j][k], dp[i][j+1][k], dp[i][j][k+1])
     * - Step 3: dp[0][0][0] contains the length of the LCS across all three
     *   strings.
     *
     * Time Complexity: O(l1 * l2 * l3) where l1,l2,l3 are string lengths.
     * Space Complexity: O(l1 * l2 * l3) for the 3D DP table.
     *
     * @param s1   First string (non-null)
     * @param s2   Second string (non-null)
     * @param s3   Third string (non-null)
     * @return     Length of longest common subsequence among the three
     */
    public static int lcsOf3(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();

        // 3D DP table where dp[i][j][k] = LCS length of s1[i..], s2[j..], s3[k..]
        int[][][] dp = new int[l1 + 1][l2 + 1][l3 + 1];

        // Fill table from bottom-right-back to top-left-front
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                for (int k = l3 - 1; k >= 0; k--) {
                    // If all three characters match, extend diagonal
                    if (s1.charAt(i) == s2.charAt(j) && s1.charAt(i) == s3.charAt(k)) {
                        dp[i][j][k] = dp[i + 1][j + 1][k + 1] + 1;
                    } else {
                        // Otherwise take the best option of skipping one char
                        dp[i][j][k] = Math.max(dp[i + 1][j][k],
                                Math.max(dp[i][j + 1][k], dp[i][j][k + 1]));
                    }
                }
            }
        }

        return dp[0][0][0];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("s1=\"abcdef\", s2=\"acbcf\", s3=\"abcf\" -> Expected: 3, Output: "
                + lcsOf3("abcdef", "acbcf", "abcf"));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("s1=\"\", s2=\"abc\", s3=\"abc\" -> Expected: 0, Output: "
                + lcsOf3("", "abc", "abc"));
        System.out.println("s1=\"abc\", s2=\"\", s3=\"abc\" -> Expected: 0, Output: "
                + lcsOf3("abc", "", "abc"));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("s1=\"a\", s2=\"a\", s3=\"a\" -> Expected: 1, Output: "
                + lcsOf3("a", "a", "a"));
        System.out.println("s1=\"a\", s2=\"b\", s3=\"a\" -> Expected: 0, Output: "
                + lcsOf3("a", "b", "a"));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("s1=\"xyzabcd\", s2=\"zabcdxy\", s3=\"abcz\" -> Expected: 3, Output: "
                + lcsOf3("xyzabcd", "zabcdxy", "abcz"));
    }
}
