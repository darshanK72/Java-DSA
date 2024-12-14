
/**
 * Problem Statement:
 *     Given a positive integer `n`, print a square pattern of `n` rows and `n` columns. 
 *     Each row contains `n` asterisks ("*") separated by spaces.
 * 
 * Input:
 *    - A single integer n (n >= 1).
 * 
 * Output:
 *    - A square pattern of size `n` x `n` consisting of asterisks ("*").
 * 
 * Example:
 * Input: 5
 * Output:
 *      * * * * * 
 *      * * * * * 
 *      * * * * * 
 *      * * * * * 
 *      * * * * *
 * 
 * Constraints:
 *      - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j01Rectangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        // Print a square pattern of size n x n
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* "); // Print an asterisk with a space
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close();
    }
}
