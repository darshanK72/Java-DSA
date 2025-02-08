/**
 * Problem Statement:
 * 
 *     You are given a maze of size m x n, where m is the number of rows and n is the number of columns. 
 *     A rat starts from the top-left corner (0, 0) and needs to reach the bottom-right corner (m-1, n-1). 
 *     The rat can make jumps of various sizes in three directions:
 *     - Horizontal (right) moves: "h" followed by the number of horizontal cells the rat can move.
 *     - Vertical (down) moves: "v" followed by the number of vertical cells the rat can move.
 *     - Diagonal moves: "d" followed by the number of diagonal cells the rat can move.
 *     
 *     Your task is to print all possible paths from the start to the destination and also return these paths in a list of strings.
 * 
 * Input:
 *     - Two integers m and n, representing the number of rows and columns in the maze.
 * 
 * Output:
 *     - A list of strings, where each string represents a valid path taken by the rat.
 *     - Print all possible paths with the move type and number of steps in each direction.
 * 
 * Example:
 *     Input:
 *     3 3
 *     Output:
 *     h1v1h1
 *     h1v2
 *     h2v1
 *     h2
 * 
 *     Explanation:
 *     The possible paths are printed as a sequence of horizontal, vertical, or diagonal moves.
 *     - h1v1h1: Move 1 step horizontally, 1 step vertically, then 1 step horizontally.
 *     - h1v2: Move 1 step horizontally, then 2 steps vertically.
 *     - h2v1: Move 2 steps horizontally, then 1 step vertically.
 *     - h2: Move 2 steps horizontally.
 * 
 * Constraints:
 *     1 <= m, n <= 50
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j09GetMazePathsWithJump {

    public static void main(String args[]) {
        // Read input: maze dimensions
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // number of rows
        int n = in.nextInt(); // number of columns

        // Get the maze jump paths
        System.out.println(getMazeJumpPaths(0, 0, m - 1, n - 1));

        // Print the maze jump paths
        printMazeJumpPaths(0, 0, m - 1, n - 1, "");

        in.close();
    }

    /**
     * Aporoach 1 : This method prints all the possible paths in the maze.
     * 
     * Intuition:
     * - The function recursively builds the path using horizontal (h), vertical (v), and diagonal (d) moves.
     * - At each step, we try to move right, down, and diagonally, and recursively call the method to explore further.
     * - The path is built incrementally by appending the move to the existing path.
     * 
     * Time Complexity:
     * - Same as getMazeJumpPaths, as it uses the same logic but prints instead of returning a list.
     * 
     * Space Complexity:
     * - Same as getMazeJumpPaths, as it uses the same recursion stack.
     * 
     * @param sr Starting row index.
     * @param sc Starting column index.
     * @param dr Destination row index.
     * @param dc Destination column index.
     * @param path The current path being built.
     */
    public static void printMazeJumpPaths(int sr, int sc, int dr, int dc, String path) {
        // Base case: If we reached the destination, print the path
        if (sr == dr && sc == dc) {
            System.out.println(path);
            return;
        }

        // Move horizontally
        for (int i = 1; i <= dc - sc; i++) {
            printMazeJumpPaths(sr, sc + i, dr, dc, path + "h" + i);
        }

        // Move vertically
        for (int i = 1; i <= dr - sr; i++) {
            printMazeJumpPaths(sr + i, sc, dr, dc, path + "v" + i);
        }

        // Move diagonally
        for (int i = 1; i <= dr - sr && i <= dc - sc; i++) {
            printMazeJumpPaths(sr + i, sc + i, dr, dc, path + "d" + i);
        }
    }


    /**
     * Approach:
     * 
     * The problem asks to find all possible paths in a maze using horizontal, vertical, and diagonal jumps. 
     * We solve this problem using **recursion** and **backtracking**.
     * 
     * Intuition:
     * - We start at the top-left corner and recursively explore all possible jumps. At each cell, we explore:
     *   - Moving horizontally (right), vertically (down), and diagonally.
     * - Each move is represented by a character ("h", "v", or "d") followed by the number of steps taken.
     * - The base case is when we reach the destination cell (m-1, n-1).
     * - We continue exploring paths until we reach the destination or go out of bounds.
     * 
     * Time Complexity:
     * - The time complexity is exponential in nature, as we are exploring all possible paths recursively.
     * - In the worst case, the time complexity can be O(3^k), where k is the maximum number of jumps allowed.
     * 
     * Space Complexity:
     * - The space complexity is O(k) due to the recursive call stack, where k is the maximum depth of recursion.
     * 
     * @param sr Starting row index.
     * @param sc Starting column index.
     * @param dr Destination row index.
     * @param dc Destination column index.
     * @return A list of strings representing all possible paths.
     */
    public static ArrayList<String> getMazeJumpPaths(int sr, int sc, int dr, int dc) {
        // Base case: If we reached the destination, return a list with an empty string
        if (sr == dr && sc == dc) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }

        ArrayList<String> output = new ArrayList<>();

        // Move horizontally: Try all possible horizontal steps
        for (int ms = 1; ms <= dc - sc; ms++) {
            ArrayList<String> hpaths = getMazeJumpPaths(sr, sc + ms, dr, dc);
            for (String path : hpaths) {
                output.add("h" + ms + path);
            }
        }

        // Move vertically: Try all possible vertical steps
        for (int ms = 1; ms <= dr - sr; ms++) {
            ArrayList<String> vpaths = getMazeJumpPaths(sr + ms, sc, dr, dc);
            for (String path : vpaths) {
                output.add("v" + ms + path);
            }
        }

        // Move diagonally: Try all possible diagonal steps
        for (int ms = 1; ms <= dr - sr && ms <= dc - sc; ms++) {
            ArrayList<String> dpaths = getMazeJumpPaths(sr + ms, sc + ms, dr, dc);
            for (String path : dpaths) {
                output.add("d" + ms + path);
            }
        }

        return output;
    }
}
