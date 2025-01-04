
/**
 * Problem Statement:
 *     Given a positive integer `n`, print a right-angled triangle pattern aligned to the right 
 *     using asterisks ("*"). The first row should contain one asterisk, the second row should contain 
 *     two asterisks, and so on, until the `n`th row. The asterisks should be right-aligned with spaces.
 * 
 * Input:
 *     - A single integer n (n >= 1).
 * 
 * Output:
 *     - A right-angled triangle pattern aligned to the right consisting of asterisks ("*").
 * 
 * Example:
 * Input: 5
 * Output:
 *            * 
 *          * * 
 *        * * * 
 *      * * * * 
 *    * * * * *
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j05RightStarRightAngledTriangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        // Print a right-angled triangle pattern aligned to the right
        for (int i = 1; i <= n; i++) {
            // Print spaces for right alignment
            for (int j = 1; j <= n - i; j++) {
                System.out.print("  ");
            }
            // Print stars in the current row
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close(); // Close the scanner to release resources
    }
}
