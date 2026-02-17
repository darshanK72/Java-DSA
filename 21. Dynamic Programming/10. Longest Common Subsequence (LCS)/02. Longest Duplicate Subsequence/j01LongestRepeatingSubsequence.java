/*-
 * GFG. Longest Repeating Subsequence
 * 
 * Problem Statement:
 *     Given a string s, find the length of the longest subsequence that
 *     appears at least twice in the string (with different indices). In
 *     other words, find the longest subsequence that can be formed using
 *     two different sets of indices from the same string.
 * 
 * Input:
 *     - s: String (length >= 0, lowercase letters)
 * 
 * Output:
 *     - Single integer: length of the longest repeating subsequence
 * 
 * Example:
 *     Input: s = "aabebcdd"
 *     Output: 3
 *     
 *     Explanation:
 *     The longest repeating subsequence is "abd" which appears twice:
 *     positions [0,1,5] and [2,4,6]. Both form "abd" with same characters
 *     but different indices.
 */

import java.util.*;

public class j01LongestRepeatingSubsequence {

    /*-
     * Approach: Dynamic Programming (Modified LCS with Index Constraint)
     * 
     * Intuition:
     * - This is similar to finding LCS of a string with itself, but with
     *   a key constraint: we can only match characters at different indices
     * - Use a 2D DP table where dp[i][j] represents the longest repeating
     *   subsequence when comparing substring from index i with substring
     *   from index j
     * - The condition (i != j) ensures we're using different indices
     * - When characters match AND indices differ, we can extend the
     *   subsequence; otherwise we take the maximum of skipping either char
     * 
     * Explanation:
     * - Step 1: Create a 2D DP table dp[n+1][n+1] initialized to 0, where
     *   dp[i][j] = longest repeating subsequence length when comparing
     *   s[i..] with s[j..] with the constraint that i != j
     * - Step 2: Iterate i from n-1 down to 0 and j from n-1 down to 0:
     *     If s[i] == s[j] AND i != j, then dp[i][j] = 1 + dp[i+1][j+1]
     *     (we found matching chars at different indices, extend diagonally)
     *     Else dp[i][j] = max(dp[i+1][j], dp[i][j+1])
     *     (try skipping either character)
     * - Step 3: dp[0][0] contains the length of the longest repeating
     *   subsequence in the string
     * 
     * Time Complexity: O(n * n) = O(n²) where n is the string length,
     *     as we fill an n×n DP table
     * 
     * Space Complexity: O(n * n) = O(n²) for the DP table
     * 
     * @param s    Input string (non-null, lowercase letters, length >= 0)
     * @return     Length of the longest repeating subsequence
     */
    public static int LongestRepeatingSubsequence(String s) {
        int n = s.length();                    // Length of the string
        
        // DP table where dp[i][j] = longest repeating subsequence length
        // when comparing s[i..] with s[j..]
        int[][] dp = new int[n + 1][n + 1];
        
        // Fill DP table from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Match characters only if they're at different indices
                if (s.charAt(i) == s.charAt(j) && i != j) {
                    // Characters match at different indices, extend diagonal
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    // Either chars don't match or indices are same,
                    // take max of skipping from either position
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        
        return dp[0][0];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: \"aabebcdd\", Expected: 3, Output: "
                + LongestRepeatingSubsequence("aabebcdd"));
        System.out.println("Input: \"aabbccdd\", Expected: 3, Output: "
                + LongestRepeatingSubsequence("aabbccdd"));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: \"\", Expected: 0, Output: "
                + LongestRepeatingSubsequence(""));
        System.out.println("Input: \"a\", Expected: 0, Output: "
                + LongestRepeatingSubsequence("a"));
        System.out.println("Input: \"ab\", Expected: 0, Output: "
                + LongestRepeatingSubsequence("ab"));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: \"aa\", Expected: 1, Output: "
                + LongestRepeatingSubsequence("aa"));
        System.out.println("Input: \"aaa\", Expected: 1, Output: "
                + LongestRepeatingSubsequence("aaa"));
        System.out.println("Input: \"aaaa\", Expected: 2, Output: "
                + LongestRepeatingSubsequence("aaaa"));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: \"abcabcab\", Expected: 3, Output: "
                + LongestRepeatingSubsequence("abcabcab"));
        System.out.println("Input: \"abcdefgh\", Expected: 0, Output: "
                + LongestRepeatingSubsequence("abcdefgh"));
    }
}
