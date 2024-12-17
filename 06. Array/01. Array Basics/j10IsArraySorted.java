/*-
 * Problem Statement:
 * 
 *     Write a program to check if a given array is sorted in non-decreasing order. 
 *     You need to provide both an iterative and a recursive approach to solve the problem.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A boolean value `true` if the array is sorted in non-decreasing order, and `false` otherwise.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [1, 2, 2, 3, 5]
 *     Output:
 *         true
 * 
 *     Explanation:
 *         - The array elements are in non-decreasing order.
 * 
 *     Input:
 *         n = 4
 *         arr = [1, 4, 3, 5]
 *     Output:
 *         false
 * 
 *     Explanation:
 *         - The element at index 1 (4) is greater than the element at index 2 (3), 
 *           so the array is not sorted.
 */

import java.util.Scanner;

public class j10IsArraySorted {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Calling iterative approach
        System.out.println("Iterative Approach: " + isSorted(arr));

        // Calling recursive approach
        System.out.println("Recursive Approach: " + isSortedRec(arr, 0));

        in.close();
    }

    /*-
     * Approach 1: Iterative Check for Sorted Array
     * 
     * Intuition:
     * - To check if an array is sorted, compare each element with the next one. 
     *   If all comparisons satisfy the condition `arr[i] <= arr[i + 1]`, the array is sorted.
     * 
     * Explanation:
     * 1. Iterate through the array from index `0` to `n - 2`.
     * 2. For each index `i`, check if `arr[i] > arr[i + 1]`. If true, the array is not sorted.
     * 3. If all pairs pass the condition, the array is sorted.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, as we traverse the array once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param arr The input array to check for sorting.
     * @return true if the array is sorted, false otherwise.
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1])
                return false; // Found an unsorted pair
        }
        return true; // All pairs are sorted
    }

    /*-
     * Approach 2: Recursive Check for Sorted Array
     * 
     * Intuition:
     * - Recursively check if the array is sorted by comparing the current element with the next one.
     *   If the array is sorted up to the current index, check the rest of the array.
     * 
     * Explanation:
     * 1. Base Case: If the current index `i` is the last index, return true (array is sorted).
     * 2. Recursive Case: Check if `arr[i] <= arr[i + 1]` and make a recursive call for the rest of the array.
     * 3. If any recursive call returns false, the entire array is not sorted.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, as we traverse the array once.
     * 
     * Space Complexity:
     * - O(n), due to the recursive call stack.
     * 
     * @param arr The input array to check for sorting.
     * @param i   The current index being checked.
     * @return true if the array is sorted, false otherwise.
     */
    public static boolean isSortedRec(int[] arr, int i) {
        // Base case: reached the end of the array
        if (i == arr.length - 1)
            return true;

        // Check current pair and make recursive call
        return arr[i] <= arr[i + 1] && isSortedRec(arr, i + 1);
    }
}
