
/**
 * Problem Statement:
 *     Given an integer `n`, print a palindrome number triangle. The triangle should have `n` rows.
 *     Each row `i` (where `i` is the row number) should contain numbers in a descending order from `i` to `1` and then ascending back from `2` to `i`.
 *     The numbers should be arranged such that the triangle has right-aligned rows with appropriate spaces.
 *
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A palindrome number triangle with `n` rows, where each row `i` contains the numbers 
 *       from `i` down to `1`, and then back up from `2` to `i`, right-aligned with spaces.
 *
 * Example:
 * Input: 5
 * Output:
 *            1
 *          2 1 2
 *        3 2 1 2 3
 *      4 3 2 1 2 3 4
 *    5 4 3 2 1 2 3 4 5
 *
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j13NumberPalindromeTriangle {
    public static void main(String args[]) {
        // Create scanner object for user input
        Scanner in = new Scanner(System.in);

        // Read the integer n from the user
        int n = in.nextInt();

        // Outer loop to print n rows
        for (int i = 1; i <= n; i++) {
            // Inner loop to print spaces before the numbers (for right-alignment)
            for (int j = 1; j <= (n - i); j++) {
                System.out.print("  "); // Print space for alignment
            }

            // Inner loop to print numbers in descending order from i to 1
            for (int j = i; j >= 1; j--) {
                System.out.print(j + " "); // Print descending numbers
            }

            // Inner loop to print numbers in ascending order from 2 to i
            for (int j = 2; j <= i; j++) {
                System.out.print(j + " "); // Print ascending numbers
            }

            // Move to the next line after printing numbers for the current row
            System.out.println();
        }

        // Close the scanner
        in.close();
    }
}
