
/**
 * Problem Statement:
 *     Given an integer `n`, print a triangle pattern using the letters of the alphabet.
 *     The pattern alternates between lowercase and uppercase letters in each row.
 *     The letters start from 'a' and continue alternately with 'B', 'c', 'D', etc., 
 *     where lowercase letters appear at even positions and uppercase letters appear at odd positions.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A triangle pattern made of alternating uppercase and lowercase alphabet letters.
 * 
 * Example:
 * Input: 5
 * Output:
 *    a
 *    B c
 *    D e F
 *    g H i J
 *    k L m N o
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 26).
 */

import java.util.Scanner;

public class j27AlternateAlphabetTriangle {
    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Variable to keep track of the character index
        int ch = 0;

        // Loop through each row of the triangle
        for (int i = 1; i <= n; i++) {
            // Loop through each letter in the current row
            for (int j = 1; j <= i; j++) {
                // Alternate between lowercase and uppercase letters
                if (ch % 2 == 0) {
                    System.out.print((char) (97 + ch) + " "); // Lowercase letter
                } else {
                    System.out.print((char) (65 + ch) + " "); // Uppercase letter
                }
                ch++; // Move to the next letter
            }
            // Move to the next line after printing one row
            System.out.println();
        }

        in.close();
    }
}
