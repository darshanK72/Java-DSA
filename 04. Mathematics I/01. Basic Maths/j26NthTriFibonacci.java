/**
 * Problem Statement:
 * 
 *     Given an integer `n`, return the nth number in the Tribonacci sequence.
 *     The Tribonacci sequence is similar to the Fibonacci sequence, but instead of each number being the sum of the previous two numbers, each number is the sum of the previous three.
 *     The sequence starts as follows:
 *     T(0) = 0, T(1) = 1, T(2) = 1, and for n >= 3: T(n) = T(n-1) + T(n-2) + T(n-3).
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 37), representing the position of the Tribonacci number.
 * 
 * Output:
 *     - The nth Tribonacci number.
 * 
 * Example:
 *     Input:
 *     4
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The Tribonacci sequence is: [0, 1, 1, 2, 4, ...]
 *     T(4) = 4.
 */

import java.util.Scanner;

public class j26NthTriFibonacci {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: integer representing the position in the Tribonacci sequence

        // Call your solution
        System.out.println("Tribonacci Number: " + tribonacci(n));

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Iterative Solution
     * 
     * Intuition:
     * - The Tribonacci sequence can be calculated iteratively by keeping track of the last three numbers in the sequence.
     * - For n >= 3, we can calculate each Tribonacci number by summing the previous three numbers.
     * 
     * Time Complexity:
     * - O(n), where `n` is the position in the Tribonacci sequence.
     * 
     * Space Complexity:
     * - O(1), as we are using only a constant amount of space for variables.
     * 
     * @param n The position of the Tribonacci number.
     * @return The nth Tribonacci number.
     */
    public static int tribonacci(int n) {
        // Base cases for the first three numbers
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;

        // Variables to store the last three numbers
        int a = 0, b = 1, c = 1, d = a + b + c;

        // Loop to calculate the nth Tribonacci number
        for (int i = 3; i <= n; i++) {
            a = b;
            b = c;
            c = d;
            d = a + b + c;
        }

        return d;
    }

    /**
     * Approach 2: Recursive Solution (Naive)
     * 
     * Intuition:
     * - A simple recursive function that directly implements the Tribonacci recurrence relation.
     * - However, it is not efficient for larger values of n as it recalculates the same subproblems multiple times.
     * 
     * Time Complexity:
     * - O(3^n), due to the repeated recalculation of the same subproblems.
     * 
     * Space Complexity:
     * - O(n), due to the recursion depth.
     * 
     * @param n The position of the Tribonacci number.
     * @return The nth Tribonacci number.
     */
    public static int tribonacciRecursive(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        return tribonacciRecursive(n - 1) + tribonacciRecursive(n - 2) + tribonacciRecursive(n - 3);
    }
}
