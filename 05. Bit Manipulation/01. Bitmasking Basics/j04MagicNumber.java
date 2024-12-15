/**
 * Problem Statement:
 * 
 *     Determine the "magic number" for a given integer `n`. A magic number is calculated 
 *     by summing powers of 5 corresponding to the positions of set bits (1s) in the binary 
 *     representation of `n`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the number whose magic number is to be calculated.
 * 
 * Output:
 *     - An integer representing the magic number of `n`.
 * 
 * Example:
 *     Input:
 *         6
 *     Output:
 *         30
 * 
 *     Explanation:
 *         The binary representation of 6 is "110". 
 *         The magic number is calculated as:
 *         (1 * 5^2) + (1 * 5^1) = 25 + 5 = 30.
 */

import java.util.Scanner;

public class j04MagicNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to calculate the magic number
        System.out.println(calculateMagicNumber(n)); // Output: the calculated magic number
        in.close();
    }

    /**
     * Approach: Bitwise Conversion and Weighted Sum
     * 
     * Intuition:
     * - The binary representation of `n` indicates which powers of 5 contribute to the magic number.
     * - Starting from the least significant bit, multiply the bit value (0 or 1) by the current power 
     *   of 5 and add it to the result.
     * - Continue until all bits are processed.
     * 
     * Time Complexity:
     * - O(log(n)), where `log(n)` is the number of bits in the binary representation of `n`.
     * 
     * Space Complexity:
     * - O(1), as the calculation is done in-place.
     * 
     * @param n The input integer.
     * @return The magic number corresponding to `n`.
     */
    public static int calculateMagicNumber(int n) {
        int ans = 0; // Initialize the magic number
        int powerOfFive = 5; // Start with 5^1
        while (n > 0) {
            int bit = n & 1; // Extract the least significant bit
            ans += bit * powerOfFive; // Add the weighted contribution of the bit
            n >>= 1; // Right shift to process the next bit
            powerOfFive *= 5; // Move to the next power of 5
        }
        return ans; // Return the calculated magic number
    }
}
