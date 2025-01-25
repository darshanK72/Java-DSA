
/**
 * Problem Statement:
 * 
 *     Implement the Bubble Sort algorithm to sort an array in ascending order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The input array `arr` sorted in ascending order.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [5, 3, 8, 4, 2]
 *     Output:
 *         arr = [2, 3, 4, 5, 8]
 * 
 *     Explanation:
 *         The Bubble Sort algorithm iteratively compares adjacent elements and swaps them
 *         if they are in the wrong order. The process continues until the entire array
 *         is sorted.
 */

import java.util.Scanner;
import java.util.Arrays;

public class j01BubbleSort {

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

        // Calling the Bubble Sort method
        bubbleSort(arr);

        // Output the sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach 1: Bubble Sort Algorithm
     * 
     * Intuition:
     * - Bubble Sort is a simple sorting algorithm that works by repeatedly
     * stepping through the array, comparing adjacent elements, and swapping
     * them if they are in the wrong order. This process is repeated until
     * the array is sorted.
     * - Each pass through the array places the next largest element in its
     * correct position, "bubbling" it to the top.
     * - To optimize, we track if any swaps were made in a pass. If no swaps
     * are made, the array is already sorted, and we can exit early.
     * 
     * Time Complexity:
     * - Worst Case: O(n^2) when the array is sorted in reverse order.
     * - Best Case: O(n) when the array is already sorted.
     * 
     * Space Complexity:
     * - O(1) as it performs sorting in place.
     * 
     * @param arr The input array to be sorted.
     */
    public static void bubbleSort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            int swapped = 0; // Track if any swaps occurred in this pass

            for (int j = 0; j < arr.length - i - 1; j++) {
                // Compare adjacent elements
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1); // Swap if elements are in the wrong order
                    swapped++;
                }
            }

            // If no swaps occurred, the array is already sorted
            if (swapped == 0)
                break;
        }
    }

    /**
     * Approach 2: Recursive Bubble Sort
     * 
     * Intuition:
     * - This method uses recursion to sort the array.
     * - Each recursive call processes one pass through the array, placing the next largest element in its correct position.
     * - The base case is when the array size reduces to 1, meaning the array is sorted.
     * 
     * Time Complexity:
     * - Worst Case: O(n^2) when the array is sorted in reverse order.
     * - Best Case: O(n) when the array is already sorted.
     * 
     * Space Complexity:
     * - O(n) due to the recursion stack.
     * 
     * @param arr The input array to be sorted.
     * @param n The size of the array to be sorted.
     */
    public static void bubbleSortRec(int arr[], int n) {
        // Base case: If the array size is 1, it's already sorted
        if (n == 1) {
            return;
        }
        // One pass of bubble sort
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
        // Recursive call for the remaining array
        bubbleSortRec(arr, n - 1);
    }

    /**
     * Helper Method: Swaps two elements in the array.
     * 
     * @param arr The array in which elements are to be swapped.
     * @param i   The index of the first element.
     * @param j   The index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
