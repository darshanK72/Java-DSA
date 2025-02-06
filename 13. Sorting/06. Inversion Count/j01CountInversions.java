/**
 * Problem Statement:
 * 
 *     Given an array of integers, find the number of inversions in the array.
 *     An inversion is a pair of indices (i, j) such that i < j and arr[i] > arr[j].
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^9).
 * 
 * Output:
 *     - An integer representing the number of inversions in the array.
 * 
 * Example:
 *     Input:
 *     5
 *     2 4 1 3 5
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The inversions are:
 *     (2, 1) at indices (0, 2)
 *     (4, 1) at indices (1, 2)
 *     (4, 3) at indices (1, 3)
 */

import java.util.Scanner;

public class j01CountInversions {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call Approach 1: Brute Force
        System.out.println("Approach 1: Brute Force");
        System.out.println("Number of Inversions: " + inversionCountBruteForce(arr));

        // Call Approach 2: Merge Sort
        System.out.println("Approach 2: Merge Sort");
        System.out.println("Number of Inversions: " + inversionCountMergeSort(arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - The simplest way to count inversions is to check every possible pair (i, j).
     * - If arr[i] > arr[j] and i < j, then we count it as an inversion.
     * - This approach iterates over all possible pairs, comparing each one.
     * 
     * Explanation:
     * - We use a nested loop: the outer loop selects an element, and the inner loop
     *   checks how many elements after it are smaller.
     * - Each time we find a valid inversion, we increment the count.
     * 
     * Time Complexity:
     * - O(n^2), since we have a nested loop iterating over all pairs.
     * 
     * Space Complexity:
     * - O(1), since we use only a single integer variable for counting.
     * 
     * @param arr The input array of numbers.
     * @return The number of inversions in the array.
     */
    public static int inversionCountBruteForce(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Merge Sort (Efficient Approach)
     * 
     * Intuition:
     * - Instead of comparing each pair individually, we use a modified merge sort.
     * - During merging, we count how many times an element from the right subarray
     *   is smaller than an element from the left subarray.
     * - Since the left and right subarrays are sorted, if arr[i] > arr[j],
     *   then all remaining elements in the left subarray also form an inversion.
     * 
     * Explanation:
     * - We use merge sort to break the array into smaller parts.
     * - While merging two sorted halves, if an element from the right half is
     *   smaller than an element from the left half, then all elements remaining
     *   in the left half are also greater, contributing to inversions.
     * - We count these inversions during the merge step.
     * 
     * Time Complexity:
     * - O(n log n), due to the recursive nature of merge sort.
     * 
     * Space Complexity:
     * - O(n), since we use temporary arrays for merging.
     * 
     * @param arr The input array of numbers.
     * @return The number of inversions in the array.
     */
    public static int inversionCountMergeSort(int[] arr) {
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    /**
    * Helper Method: mergeSortAndCount
    * 
    * Intuition:
    * - This method recursively divides the array into smaller halves.
    * - It calls itself for the left and right halves and counts inversions in both.
    * - The key operation happens in `mergeAndCount()`, where cross inversions are counted.
    * 
    * Time Complexity:
    * - O(n log n), as each division step is O(log n) and merging is O(n).
    * 
    * Space Complexity:
    * - O(n), due to the use of temporary arrays during merging.
    * 
    * @param arr The input array.
    * @param left The starting index.
    * @param right The ending index.
    * @return The number of inversions found in this range.
    */
    private static int mergeSortAndCount(int[] arr, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSortAndCount(arr, left, mid);
            count += mergeSortAndCount(arr, mid + 1, right);

            count += mergeAndCount(arr, left, mid, right);
        }
        return count;
    }

    /**
    * Helper Method: mergeAndCount
    * 
    * Intuition:
    * - This method merges two sorted halves of the array while counting inversions.
    * - If an element in the right half is smaller than an element in the left half,
    *   then all remaining elements in the left half contribute to inversions.
    * - This is because the left half and right half are both sorted.
    * 
    * Explanation:
    * - We use two pointers, `i` for the left subarray and `j` for the right subarray.
    * - If `arr[i] <= arr[j]`, then there is no inversion, and we place `arr[i]` in the sorted array.
    * - Otherwise, `arr[i] > arr[j]`, which means all elements remaining in the left subarray
    *   (from index `i` onward) are greater than `arr[j]`, contributing to inversions.
    * 
    * Time Complexity:
    * - O(n), since we merge two sorted halves in linear time.
    * 
    * Space Complexity:
    * - O(n), since we use temporary arrays for merging.
    * 
    * @param arr The input array.
    * @param left The starting index of the left subarray.
    * @param mid The midpoint index.
    * @param right The ending index of the right subarray.
    * @return The number of cross-inversions found.
    */
    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        System.arraycopy(arr, left, leftArr, 0, leftArr.length);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightArr.length);

        int i = 0, j = 0, k = left, swaps = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                swaps += (mid + 1) - (left + i); // Count inversions
            }
        }

        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }

        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }

        return swaps;
    }
}
