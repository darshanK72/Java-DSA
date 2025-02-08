/**
 * Problem Statement:
 * 
 *     Given a staircase with `n` steps, you can climb 1, 2, or 3 steps at a time.
 *     Your task is to print all possible ways to climb the staircase and return them
 *     as a list.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the number of steps.
 * 
 * Output:
 *     - Print all possible paths as strings where each digit represents the step count.
 *     - Return an ArrayList<String> containing all paths.
 * 
 * Example:
 *     Input:
 *     3
 * 
 *     Output:
 *     111
 *     12
 *     21
 *     3
 *     [111, 12, 21, 3]
 * 
 *     Explanation:
 *     - There are four possible ways to climb 3 steps: {1-1-1, 1-2, 2-1, 3}.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j01GetStairPaths {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of steps
        System.out.println(getStairPaths(n)); // Compute and return stair paths
        printStairPaths(n, ""); // Print paths using recursive approach
        in.close();
    }

    /**
     * Approach: Recursive Printing of Staircase Paths
     * 
     * Intuition:
     * - We can solve this problem using recursion by breaking it into smaller subproblems.
     * - At any given step, we have three choices: take 1 step, take 2 steps, or take 3 steps.
     * - We recursively explore each of these choices until we reach the base case.
     * - The function prints all valid paths using recursion.
     * 
     * Explanation:
     * - If `n == 0`, it means we have reached the exact number of steps required, 
     *   so we print the accumulated path.
     * - If `n < 0`, it means we have exceeded the number of steps, so we return without doing anything.
     * - We make recursive calls for `n-1`, `n-2`, and `n-3` while appending `"1"`, `"2"`, and `"3"` 
     *   to the path respectively.
     * 
     * Time Complexity:
     * - O(3^n) in the worst case since each step branches into three recursive calls.
     * 
     * Space Complexity:
     * - O(n) due to recursive function call stack.
     * 
     * @param n The remaining number of steps.
     * @param path The current path formed.
     */
    public static void printStairPaths(int n, String path) {
        if (n == 0) {
            System.out.println(path);
            return;
        }
        if (n < 0) {
            return;
        }

        printStairPaths(n - 1, path + "1");
        printStairPaths(n - 2, path + "2");
        printStairPaths(n - 3, path + "3");
    }

    /**
     * Approach: Recursive Collection of Staircase Paths
     * 
     * Intuition:
     * - Instead of printing the paths directly, we return a list of all possible paths.
     * - Like before, we recursively explore taking 1, 2, or 3 steps.
     * - For each recursive call, we prepend `"1"`, `"2"`, or `"3"` to the paths
     *   returned from the smaller subproblem.
     * - The final result is a list of all valid paths.
     * 
     * Explanation:
     * - Base case 1: If `n == 0`, we have found a valid path, so we return a list containing an empty string.
     * - Base case 2: If `n < 0`, it means we have taken too many steps, so we return an empty list.
     * - We recursively call `getStairPaths(n-1)`, `getStairPaths(n-2)`, and `getStairPaths(n-3)`,
     *   storing results in `arr1`, `arr2`, and `arr3` respectively.
     * - We then append `"1"`, `"2"`, and `"3"` to each path retrieved from `arr1`, `arr2`, and `arr3`.
     * - Finally, we return the list of all generated paths.
     * 
     * Time Complexity:
     * - O(3^n) since each step branches into three recursive calls.
     * 
     * Space Complexity:
     * - O(3^n) to store all possible paths.
     * - O(n) for recursive call stack.
     * 
     * @param n The remaining number of steps.
     * @return An ArrayList containing all valid paths.
     */
    public static ArrayList<String> getStairPaths(int n) {
        if (n == 0) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }
        if (n < 0) {
            return new ArrayList<>();
        }

        ArrayList<String> arr1 = getStairPaths(n - 1);
        ArrayList<String> arr2 = getStairPaths(n - 2);
        ArrayList<String> arr3 = getStairPaths(n - 3);

        ArrayList<String> output = new ArrayList<>();

        for (String s : arr1) {
            output.add("1" + s);
        }
        for (String s : arr2) {
            output.add("2" + s);
        }
        for (String s : arr3) {
            output.add("3" + s);
        }

        return output;
    }
}
