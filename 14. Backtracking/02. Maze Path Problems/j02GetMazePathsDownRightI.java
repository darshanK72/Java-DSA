/*-
 * Problem Statement:
 * 
 *     Given a maze of size `m x n`, you are initially at the top-left corner (0, 0).
 *     You need to find all the possible paths to reach the bottom-right corner (m-1, n-1).
 *     In each step, you can move either down or right.
 *     Print all the paths and also return them as a list of strings.
 * 
 * Input:
 *     - Two integers `m` and `n` representing the number of rows and columns in the maze.
 * 
 * Output:
 *     - Print all possible paths to reach the destination.
 *     - Return an ArrayList<String> containing all possible paths where each path is a string
 *       of 'R' (right) and 'D' (down) representing the moves.
 * 
 * Example:
 *     Input:
 *     3 3
 * 
 *     Output:
 *     RRD
 *     RDR
 *     DRR
 *     [RRD, RDR, DRR]
 * 
 *     Explanation:
 *     - There are three distinct ways to reach the bottom-right corner from the top-left corner:
 *       {RRD, RDR, DRR}.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j02GetMazePathsDownRightI {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // Input: number of rows
        int n = in.nextInt(); // Input: number of columns
        System.out.println(getMazePaths(0, 0, m - 1, n - 1)); // Compute and return maze paths
        printMazePaths(0, 0, m - 1, n - 1, ""); // Print paths using recursive approach

        ArrayList<String> out = new ArrayList<>();
        getMazePaths2(0, 0, m - 1, n - 1, "", out);
        System.out.println(out);
        in.close();
    }

    /*-
     * Approach: Recursive Printing of Maze Paths
     * 
     * Intuition:
     * - At each step, we have two possible directions to move: right (R) or down (D).
     * - We recursively explore all possible paths by moving right or down, until we reach
     *   the bottom-right corner of the maze.
     * - If we exceed the bounds of the maze, we stop that recursive path.
     * - The function prints all the valid paths.
     * 
     * Explanation:
     * - If the current position (`sr`, `sc`) goes out of bounds, we stop the recursion.
     * - If the destination (`dr`, `dc`) is reached, we print the accumulated path.
     * - We make two recursive calls: one by moving right (increment column) and one by moving down (increment row).
     * 
     * Time Complexity:
     * - O(2^(m + n)) since each step has two choices (right or down) and we recursively explore all paths.
     * 
     * Space Complexity:
     * - O(m + n) due to the recursion stack.
     * 
     * @param sr The current row position.
     * @param sc The current column position.
     * @param dr The destination row position.
     * @param dc The destination column position.
     * @param step The current accumulated path.
     */
    public static void printMazePaths(int sr, int sc, int dr, int dc, String step) {
        if (sc > dc || sr > dr) {
            return; // Stop if out of bounds
        }
        if (sr == dr && sc == dc) {
            System.out.println(step); // Print path when destination is reached
            return;
        }

        // Move right
        printMazePaths(sr, sc + 1, dr, dc, step + "R");
        // Move down
        printMazePaths(sr + 1, sc, dr, dc, step + "D");
    }

    /*-
     * Approach: Recursive Collection of Maze Paths
     * 
     * Intuition:
     * - Instead of printing the paths, we collect them in an ArrayList and return the list.
     * - Similar to the previous approach, we recursively explore all possible paths,
     *   but instead of printing, we add the current path to the result list.
     * - The function returns all valid paths as a list of strings.
     * 
     * Explanation:
     * - If the current position is the destination, return a list containing an empty string (no further moves).
     * - If the destination hasn't been reached, recursively explore the paths moving right or down.
     * - Prepend "R" or "D" to the paths returned from the recursive calls.
     * - Return the combined list of paths from both directions.
     * 
     * Time Complexity:
     * - O(2^(m + n)) since there are two recursive calls at each step and we explore all paths.
     * 
     * Space Complexity:
     * - O(2^(m + n)) for storing all paths.
     * - O(m + n) for the recursion stack.
     * 
     * @param sr The current row position.
     * @param sc The current column position.
     * @param dr The destination row position.
     * @param dc The destination column position.
     * @return An ArrayList containing all valid paths.
     */
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(""); // Reached destination, return empty string to indicate no more moves
            return arr;
        }

        ArrayList<String> rpaths = new ArrayList<>();
        ArrayList<String> dpaths = new ArrayList<>();

        // Explore the right move if within bounds
        if (sc < dc) {
            rpaths = getMazePaths(sr, sc + 1, dr, dc);
        }
        // Explore the down move if within bounds
        if (sr < dr) {
            dpaths = getMazePaths(sr + 1, sc, dr, dc);
        }

        ArrayList<String> output = new ArrayList<>();

        // Add "R" for right paths
        for (String path : rpaths) {
            output.add("R" + path);
        }
        // Add "D" for down paths
        for (String path : dpaths) {
            output.add("D" + path);
        }

        return output;
    }

   /*-
    * Approach: Recursive Collection with ArrayList (Modified)
    * 
    * Intuition:
    * - This method is similar to the previous approach where we recursively explore all possible
    *   paths from the top-left corner to the bottom-right corner of the maze.
    * - Instead of returning the list of paths, we pass the list (`set`) as a parameter to accumulate
    *   the paths directly. This avoids creating multiple ArrayLists and can be more efficient.
    * - We continue moving right ("R") and down ("D") until we reach the destination, and then add the
    *   current path (`step`) to the passed `set`.
    * 
    * Explanation:
    * - If the current position (`sr`, `sc`) goes out of bounds (i.e., beyond the destination), we stop the recursion.
    * - If we have reached the destination (`dr`, `dc`), we add the current path string to the result list (`set`).
    * - We explore two recursive directions: move right (increment `sc`), and move down (increment `sr`).
    * - The list `set` collects all valid paths and is passed down through recursive calls.
    * 
    * Time Complexity:
    * - O(2^(m + n)) since at each step we have two options (right or down) and we explore all possible paths.
    * 
    * Space Complexity:
    * - O(2^(m + n)) for storing all the paths in the result list (`set`).
    * - O(m + n) for the recursion stack.
    * 
    * @param sr The current row position.
    * @param sc The current column position.
    * @param dr The destination row position.
    * @param dc The destination column position.
    * @param step The current accumulated path.
    * @param set The ArrayList where all valid paths are collected.
    */
    public static void getMazePaths2(int sr, int sc, int dr, int dc, String step, ArrayList<String> set) {
        if (sc > dc || sr > dr) {
            return; // Stop if out of bounds
        }
        if (sr == dr && sc == dc) {
            set.add(step); // Add the current path to the result list when destination is reached
            return;
        }

        // Move right
        getMazePaths2(sr, sc + 1, dr, dc, step + "R", set);
        // Move down
        getMazePaths2(sr + 1, sc, dr, dc, step + "D", set);
    }
}
