import java.util.Arrays;
import java.util.Scanner;

public class j02SpiralMatrixII {

    public static void main(String args[]) {
        // Scanner to read the input value for n
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the matrix (n x n)

        // Generate the spiral matrix
        int[][] mat = generateSpiralMatrix1(n);

        // Print the generated spiral matrix
        for (int[] arr : mat) {
            System.out.println(Arrays.toString(arr)); // Print each row of the matrix
        }

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Generate Spiral Matrix Iteratively
     * 
     * Intuition:
     * - Start by defining the boundaries of the matrix: rowStart, rowEnd, colStart, and colEnd.
     * - Traverse the matrix in a spiral order, starting from the top-left corner and move outward in a spiral pattern.
     * - Fill in the matrix with consecutive numbers starting from 1.
     * - The loop continues until all positions in the matrix are filled.
     * 
     * Time Complexity:
     * - O(n^2), where n is the size of the matrix. We fill every position of the matrix once.
     * 
     * Space Complexity:
     * - O(n^2), because we store the matrix of size n x n.
     * 
     * @param n The size of the matrix (n x n).
     * @return A matrix filled with numbers in spiral order.
     */
    public static int[][] generateSpiralMatrix1(int n) {
        // Initialize boundary pointers
        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;

        // Create an empty n x n matrix
        int[][] out = new int[n][n];
        int k = 1; // Start filling from 1

        // Start filling the matrix in a spiral order
        while (true) {
            // Traverse from left to right along the top row
            for (int i = colStart; i <= colEnd; i++) {
                out[rowStart][i] = k;
                k++;
            }
            rowStart++; // Move the row start down
            if (rowStart > rowEnd)
                break; // Check if the top row has been filled

            // Traverse from top to bottom along the right column
            for (int j = rowStart; j <= rowEnd; j++) {
                out[j][colEnd] = k;
                k++;
            }
            colEnd--; // Move the column end left
            if (colStart > colEnd)
                break; // Check if the right column has been filled

            // Traverse from right to left along the bottom row
            for (int i = colEnd; i >= colStart; i--) {
                out[rowEnd][i] = k;
                k++;
            }
            rowEnd--; // Move the row end up
            if (rowStart > rowEnd)
                break; // Check if the bottom row has been filled

            // Traverse from bottom to top along the left column
            for (int j = rowEnd; j >= rowStart; j--) {
                out[j][colStart] = k;
                k++;
            }
            colStart++; // Move the column start right
            if (colStart > colEnd)
                break; // Check if the left column has been filled
        }

        return out; // Return the filled spiral matrix
    }

    /**
     * Approach 2: Generate Spiral Matrix Iteratively (Optimized)
     * 
     * Intuition:
     * - This approach is similar to the first one, but with explicit checks for the boundaries during the traversal.
     * - The matrix is filled in spiral order, and the boundary checks are simplified by directly checking whether rowStart <= rowEnd and colStart <= colEnd before each loop.
     * - The result is the same as the first approach but with a slightly optimized loop structure.
     * 
     * Time Complexity:
     * - O(n^2), where n is the size of the matrix. We still fill every position in the matrix.
     * 
     * Space Complexity:
     * - O(n^2), for the matrix of size n x n.
     * 
     * @param n The size of the matrix (n x n).
     * @return A matrix filled with numbers in spiral order.
     */
    public static int[][] generateMatrix2(int n) {
        // Initialize boundary pointers
        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;

        // Create an empty n x n matrix
        int[][] out = new int[n][n];
        int k = 1; // Start filling from 1

        // Start filling the matrix in a spiral order
        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Traverse from left to right along the top row
            for (int i = colStart; i <= colEnd; i++) {
                out[rowStart][i] = k;
                k++;
            }
            rowStart++; // Move the row start down

            // Traverse from top to bottom along the right column
            for (int j = rowStart; j <= rowEnd; j++) {
                out[j][colEnd] = k;
                k++;
            }
            colEnd--; // Move the column end left

            if (rowStart <= rowEnd) {
                // Traverse from right to left along the bottom row
                for (int i = colEnd; i >= colStart; i--) {
                    out[rowEnd][i] = k;
                    k++;
                }
                rowEnd--; // Move the row end up
            }

            if (colStart <= colEnd) {
                // Traverse from bottom to top along the left column
                for (int j = rowEnd; j >= rowStart; j--) {
                    out[j][colStart] = k;
                    k++;
                }
                colStart++; // Move the column start right
            }
        }

        return out; // Return the filled spiral matrix
    }
}
