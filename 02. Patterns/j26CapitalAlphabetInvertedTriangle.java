
/**
 * Problem Statement:
 *     Given an integer `n`, print an inverted triangle pattern with capital alphabet letters.
 *     The first row starts with the letter corresponding to `n`, and each subsequent row 
 *     contains one letter less, until it reaches 'A'.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A pattern of capital alphabet letters forming an inverted triangle.
 * 
 * Example:
 * Input: 5
 * Output:
 *    E D C B A
 *    D C B A
 *    C B A
 *    B A
 *    A
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 26).
 */

import java.util.Scanner;

public class j26CapitalAlphabetInvertedTriangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Loop through each row of the inverted triangle
        for (int i = 1; i <= n; i++) {
            // Loop through each letter in the current row
            for (int j = (n - i + 1); j >= 1; j--) {
                // Print the corresponding capital alphabet (A = 65 in ASCII)
                System.out.print((char) (64 + j) + " ");
            }
            // Move to the next line after printing one row
            System.out.println();
        }

        in.close();
    }
}
