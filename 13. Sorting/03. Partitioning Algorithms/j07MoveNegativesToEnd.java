/*-
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to rearrange the array such that all the negative numbers 
 *     are moved to the end of the array while maintaining the relative order of the elements.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (−10^5 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The array is modified such that all non-negative numbers appear first, followed by all the negative numbers.
 * 
 * Example:
 *     Input:
 *     6
 *     1 -2 3 -4 5 -6
 *     Output:
 *     [1, 3, 5, -4, -2, -6]
 * 
 *     Explanation:
 *     - The non-negative numbers (1, 3, 5) appear first in the same relative order.
 *     - The negative numbers (-4, -2, -6) appear after the non-negative numbers.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j07MoveNegativesToEnd {

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

        // Calling the efficient sorting method
        segregateElementsEfficient(arr);

        // Output the rearranged array
        System.out.println("Rearranged array: " + Arrays.toString(arr));

        in.close();
    }

    /*-
     * Approach 1: Naive Partitioning Approach (In-Place)
     * 
     * Intuition:
     * - The idea is to keep track of where the first non-negative number should be placed.
     * - We iterate through the array, and for each non-negative element found, we shift all the preceding 
     *   elements one position to the right and place the current non-negative element in its correct position.
     * - This approach takes O(n^2) time due to the shifting of elements inside the loop.
     * 
     * Time Complexity:
     * - O(n^2): Due to the inner loop used to shift elements.
     * 
     * Space Complexity:
     * - O(1): In-place partitioning without requiring extra memory.
     * 
     * @param arr The input array.
     */
    public static void segregateElementsPartition(int[] arr) {
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] < 0)
                continue;

            int val = arr[j];
            for (int idx = j; idx > i; idx--) {
                arr[idx] = arr[idx - 1];
            }
            arr[i] = val;
            i++;
        }
    }

    /**
     * Approach 2: Insertion Sort Based Partitioning
     * 
     * Intuition:
     * - This method uses an insertion sort-like approach to move negative elements to the end.
     * - It iterates through the array and for each positive element, it shifts the negative elements to the right.
     * - This ensures that all negative elements are moved to the end while maintaining the order of positive elements.
     * 
     * Time Complexity:
     * - O(n^2) in the worst case due to the nested loops.
     * 
     * Space Complexity:
     * - O(1) as it performs partitioning in place.
     * 
     * @param arr The input array.
     */
    public static void segregateElementsInsertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) continue;
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] < 0) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    /*-
     * Approach 3: Efficient Two-Pass Approach (Using Extra Space)
     * 
     * Intuition:
     * - We use an auxiliary array `temp` to store all the non-negative elements first and then the negative ones.
     * - This approach is faster as we traverse the array twice, first to copy non-negative numbers and then to copy 
     *   negative numbers. It avoids the need for shifting elements in-place.
     * 
     * Time Complexity:
     * - O(n): Two traversals of the array.
     * 
     * Space Complexity:
     * - O(n): An auxiliary array `temp` is used.
     * 
     * @param arr The input array.
     */
    public static void segregateElementsEfficient(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        int idx = 0;
        // First pass: Copy non-negative elements
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0)
                temp[idx++] = arr[i];
        }
        // Second pass: Copy negative elements
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0)
                temp[idx++] = arr[i];
        }

        // Copy the elements back to the original array
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }

    /*-
     * Helper Function: Swap
     * 
     * Intuition:
     * - This function swaps the elements at two given indices in the array.
     * 
     * Time Complexity:
     * - O(1): Single operation to exchange two elements.
     * 
     * @param arr The input array.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
