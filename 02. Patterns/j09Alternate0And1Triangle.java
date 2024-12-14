
/**
 * Problem Statement:
 *     Given a positive integer `n`, print a right-angled triangle pattern consisting of alternating `0`s and `1`s. 
 *     The first row should contain `1`, the second row should contain `0 1`, the third row should contain `1 0 1`, 
 *     and so on. The pattern alternates between `1` and `0`, and each row contains alternating numbers starting with `1` 
 *     for odd rows and starting with `0` for even rows.
 * 
 * Input:
 *     - A single integer n (n >= 1).
 * 
 * Output:
 *     - A right-angled triangle pattern with alternating `0`s and `1`s.
 * 
 * Example:
 * Input: 5
 * Output:
 *     1 
 *     0 1 
 *     1 0 1 
 *     0 1 0 1 
 *     1 0 1 0 1
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j09Alternate0And1Triangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        // Print a right-angled triangle pattern with alternating 0s and 1s
        for (int i = 1; i <= n; i++) {
            // Check if the current row number is odd or even
            if (i % 2 == 1) {
                // For odd rows, start with 1 and alternate
                for (int j = 1; j <= i; j++) {
                    System.out.print(j % 2 + " ");
                }
            } else {
                // For even rows, start with 0 and alternate
                for (int j = 1; j <= i; j++) {
                    System.out.print((j - 1) % 2 + " ");
                }
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close(); // Close the scanner to release resources
    }
}
