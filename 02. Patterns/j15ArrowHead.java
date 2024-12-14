
/**
 * Problem Statement:
 *     Given an integer `n`, print an arrowhead pattern made of asterisks `*`. 
 *     The pattern consists of `n` rows, where `n` must be an odd number.
 *     The arrowhead pattern starts with a single star, and the number of stars increases as we go down until the middle 
 *     row, after which the number of stars decreases symmetrically.
 *
 * Input:
 *     - A single integer `n` (n >= 1 and n must be odd).
 * 
 * Output:
 *     - An arrowhead pattern with `n` rows, where the number of stars starts from 1 and increases up to the middle row 
 *       and then decreases symmetrically.
 *
 * Example:
 * Input: 5
 * Output:
 *     *
 *     * * 
 *     * * *
 *     * *
 *     *
 *
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 99).
 */

import java.util.Scanner;

public class j15ArrowHead {
    public static void main(String args[]) {
        // Create scanner object to take input from user
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // If n is not odd, prompt user for an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        // Initialize star count (st)
        int st = 1;

        // Loop to print the arrowhead pattern
        for (int i = 1; i <= n; i++) {
            // Print stars for each row
            for (int j = 1; j <= st; j++) {
                System.out.print("* ");
            }
            // Adjust star count for the next row
            if (i <= n / 2) {
                st++; // Increase stars in the upper half
            } else {
                st--; // Decrease stars in the lower half
            }
            // Move to the next line after printing each row
            System.out.println();
        }

        in.close();
    }
}
