/**
 * Problem Statement:
 * 
 *     Given a number `n`, compute the factorial of `n`. The factorial of `n` is the product of all positive integers 
 *     from 1 to `n`, denoted as `n!`.
 * 
 * Input:
 *     - A single integer `n` (0 <= n <= 20).
 * 
 * Output:
 *     - Return the factorial of the given number `n`.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     120
 * 
 *     Explanation:
 *     The factorial of 5 is: 5 * 4 * 3 * 2 * 1 = 120.
 */

import java.util.Scanner;

public class j28Factorial {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        long n = in.nextLong(); // Input: the number for which factorial is to be calculated

        // Call and print the solution
        System.out.println("Factorial of " + n + " is " + fact(n));

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Iterative Solution
     * 
     * Intuition:
     * - We can compute the factorial iteratively by starting with a result of 1 and multiplying it by each number
     *   from 1 to `n`.
     * 
     * Time Complexity:
     * - O(n), because we are looping through numbers from 1 to `n`.
     * 
     * Space Complexity:
     * - O(1), as we are only using a fixed amount of space for the result.
     * 
     * @param n The number to compute the factorial of.
     * @return The factorial of the given number.
     */
    public static long factorial(long n) {
        long ans = 1;
        for (long i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }

    /**
     * Approach 2: Recursive Solution
     * 
     * Intuition:
     * - We can compute the factorial recursively by using the base case of `n! = 1` when `n = 0` or `n = 1`.
     * - For `n > 1`, the factorial is `n * factorial(n-1)`.
     * 
     * Time Complexity:
     * - O(n), because each recursive call makes one computation and decreases `n` by 1.
     * 
     * Space Complexity:
     * - O(n), due to the recursive function calls.
     * 
     * @param n The number to compute the factorial of.
     * @return The factorial of the given number.
     */
    public static long fact(long n) {
        if (n <= 1) {
            return 1;
        }
        return n * fact(n - 1);
    }
}
