/**
 * Problem Statement:
 * 
 *     The score of an array is defined as the product of its sum and its length. For example, 
 *     the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
 *     Given an integer array `arr` and a long integer `k`, the task is to find the number of contiguous subarrays 
 *     whose score (sum * length) is strictly less than `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - A long integer `k` (1 <= k <= 10^9), the target score.
 * 
 * Output:
 *     - Return the number of contiguous subarrays whose score is strictly less than `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     75
 *     Output:
 *     12
 * 
 *     Explanation:
 *     There are 12 subarrays whose score is strictly less than 75. The score is calculated as 
 *     the product of sum of elements and the length of the subarray.
 */

import java.util.Scanner;

// The score of an array is defined as the product of its sum and its length.
// For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.

public class j05CountSubarraysScoreLessThanK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        long k = in.nextLong(); // Input: the target score

        // Call the solution method
        System.out.println(countSubarrays(arr, k)); // Output the result

        in.close();
    }

    /**
     * Approach: Sliding Window (Two Pointers)
     * 
     * Intuition:
     * - The goal is to find the number of contiguous subarrays where the score (sum * length) is less than `k`.
     * - We use a sliding window (two pointers) approach to efficiently calculate the number of valid subarrays.
     * - For each element, we expand the window by adding elements to the sum and increasing the window length.
     * - If the score of the current window exceeds or equals `k`, we shrink the window by moving the left pointer `j` to reduce the sum and length until the score becomes valid again.
     * - For each valid window, we count the subarrays ending at the current position.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. Both pointers traverse the array once.
     * 
     * Space Complexity:
     * - O(1), as we are using only a constant amount of space to track the sum and pointers.
     * 
     * @param nums The input array of integers.
     * @param k The target score threshold.
     * @return The number of contiguous subarrays whose score is strictly less than `k`.
     */
    public static long countSubarrays(int[] nums, long k) {
        long currentSum = 0; // To track the sum of the current window
        int j = 0; // Left pointer of the sliding window
        long out = 0; // To store the total count of valid subarrays

        // Iterate through the array with the right pointer `i`
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i]; // Add the current element to the window sum

            // Shrink the window from the left if the score (sum * length) exceeds or equals
            // `k`
            while (currentSum * (i - j + 1) >= k) {
                currentSum -= nums[j]; // Remove the element at the left pointer
                j++; // Move the left pointer to shrink the window
            }

            // Add the number of valid subarrays ending at `i`
            out += (i - j + 1);
        }

        return out; // Return the total count of valid subarrays
    }
}
