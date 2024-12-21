/**
 * Problem Statement:
 * 
 *     Given two `n x n` matrices `mat1` and `mat2` and an integer `k`, the task is to count 
 *     the number of pairs `(i, j)` such that `mat1[i][j] + mat2[i][j] = k` for each element
 *     in the matrices. We will explore two approaches for counting these pairs.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100), representing the size of the matrices.
 *     - Two `n x n` matrices `mat1` and `mat2` where each element satisfies (1 <= mat1[i][j], mat2[i][j] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5).
 * 
 * Output:
 *     - An integer representing the number of pairs `(i, j)` such that `mat1[i][j] + mat2[i][j] = k`.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     9 8 7
 *     6 5 4
 *     3 2 1
 *     10
 * 
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The pairs `(i, j)` that satisfy the condition are:
 *     - mat1[0][0] + mat2[0][0] = 1 + 9 = 10
 *     - mat1[1][1] + mat2[1][1] = 5 + 5 = 10
 *     - mat1[2][2] + mat2[2][2] = 9 + 1 = 10
 */

import java.util.Scanner;

public class j05CountKSumPairsInSubmatrix {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the matrix
        int[][] matrix1 = new int[n][n];
        int[][] matrix2 = new int[n][n];

        // Reading the first matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix1[i][j] = in.nextInt();
            }
        }

        // Reading the second matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix2[i][j] = in.nextInt();
            }
        }

        // Reading the target sum
        int k = in.nextInt();

        // Call the solution functions and print the results
        System.out.println("Count of pairs: " + countPairs(matrix1, matrix2, n, k));
        System.out.println("Efficient Count of pairs: " + countPairsEfficient(matrix1, matrix2, n, k));

        in.close();
    }

    /**
     * Approach 1: Convert matrices to 1D arrays and use the two-pointer technique.
     * 
     * Intuition:
     * - The idea is to convert both matrices into flat 1D arrays.
     * - We then apply the two-pointer technique to find pairs of elements from both arrays that add up to `k`.
     * - This is essentially searching for pairs that sum up to a target value in a sorted array.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the size of the matrix. We are flattening the matrices into 1D arrays 
     *   and then using a two-pointer technique which takes linear time on the 1D arrays.
     * 
     * Space Complexity:
     * - O(n^2), to store the flattened matrices.
     * 
     * @param mat1 The first input matrix.
     * @param mat2 The second input matrix.
     * @param n The size of the matrix (n x n).
     * @param x The target sum.
     * @return The count of pairs whose sum is equal to `k`.
     */
    public static int countPairs(int mat1[][], int mat2[][], int n, int x) {
        int count = 0;
        int[] fmat1 = new int[n * n];
        int[] fmat2 = new int[n * n];
        int k = 0;

        // Flatten matrix 1 into fmat1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmat1[k] = mat1[i][j];
                k++;
            }
        }
        k = 0;

        // Flatten matrix 2 into fmat2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmat2[k] = mat2[i][j];
                k++;
            }
        }

        // Apply two-pointer technique to count pairs
        int s = 0;
        int e = n * n - 1;
        while (s < n * n && e >= 0) {
            int sum = fmat1[s] + fmat2[e];
            if (sum == x) {
                count++;
                s++;
                e--;
            } else if (sum > x) {
                e--;
            } else {
                s++;
            }
        }
        return count;
    }

    /**
     * Approach 2: Efficient approach by using row and column pointers.
     * 
     * Intuition:
     * - Instead of flattening the matrices, this approach directly works on the 2D matrices by 
     *   traversing them with row and column pointers.
     * - We compare the sum of the elements from the current positions in both matrices and adjust 
     *   the pointers accordingly.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the size of the matrix. We are traversing the matrices and adjusting 
     *   the pointers in constant time.
     * 
     * Space Complexity:
     * - O(1), since we are not using any additional space other than the input matrices.
     * 
     * @param mat1 The first input matrix.
     * @param mat2 The second input matrix.
     * @param n The size of the matrix (n x n).
     * @param x The target sum.
     * @return The count of pairs whose sum is equal to `k`.
     */
    public static int countPairsEfficient(int mat1[][], int mat2[][], int n, int x) {
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = n - 1;
        int colEnd = n - 1;
        int count = 0;

        while (rowStart < n && colStart < n && rowEnd >= 0 && colEnd <= n) {
            int sum = mat1[rowStart][colStart] + mat2[rowEnd][colEnd];
            if (sum == x) {
                count++;
                if (colStart < n - 1) {
                    colStart++;
                } else {
                    rowStart++;
                    colStart = 0;
                }
                if (colEnd > 0) {
                    colEnd--;
                } else {
                    rowEnd--;
                    colEnd = n - 1;
                }
            } else if (sum > x) {
                if (colEnd > 0) {
                    colEnd--;
                } else {
                    rowEnd--;
                    colEnd = n - 1;
                }
            } else {
                if (colStart < n - 1) {
                    colStart++;
                } else {
                    rowStart++;
                    colStart = 0;
                }
            }
        }
        return count;
    }
}
