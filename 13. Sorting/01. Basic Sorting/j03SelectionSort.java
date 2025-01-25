
/**
 * Problem Statement:
 * 
 *     Implement the Selection Sort algorithm to sort an array of integers in ascending order.
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
 *         64 25 12 22 11
 *     Output:
 *         11 12 22 25 64
 * 
 *     Explanation:
 *     - The selection sort algorithm works by finding the smallest element in the unsorted portion
 *       of the array and swapping it with the first element of the unsorted portion. This process
 *       is repeated until the entire array is sorted.
 */

import java.util.Scanner;

public class j03SelectionSort {

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
        selectionSort(arr);
        // Output the sorted array
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        in.close();
    }


    /**
     * Approach 1: Selection Sort
     * 
     * Intuition:
     * - The Selection Sort algorithm divides the array into two parts: sorted and
     * unsorted.
     * - It repeatedly finds the minimum element from the unsorted portion of the
     * array
     * and swaps it with the first element of the unsorted portion.
     * - This ensures that with each iteration, one additional element is placed in
     * its correct position.
     * 
     * Time Complexity:
     * - Best Case: O(n^2), even if the array is already sorted, as it still
     * compares every element.
     * - Worst Case: O(n^2), as the number of comparisons is the same regardless of
     * input.
     * 
     * Space Complexity:
     * - O(1), as the sorting is performed in place without requiring extra space.
     * 
     * Optimization:
     * - While Selection Sort inherently has O(n^2) comparisons, an optimization can
     * be
     * to stop swapping when the minimum element is already in the correct position.
     * - However, this optimization does not improve time complexity but may reduce
     * the number of swaps.
     * 
     * @param arr The input array to be sorted.
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i; // Assume the first unsorted element is the minimum
            for (int j = i + 1; j < arr.length; j++) {
                // Find the actual minimum element in the unsorted portion
                if (arr[j] < arr[min])
                    min = j;
            }
            // Swap only if the found minimum is not already in the correct position
            if (min != i) {
                swap(arr, i, min);
            }
        }
    }

    /**
     * Approach 2: Recursive Selection Sort
     * 
     * Intuition:
     * - This method uses recursion to sort the array.
     * - Each recursive call processes one pass through the array, finding the maximum element in the unsorted portion and placing it at the end.
     * - The base case is when the array size reduces to 1, meaning the array is sorted.
     * 
     * Time Complexity:
     * - Worst Case: O(n^2) due to the nested loops.
     * - Best Case: O(n^2) as the algorithm always performs the same number of comparisons.
     * 
     * Space Complexity:
     * - O(n) due to the recursion stack.
     * 
     * @param arr The input array to be sorted.
     * @param n The size of the array to be sorted.
     */
    public static void selectionSortRec(int[] arr, int n) {
        if (n <= 1)
            return;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > arr[max])
                max = i;
        }
        swap(arr, max, n - 1);

        selectionSortRec(arr, n - 1);
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
