/*-
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to find the minimum number of swaps required to sort the array.
 *     We need to count the number of swaps necessary to make the array sorted in ascending order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The minimum number of swaps required to sort the array in ascending order.
 * 
 * Example:
 *     Input:
 *     5
 *     2 3 4 1 5
 *     Output:
 *     2
 * 
 *     Explanation:
 *     In this case, two swaps are required:
 *     Swap 1: Swap 2 with 1 (Array: 1 3 4 2 5)
 *     Swap 2: Swap 3 with 2 (Array: 1 2 4 3 5)
 */

public class j02MimimumSwaps {

    public static void main(String[] args) {
        j02MimimumSwaps obj = new j02MimimumSwaps();

        // Test Case 1
        int[] arr1 = { 2, 3, 4, 1, 5 };
        System.out.println("Minimum swaps for arr1: " + obj.countSwaps(arr1, arr1.length)); // Expected: 2

        // Test Case 2
        int[] arr2 = { 1, 2, 3, 4, 5 };
        System.out.println("Minimum swaps for arr2: " + obj.countSwaps(arr2, arr2.length)); // Expected: 0

        // Test Case 3
        int[] arr3 = { 5, 4, 3, 2, 1 };
        System.out.println("Minimum swaps for arr3: " + obj.countSwaps(arr3, arr3.length)); // Expected: 10

        // Test Case 4
        int[] arr4 = { 1, 5, 3, 4, 2 };
        System.out.println("Minimum swaps for arr4: " + obj.countSwaps(arr4, arr4.length)); // Expected: 3
    }

    /*-
     * Approach: Merge Sort-based Inversion Counting
     * 
     * Intuition:
     * - We use merge sort to count the number of inversions in the array. 
     *   An inversion occurs when a larger element appears before a smaller element in the array.
     * - Every time we merge two subarrays, we count how many times an element from the right subarray
     *   is smaller than an element from the left subarray. This gives the number of swaps needed for sorting.
     * 
     * Time Complexity:
     * - O(n log n), because merge sort divides the array into two halves and performs merging with inversion counting.
     * 
     * Space Complexity:
     * - O(n), as merge sort requires additional space for the left and right subarrays.
     * 
     * @param arr The input array of numbers.
     * @param n The size of the array.
     * @return The minimum number of swaps required to sort the array.
     */
    int countSwaps(int arr[], int n) {
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    /*-
     * Helper function: Performs merge sort and counts the inversions.
     * 
     * Intuition:
     * - This method recursively divides the array into two halves and counts inversions by calling merge and count.
     * - The merge step is responsible for combining the results of the subarrays and counting the inversions that
     *   happen while merging.
     * 
     * Time Complexity:
     * - O(n log n), as this is part of the merge sort algorithm.
     * 
     * @param arr The input array.
     * @param left The starting index of the subarray.
     * @param right The ending index of the subarray.
     * @return The count of swaps (inversions) for this subarray.
     */
    private static int mergeSortAndCount(int[] arr, int left, int right) {
        int count = 0;
        if (left < right) {
            // Find the middle point to divide the array into two halves
            int mid = left + (right - left) / 2;

            // Recursively count inversions in the left half and right half
            count += mergeSortAndCount(arr, left, mid);
            count += mergeSortAndCount(arr, mid + 1, right);

            // Merge the two halves and count inversions that occur during the merge
            count += mergeAndCount(arr, left, mid, right);
        }
        return count;
    }

    /*-
     * Helper function: Merges two subarrays and counts the number of inversions.
     * 
     * Intuition:
     * - This method merges two sorted subarrays into a single sorted array while counting how many inversions
     *   occur. An inversion occurs when an element from the right subarray is smaller than an element from the
     *   left subarray, and this contributes to a swap.
     * 
     * Time Complexity:
     * - O(n), as the merging process takes linear time.
     * 
     * @param arr The input array.
     * @param left The starting index of the subarray.
     * @param mid The middle index where the array is divided.
     * @param right The ending index of the subarray.
     * @return The number of inversions (swaps).
     */
    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        // Create temporary arrays to store left and right subarrays
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArr, 0, leftArr.length);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightArr.length);

        int i = 0, j = 0, k = left, swaps = 0;

        // Merge the two subarrays while counting inversions
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                // Count how many elements are remaining in the left subarray,
                // as each of these elements forms an inversion with the element from the right
                // subarray.
                swaps += (mid + 1) - (left + i); // Count inversions
            }
        }

        // Copy any remaining elements from the left subarray
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }

        // Copy any remaining elements from the right subarray
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }

        return swaps;
    }
}
