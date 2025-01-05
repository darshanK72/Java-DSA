
/**
 * Problem Statement:
 *     Given an integer `n`, print a hollow diamond pattern made of asterisks `*`.
 *     The pattern consists of `n` rows where the first row has one star, 
 *     the middle rows have stars at the beginning and end with spaces in between, 
 *     and the last row has one star at the center. The upper half of the diamond 
 *     is symmetrical to the lower half.
 * 
 * Input:
 *     - A single integer `n` (n >= 1 and n must be an odd number).
 * 
 * Output:
 *     - A hollow diamond pattern with `n` rows.
 * 
 * Example:
 * Input: 5
 * Output:
 *     *
 *    * *
 *   *   *
 *    * *
 *     *
 *
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j19HollowDiamond {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Ensure n is odd
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number: ");
            n = in.nextInt();
        }

        // Initialize spaces for the upper half of the diamond
        int sp1 = n / 2;
        int sp2 = -1;

        // Loop through each row of the diamond
        for (int i = 1; i <= n; i++) {
            // Print leading spaces for the current row
            for (int j = 1; j <= sp1; j++) {
                System.out.print("  ");
            }
            // Print the first star
            System.out.print("* ");
            // Print the spaces between the stars
            for (int j = 1; j <= sp2; j++) {
                System.out.print("  ");
            }
            // Print the second star if it's not the first or last row
            if (i != 1 && i != n) {
                System.out.print("* ");
            }

            // Update spaces for the next row (depending on whether we're in the upper or
            // lower half)
            if (i <= n / 2) {
                sp1--;
                sp2 += 2;
            } else {
                sp1++;
                sp2 -= 2;
            }

            // Move to the next line after printing the row
            System.out.println();
        }

        in.close();
    }
}
