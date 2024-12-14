import java.util.Scanner;

/**
 * Problem Statement:
 * Print numbers from `n` down to `1` in decreasing order and then back up to
 * `n` in increasing order using recursion.
 *
 * Input:
 * - A single integer n (n >= 0).
 *
 * Output:
 * - Numbers printed in the sequence: decreasing order from `n` to `1` and then
 * increasing back to `n`.
 *
 * Example:
 * Input: 3
 * Output:
 * 3
 * 2
 * 1
 * 1
 * 2
 * 3
 *
 * Constraints:
 * - The input `n` is a non-negative integer.
 */

public class j03PrintDecreasingIncreasing {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the input number
        int n = in.nextInt();

        // Call the recursive function to print the pattern
        printDecreasingIncreasing(n);

        in.close(); // Close the scanner to release resources
    }

    /**
     * Recursive method to print numbers in decreasing and increasing order.
     *
     * Time Complexity: O(n), where n is the input number (as the function is called
     * n times).
     * Space Complexity: O(n), due to the recursion stack (each function call
     * occupies space).
     *
     * @param n The starting number for the pattern.
     */
    public static void printDecreasingIncreasing(int n) {
        if (n == 0) { // Base case: If n is 0, stop recursion
            return;
        }

        // Print the current number (decreasing order)
        System.out.println(n);

        // Recursive call with n-1
        printDecreasingIncreasing(n - 1);

        // Print the current number again (increasing order)
        System.out.println(n);
    }

    /**
     * Recursion Tree Explanation for n = 3:
     *
     * printDecreasingIncreasing(3) -> calls printDecreasingIncreasing(2)
     * |
     * printDecreasingIncreasing(2) -> calls printDecreasingIncreasing(1)
     * |
     * printDecreasingIncreasing(1) -> calls printDecreasingIncreasing(0)
     * |
     * printDecreasingIncreasing(0) -> base case, stops recursion
     *
     * Once the base case (n == 0) is reached, the recursion starts unwinding and
     * prints the values.
     *
     * The output is:
     * printDecreasingIncreasing(1) prints 1, then prints 1 again
     * printDecreasingIncreasing(2) prints 2, then prints 2 again
     * printDecreasingIncreasing(3) prints 3, then prints 3 again
     *
     * This shows the process of recursion where the function calls itself until n
     * reaches 0, and then
     * the results are printed in reverse order as the recursion unwinds.
     */
}
