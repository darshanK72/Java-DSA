/**
 * Problem Statement:
 * 
 *      Implement the Merge Sort algorithm for an array without using a return type. The function 
 *      should directly sort the input array by modifying it in place. Merge Sort is a divide-and-conquer 
 *      algorithm that recursively splits the array, sorts smaller subarrays, and merges them back.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A sorted version of the input array (modified in place).
 * 
 * Example:
 *     Input:
 *         6
 *         5 2 9 1 5 6
 *     Output:
 *         Sorted arr: [1, 2, 5, 5, 6, 9]
 * 
 *     Explanation:
 *         The array [5, 2, 9, 1, 5, 6] is recursively split into smaller subarrays, 
 *         sorted, and then merged back in place to form [1, 2, 5, 5, 6, 9].
 */

import java.util.Arrays;
import java.util.Scanner;

public class j03MergeSortWithoutReturnType {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];

        // Read elements for arr
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Sort the array using Merge Sort
        mergeSort(arr, 0, n - 1);

        // Output the sorted array
        System.out.println("Sorted arr: " + Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach: Merge Sort (In-Place Modification)
     * 
     * Intuition:
     * - This implementation of Merge Sort modifies the input array in place instead of 
     *   returning a new sorted array. By using indices to track subarray boundaries, 
     *   we avoid creating new arrays during recursive calls and perform the sorting 
     *   directly within the input array.
     * 
     * - Why this works:
     *     - The array is divided into smaller subarrays using indices (left and right).
     *     - Each subarray is sorted recursively, and the sorted halves are merged back.
     * 
     * Time Complexity:
     * - O(n log n): The array is divided log n times, and each merge operation takes O(n) time.
     * 
     * Space Complexity:
     * - O(n): A temporary array is used for merging, but no additional arrays are created 
     *   during recursive calls.
     * 
     * @param arr The input array to be sorted.
     * @param i The starting index of the array.
     * @param j The ending index of the array.
     */
    public static void mergeSort(int[] arr, int i, int j) {
        if (i == j) {
            return; // Base case: single element array
        }
        int mid = i + (j - i) / 2;

        // Recursively split and sort the left and right halves
        mergeSort(arr, i, mid);
        mergeSort(arr, mid + 1, j);

        // Merge the sorted halves in place
        merge(arr, i, mid, mid + 1, j);
    }

    /**
     * Helper Function: Merge
     * 
     * Intuition:
     * - Merging two sorted subarrays involves comparing their smallest elements and placing 
     *   the smaller one in the correct position in the merged array. After merging, the 
     *   original array is updated in place with the merged result.
     * 
     * Time Complexity:
     * - O(n): Each element in the subarrays is processed once.
     * 
     * Space Complexity:
     * - O(n): A temporary array is used to hold the merged result.
     * 
     * @param arr The input array containing the subarrays to be merged.
     * @param l1 The starting index of the first subarray.
     * @param r1 The ending index of the first subarray.
     * @param l2 The starting index of the second subarray.
     * @param r2 The ending index of the second subarray.
     */
    public static void merge(int[] arr, int l1, int r1, int l2, int r2) {
        int n = r2 - l1 + 1;
        int[] merged = new int[n];
        int i = l1, j = l2, k = 0;

        // Compare and merge elements from both subarrays
        while (i <= r1 && j <= r2) {
            if (arr[i] <= arr[j]) {
                merged[k++] = arr[i++];
            } else {
                merged[k++] = arr[j++];
            }
        }

        // Copy any remaining elements from the first subarray
        while (i <= r1) {
            merged[k++] = arr[i++];
        }

        // Copy any remaining elements from the second subarray
        while (j <= r2) {
            merged[k++] = arr[j++];
        }

        // Update the original array with the merged result
        for (int idx = l1; idx <= r2; idx++) {
            arr[idx] = merged[idx - l1];
        }
    }
}
