
/**
 * Problem Statement:
 * 
 *     Given a 32-bit unsigned integer `n`, reverse the bits of `n` and return the modified integer with the bits reversed. 
 *     The output should preserve the reversed bit order, and the result should be the number formed by reversing the binary representation of `n`.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 2^32 - 1), representing the unsigned 32-bit integer.
 * 
 * Output:
 *     - An integer that is the result of reversing the bits of `n`.
 * 
 * Example:
 *     Input:
 *     43261596
 *     Output:
 *     964176192
 * 
 *     Explanation:
 *     The binary representation of 43261596 is `00000010100101000001111010011100`.
 *     The reversed binary is `00111001011110000010100101000000`, which is 964176192 in decimal.
 * 
 */

import java.util.Scanner;

public class j02ReverseBits {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to reverse the bits

        // Output: Original binary, reversed using naive and efficient approaches
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(reverseBitsNive(n))); // Naive approach
        System.out.println(Integer.toBinaryString(reverseBitsEfficient(n))); // Efficient approach

        in.close();
    }

    /**
     * Approach 1: Naive Approach - Bit Swapping
     * 
     * Intuition:
     * - Swap the bits from the two ends of the 32-bit number, moving toward the center, to reverse the order of the bits.
     * 
     * Time Complexity:
     * - O(32), as we perform a fixed number of operations (swapping bits) across 32 bit positions.
     * 
     * Space Complexity:
     * - O(1), no additional space is used except for the variables.
     * 
     * @param n The input number.
     * @return The number with its bits reversed.
     */
    public static int reverseBitsNive(int n) {
        int s = 0; // Start pointer
        int e = 31; // End pointer (32-bit number)
        while (s < e) {
            n = swapBits(n, s, e); // Swap the bits at positions s and e
            s++; // Move the start pointer rightward
            e--; // Move the end pointer leftward
        }
        return n; // Return the modified number with reversed bits
    }

    /**
     * Helper: Swap Bits at Specific Positions
     * 
     * Intuition:
     * - To swap the bits at positions s and e, use bitwise AND and XOR operations to flip them.
     * 
     * Time Complexity:
     * - O(1), as the swapping of two bits is a constant-time operation.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The number whose bits are being swapped.
     * @param s The start position.
     * @param e The end position.
     * @return The modified number with swapped bits at positions s and e.
     */
    public static int swapBits(int n, int s, int e) {
        // Extract the bits at positions s and e
        int leftBit = n & (1 << s);
        int rightBit = n & (1 << e);

        // If the bits differ, swap them using XOR
        if ((leftBit != 0) != (rightBit != 0)) {
            n = n ^ (1 << s); // Flip the bit at position s
            n = n ^ (1 << e); // Flip the bit at position e
        }
        return n; // Return the number with swapped bits
    }

    /**
     * Approach 2: Efficient Approach - Bitwise Reversal
     * 
     * Intuition:
     * - Reverse the bits of the number by shifting and OR-ing the bits from right to left. This method is efficient, requiring only a single pass through the 32 bits.
     * 
     * Time Complexity:
     * - O(32), as we perform 32 bit shifts and bitwise operations in a loop.
     * 
     * Space Complexity:
     * - O(1), no additional space is used except for the variables.
     * 
     * @param n The input number.
     * @return The number with its bits reversed.
     */
    public static int reverseBitsEfficient(int n) {
        int ans = 0; // Variable to store the reversed bits
        for (int i = 0; i < 32; i++) {
            ans <<= 1; // Shift the result left by 1 bit
            ans |= (n & 1); // Add the rightmost bit of n to ans
            n >>= 1; // Shift n right by 1 bit to process the next bit
        }
        return ans; // Return the reversed number
    }
}
