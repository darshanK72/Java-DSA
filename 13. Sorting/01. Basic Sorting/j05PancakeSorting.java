/**
 * Problem Statement:
 * 
 *     Implement the Pancake Sorting algorithm to sort an array of integers in ascending order.
 *     Pancake Sorting is a process in which the maximum element in the unsorted portion of the array
 *     is brought to the front by flipping the array at its position. Then, the maximum element is moved
 *     to its correct position by flipping the entire unsorted portion. This process is repeated until
 *     the array is fully sorted.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^3), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^3).
 * 
 * Output:
 *     - A list of integers representing the flip positions (1-based indexing) to sort the array.
 * 
 * Example:
 *     Input:
 *         4
 *         3 2 4 1
 *     Output:
 *         [3, 4, 2, 3, 1, 2]
 * 
 *     Explanation:
 *     - Flip the first 3 elements: [4, 2, 3, 1].
 *     - Flip all 4 elements: [1, 3, 2, 4].
 *     - Flip the first 2 elements: [3, 1, 2, 4].
 *     - Flip the first 3 elements: [2, 1, 3, 4].
 *     - Flip the first element: [1, 2, 3, 4].
 *     - Flip the first 2 elements: [1, 2, 3, 4].
 */

import java.util.ArrayList;
import java.util.List;

public class j05PancakeSorting {

    public static void main(String[] args) {
        // Reading input
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Create an instance of the class and call the pancakeSort method
        j05PancakeSorting sorter = new j05PancakeSorting();
        List<Integer> flips = sorter.pancakeSort(arr);

        // Output the sorted array
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Output the flip positions
        System.out.println("Flip positions:");
        System.out.println(flips);

        in.close();
    }

    /**
     * Approach: Pancake Sorting
     * 
     * Intuition:
     * - The algorithm identifies the maximum element in the unsorted portion of the array
     *   and brings it to the front using a "flip" (reverse operation).
     * - The maximum element is then moved to its correct position by flipping the entire unsorted portion.
     * - Repeating this process for the remaining unsorted elements ensures the array is sorted.
     * 
     * Time Complexity:
     * - O(n^2): Finding the maximum element takes O(n), and flipping takes O(n). This is repeated for each element.
     * 
     * Space Complexity:
     * - O(n): The output list stores the flip positions.
     * 
     * @param arr The input array to be sorted.
     * @return A list of integers representing the flip positions (1-based indexing).
     */
    public List<Integer> pancakeSort(int[] arr) {
        ArrayList<Integer> out = new ArrayList<>();
        int n = arr.length;

        // Sort the array using pancake flips
        while (n > 0) {
            // Find the index of the maximum element in the unsorted portion
            int max = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] > arr[max]) {
                    max = i;
                }
            }

            // Bring the maximum element to the front
            reverse(arr, 0, max);
            out.add(max + 1);

            // Move the maximum element to its correct position
            reverse(arr, 0, n - 1);
            out.add(n);

            // Reduce the unsorted portion
            n--;
        }
        return out;
    }

    /**
     * Helper Method: Reverse
     * 
     * Reverses the elements in the array between indices `i` and `j` (inclusive).
     * 
     * @param arr The array to be reversed.
     * @param i   The starting index.
     * @param j   The ending index.
     */
    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
