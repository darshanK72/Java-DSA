/**
 * Problem Statement:
 * 
 *     Implement the Insertion Sort algorithm to sort an array of integers in ascending order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The input array sorted in ascending order.
 * 
 * Example:
 *     Input:
 *         5
 *         12 11 13 5 6
 *     Output:
 *         5 6 11 12 13
 * 
 *     Explanation:
 *     - The Insertion Sort algorithm iteratively builds the sorted portion of the array by
 *       taking one element from the unsorted portion and placing it at the correct position
 *       in the sorted portion.
 */

import java.util.Scanner;

public class j04InsertionSort {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        insertionSort1(arr);
        // Output the sorted array
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        in.close();
    }

    /**
     * Approach 1: Insertion Sort (With Swapping)
     * 
     * Intuition:
     * - The algorithm iterates through the array starting from the second element.
     * - For each element, it compares it with the elements in the sorted portion (to its left).
     * - If the current element is smaller than the compared element, it swaps them.
     * - This process continues until the current element is in its correct position.
     * - The sorted portion grows one element at a time, maintaining the order.
     * 
     * Time Complexity:
     * - Best Case: O(n), when the array is already sorted.
     * - Worst Case: O(n^2), when the array is sorted in reverse order.
     * 
     * Space Complexity:
     * - O(1), as it sorts the array in place.
     */
    public static void insertionSort1(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Approach 2: Insertion Sort (Without Swapping)
     * 
     * Intuition:
     * - The algorithm treats the first element as a sorted portion.
     * - For each subsequent element, it iterates backward through the sorted portion to find
     *   the correct position for the current element and inserts it.
     * - The sorted portion grows one element at a time, maintaining the order.
     * 
     * Time Complexity:
     * - Best Case: O(n), when the array is already sorted.
     * - Worst Case: O(n^2), when the array is sorted in reverse order.
     * 
     * Space Complexity:
     * - O(1), as the sorting is performed in place without requiring extra space.
     * 
     * Optimization:
     * - Instead of using a nested loop for swapping, the algorithm can shift elements in
     *   the sorted portion until the correct position is found, reducing the number of swaps.
     * 
     * @param arr The input array to be sorted.
     */
    public static void insertionSort2(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // Store the current element
            int j = i - 1;

            // Shift elements of the sorted portion to the right to make space for the key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Place the key in its correct position
            arr[j + 1] = key;
        }
    }

    /**
     * Approach 3: Recursive Insertion Sort
     * 
     * Intuition:
     * - This method uses recursion to sort the array.
     * - Each recursive call processes one element, placing it in its correct position in the sorted portion.
     * - The base case is when the array size reduces to 1, meaning the array is sorted.
     * 
     * Time Complexity:
     * - Worst Case: O(n^2) due to the nested loops.
     * - Best Case: O(n) when the array is already sorted.
     * 
     * Space Complexity:
     * - O(n) due to the recursion stack.
     * 
     * @param arr The input array to be sorted.
     * @param n The size of the array to be sorted.
     */
    public static void insertionSortRec(int[] arr, int n) {
        if (n <= 1) return;

        // Sort the first n-1 elements
        insertionSortRec(arr, n - 1);

        // Insert the last element at its correct position in the sorted array
        int last = arr[n - 1];
        int j = n - 2;
        while (j >= 0 && arr[j] > last) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = last;
    }

    /**
     * Helper Method: Swap
     * 
     * Swaps two elements in the array.
     * 
     * @param arr The array in which elements are to be swapped.
     * @param i   Index of the first element.
     * @param j   Index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
