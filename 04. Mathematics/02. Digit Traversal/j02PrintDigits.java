/**
 * Problem Statement:
 *     Given an integer `n`, print each digit of the number on a new line.
 *     The digits should be printed in the same order as they appear in the number.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9), representing the number whose digits are to be printed.
 * 
 * Output:
 *     - Each digit of the input number printed on a new line.
 * 
 * Example:
 *     Input:
 *         n = 12345
 *     Output:
 *         1
 *         2
 *         3
 *         4
 *         5
 * 
 *     Explanation:
 *         The input number `12345` is decomposed into its digits, which are printed line by line.
 */

import java.util.Scanner;

public class j02PrintDigits {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Printing each digit of the number
        printDigits(n);

        // Closing the scanner
        in.close();
    }

    /**
     * Approach: Logarithmic Calculation
     * 
     * Intuition:
     * - Calculate the number of digits in the number using logarithms, then use
     *   powers of 10 to extract and print each digit sequentially.
     * 
     * Steps:
     * - Determine the total number of digits `l` in the number using `floor(log10(n)) + 1`.
     * - Use powers of 10 to isolate each digit from most significant to least significant.
     * - Print each digit on a new line.
     * 
     * Time Complexity:
     * - O(log10(n)), as the process involves iterating through the digits of the number.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space.
     * 
     * @param n The input number.
     */
    public static void printDigits(int n) {
        int l = (int) (Math.floor(Math.log10(n)) + 1); // Number of digits
        int div = (int) Math.pow(10, l - 1); // Divisor to isolate the most significant digit

        while (div > 0) {
            int d = n / div; // Extract the most significant digit
            System.out.println(d);
            n = n % div; // Remove the most significant digit
            div /= 10; // Move to the next digit
        }
    }
}
