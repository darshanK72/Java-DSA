
/**
 * Problem Statement:
 *     Given an integer `n`, print a pattern in the shape of the letter "V" using numbers.
 *     The first half of the V is a sequence of increasing numbers, followed by spaces,
 *     and then a sequence of decreasing numbers that mirror the increasing sequence.
 *     The width of the pattern decreases as we move towards the middle row and then increases again symmetrically.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A pattern that looks like the letter "V" with numbers.
 * 
 * Example:
 * Input: 4
 * Output:
 *    1      1
 *    12    21
 *    123  321
 *    12344321
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j28NumberV {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Loop through each row of the V pattern
        for (int i = 1; i <= n; i++) {
            // Print the increasing sequence of numbers
            int j;
            for (j = 1; j <= i; j++) {
                System.out.print(j);
            }

            // Print spaces in between
            for (int k = 1; k <= (2 * n - 2 * i); k++) {
                System.out.print(" ");
            }

            // Print the decreasing sequence of numbers
            j--; // Adjust the value of j to avoid printing the middle number twice
            for (; j >= 1; j--) {
                System.out.print(j);
            }

            // Move to the next line after completing one row
            System.out.println();
        }

        in.close();
    }
}
