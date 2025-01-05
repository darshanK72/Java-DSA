
/**
 * Problem Statement:
 *     Given an integer `n`, print the right diagonal of a square matrix of size `n`. The matrix should have `*` on the right diagonal 
 *     (where the sum of row index and column index equals `n + 1`) and spaces elsewhere.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A pattern of size `n` with `*` marks along the right diagonal and spaces elsewhere.
 * 
 * Example:
 * Input: 5
 * Output:
 *                *
 *              *
 *            *
 *          *
 *        *
 *      *
 *    *
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j24RightDiagonal {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Loop through rows
        for (int i = 1; i <= n; i++) {
            // Loop through columns
            for (int j = 1; j <= n; j++) {
                // Print '*' on the right diagonal (where i + j = n + 1)
                if (i + j == n + 1) {
                    System.out.print("* ");
                } else {
                    // Print space elsewhere
                    System.out.print("  ");
                }
            }
            // Move to the next line after completing one row
            System.out.println();
        }

        in.close();
    }
}
