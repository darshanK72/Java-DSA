/**
 * Problem Statement:
 * 
 *     Given an integer `num` and an index `k`, toggle the `k`-th bit (starting from 0) of `num`. 
 *     The toggle operation will flip the bit: if it is 0, it becomes 1, and if it is 1, it becomes 0.
 *     Additionally, a method is provided to toggle the `n`-th bit (starting from 1).
 * 
 * Input:
 *     - An integer `num` (1 <= num <= 10^5), the number whose bit is to be toggled.
 *     - An integer `k` (0 <= k <= 31), the bit position to toggle (starting from 0).
 *     - Alternatively, an integer `n` (1 <= n <= 32) for toggling the `n`-th bit (starting from 1).
 * 
 * Output:
 *     - A boolean value indicating whether the `k`-th or `n`-th bit is toggled (flipped).
 * 
 * Example:
 *     Input:
 *         5 1
 *     Output:
 *         true
 * 
 *     Explanation:
 *         The binary representation of 5 is "101". After toggling the 1st bit, it becomes "111", which is 7. 
 *         The result indicates whether the bit is toggled.
 */

import java.util.Scanner;

public class j11ToggleKthBit {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num = in.nextInt(); // Input: the number to toggle the bit of
        int k = in.nextInt(); // Input: the k-th bit position
        System.out.println(checkKthBit(num, k)); // Output: whether the k-th bit is toggled
        in.close();
    }

    /**
     * Approach 1: Toggle k-th Bit (0-indexed)
     * 
     * Intuition:
     * - We can toggle the k-th bit by performing a bitwise XOR operation between `num` and a mask that has only the k-th bit set.
     * - This flips the k-th bit, changing its value from 0 to 1, or from 1 to 0.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param num The input integer.
     * @param k The position of the bit to toggle (0-indexed).
     * @return True if the k-th bit is toggled (flipped), otherwise false.
     */
    public static boolean checkKthBit(int num, int k) {
        return (num ^ (1 << k)) != 0; // Perform bitwise XOR with the k-th bit mask to toggle the bit
    }

    /**
     * Approach 2: Toggle n-th Bit (1-indexed)
     * 
     * Intuition:
     * - The n-th bit (1-indexed) can be toggled by shifting the bits of `num` to the right by (n-1) positions and applying a bitwise XOR.
     * - This flips the n-th bit.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param num The input integer.
     * @param n The position of the bit to toggle (1-indexed).
     * @return True if the n-th bit is toggled (flipped), otherwise false.
     */
    public static boolean checkNthBit(int num, int n) {
        return (num ^ (1 << (n - 1))) != 0; // Perform bitwise XOR with the n-th bit mask to toggle the bit
    }
}
