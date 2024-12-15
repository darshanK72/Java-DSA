/**
 * Problem Statement:
 * 
 *     Given two integers `a` and `b`, swap their values using bitwise XOR without using any temporary variables.
 *     You are required to print the values of `a` and `b` before and after the swap.
 * 
 * Input:
 *     - Two integers `a` and `b` (1 <= a, b <= 10^5), the numbers to be swapped.
 * 
 * Output:
 *     - Print the values of `a` and `b` before and after swapping.
 * 
 * Example:
 *     Input:
 *         5 7
 *     Output:
 *         Before Swapping : a = 5, b = 7
 *         After Swapping : a = 7, b = 5
 * 
 *     Explanation:
 *         The values of `a` and `b` are swapped using the XOR operation without using any temporary variable.
 */

import java.util.Scanner;

public class j12SwapTwoNumbers {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(); // Input: first number a
        int b = in.nextInt(); // Input: second number b
        swapNums(a, b); // Call to swap the numbers and display the result
        in.close();
    }

    /**
     * Approach: Swap Using XOR
     * 
     * Intuition:
     * - The XOR operation can be used to swap two numbers without using any additional memory.
     * - By applying XOR between the numbers, we can swap the values in-place.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a constant number of XOR operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space (no additional variables).
     * 
     * @param a The first number to swap.
     * @param b The second number to swap.
     */
    public static void swapNums(int a, int b) {
        System.out.println("Before Swapping : a = " + a + ", b = " + b);
        // Swapping using XOR
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("After Swapping : a = " + a + ", b = " + b);
    }
}
