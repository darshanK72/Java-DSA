
/**
 * Problem Statement:
 *     Given a positive integer `n`, print a left-angled triangle pattern using asterisks ("*"). 
 *     The first row should contain one asterisk, the second row should contain two asterisks, 
 *     and so on, until the `n`th row, which contains `n` asterisks.
 * 
 * Input:
 *     - A single integer n (n >= 1).
 * 
 * Output:
 *     - A left-angled triangle pattern consisting of asterisks ("*").
 * 
 * Example:
 * Input: 5
 * Output:
 *     * 
 *     * * 
 *     * * * 
 *     * * * * 
 *     * * * * * 
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j03LeftRightAngledTriangleStar {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        // Print a left-angled triangle pattern with n rows
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* "); // Print stars in the current row
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close(); // Close the scanner to release resources
    }
}
