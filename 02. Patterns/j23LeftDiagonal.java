
/**
 * Problem Statement:
 *     Given an integer `n`, print the left diagonal of a square matrix of size `n`. The matrix should have `*` on the left diagonal 
 *     (where row index equals column index) and spaces elsewhere.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A pattern of size `n` with `*` marks along the left diagonal and spaces elsewhere.
 * 
 * Example:
 * Input: 5
 * Output:
 *    *         
 *      *       
 *        *     
 *          *   
 *            * 
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j23LeftDiagonal {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Loop through rows
        for (int i = 1; i <= n; i++) {
            // Loop through columns
            for (int j = 1; j <= n; j++) {
                // Print '*' on the left diagonal (where row index equals column index)
                if (i == j) {
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
