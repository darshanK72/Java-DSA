/*-
 * Problem Statement:
 * 
 *     You are given an m x n matrix, where each row is sorted in ascending order 
 *     and each column is sorted in ascending order. You need to write a function 
 *     to search for a target value in the matrix. If the target exists in the matrix, 
 *     return true; otherwise, return false.
 * 
 * Input:
 *     - An integer m (1 <= m <= 1000), the number of rows in the matrix.
 *     - An integer n (1 <= n <= 1000), the number of columns in the matrix.
 *     - A matrix of size m x n where each row is sorted in ascending order 
 *       and each column is sorted in ascending order.
 *     - An integer target (1 <= target <= 10^6) which is the value to be searched.
 * 
 * Output:
 *     - Return true if the target exists in the matrix, otherwise return false.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 4 7
 *     2 5 8
 *     3 6 9
 *     5
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The target 5 is present at the matrix[1][1].
 */

import java.util.Scanner;

public class j04SearchInRowColSortedMatrixII {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // Number of rows
        int n = in.nextInt(); // Number of columns
        int[][] arr = new int[m][n];

        // Reading the matrix input
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        int target = in.nextInt(); // Target value to search for

        // Output the result of searchMatrix function
        System.out.println(searchMatrix(arr, target));
        in.close();
    }

    /*-
     * Approach:
     * 
     * Intuition:
     * - We can use the Staircase Algorithm to find the target.
     * - Start from the top-right corner of the matrix.
     * - If the current element is equal to the target, return true.
     * - If the current element is greater than the target, move left (decrease column).
     * - If the current element is smaller than the target, move down (increase row).
     * - Continue the process until we either find the target or move out of bounds.
     * 
     * Time Complexity:
     * - O(m + n), where m is the number of rows and n is the number of columns.
     *   In the worst case, we either move down m times or left n times.
     * 
     * Space Complexity:
     * - O(1): We are only using constant space for the search.
     * 
     * @param matrix The input 2D matrix.
     * @param target The value to search for.
     * @return true if target is found, false otherwise.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0; // Start from the top-right corner (row=0, col=n-1)
        int col = matrix[0].length - 1;

        // Traverse the matrix
        while (row < matrix.length && col >= 0) {
            // If we find the target, return true
            if (matrix[row][col] == target) {
                return true;
            }
            // If the current element is greater than target, move left
            else if (matrix[row][col] > target) {
                col--;
            }
            // If the current element is smaller than target, move down
            else {
                row++;
            }
        }

        // If the target was not found, return false
        return false;
    }
}
