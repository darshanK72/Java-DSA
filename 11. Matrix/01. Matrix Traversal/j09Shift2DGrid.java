/**
 * Problem Statement:
 * 
 *     Given a 2D grid of size `m x n` and an integer `k`, the task is to shift the grid by `k` positions to the right. 
 *     More specifically, move each element of the grid to its right and if it goes beyond the boundary, it should wrap around 
 *     to the next row. The movement should happen in a row-major order.
 * 
 * Input:
 *     - Two integers `m` and `n` (1 <= m, n <= 100) representing the number of rows and columns of the grid.
 *     - A 2D grid of size `m x n` where each element satisfies (1 <= grid[i][j] <= 1000).
 *     - An integer `k` (0 <= k <= 1000), representing the number of times the grid should be shifted.
 * 
 * Output:
 *     - A 2D grid after shifting `k` times to the right.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     1
 *     
 *     Output:
 *     [[9, 1, 2], [3, 4, 5], [6, 7, 8]]
 * 
 *     Explanation:
 *     - After shifting the grid by 1 position, the elements move to the right with wrapping.
 *     - The result is the grid after 1 shift.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j09Shift2DGrid {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col]; // Initialize the 2D matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }
        int k = in.nextInt(); // Input: the number of shifts

        List<List<Integer>> grid = shiftGrid(matrix, k); // Perform the shifting operation

        // Output the resulting grid
        System.out.println(grid);
        in.close();
    }

    /**
     * Approach: Shift the Grid by k Positions
     * 
     * Intuition:
     * - We need to shift the grid `k` times to the right.
     * - For each shift operation, the elements move one position to the right, and any element that moves past 
     *   the right boundary of the grid wraps around to the next row.
     * - This can be achieved by shifting all elements and using a temporary array to store values that wrap around.
     * 
     * Time Complexity:
     * - O(k * m * n), where `k` is the number of shifts and `m * n` is the total number of elements in the grid.
     *   Each shift involves updating all elements in the grid, which takes O(m * n) time.
     * 
     * Space Complexity:
     * - O(m * n), as we use an additional array of size `m` to store temporary values during shifting.
     * 
     * @param grid The input 2D grid.
     * @param k The number of shifts.
     * @return The grid after shifting `k` times to the right.
     */
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        ArrayList<List<Integer>> out = new ArrayList<List<Integer>>(); // Initialize output list
        while (k > 0) {
            shiftOne(grid); // Shift the grid by one position
            k--; // Decrement shift count
        }
        // Convert the shifted 2D grid to a list of lists
        for (int i = 0; i < grid.length; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int j = 0; j < grid[0].length; j++) {
                temp.add(grid[i][j]);
            }
            out.add(temp); // Add the row to the result
        }
        return out; // Return the result
    }

    /**
     * Approach: Shift One Position in the Grid
     * 
     * Intuition:
     * - For each shift operation, we need to move all elements to the right.
     * - The last element of each row should move to the first element of the next row.
     * - We can achieve this by saving the last element in a temporary array, shifting the elements, 
     *   and then placing the temporary values in their new positions.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns in the grid.
     *   This is because we are shifting all elements of the grid.
     * 
     * Space Complexity:
     * - O(m), as we only use a temporary array to store the last column's values.
     * 
     * @param grid The input 2D grid.
     */
    public static void shiftOne(int[][] grid) {
        int[] temp = new int[grid.length]; // Temporary array to store last column values
        // Save the last element of each row
        for (int i = 0; i < grid.length; i++) {
            temp[i] = grid[i][grid[0].length - 1];
        }

        // Shift all elements in the grid to the right
        for (int j = grid[0].length - 1; j > 0; j--) {
            for (int i = 0; i < grid.length; i++) {
                grid[i][j] = grid[i][j - 1];
            }
        }

        // Move the last element of each row to the first column of the next row
        int t = temp[grid.length - 1];
        for (int i = grid.length - 1; i > 0; i--) {
            grid[i][0] = temp[i - 1];
        }
        grid[0][0] = t; // Set the first element
    }
}
