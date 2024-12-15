/**
 * Problem Statement:
 * 
 *     Given two integers `n1` and `n2`, and a range [left, right], copy all the set bits (bits with value 1) from `n2` to `n1` within the specified bit range, and return the modified value of `n1`. The positions in the range are 1-based indices, where the leftmost bit is considered the first bit.
 * 
 * Input:
 *     - An integer `n1` (1 <= n1 <= 10^5), representing the first integer.
 *     - An integer `n2` (1 <= n2 <= 10^5), representing the second integer.
 *     - An integer `left` (1 <= left <= 32), representing the left index of the bit range.
 *     - An integer `right` (1 <= right <= 32), representing the right index of the bit range.
 * 
 * Output:
 *     - The modified integer `n1` after copying the set bits from `n2` in the specified range [left, right].
 * 
 * Example:
 *     Input:
 *     65 66 2 6
 *     Output:
 *     66 (1000010)
 * 
 *     Explanation:
 *     The binary representation of `n1` is 1000001 and that of `n2` is 1000010.
 *     We copy the set bits of `n2` in the range from bit position 2 to 6 to `n1`, which results in 1000010.
 * 
 */

import java.util.Scanner;

public class j01CopySetBitsInRange {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // Input: the first integer
        int n2 = in.nextInt(); // Input: the second integer
        int left = in.nextInt(); // Input: left bit position (1-based)
        int right = in.nextInt(); // Input: right bit position (1-based)

        // Output: Result after copying set bits from n2 to n1 in the specified range
        System.out.println(n1 + "(" + Integer.toBinaryString(n1) + ")");
        System.out.println(n2 + "(" + Integer.toBinaryString(n2) + ")");
        System.out.println(copySetBitsInRange(n1, n2, left, right) + "("
                + Integer.toBinaryString(copySetBitsInRange(n1, n2, left, right)) + ")");
        System.out.println(copySetBitsInRangeEfficient(n1, n2, left, right) + "("
                + Integer.toBinaryString(copySetBitsInRangeEfficient(n1, n2, left, right)) + ")");

        in.close();
    }

    /**
     * Approach 1: Brute Force Solution
     * 
     * Intuition:
     * - Iterate through the bit positions from `left` to `right`, and for each bit that is set (1) in `n2`, set the corresponding bit in `n1`.
     * 
     * Time Complexity:
     * - O(r - l + 1), where `l` and `r` are the `left` and `right` bit positions respectively. In the worst case, this is O(32).
     * 
     * Space Complexity:
     * - O(1), no extra space is used except for variables.
     * 
     * @param n1 The first integer.
     * @param n2 The second integer.
     * @param left The left bit position (1-based).
     * @param right The right bit position (1-based).
     * @return The result after copying set bits.
     */
    public static int copySetBitsInRange(int n1, int n2, int left, int right) {
        // Iterate over the range from left to right (1-based indices)
        for (int i = left - 1; i <= right - 1; i++) {
            int bit = n2 & (1 << i); // Check if the bit is set in n2
            if (bit != 0) {
                n1 = n1 | bit; // Set the corresponding bit in n1
            }
        }
        return n1; // Return the modified n1
    }

    /**
     * Approach 2: Optimized Solution Using Bitmasking
     * 
     * Intuition:
     * - Create a bitmask that covers the range from `left` to `right` in `n2`, extract the corresponding bits, and then OR these bits with `n1`.
     * - This is more efficient as it avoids bit-by-bit iteration.
     * 
     * Time Complexity:
     * - O(1), as the mask creation and OR operation are constant-time operations.
     * 
     * Space Complexity:
     * - O(1), no extra space is used except for the bitmask.
     * 
     * @param n1 The first integer.
     * @param n2 The second integer.
     * @param left The left bit position (1-based).
     * @param right The right bit position (1-based).
     * @return The result after copying set bits.
     */
    public static int copySetBitsInRangeEfficient(int n1, int n2, int left, int right) {
        // Create a mask that covers the range of bits [left, right] in n2
        int mask1 = ((1 << (right - left + 1)) - 1) << (left - 1); // Mask for the specified range
        System.out.println(Integer.toBinaryString(mask1)); // Debugging: mask1 in binary
        int mask2 = n2 & mask1; // Extract the set bits from n2 in the specified range
        System.out.println(Integer.toBinaryString(mask2)); // Debugging: mask2 in binary
        return n1 | mask2; // OR the extracted bits with n1 to get the final result
    }
}
