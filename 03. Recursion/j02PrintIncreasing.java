
/**
 * Problem Statement:
 *    Print numbers from `1` to `n` in increasing order using recursion.
 *
 * Input:
 *    - A single integer n (n >= 0).
 *
 * Output:
 *    - Numbers printed in increasing order from 1 to n, each on a new line.
 *
 * Example:
 * Input: 
 *    5
 * Output:
 *    1
 *    2
 *    3
 *    4
 *    5
 *
 * Constraints:
 *    - The input `n` is a non-negative integer.
 */

import java.util.Scanner;

public class j02PrintIncreasing {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the input number
        int n = in.nextInt();

        // Call the recursive function to print numbers in increasing order
        printIncreasing(n);

        in.close();
    }

    /**
     * Recursive method to print numbers from 1 to n in increasing order.
     *
     * Time Complexity: O(n), where n is the input number (as the function is called
     * n times).
     * Space Complexity: O(n), due to the recursion stack (each function call
     * occupies space).
     *
     * @param n The ending number to print in increasing order.
     */
    public static void printIncreasing(int n) {
        if (n == 0) { // Base case: If n is 0, stop recursion
            return;
        }

        // Recursive call to print numbers from 1 to n-1
        printIncreasing(n - 1);

        // Print the current number after the recursive call
        System.out.println(n);
    }

    /**
     * Recursion Tree Explanation for n = 5:
     *
     * printIncreasing(5) -> calls printIncreasing(4)
     * |
     * printIncreasing(4) -> calls printIncreasing(3)
     * |
     * printIncreasing(3) -> calls printIncreasing(2)
     * |
     * printIncreasing(2) -> calls printIncreasing(1)
     * |
     * printIncreasing(1) -> calls printIncreasing(0)
     * |
     * printIncreasing(0) -> base case, stops recursion
     *
     * Once the base case (n == 0) is reached, the recursion starts unwinding and
     * prints the values.
     *
     * The output is:
     * printIncreasing(1) prints 1
     * printIncreasing(2) prints 2
     * printIncreasing(3) prints 3
     * printIncreasing(4) prints 4
     * printIncreasing(5) prints 5
     *
     * This shows the process of recursion where the function calls itself until n
     * reaches 0, and then
     * the results are printed in reverse order as the recursion unwinds.
     */
}
