/*-
 * Problem Statement:
 * 
 * Implement the Lomuto partitioning scheme for an array with a given pivot index. The array is partitioned 
 * around the pivot element such that all elements smaller than or equal to the pivot appear before it, 
 * and all elements greater than the pivot appear after it.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^9 <= arr[i] <= 10^9).
 *     - An integer `pivotIndex` (0 <= pivotIndex < n), representing the index of the pivot element.
 * 
 * Output:
 *     - The input array rearranged based on Lomuto's partitioning scheme.
 * 
 * Example:
 *     Input:
 *         Enter the size of the array: 7
 *         Enter the elements of the array: 3 2 8 5 1 4 7
 *         Enter the pivot Index: 2
 *     Output:
 *         Sorted array: [3, 2, 1, 4, 5, 7, 8]
 * 
 *     Explanation:
 *         - The pivot element is 8 (from index 2).
 *         - After partitioning, all elements less than or equal to 8 appear before it,
 *           and all elements greater than 8 appear after it.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j03LomutosPartitionScheme {
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
        int[] arr2 = Arrays.copyOf(arr, n);
        System.out.print("Enter the pivot Index: ");
        int pivotIndex = in.nextInt();
        int pivot = in.nextInt();

        System.out.print("Enter the pivot element: ");
        // Calling the partitioning method
        lomutosPartition1(arr, pivotIndex);
        lomutosPartition2(arr2, pivot);

        // Output the rearranged array
        System.out.println("Partitioned array: " + Arrays.toString(arr));
        System.out.println("Partitioned array: " + Arrays.toString(arr2));

        in.close();
    }

    /*-
     * Approach: Lomuto Partitioning Scheme
     * 
     * Intuition:
     * - The Lomuto partitioning algorithm selects a pivot element and rearranges the array such that:
     *   - All elements smaller than or equal to the pivot are placed to its left.
     *   - All elements greater than the pivot are placed to its right.
     * - This is achieved in a single pass through the array using two pointers:
     *   - `i` keeps track of the position for elements <= pivot.
     *   - `j` traverses the array to compare elements with the pivot.
     * 
     * Steps:
     * 1. Move the pivot element to the end of the array.
     * 2. Traverse the array and swap elements smaller than or equal to the pivot to the left.
     * 3. Finally, place the pivot in its correct position.
     * 
     * Why This Works:
     * - The pivot element acts as a reference for partitioning.
     * - The use of two pointers ensures that elements are rearranged in a single traversal.
     * 
     * Time Complexity:
     * - O(n): Single traversal of the array to partition it.
     * 
     * Space Complexity:
     * - O(1): In-place partitioning without requiring extra memory.
     * 
     * @param arr The array to be partitioned.
     * @param pivotIndex The index of the pivot element.
     */
    public static void lomutosPartition1(int[] arr, int pivotIndex) {
        int low = 0;
        int high = arr.length - 1;

        // Move the pivot element to the end of the array
        swap(arr, high, pivotIndex);

        int pivot = arr[high]; // Pivot element
        int i = low - 1;

        // Partitioning process
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // Place the pivot element in its correct position
        swap(arr, high, i + 1);
    }

    /*-
    * Approach: Lomuto Partitioning Scheme (Using Pivot Element)
    * 
    * Intuition:
    * - The Lomuto partitioning algorithm selects a pivot element and rearranges the array such that:
    *   - All elements smaller than or equal to the pivot are placed to its left.
    *   - All elements greater than the pivot are placed to its right.
    * - This method directly uses the pivot element provided as input.
    * 
    * Time Complexity:
    * - O(n), where n is the number of elements in the array.
    * 
    * Space Complexity:
    * - O(1), as it performs partitioning in place.
    * 
    * Difference:
    * - `lomutosPartition1` uses the pivot index to determine the pivot element.
    * - `lomutosPartition2` directly uses the pivot element provided as input.
    * 
    * @param arr The input array to be partitioned.
    * @param pivot The pivot element.
    */
    public static void lomutosPartition2(int[] arr, int pivot) {
        int low = 0; // Points to where the next < pivot element should go
        int high = arr.length - 1; // Points to where the next > pivot element should go
        int current = 0; // Current element being processed

        while (current <= high) {
            if (arr[current] < pivot) {
                // Swap the current element with the low pointer
                swap(arr, low, current);
                low++;
                current++;
            } else if (arr[current] > pivot) {
                // Swap the current element with the high pointer
                swap(arr, current, high);
                high--;
            } else {
                // Move to the next element if it is equal to the pivot
                current++;
            }
        }
    }

    /*-
     * Helper Function: Swap
     * 
     * Intuition:
     * - The swap function exchanges the values of two elements in the array. This is 
     *   used to rearrange elements during the partition process.
     * 
     * Time Complexity:
     * - O(1): Single operation to swap two elements.
     * 
     * @param arr The input array.
     * @param i The index of the first element to be swapped.
     * @param j The index of the second element to be swapped.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
