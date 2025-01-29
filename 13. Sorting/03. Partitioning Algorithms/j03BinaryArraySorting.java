/**
 * Problem Statement:
 * 
 *      Given a binary array (containing only 0s and 1s), sort the array such that all 0s appear 
 *      before all 1s. The relative order of elements is not required to be maintained.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies arr[i] ∈ {0, 1}.
 * 
 * Output:
 *     - A rearranged version of the array such that all 0s come before all 1s.
 * 
 * Example:
 *     Input:
 *         Enter the size of the array: 7
 *         Enter the elements of the array: 1 0 1 1 0 0 1
 *     Output:
 *         Sorted array: [0, 0, 0, 1, 1, 1, 1]
 * 
 *     Explanation:
 *         The 0s are moved to the left: [0, 0, 0], and the 1s are moved to the right: [1, 1, 1, 1].
 *         The relative order of elements is not preserved.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j03BinaryArraySorting {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = in.nextInt(); // Input: size of the array

        int[] arr = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Calling the sorting method
        binSort(arr);

        // Output the rearranged array
        System.out.println("Sorted array: " + Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach: Two Pointer Technique
     * 
     * Intuition:
     * - The problem can be visualized as partitioning the array into two groups:
     *   one group containing only 0s and the other containing only 1s.
     * - Using two pointers, `left` and `right`, traverse the array from left to right:
     *   - If a 0 is encountered, it is swapped to the current position of `left`, and 
     *     `left` is incremented.
     *   - If a 1 is encountered, simply increment `right` without making changes.
     * - This ensures all 0s are grouped at the beginning and 1s at the end.
     * 
     * Why This Works:
     * - The `left` pointer keeps track of the position where the next 0 should be placed.
     * - The `right` pointer traverses the array, ensuring all elements are checked.
     * 
     * Time Complexity:
     * - O(n): Each element is processed once.
     * 
     * Space Complexity:
     * - O(1): The sorting is performed in place without requiring additional memory.
     * 
     * @param arr The binary array to be sorted.
     */
    public static void binSort(int[] arr) {
        int left = 0; // Pointer for the position to place the next 0
        int right = 0; // Pointer for traversing the array

        while (right < arr.length) {
            if (arr[right] == 0) {
                // Swap the current element with the position indicated by `left`
                swap(arr, right, left);
                left++;
            }
            right++;
        }
    }

    /**
     * Helper Function: Swap
     * 
     * Intuition:
     * - This function swaps the elements at two given indices in the array.
     * 
     * Time Complexity:
     * - O(1): Single operation to exchange two elements.
     * 
     * @param arr The input array.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
