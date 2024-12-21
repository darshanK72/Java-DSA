/**
 * Problem Statement:
 * 
 *     Given a square matrix of size `n x n` containing integers, the task is to check whether each row 
 *     and each column contains all integers from 1 to `n` exactly once. If both conditions are satisfied, 
 *     return `true`, otherwise return `false`.
 * 
 * Input:
 *     - An integer `n` representing the size of the matrix (n x n).
 *     - A 2D matrix of integers of size `n x n` where each element represents a number in the matrix.
 * 
 * Output:
 *     - Return `true` if each row and column contains all integers from 1 to `n` exactly once. 
 *       Otherwise, return `false`.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     3 1 2
 *     2 3 1
 * 
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - Each row contains the numbers from 1 to 3 exactly once: 
 *       - Row 1: [1, 2, 3]
 *       - Row 2: [3, 1, 2]
 *       - Row 3: [2, 3, 1]
 *     - Each column contains the numbers from 1 to 3 exactly once:
 *       - Column 1: [1, 3, 2]
 *       - Column 2: [2, 1, 3]
 *       - Column 3: [3, 2, 1]
 *     - Since both the rows and columns meet the condition, the output is `true`.
 */

import java.util.Scanner;

public class j08RowColContainsAllNums {

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

        // Call the function to check if each row and column contains all numbers from 1
        // to n exactly once
        System.out.println(checkValid(matrix));

        in.close();
    }

    /**
     * Approach: Brute Force Approach with Validation
     * 
     * Intuition:
     * - We need to validate that each row and each column of the matrix contains all numbers from 1 to n 
     *   exactly once.
     * - For each row, we maintain a map (array) that counts the occurrences of each number from 1 to n.
     * - Similarly, for each column, we also maintain a map (array) to count occurrences.
     * - If any number is missing or repeated in a row or column, we return `false`. If all rows and columns 
     *   are valid, we return `true`.
     * 
     * Time Complexity:
     * - O(N^2), where N is the number of rows (or columns) in the matrix. We are iterating through each 
     *   element of the matrix twice: once for the rows and once for the columns.
     * 
     * Space Complexity:
     * - O(N), where N is the number of rows (or columns) in the matrix. We are using an array of size N 
     *   for each row and column to count the occurrences of the numbers.
     * 
     * @param matrix The matrix represented as a 2D array.
     * @return `true` if each row and column contains all numbers from 1 to n exactly once, otherwise `false`.
     */
    public static boolean checkValid(int[][] matrix) {
        // Iterate through each row and column
        for (int i = 0; i < matrix.length; i++) {
            int[] map = new int[matrix.length];

            // Check row validity
            for (int j = 0; j < matrix.length; j++) {
                map[matrix[i][j] - 1]++;
            }

            // Validate row: All elements from 1 to n must appear exactly once
            for (int j = 0; j < map.length; j++) {
                if (map[j] == 0 || map[j] > 1)
                    return false;
            }

            // Reset map to check column
            map = new int[matrix.length];

            // Check column validity
            for (int j = 0; j < matrix.length; j++) {
                map[matrix[j][i] - 1]++;
            }

            // Validate column: All elements from 1 to n must appear exactly once
            for (int j = 0; j < map.length; j++) {
                if (map[j] == 0 || map[j] > 1)
                    return false;
            }
        }

        // Return true if all rows and columns are valid
        return true;
    }
}
