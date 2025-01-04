
/**
 * Problem Statement:
 *     Given an integer `n`, print a hollow diamond-shaped rectangle pattern. The pattern follows these rules:
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
 *    * * *   * * * 
 *    * *       * * 
 *    *           * 
 *    * *       * * 
 *    * * *   * * * 
 * 
 * Input: 7
 * Output:
 *    * * * *   * * * * 
 *    * * *       * * * 
 *    * *           * * 
 *    *               * 
 *    * *           * * 
 *    * * *       * * * 
 *    * * * *   * * * * 
 * 
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j22HollowDiamondRactangle2 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Ensure n is an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int st = n / 2 + 1; // Initial number of stars on each side
        int sp = 1; // Initial spaces between stars

        // Loop through each row to print the pattern
        for (int i = 1; i <= n; i++) {

            // Print the first set of stars
            for (int j = 1; j <= st; j++) {
                System.out.print("* ");
            }

            // Print spaces in the middle
            for (int j = 1; j <= sp; j++) {
                System.out.print("  ");
            }

            // Print the second set of stars
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

            // Move to the next line
            System.out.println();
        }

        in.close();
    }
}
