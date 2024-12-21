/**
 * Problem Statement:
 * 
 *     Given two matrices `matrix1` and `matrix2` of the same dimensions, the task is to add the corresponding 
 *     elements of both matrices and store the result in the first matrix `matrix1`. 
 *     The addition of two matrices is done by adding corresponding elements from both matrices.
 * 
 * Input:
 *     - An integer `row` representing the number of rows in both matrices.
 *     - An integer `col` representing the number of columns in both matrices.
 *     - A matrix `matrix1` of size `row x col`.
 *     - A matrix `matrix2` of size `row x col`.
 * 
 * Output:
 *     - The resulting matrix after adding the corresponding elements of both matrices. The result is stored in `matrix1`.
 * 
 * Example:
 *     Input:
 *     2 2
 *     1 2
 *     3 4
 *     2 2
 *     5 6
 *     7 8
 * 
 *     Output:
 *     6 8
 *     10 12
 * 
 *     Explanation:
 *     The two matrices are added element-wise: 
 *     (1+5) (2+6) => 6 8
 *     (3+7) (4+8) => 10 12
 */

import java.util.Scanner;

public class j02MatrixAddition {

    public static void main(String args[]) {
        // Reading input for the first matrix
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows
        int col = in.nextInt(); // Input: number of columns
        int[][] matrix1 = new int[row][col];

        // Input: elements of the first matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix1[i][j] = in.nextInt();
            }
        }

        // Reading input for the second matrix
        int[][] matrix2 = new int[row][col];

        // Input: elements of the second matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix2[i][j] = in.nextInt();
            }
        }

        // Add the two matrices and update matrix1
        add(matrix1, matrix2);

        // Output the resulting matrix after addition
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }

        in.close();
    }

    /**
     * Approach: Add the corresponding elements of two matrices
     * 
     * Intuition:
     * - We iterate through each element of the two matrices. For each corresponding element `matrix1[i][j]` 
     *   and `matrix2[i][j]`, we add the values and store the result back in `matrix1[i][j]`.
     * - This operation is element-wise addition where each element of the result is the sum of corresponding 
     *   elements in `matrix1` and `matrix2`.
     * - Since the matrices have the same dimensions, we are assured that we can perform the addition for every 
     *   corresponding element.
     * 
     * Time Complexity:
     * - O(n * m), where n is the number of rows and m is the number of columns. We need to visit each element 
     *   once to perform the addition.
     * 
     * Space Complexity:
     * - O(1), since we are performing the addition directly on `matrix1`, and we don't need any additional space 
     *   besides the input matrices.
     * 
     * @param g1 The first matrix.
     * @param g2 The second matrix.
     */
    public static void add(int[][] g1, int[][] g2) {
        for (int i = 0; i < g1.length; i++) {
            for (int j = 0; j < g2[i].length; j++) {
                g1[i][j] += g2[i][j]; // Add corresponding elements and store in matrix1
            }
        }
    }
}
