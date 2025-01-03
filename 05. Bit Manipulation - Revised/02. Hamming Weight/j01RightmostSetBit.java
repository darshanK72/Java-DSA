/**
 * Problem Statement:
 * 
 *     Given an integer `n`, find the index of the rightmost set bit (1-bit) in its binary representation.
 *     The rightmost set bit is the bit that is the lowest in the binary number, starting from the rightmost 
 *     position. If no set bit is found, return -1.
 * 
 *     Additionally, find the value of the rightmost set bit in `n`.
 * 
 * Input:
 *     - A positive integer `n` (1 <= n <= 10^9).
 * 
 * Output:
 *     - The value of the rightmost set bit.
 *     - The index of the rightmost set bit.
 * 
 * Example:
 *     Input:
 *     18
 *     Output:
 *     2
 *     1
 * 
 *     Input:
 *     12
 *     Output:
 *     4
 *     2
 * 
 * Explanation:
 *     In the first example, the binary representation of 18 is `10010`. 
 *     The rightmost set bit is at index 1 (counting from 0), and the value of that bit is 2.
 *     In the second example, the binary representation of 12 is `1100`. 
 *     The rightmost set bit is at index 2 (counting from 0), and the value of that bit is 4.
 */

import java.util.Scanner;

public class j01RightmostSetBit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(log2(rightMostSetBitMethod(n)));
        System.out.println(rightmostSetBitIndexNive(n));
        System.out.println(log2(rightmostSetBitEfficient(n)));

        in.close();
    }

    /**
     * Approach 1: Using Integer.lowestOneBit
     * 
     * Intuition:
     * - The method `Integer.lowestOneBit(n)` returns the value of the rightmost set bit in `n`.
     * 
     * Time Complexity:
     * - O(1), as this operation is directly provided by the Java standard library.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The value of the rightmost set bit.
     */
    public static int rightMostSetBitMethod(int n) {
        return Integer.lowestOneBit(n);
    }

    /**
     * Approach 2: Brute Force (Iterative Method)
     * 
     * Intuition:
     * - This method iterates through all 32 bits and checks if the bit at position `i` is set (1). 
     * - If the bit is set, it returns the index `i` of that bit.
     * 
     * Time Complexity:
     * - O(32) = O(1), as there are at most 32 bits to check.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The index of the rightmost set bit.
     */
    public static int rightmostSetBitIndexNive(int n) {
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0)
                return i;
        }
        return -1;
    }

    /**
     * Approach 3: Efficient Method Using Two's Complement
     * 
     * Intuition:
     * - By performing a bitwise AND operation with the two's complement of `n`, we isolate the rightmost set bit.
     * - The two's complement of a number `n` is `-n`, and the result of `n & -n` isolates the rightmost set bit.
     * 
     * Time Complexity:
     * - O(1), as this operation is performed in constant time.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The value of the rightmost set bit.
     */
    public static int rightmostSetBitEfficient(int n) {
        // Performing bitwise AND with two's complement to isolate rightmost set bit
        int rightmostSetBit = n & -n; // n & ~(n - 1)
        return rightmostSetBit;
    }

    /**
     * Utility Method: Logarithm Base 2
     * 
     * Intuition:
     * - This method calculates the logarithm of `n` to the base 2, which effectively returns the index of the highest set bit.
     * 
     * Time Complexity:
     * - O(1), as the logarithmic calculation is a constant-time operation.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The logarithm of `n` to the base 2.
     */
    public static int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}
