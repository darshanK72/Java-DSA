/**
 * Problem Statement:
 *     You are given two integers `a` and `b`. Your task is to calculate their sum without using the `+` operator.
 *     You are only allowed to use bitwise operators to solve the problem.
 * 
 * Input:
 *     - Two integers `a` and `b`.
 * 
 * Output:
 *     - The sum of `a` and `b` without using the `+` operator.
 * 
 * Example:
 *     Input:
 *     3 5
 *     Output:
 *     8
 *     
 *     Explanation:
 *     The sum of 3 and 5 is 8, and we calculate it without using the `+` operator by using bitwise operations.
 */

import java.util.Scanner;

public class j04SumWithoutPlus {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(); // Input: first integer
        int b = in.nextInt(); // Input: second integer

        // Calculate the sum using bitwise operations
        System.out.println(getSum(a, b)); // Print the sum

        // Close the scanner
        in.close();
    }

    /**
     * Approach: Using Bitwise Operations (Carry and XOR)
     * 
     * Intuition:
     * - The sum of two integers can be broken down using bitwise operations:
     *   1. XOR (`^`) gives the sum of bits without considering the carry.
     *   2. AND (`&`) followed by left shift (`<<`) gives the carry, which needs to be added to the sum.
     * - The process continues until there is no carry left.
     * 
     * Time Complexity:
     * - O(1), since we are using bitwise operations and the number of operations is constant.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of space.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The sum of `a` and `b` without using the `+` operator.
     */
    public static int getSum(int a, int b) {
        // Keep calculating until there is no carry
        while (b != 0) {
            int carry = (a & b) << 1; // Calculate the carry
            a = a ^ b; // Calculate the sum without carry
            b = carry; // Update `b` with the carry to add it to the sum
        }
        return a; // Return the result after all carry bits have been added
    }
}
