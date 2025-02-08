/**
 * Problem Statement:
 * 
 *     Given a matrix of integers, we need to find all possible paths from the top-left corner
 *     (0, 0) to the bottom-right corner (m-1, n-1). You can only move right or down at each step.
 *     The function should return all the paths from the start to the destination, where each path
 *     is a sequence of integers representing the matrix elements traversed in order.
 * 
 * Input:
 *     - A 2D matrix `matrix` of size `m x n` where each element represents a number in the matrix.
 * 
 * Output:
 *     - A list of lists, where each inner list represents a path from the top-left to the bottom-right corner.
 * 
 * Example:
 *     Input:
 *     [
 *         [1, 2, 3],
 *         [4, 5, 6],
 *         [7, 8, 9]
 *     ]
 * 
 *     Output:
 *     [
 *         [1, 2, 3, 6, 9],
 *         [1, 2, 5, 6, 9],
 *         [1, 2, 5, 8, 9],
 *         [1, 4, 5, 6, 9],
 *         [1, 4, 5, 8, 9],
 *         [1, 4, 7, 8, 9]
 *     ]
 * 
 * Explanation:
 *     - The function finds all possible paths to reach the bottom-right corner by moving right or down.
 */

import java.util.ArrayList;
import java.util.List;

public class j03GetMazePathsDownRightII {

    public static void main(String[] args) {
        // Initialize a sample matrix
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        List<List<Integer>> allPaths = new ArrayList<>();

        // Start the recursive process from (0,0)
        getAllPaths1(matrix, 0, 0, new ArrayList<>(), allPaths);

        // Print all paths
        for (List<Integer> path : allPaths) {
            System.out.println(path);
        }

        List<List<Integer>> allPaths2 = new ArrayList<>();

        getAllPaths2(matrix, 0, 0, new ArrayList<>(), allPaths2);

        // Print all paths
        for (List<Integer> path : allPaths2) {
            System.out.println(path);
        }
    }

    /**
     * Approach 1: Recursive Collection of All Paths with Backtracking
     * 
     * Intuition:
     * - We start from the top-left corner of the matrix and recursively explore two directions: 
     *   move right (increases the column) and move down (increases the row).
     * - Each recursive call adds the current element in the matrix to the path and continues exploring.
     * - If we reach the bottom-right corner, we add the current path to the list of all paths.
     * - After exploring a direction (either right or down), we backtrack by removing the last element from the path.
     * 
     * Explanation:
     * - At each step, we add the current element to the `currentPath`. When we reach the bottom-right corner,
     *   we store the path into `allPaths`.
     * - If we exceed matrix bounds, we return from the recursion without adding anything.
     * - We backtrack after exploring both right and down directions to remove the most recent element from `currentPath`
     *   and explore other possibilities.
     * 
     * Time Complexity:
     * - O(2^(m + n)) since at each position, we have two choices (right or down) and recursively explore both.
     * 
     * Space Complexity:
     * - O(m + n) for the recursion stack.
     * 
     * @param matrix The matrix to traverse.
     * @param r The current row position.
     * @param c The current column position.
     * @param currentPath The list that holds the current path.
     * @param allPaths The list of all valid paths found.
     */
    public static void getAllPaths1(int[][] matrix, int r, int c, List<Integer> currentPath,
            List<List<Integer>> allPaths) {
        // Check if out of bounds
        if (r >= matrix.length || c >= matrix[0].length) {
            return; // Stop if out of bounds
        }

        // Add the current element to the path
        currentPath.add(matrix[r][c]);

        // If we've reached the bottom-right corner, add the current path to the result
        if (r == matrix.length - 1 && c == matrix[0].length - 1) {
            allPaths.add(new ArrayList<>(currentPath)); // Add the current path to allPaths
            currentPath.remove(currentPath.size() - 1); // Backtrack
            return;
        }

        // Explore right (move right)
        getAllPaths1(matrix, r, c + 1, currentPath, allPaths);

        // Explore down (move down)
        getAllPaths1(matrix, r + 1, c, currentPath, allPaths);

        // Backtrack: remove the current element from the path
        currentPath.remove(currentPath.size() - 1);
    }

    /**
     * Approach 2: Recursive Collection of All Paths (Without Backtracking)
     * 
     * Intuition:
     * - This approach is similar to the previous one, but the handling of backtracking is done after adding
     *   the path to the list `allPaths` rather than before. This can simplify the backtracking logic and ensures
     *   that the current element is not removed prematurely.
     * - When we reach the bottom-right corner, we immediately add the current path to the list.
     * 
     * Explanation:
     * - We explore both right and down directions. When the bottom-right corner is reached, we immediately
     *   add the path to `allPaths`.
     * - After each recursive call (right or down), we backtrack by removing the last element added to `currentPath`.
     * 
     * Time Complexity:
     * - O(2^(m + n)) since we are exploring all possible paths.
     * 
     * Space Complexity:
     * - O(m + n) for the recursion stack.
     * 
     * @param matrix The matrix to traverse.
     * @param r The current row position.
     * @param c The current column position.
     * @param currentPath The list that holds the current path.
     * @param allPaths The list of all valid paths found.
     */
    public static void getAllPaths2(int[][] matrix, int r, int c, List<Integer> currentPath,
            List<List<Integer>> allPaths) {

        // Check if out of bounds
        if (r >= matrix.length || c >= matrix[0].length) {
            return; // Stop if out of bounds
        }

        // Add the current element to the path
        currentPath.add(matrix[r][c]);

        // If we've reached the bottom-right corner, add the current path to the result
        if (r == matrix.length - 1 && c == matrix[0].length - 1) {
            allPaths.add(new ArrayList<>(currentPath)); // Add the path to allPaths
        } else {
            // Explore right (move right)
            getAllPaths2(matrix, r, c + 1, currentPath, allPaths);

            // Explore down (move down)
            getAllPaths2(matrix, r + 1, c, currentPath, allPaths);
        }

        // Backtrack: remove the current element from the path
        currentPath.remove(currentPath.size() - 1);
    }

    /**
     * Approach 3: Recursive Collection of All Paths with Explicit Start and Destination
     * 
     * Intuition:
     * - This approach is similar to the previous ones but allows specifying the start and destination positions.
     * - We recursively explore two directions: move right (increases the column) and move down (increases the row).
     * - Each recursive call adds the current element in the matrix to the path and continues exploring.
     * - If we reach the destination, we add the current path to the list of all paths.
     * - After exploring a direction (either right or down), we backtrack by removing the last element from the path.
     * 
     * Explanation:
     * - At each step, we add the current element to the `curr`. When we reach the destination, we store the path into `set`.
     * - If we exceed matrix bounds, we return from the recursion without adding anything.
     * - We backtrack after exploring both right and down directions to remove the most recent element from `curr`
     *   and explore other possibilities.
     * 
     * Time Complexity:
     * - O(2^(m + n)) since at each position, we have two choices (right or down) and recursively explore both.
     * 
     * Space Complexity:
     * - O(m + n) for the recursion stack.
     * 
     * @param mat The matrix to traverse.
     * @param sr The starting row position.
     * @param sc The starting column position.
     * @param dr The destination row position.
     * @param dc The destination column position.
     * @param curr The list that holds the current path.
     * @param set The list of all valid paths found.
     */
    public static void generatePaths(int[][] mat, int sr, int sc, int dr, int dc, ArrayList<Integer> curr,
            ArrayList<ArrayList<Integer>> set) {
        if (sr > dr || sc > dc) {
            return; // Stop if out of bounds
        }
        curr.add(mat[sr][sc]);

        if (sr == dr && sc == dc) {
            set.add(new ArrayList<>(curr)); // Add the current path to set
        } else {
            generatePaths(mat, sr + 1, sc, dr, dc, curr, set); // Explore down
            generatePaths(mat, sr, sc + 1, dr, dc, curr, set); // Explore right
        }

        curr.remove(curr.size() - 1); // Backtrack: remove the current element from the path
    }
}
