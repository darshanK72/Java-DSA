
/**
 * Problem Statement:
 *     Given an integer `n`, print a diagonal grid of numbers.
 *     The grid should have `n` rows and `n` columns, and each element of the grid
 *     at position `(i, j)` should be the difference of `i` and `j`, where `i` is the row index
 *     and `j` is the column index.
 * 
 * Input:
 *     - A single integer `n` (n is positive).
 * 
 * Output:
 *     - A grid of numbers with diagonal differences.
 * 
 * Example:
 * Input: 5
 * Output:
 *    0 1 2 3 4
 *    1 2 3 4 5
 *    2 3 4 5 6
 *    3 4 5 6 7
 *    4 5 6 7 8
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */
import java.util.Scanner;

public class j34DiagonalGrid2 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from user
        int n = in.nextInt();

        // Loop through each row
        for (int i = 0; i < n; i++) {
            // Loop through each column in the row
            for (int j = 0; j < n; j++) {
                // Print the difference between row index i and column index j
                System.out.print((i - j) + " ");
            }
            // Move to the next line after each row
            System.out.println();
        }

        in.close();
    }
}
