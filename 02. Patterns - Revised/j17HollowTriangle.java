
/**
 * Problem Statement:
 *     Given an integer `n`, print a hollow right-angled triangle pattern made of asterisks `*`.
 *     The triangle has `n` rows, where the first and last rows are completely filled with stars.
 *     The rows between the first and last have stars at the beginning and end, with spaces in between.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A hollow triangle pattern of height `n` and base `2n-1`.
 * 
 * Example:
 * Input: 5
 * Output:
 *       *
 *      * *
 *     *   *
 *    *     *
 *   *********
 *
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j17HollowTriangle {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Loop through each row of the hollow triangle, excluding the last row
        for (int i = 1; i <= n - 1; i++) {
            // Print leading spaces before the first star
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }

            // Print the first star
            System.out.print("*");

            // Print spaces between the stars
            for (int j = 3; j <= 2 * i - 1; j++) {
                System.out.print(" ");
            }

            // Print the second star only if it's not the first row
            if (i > 1)
                System.out.print("*");

            // Move to the next line after printing the row
            System.out.println();
        }

        // Print the last row (base of the triangle) filled with stars
        for (int i = 1; i <= n * 2 - 1; i++) {
            System.out.print("*");
        }

        in.close();
    }
}
