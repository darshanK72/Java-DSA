
/**
 * Problem Statement:
 *     Given an integer `n`, print a diamond-shaped pattern made of asterisks `*`. 
 *     The pattern consists of `n` rows, where `n` must be an odd number.
 *     The diamond should be symmetric, with a peak in the center. Each row `i` contains a series of stars with spaces 
 *     before them for alignment, and the number of stars increases up to the center and then decreases symmetrically.
 *
 * Input:
 *     - A single integer `n` (n >= 1 and n must be odd).
 * 
 * Output:
 *     - A diamond pattern with `n` rows, where the number of stars starts from 1 and increases up to the middle row 
 *       and then decreases symmetrically. The stars are right-aligned with spaces for symmetry.
 *
 * Example:
 * Input: 5
 * Output:
 *        *
 *      * * *
 *    * * * * *
 *      * * *
 *        * 
 *
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 99).
 */

import java.util.Scanner;

public class j14Diamond {
    public static void main(String args[]) {
        // Create scanner object to take input from user
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // If n is not odd, prompt user for an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number: ");
            n = in.nextInt();
        }

        // Initialize space count (sp) and star count (st)
        int sp = (n / 2);
        int st = 1;

        // Loop to print the upper part of the diamond (including the middle row)
        for (int i = 1; i <= n; i++) {
            // Print spaces for alignment
            for (int j = 1; j <= sp; j++) {
                System.out.print("  ");
            }
            // Print stars
            for (int j = 1; j <= st; j++) {
                System.out.print("* ");
            }
            // Adjust space and star counts for the next row
            if (i <= n / 2) {
                sp--; // Decrease space for upper half
                st += 2; // Increase stars for upper half
            } else {
                sp++; // Increase space for lower half
                st -= 2; // Decrease stars for lower half
            }
            // Move to the next line after printing each row
            System.out.println();
        }

        in.close();
    }
}
