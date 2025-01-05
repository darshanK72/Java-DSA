/**
 * Problem Statement:
 *     Given an integer `n`, print each digit of the number in reverse order, each on a new line.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9), representing the number whose digits are to be printed in reverse order.
 * 
 * Output:
 *     - Each digit of the input number printed in reverse order, one per line.
 * 
 * Example:
 *     Input:
 *         n = 12345
 *     Output:
 *         5
 *         4
 *         3
 *         2
 *         1
 * 
 *     Explanation:
 *         The input number `12345` is decomposed into its digits and printed in reverse order.
 */

import java.util.Scanner;

public class j03PrintDigitsReverse {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Printing each digit of the number in reverse order
        printDigitsReverse(n);

        // Closing the scanner
        in.close();
    }

    /**
     * Approach: Iterative Calculation
     * 
     * Intuition:
     * - Use modulo operation to extract the last digit of the number and division to
     *   remove the last digit, iterating until the number becomes zero.
     * 
     * Steps:
     * - Use `n % 10` to extract the last digit of the number.
     * - Print the extracted digit.
     * - Use `n /= 10` to remove the last digit and continue.
     * 
     * Time Complexity:
     * - O(log10(n)), as the process involves iterating through the digits of the number.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space.
     * 
     * @param n The input number.
     */
    public static void printDigitsReverse(int n) {
        while (n > 0) {
            int d = n % 10; // Extract the last digit
            System.out.println(d); // Print the last digit
            n /= 10; // Remove the last digit
        }
    }
}
