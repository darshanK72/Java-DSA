/**
 * Problem Statement:
 * 
 *     Given two arrays `arr1` and `arr2`, the goal is to sort `arr1` such that:
 *     1. The relative order of elements in `arr1` follows the order given in `arr2`.
 *     2. Elements in `arr1` that are not in `arr2` should be sorted in ascending order at the end.
 * 
 * Input:
 *     - `arr1`: An integer array (1 <= arr1.length <= 1000, 0 <= arr1[i] <= 1000).
 *     - `arr2`: A distinct integer array (1 <= arr2.length <= 1000, 0 <= arr2[i] <= 1000).
 * 
 * Output:
 *     - A sorted version of `arr1` based on the above rules.
 * 
 * Example:
 *     Input:
 *         arr1 = [2,3,1,3,2,4,6,7,9,2,19]
 *         arr2 = [2,1,4,3,9,6]
 *     Output:
 *         [2,2,2,1,4,3,3,9,6,7,19]
 * 
 *     Explanation:
 *         - Elements [2,1,4,3,9,6] appear in `arr2` and must follow that order.
 *         - Remaining elements [7,19] appear in sorted order at the end.
 */

import java.util.Arrays;

public class j05RelativeSortArray {

    public static void main(String[] args) {

        int[][] testArr1 = {
            { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 },
            { 28, 6, 22, 8, 44, 17 },
            { 5, 3, 4, 2, 1 },
            { 7, 7, 7, 7, 8, 5, 6, 5, 5 },
            { 1, 2, 3, 4, 5 }
        };

        int[][] testArr2 = {
            { 2, 1, 4, 3, 9, 6 },
            { 22, 28, 8, 6 },
            { 1, 2, 3 },
            { 5, 7 },
            { 5, 4, 3, 2, 1 }
        };

        for (int i = 0; i < testArr1.length; i++) {
            int[] result = relativeSortArray(testArr1[i], testArr2[i]);
            System.out.println("Test Case " + (i + 1) + ": " + Arrays.toString(result));
        }
    }

    /**
     * Approach: Counting Sort-Based Ordering
     * 
     * Intuition:
     * - Since the range of values is limited (0 to 1000), we can use a frequency
     *   array (`count`) to store the occurrences of each number in `arr1`.
     * - We first place elements from `arr2` in the correct order.
     * - The remaining numbers are sorted naturally in increasing order.
     * 
     * Explanation:
     * - Build a frequency array `count` for `arr1`.
     * - Iterate through `arr2` and place elements in `out` based on frequency.
     * - Iterate through the entire range (0-1000) and append remaining elements.
     * 
     * Time Complexity:
     * - O(n + m), where `n` is the length of `arr1` and `m` is the length of `arr2`.
     * - Counting sort runs in O(n), and placement takes O(n).
     * 
     * Space Complexity:
     * - O(1) since we use a fixed-size `count` array.
     * 
     * @param arr1 The array to be sorted.
     * @param arr2 The array specifying relative order.
     * @return The sorted array following the given constraints.
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001]; // Constraint: values range from 0 to 1000
        for (int n : arr1) {
            count[n]++;
        }

        int[] out = new int[arr1.length];
        int idx = 0;

        // Place elements from arr2 in order
        for (int n : arr2) {
            while (count[n] > 0) {
                out[idx++] = n;
                count[n]--;
            }
        }

        // Place remaining elements in ascending order
        for (int i = 0; i < 1001; i++) {
            while (count[i] > 0) {
                out[idx++] = i;
                count[i]--;
            }
        }
        return out;
    }
}
