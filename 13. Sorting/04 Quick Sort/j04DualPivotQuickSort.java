/**
 * Problem Statement:
 * 
 *     Implement the Dual-Pivot QuickSort algorithm to sort an array of integers.
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
 *     9 4 7 3 10 5
 * 
 *     Output:
 *     3 4 5 7 9 10
 * 
 *     Explanation:
 *     - The array is sorted using Dual-Pivot QuickSort.
 *     - This method selects two pivots instead of one, leading to a more balanced partition.
 *     - The array is divided into three parts:
 *       - Elements smaller than the left pivot.
 *       - Elements between the left and right pivots.
 *       - Elements greater than the right pivot.
 */

import java.util.Scanner;

public class j04DualPivotQuickSort {

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
     * Approach: Dual-Pivot QuickSort
     * 
     * Intuition:
     * - Traditional QuickSort uses one pivot, dividing the array into two parts.
     * - Dual-Pivot QuickSort selects two pivots:
     *   - A left pivot (smallest of two pivots).
     *   - A right pivot (largest of two pivots).
     * - The array is partitioned into three segments:
     *   - Elements less than the left pivot.
     *   - Elements between the two pivots.
     *   - Elements greater than the right pivot.
     * - This results in better partitioning, reducing the depth of recursion.
     * 
     * Time Complexity:
     * - **Best/Average Case:** O(n log n) (Better balanced partitions)
     * - **Worst Case:** O(n^2) (If partitions are unbalanced)
     * 
     * Space Complexity:
     * - O(log n) (Recursive stack space)
     * 
     * @param nums The input array of numbers.
     * @param left The left boundary index of the partition.
     * @param right The right boundary index of the partition.
     */
    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;

        // Partitioning the array and getting two pivot indices
        int[] indexes = partition(nums, left, right);

        // Recursively sort left, middle, and right partitions
        quickSort(nums, left, indexes[0] - 1);
        quickSort(nums, indexes[0] + 1, indexes[1] - 1);
        quickSort(nums, indexes[1] + 1, right);
    }

    /**
     * Partition Method: Dual-Pivot Partitioning
     * 
     * - Selects two pivots: leftPivot and rightPivot.
     * - Partitions the array into three sections:
     *   - Elements less than leftPivot.
     *   - Elements between leftPivot and rightPivot.
     *   - Elements greater than rightPivot.
     * - Swaps pivots to correct positions after partitioning.
     * 
     * @param nums The input array of numbers.
     * @param left The left boundary index.
     * @param right The right boundary index.
     * @return An array containing the indices of the two pivots after partitioning.
     */
    public static int[] partition(int[] nums, int left, int right) {
        // Ensure left pivot is smaller than right pivot
        if (nums[left] > nums[right])
            swap(nums, left, right);

        int leftPivot = nums[left];
        int rightPivot = nums[right];

        int i = left + 1; // Index for elements smaller than left pivot
        int j = right - 1; // Index for elements greater than right pivot
        int k = left + 1; // Index for traversal

        while (k <= j) {
            if (nums[k] < leftPivot) {
                swap(nums, k, i);
                i++;
            } else if (nums[k] >= rightPivot) {
                while (k < j && nums[j] > rightPivot)
                    j--;
                swap(nums, k, j);
                j--;
                if (nums[k] < leftPivot) {
                    swap(nums, k, i);
                    i++;
                }
            }
            k++;
        }
        i--; // Move left pivot to its correct position
        j++; // Move right pivot to its correct position

        swap(nums, left, i);
        swap(nums, right, j);

        return new int[] { i, j }; // Return indices of left and right pivots
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
