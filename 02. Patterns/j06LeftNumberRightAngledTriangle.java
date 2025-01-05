
/**
 * Problem Statement:
 *     Given a positive integer `n`, print a right-angled triangle pattern where the numbers 
 *     in each row start from 1 and increment by 1 up to the row number. 
 *     The first row should contain the number `1`, the second row should contain `1 2`, 
 *     and so on, until the `n`th row which contains numbers from 1 to `n`.
 * 
 * Input:
 *     - A single integer n (n >= 1).
 * 
 * Output:
 *     - A right-angled triangle pattern consisting of numbers starting from 1 in each row.
 * 
 * Example:
 * Input: 5
 * Output:
 *     1 
 *     1 2 
 *     1 2 3 
 *     1 2 3 4 
 *     1 2 3 4 5 
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j06LeftNumberRightAngledTriangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        // Print a right-angled triangle pattern with numbers in each row
        for (int i = 1; i <= n; i++) {
            // Print numbers from 1 to i in the current row
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close(); // Close the scanner to release resources
    }
}
