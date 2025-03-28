/**
 * Problem Statement:
 * 
 *     Implement the Three-Way Partition QuickSort algorithm to sort an array of integers.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - The sorted array in ascending order.
 * 
 * Example:
 *     Input:
 *     6
 *     4 9 4 2 4 7
 * 
 *     Output:
 *     2 4 4 4 7 9
 * 
 *     Explanation:
 *     - The array is sorted using Three-Way Partition QuickSort.
 *     - This method partitions the array into three parts:
 *       - Elements smaller than the pivot.
 *       - Elements equal to the pivot.
 *       - Elements greater than the pivot.
 *     - This approach efficiently handles duplicate values.
 */

import java.util.Scanner;

public class j03ThreeWayPartitionQuickSort {

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

    /**
     * Approach: Three-Way Partition QuickSort
     * 
     * Intuition:
     * - Traditional QuickSort does not handle duplicate elements efficiently.
     * - The Three-Way Partitioning technique divides the array into three sections:
     *   - Elements less than the pivot.
     *   - Elements equal to the pivot.
     *   - Elements greater than the pivot.
     * - This ensures that duplicate elements are not repeatedly compared in recursive calls.
     * 
     * Time Complexity:
     * - **Best/Average Case:** O(n log n) (Efficient partitioning)
     * - **Worst Case:** O(n^2) (Rare, when all elements are the same)
     * 
     * Space Complexity:
     * - O(log n) (Recursive stack space for calls)
     * 
     * @param nums The input array of numbers.
     * @param left The left boundary index of the partition.
     * @param right The right boundary index of the partition.
     */
    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;

        // Partitioning the array and getting pivot index range
        int[] indexes = partition(nums, left, right);

        // Recursively sort left and right partitions
        quickSort(nums, left, indexes[0] - 1);
        quickSort(nums, indexes[1] + 1, right);
    }

    /**
     * Partition Method: Dutch National Flag Algorithm
     * 
     * - Selects the last element as pivot.
     * - Partitions the array into three sections:
     *   - Elements less than the pivot.
     *   - Elements equal to the pivot.
     *   - Elements greater than the pivot.
     * - Efficiently handles duplicate elements.
     * 
     * @param nums The input array of numbers.
     * @param left The left boundary index.
     * @param right The right boundary index (pivot).
     * @return An array containing the start and end indices of the middle partition.
     */
    public static int[] partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        int j = right;
        int k = left;

        while (k <= j) {
            if (nums[k] < pivot) {
                swap(nums, k, i);
                i++;
                k++;
            } else if (nums[k] > pivot) {
                swap(nums, k, j);
                j--;
            } else {
                k++;
            }
        }
        return new int[] { i, k - 1 }; // Start and end of pivot elements range
    }

    /**
     * Swap Helper Function
     * 
     * - Swaps two elements in an array.
     * 
     * @param nums The input array.
     * @param i The first index.
     * @param j The second index.
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
