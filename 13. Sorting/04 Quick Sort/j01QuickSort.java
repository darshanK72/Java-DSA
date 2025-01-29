/*-
 * Problem Statement:
 * 
 *     Implement the QuickSort algorithm to sort an array of integers.
 *     The array must be sorted in ascending order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - A sorted array in ascending order.
 * 
 * Example:
 *     Input:
 *     6
 *     3 7 8 5 2 1
 *     
 *     Output:
 *     1 2 3 5 7 8
 *     
 *     Explanation:
 *     The array is sorted in ascending order using the QuickSort algorithm.
 */

import java.util.Scanner;

public class j01QuickSort {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] nums = new int[n];

        // Input: elements of the array
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        // Call the quickSort method to sort the array
        quickSort(nums, 0, n - 1);

        // Output the sorted array
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }

        in.close();
    }

    /*-
     * Approach: Quick Sort Algorithm
     * 
     * Intuition:
     * - Quick Sort is a divide and conquer algorithm. It works by selecting a pivot element
     *   and partitioning the array into two halves:
     *   - Elements less than or equal to the pivot are placed on the left side.
     *   - Elements greater than the pivot are placed on the right side.
     * - The algorithm recursively sorts the left and right subarrays.
     * 
     * Steps:
     * 1. Select a pivot element (typically the last element).
     * 2. Partition the array such that elements smaller than or equal to the pivot
     *    are moved to the left, and elements greater than the pivot are moved to the right.
     * 3. Recursively sort the left and right subarrays.
     * 
     * Time Complexity:
     * - O(n log n) on average: The array is divided into two halves, and the partitioning process
     *   takes linear time.
     * - O(n^2) in the worst case: When the pivot is not balanced (e.g., already sorted array).
     * 
     * Space Complexity:
     * - O(log n): The recursive calls stack uses O(log n) space.
     * 
     * @param nums The input array to be sorted.
     * @param left The left index of the array/subarray.
     * @param right The right index of the array/subarray.
     */
    static void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int mid = partition(nums, left, right);
        quickSort(nums, left, mid - 1); // Recursively sort the left subarray
        quickSort(nums, mid + 1, right); // Recursively sort the right subarray
    }

    /*-
     * Helper Function: Partition
     * 
     * Intuition:
     * - The partition function places the pivot element in its correct position in the sorted array,
     *   and rearranges elements around it.
     * - Elements less than or equal to the pivot are moved to the left of the pivot.
     * - Elements greater than the pivot are moved to the right.
     * 
     * Time Complexity:
     * - O(n): We scan the array once, performing comparisons and swaps.
     * 
     * @param nums The array to be partitioned.
     * @param left The left index.
     * @param right The right index.
     * @return The index of the pivot element after partitioning.
     */
    static int partition(int[] nums, int left, int right) {
        int pivot = nums[right]; // Choose the last element as pivot
        int i = left;
        int j = left;

        // Traverse the array and rearrange elements
        while (j <= right) {
            if (nums[j] <= pivot) {
                swap(nums, i, j); // Swap elements if they are smaller than or equal to pivot
                i++;
            }
            j++;
        }
        return i - 1; // Return the pivot's final position
    }

    /*-
     * Helper Function: Swap
     * 
     * Intuition:
     * - The swap function exchanges the values of two elements in the array.
     * 
     * Time Complexity:
     * - O(1): The swap operation is a constant-time action.
     * 
     * @param nums The input array.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
