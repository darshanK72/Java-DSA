
/**
 * Problem Statement:
 *     Given an integer `n`, print a cross pattern made of asterisks `*`. The cross consists of two diagonals:
 *     - The first diagonal runs from the top-left to the bottom-right.
 *     - The second diagonal runs from the top-right to the bottom-left.
 *     All positions where these diagonals intersect should have a `*`.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A pattern of size `n` with `*` marks along the two diagonals forming a cross.
 * 
 * Example:
 * Input: 5
 * Output:
 *    *       *
 *      *   *
 *        *
 *      *   *
 *    *       *
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j25DiagonalCross {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Loop through rows
        for (int i = 1; i <= n; i++) {
            // Loop through columns
            for (int j = 1; j <= n; j++) {
                // Print '*' on the diagonals (either i + j = n + 1 or i = j)
                if (i + j == n + 1 || i == j) {
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
