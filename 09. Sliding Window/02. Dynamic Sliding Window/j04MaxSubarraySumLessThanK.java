/**
 * Problem Statement:
 * 
 *     Given an array of positive integers `arr` and a long integer `k`, the task is to find the maximum sum of a 
 *     contiguous subarray whose sum is strictly less than `k`. If no such subarray exists, return `0`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - A long integer `k` (1 <= k <= 10^9), the target sum.
 * 
 * Output:
 *     - Return the maximum sum of a subarray whose sum is strictly less than `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     2 3 1 2 4 3
 *     7
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The maximum subarray sum that is less than 7 is [2, 3, 1], which has a sum of 6.
 */

import java.util.Scanner;

public class j04MaxSubarraySumLessThanK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        long k = in.nextLong(); // Input: target sum

        // Call the solution method
        System.out.println(findMaxSubarraySum(arr, k)); // Output the result

        in.close();
    }

    /**
     * Approach: Sliding Window (Two Pointers)
     * 
     * Intuition:
     * - We can use the sliding window (two pointers) approach to efficiently find the maximum subarray sum that is less than `k`.
     * - Start with both pointers `i` and `j` at the beginning of the array.
     * - Expand the window by adding elements at `i` to `currentSum`.
     * - If `currentSum` exceeds `k`, move the left pointer `j` forward to shrink the window until `currentSum` is less than or equal to `k`.
     * - Continuously update the maximum sum whenever the current sum is less than `k`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. Both pointers traverse the array once.
     * 
     * Space Complexity:
     * - O(1), no additional space is used other than variables to track the sum and pointers.
     * 
     * @param arr The input array of positive integers.
     * @param x The target sum.
     * @return The maximum sum of a subarray whose sum is strictly less than `x`.
     */
    public static long findMaxSubarraySum(int[] arr, long x) {
        int j = 0; // Left pointer of the sliding window
        long currentSum = 0; // To track the sum of elements in the current window
        long maxSum = 0; // To store the maximum subarray sum that is less than `x`

        // Iterate through the array with the right pointer
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i]; // Add the current element to the window sum

            // Shrink the window from the left if the sum exceeds `x`
            while (currentSum >= x) {
                currentSum -= arr[j]; // Remove the element at the left pointer
                j++; // Move the left pointer to shrink the window
            }

            // Update maxSum if the current sum is less than `x`
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum; // Return the maximum subarray sum less than `x`
    }
}
