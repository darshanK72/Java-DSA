/**
 * Problem Statement:
 * 
 *     Given a matrix of integers, print the matrix row by row.
 *     You are given a matrix of size `m x n`, where `m` is the number of rows and `n` is the number of columns.
 *     Your task is to print each row of the matrix on a new line, where the elements of each row are space-separated.
 * 
 * Input:
 *     - Two integers `m` and `n` representing the number of rows and columns in the matrix.
 *     - A matrix `mat` of size `m x n` where each element `mat[i][j]` is an integer.
 * 
 * Output:
 *     - Print each row of the matrix, with each element separated by a space.
 * 
 * Example:
 * 
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     
 *     Output:
 *     1 2 3
 *     4 5 6
 *     7 8 9
 * 
 *     Explanation:
 *     The matrix is a 3x3 matrix. Each row is printed on a new line.
 */

import java.util.Scanner;

public class j01PrintMatrixRowWise {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows
        int col = in.nextInt(); // Input: number of columns
        int[][] matrix = new int[row][col];

        // Populating the matrix with input values
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input matrix elements
            }
        }

        // Call the method to print the matrix row-wise
        printMatrixRowWise(matrix);
        in.close();
    }

    /**
     * Approach:
     * The method `printMatrixRowWise` iterates through each row of the matrix and 
     * prints the elements of each row space-separated. Each row is printed on a new line.
     * 
     * Intuition:
     * - In a matrix, each row is independent of others, so we simply need to 
     *   iterate through the rows and print each element of a row.
     * - This is a simple linear scan through the matrix where each element is accessed 
     *   once in a row-major order.
     * 
     * Time Complexity:
     * - O(m * n), where m is the number of rows and n is the number of columns in the matrix.
     *   Since we need to visit each element of the matrix once.
     * 
     * Space Complexity:
     * - O(1), since the matrix is already provided in the input, we do not use any additional space 
     *   besides the input matrix itself.
     * 
     * @param mat The matrix to be printed row-wise.
     */
    public static void printMatrixRowWise(int[][] mat) {
        // Iterate through each row
        for (int row = 0; row < mat.length; row++) {
            // For each row, iterate through the columns and print each element
            for (int col = 0; col < mat[row].length; col++) {
                System.out.print(mat[row][col] + " "); // Print each element in a row
            }
            System.out.println(); // Move to the next line after printing a row
        }
    }
}
