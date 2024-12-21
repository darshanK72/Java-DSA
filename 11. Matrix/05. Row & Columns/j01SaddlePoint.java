/**
 * Problem Statement:
 * 
 *     A saddle point in a matrix is an element which is the minimum element in its row, and the maximum element in its column.
 *     Your task is to find the saddle point in a given `m x n` matrix.
 * 
 * Input:
 *     - An integer `m` (1 <= m <= 100) representing the number of rows in the matrix.
 *     - An integer `n` (1 <= n <= 100) representing the number of columns in the matrix.
 *     - A matrix `matrix` of size `m x n` where each element satisfies (-1000 <= matrix[i][j] <= 1000).
 * 
 * Output:
 *     - Return a list containing the saddle point element, if found. If there is no saddle point, return an empty list.
 * 
 * Example:
 *     Input:
 *     3 3
 *     3 8 4
 *     2 7 6
 *     9 1 5
 *     
 *     Output:
 *     [7]
 * 
 *     Explanation:
 *     The minimum element in the second row is 2, which is also the maximum element in the second column (7).
 *     Therefore, 7 is the saddle point.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j01SaddlePoint {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows
        int col = in.nextInt(); // Input: number of columns
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: matrix elements
            }
        }

        // Call the solution method and print the result
        System.out.println(getSaddlePoint(matrix));

        in.close();
    }

    /**
     * Approach 1: Find the Saddle Point by Checking Row Minimum and Column Maximum
     * 
     * Intuition:
     * - A saddle point is defined as the minimum element in its row and the maximum element in its column.
     * - We can iterate through each row and find the minimum element. Then, check if that element is the maximum in its respective column.
     * - If both conditions are met, we have found the saddle point and can return it.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns. 
     *   We check each element in the row and each element in the corresponding column.
     * 
     * Space Complexity:
     * - O(1), as we are only using a few variables and not storing extra data.
     * 
     * @param matrix The input matrix.
     * @return A list containing the saddle point, if found; otherwise, an empty list.
     */
    public static List<Integer> getSaddlePoint(int[][] matrix) {
        ArrayList<Integer> out = new ArrayList<Integer>();

        // Iterate through each row to find the minimum element
        for (int i = 0; i < matrix.length; i++) {
            int minColIndex = 0;
            // Find the minimum element in the current row
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < matrix[i][minColIndex])
                    minColIndex = j;
            }

            // Check if the minimum element in the row is the maximum in its column
            int maxRowIndex = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][minColIndex] > matrix[maxRowIndex][minColIndex])
                    maxRowIndex = j;
            }

            // If the row index of the maximum element in the column matches the current
            // row, it's the saddle point
            if (maxRowIndex == i) {
                out.add(matrix[maxRowIndex][minColIndex]);
                break; // Only one saddle point exists, so break out of the loop
            }
        }

        return out; // Return the saddle point or an empty list if no saddle point is found
    }
}
