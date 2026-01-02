/**
 * LeetCode 1143. Longest Common Subsequence
 *
 * Problem Statement:
 *     Given two strings text1 and text2, return the length of their
 *     longest common subsequence. If there is no common subsequence,
 *     return 0. A subsequence of a string is a new string generated
 *     from the original string with some characters (can be none)
 *     deleted without changing the relative order of the remaining
 *     characters.
 *
 * Input:
 *     - text1: First string consisting of lowercase English letters
 *              (1 <= text1.length <= 1000).
 *     - text2: Second string consisting of lowercase English letters
 *              (1 <= text2.length <= 1000).
 *
 * Output:
 *     - Single integer representing the length of the longest common
 *       subsequence between text1 and text2.
 *
 * Example:
 *     Input:
 *         text1 = "abcde"
 *         text2 = "ace"
 *     Output:
 *         3
 *
 *     Explanation:
 *         The longest common subsequence is "ace" which has length 3.
 *         Note that "ace" is not a substring because the characters
 *         are not required to be contiguous, only in the same order.
 */

import java.util.Arrays;

public class j01LongestCommonSubsequence {

    /**
     * Approach 1: Top-Down DP (Memoization)
     *
     * Intuition:
     * - We compare characters at current indices i and j in both strings.
     * - If they match, we must include this character and move both indices
     *   forward, adding 1 to the result.
     * - If they do not match, we have two choices: skip character from
     *   text1 or skip from text2, and take the better (max) result.
     * - These overlapping subproblems (same i, j pairs) are cached in a
     *   memo table so each state is solved once.
     *
     * Explanation:
     * - Step 1: Create a 2D memo array dp[m][n] initialized with -1 where
     *   dp[i][j] stores LCS length for suffixes starting at i and j.
     * - Step 2: Use a recursive helper that:
     *       * Stops when either index hits the end of its string.
     *       * Returns cached value if dp[i][j] is already computed.
     *       * If characters match, sets dp[i][j] to 1 plus result of
     *         moving both indices.
     *       * If they do not match, sets dp[i][j] to max of moving i or
     *         moving j.
     * - Step 3: The initial call starts from (0, 0) covering entire
     *   strings.
     *
     * Time Complexity:
     *     O(m * n) where m = text1.length, n = text2.length, since each
     *     state (i, j) is computed at most once.
     *
     * Space Complexity:
     *     O(m * n) for the memo table plus O(m + n) recursion stack in
     *     the worst case.
     *
     * @param text1  First input string (non-null, length >= 0).
     * @param text2  Second input string (non-null, length >= 0).
     * @return       Length of the longest common subsequence between the
     *               two strings.
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();                // Length of first string
        int n = text2.length();                // Length of second string

        // Memoization table where dp[i][j] stores LCS length for
        // text1[i..] and text2[j..]. Initialize with -1 to indicate
        // uncomputed states.
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);            // Mark all cells as unvisited
        }

        // Start recursion from index 0 of both strings
        return getLCS(dp, text1, text2, 0, 0);
    }

    /**
     * Helper Method: getLCS
     *
     * Intuition:
     * - Computes the LCS length for suffixes starting at indices i and j.
     * - If characters at i and j match, they contribute +1 and we move
     *   both indices.
     * - If they do not match, we explore both possibilities (skip from
     *   first or second string) and take the maximum.
     *
     * Explanation:
     * - Base case: if either i reaches end of s1 or j reaches end of s2,
     *   no characters remain to match, so return 0.
     * - Memo check: if dp[i][j] != -1, we already computed this state,
     *   return cached value to avoid recomputation.
     * - Match case: if s1[i] == s2[j], set dp[i][j] to
     *       1 + getLCS(i + 1, j + 1).
     * - Mismatch case: set dp[i][j] to max of skipping s1[i] or s2[j],
     *   i.e. max(getLCS(i + 1, j), getLCS(i, j + 1)).
     *
     * Time Complexity:
     *     O(m * n) because each pair (i, j) is processed once.
     *
     * Space Complexity:
     *     O(m * n) for dp plus recursion stack.
     *
     * @param dp  Memo table storing intermediate LCS results.
     * @param s1  First string.
     * @param s2  Second string.
     * @param i   Current index in s1.
     * @param j   Current index in s2.
     * @return    LCS length for suffixes s1[i..] and s2[j..].
     */
    public static int getLCS(int[][] dp, String s1, String s2, int i, int j) {
        // If either index reaches end of its string, no subsequence remains
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }

        // If we already computed this state, return cached result
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // If current characters match, include this character and move both
        // indices forward
        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = getLCS(dp, s1, s2, i + 1, j + 1) + 1;
        } else {
            // If characters do not match, either skip s1[i] or skip s2[j]
            // and choose the better result
            dp[i][j] = Math.max(
                    getLCS(dp, s1, s2, i + 1, j),
                    getLCS(dp, s1, s2, i, j + 1)
            );
        }

        return dp[i][j];                       // Return memoized result
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     *
     * Intuition:
     * - Instead of recursion, fill a DP table iteratively from the end
     *   of the strings towards the beginning.
     * - dp[i][j] will store the LCS length of text1[i..] and text2[j..].
     * - We process indices in reverse so that when computing dp[i][j],
     *   its dependent states dp[i+1][j], dp[i][j+1], and dp[i+1][j+1]
     *   are already known.
     *
     * Explanation:
     * - Step 1: Create dp[m + 1][n + 1] initialized to 0. Extra row and
     *   column naturally handle base cases (empty suffix).
     * - Step 2: Iterate i from m - 1 down to 0 and j from n - 1 down to 0:
     *       * If characters match, dp[i][j] = 1 + dp[i + 1][j + 1].
     *       * Else, dp[i][j] = max(dp[i + 1][j], dp[i][j + 1]).
     * - Step 3: The answer for full strings is at dp[0][0].
     *
     * Time Complexity:
     *     O(m * n) since we fill an m by n table once.
     *
     * Space Complexity:
     *     O(m * n) for the DP table.
     *
     * @param text1  First input string (non-null, length >= 0).
     * @param text2  Second input string (non-null, length >= 0).
     * @return       Length of the longest common subsequence between the
     *               two strings.
     */
    public static int longestCommonSubsequenceTabulation(String text1,
                                                         String text2) {
        int m = text1.length();                // Length of first string
        int n = text2.length();                // Length of second string

        // DP table where dp[i][j] represents LCS length of text1[i..] and
        // text2[j..]. Extra row and column represent empty suffixes.
        int[][] dp = new int[m + 1][n + 1];

        // Fill the table from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // If characters match, take 1 plus diagonal value
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    // Otherwise, take best of skipping from text1 or text2
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // Final answer for the whole strings
        return dp[0][0];
    }

    /**
     * Approach 3: Bottom-Up DP (Space Optimized)
     *
     * Intuition:
     * - From the tabulation approach, each row dp[i][*] depends only on the
     *   next row dp[i + 1][*] and its own right neighbor.
     * - This means we do not need the full 2D table; we can keep only two
     *   1D arrays representing "current" and "next" rows.
     * - We still process from bottom-right to top-left so dependencies are
     *   already computed when needed.
     *
     * Explanation:
     * - Step 1: Maintain an array next[j] representing dp[i + 1][j] for the
     *   row below the current one.
     * - Step 2: For each i from m - 1 down to 0, construct a new array
     *   curr[j] that will represent dp[i][j]:
     *       * If characters match, curr[j] = 1 + next[j + 1].
     *       * Otherwise, curr[j] = max(next[j], curr[j + 1]).
     * - Step 3: After finishing row i, assign curr to next and move to the
     *   previous row. At the end, next[0] holds the LCS length.
     *
     * Time Complexity:
     *     O(m * n) as we still iterate over all i, j states once.
     *
     * Space Complexity:
     *     O(n) because we use only two 1D arrays of size n + 1 instead of
     *     an (m + 1) by (n + 1) table.
     *
     * @param text1  First input string (non-null, length >= 0).
     * @param text2  Second input string (non-null, length >= 0).
     * @return       Length of the longest common subsequence between the
     *               two strings using space-optimized DP.
     */
    public static int longestCommonSubsequenceTabulationSpaceOptimized(
            String text1,
            String text2) {
        int m = text1.length();                // Length of first string
        int n = text2.length();                // Length of second string

        // "next" represents dp[i + 1][*] for the row below current i.
        int[] next = new int[n + 1];

        // Iterate rows from bottom to top
        for (int i = m - 1; i >= 0; i--) {
            // "curr" represents dp[i][*] for current row
            int[] curr = new int[n + 1];

            // Iterate columns from right to left
            for (int j = n - 1; j >= 0; j--) {
                // If characters match, take 1 plus diagonal value using
                // next row and next column
                if (text1.charAt(i) == text2.charAt(j)) {
                    curr[j] = next[j + 1] + 1;
                } else {
                    // Otherwise, choose the best between:
                    // - Skipping text1[i]  -> next[j]
                    // - Skipping text2[j]  -> curr[j + 1]
                    curr[j] = Math.max(next[j], curr[j + 1]);
                }
            }

            // Move current row up to become the "next" row for i - 1
            next = curr;
        }

        // LCS length for full strings is stored at index 0
        return next[0];
    }

    public static void main(String[] args) {
        // Basic / Happy path test cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("text1 = \"abcde\", text2 = \"ace\", "
                + "Expected: 3, Output: "
                + longestCommonSubsequence("abcde", "ace"));
        System.out.println("text1 = \"abc\", text2 = \"abc\", "
                + "Expected: 3, Output: "
                + longestCommonSubsequence("abc", "abc"));
        System.out.println("text1 = \"abc\", text2 = \"def\", "
                + "Expected: 0, Output: "
                + longestCommonSubsequence("abc", "def"));

        // Edge cases: one or both strings empty
        System.out.println("\nEdge Cases:");
        System.out.println("text1 = \"\", text2 = \"abc\", "
                + "Expected: 0, Output: "
                + longestCommonSubsequence("", "abc"));
        System.out.println("text1 = \"abc\", text2 = \"\", "
                + "Expected: 0, Output: "
                + longestCommonSubsequence("abc", ""));
        System.out.println("text1 = \"\", text2 = \"\", "
                + "Expected: 0, Output: "
                + longestCommonSubsequence("", ""));

        // Boundary cases: repeated characters
        System.out.println("\nBoundary Cases:");
        System.out.println("text1 = \"aaaaa\", text2 = \"aaa\", "
                + "Expected: 3, Output: "
                + longestCommonSubsequence("aaaaa", "aaa"));
        System.out.println("text1 = \"abc\", text2 = \"aaaaabc\", "
                + "Expected: 3, Output: "
                + longestCommonSubsequence("abc", "aaaaabc"));

        // Complex / Larger input style cases
        System.out.println("\nComplex / Larger Cases:");
        System.out.println("text1 = \"AGGTAB\", text2 = \"GXTXAYB\", "
                + "Expected: 4, Output: "
                + longestCommonSubsequence("AGGTAB", "GXTXAYB"));
        System.out.println("Tabulation check (same case), "
                + "Expected: 4, Output: "
                + longestCommonSubsequenceTabulation("AGGTAB", "GXTXAYB"));
    }
}
