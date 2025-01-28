/**
 * Problem Statement:
 * 
 *      Implement the Merge Sort algorithm for an array. Merge Sort is a divide-and-conquer 
 *      algorithm that splits the input array into smaller subarrays, sorts them individually, 
 *      and then merges them back to produce the sorted array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A sorted version of the input array.
 * 
 * Example:
 *     Input:
 *         6
 *         5 2 9 1 5 6
 *     Output:
 *         Merged arr2: [1, 2, 5, 5, 6, 9]
 * 
 *     Explanation:
 *         The array [5, 2, 9, 1, 5, 6] is divided into smaller subarrays, sorted 
 *         individually, and then merged back to form the sorted array [1, 2, 5, 5, 6, 9].
 */

import java.util.Arrays;
import java.util.Scanner;

public class j02MergeSortWithReturnType {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];

        // Read elements for arr
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Output the sorted array
        System.out.println("Sorted arr: " + Arrays.toString(mergeSort(arr, 0, n - 1)));

        in.close();
    }

    /**
     * Approach: Merge Sort
     * 
     * Intuition:
     * - Merge Sort is a divide-and-conquer algorithm that recursively splits the input array 
     *   into smaller halves until each half contains a single element. These subarrays are 
     *   then merged back together in sorted order.
     * 
     * - Why this works:
     *     - Splitting the array ensures that each smaller array is easier to sort.
     *     - During merging, elements from each sorted half are compared and combined in sorted order.
     * 
     * Time Complexity:
     * - O(n log n): The array is divided into halves log n times, and each merge operation 
     *   takes O(n) time.
     * 
     * Space Complexity:
     * - O(n): Temporary arrays are used for merging.
     * 
     * @param arr The input array to be sorted.
     * @param i The starting index of the array.
     * @param j The ending index of the array.
     * @return The sorted array.
     */
    public static int[] mergeSort(int[] arr, int i, int j) {
        if (i == j) {
            return new int[] { arr[i] }; // Base case: single element array
        }
        int mid = i + (j - i) / 2;

        // Recursively split and sort the left and right halves
        int[] left = mergeSort(arr, i, mid);
        int[] right = mergeSort(arr, mid + 1, j);

        // Merge the sorted halves
        return merge(left, right);
    }

    /**
     * Helper Function: Merge
     * 
     * Intuition:
     * - Merging two sorted arrays involves comparing the smallest elements of each array 
     *   and placing the smaller one in the result array. This ensures the merged array is sorted.
     * 
     * Time Complexity:
     * - O(n): Each element is processed once.
     * 
     * Space Complexity:
     * - O(n): Temporary array is used to store the merged result.
     * 
     * @param arr1 The first sorted array.
     * @param arr2 The second sorted array.
     * @return The merged and sorted array.
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        // Compare and merge elements from both arrays
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        // Copy any remaining elements from arr1
        while (i < arr1.length) {
            merged[k++] = arr1[i++];
        }

        // Copy any remaining elements from arr2
        while (j < arr2.length) {
            merged[k++] = arr2[j++];
        }

        return merged;
    }
}
