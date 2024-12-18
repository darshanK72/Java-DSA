/**
 * Problem Statement:
 * 
 *     Given an array `arr` of integers, the task is to compute the prefix sum array of the input array.
 *     A prefix sum array is an array where each element at index `i` is the sum of all elements from index `0` to index `i` of the original array.
 *     For example, if the original array is `[1, 2, 3, 4]`, the prefix sum array would be `[1, 3, 6, 10]`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An array of integers representing the prefix sum of the input array.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     Output:
 *     [1, 3, 6, 10]
 * 
 *     Explanation:
 *     The prefix sum array is formed by cumulative summation:
 *     - Prefix sum at index 0: 1
 *     - Prefix sum at index 1: 1 + 2 = 3
 *     - Prefix sum at index 2: 1 + 2 + 3 = 6
 *     - Prefix sum at index 3: 1 + 2 + 3 + 4 = 10
 */

import java.util.*;

public class j01PrefixSum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] arr = new int[n]; // Array to store the input numbers
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each element into the array
        }

        // Call the method to compute the prefix sum array
        System.out.println(Arrays.toString(prefixSum(arr)));

        // Close the scanner to avoid resource leaks
        in.close();
    }

    /**
     * Approach: Prefix Sum Calculation
     * 
     * Intuition:
     * - The prefix sum array is formed by iterating over the input array and cumulatively summing the elements.
     * - For each element at index `i`, the prefix sum at that index is the sum of all elements from index `0` to index `i` in the input array.
     * - This allows for quick querying of the sum of any subarray (from index `i` to `j`) by subtracting the prefix sum at index `i-1` from the prefix sum at index `j`.
     * 
     * Time Complexity:
     * - O(n), because we iterate over the array once to compute the prefix sum.
     * 
     * Space Complexity:
     * - O(n), because we store the prefix sum in an array of the same size as the input array.
     * 
     * @param arr The input array of integers.
     * @return The prefix sum array.
     */
    public static int[] prefixSum(int[] arr) {
        int[] preSum = new int[arr.length]; // Array to store the prefix sum
        preSum[0] = arr[0]; // Initialize the first element of prefix sum
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = arr[i] + preSum[i - 1]; // Compute the prefix sum for each index
        }
        return preSum; // Return the resulting prefix sum array
    }
}
