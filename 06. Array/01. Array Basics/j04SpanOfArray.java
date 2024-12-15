/*-
 * Problem Statement:
 * 
 *     Given an array of integers, `arr[]`, find the span of the array.
 *     The span of an array is defined as the difference between the maximum and minimum elements in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The span of the array, which is the difference between the maximum and minimum elements.
 * 
 * Example:
 *     Input:
 *     5
 *     3 1 4 5 2
 *     Output:
 *     4
 *     Explanation:
 *     - The maximum element in the array is 5, and the minimum element is 1.
 *     - The span is the difference between the maximum and minimum, i.e., 5 - 1 = 4.
 * 
 */

import java.util.Scanner;

public class j04SpanOfArray {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution method
        System.out.println(spanOfArray(arr)); // Find the span of the array

        in.close();
    }

    /*-
     * Approach 1: Find the Span (Iterative Approach)
     * 
     * Intuition:
     * - Traverse the array and maintain variables for the minimum and maximum elements.
     * - The span is the difference between the maximum and minimum values.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We scan the array once.
     * 
     * Space Complexity:
     * - O(1), since only a constant amount of space is used for the `min` and `max` variables.
     * 
     * @param arr The input array of numbers.
     * @return The span of the array, which is the difference between the maximum and minimum values.
     */
    public static int spanOfArray(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // Update the maximum value if the current element is greater
            }
            if (arr[i] < min) {
                min = arr[i]; // Update the minimum value if the current element is smaller
            }
        }
        return max - min; // Return the span as the difference between the max and min
    }
}
