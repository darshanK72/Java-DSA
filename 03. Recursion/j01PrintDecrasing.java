
/**
 * Problem Statement:
 *    Print numbers from `n` down to 1 in decreasing order using recursion.
 *
 * Input:
 *    - A single integer n (n >= 0).
 *
 * Output:
 *    - Numbers printed in decreasing order from n to 1, each on a new line.
 *
 * Example:
 * Input: 
 *    5
 * Output:
 *    5
 *    4
 *    3
 *    2
 *    1
 *
 * Constraints:
 *    - The input `n` is a non-negative integer.
 */

import java.util.Scanner;

public class j01PrintDecrasing {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the input number
        int n = in.nextInt();

        // Call the recursive function to print numbers in decreasing order
        printDecreasing(n);

        in.close();
    }

    /**
     * Recursive method to print numbers from n down to 1.
     *
     * Time Complexity: O(n), where n is the input number (as the function is called
     * n times).
     * Space Complexity: O(n), due to the recursion stack (each function call
     * occupies space).
     *
     * @param n The starting number to print in decreasing order.
     */
    public static void printDecreasing(int n) {
        if (n == 0) { // Base case: If n is 0, stop recursion
            return;
        }

        // Print the current number
        System.out.println(n);

        // Recursively call the function with n-1
        printDecreasing(n - 1);
    }

    /**
     * Recursion Tree Explanation for n = 5:
     *
     * printDecreasing(5) -> prints 5
     * |
     * printDecreasing(4) -> prints 4
     * |
     * printDecreasing(3) -> prints 3
     * |
     * printDecreasing(2) -> prints 2
     * |
     * printDecreasing(1) -> prints 1
     * |
     * printDecreasing(0) -> base case, stops recursion
     *
     * The base case occurs when n becomes 0. After each print, the function calls
     * itself with n-1, and the process repeats until n reaches 0.
     * 
     * This is how recursion unfolds:
     * - printDecreasing(5) prints 5, then calls printDecreasing(4).
     * - printDecreasing(4) prints 4, then calls printDecreasing(3), and so on.
     * - Once n reaches 0, the base case is hit, and the recursion starts unwinding.
     * 
     * Each function call corresponds to a level in the tree, and the function calls
     * itself at each step.
     */
}
