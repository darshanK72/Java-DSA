/**
 * Problem Statement:
 * 
 *     Given a 32-bit integer `n`, swap its odd and even bits. Specifically, swap the bits at positions
 *     0, 2, 4, ... with the bits at positions 1, 3, 5, ..., respectively. The result should be a number 
 *     with the bits swapped between odd and even positions.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 2^32 - 1), representing the 32-bit number.
 * 
 * Output:
 *     - An integer that represents the result after swapping the odd and even bits of `n`.
 * 
 * Example:
 *     Input:
 *     23
 *     Output:
 *     43
 * 
 *     Explanation:
 *     The binary representation of 23 is `00010111`. After swapping the odd and even bits, the result is 
 *     `00101011`, which is 43 in decimal.
 * 
 */

import java.util.Scanner;

public class j03SwapOddEvenBits {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to swap odd and even bits

        // Output: Original binary, swapped using naive and efficient approaches
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(swapOddEvenBitsNive(n))); // Naive approach
        System.out.println(Integer.toBinaryString(swapOddEvenBitsEfficient(n))); // Efficient approach

        in.close();
    }

    /**
     * Approach 1: Naive Approach - Bitwise Swapping
     * 
     * Intuition:
     * - Iterate through the number and swap each odd-indexed bit with the next even-indexed bit. 
     *   This is done using bitwise operations.
     * 
     * Time Complexity:
     * - O(32), as we iterate through all 32 bits.
     * 
     * Space Complexity:
     * - O(1), no additional space is used except for variables.
     * 
     * @param n The input number.
     * @return The number with odd and even bits swapped.
     */
    public static int swapOddEvenBitsNive(int n) {
        // Loop through every pair of bits (odd and even indexed)
        for (int i = 0; i < 32; i += 2) {
            int bit1 = n & (1 << i); // Extract the bit at position i (odd)
            int bit2 = n & (1 << (i + 1)); // Extract the bit at position i+1 (even)

            // If the bits differ, swap them using XOR
            if ((bit1 != 0) != (bit2 != 0)) {
                n = n ^ (1 << i); // Flip the bit at position i
                n = n ^ (1 << (i + 1)); // Flip the bit at position i+1
            }
        }
        return n; // Return the number with swapped odd and even bits
    }

    /**
     * Approach 2: Optimized Approach - Bit Masking
     * 
     * Intuition:
     * - Use bitmasks to isolate the odd and even bits of `n`, then shift and combine the results. 
     *   This approach uses pre-defined masks to preserve odd and even bits, making it more efficient.
     * 
     * Time Complexity:
     * - O(1), as the operations (bitmasking, shifting, and OR-ing) are constant-time operations.
     * 
     * Space Complexity:
     * - O(1), no additional space is used except for the bitmask.
     * 
     * @param n The input number.
     * @return The number with odd and even bits swapped.
     */
    public static int swapOddEvenBitsEfficient(int n) {
        // Masks for preserving odd and even indexed bits
        int oddPreservingMask = 0x55555555; // 01010101010101010101010101010101
        System.out.println(Integer.toBinaryString(oddPreservingMask)); // Debugging: oddPreservingMask in binary
        int evenPreservingMask = 0xAAAAAAAA; // 10101010101010101010101010101010
        System.out.println(Integer.toBinaryString(evenPreservingMask)); // Debugging: evenPreservingMask in binary

        // Extract odd and even bits
        int odd = n & oddPreservingMask;
        System.out.println(Integer.toBinaryString(odd)); // Debugging: odd bits
        int even = n & evenPreservingMask;
        System.out.println(Integer.toBinaryString(even)); // Debugging: even bits

        // Shift odd bits left and even bits right
        odd <<= 1;
        even >>= 1;

        // Combine the shifted bits to get the final result
        return even | odd;
    }
}
