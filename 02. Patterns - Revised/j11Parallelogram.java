
/**
 * Problem Statement:
 *     Given an integer `n`, print a parallelogram pattern using stars (`*`). The pattern should
 *     consist of `n` rows and `n` stars in each row. Each row should be indented with a number of 
 *     spaces equal to the row's position, forming a parallelogram shape.
 *
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A parallelogram pattern formed by stars (`*`) and spaces (` `).
 *
 * Example:
 * Input: 5
 * Output:
 *             * * * * * 
 *           * * * * * 
 *         * * * * *
 *       * * * * *
 *     * * * * *
 *
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j11Parallelogram {
    public static void main(String args[]) {
        // Create scanner object for user input
        Scanner in = new Scanner(System.in);

        // Read the integer n from the user
        int n = in.nextInt();

        // Outer loop to print n rows
        for (int i = 1; i <= n; i++) {
            // Inner loop to print spaces before stars (this creates the indentation for the
            // parallelogram)
            for (int j = 1; j <= (n - i); j++) {
                System.out.print("  "); // Print space to create indentation
            }

            // Inner loop to print n stars in each row
            for (int j = 1; j <= n; j++) {
                System.out.print("* "); // Print star with a space between them
            }

            // Move to the next line after printing stars for the current row
            System.out.println();
        }

        in.close();
    }
}
