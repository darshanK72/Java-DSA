
/**
 * Problem Statement:
 *     Given an integer `n`, print a triangle where each row contains Fibonacci numbers.
 *     The first row has 1 Fibonacci number, the second row has 2 Fibonacci numbers, and so on.
 * 
 * Input:
 *     - A single integer `n` (n >= 1).
 * 
 * Output:
 *     - A triangle of Fibonacci numbers, with each row containing increasing Fibonacci numbers.
 * 
 * Example:
 * Input: 5
 * Output:
 *    0 
 *    1 1 
 *    2 3 5 
 *    8 13 21 34 
 *    55 89 144 233 377
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j29FibonacciTriangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Initialize the first two Fibonacci numbers
        int a = 0;
        int b = 1;
        int c = a + b;

        // Loop to print each row of the triangle
        for (int i = 1; i <= n; i++) {
            // For each row, print Fibonacci numbers
            for (int j = 1; j <= i; j++) {
                // Print the current Fibonacci number
                System.out.print(a + " ");
                // Update Fibonacci numbers for the next iteration
                a = b;
                b = c;
                c = a + b;
            }
            // Move to the next line after printing a row
            System.out.println();
        }

        in.close();
    }
}
