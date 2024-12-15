/**
 * Problem Statement:
 * 
 *     Given an integer `n`, determine if it is a power of four. A number is considered a power 
 *     of four if it can be written as 4^k, where `k` is a non-negative integer.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9), representing the number to check if it is a power of four.
 * 
 * Output:
 *     - `true` if `n` is a power of four, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     16
 *     Output:
 *     true
 * 
 *     Explanation:
 *     16 is a power of four because 4^2 = 16.
 * 
 *     Input:
 *     20
 *     Output:
 *     false
 * 
 *     Explanation:
 *     20 is not a power of four.
 * 
 */

import java.util.Scanner;

public class j08IsPowerOfFour {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to check if it is a power of four

        // Output: Checking using three different approaches
        System.out.println(isPowerOfFour1(n)); // Using bit manipulation with mask
        System.out.println(isPowerOfFour2(n)); // Using bit manipulation with different mask
        System.out.println(isPowerOfFour3(n)); // Using modulo approach

        in.close();
    }

    /**
     * Approach 1: Bit Manipulation with Mask (0x55555555)
     * 
     * Intuition:
     * - This approach checks if the number `n` is a power of two (i.e., only one bit is set) and 
     *   if that bit is in a position corresponding to powers of four.
     * - The mask `0x55555555` (binary: `01010101010101010101010101010101`) ensures that we 
     *   check for bits in the odd positions, as powers of four have their set bits in odd positions.
     * 
     * Time Complexity:
     * - O(1), as it involves constant-time bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return `true` if `n` is a power of four, otherwise `false`.
     */
    public static boolean isPowerOfFour1(int n) {
        // 0b01010101010101010101010101010101
        int mask = 0x55555555;
        return n > 0 && (n & (n - 1)) == 0 && (mask & n) > 0;
    }

    /**
     * Approach 2: Bit Manipulation with Mask (0xAAAAAAAA)
     * 
     * Intuition:
     * - This approach checks if the number `n` is a power of two (i.e., only one bit is set) and 
     *   if that bit is in a position corresponding to powers of four.
     * - The mask `0xAAAAAAAA` (binary: `10101010101010101010101010101010`) ensures that we check 
     *   for bits in the even positions, as powers of four does not have their set bits in even positions.
     * 
     * Time Complexity:
     * - O(1), as it involves constant-time bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return `true` if `n` is a power of four, otherwise `false`.
     */
    public static boolean isPowerOfFour2(int n) {
        // 0b10101010101010101010101010101010
        int mask = 0xAAAAAAAA;
        return n != 0 && (n & (n - 1)) == 0 && (n & mask) == 0;
    }

    /**
     * Approach 3: Using Modulo Operation
     * 
     * Intuition:
     * - This approach checks if `n` is a power of two and if `n % 3 == 1`, because powers of 
     *   four modulo 3 always leave a remainder of 1.
     * 
     * Time Complexity:
     * - O(1), as it involves constant-time arithmetic operations.
     * 
     * Space Complexity:
     * - O(1), no additional space is used.
     * 
     * @param n The input number.
     * @return `true` if `n` is a power of four, otherwise `false`.
     */
    public static boolean isPowerOfFour3(int n) {
        return (n > 0) && ((n & (n - 1)) == 0) && (n % 3 == 1);
    }
}
