/**
 * Problem Statement:
 * 
 *     You are given an m x n matrix, where each row is sorted in ascending order, 
 *     and each column is also sorted in ascending order. You need to write a function 
 *     to search for a target value in the matrix. If the target exists in the matrix, 
 *     return true; otherwise, return false.
 * 
 * Input:
 *     - An integer m (1 <= m <= 1000), the number of rows in the matrix.
 *     - An integer n (1 <= n <= 1000), the number of columns in the matrix.
 *     - A matrix of size m x n where each row is sorted in ascending order and 
 *       each column is sorted in ascending order.
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

public class j03SearchInRowColSortedMatrixI {

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

    /**
     * Approach:
     * 
     * Intuition:
     * - Treat the matrix as a 1D sorted array to perform binary search on it.
     * - The 1D array is "flattened" by considering row and column indices as a single index.
     * - Given an element at position `mid`, the row and column can be calculated using:
     *     - `row = mid / n`
     *     - `col = mid % n`
     * - Perform binary search on this 1D representation of the matrix.
     * 
     * Time Complexity:
     * - O(log(m * n)): Since we're using binary search on the "flattened" matrix, 
     *   the time complexity is logarithmic with respect to the total number of elements.
     * 
     * Space Complexity:
     * - O(1): We are using constant space for the search operation.
     * 
     * @param matrix The input 2D matrix.
     * @param target The value to search for.
     * @return true if target is found, false otherwise.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length; // Number of rows
        int n = matrix[0].length; // Number of columns

        // Binary search on the "flattened" 1D array
        int s = 0; // Start index
        int e = m * n - 1; // End index

        while (s <= e) {
            int mid = s + (e - s) / 2; // Mid index

            // Convert the 1D mid index to 2D row and column
            int row = mid / n;
            int col = mid % n;

            // Check if the mid element is the target
            if (matrix[row][col] == target) {
                return true;
            }
            // If the mid element is greater than target, search the left side
            else if (matrix[row][col] > target) {
                e = mid - 1;
            }
            // If the mid element is smaller than target, search the right side
            else {
                s = mid + 1;
            }
        }

        // Return false if the target was not found
        return false;
    }
}
