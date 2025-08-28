/**
 * LeetCode 91. Decode Ways
 *
 * Problem Statement:
 *     A message containing letters from 'A' to 'Z' can be encoded by mapping
 *     'A' -> 1, 'B' -> 2, ..., 'Z' -> 26. Given a non-empty string 's' that
 *     contains only digits, return the total number of ways to decode it.
 *
 * Input:
 *     - s (1 <= s.length <= 100): String of digits '0'..'9'. '0' cannot be
 *       decoded alone. Pairs '10' and '20' are valid, while any other pair
 *       starting with '0' is invalid.
 *
 * Output:
 *     - Integer: number of valid decodings of the string.
 *
 * Example:
 *     Input: "12"
 *     Output: 2
 *     Explanation:
 *         "1|2" -> 'A' 'B' and "12" -> 'L'.
 *
 *     Input: "226"
 *     Output: 3
 *     Explanation:
 *         "2|2|6" -> 'B' 'B' 'F', "22|6" -> 'V' 'F', "2|26" -> 'B' 'Z'.
 */

import java.util.Arrays;

public class j05DecodeWays {
    /**
     * Approach: Top-Down DP (Memoized Recursion)
     *
     * Intuition:
     * - At any index, we can decode either one digit (if it's '1'..'9') or two
     *   digits (if the pair forms a valid number 10..26). The total ways from
     *   the current position is the sum of ways from the next valid positions.
     * - This forms an overlapping subproblems structure suitable for DP.
     *
     * Explanation:
     * - Use recursion with memoization over index 'i'.
     * - Base: reaching index == s.length() contributes 1 valid decoding.
     * - If s[i] == '0', no decoding starts here (contributes 0).
     * - Otherwise, take one-digit path; optionally take two-digit path if the
     *   pair [i,i+1] is between 10 and 26 (inclusive).
     * - Memoize results in dp[i] to avoid recomputation.
     *
     * Time Complexity: O(n)
     *     Each index is computed once and cached.
     * Space Complexity: O(n)
     *     For memo array and recursion stack in worst case.
     *
     * @param s        Input digit string; may be null or empty
     * @return         Number of valid decodings; 0 for null/empty/invalid
     */
    public static int numDecodings(String s) {
        // Handle null or empty input explicitly as per problem definition
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Prepare memoization array initialized to -1 (uncomputed)
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);

        // Start recursive counting from index 0
        return countDecodings(s, dp, 0);
    }

    /**
     * Helper Method: countDecodings
     *
     * Intuition:
     * - Computes the number of decodings starting at a given index using
     *   memoization to store intermediate results.
     *
     * Explanation:
     * - If index reaches the end, we've formed a valid decoding (return 1).
     * - If current char is '0', it can't start a decoding (return 0).
     * - Otherwise, try one-digit and (if valid) two-digit decodes.
     *
     * Time Complexity: O(1) amortized per index due to memoization
     * Space Complexity: O(1) extra per call beyond memo references
     *
     * @param s        Input digit string (non-null when called)
     * @param dp       Memoization array; dp[i] stores ways from index i
     * @param index    Current position in the string
     * @return         Number of ways to decode from 'index'
     */
    public static int countDecodings(String s, int[] dp, int index) {
        // If we've consumed all characters, count as one valid decoding
        if (index == s.length()) return 1;

        // A '0' cannot be decoded as a standalone character
        if (s.charAt(index) == '0') return 0;

        // Return memoized result if already computed
        if (dp[index] != -1) return dp[index];

        // Decode single character at 'index'
        int totalWays = countDecodings(s, dp, index + 1);

        // Optionally decode two characters if they form 10..26
        if (index + 1 < s.length()) {
            int code = (s.charAt(index) - '0') * 10 + (s.charAt(index + 1) - '0');
            if (code >= 10 && code <= 26) {
                totalWays += countDecodings(s, dp, index + 2);
            }
        }

        // Cache and return the computed ways from 'index'
        return dp[index] = totalWays;
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     *
     * Intuition:
     * - Let dp[i] be the number of ways to decode the substring s[0..i-1].
     * - At position i, we can decode either one digit (if it's '1'..'9') or
     *   two digits (if the pair forms 10..26). This gives the recurrence:
     *     dp[i] = dp[i-1] (if s[i-1] != '0') + dp[i-2] (if s[i-2..i-1] is 10..26).
     * - Base cases: dp[0] = 1 (empty string), dp[1] = 1 (if s[0] != '0').
     *
     * Explanation:
     * - Handle null/empty input by returning 0.
     * - If the first character is '0', no valid decoding exists.
     * - Initialize dp[0] = 1 (empty string) and dp[1] = 1 (single valid digit).
     * - For each position i >= 2, check if we can decode one digit (s[i-1])
     *   and/or two digits (s[i-2..i-1]). Add the corresponding dp values.
     *
     * Time Complexity: O(n)
     *     Single pass through the string.
     * Space Complexity: O(n)
     *     dp array of size n+1.
     *
     * @param s        Input digit string; may be null or empty
     * @return         Number of valid decodings; 0 for null/empty/invalid
     */
    public static int numDecodingsTabulation(String s) {
        // Handle null or empty input explicitly
        if (s == null || s.length() == 0) {
            return 0;
        }

        // If the first character is '0', no valid decoding exists
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        // dp[i] stores the number of ways to decode substring s[0..i-1]
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1; // Empty string: one way (do nothing)
        dp[1] = 1; // Single character: one way if it's not '0'

        // Fill the dp table for positions 2..n
        for (int i = 2; i <= n; i++) {
            char one = s.charAt(i - 1);   // Current digit at position i-1
            char ten = s.charAt(i - 2);   // Previous digit at position i-2

            // Option 1: Decode single digit (1..9) at position i-1
            if (one != '0') {
                dp[i] += dp[i - 1];
            }

            // Option 2: Decode two digits (10..26) spanning positions i-2 and i-1
            int val = (ten - '0') * 10 + (one - '0');
            if (val >= 10 && val <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        // Return the number of ways to decode the entire string
        return dp[n];
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: '12', Expected: 2, Output: " + numDecodings("12"));
        System.out.println("Input: '226', Expected: 3, Output: " + numDecodings("226"));
        System.out.println("Input: '27', Expected: 1, Output: " + numDecodings("27"));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: '', Expected: 0, Output: " + numDecodings(""));
        System.out.println("Input: null, Expected: 0, Output: " + numDecodings(null));
        System.out.println("Input: '0', Expected: 0, Output: " + numDecodings("0"));
        System.out.println("Input: '06', Expected: 0, Output: " + numDecodings("06"));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: '1', Expected: 1, Output: " + numDecodings("1"));
        System.out.println("Input: '10', Expected: 1, Output: " + numDecodings("10"));

        // Special/Tricky Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: '101', Expected: 1, Output: " + numDecodings("101"));
        System.out.println("Input: '11106', Expected: 2, Output: " + numDecodings("11106"));
    }
}
