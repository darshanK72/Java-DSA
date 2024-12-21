/**
 * Problem Statement:
 * 
 *     You are given a matrix of size `n x m` consisting of binary values (0 or 1). 
 *     The goal is to find the exit point from the matrix, following a set of movement rules:
 * 
 *     - You start at the top-left corner of the matrix (0,0).
 *     - At each point, you follow the directions based on the current value in the matrix:
 *       - 0: Move in the current direction.
 *       - 1: Turn 90 degrees clockwise, and then move in the new direction.
 *     - The movement continues until you go out of the bounds of the matrix. The point where you exit the matrix is the exit point.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^3), representing the number of rows in the matrix.
 *     - An integer `m` (1 <= m <= 10^3), representing the number of columns in the matrix.
 *     - A matrix `matrix` of size `n x m`, where each element is either 0 or 1.
 * 
 * Output:
 *     - A pair of integers representing the row and column indices of the exit point.
 * 
 * Example:
 *     Input:
 *     3 3
 *     0 0 1
 *     1 0 0
 *     1 1 0
 *     
 *     Output:
 *     [2, 1]
 *     
 *     Explanation:
 *     - Start at (0, 0).
 *     - Move right (0 → 0).
 *     - Turn 90 degrees clockwise because of 1 at (0, 2).
 *     - Move down to (1, 2).
 *     - Move left to (1, 1).
 *     - Move up to (0, 1).
 *     - Exit at (2, 1).
 */

import java.util.Arrays;
import java.util.Scanner;

public class j03ExitPointInMatrix {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col];

        // Reading the matrix elements
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }

        // Call the method to find the exit point
        System.out.println(Arrays.toString(FindExitPoint(row, col, matrix)));

        in.close();
    }

    /**
     * Approach: Simulate the movement in the matrix
     * 
     * Intuition:
     * - Start at the top-left corner of the matrix (0,0).
     * - Follow the directions by checking the value at each cell:
     *   - If the value is 0, continue in the current direction.
     *   - If the value is 1, turn 90 degrees clockwise and continue.
     * - Once the movement goes out of bounds, the current position is the exit point.
     * 
     * The key idea is to simulate the movement through the matrix while adjusting the direction 
     * when encountering a 1. The matrix cells are modified to mark the visited cells by changing 
     * their value to 0 when a turn is made.
     * 
     * Time Complexity:
     * - O(M * N), where M is the number of rows and N is the number of columns in the matrix.
     *   - We may visit each cell once, and the matrix size is M * N.
     * 
     * Space Complexity:
     * - O(M * N), due to the space required to store the matrix.
     * 
     * @param n The number of rows in the matrix.
     * @param m The number of columns in the matrix.
     * @param matrix The input matrix.
     * @return The coordinates of the exit point.
     */
    public static int[] FindExitPoint(int n, int m, int[][] matrix) {
        int i = 0; // Row pointer
        int j = 0; // Column pointer
        int dir = 0; // Direction pointer (0 → Right, 1 → Down, 2 → Left, 3 → Up)

        // Simulating the movement
        while (i >= 0 && i < n && j >= 0 && j < m) {
            if (matrix[i][j] == 1) {
                // Change direction if the value is 1
                matrix[i][j] = 0;
                dir = (dir + 1) % 4; // Rotate direction clockwise
            }

            // Move based on the current direction
            if (dir == 0)
                j++; // Move Right
            else if (dir == 1)
                i++; // Move Down
            else if (dir == 2)
                j--; // Move Left
            else if (dir == 3)
                i--; // Move Up
        }

        // Adjust for the exit point if out of bounds
        if (i < 0)
            i = 0;
        if (j < 0)
            j = 0;
        if (i >= n)
            i = n - 1;
        if (j >= m)
            j = m - 1;

        // Return the coordinates of the exit point
        return new int[] { i, j };
    }
}
