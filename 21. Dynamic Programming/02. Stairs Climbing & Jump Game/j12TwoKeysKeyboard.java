/**
 * Two Keys Keyboard - Minimum Steps to Get 'n' 'A's
 * 
 * Problem Statement:
 *     Initially on a notepad only one character 'A' is present. You can perform 
 *     two operations on this notepad for each step:
 *     1. Copy All: You can copy all the characters present on the notepad 
 *        (partial copy is not allowed).
 *     2. Paste: You can paste the characters which are copied last time.
 *     Given a number n, return the minimum number of operations to get exactly 
 *     n 'A' on the notepad.
 * 
 * Input:
 *     - n: int (1 <= n <= 1000)
 * 
 * Output:
 *     - int: minimum number of operations required
 * 
 * Example:
 *     Input: n = 3
 *     Output: 3
 * 
 *     Explanation:
 *     Intitally, we have one 'A'.
 *     In step 1, we use Copy All operation.
 *     In step 2, we use Paste operation to get 'AA'.
 *     In step 3, we use Paste operation to get 'AAA'.
 * 
 *     Input: n = 1
 *     Output: 0
 * 
 *     Explanation:
 *     We already have one 'A', so no operations needed.
 */

import java.util.Arrays;

public class j12TwoKeysKeyboard {

    /**
     * Approach 1: Dynamic Programming with Memoization (Top-Down)
     * 
     * Intuition:
     * - We need to track two states: current output (number of A's) and buffer (copied A's)
     * - At each step, we can either copy all current A's or paste from buffer
     * - Use memoization to avoid recalculating overlapping subproblems
     * - The goal is to reach exactly n A's with minimum operations
     * 
     * Explanation:
     * - Step 1: Initialize 2D DP array to store results for (output, buffer) states
     * - Step 2: Base case: if output == n, return 0 (no more operations needed)
     * - Step 3: If output > n, return MAX_VALUE (invalid state)
     * - Step 4: Try paste operation if buffer > 0 and output + buffer <= n
     * - Step 5: Try copy operation if output < n and output != buffer (avoid redundant copy)
     * - Step 6: Return minimum of all valid operations
     * 
     * Time Complexity: O(n^2) where n is the target number of A's
     * Space Complexity: O(n^2) for DP array and recursion stack
     * 
     * @param n  Target number of A's to achieve
     * @return   Minimum number of operations required
     */
    public static int minSteps(int n) {
        // Handle edge case: if n == 1, no operations needed
        if (n == 1) {
            return 0;
        }
        
        // Initialize 2D DP array with -1 to mark uncomputed states
        // dp[output][buffer] represents minimum operations needed to reach n A's
        // starting with 'output' A's and 'buffer' A's in clipboard
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        // Start with 1 A (output) and 0 A in buffer (clipboard)
        return performOperations(n, dp, 0, 1);
    }

    /**
     * Helper Method: Recursive DP with Memoization
     * 
     * Intuition:
     * - Recursively try copy and paste operations
     * - Track current output (A's on screen) and buffer (A's in clipboard)
     * - Use memoization to cache computed results
     * 
     * Explanation:
     * - Step 1: Check base cases (output == n or output > n)
     * - Step 2: Check if result is already computed (memoization)
     * - Step 3: Try paste operation: add buffer to output (cost: 1 operation)
     * - Step 4: Try copy operation: copy all current output to buffer (cost: 1 operation)
     * - Step 5: Return minimum operations among all valid paths
     * 
     * Time Complexity: O(1) per call due to memoization
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param n       Target number of A's to achieve
     * @param dp      2D memoization array
     * @param buffer  Number of A's in clipboard (copied but not pasted)
     * @param output  Current number of A's on the notepad
     * @return        Minimum operations needed from current state
     */
    public static int performOperations(int n, int[][] dp, int buffer, int output) {
        // Base case: if we've exceeded the target, this path is invalid
        if (output > n) {
            return Integer.MAX_VALUE;
        }
        
        // Base case: if we've reached the target, no more operations needed
        if (output == n) {
            return 0;
        }

        // Return memoized result if already computed
        if (dp[output][buffer] != -1) {
            return dp[output][buffer];
        }

        // Initialize answer to maximum possible value
        int ans = Integer.MAX_VALUE;

        // Try paste operation: paste buffer content to output
        // Only if buffer > 0 and the result won't exceed target
        if (buffer > 0 && output + buffer <= n) {
            int paste = performOperations(n, dp, buffer, output + buffer);
            if (paste != Integer.MAX_VALUE) {
                ans = Math.min(ans, paste + 1); // +1 for paste operation
            }
        }

        // Try copy operation: copy all current output to buffer
        // Only if output < n and output != buffer (avoid redundant copy)
        if (output < n && output != buffer) {
            int copy = performOperations(n, dp, output, output);
            if (copy != Integer.MAX_VALUE) {
                ans = Math.min(ans, copy + 1); // +1 for copy operation
            }
        }

        // Store and return the computed result
        return dp[output][buffer] = ans;
    }

    /**
     * Approach 2: Mathematical Solution (Prime Factorization)
     * 
     * Intuition:
     * - The optimal solution involves finding the prime factorization of n
     * - Each prime factor represents a copy-paste sequence
     * - The sum of all prime factors gives the minimum operations
     * 
     * Explanation:
     * - Step 1: Find all prime factors of n
     * - Step 2: Sum all prime factors
     * - Step 3: Return the sum as minimum operations
     * 
     * Time Complexity: O(sqrt(n)) for prime factorization
     * Space Complexity: O(1) for constant extra space
     * 
     * @param n  Target number of A's to achieve
     * @return   Minimum number of operations required
     */
    public static int minStepsMathematical(int n) {
        // Handle edge case
        if (n == 1) return 0;
        
        int result = 0;
        int d = 2; // Start with smallest prime factor
        
        // Find prime factors of n
        while (n > 1) {
            while (n % d == 0) {
                result += d; // Add prime factor to result
                n /= d;      // Divide n by the prime factor
            }
            d++; // Try next potential prime factor
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: n=3, Expected: 3");
        System.out.println("Memoization Output: " + minSteps(3));
        System.out.println("Mathematical Output: " + minStepsMathematical(3));
        
        System.out.println("\nInput: n=6, Expected: 5");
        System.out.println("Memoization Output: " + minSteps(6));
        System.out.println("Mathematical Output: " + minStepsMathematical(6));
        
        System.out.println("\nInput: n=8, Expected: 6");
        System.out.println("Memoization Output: " + minSteps(8));
        System.out.println("Mathematical Output: " + minStepsMathematical(8));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, Expected: 0");
        System.out.println("Memoization Output: " + minSteps(1));
        System.out.println("Mathematical Output: " + minStepsMathematical(1));
        
        System.out.println("Input: n=2, Expected: 2");
        System.out.println("Memoization Output: " + minSteps(2));
        System.out.println("Mathematical Output: " + minStepsMathematical(2));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=4, Expected: 4");
        System.out.println("Memoization Output: " + minSteps(4));
        System.out.println("Mathematical Output: " + minStepsMathematical(4));
        
        System.out.println("Input: n=5, Expected: 5");
        System.out.println("Memoization Output: " + minSteps(5));
        System.out.println("Mathematical Output: " + minStepsMathematical(5));
        
        System.out.println("Input: n=7, Expected: 7");
        System.out.println("Memoization Output: " + minSteps(7));
        System.out.println("Mathematical Output: " + minStepsMathematical(7));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=9, Expected: 6");
        System.out.println("Memoization Output: " + minSteps(9));
        System.out.println("Mathematical Output: " + minStepsMathematical(9));
        
        System.out.println("Input: n=12, Expected: 7");
        System.out.println("Memoization Output: " + minSteps(12));
        System.out.println("Mathematical Output: " + minStepsMathematical(12));
        
        System.out.println("Input: n=16, Expected: 8");
        System.out.println("Memoization Output: " + minSteps(16));
        System.out.println("Mathematical Output: " + minStepsMathematical(16));
        
        System.out.println("Input: n=25, Expected: 10");
        System.out.println("Memoization Output: " + minSteps(25));
        System.out.println("Mathematical Output: " + minStepsMathematical(25));
    }
}
