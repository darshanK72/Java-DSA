/**
 * Problem Statement:
 * 
 *     Given an `n x n` matrix, the goal is to compute the sum of two triangles of the matrix:
 *     1. The upper triangle (including the main diagonal) of the matrix.
 *     2. The lower triangle (including the main diagonal) of the matrix.
 *     
 *     The upper triangle consists of all elements where the row index is less than or equal to the column index.
 *     The lower triangle consists of all elements where the row index is greater than or equal to the column index.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100) representing the size of the square matrix.
 *     - A matrix `matrix` of size `n x n` where each element satisfies (-1000 <= matrix[i][j] <= 1000).
 * 
 * Output:
 *     - An array of two integers:
 *       1. The sum of elements in the upper triangle.
 *       2. The sum of elements in the lower triangle.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     
 *     Output:
 *     [15, 30]
 * 
 *     Explanation:
 *     Upper triangle:
 *     [1, 2, 3] (first row), [5, 6] (second row), [9] (third row) => Sum = 15
 *     Lower triangle:
 *     [1] (first row), [4, 5] (second row), [7, 8, 9] (third row) => Sum = 30
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j09SumOfUpperLower {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the square matrix
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt(); // Input: matrix elements
            }
        }

        // Call the solution method and print the result
        System.out.println(sumTriangles(matrix, n));

        in.close();
    }

    /**
     * Approach 1: Calculate Sum of Upper and Lower Triangles Separately
     * 
     * Intuition:
     * - The upper triangle includes elements where the row index is less than or equal to the column index (i <= j).
     * - The lower triangle includes elements where the row index is greater than or equal to the column index (i >= j).
     * - We can iterate over the matrix and separate the elements into upper and lower triangles, adding the appropriate elements to each sum.
     * 
     * Time Complexity:
     * - O(n^2), as we need to iterate through the entire matrix and check every element.
     * 
     * Space Complexity:
     * - O(1), as we are only using a few integer variables and the result array.
     * 
     * @param matrix The input square matrix.
     * @param n The size of the matrix.
     * @return An ArrayList containing two integers: sum of upper triangle and sum of lower triangle.
     */
    public static ArrayList<Integer> sumTriangles(int[][] matrix, int n) {
        int upSum = 0; // Sum of upper triangle
        int lowSum = 0; // Sum of lower triangle

        // Calculate sum of the upper triangle
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                upSum += matrix[i][j]; // Add elements to upper triangle sum
            }
        }

        // Calculate sum of the lower triangle
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                lowSum += matrix[i][j]; // Add elements to lower triangle sum
            }
        }

        // Store the results in the output list
        ArrayList<Integer> out = new ArrayList<>();
        out.add(upSum); // Add upper triangle sum
        out.add(lowSum); // Add lower triangle sum
        return out;
    }
}
