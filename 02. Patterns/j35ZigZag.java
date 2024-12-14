
/**
 * Problem Statement:
 *     Given two integers `height` and `length`, print a ZigZag pattern of stars.
 *     The pattern should be `height` rows high and `length` columns wide. 
 *     The pattern should be shaped like a ZigZag with alternating upward and downward stars.
 * 
 * Input:
 *     - Two integers `height` and `length` where both are positive integers.
 * 
 * Output:
 *     - A ZigZag pattern of stars (`*`), with the specified number of rows and columns.
 * 
 * Example:
 * Input: 
 *     height = 5
 *     length = 5
 * Output:
 *         *               *               *               *               *
 *       *   *           *   *           *   *           *   *           *   *
 *     *       *       *       *       *       *       *       *       *       *
 *   *           *   *           *   *           *   *           *   *           *   
 * *               *               *               *               *               *
 * 
 * Constraints:
 *     - The input `height` and `length` are positive integers (1 <= height, length <= 50).
 */

import java.util.Scanner;

public class j35ZigZag {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the height and length of the ZigZag from the user
        int height = in.nextInt();
        int length = in.nextInt();

        // Call the method to print the ZigZag pattern
        printZigZag(height, length);

        in.close();
    }

    // Method to print the ZigZag pattern
    public static void printZigZag(int n, int l) {
        // Variables for space management
        int sp1 = n - 1; // Space before the first star in each row
        int sp2 = -1; // Space between two stars within the same row
        int sp3 = (n - 1) * 2 - 1; // Space between the groups of stars

        // Loop to handle the height of the ZigZag
        for (int i = 1; i <= n; i++) {
            // Loop to print leading spaces before stars in the row
            for (int j = 1; j <= sp1; j++) {
                System.out.print("  ");
            }

            // Loop to print stars in the ZigZag pattern
            for (int k = 1; k <= l; k++) {
                System.out.print("* ");

                // Loop to print space between two stars in the same row
                for (int j = 1; j <= sp2; j++) {
                    System.out.print("  ");
                }

                // Print stars for middle rows (except the first and last rows)
                if (i > 1 && i < n) {
                    System.out.print("* ");
                }

                // Loop to print space between two groups of stars
                for (int j = 1; j <= sp3; j++) {
                    System.out.print("  ");
                }

                // Print star in the last column for the last row
                if (k == l && i == n) {
                    System.out.print("* ");
                }
            }

            // Update spaces for next row
            sp1--; // Reduce leading spaces
            sp2 += 2; // Increase space between stars within a row
            sp3 -= 2; // Decrease space between two groups of stars

            // Move to the next line after each row
            System.out.println();
        }
    }
}
