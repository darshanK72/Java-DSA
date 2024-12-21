/*-
 * Problem Statement:
 * 
 *     You have `n` coins and you need to arrange them in a staircase pattern.
 *     In the first row, you will have 1 coin, in the second row, you will have 2 coins, in the third row, you will have 3 coins, and so on.
 *     You need to find how many complete rows of coins can be arranged such that the total number of coins used is less than or equal to `n`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 2 * 10^31), the number of coins.
 * 
 * Output:
 *     - An integer representing the number of complete rows that can be formed.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     2
 *     
 *     Explanation:
 *     The total number of coins used in the first two rows is:
 *     1st row: 1 coin
 *     2nd row: 2 coins
 *     Total = 1 + 2 = 3 coins.
 *     The third row would require 3 coins, but only 2 coins are remaining, so the output is 2 rows.
 */

import java.util.Scanner;

public class j05ArrangeCoins {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of coins
        System.out.println(arrangeCoins(n)); // Output: the number of complete rows
        in.close();
    }

    /*-
     * Approach 1: Binary Search to Find the Maximum Number of Complete Rows
     * 
     * Intuition:
     * - The problem asks us to find the maximum number of complete rows that can be arranged with `n` coins.
     * - If we think of the total coins required for `k` rows, it is the sum of the first `k` natural numbers:
     *     - Total coins for `k` rows = k * (k + 1) / 2.
     * - We need to find the maximum value of `k` such that this sum does not exceed `n`.
     * - This can be efficiently done using binary search, where:
     *     - We check the middle number `mid` (representing a possible number of rows).
     *     - If the sum for `mid` rows is less than `n`, we move the start of the search to `mid + 1`.
     *     - If the sum exceeds `n`, we move the end of the search to `mid - 1`.
     * - The binary search ensures that we find the largest value of `k` such that the sum does not exceed `n`.
     * 
     * Time Complexity:
     * - O(log n), because the binary search works in logarithmic time.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables to store the bounds and the mid-point.
     * 
     * @param n The number of coins.
     * @return The number of complete rows that can be formed.
     */
    public static int arrangeCoins(int n) {
        long s = 1; // Start of the search range (1 row)
        long e = n; // End of the search range (maximum rows)
        while (s <= e) {
            long mid = s + (e - s) / 2; // Calculate middle value of rows
            long k = mid * (mid + 1) / 2; // Calculate the total coins required for `mid` rows
            if (k == n) {
                return (int) mid; // If the total coins exactly match `n`, return the current number of rows
            } else if (k < n) {
                s = mid + 1; // If the sum is less than `n`, try with more rows
            } else {
                e = mid - 1; // If the sum exceeds `n`, try with fewer rows
            }
        }
        return (int) e; // Return the largest valid `mid` value that works
    }
}
