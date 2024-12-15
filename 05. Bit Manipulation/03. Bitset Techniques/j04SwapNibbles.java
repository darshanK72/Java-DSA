/**
 * Problem Statement:
 * 
 *     Given a 32-bit integer `n`, swap the nibbles (4-bit blocks) of the integer. Specifically, 
 *     swap the 4 least significant bits (right nibble) with the next 4 bits (left nibble), 
 *     and return the modified number. The result should preserve the swapped nibbles' position.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 2^32 - 1), representing the 32-bit number.
 * 
 * Output:
 *     - An integer that represents the result after swapping the nibbles of `n`.
 * 
 * Example:
 *     Input:
 *     23
 *     Output:
 *     368
 * 
 *     Explanation:
 *     The binary representation of 23 is `00010111`. After swapping the nibbles (4-bit blocks), the result is `00010111` becoming `11101000`, which is 368 in decimal.
 * 
 */

import java.util.Scanner;

public class j04SwapNibbles {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to swap nibbles

        // Output: Original binary, swapped nibbles
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(swapNibbles(n))); // Swapping nibbles

        in.close();
    }

    /**
     * Approach: Bitwise Swap of Nibbles
     * 
     * Intuition:
     * - The number is divided into 4-bit blocks, and each 4-bit block is referred to as a nibble. 
     *   The idea is to swap these nibbles using bitwise operations.
     * - First, extract the left and right nibbles. Then, shift the left nibble by 4 positions to the 
     *   right and the right nibble by 4 positions to the left.
     * - Finally, combine the shifted nibbles using the OR operation to form the final result.
     * 
     * Time Complexity:
     * - O(1), as we perform a fixed number of operations regardless of the input size.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used.
     * 
     * @param n The input number.
     * @return The number with swapped nibbles.
     */
    public static int swapNibbles(int n) {
        // Extract the 4 least significant bits (right nibble) and shift them to the
        // left
        int left = (n & 15) << 4;

        // Extract the next 4 bits (left nibble) and shift them to the right
        int right = (n & (15 << 4)) >> 4;

        // Combine the shifted nibbles and return the result
        return left | right;
    }
}
