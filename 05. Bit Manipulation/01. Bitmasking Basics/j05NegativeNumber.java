/**
 * Problem Statement:
 * 
 *     Given a positive integer `n`, calculate and print its binary representation, 
 *     as well as the binary representation of its negative counterpart in two's complement.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the positive integer to calculate negative binary representation.
 * 
 * Output:
 *     - A string showing the binary representation of `n` and its negative equivalent in two's complement.
 * 
 * Example:
 *     Input:
 *         5
 *     Output:
 *         Positive Number (5) : 101
 *         Negative Number (-5) : 11111111111111111111111111111011
 * 
 *     Explanation:
 *         The binary representation of 5 is "101".
 *         The binary representation of -5 is the two's complement of 5, which is "11111111111111111111111111111011".
 */

import java.util.Scanner;

public class j05NegativeNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the positive number to get the binary representation
        System.out.println("Positive Number (" + n + ") : " + Integer.toBinaryString(n)); // Print binary for positive
        System.out.println(
                "Negative Number (" + getNegativeNumber(n) + ") : " + Integer.toBinaryString(getNegativeNumber(n))); // Print
                                                                                                                     // binary
                                                                                                                     // for
                                                                                                                     // negative
        in.close();
    }

    /**
     * Approach: Two's Complement Calculation
     * 
     * Intuition:
     * - To get the negative of a number in binary, we use two's complement.
     * - First, calculate the one's complement by flipping all the bits (`~n`).
     * - Then, add 1 to the one's complement to get the two's complement.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param n The input integer.
     * @return The negative number in two's complement.
     */
    public static int getNegativeNumber(int n) {
        int ones = ~n; // Ones Complement: flip all bits
        int twos = ones + 1; // Twos Complement: add 1 to the ones complement
        return twos; // Return the two's complement (negative of `n`)
    }
}
