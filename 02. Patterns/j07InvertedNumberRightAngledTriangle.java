
/**
 * Problem Statement:
 *     Given a positive integer `n`, print an inverted right-angled triangle pattern where the numbers 
 *     in each row start from 1 and increment by 1 up to a certain number, which decreases as you move down 
 *     to the next row. The first row should contain numbers from `1` to `n`, the second row should contain 
 *     numbers from `1` to `n-1`, and so on, until the last row contains only the number `1`.
 * 
 * Input:
 *     - A single integer n (n >= 1).
 * 
 * Output:
 *     - An inverted right-angled triangle pattern consisting of numbers starting from 1 in each row.
 * 
 * Example:
 * Input: 5
 * Output:
 *     1 2 3 4 5 
 *     1 2 3 4 
 *     1 2 3 
 *     1 2 
 *     1 
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 100).
 */

import java.util.Scanner;

public class j07InvertedNumberRightAngledTriangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // Read the input number

        // Print an inverted right-angled triangle pattern with numbers in each row
        for (int i = 1; i <= n; i++) {
            // Print numbers from 1 to n-i+1 in the current row
            for (int j = 1; j <= (n - i + 1); j++) {
                System.out.print(j + " ");
            }
            System.out.println(); // Move to the next line after printing a row
        }

        in.close(); // Close the scanner to release resources
    }
}
