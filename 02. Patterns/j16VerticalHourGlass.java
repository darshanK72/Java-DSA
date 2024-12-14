
/**
 * Problem Statement:
 *     Given an integer `n`, print a vertical hourglass pattern made of asterisks `*`.
 *     The pattern consists of `n` rows, where `n` must be an odd number. The hourglass pattern 
 *     starts with the maximum number of stars on the first row and gradually decreases as the rows go down 
 *     to the center, after which the number of stars increases symmetrically.
 * 
 * Input:
 *     - A single integer `n` (n >= 1 and n must be odd).
 * 
 * Output:
 *     - A vertical hourglass pattern with `n` rows, where the number of stars starts from `n` in the first row, 
 *       and decreases until reaching 1 star at the center, then increases symmetrically.
 * 
 * Example:
 * Input: 5
 * Output:
 *     * * * * *
 *       * * *
 *         *
 *       * * *
 *     * * * * *
 *
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 99).
 */

import java.util.Scanner;

public class j16VerticalHourGlass {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // If n is not odd, prompt user for an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        // Initialize the number of stars (st) and spaces (sp)
        int st = n;
        int sp = 0;

        // Loop to print the vertical hourglass pattern
        for (int i = 1; i <= n; i++) {
            // Print leading spaces before stars
            for (int j = 1; j <= sp; j++) {
                System.out.print("  ");
            }

            // Print stars for the current row
            for (int j = 1; j <= st; j++) {
                System.out.print("* ");
            }

            // Adjust spaces and stars for the next row
            if (i <= n / 2) {
                sp++; // Increase spaces in the upper half
                st -= 2; // Decrease stars in the upper half
            } else {
                sp--; // Decrease spaces in the lower half
                st += 2; // Increase stars in the lower half
            }

            // Move to the next line after printing each row
            System.out.println();
        }

        // Close the scanner to release resources
        in.close();
    }
}
