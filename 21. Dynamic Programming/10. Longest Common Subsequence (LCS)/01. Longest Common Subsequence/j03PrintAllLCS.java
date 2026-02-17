/*-
 * GFG. Print All LCS
 * 
 * Problem Statement:
 *     Given two strings s1 and s2, find and print all possible longest
 *     common subsequences (LCS) between them. Multiple LCS sequences may
 *     exist and should all be returned in lexicographically sorted order.
 * 
 * Input:
 *     - s1: First string (length >= 0, characters a-z)
 *     - s2: Second string (length >= 0, characters a-z)
 * 
 * Output:
 *     - List of all distinct LCS strings in lexicographically sorted order
 * 
 * Example:
 *     Input: s1 = "abacd", s2 = "acbaced"
 *     Output: ["aacd", "abcd", "acad"]
 *     
 *     Explanation:
 *     All three strings are valid LCS with length 4. They are returned
 *     in lexicographically sorted order.
 */

import java.util.*;

public class j03PrintAllLCS {

    /*-
     * Approach: Backtracking with Memoization (Bottom-Up DP)
     * 
     * Intuition:
     * - First compute the LCS length using dynamic programming, where
     *   dp[i][j] represents the LCS length of suffixes s1[i..] and s2[j..]
     * - Then use backtracking from the start (0, 0) to reconstruct all
     *   possible LCS strings by following paths that maintain the same
     *   LCS length
     * - Use a TreeSet to automatically eliminate duplicates and sort
     *   lexicographically
     * 
     * Explanation:
     * - Step 1: Compute LCS lengths bottom-up from right to left:
     *     If s1[i] == s2[j], dp[i][j] = 1 + dp[i+1][j+1]
     *     Else dp[i][j] = max(dp[i+1][j], dp[i][j+1])
     * - Step 2: Backtrack from (0,0) to reconstruct all LCS strings:
     *     If s1[i] == s2[j], append this character and move diagonally
     *     Else follow paths where LCS length remains unchanged by moving
     *     down (skip s1[i]) or right (skip s2[j])
     * - Step 3: Use TreeSet to store results in sorted order and avoid
     *     duplicate sequences
     * 
     * Time Complexity: O(m*n + k*L) where m,n are string lengths, k is
     *     the number of distinct LCS strings, and L is the average LCS
     *     length. DP takes O(m*n), backtracking explores all paths.
     * 
     * Space Complexity: O(m*n) for the DP table plus O(k*L) for storing
     *     all LCS strings
     * 
     * @param s1    First string (non-null, length >= 0)
     * @param s2    Second string (non-null, length >= 0)
     * @return      List of all distinct LCS strings in sorted order
     */
    public static List<String> allLCS(String s1, String s2) {
        int n = s1.length();                   // Length of first string
        int m = s2.length();                   // Length of second string
        
        // DP table where dp[i][j] = LCS length of s1[i..] and s2[j..]
        int[][] dp = new int[n + 1][m + 1];
        
        // Compute LCS lengths bottom-up
        longestCommonSubsequence(s1, s2, dp);
        
        // TreeSet to store unique LCS strings in sorted order
        TreeSet<String> out = new TreeSet<>();
        
        // Backtrack to reconstruct all possible LCS strings
        getAllLCS(dp, out, "", 0, 0, n, m, s1, s2);
        
        // Convert TreeSet to ArrayList and return
        ArrayList<String> list = new ArrayList<>();
        for (String ans : out) {
            list.add(ans);
        }
        return list;
    }

    /*-
     * Helper Method: getAllLCS (Backtracking)
     * 
     * Intuition:
     * - Reconstruct all LCS strings by exploring multiple paths through
     *   the DP table that yield valid LCS sequences
     * - At each position, we check if current characters match or if we
     *   can continue with the same LCS length by moving in other directions
     * 
     * Explanation:
     * - Base case: When i reaches n or j reaches m, we've completed one
     *     LCS string and add it to the result set
     * - If s1[i] == s2[j]: Characters match, so append to LCS and move
     *     diagonally to (i+1, j+1)
     * - If mismatch: Only follow paths where dp[i+1][j] == dp[i][j] or
     *     dp[i][j+1] == dp[i][j], ensuring we stay on paths with valid
     *     LCS length
     * 
     * Time Complexity: O(k*L) where k is number of distinct LCS and L
     *     is average LCS length
     * 
     * Space Complexity: O(L) for recursion depth (height of call stack)
     * 
     * @param dp    DP table with LCS lengths computed
     * @param out   TreeSet to store unique LCS strings
     * @param lcs   Current LCS string being constructed
     * @param i     Current index in s1
     * @param j     Current index in s2
     * @param n     Length of s1
     * @param m     Length of s2
     * @param s1    First string
     * @param s2    Second string
     */
    private static void getAllLCS(int[][] dp, TreeSet<String> out, String lcs,
                                   int i, int j, int n, int m, String s1, String s2) {
        // Base case: reached end of either string, add LCS to result
        if (i == n || j == m) {
            out.add(lcs);
            return;
        }

        // If characters match, append to LCS and explore diagonal path
        if (s1.charAt(i) == s2.charAt(j)) {
            getAllLCS(dp, out, lcs + s1.charAt(i), i + 1, j + 1, n, m, s1, s2);
        } else {
            // Characters don't match, follow paths where LCS length stays same
            // Only skip s1[i] if LCS length remains unchanged
            if (dp[i + 1][j] == dp[i][j]) {
                getAllLCS(dp, out, lcs, i + 1, j, n, m, s1, s2);
            }
            // Only skip s2[j] if LCS length remains unchanged
            if (dp[i][j + 1] == dp[i][j]) {
                getAllLCS(dp, out, lcs, i, j + 1, n, m, s1, s2);
            }
        }
    }

    /*-
     * Helper Method: longestCommonSubsequence (DP Computation)
     * 
     * Intuition:
     * - Build up LCS lengths from right to left, storing the length of
     *   the longest common subsequence for each suffix pair
     * - This table is used later during backtracking to identify valid paths
     * 
     * Explanation:
     * - Fill the DP table from bottom-right to top-left
     * - If characters match at positions i and j, add 1 to the diagonal
     *     value (move both pointers forward)
     * - If they don't match, take the maximum of skipping either character
     *     (move down or right)
     * 
     * Time Complexity: O(m*n) where m and n are string lengths
     * 
     * Space Complexity: O(1) excluding the input DP table space
     * 
     * @param text1    First string
     * @param text2    Second string
     * @param dp       DP table to fill with LCS lengths
     */
    private static void longestCommonSubsequence(String text1, String text2, int[][] dp) {
        int n = text1.length();                // Length of first string
        int m = text2.length();                // Length of second string

        // Fill DP table from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // If characters match, add 1 to diagonal value
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    // Otherwise take max of skipping either character
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: s1 = \"abacd\", s2 = \"acbaced\"");
        System.out.println("Output: " + allLCS("abacd", "acbaced"));
        System.out.println("Input: s1 = \"oxcpqrsvwf\", s2 = \"sxyspmqyhbt\"");
        System.out.println("Output: " + allLCS("oxcpqrsvwf", "sxyspmqyhbt"));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: s1 = \"\", s2 = \"abc\"");
        System.out.println("Output: " + allLCS("", "abc"));
        System.out.println("Input: s1 = \"abc\", s2 = \"\"");
        System.out.println("Output: " + allLCS("abc", ""));
        System.out.println("Input: s1 = \"\", s2 = \"\"");
        System.out.println("Output: " + allLCS("", ""));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: s1 = \"a\", s2 = \"a\"");
        System.out.println("Output: " + allLCS("a", "a"));
        System.out.println("Input: s1 = \"a\", s2 = \"b\"");
        System.out.println("Output: " + allLCS("a", "b"));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: s1 = \"abc\", s2 = \"abc\"");
        System.out.println("Output: " + allLCS("abc", "abc"));
        System.out.println("Input: s1 = \"abcd\", s2 = \"dcba\"");
        System.out.println("Output: " + allLCS("abcd", "dcba"));
    }
}
