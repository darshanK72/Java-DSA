/**
 * Problem Statement:
 * 
 *     Given a matrix of size `m x n` (m rows and n columns), you are tasked with rotating the elements of each "ring" of the matrix by `k` positions in the clockwise direction. 
 *     A "ring" is a cycle of elements in the matrix that follows the boundary of a sub-matrix. The number of rings in a matrix depends on the number of rows and columns.
 * 
 * Input:
 *     - An integer `row` (1 <= row <= 10^5) representing the number of rows in the matrix.
 *     - An integer `col` (1 <= col <= 10^5) representing the number of columns in the matrix.
 *     - An integer `k` (1 <= k <= 10^5) representing the number of positions each ring should be rotated.
 *     - A 2D matrix `matrix` of size `row x col` where each element is an integer between `1` and `10^5`.
 * 
 * Output:
 *     - A 2D matrix where each "ring" of the matrix has been rotated by `k` positions.
 * 
 * Example:
 *     Input:
 *     4 4
 *     1 2 3 4
 *     5 6 7 8
 *     9 10 11 12
 *     13 14 15 16
 *     2
 * 
 *     Output:
 *     4 3 2 1
 *     10 6 7 5
 *     11 12 8 4
 *     14 13 9 15
 * 
 *     Explanation:
 *     The first ring (containing elements 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5) has been rotated by `2` positions clockwise.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class j05ShellRotateByK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }
        int k = in.nextInt(); // Input: number of positions to rotate
        rotateRings(matrix, k, row, col); // Rotate the rings by k positions
        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr)); // Print the rotated matrix
        }
        in.close();
    }

    /**
     * Approach: Rotate matrix rings by k positions.
     * 
     * Intuition:
     * - The matrix is considered as a set of concentric rings. 
     * - Each ring can be treated as a list, and we need to rotate these lists by `k` positions.
     * - After rotating the list, we put the elements back into their respective positions in the matrix.
     * - This involves extracting the elements in a spiral order, rotating the list, and then placing them back in the spiral order.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns in the matrix. 
     * - This is because we process each element in the matrix exactly once.
     * 
     * Space Complexity:
     * - O(m + n), as we need extra space to store the elements of each ring during the rotation process.
     * 
     * @param mat The input matrix to be rotated.
     * @param k The number of positions to rotate the rings.
     * @param m The number of rows in the matrix.
     * @param n The number of columns in the matrix.
     */
    public static void rotateRings(int[][] mat, int k, int m, int n) {
        // Spiral rotation parameters.
        int top = 0, bottom = m - 1, left = 0, right = n - 1;

        // Rotate while the ring exists.
        while (top <= bottom) {

            // To hold the ring elements.
            List<Integer> elems = new ArrayList<>();

            // Do the spiral traversal and store the ring elements.
            for (int i = left; i <= right; i++) {
                elems.add(mat[top][i]);
            }

            for (int i = top + 1; i <= bottom; i++) {
                elems.add(mat[i][right]);
            }

            for (int i = right - 1; i >= left; i--) {
                elems.add(mat[bottom][i]);
            }

            for (int i = bottom - 1; i > top; i--) {
                elems.add(mat[i][left]);
            }

            // Check if the ring size is less than or equal to k
            // If true, break as the rings after will also be smaller than k.
            if (elems.size() <= k) {
                break;
            }

            // Rotation starts.
            // ind represents the index of the
            // position that should be at the start of the ring.
            int sz = elems.size();
            int ind = sz - k;

            // Store the rotated ring.
            for (int i = left; i <= right; i++) {
                mat[top][i] = elems.get(ind);
                ind++;
                ind %= sz;
            }

            for (int i = top + 1; i <= bottom; i++) {
                mat[i][right] = elems.get(ind);
                ind++;
                ind %= sz;
            }

            for (int i = right - 1; i >= left; i--) {
                mat[bottom][i] = elems.get(ind);
                ind++;
                ind %= sz;
            }

            for (int i = bottom - 1; i > top; i--) {
                mat[i][left] = elems.get(ind);
                ind++;
                ind %= sz;
            }

            // Update the rotation parameters.
            top++;
            bottom--;
            left++;
            right--;
        }
    }
}
