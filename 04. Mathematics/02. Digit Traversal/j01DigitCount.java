/**
 * Problem Statement:
 *     Given an integer `n`, find the number of digits in the number.
 *     The input number will be a positive integer.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9), representing the number for which we need to count the digits.
 * 
 * Output:
 *     - An integer representing the number of digits in the given number.
 * 
 * Example:
 *     Input:
 *         n = 12345
 *     Output:
 *         Number of Digits in 12345 are 5
 * 
 *     Explanation:
 *         The input number `12345` has 5 digits.
 */

import java.util.Scanner;

public class j01DigitCount {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Approach 1: Iterative Count
        System.out.println("Number of Digits in " + n + " are " + digitCount(n));

        // Approach 2: Logarithmic Count
        System.out.println("Number of Digits in " + n + " are " + digitCountLog(n));

        // Closing the scanner
        in.close();
    }

    /**
     * Approach 1: Iterative Counting
     * 
     * Intuition:
     * - Traverse through the digits of the number by continuously dividing it by 10,
     *   and count the iterations required to reduce the number to 0.
     * 
     * Time Complexity:
     * - O(d), where `d` is the number of digits in the input number.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space.
     * 
     * @param n The input number.
     * @return The number of digits in the number.
     */
    public static int digitCount(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    /**
     * Approach 2: Logarithmic Calculation
     * 
     * Intuition:
     * - The number of digits in a number `n` can be calculated using the formula:
     * `floor(log10(n)) + 1`. This works because the logarithm base 10 measures
     * the number of powers of 10 that fit into the number.
     * 
     * Time Complexity:
     * - O(1), as it is a single mathematical calculation.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space.
     * 
     * @param n The input number.
     * @return The number of digits in the number.
     */
    public static int digitCountLog(int n) {
        return (int) (Math.floor(Math.log10(n)) + 1);
    }
}
