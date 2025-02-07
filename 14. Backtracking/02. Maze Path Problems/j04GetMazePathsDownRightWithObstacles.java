/*-
 * Problem Statement:
 * 
 *     Given a 2D boolean matrix representing a maze, where `true` indicates a valid cell and `false`
 *     represents an obstacle, find all possible paths from the top-left corner (0, 0) to the bottom-right 
 *     corner (m-1, n-1). You can only move right (`R`) or down (`D`).
 * 
 * Input:
 *     - A `boolean[][] maze` where `true` represents a valid path and `false` represents an obstacle.
 * 
 * Output:
 *     - A list of all valid paths represented as strings of "R" (right) and "D" (down).
 * 
 * Example:
 *     Input:
 *     [
 *         [true,  true,  true],
 *         [true,  false, true],
 *         [true,  true,  true]
 *     ]
 * 
 *     Output:
 *     [
 *         "RRDD",
 *         "RDRD",
 *         "RDDR",
 *         "DRRD",
 *         "DRDR"
 *     ]
 * 
 * Explanation:
 *     - The function avoids obstacles (cells with `false`) and finds all valid paths to the destination.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j04GetMazePathsDownRightWithObstacles {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Initialize a sample maze (true = open path, false = obstacle)
        boolean[][] maze = {
                { true, true, true },
                { true, false, true },
                { true, true, true }
        };

        // Approach 1: Collect paths recursively into a list
        System.out.println(getMazePaths1(0, 0, 2, 2, maze));

        // Approach 2: Print paths directly using recursion
        printMazePaths(0, 0, 2, 2, "", maze);

        // Approach 3: Store paths in an external list using recursion
        ArrayList<String> set = new ArrayList<>();
        getMazePaths2(0, 0, 2, 2, "", maze, set);
        System.out.println(set);

        in.close();
    }

    /*-
     * Approach 1: Collect all paths using recursion and return an ArrayList<String>
     * 
     * Intuition:
     * - We use recursion to explore all possible paths from the starting point `(0, 0)` to `(m-1, n-1)`.
     * - At each step, we check if moving right or down is possible and recursively explore that path.
     * - If an obstacle (`false`) is encountered, we stop that path.
     * - When we reach the destination `(m-1, n-1)`, we return a list containing an empty string (base case).
     * - After recursion, we append `"R"` for right moves and `"D"` for down moves to construct full paths.
     * 
     * Explanation:
     * - The function explores both right and down movements at each step.
     * - If we reach the target `(m-1, n-1)`, we add an empty string to indicate a valid path.
     * - If a move is out of bounds or hits an obstacle, we stop exploring that direction.
     * - The final list of paths is constructed recursively by appending "R" and "D" to previous results.
     * 
     * Time Complexity:
     * - O(2^(m+n)) in the worst case (when there are no obstacles).
     * 
     * Space Complexity:
     * - O(m+n) due to the recursion stack depth.
     * 
     * @param sr The current row position.
     * @param sc The current column position.
     * @param dr The destination row.
     * @param dc The destination column.
     * @param maze The boolean matrix representing the maze.
     * @return A list of valid paths from start to destination.
     */
    public static ArrayList<String> getMazePaths1(int sr, int sc, int dr, int dc, boolean[][] maze) {
        if (sr == dr && sc == dc) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(""); // Reached destination, add an empty path
            return arr;
        }

        ArrayList<String> output = new ArrayList<>();

        // If the current cell is an obstacle, return an empty list
        if (!maze[sr][sc]) {
            return output;
        }

        // Move right
        if (sc < dc) {
            for (String path : getMazePaths1(sr, sc + 1, dr, dc, maze)) {
                output.add("R" + path);
            }
        }

        // Move down
        if (sr < dr) {
            for (String path : getMazePaths1(sr + 1, sc, dr, dc, maze)) {
                output.add("D" + path);
            }
        }

        return output;
    }

    /*-
     * Approach 2: Print all paths using recursion (without returning an ArrayList)
     * 
     * Intuition:
     * - Instead of storing paths in a list, we print them directly when reaching the destination.
     * - We stop exploring when an obstacle is encountered.
     * - At each step, explore right (`R`) and down (`D`).
     * 
     * Explanation:
     * - If the position is out of bounds or an obstacle, return immediately.
     * - If we reach the target `(m-1, n-1)`, print the accumulated path.
     * - Recursively explore `R` (right) and `D` (down), appending them to the path.
     * 
     * Time Complexity:
     * - O(2^(m+n)) (same as previous).
     * 
     * Space Complexity:
     * - O(m+n) (due to recursion depth).
     * 
     * @param sr The current row position.
     * @param sc The current column position.
     * @param dr The destination row.
     * @param dc The destination column.
     * @param path The accumulated path.
     * @param maze The boolean matrix representing the maze.
     */
    public static void printMazePaths(int sr, int sc, int dr, int dc, String path, boolean[][] maze) {
        if (sr > dr || sc > dc || !maze[sr][sc]) {
            return;
        }
        if (sr == dr && sc == dc) {
            System.out.println(path);
            return;
        }
        printMazePaths(sr, sc + 1, dr, dc, path + "R", maze);
        printMazePaths(sr + 1, sc, dr, dc, path + "D", maze);
    }

    /*-
     * Approach 3: Store paths in an external ArrayList (Backtracking)
     * 
     * Intuition:
     * - Similar to `printMazePaths`, but stores paths in an external list instead of printing them.
     * - Uses backtracking: after exploring, removes the last move.
     * 
     * Explanation:
     * - Recursively explores right (`R`) and down (`D`).
     * - If the target `(m-1, n-1)` is reached, add the path to the external list.
     * - If an obstacle is encountered, stop that path.
     * - Backtracking: After a recursive call, the last character is removed.
     * 
     * Time Complexity:
     * - O(2^(m+n)) (same as previous).
     * 
     * Space Complexity:
     * - O(m+n) (due to recursion).
     * 
     * @param sr The current row position.
     * @param sc The current column position.
     * @param dr The destination row.
     * @param dc The destination column.
     * @param path The accumulated path.
     * @param maze The boolean matrix representing the maze.
     * @param set The list to store valid paths.
     */
    public static void getMazePaths2(int sr, int sc, int dr, int dc, String path, boolean[][] maze, ArrayList<String> set) {
        if (sr > dr || sc > dc || !maze[sr][sc]) {
            return;
        }
        if (sr == dr && sc == dc) {
            set.add(path);
            return;
        }
        getMazePaths2(sr, sc + 1, dr, dc, path + "R", maze, set);
        getMazePaths2(sr + 1, sc, dr, dc, path + "D", maze, set);
    }
}
