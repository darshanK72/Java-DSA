/**
 * Problem Statement:
 * 
 *     Given two integers `x` and `y`, calculate the **Hamming distance** between the two integers.
 *     The Hamming distance between two integers is the number of positions at which the corresponding 
 *     bits are different in their binary representations.
 * 
 * Input:
 *     - Two integers `x` and `y` (0 <= x, y <= 10^9).
 * 
 * Output:
 *     - An integer representing the Hamming distance between `x` and `y`.
 * 
 * Example:
 *     Input:
 *     1 4
 *     Output:
 *     2
 * 
 *     Explanation:
 *     Binary representation of 1: 0001
 *     Binary representation of 4: 0100
 *     The Hamming distance is 2 because bits at positions 2 and 3 differ.
 */

import java.util.Scanner;

public class j06HammingDistance {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt(); // Input integer x
        int y = in.nextInt(); // Input integer y

        // Using the first method to calculate Hamming Distance
        System.out.println(hammingDistance(x, y));

        // Using the second, more efficient method to calculate Hamming Distance
        System.out.println(hammingDistanceEfficient(x, y));

        in.close();
    }

    /**
     * Approach 1: Naive Method to Calculate Hamming Distance
     * 
     * Intuition:
     * - We calculate the XOR of `x` and `y`. XOR will give a number with 1s at positions where the 
     *   corresponding bits of `x` and `y` are different.
     * - After obtaining the XOR result, we count the number of 1s in the result to determine the Hamming distance.
     * 
     * Time Complexity:
     * - O(log n), where `n` is the number of bits in the largest of `x` or `y`. We use bit manipulation, 
     *   and the number of operations is proportional to the number of bits.
     * 
     * Space Complexity:
     * - O(1) as we use a constant amount of space.
     * 
     * @param x The first input number.
     * @param y The second input number.
     * @return The Hamming distance between `x` and `y`.
     */
    public static int hammingDistance(int x, int y) {
        int r = x ^ y; // XOR of x and y will give a number where the differing bits are set to 1
        int c = 0; // To count the number of set bits (1s)

        // Count the number of set bits in the XOR result
        while (r > 0) {
            r &= (r - 1); // Drop the rightmost set bit
            c++; // Increment the counter for each set bit
        }
        return c; // Return the Hamming distance
    }

    /**
     * Approach 2: Efficient Method to Calculate Hamming Distance
     * 
     * Intuition:
     * - The efficient method leverages the built-in `Integer.bitCount` method, which directly counts 
     *   the number of 1s in the binary representation of a number.
     * - We XOR `x` and `y` and use `Integer.bitCount` to return the Hamming distance.
     * 
     * Time Complexity:
     * - O(1), since `Integer.bitCount` operates in constant time for 32-bit integers.
     * 
     * Space Complexity:
     * - O(1) for storing the result.
     * 
     * @param x The first input number.
     * @param y The second input number.
     * @return The Hamming distance between `x` and `y`.
     */
    public static int hammingDistanceEfficient(int x, int y) {
        return Integer.bitCount(x ^ y); // Use XOR and count the number of 1s in the result
    }
}
