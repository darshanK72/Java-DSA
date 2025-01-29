/*-
 * Problem Statement:
 * 
 *      Given an array and a pivot element, rearrange the array such that all elements less than 
 *      or equal to the pivot appear before all elements greater than the pivot. The order of 
 *      elements relative to each other within the partitions is not required to be maintained.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `pivot` representing the pivot element.
 * 
 * Output:
 *     - A rearranged version of the array such that all elements <= pivot come before elements > pivot.
 * 
 * Example:
 *     Input:
 *         Enter the size of the array: 7
 *         Enter the elements of the array: 3 9 2 7 6 5 8
 *         Enter the pivot element: 5
 *     Output:
 *         Sorted array: [3, 2, 5, 7, 6, 9, 8]
 * 
 *     Explanation:
 *         The elements less than or equal to the pivot (5) are moved to the left:
 *         [3, 2, 5], while elements greater than the pivot [7, 6, 9, 8] are moved to the right.
 *         The relative order of elements within these groups may vary.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j01PartitioningAroundPivotUnstable {

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
        System.out.print("Enter the pivot element: ");
        int pivot = in.nextInt();

        // Calling the sorting method
        sortAroundPivot(arr, pivot);

        // Output the rearranged array
        System.out.println("Sorted array: " + Arrays.toString(arr));

        in.close();
    }

    /*-
     * Approach 1: Partition Around Pivot
     *  
     * Intuition:
     * - This approach divides the array into two partitions: one with elements less than 
     *   or equal to the pivot and the other with elements greater than the pivot.
     * - By maintaining two pointers, `left` and `right`, we can scan the array once and 
     *   swap elements to ensure the desired arrangement.
     * 
     * Why This Works:
     * - Whenever an element less than or equal to the pivot is encountered, it is swapped 
     *   with the element at the `left` pointer, which ensures all elements up to `left - 1` 
     *   are <= pivot.
     * - The `right` pointer traverses the array, and the process continues until all elements 
     *   are checked.
     * 
     * Time Complexity:
     * - O(n): The array is traversed once.
     * 
     * Space Complexity:
     * - O(1): Sorting is performed in place without requiring additional space.
     * 
     * @param arr The input array to be partitioned.
     * @param pivot The pivot element around which the array is sorted.
     */
    public static void sortAroundPivot(int[] arr, int pivot) {
        int left = 0;
        int right = 0;
        // Traverse the array with two pointers
        while (right < arr.length) {
            if (arr[right] <= pivot) {
                // Swap elements if current element <= pivot
                swap(arr, right, left);
                left++; // Move the left pointer to the next element
            }
            // Move the right pointer to the next element
            right++;

        }
    }

    /**
     * Approach: Dutch National Flag Algorithm
     * 
     * Intuition:
     * - This method partitions the array into three parts:
     *   - Elements less than the pivot.
     *   - Elements equal to the pivot.
     *   - Elements greater than the pivot.
     * - It uses three pointers: low, high, and current to achieve this partitioning.
     * 
     * Time Complexity:
     * - O(n), where n is the number of elements in the array.
     * 
     * Space Complexity:
     * - O(1), as it performs partitioning in place.
     * 
     * Difference:
     * - `sortAroundPivot` partitions the array into two parts: elements less than or equal to the pivot and elements greater than the pivot.
     * - `duchNationalFlag` partitions the array into three parts: elements less than the pivot, elements equal to the pivot, and elements greater than the pivot.
     * 
     * @param arr The input array to be partitioned.
     * @param pivot The pivot element.
     */
    public static void duchNationalFlag(int[] arr, int pivot) {
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
