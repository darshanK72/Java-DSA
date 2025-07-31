/**
 * GeeksForGeeks: Count Binary Strings Without Consecutive 1's
 * 
 * Problem Statement:
 *     Given a positive integer n, count all possible binary strings of length n 
 *     such that there are no consecutive 1's. A binary string is a string 
 *     consisting only of 0's and 1's.
 * 
 * Input:
 *     - n: Length of binary string (1 ≤ n ≤ 10^5)
 * 
 * Output:
 *     - int: Number of valid binary strings
 * 
 * Example:
 *     Input: n = 3
 *     Output: 5
 * 
 *     Explanation:
 *     Valid strings: "000", "001", "010", "100", "101"
 *     Invalid string: "011" (consecutive ones)
 */

import java.util.Arrays;

public class j03ConsecuitiveOnesNotAllowed {
    /**
     * Approach: Dynamic Programming with State
     * 
     * Intuition:
     * - Use previous digit state to ensure no consecutive ones
     * - Build strings digit by digit maintaining valid states
     * 
     * Explanation:
     * - Step 1: Use 2D DP array where:
     *     - First dimension (0/1) represents previous digit
     *     - Second dimension represents remaining length
     * - Step 2: For each position:
     *     - Can always add 0
     *     - Can add 1 only if previous digit was 0
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for DP array and recursion
     * 
     * @param n Length of binary string
     * @return Count of valid binary strings
     */
    public static int countStrings(int n) {
        // Initialize 2D DP array
        int[][] dp = new int[2][n + 1];
        Arrays.fill(dp[0], -1);        // For strings ending with 0
        Arrays.fill(dp[1], -1);        // For strings ending with 1
        
        // Start with no previous digit (0)
        return countNonConsecutive1String(n, dp, 0);
    }

    /**
     * Helper Method: Recursive String Building
     * 
     * Intuition:
     * - Build string from right to left
     * - Track previous digit to maintain constraint
     * 
     * Explanation:
     * - Step 1: Base case - empty string is valid
     * - Step 2: Check if already computed
     * - Step 3: Try adding 0 (always possible)
     * - Step 4: Try adding 1 (only if prev was 0)
     * 
     * @param n         Remaining length
     * @param dp        Memoization array
     * @param prevDigit Previous digit used (0 or 1)
     * @return         Count of valid strings
     */
    private static int countNonConsecutive1String(int n, int[][] dp, int prevDigit) {
        // Base case: empty string
        if (n == 0) return 1;
        
        // Return if already computed
        if (dp[prevDigit][n] != -1)
            return dp[prevDigit][n];
        
        // Try adding digit 0
        int withDigitZero = countNonConsecutive1String(n - 1, dp, 0);
        
        // Try adding digit 1 if previous was 0
        int withDigitOne = (prevDigit == 0) ? 
            countNonConsecutive1String(n - 1, dp, 1) : 0;
        
        // Store and return total count
        dp[prevDigit][n] = withDigitZero + withDigitOne;
        return dp[prevDigit][n];
    }

    public static void main(String[] args) {
        // Test Case 1: Small input
        System.out.println("\nSmall Input Test:");
        System.out.println("n = 3");
        System.out.println("Output: " + countStrings(3));
        System.out.println("Expected: 5");

        // Test Case 2: Minimal input
        System.out.println("\nMinimal Input Test:");
        System.out.println("n = 1");
        System.out.println("Output: " + countStrings(1));
        System.out.println("Expected: 2");

        // Test Case 3: Medium input
        System.out.println("\nMedium Input Test:");
        System.out.println("n = 5");
        System.out.println("Output: " + countStrings(5));
        System.out.println("Expected: 13");

        // Test Case 4: Larger input
        System.out.println("\nLarger Input Test:");
        System.out.println("n = 10");
        System.out.println("Output: " + countStrings(10));
        System.out.println("Expected: 144");
    }
}
