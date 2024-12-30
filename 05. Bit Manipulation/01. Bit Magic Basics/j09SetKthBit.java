/**
 * Problem Statement:
 * 
 *     Given an integer `num` and an index `k`, set the `k`-th bit (starting from 0) of `num` to 1.
 *     Additionally, a method is provided to set the `n`-th bit (starting from 1).
 * 
 * Input:
 *     - An integer `num` (1 <= num <= 10^5), the number whose bit is to be set.
 *     - An integer `k` (0 <= k <= 31), the bit position to set (starting from 0).
 *     - Alternatively, an integer `n` (1 <= n <= 32) for setting the `n`-th bit (starting from 1).
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
 *         The binary representation of 5 is "101". After setting the 1st bit, it becomes "111".
 */

import java.util.Scanner;

public class j09SetKthBit {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num = in.nextInt(); // Input: the number to set the bit of
        int k = in.nextInt(); // Input: the k-th bit position
        System.out.println(checkKthBit(num, k)); // Output: whether the k-th bit is set
        in.close();
    }

    /**
     * Approach 1: Set k-th Bit (0-indexed)
     * 
     * Intuition:
     * - We can set the k-th bit by performing a bitwise OR operation between `num` and a mask that has only the k-th bit set.
     * - The result will be a number with the k-th bit set to 1, regardless of its previous value.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param num The input integer.
     * @param k The position of the bit to set (0-indexed).
     * @return True if the k-th bit is successfully set (1), otherwise false.
     */
    public static boolean checkKthBit(int num, int k) {
        return (num | (1 << k)) != 0; // Perform bitwise OR and check if the k-th bit is set
    }

    /**
     * Approach 2: Set n-th Bit (1-indexed)
     * 
     * Intuition:
     * - The n-th bit (1-indexed) can be set by shifting the bits of `num` to the right by (n-1) positions and then performing a bitwise OR.
     * - This sets the n-th bit to 1.
     * 
     * Time Complexity:
     * - O(1), as the operation involves a fixed number of bitwise operations.
     * 
     * Space Complexity:
     * - O(1), as the operation uses a constant amount of space.
     * 
     * @param num The input integer.
     * @param n The position of the bit to set (1-indexed).
     * @return True if the n-th bit is successfully set (1), otherwise false.
     */
    public static boolean checkNthBit(int num, int n) {
        return (num | (1 << (n - 1))) != 0; // Perform bitwise OR and check if the n-th bit is set
    }
}
