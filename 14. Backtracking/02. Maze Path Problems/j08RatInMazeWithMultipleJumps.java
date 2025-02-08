/**
 * Problem Statement:
 * 
 *     You are given a maze represented as an `n x n` matrix, where the starting block is at the upper left 
 *     (matrix[0][0]) and the destination block is at the lower right (matrix[n-1][n-1]). 
 *     The rat can move in two directions: forward (to the right) or downward. The rat can also jump a number 
 *     of cells depending on the value at each block in the maze. If the cell has a non-zero value `k`, it indicates 
 *     the maximum number of cells the rat can jump from that position (either to the right or downward).
 *     
 *     Your task is to determine if the rat can reach the destination and, if so, find the path. 
 *     You must return a matrix where 1 represents the path the rat took and 0 represents cells that were not
 *     part of the path. If there is no path, return a matrix `{{-1}}`.
 * 
 * Input:
 *     - An `n x n` matrix of integers where matrix[i][j] indicates the maximum number of jumps the rat can 
 *     make from that position.
 * 
 * Output:
 *     - A matrix of the same size where `1` represents the cells visited by the rat on the valid path, and 
 *      `0` represents non-path cells.
 *     - If no path exists, return a matrix of size `1x1` with value `-1`.
 * 
 * Example 1:
 *     Input:
 *     {{2,1,0,0},
 *      {3,0,0,1},
 *      {0,1,0,1},
 *      {0,0,0,1}}
 *     Output:
 *     {{1,0,0,0},
 *      {1,0,0,1},
 *      {0,0,0,1},
 *      {0,0,0,1}}
 * 
 *     Explanation: The rat starts at matrix[0][0], where it can jump 2 steps. First, it moves forward to 
 *     matrix[0][1], then to matrix[1][0].  From matrix[1][0], it jumps 3 steps and reaches matrix[1][3],
 *     where it then moves downward to matrix[3][3] to reach the destination.
 * 
 */

public class j08RatInMazeWithMultipleJumps {

    public static void main(String[] args) {
        // Test Case 1
        int[][] matrix1 = {
                { 2, 1, 0, 0 },
                { 3, 0, 0, 1 },
                { 0, 1, 0, 1 },
                { 0, 0, 0, 1 }
        };
        int[][] result1 = shortestDistance(matrix1);
        printMatrix(result1); // Output: Valid path matrix

        // Test Case 2
        int[][] matrix2 = {
                { 2, 1, 0, 0 },
                { 2, 0, 0, 1 },
                { 0, 1, 0, 1 },
                { 0, 0, 0, 1 }
        };
        int[][] result2 = shortestDistance(matrix2);
        printMatrix(result2); // Output: {-1} as no valid path exists
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static int[][] shortestDistance(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ans = new int[m][n];
        if (generateRatMazePath(matrix, ans, 0, 0, m - 1, n - 1))
            return ans;
        return new int[][] { { -1 } };
    }

    /**
     * Approach 1: Recursive Depth-First Search (DFS) with Backtracking
     * 
     * Intuition:
     * - The idea is to explore all possible jumps from the current cell, both horizontally and vertically. 
     * - The rat can move to any neighboring cell within the jump limit from the current position. If we find 
     *    a valid path 
     *   that reaches the destination, we return true. Otherwise, we backtrack and try another direction.
     * - We use a visited matrix to mark the cells that have been visited to avoid revisiting the same cells in
     *   a circular way.
     *
     * Time Complexity:
     * - O(m * n), where m is the number of rows and n is the number of columns. This is because, in the worst case, 
     *   we may need to explore all cells to find the shortest path.
     *
     * Space Complexity:
     * - O(m * n), due to the recursive call stack and the visited matrix used for backtracking.
     * 
     * @param matrix The input maze represented as a 2D matrix.
     * @param visited The 2D matrix to track visited cells during the search.
     * @param sr The current row position of the rat.
     * @param sc The current column position of the rat.
     * @param dr The destination row position (m-1).
     * @param dc The destination column position (n-1).
     * @return true if a path exists, otherwise false.
     */
    public static boolean generateRatMazePath(int[][] matrix, int[][] visited, int sr, int sc, int dr, int dc) {
        if (sr > dr || sc > dc)
            return false; // If we move out of bounds, return false.

        visited[sr][sc] = 1; // Mark the current cell as visited.

        if (sr == dr && sc == dc) // If we reach the destination, return true.
            return true;

        if (matrix[sr][sc] != 0) { // If the current cell is not blocked.
            for (int i = 1; i <= matrix[sr][sc]; i++) {
                // Try to move horizontally.
                if (generateRatMazePath(matrix, visited, sr, sc + i, dr, dc))
                    return true;

                // Try to move vertically.
                if (generateRatMazePath(matrix, visited, sr + i, sc, dr, dc))
                    return true;
            }
        }

        visited[sr][sc] = 0; // Unmark the current cell before backtracking.
        return false; // Return false if no valid path is found.
    }
}