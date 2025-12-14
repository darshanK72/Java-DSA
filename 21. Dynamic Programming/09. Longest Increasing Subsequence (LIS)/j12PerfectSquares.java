/**
 * LeetCode 279. Perfect Squares
 * 
 * Problem Statement:
 *     Given an integer n, return the least number of perfect square numbers 
 *     that sum to n. A perfect square is an integer that is the square of an 
 *     integer; in other words, it is the product of some integer with itself. 
 *     For example, 1, 4, 9, and 16 are perfect squares, while 3 and 11 are 
 *     not.
 * 
 * Input:
 *     - n (1 <= n <= 10^4)
 * 
 * Output:
 *     - Integer representing the least number of perfect square numbers that 
 *       sum to n
 * 
 * Example:
 *     Input: n = 12
 *     Output: 3
 * 
 *     Explanation:
 *     12 can be expressed as the sum of three 4's: 12 = 4 + 4 + 4.
 * 
 *     Input: n = 13
 *     Output: 2
 * 
 *     Explanation:
 *     13 can be expressed as the sum of 4 and 9: 13 = 4 + 9.
 */

public class j12PerfectSquares {

    /**
     * Approach: Dynamic Programming
     * 
     * Intuition:
     * - For any number n, we can express it as n = (n - j*j) + j*j where j*j is a perfect square
     * - The minimum number of perfect squares for n is 1 plus the minimum number of perfect squares for (n - j*j)
     * - We try all possible perfect squares j*j <= n and take the minimum
     * - This is a classic DP problem where we build solutions bottom-up from smaller subproblems
     * - dp[i] represents the minimum number of perfect squares needed to sum to i
     * 
     * Explanation:
     * - Step 1: Initialize DP array
     *   - dp[0] = 0 (0 requires 0 perfect squares)
     *   - dp[1] = 1 (1 is a perfect square itself)
     *   - For i >= 2, we'll compute dp[i] using previous values
     * - Step 2: For each number i from 2 to n
     *   - Initialize min to i (worst case: sum of i ones)
     *   - Try all perfect squares j*j <= i
     *   - For each perfect square j*j, check if we can use it
     *   - Update min = min(min, dp[i - j*j]) to find the best previous state
     *   - Set dp[i] = min + 1 (add the current perfect square j*j)
     * - Step 3: Return dp[n] - This gives the minimum number of perfect squares needed to sum to n
     * 
     * Time Complexity: O(n * sqrt(n)) where n is the input number
     *  - Outer loop: O(n) iterations
     *  - Inner loop: O(sqrt(n)) iterations (j*j <= i)
     * 
     * Space Complexity: O(n) where n is the input number
     *  - DP array of size n+1
     * 
     * @param n    Positive integer (1 <= n <= 10^4)
     * @return     Minimum number of perfect square numbers that sum to n
     */
    public static int numSquares(int n) {
        // Initialize DP array where dp[i] stores minimum perfect squares 
        // needed to sum to i
        int[] dp = new int[n + 1];
        
        // Base case: 0 requires 0 perfect squares
        dp[0] = 0;
        // Base case: 1 is a perfect square itself, requires 1
        dp[1] = 1;
        
        // Build DP array from 2 to n
        for (int i = 2; i <= n; i++) {
            // Start with j=1 to generate perfect squares
            int j = 1;
            // Initialize min to worst case (sum of i ones)
            int min = i;
            
            // Try all perfect squares j*j <= i
            while (j * j <= i) {
                // Update min with minimum perfect squares needed for 
                // (i - j*j) plus 1 for current square
                min = Math.min(min, dp[i - j * j]);
                j++;
            }
            
            // Store minimum perfect squares needed for i
            dp[i] = min + 1;
        }

        // Return minimum perfect squares needed to sum to n
        return dp[n];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: 12, Expected: 3, Output: " + 
            numSquares(12));
        System.out.println("Input: 13, Expected: 2, Output: " + 
            numSquares(13));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: 1, Expected: 1, Output: " + 
            numSquares(1));
        System.out.println("Input: 4, Expected: 1, Output: " + 
            numSquares(4));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: 2, Expected: 2, Output: " + 
            numSquares(2));
        System.out.println("Input: 3, Expected: 3, Output: " + 
            numSquares(3));
        System.out.println("Input: 10000, Expected: 1, Output: " + 
            numSquares(10000));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: 9, Expected: 1, Output: " + 
            numSquares(9));
        System.out.println("Input: 43, Expected: 3, Output: " + 
            numSquares(43));
        System.out.println("Input: 99, Expected: 3, Output: " + 
            numSquares(99));
    }
}
