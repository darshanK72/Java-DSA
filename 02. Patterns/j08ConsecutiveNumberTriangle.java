
/**
 * Problem Statement:
 *     Given a positive integer `n`, print a right-angled triangle pattern with consecutive numbers. 
 *     The first row should contain the number `1`, the second row should contain the next two consecutive numbers, 
 *     the third row should contain the next three consecutive numbers, and so on until the `n`th row.
 * 
 * Input:
 *     - A single integer n (n >= 1).
 * 
 * Output:
 *     - A right-angled triangle pattern consisting of consecutive numbers.
 * 
 * Example:
 * Input: 5
 * Output:
 *     1 
 *     2 3 
 *     4 5 6 
 *     7 8 9 10 
 *     11 12 13 14 15
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j08ConsecutiveNumberTriangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        int x = 1; // Variable to keep track of consecutive numbers
        // Print a right-angled triangle pattern with consecutive numbers
        for (int i = 1; i <= n; i++) {
            // Print consecutive numbers in each row
            for (int j = 1; j <= i; j++) {
                System.out.print(x + " ");
                x += 1; // Increment x for the next number
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close(); // Close the scanner to release resources
    }
}
