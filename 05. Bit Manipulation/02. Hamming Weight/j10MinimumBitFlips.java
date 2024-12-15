/**
 * Problem Statement:
 *
 *     Given two integers `start` and `goal`, return the minimum number of bit flips required to convert `start` to `goal`.
 *
 * Input:
 *     - Two integers `start` and `goal` (0 <= start, goal <= 2^31 - 1).
 *
 * Output:
 *     - The minimum number of bit flips required to convert `start` to `goal`.
 *
 * Example:
 *     Input:
 *     10 7
 *     Output:
 *     3
 *
 *     Explanation:
 *     The binary representations of `10` and `7` are:
 *     10  -> 1010
 *     7   -> 0111
 *
 *     The positions where the bits differ are 1, 2, and 3. Thus, the minimum number of bit flips is 3.
 */

import java.util.Scanner;

public class j10MinimumBitFlips {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int start = in.nextInt(); // Input: start integer
        int goal = in.nextInt(); // Input: goal integer

        System.out.println(minBitFlipsEfficient(start, goal)); // Output: the minimum bit flips
        in.close();
    }

    /**
     * Approach:
     * 
     * 1. The number of bit flips required is essentially the number of differing bits between `start` and `goal`.
     * 2. The XOR operation between `start` and `goal` will set the bits to `1` wherever the bits of the two integers differ.
     * 3. We then count how many `1` bits are set in the result of XOR. This will give the number of bit flips required.
     * 4. We implement an efficient approach that counts the set bits in the XOR result.
     * 
     * Time Complexity:
     * - O(number of set bits), as we count the set bits using the XOR result.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of space.
     * 
     * @param start The starting integer.
     * @param goal The goal integer.
     * @return The minimum number of bit flips required.
     */
    // Efficient approach using Hamming Distance
    public static int minBitFlipsEfficient(int start, int goal) {
        int out = start ^ goal; // XOR to find differing bits
        int count = 0;
        while (out > 0) {
            out &= (out - 1); // Remove the rightmost set bit
            count++; // Count the number of set bits
        }
        return count;
    }

    // Alternative approach with iterative bit comparison
    public static int minBitFlips(int start, int goal) {
        int count = 0;
        while (start > 0 || goal > 0) {
            if ((start & 1) != (goal & 1))
                count++; // Check if the bits differ
            start >>= 1; // Right shift start
            goal >>= 1; // Right shift goal
        }
        return count;
    }

    // Using Java's built-in bit count method
    public static int minBitFlipsMethod(int start, int goal) {
        return Integer.bitCount(start ^ goal); // Use Java's bitCount to count differing bits
    }
}
