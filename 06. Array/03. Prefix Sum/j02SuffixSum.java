/**
 * Problem Statement:
 * 
 *     Given an array `arr` of integers, the task is to compute the suffix sum array of the input array.
 *     A suffix sum array is an array where each element at index `i` is the sum of all elements from index `i` to the last index of the original array.
 *     For example, if the original array is `[1, 2, 3, 4]`, the suffix sum array would be `[10, 9, 7, 4]`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An array of integers representing the suffix sum of the input array.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     Output:
 *     [10, 9, 7, 4]
 * 
 *     Explanation:
 *     The suffix sum array is formed by cumulative summation starting from the end of the array:
 *     - Suffix sum at index 3: 4
 *     - Suffix sum at index 2: 3 + 4 = 7
 *     - Suffix sum at index 1: 2 + 3 + 4 = 9
 *     - Suffix sum at index 0: 1 + 2 + 3 + 4 = 10
 */

import java.util.*;

public class j02SuffixSum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] arr = new int[n]; // Array to store the input numbers
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each element into the array
        }

        // Call the method to compute the suffix sum array
        System.out.println(Arrays.toString(suffixSum(arr)));

        // Close the scanner to avoid resource leaks
        in.close();
    }

    /**
     * Approach: Suffix Sum Calculation
     * 
     * Intuition:
     * - The suffix sum array is formed by iterating over the original array in reverse order.
     * - Starting from the last element, we compute the sum of all elements from the current index to the end of the array.
     * - This is done by adding the current element to the previously computed suffix sum.
     * 
     * Time Complexity:
     * - O(n), because we iterate over the array once to compute the suffix sum.
     * 
     * Space Complexity:
     * - O(n), because we store the suffix sum in an array of the same size as the input array.
     * 
     * @param arr The input array of integers.
     * @return The suffix sum array.
     */
    public static int[] suffixSum(int[] arr) {
        int[] sufSum = new int[arr.length]; // Array to store the suffix sum
        sufSum[arr.length - 1] = arr[arr.length - 1]; // Initialize the last element of suffix sum
        for (int i = arr.length - 2; i >= 0; i--) {
            sufSum[i] = arr[i] + sufSum[i + 1]; // Compute the suffix sum for each index
        }
        return sufSum; // Return the resulting suffix sum array
    }
}
