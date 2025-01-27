/*-
 * Problem Statement:
 * 
 *     Given two sorted arrays `arr1` and `arr2` of sizes `m` and `n` respectively,
 *     merge them into a single sorted array `arr3` of size `m + n`. The merged array
 *     should also be sorted in non-decreasing order.
 * 
 * Input:
 *     - Two integers `m` and `n`, representing the sizes of the arrays.
 *     - Two sorted arrays `arr1` and `arr2` of sizes `m` and `n` respectively,
 *       where each element satisfies (1 <= arr1[i], arr2[i] <= 10^5).
 * 
 * Output:
 *     - A sorted array `arr3` of size `m + n`, containing all elements of `arr1` and `arr2`.
 * 
 * Example:
 *     Input:
 *         m = 4, n = 3
 *         arr1 = [1, 3, 5, 7]
 *         arr2 = [2, 4, 6]
 *     Output:
 *         [1, 2, 3, 4, 5, 6, 7]
 * 
 *     Explanation:
 *         The merged array includes all elements from arr1 and arr2 in sorted order.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j08MergeTwoSortedArraysInThird {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);

        int m = in.nextInt();
        int n = in.nextInt();
        int[] arr1 = new int[m];
        int[] arr2 = new int[n];

        // Read the first array
        for (int i = 0; i < m; i++) {
            arr1[i] = in.nextInt();
        }

        // Read the second array
        for (int j = 0; j < n; j++) {
            arr2[j] = in.nextInt();
        }

        // Call the merge method and print the result
        System.out.println(Arrays.toString(mergeTwoSortedArrayInThird(arr1, arr2, m, n)));

        in.close();
    }

    /*-
     * Approach: Two-pointer Technique
     * 
     * Intuition:
     * - Both input arrays are sorted, so we can merge them efficiently using
     *   a two-pointer approach. This avoids the need for sorting the merged
     *   array afterward.
     * - We maintain three pointers: one for each input array and one for the output array.
     *   Compare the elements pointed by the pointers in `arr1` and `arr2`, and add the
     *   smaller element to the output array. Advance the respective pointer.
     * - After processing both arrays, append any remaining elements from `arr1` or `arr2`.
     * 
     * Time Complexity:
     * - O(m + n), where `m` and `n` are the sizes of the input arrays.
     * 
     * Space Complexity:
     * - O(m + n), for storing the output array.
     * 
     * @param arr1 The first sorted array.
     * @param arr2 The second sorted array.
     * @param m The size of the first array.
     * @param n The size of the second array.
     * @return A sorted array `arr3` of size `m + n`.
     */
    public static int[] mergeTwoSortedArrayInThird(int[] arr1, int[] arr2, int m, int n) {
        int[] out = new int[m + n]; // Output array to store merged elements
        int i = 0; // Pointer for arr1
        int j = 0; // Pointer for arr2
        int k = 0; // Pointer for output array

        // Merge the arrays while elements remain in both
        while (i < m && j < n) {
            if (arr1[i] <= arr2[j]) {
                out[k++] = arr1[i++];
            } else {
                out[k++] = arr2[j++];
            }
        }

        // Add remaining elements from arr1
        while (i < m) {
            out[k++] = arr1[i++];
        }

        // Add remaining elements from arr2
        while (j < n) {
            out[k++] = arr2[j++];
        }

        return out;
    }
}
