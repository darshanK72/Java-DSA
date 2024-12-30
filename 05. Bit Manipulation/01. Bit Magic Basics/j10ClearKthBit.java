/**
 * Problem Statement:
 * 
 *     Given an integer `num` and an index `k`, clear the `k`-th bit (starting from 0) of `num` to 0.
 *     Additionally, a method is provided to clear the `n`-th bit (starting from 1).
 * 
 * Input:
 *     - An integer `num` (1 <= num <= 10^5), the number whose bit is to be cleared.
 *     - An integer `k` (0 <= k <= 31), the bit position to clear (starting from 0).
 *     - Alternatively, an integer `n` (1 <= n <= 32) for clearing the `n`-th bit (starting from 1).
 * 
 * Output:
 *     - A boolean value indicating whether the `k`-th or `n`-th bit is cleared (0).
 * 
 * Example:
 *     Input:
 *         5 1
 *     Output:
 *         false
 * 
 *     Explanation:
 *         The binary representation of 5 is "101". After clearing the 1st bit, it becomes "100", which is 4. 
 *         The result indicates whether the bit is cleared.
 */

import java.util.Scanner;

public class j10ClearKthBit {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num = in.nextInt(); // Input: the number to clear the bit of
        int k = in.nextInt(); // Input: the k-th bit position
        System.out.println(checkKthBit(num, k)); // Output: whether the k-th bit is cleared
        in.close();
    }

    /**
     * Approach 1: Clear k-th Bit (0-indexed)
     * 
     * Intuition:
     * - We can clear the k-th bit by performing a bitwise AND operation between `num` and the negation of a mask that has only the k-th bit set.
     * - This results in a number with the k-th bit cleared (set to 0).
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param num The input integer.
     * @param k The position of the bit to clear (0-indexed).
     * @return True if the k-th bit is cleared (0), otherwise false.
     */
    public static boolean checkKthBit(int num, int k) {
        return (num & ~(1 << k)) != 0; // Perform bitwise AND with the negation of the k-th bit mask
    }

    /**
     * Approach 2: Clear n-th Bit (1-indexed)
     * 
     * Intuition:
     * - The n-th bit (1-indexed) can be cleared by shifting the bits of `num` to the right by (n-1) positions, negating the mask, and then performing a bitwise AND.
     * - This clears the n-th bit.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param num The input integer.
     * @param n The position of the bit to clear (1-indexed).
     * @return True if the n-th bit is cleared (0), otherwise false.
     */
    public static boolean checkNthBit(int num, int n) {
        return (num & ~(1 << (n - 1))) != 0; // Perform bitwise AND with the negation of the n-th bit mask
    }
}
