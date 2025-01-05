
/**
 * Problem Statement:
 *     Given an integer `n`, print a "W" shape pattern made of asterisks `*`.
 *     The pattern should form a "W" with stars at the edges and a crossing star pattern in the middle of the shape.
 * 
 * Input:
 *     - A single integer `n` (n is odd).
 * 
 * Output:
 *     - A "W" shape pattern.
 * 
 * Example:
 * Input: 5
 * Output:
 *    *       *
 *    *       *
 *    *   *   *
 *    * *   * *
 *    *       *
 *
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j31W {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Check if n is an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        // Loop through each row to form the W shape
        for (int i = 1; i <= n; i++) {
            // Loop through each column in the row
            for (int j = 1; j <= n; j++) {
                // Print stars on the edges of the "W" shape
                if (j == 1 || j == n) {
                    System.out.print("* ");
                }
                // Print stars for the diagonal pattern in the middle part of the "W"
                else if (i > n / 2 && (i == j || i + j == n + 1)) {
                    System.out.print("* ");
                }
                // Otherwise, print spaces
                else {
                    System.out.print("  ");
                }
            }
            // Move to the next line after each row
            System.out.println();
        }

        in.close();
    }
}
