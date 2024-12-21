/**
 * Problem Statement:
 * 
 *     Given a matrix of size `row x col` containing integers, the task is to determine if the sum of the 
 *     elements in each row is equal to the sum of the corresponding elements in the same column. If the sum 
 *     of every row is equal to the sum of every column for the corresponding index, return 1; otherwise, 
 *     return 0.
 * 
 * Input:
 *     - Two integers `row` and `col` representing the number of rows and columns in the matrix.
 *     - A 2D matrix of integers where each element represents the matrix entry at the corresponding position.
 * 
 * Output:
 *     - Output 1 if for all row sums, the corresponding column sums are equal; otherwise, output 0.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 * 
 *     Output:
 *     0
 * 
 *     Explanation:
 *     - Sum of 1st row: 1 + 2 + 3 = 6, Sum of 1st column: 1 + 4 + 7 = 12
 *     - Sum of 2nd row: 4 + 5 + 6 = 15, Sum of 2nd column: 2 + 5 + 8 = 15
 *     - Sum of 3rd row: 7 + 8 + 9 = 24, Sum of 3rd column: 3 + 6 + 9 = 18
 *     - Since not all row sums equal the corresponding column sums, the output is 0.
 */

import java.util.Scanner;

public class j07SumIthRowIthCol {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows
        int col = in.nextInt(); // Input: number of columns
        int[][] matrix = new int[row][col];

        // Input matrix values
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        // Call the function to check sum equality
        System.out.println(sumOfRowCol(row, col, matrix));

        in.close();
    }

    /**
     * Approach: Brute Force Approach
     * 
     * Intuition:
     * - The problem asks us to check if the sum of the i-th row is equal to the sum of the i-th column.
     * - We iterate over the matrix and calculate the sum of each row and the sum of each column. If at any 
     *   point, the sum of a row is not equal to the sum of the corresponding column, we immediately return 0.
     * - If all rows and columns satisfy the condition, we return 1.
     * 
     * Time Complexity:
     * - O(N * M), where N is the number of rows and M is the number of columns. This is because we are iterating 
     *   through every row and column once to compute the sums.
     * 
     * Space Complexity:
     * - O(1), as we are not using any extra space other than the input matrix.
     * 
     * @param N The number of rows in the matrix.
     * @param M The number of columns in the matrix.
     * @param A The matrix represented as a 2D array.
     * @return 1 if the sum of rows equals the sum of columns, otherwise 0.
     */
    public static int sumOfRowCol(int N, int M, int A[][]) {
        int n = Math.min(N, M);

        // Iterate through each index
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            // Calculate sum of the i-th row
            for (int j = 0; j < N; j++) {
                rowSum += A[j][i];
            }

            int colSum = 0;
            // Calculate sum of the i-th column
            for (int j = 0; j < M; j++) {
                colSum += A[i][j];
            }

            // If the row sum is not equal to the column sum, return 0
            if (rowSum != colSum) {
                return 0;
            }
        }

        // If all row sums equal column sums, return 1
        return 1;
    }
}
