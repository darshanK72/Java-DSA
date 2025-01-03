/**
 * Problem Statement:
 * 
 *     Given two integers `left` and `right`, return the bitwise AND of all numbers in the range [left, right] (inclusive).
 * 
 * Input:
 *     - Two integers, `left` and `right` (0 <= left <= right <= 2^31 - 1).
 * 
 * Output:
 *     - The bitwise AND of all numbers in the range [left, right].
 * 
 * Example:
 *     Input:
 *     5 7
 *     Output:
 *     4
 *     
 *     Explanation:
 *     The binary representations of the numbers in the range [5, 7] are:
 *     5  -> 101
 *     6  -> 110
 *     7  -> 111
 *     
 *     The bitwise AND of these numbers is 4 (binary: 100).
 */

import java.util.Scanner;

public class j09BitwiseAndOfNumberInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int left = in.nextInt(); // Input: left bound of the range
        int right = in.nextInt(); // Input: right bound of the range
        System.out.println(rangeBitwiseAnd(left, right)); // Output: the bitwise AND of all numbers in the range
        in.close();
    }

    /**
     * Approach:
     * 
     * 1. The bitwise AND of numbers in a range is only affected by the common prefix of their binary representations.
     * 2. The most significant bit where the numbers differ will determine the result. 
     * 3. We iteratively reduce the range by performing `right &= (right - 1)` to eliminate the least significant bit 
     *    that is not common between `left` and `right`.
     * 4. Once `left` and `right` are equal, the result is the bitwise AND of all numbers in the range.
     * 
     * Time Complexity:
     * - O(log(n)), where n is the maximum value of `right`, because we keep shifting the `right` value.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of space.
     * 
     * @param left The left bound of the range.
     * @param right The right bound of the range.
     * @return The bitwise AND of all numbers in the range [left, right].
     */
    public static int rangeBitwiseAnd(int left, int right) {
        // While left is less than right, eliminate the rightmost differing bits of
        // right
        while (left < right) {
            right &= (right - 1); // Remove the rightmost set bit
        }
        return right; // When left == right, return the result
    }
}
