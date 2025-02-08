/**
 * Problem Statement:
 * 
 *     Given a maze represented by a grid of size m x n, where the starting point is at the top-left corner (0,0)
 *     and the destination is at the bottom-right corner (m-1, n-1), the task is to find all possible paths from the
 *     start to the destination using the four possible directions: down (D), left (L), right (R), and up (U).
 *     A path is a sequence of steps where each step is one of the four directions, and each step should not revisit
 *     a cell.
 * 
 * Input:
 *     - Two integers `m` and `n` (1 <= m, n <= 10^3), representing the number of rows and columns of the maze.
 * 
 * Output:
 *     - A list of all possible paths from the start to the destination in the format of a sequence of directions
 *       (D, L, R, U).
 * 
 * Example:
 *     Input:
 *     3 3
 *     Output:
 *     ["DDRR", "DRRD", "DRUR", "RDDR", "RDRD", "RRDD"]
 * 
 *     Explanation:
 *     The maze has a size of 3x3, and the paths from the start (0, 0) to the destination (2, 2) are generated using
 *     the four possible directions. The output lists all unique paths.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j05GetMazePathsAllDirection {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // Input: number of rows
        int n = in.nextInt(); // Input: number of columns

        // Print all possible maze paths
        printAllMazePaths(0, 0, m - 1, n - 1, "", new boolean[m][n]);

        // Get all possible maze paths and print
        System.out.println(getAllMazePaths(0, 0, m - 1, n - 1, "", new boolean[m][n]));

        // Generate all maze paths in a list and print
        ArrayList<String> set = new ArrayList<String>();
        generateAllMazePaths(0, 0, m - 1, n - 1, new boolean[m][n], "", set);
        System.out.println(set);
        in.close();
    }

    /**
     * Approach 1: Print All Maze Paths
     * 
     * Intuition:
     * - This approach prints the valid paths as soon as they are found.
     * - We start from the top-left corner of the maze and explore each of the four possible directions (Down, Left, Right, Up).
     * - As we move, we mark the cells as "visited" to prevent revisiting and ensure the path remains valid.
     * - Once we reach the destination (bottom-right corner), we print the current path.
     * - The recursive calls explore all possibilities by moving down, left, right, and up in sequence.
     * 
     * Time Complexity:
     * - O(4^n) in the worst case because there are 4 possible directions to move at each step. With branching at each step, this could lead to an exponential number of possible paths.
     * 
     * Space Complexity:
     * - O(m * n) for the visited array, where m and n are the maze dimensions. The recursion stack will also use space proportional to the depth of the recursion (which could go up to m * n in the worst case).
     * 
     * @param sr The starting row index.
     * @param sc The starting column index.
     * @param dr The destination row index.
     * @param dc The destination column index.
     * @param step The current path taken.
     * @param visited The visited cells to prevent revisiting.
     */
    public static void printAllMazePaths(int sr, int sc, int dr, int dc, String step, boolean[][] visited) {
        // Base case: check boundaries and if the cell is visited
        if (sr < 0 || sc < 0 || sc > dc || sr > dr || visited[sr][sc]) {
            return; // Return once the destination is reached and the path is printed
        }

        // Mark the current cell as visited
        visited[sr][sc] = true;

        // Recursively move in all four possible directions (Down, Left, Right, Up)
        printAllMazePaths(sr + 1, sc, dr, dc, step + "D", visited); // Move Down
        printAllMazePaths(sr, sc - 1, dr, dc, step + "L", visited); // Move Left
        printAllMazePaths(sr, sc + 1, dr, dc, step + "R", visited); // Move Right
        printAllMazePaths(sr - 1, sc, dr, dc, step + "U", visited); // Move Up

        // Mark the current cell as unvisited to allow other paths to use it
        visited[sr][sc] = false;
    }

    /**
     * Approach 2: Get All Maze Paths and Return List
     * 
     * Intuition:
     * - This approach collects all valid paths into an ArrayList instead of printing them directly.
     * - We use the same recursive exploration as in Approach 1, but instead of printing the path when we reach the destination,
     *   we add the path to an ArrayList.
     * - This allows us to gather all paths and return them as a collection.
     * 
     * Time Complexity:
     * - O(4^n) due to the exponential number of recursive calls in the worst case.
     * 
     * Space Complexity:
     * - O(m * n) for the visited array. The recursion stack will also use space proportional to the depth of recursion.
     *   In addition, the space required to store all paths is proportional to the number of unique paths found.
     * 
     * @param sr The starting row index.
     * @param sc The starting column index.
     * @param dr The destination row index.
     * @param dc The destination column index.
     * @param step The current path taken.
     * @param visited The visited cells to prevent revisiting.
     * @return A list of all paths from start to destination.
     */
    public static ArrayList<String> getAllMazePaths(int sr, int sc, int dr, int dc, String step, boolean[][] visited) {
        // Base case: check boundaries and if the cell is visited
        if (sr < 0 || sc < 0 || sc > dc || sr > dr || visited[sr][sc]) {
            return new ArrayList<String>(); // Return empty list if invalid path
        }

        // Base case: if we've reached the destination, add the path to the list
        if (sr == dr && sc == dc) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add(step); // Add the path to the result list
            return arr;
        }

        // Mark the current cell as visited
        visited[sr][sc] = true;

        // Recursively explore all four directions and collect the results
        ArrayList<String> downPaths = getAllMazePaths(sr + 1, sc, dr, dc, step + "D", visited); // Move Down
        ArrayList<String> leftPaths = getAllMazePaths(sr, sc - 1, dr, dc, step + "L", visited); // Move Left
        ArrayList<String> rightPaths = getAllMazePaths(sr, sc + 1, dr, dc, step + "R", visited); // Move Right
        ArrayList<String> upPaths = getAllMazePaths(sr - 1, sc, dr, dc, step + "U", visited); // Move Up

        // Mark the current cell as unvisited to allow other paths to use it
        visited[sr][sc] = false;

        // Combine all the paths found and return them
        downPaths.addAll(leftPaths);
        downPaths.addAll(rightPaths);
        downPaths.addAll(upPaths);

        return rightPaths;
    }

    /**
     * Approach 3: Generate All Maze Paths into a Set
     * 
     * Intuition:
     * - This approach is similar to Approach 2, but instead of returning a list, we use a Set to store the paths.
     * - The Set is used to ensure that all paths stored are unique (in case any path gets duplicated).
     * - We recursively explore each direction, adding valid paths to the set.
     * 
     * Time Complexity:
     * - O(4^n) due to the exponential number of recursive calls in the worst case.
     * 
     * Space Complexity:
     * - O(m * n) for the visited array. The recursion stack also uses space proportional to the depth of recursion.
     *   In addition, the space required to store all paths is proportional to the number of unique paths found.
     * 
     * @param sr The starting row index.
     * @param sc The starting column index.
     * @param dr The destination row index.
     * @param dc The destination column index.
     * @param visited The visited cells to prevent revisiting.
     * @param curr The current path taken.
     * @param set The set to store unique paths.
     */
    public static void generateAllMazePaths(int sr, int sc, int dr, int dc, boolean[][] visited, String curr,
            ArrayList<String> set) {
        // Base case: check boundaries and if the cell is visited
        if (sr < 0 || sc < 0 || sr > dr || sc > dc || visited[sr][sc])
            return; // Return if out of bounds or the cell is already visited

        // Base case: if we've reached the destination, add the current path to the set
        if (sr == dr && sc == dc) {
            set.add(curr); // Add the path to the set
            return;
        }

        // Mark the current cell as visited
        visited[sr][sc] = true;

        // Recursively explore all four directions
        generateAllMazePaths(sr + 1, sc, dr, dc, visited, curr + "D", set); // Move Down
        generateAllMazePaths(sr, sc - 1, dr, dc, visited, curr + "L", set); // Move Left
        generateAllMazePaths(sr, sc + 1, dr, dc, visited, curr + "R", set); // Move Right
        generateAllMazePaths(sr - 1, sc, dr, dc, visited, curr + "U", set); // Move Up

        // Mark the current cell as unvisited to allow other paths to use it
        visited[sr][sc] = false;
    }
}
