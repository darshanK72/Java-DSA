/**
 * Problem Statement:
 *
 *     Given two integers `l` and `r`, return the maximum possible value of `l ^ x` where `l <= x <= r`.
 *
 * Input:
 *     - Two integers `l` and `r` (1 <= l <= r <= 2^30).
 *
 * Output:
 *     - The maximum possible value of `l ^ x` where `l <= x <= r`.
 *
 * Example:
 *     Input:
 *     10 15
 *     Output:
 *     7
 *
 *     Explanation:
 *     The binary representations of `l` and `r` are:
 *     l = 10  -> 1010
 *     r = 15  -> 1111
 *
 *     The maximum XOR value is achieved when `x = 15` since 1010 ^ 1111 = 0111 (which is 7 in decimal).
 */

import java.util.Scanner;

public class j11MaximizingXOR {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt(); // Input: lower bound of the range
        int r = in.nextInt(); // Input: upper bound of the range

        System.out.println(maximizingXor(l, r)); // Output: the maximum XOR value
        in.close();
    }

    /**
     * Approach:
     *
     * 1. The key observation is that the XOR value is maximized when the binary representations of `l` and `r` differ in the most significant bit.
     * 2. If `l == r`, the maximum XOR is 0 because no number between `l` and `r` will provide any difference.
     * 3. To calculate the maximum XOR, we look for the leftmost bit where `l` and `r` differ. This bit will determine the range of possible XOR results.
     * 4. The result will be a number with all bits set to `1` from the most significant bit down to the least significant bit where `l` and `r` differ.
     * 5. The efficient way to find the result is to calculate the highest bit where `l` and `r` differ, and then return a number with all bits set from that position.
     *
     * Time Complexity:
     * - O(1), since the operation involves a constant number of bit manipulations.
     *
     * Space Complexity:
     * - O(1), as only a constant amount of space is used.
     *
     * @param l The lower bound of the range.
     * @param r The upper bound of the range.
     * @return The maximum XOR value.
     */
    public static int maximizingXor(int l, int r) {
        if (l == r) // If l and r are equal, the maximum XOR is 0
            return 0;

        // Find the leftmost bit where l and r differ
        int leftMostBit = Integer.highestOneBit(l ^ r);

        // Return a number with all bits set to 1 from that position
        return (leftMostBit << 1) - 1;
    }
}
