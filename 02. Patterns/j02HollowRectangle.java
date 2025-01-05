
/**
 * Problem Statement:
 *    Given a positive integer `n`, print a hollow square pattern of size `n` x `n` using asterisks ("*"). 
 *    The outer border of the square should consist of asterisks, while the interior should be empty spaces.
 *    Specifically, the first and last rows, and the first and last columns should have asterisks, 
 *    and the interior should contain only spaces.
 * 
 * Input:
 *    - A single integer n (n >= 1).
 * 
 * Output:
 *    - A hollow square pattern of size `n` x `n` consisting of asterisks ("*") on the borders and spaces in the interior.
 * 
 * Example:
 * Input:5
 * Output:
 *    * * * * * 
 *    *       *
 *    *       *
 *    *       * 
 *    * * * * * 
 * 
 * Constraints:
 *    - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j02HollowRectangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        // Print a hollow square pattern of size n x n
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // Print asterisk on borders
                if (i == 1 || i == n || j == 1 || j == n) {
                    System.out.print("* ");
                } else {
                    // Print spaces for the interior
                    System.out.print("  ");
                }
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close();
    }
}
