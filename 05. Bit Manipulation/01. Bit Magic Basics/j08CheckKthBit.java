/**
 * Problem Statement:
 * 
 *     Given an integer `num` and an index `k`, check if the `k`-th bit (starting from 0) of `num` is set (1) or not (0).
 *     Additionally, a method is provided to check the `n`-th bit (starting from 1).
 * 
 * Input:
 *     - An integer `num` (1 <= num <= 10^5), the number whose bit is to be checked.
 *     - An integer `k` (0 <= k <= 31), the bit position to check (starting from 0).
 *     - Alternatively, an integer `n` (1 <= n <= 32) for checking the `n`-th bit (starting from 1).
 * 
 * Output:
 *     - A boolean value indicating whether the `k`-th or `n`-th bit is set or not.
 * 
 * Example:
 *     Input:
 *         5 1
 *     Output:
 *         true
 * 
 *     Explanation:
 *         The binary representation of 5 is "101". The 1st bit (starting from 0) is set (1).
 */

import java.util.Scanner;

public class j08CheckKthBit {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num = in.nextInt(); // Input: the number to check the bit of
        int k = in.nextInt(); // Input: the k-th bit position
        System.out.println(checkKthBit(num, k)); // Output: whether the k-th bit is set
        in.close();
    }

    /**
     * Approach 1: Check k-th Bit (0-indexed)
     * 
     * Intuition:
     * - We can check the k-th bit by performing a bitwise AND operation between `num` and a mask that has only the k-th bit set.
     * - If the result is non-zero, the k-th bit is set to 1, otherwise it is 0.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param num The input integer.
     * @param k The position of the bit to check (0-indexed).
     * @return True if the k-th bit is set (1), otherwise false.
     */
    public static boolean checkKthBit(int num, int k) {
        return (num & (1 << k)) != 0; // Perform bitwise AND and check if the k-th bit is set
    }

    /**
     * Approach 2: Check n-th Bit (1-indexed)
     * 
     * Intuition:
     * - The n-th bit (1-indexed) can be checked by shifting the bits of `num` to the right by (n-1) positions and then checking if the result is non-zero.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param num The input integer.
     * @param n The position of the bit to check (1-indexed).
     * @return True if the n-th bit is set (1), otherwise false.
     */
    public static boolean checkNthBit(int num, int n) {
        return (num & (1 << (n - 1))) != 0; // Perform bitwise AND and check if the n-th bit is set
    }
}
