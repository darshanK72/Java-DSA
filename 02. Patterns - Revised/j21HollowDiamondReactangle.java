
/**
 * Problem Statement:
 *     Given an integer `n`, print a hollow diamond-shaped rectangle pattern. The pattern has a
 *     width and height of `n` and follows these rules:
 *     - The first and last rows of the rectangle are fully filled with asterisks `*`.
 *     - The rows between the first and last rows have asterisks at both ends, with spaces in between.
 *     - The number of spaces in each row increases and decreases as you move towards the center of the rectangle.
 * 
 * Input:
 *     - A single integer `n` (n >= 1), where `n` is an odd number.
 * 
 * Output:
 *     - A hollow diamond-shaped rectangle pattern with `n` rows.
 * 
 * Example:
 * Input: 5
 * Output:
 *    * * * * *
 *    * *   * *
 *    *       * 
 *    * *   * *
 *    * * * * *
 * 
 * Input: 7
 * Output:
 *    * * * * * * *
 *    * * *   * * * 
 *    * *       * * 
 *    *           * 
 *    * *       * * 
 *    * * *   * * * 
 *    * * * * * * *
 * 
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j21HollowDiamondReactangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Ensure n is an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number: ");
            n = in.nextInt();
        }

        int st = n / 2; // Number of stars on either side
        int sp = 1; // Number of spaces in the middle

        // Loop through each row to print the pattern
        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == n) {
                // Print full row of stars for the first and last rows
                for (int j = 1; j <= n; j++) {
                    System.out.print("* ");
                }
            } else {
                // Print the first set of stars
                for (int j = 1; j <= st; j++) {
                    System.out.print("* ");
                }

                // Print spaces in the middle
                for (int j = 1; j <= sp; j++) {
                    System.out.print("  ");
                }

                // Print the second set of stars only if it's not the first row
                for (int j = 1; j <= st; j++) {
                    System.out.print("* ");
                }

                // Update the number of stars and spaces for the next row
                if (i <= n / 2) {
                    st--; // Decrease stars
                    sp += 2; // Increase spaces
                } else {
                    st++; // Increase stars
                    sp -= 2; // Decrease spaces
                }
            }
            // Move to the next row
            System.out.println();
        }

        in.close();
    }
}
