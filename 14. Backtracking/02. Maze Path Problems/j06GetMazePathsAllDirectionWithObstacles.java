/**
 * Problem Statement:
 * 
 *     You are given a maze represented as a 2D grid of size `m x n`. Each cell in the maze is either a 
 *     free space or an obstacle:
 * 
 *     - `false` represents a free space (you can move through it).
 *     - `true` represents an obstacle (you cannot move through it).
 * 
 *     Your task is to find all the possible paths from the top-left corner (0, 0) to the bottom-right 
 *     corner (m-1, n-1), moving only through free spaces.
 *     You are allowed to move in the following directions:
 *     - Down (D): Move to the cell below.
 *     - Up (U): Move to the cell above.
 *     - Left (L): Move to the cell on the left.
 *     - Right (R): Move to the cell on the right.
 * 
 *     You are not allowed to move outside the bounds of the maze or through cells that are blocked by obstacles.
 * 
 * Input:
 *     - A 2D boolean array `maze[][]` where each element represents a cell in the maze. 
 *       - `false` indicates a free space.
 *       - `true` indicates an obstacle.
 * 
 *     - You start at the top-left corner `(0, 0)` and want to reach the bottom-right corner `(m-1, n-1)`.
 * 
 * Example:
 *     Input:
 *     maze = {
 *         { false, true, true, true },
 *         { false, false, true, false },
 *         { true, false, false, false },
 *         { true, true, false, false }
 *     }
 * 
 *     Output:
 *     - Print all paths: 
 *         DDDR
 *         DDRR
 *     - Return paths as List:
 *         ["DDRR", "DDDR"]
 *     - Return paths as Set:
 *         {"DDRR", "DDDR"}
 * 
 *     Explanation:
 *     The maze allows several paths from the top-left to the bottom-right corner. The given paths are valid as they avoid obstacles, and you can move freely within the grid.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j06GetMazePathsAllDirectionWithObstacles {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = 4, n = 4;
        boolean[][] maze = {
                { false, true, true, true },
                { false, false, true, false },
                { true, false, false, false },
                { true, true, false, false }
        };

        printAllMazePaths(maze, 0, 0, m - 1, n - 1, "", new boolean[m][n]);

        System.out.println(getAllMazePaths(maze, 0, 0, m - 1, n - 1, "", new boolean[m][n]));

        ArrayList<String> set = new ArrayList<String>();
        generateAllMazePaths(maze, 0, 0, m - 1, n - 1, new boolean[m][n], "", set);
        System.out.println(set);

        in.close();
    }

    /**
     * Approach 1: Print All Maze Paths (Backtracking)
     * 
     * Intuition:
     * - The goal is to find all paths from the top-left corner (0, 0) to the bottom-right corner (m-1, n-1).
     * - If a cell is blocked (true in maze), we cannot pass through it.
     * - We use backtracking to explore each direction (Down, Left, Right, Up) recursively.
     * - If we reach the destination, we print the path.
     * - We also use a visited array to prevent revisiting cells in a single path.
     * 
     * Time Complexity:
     * - O(4^n) in the worst case as we recursively explore all four directions.
     * 
     * Space Complexity:
     * - O(m * n) for the visited array and recursion stack depth.
     * 
     * @param maze The maze grid where false indicates free space and true indicates obstacles.
     * @param sr The starting row index.
     * @param sc The starting column index.
     * @param dr The destination row index.
     * @param dc The destination column index.
     * @param step The current path taken.
     * @param visited The visited cells to prevent revisiting.
     */
    public static void printAllMazePaths(boolean[][] maze, int sr, int sc, int dr, int dc, String step,
            boolean[][] visited) {
        // Base case: if the current cell is out of bounds or blocked or already
        // visited, return
        if (sr < 0 || sc < 0 || sc > dc || sr > dr || maze[sr][sc] || visited[sr][sc]) {
            return;
        }

        // Base case: if we've reached the destination, print the path
        if (sr == dr && sc == dc) {
            System.out.println(step);
        }

        // Mark the current cell as visited
        visited[sr][sc] = true;

        // Recursively explore all four directions (Down, Left, Right, Up)
        printAllMazePaths(maze, sr + 1, sc, dr, dc, step + "D", visited); // Move Down
        printAllMazePaths(maze, sr, sc - 1, dr, dc, step + "L", visited); // Move Left
        printAllMazePaths(maze, sr, sc + 1, dr, dc, step + "R", visited); // Move Right
        printAllMazePaths(maze, sr - 1, sc, dr, dc, step + "U", visited); // Move Up

        // Unmark the current cell as visited for other possible paths
        visited[sr][sc] = false;
    }

    /**
     * Approach 2: Return All Maze Paths as a List (Backtracking)
     * 
     * Intuition:
     * - Similar to Approach 1, but instead of printing the paths, we store them in an ArrayList.
     * - The list is returned once all paths are collected.
     * - The key idea remains recursive exploration of all four directions.
     * 
     * Time Complexity:
     * - O(4^n) as we explore all possible paths in a backtracking manner.
     * 
     * Space Complexity:
     * - O(m * n) for the visited array and recursion stack. Additionally, we store all the paths, 
     *   which may take O(4^n) space.
     * 
     * @param maze The maze grid.
     * @param sr The starting row index.
     * @param sc The starting column index.
     * @param dr The destination row index.
     * @param dc The destination column index.
     * @param step The current path taken.
     * @param visited The visited cells to prevent revisiting.
     * @return A list of all valid paths.
     */
    public static ArrayList<String> getAllMazePaths(boolean[][] maze, int sr, int sc, int dr, int dc, String step,
            boolean[][] visited) {
        // Base case: if the current cell is out of bounds or blocked or already
        // visited, return empty list
        if (sr < 0 || sc < 0 || sc > dc || sr > dr || maze[sr][sc] || visited[sr][sc]) {
            return new ArrayList<String>();
        }

        // Base case: if we've reached the destination, return the path
        if (sr == dr && sc == dc) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add(step);
            return arr;
        }

        // Mark the current cell as visited
        visited[sr][sc] = true;

        // Explore all four directions and gather results
        ArrayList<String> rightPaths = getAllMazePaths(maze, sr + 1, sc, dr, dc, step + "D", visited); // Move Down
        ArrayList<String> leftPaths = getAllMazePaths(maze, sr, sc - 1, dr, dc, step + "L", visited); // Move Left
        ArrayList<String> downPaths = getAllMazePaths(maze, sr, sc + 1, dr, dc, step + "R", visited); // Move Right
        ArrayList<String> upPaths = getAllMazePaths(maze, sr - 1, sc, dr, dc, step + "U", visited); // Move Up

        // Unmark the current cell as visited for other possible paths
        visited[sr][sc] = false;

        // Combine all the paths and return
        rightPaths.addAll(leftPaths);
        rightPaths.addAll(downPaths);
        rightPaths.addAll(upPaths);

        return rightPaths;
    }

    /**
     * Approach 3: Generate All Maze Paths into a Set (Backtracking)
     * 
     * Intuition:
     * - This approach is similar to Approach 2, but we store paths in a Set to avoid duplicate paths.
     * - The Set guarantees that only unique paths are stored, in case there are any redundant paths.
     * 
     * Time Complexity:
     * - O(4^n) due to the recursive exploration of all paths.
     * 
     * Space Complexity:
     * - O(m * n) for the visited array and recursion stack. The Set will take O(4^n) space to store unique paths.
     * 
     * @param maze The maze grid.
     * @param sr The starting row index.
     * @param sc The starting column index.
     * @param dr The destination row index.
     * @param dc The destination column index.
     * @param visited The visited cells to prevent revisiting.
     * @param curr The current path taken.
     * @param set The set to store unique paths.
     */
    public static void generateAllMazePaths(boolean[][] maze, int sr, int sc, int dr, int dc, boolean[][] visited,
            String curr, ArrayList<String> set) {
        // Base case: if the current cell is out of bounds or blocked or already
        // visited, return
        if (sr < 0 || sc < 0 || sr > dr || sc > dc || maze[sr][sc] || visited[sr][sc])
            return;

        // Base case: if we've reached the destination, add the path to the set
        if (sr == dr && sc == dc) {
            set.add(curr);
            return;
        }

        // Mark the current cell as visited
        visited[sr][sc] = true;

        // Recursively explore all four directions
        generateAllMazePaths(maze, sr + 1, sc, dr, dc, visited, curr + "D", set); // Move Down
        generateAllMazePaths(maze, sr, sc - 1, dr, dc, visited, curr + "L", set); // Move Left
        generateAllMazePaths(maze, sr, sc + 1, dr, dc, visited, curr + "R", set); // Move Right
        generateAllMazePaths(maze, sr - 1, sc, dr, dc, visited, curr + "U", set); // Move Up

        // Unmark the current cell as visited for other possible paths
        visited[sr][sc] = false;
    }
}
