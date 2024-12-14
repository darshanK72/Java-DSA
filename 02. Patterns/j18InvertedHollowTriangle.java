
/**
 * Problem Statement:
 *     Given an integer `n`, print an inverted hollow triangle pattern made of asterisks `*`.
 *     The pattern consists of `2n - 1` stars in the first row. In subsequent rows, stars are placed
 *     at the beginning and end, with spaces between them. The last row has a single star.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - An inverted hollow triangle pattern with `n` rows.
 * 
 * Example:
 * Input: 5
 * Output:
 *    *********
 *     *     *
 *      *   *
 *       * *
 *        *
 *
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j18InvertedHollowTriangle {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Print the first row (completely filled with stars)
        for (int i = 1; i <= 2 * n - 1; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Loop through each row starting from n-1 down to 1
        for (int i = n - 1; i >= 1; i--) {
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

            // Print the second star only if it's not the last row
            if (i > 1)
                System.out.print("*");

            // Move to the next line after printing the row
            System.out.println();
        }

        in.close();
    }
}
