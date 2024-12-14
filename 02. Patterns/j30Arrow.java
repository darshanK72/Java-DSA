
/**
 * Problem Statement:
 *     Given an integer `n`, print an arrow pattern made of asterisks `*`.
 *     The pattern has an upper part with an increasing number of stars, followed by a middle row, and then a lower part where the number of stars decreases.
 * 
 * Input:
 *     - A single integer `n` (n is odd).
 * 
 * Output:
 *     - A pattern that forms an arrow shape.
 * 
 * Example:
 * Input: 5
 * Output:
 *       *
 *       * *
 *   * * * * *
 *       * *
 *       *
 *
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j30Arrow {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Check if n is an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int st = 1;

        // Loop through each row to form the arrow shape
        for (int i = 1; i <= n; i++) {
            // Print leading spaces for the arrow
            for (int j = 1; j <= n / 2; j++) {
                if (i != n / 2 + 1)
                    System.out.print("  ");
                else
                    System.out.print("* ");
            }

            // Print stars for the current row
            for (int j = 1; j <= st; j++) {
                System.out.print("* ");
            }

            // Adjust the number of stars for the next row
            if (i <= n / 2) {
                st++;
            } else {
                st--;
            }

            // Move to the next line after completing the row
            System.out.println();
        }

        in.close();
    }
}
