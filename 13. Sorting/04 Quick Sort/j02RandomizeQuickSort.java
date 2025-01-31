/**
 * Problem Statement:
 * 
 *     Implement the Randomized QuickSort algorithm to sort an array of integers.
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
 *     5
 *     4 2 6 9 1
 * 
 *     Output:
 *     1 2 4 6 9
 * 
 *     Explanation:
 *     - The array is sorted using the Randomized QuickSort algorithm.
 *     - A pivot is randomly selected to improve performance in worst-case scenarios.
 *     - The partitioning step places elements smaller than the pivot to its left 
 *       and elements greater than the pivot to its right.
 */

import java.util.Scanner;

public class j02RandomizeQuickSort {

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
     * Approach: Randomized QuickSort
     * 
     * Intuition:
     * - QuickSort is a divide-and-conquer sorting algorithm that selects a pivot element, 
     *   partitions the array around it, and then recursively sorts the left and right parts.
     * - The problem with normal QuickSort is that if the array is already sorted or has 
     *   many duplicate values, its worst-case complexity becomes O(n^2).
     * - To avoid this, we introduce **randomization**, where we randomly pick a pivot 
     *   element before partitioning, reducing the probability of encountering worst-case scenarios.
     * 
     * Time Complexity:
     * - **Best/Average Case:** O(n log n) (When the partitioning is balanced)
     * - **Worst Case:** O(n^2) (Happens rarely due to randomization)
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

        // Randomizing pivot to avoid worst-case complexity
        randomize(nums, left, right);

        // Partitioning the array and getting pivot index
        int mid = partition(nums, left, right);

        // Recursively sort left and right partitions
        quickSort(nums, left, mid - 1);
        quickSort(nums, mid + 1, right);
    }

    /**
     * Partition Method: Lomuto Partition Scheme
     * 
     * - Selects the last element as pivot.
     * - Rearranges elements such that elements <= pivot are on the left 
     *   and elements > pivot are on the right.
     * 
     * @param nums The input array of numbers.
     * @param left The left boundary index.
     * @param right The right boundary index (pivot).
     * @return The partition index.
     */
    public static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        int j = left;

        while (j <= right) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
            j++;
        }
        return i - 1; // Final pivot position
    }

    /**
     * Randomize Pivot Selection
     * 
     * - Randomly selects an index in the range [left, right].
     * - Swaps it with the last element to ensure a randomized pivot.
     * - Reduces the chance of encountering worst-case O(n^2) scenarios.
     * 
     * @param nums The input array.
     * @param left The left index.
     * @param right The right index.
     */
    public static void randomize(int[] nums, int left, int right) {
        int range = right - left + 1;
        int randIndex = (int) (Math.random() * range + left);
        swap(nums, right, randIndex);
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
