
/**
 * Problem Statement:
 *     Given a positive integer `n`, calculate the sum of all integers from 1 to `n` (inclusive).
 * 
 * Input:
 *     - A single integer n (n >= 1).
 * 
 * Output:
 *     - The sum of all integers from 1 to `n`.
 * 
 * Example:
 * Input: 5
 * Output:
 *     15
 * 
 * Constraints:
 * - The input `n` is a positive integer (1 <= n <= 10^4).
 */

import java.util.Scanner;

public class j05SumOfNumbers {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the input number from user
        int n = in.nextInt();

        // Print the sum using recursion
        System.out.println("Sum using recursion: " + sumTillN(n));

        // Print the sum using the formula
        System.out.println("Sum using formula: " + sumTillNFormula(n));

        in.close(); // Close the scanner to release resources
    }

    /**
     * Recursive method to calculate the sum of numbers from 1 to n.
     * This method calls itself to reduce the problem size by 1 until it reaches the
     * base case (n == 1).
     *
     * The sum is calculated by adding `n` to the result of the recursive call for
     * `n-1`.
     *
     * Time Complexity: O(n) - The function makes `n` recursive calls.
     * Space Complexity: O(n) - Each recursive call adds a frame to the call stack.
     *
     * @param n The input number (upper bound of the sum).
     * @return The sum of all integers from 1 to n.
     */
    public static int sumTillN(int n) {
        if (n == 1)
            return 1; // Base case: if n is 1, return 1
        return n + sumTillN(n - 1); // Recursive call: add n to the sum of numbers from 1 to n-1
    }

    /**
     * sumTillN(3) -> 3 + sumTillN(2)
     * sumTillN(2) -> 2 + sumTillN(1)
     * sumTillN(1) -> 1 (base case)
     * So, sumTillN(3) = 3 + 2 + 1 = 6.
     */

    /**
     * Method to calculate the sum of numbers from 1 to n using the mathematical
     * formula.
     * The formula for the sum of the first `n` natural numbers is: n * (n + 1) / 2.
     *
     * Time Complexity: O(1) - The formula is evaluated in constant time.
     * Space Complexity: O(1) - No additional space is required for computation.
     *
     * @param n The input number (upper bound of the sum).
     * @return The sum of all integers from 1 to n.
     */
    public static int sumTillNFormula(int n) {
        return n * (n + 1) / 2; // Direct formula for the sum of numbers from 1 to n
    }
}
