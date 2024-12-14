
/**
 * Problem Statement:
 *     Given a positive integer `n`, print an inverted right-angled triangle pattern using asterisks ("*"). 
 *     The first row should contain `n` asterisks, the second row should contain `n-1` asterisks, 
 *     and so on, until the last row contains one asterisk.
 * 
 * Input:
 *     - A single integer n (n >= 1).
 * 
 * Output:
 *     - An inverted right-angled triangle pattern consisting of asterisks ("*").
 * 
 * Example:
 * Input: 5
 * Output:
 *     * * * * * 
 *     * * * * 
 *     * * * 
 *     * * 
 *     * 
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j04InvertedRightAngledTriangleStar {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        // Print an inverted right-angled triangle pattern
        for (int i = 1; i <= n; i++) {
            for (int j = n; j >= i; j--) {
                System.out.print("* "); // Print stars in the current row
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close(); // Close the scanner to release resources
    }
}
