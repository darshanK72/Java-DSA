/**
 * Problem Statement:
 * 
 *     Given a number `n`, return an array where the i-th element is the number of set bits (1's) 
 *     in the binary representation of all numbers from 0 to `i` (inclusive).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5).
 * 
 * Output:
 *     - An array of integers where the i-th element represents the number of set bits in the 
 *       binary representation of the number `i`.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     [0, 1, 1, 2, 1, 2]
 * 
 *     Explanation:
 *     For `n = 5`, the binary representations are:
 *     0 -> 0 set bits
 *     1 -> 1 set bit
 *     2 -> 1 set bit
 *     3 -> 2 set bits
 *     4 -> 1 set bit
 *     5 -> 2 set bits
 */

import java.util.Arrays;
import java.util.Scanner;

public class j05CountSetBitsOfSeries {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(Arrays.toString(countBits(n)));
        System.out.println(Arrays.toString(countBitsEfficient(n)));
        in.close();
    }

    /**
     * Approach 1: Naive method using Hamming Weight
     * 
     * Intuition:
     * - For each number from 0 to `n`, count the number of set bits (1s) using the Hamming Weight method.
     * - This approach works by checking each number individually.
     * 
     * Time Complexity:
     * - O(n * log n) since we are iterating through all numbers up to `n`, and for each number, we 
     *   check its bits using the Hamming Weight method.
     * 
     * Space Complexity:
     * - O(n) for storing the result array.
     * 
     * @param n The input number.
     * @return An array with the number of set bits for each number from 0 to `n`.
     */
    public static int[] countBits(int n) {
        int[] out = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            out[i] = hammingWeight(i);
        }
        return out;
    }

    /**
     * Helper function to count the number of set bits (1s) in a given number.
     * 
     * @param n The number to count the set bits.
     * @return The count of set bits in the number.
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n > 0) {
            n -= (n & -n);
            count++;
        }
        return count;
    }

    /**
     * Approach 2: Optimized method using Dynamic Programming
     * 
     * Intuition:
     * - This approach calculates the number of set bits for each number by using a previously computed result.
     * - The number of set bits for any number `i` is related to `i >> 1` (the number with one less bit).
     * - It uses a dynamic programming approach to avoid recalculating set bits for numbers multiple times.
     * 
     * Time Complexity:
     * - O(n) as we are calculating the number of set bits for each number once using a direct formula.
     * 
     * Space Complexity:
     * - O(n) for storing the result array.
     * 
     * @param n The input number.
     * @return An array with the number of set bits for each number from 0 to `n`.
     */
    public static int[] countBitsEfficient(int n) {
        int[] out = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            out[i] = out[(i >> 1)] + (i & 1);
        }
        return out;
    }
}
