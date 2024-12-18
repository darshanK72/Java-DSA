/**
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to find the minimum XOR value of any pair of integers from the array.
 *     The XOR value of two numbers is computed by performing the bitwise XOR operation between them. The goal is to find
 *     the pair whose XOR value is the smallest among all possible pairs in the array.
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the minimum XOR value of any pair from the array.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     Output:
 *     1
 * 
 *     Explanation:
 *     The possible XOR values for the pairs are:
 *     (1, 2) → 3
 *     (1, 3) → 2
 *     (1, 4) → 5
 *     (2, 3) → 1
 *     (2, 4) → 6
 *     (3, 4) → 7
 *     The minimum XOR value is 1, which occurs for the pair (2, 3).
 */

import java.util.Arrays;
import java.util.Scanner;

public class j08MinimumXorValuePair {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array

        int[] arr = new int[n]; // Array to store the input numbers
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each element into the array
        }

        // Call the method to calculate the minimum XOR value of a pair
        System.out.println(minimumXorValuePair(arr));

        // Close the scanner to avoid resource leaks
        in.close();
    }

    /**
     * Approach: Sorting and Pairwise XOR Calculation
     * 
     * Intuition:
     * - The XOR of two numbers tends to be smaller when the numbers are closer to each other in value.
     * - By sorting the array, we can efficiently check adjacent pairs, which are the most likely candidates for yielding
     *   the smallest XOR value.
     * - After sorting, the smallest XOR value will likely be found between consecutive elements in the sorted array,
     *   since large differences in values tend to produce larger XOR values.
     * 
     * Time Complexity:
     * - O(n log n), because the array is sorted, and then a linear scan is performed to compute XOR values between consecutive elements.
     * 
     * Space Complexity:
     * - O(1), since we are only using a constant amount of extra space for the result and temporary variables.
     * 
     * @param arr The input array of integers.
     * @return The minimum XOR value of any pair in the array.
     */
    public static int minimumXorValuePair(int[] arr) {
        Arrays.sort(arr); // Sort the array first
        int result = Integer.MAX_VALUE; // Initialize result with a large value
        for (int i = 0; i < arr.length - 1; i++) {
            result = Math.min(result, arr[i] ^ arr[i + 1]); // Calculate XOR for adjacent pairs
        }
        return result; // Return the smallest XOR value
    }
}
