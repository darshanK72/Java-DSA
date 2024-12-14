
/**
 * Problem Statement:
 *     Given a positive odd integer `n`, print a horizontal hourglass pattern using stars (`*`). 
 *     The pattern should have the following characteristics:
 *         - The first and last rows have one `*` at both ends, with a certain number of spaces in between.
 *         - The rows in between gradually increase and decrease the number of stars symmetrically from the center.
 *         - The spaces between the stars decrease from the first to the middle row and then increase again symmetrically in the second half.
 *     If the input `n` is even, prompt the user to input an odd number.
 *
 * Input:
 *     - A single integer n (n >= 1).
 *     - If `n` is even, the program will prompt for an odd number.
 * 
 * Output:
 *     - A horizontal hourglass pattern formed by stars (`*`) and spaces (` `).
 * 
 * Example:
 *     Input: 5
 * Output:
 *     *         * 
 *     * *     * * 
 *     * * * * * * 
 *     * *     * * 
 *     *         * 
 * 
 * Input: 9
 * Output:
 *     *                 *
 *     * *             * *
 *     * * *         * * *
 *     * * * *     * * * *
 *     * * * * * * * * * *
 *     * * * *     * * * *
 *     * * *         * * *
 *     * *             * *
 *     *                 *
 * 
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j10HorizontalHourGlass {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the number n and ensure it's odd
        int n = in.nextInt();

        // If n is even, prompt the user for an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int st = 1; // Initial number of stars in the first row
        int sp = n - 1; // Initial number of spaces between stars

        // Loop through rows to print the hourglass pattern
        for (int i = 1; i <= n; i++) {
            // Print stars on the left
            for (int j = 1; j <= st; j++) {
                System.out.print("* ");
            }

            // Print spaces in the middle
            for (int j = 1; j <= sp; j++) {
                System.out.print("  ");
            }

            // Print stars on the right
            for (int j = 1; j <= st; j++) {
                System.out.print("* ");
            }

            // Adjust the number of stars and spaces for the next row
            if (i <= n / 2) {
                st++; // Increase stars in the upper half
                sp -= 2; // Decrease spaces in the upper half
            } else {
                st--; // Decrease stars in the lower half
                sp += 2; // Increase spaces in the lower half
            }

            // Move to the next line after each row
            System.out.println();
        }

        in.close();
    }
}
