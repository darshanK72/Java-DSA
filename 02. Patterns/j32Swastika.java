
/**
 * Problem Statement:
 *     Given an integer `n`, print a Swastika pattern made of asterisks `*`.
 *     The pattern should form a swastika shape with stars arranged in specific positions.
 *     The shape should have stars at the edges, a central vertical and horizontal line,
 *     and specific patterns for the upper and lower halves of the swastika.
 * 
 * Input:
 *     - A single integer `n` (n is odd).
 * 
 * Output:
 *     - A Swastika shape pattern formed with asterisks `*`.
 * 
 * Example:
 * Input: 5
 * Output:
 *    *   * * *
 *    *   *
 *    * * * * *
 *        *   *
 *    * * *   *
 * 
 * Constraints:
 *     - The input `n` is a positive odd integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j32Swastika {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Check if n is an odd number
        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        // Print the swastika pattern
        printSwastika(n);

        in.close();
    }

    // Method to print the swastika pattern
    public static void printSwastika(int n) {
        // Loop through each row
        for (int i = 1; i <= n; i++) {
            // Loop through each column in the row
            for (int j = 1; j <= n; j++) {
                // Print stars in the center row and column
                if (i == n / 2 + 1 || j == n / 2 + 1) {
                    System.out.print("* ");
                }
                // Upper half of the swastika (first part)
                else if (i <= n / 2) {
                    if (j == 1 || (i == 1 && j > n / 2)) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
                // Lower half of the swastika (second part)
                else {
                    if ((i == n && j <= n / 2) || j == n) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            // Move to the next line after each row
            System.out.println();
        }
    }
}
