
/**
 * Problem Statement:
 *     Given an integer `n`, print a concentric number rectangle pattern.
 *     The pattern will have a size of `2n - 1` by `2n - 1`, where the outermost
 *     cells contain the number `n`, and each inner layer of cells decreases by 1 
 *     until reaching the center, which is 1.
 * 
 * Input:
 *     - A single integer `n` (1 <= n <= 50).
 * 
 * Output:
 *     - A concentric number rectangle pattern with dimensions `2n - 1` by `2n - 1`.
 * 
 * Example:
 * Input: 4
 * Output:
 *     4 4 4 4 4 4 4  
 *     4 3 3 3 3 3 4   
 *     4 3 2 2 2 3 4   
 *     4 3 2 1 2 3 4   
 *     4 3 2 2 2 3 4   
 *     4 3 3 3 3 3 4   
 *     4 4 4 4 4 4 4   
 * 
 * Constraints:
 *     - The input `n` is a positive integer (1 <= n <= 50).
 */

import java.util.Scanner;

public class j36ConcentricNumberRectangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the integer n from the user
        int n = in.nextInt();

        // Call the method to print the concentric number pattern
        printConcentricNumberPattern(n);

        in.close();
    }

    /**
     * Intuition for the pattern:
     * 
     * 1. The pattern is square-shaped and has a size of (2n-1) by (2n-1), meaning
     * it has n layers
     * where the outermost layer is the number `n` and the innermost is `1`.
     * 
     * 2. For every position in the grid, we calculate the number to be printed
     * based on its distance
     * from the edges (top, bottom, left, right). The value printed is the smallest
     * of these distances.
     * This is because the value of the numbers decreases as we move towards the
     * center.
     * 
     * 3. For example, in a 4x4 grid, the outermost cells are filled with `4`, the
     * next layer with `3`,
     * then `2`, and the center would be filled with `1`. This is the essence of the
     * concentric layers.
     * 
     * 4. The pattern for a number `n` will always be symmetric, both horizontally
     * and vertically.
     * 
     * 5. To compute the number for a given cell (i, j), the formula is:
     * number = n - min(i, j, 2n - i - 1, 2n - j - 1)
     * This finds the minimum distance of the current cell from any edge (top,
     * bottom, left, right).
     */

    // Method to print the concentric number pattern
    public static void printConcentricNumberPattern(int n) {
        // Calculate the size of the pattern (2n-1 x 2n-1)
        int n2 = n * 2 - 1;

        // Loop through each row
        for (int i = 0; i < n2; i++) {
            // Loop through each column in the row
            for (int j = 0; j < n2; j++) {
                // Calculate the number to be printed based on the current row and column
                int num = n - Math.min(Math.min(i, j), Math.min(n2 - i - 1, n2 - j - 1));
                System.out.print(num + " ");
            }
            // Move to the next line after printing each row
            System.out.println();
        }
    }
}
