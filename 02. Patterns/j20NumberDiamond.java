
/**
 * Problem Statement:
 *     Given an integer `n`, print a number diamond pattern where the first part of the diamond
 *     has numbers decreasing from `i` to `1` and then increasing from `1` to `i` in each row, where `i`
 *     is the row number. The second part of the diamond is a mirror image of the first part.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A number diamond pattern with `2n-1` rows, with the middle row containing numbers from `n` down to `1` 
 *       and then back up to `n`. The upper half forms the first part of the diamond and the lower half mirrors the upper part.
 * 
 * Example:
 * Input: 4
 * Output:
 *      1
 *     212
 *    32123
 *   4321234
 *    32123
 *     212
 *      1
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j20NumberDiamond {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from the user
        int n = in.nextInt();

        // Print the upper half of the number diamond
        for (int i = 1; i <= n; i++) {
            // Print leading spaces for the current row
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }

            // Print the decreasing sequence from i to 1
            for (int j = i; j >= 1; j--) {
                System.out.print(j);
            }

            // Print the increasing sequence from 2 to i
            for (int j = 2; j <= i; j++) {
                System.out.print(j);
            }

            // Move to the next line after printing the row
            System.out.println();
        }

        // Print the lower half of the number diamond (mirror image of the upper half)
        for (int i = n - 1; i >= 1; i--) {
            // Print leading spaces for the current row
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }

            // Print the decreasing sequence from i to 1
            for (int j = i; j >= 1; j--) {
                System.out.print(j);
            }

            // Print the increasing sequence from 2 to i
            for (int j = 2; j <= i; j++) {
                System.out.print(j);
            }

            // Move to the next line after printing the row
            System.out.println();
        }

        in.close();
    }
}
