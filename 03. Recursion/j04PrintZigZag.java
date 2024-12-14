
/**
 * Problem Statement:
 *    Print a "zigzag" pattern for the given number `n` using recursion.
 *    The pattern consists of printing the number `n`, followed by a recursive call to `n-1`,
 *    then printing the number again, another recursive call to `n-1`, and finally printing the number once more.
 *
 * Input:
 *    - A single integer n (n >= 0).
 *
 * Output:
 *   The zigzag pattern for the number `n`:
 *   Print the number `n`, followed by the zigzag pattern of `n-1`, then the number `n`, 
 *   another zigzag pattern of `n-1`, and finally printing the number `n` again.
 *
 * Example:
 * Input: 2
 * Output:
 *    2
 *    1
 *    1
 *    2
 *    1
 *    1
 *    2
 *
 * Constraints:
 * - The input `n` is a non-negative integer.
 */

import java.util.Scanner;

public class j04PrintZigZag {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the input number
        int n = in.nextInt();

        // Call the recursive function to print the zigzag pattern
        printZigZag(n);

        in.close(); // Close the scanner to release resources
    }

    /**
     * Recursive method to print numbers in a zigzag pattern.
     *
     * Time Complexity: O(2^n), as each recursive call branches into two additional
     * recursive calls.
     * Space Complexity: O(n), due to the recursion stack (one function call for
     * each level).
     *
     * @param n The starting number for the zigzag pattern.
     */
    public static void printZigZag(int n) {
        if (n == 0) { // Base case: If n is 0, stop recursion
            return;
        }

        // Print the current number before the first recursive call
        System.out.println(n);

        // First recursive call to print the zigzag pattern of n-1
        printZigZag(n - 1);

        // Print the current number again after the first recursive call
        System.out.println(n);

        // Second recursive call to print the zigzag pattern of n-1
        printZigZag(n - 1);

        // Print the current number once more after the second recursive call
        System.out.println(n);
    }
}
