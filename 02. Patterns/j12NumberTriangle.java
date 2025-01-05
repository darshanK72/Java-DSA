
/**
 * Problem Statement:
 *     Given an integer `n`, print a number triangle pattern. The triangle should have `n` rows.
 *     Each row `i` (where `i` is the row number) should contain the number `i` repeated `i` times, 
 *     and the numbers should be aligned such that the triangle has right-aligned rows with appropriate spaces.
 *
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A number triangle pattern with `n` rows, where each row `i` contains the number `i`
 *       repeated `i` times, right-aligned with spaces.
 *
 * Example:
 * Input: 5
 * Output:
 *        1 
 *       2 2
 *      3 3 3
 *     4 4 4 4
 *    5 5 5 5 5
 *
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j12NumberTriangle {
    public static void main(String args[]) {
        // Create scanner object for user input
        Scanner in = new Scanner(System.in);

        // Read the integer n from the user
        int n = in.nextInt();

        // Outer loop to print n rows
        for (int i = 1; i <= n; i++) {
            // Inner loop to print spaces before the numbers (for right-alignment)
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" "); // Print space for alignment
            }

            // Inner loop to print the number i, repeated i times
            for (int j = 1; j <= i; j++) {
                System.out.print(i + " "); // Print the current number with space
            }

            // Move to the next line after printing numbers for the current row
            System.out.println();
        }

        // Close the scanner
        in.close();
    }
}
