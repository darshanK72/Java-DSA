/**
 * Problem Statement:
 * 
 *     Given an array of positive integers and a target sum `k`, your task is to find the length of the longest
 *     subarray whose sum equals `k`. The array consists of positive integers only.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^9), the target sum.
 * 
 * Output:
 *     - Return an integer representing the length of the longest subarray whose sum equals `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     9
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The longest subarray with sum exactly equal to 9 is [4, 5] with length 2.
 */

import java.util.Scanner;

public class j02LongestPositiveSubarrayWithSumK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: target sum

        // Call the solution methods
        System.out.println(longestSubarraySumK(arr, k)); // Brute force solution
        System.out.println(longestSubarraySumKEfficientForPositive1(arr, k)); // Optimized solution 1 (Two Pointers)
        System.out.println(longestSubarraySumKEfficientForPositive2(arr, k)); // Optimized solution 2 (Two Pointers)
        System.out.println(longestSubarraySumKEfficientForPositive3(arr, k)); // Optimized solution 3 (Two Pointers)

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - In this approach, we check all subarrays by iterating over every possible starting index `i` and ending index `j`.
     * - For each subarray, we compute the sum and check if it equals `k`. If it does, we update the maximum length.
     * - While simple, this approach is inefficient for larger arrays due to its O(n^2) time complexity.
     * 
     * Time Complexity:
     * - O(n^2), as we are iterating over all pairs of starting and ending indices.
     * 
     * Space Complexity:
     * - O(1), as no extra space is required apart from variables used for calculations.
     * 
     * @param arr The input array of positive integers.
     * @param k The target sum.
     * @return The length of the longest subarray whose sum equals `k`.
     */
    public static int longestSubarraySumK(int[] arr, int k) {
        int maxLength = 0; // Initialize the maximum length
        for (int i = 0; i < arr.length; i++) {
            int sum = 0; // Initialize sum for the current subarray
            for (int j = i; j < arr.length; j++) {
                sum += arr[j]; // Add the current element to sum
                if (sum == k) { // Check if the sum is equal to the target
                    maxLength = Math.max(maxLength, j - i + 1); // Update the maximum length
                }
            }
        }
        return maxLength; // Return the result
    }

    /**
     * Approach 2: Optimized for Positive Numbers (Two Pointers)
     * 
     * Intuition:
     * - Here, we use a sliding window approach with two pointers (`i` and `j`).
     * - The right pointer `j` extends the window by adding elements, and the left pointer `i` shrinks the window if the sum exceeds `k`.
     * - This ensures that we are always examining a valid subarray whose sum is either less than or equal to `k`.
     * - This approach works efficiently for positive numbers due to the nature of the problem (the sum can only increase as the window expands).
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. Both pointers only move forward once.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used other than the variables tracking the sum and pointers.
     * 
     * @param arr The input array of positive integers.
     * @param k The target sum.
     * @return The length of the longest subarray whose sum equals `k`.
     */
    public static int longestSubarraySumKEfficientForPositive1(int[] arr, int k) {
        int i = 0; // Left pointer
        int j = 0; // Right pointer
        int sum = 0; // To track the sum of the current window
        int maxLength = 0; // To track the length of the longest subarray with sum exactly equal to k

        // Sliding window approach
        while (j < arr.length) {
            // Add the current element to the sum
            sum += arr[j];

            // If the sum exceeds k, move the left pointer to shrink the window
            while (i <= j && sum > k) {
                sum -= arr[i]; // Remove arr[i] from the sum
                i++; // Shrink the window from the left
            }

            // If the sum is exactly equal to k, update the maxLength
            if (sum == k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }

            // Move the right pointer
            j++;
        }
        return maxLength; // Return the result
    }

    /**
     * Approach 3: Optimized for Positive Numbers (Two Pointers - Slightly Optimized)
     * 
     * Intuition:
     * - Similar to the previous approach, but we immediately start shrinking the window from the left once the sum exceeds `k`.
     * - This avoids an additional check and is designed to improve efficiency in edge cases with very large sums.
     * 
     * Time Complexity:
     * - O(n), as we are just iterating through the array once with two pointers.
     * 
     * Space Complexity:
     * - O(1), no additional space is required.
     * 
     * @param arr The input array of positive integers.
     * @param k The target sum.
     * @return The length of the longest subarray whose sum equals `k`.
     */
    public static int longestSubarraySumKEfficientForPositive2(int[] arr, int k) {
        int i = 0; // Left pointer
        int j = 0; // Right pointer
        int sum = 0; // To track the sum of the current window
        int maxLength = 0; // To track the length of the longest subarray with sum exactly equal to k

        // Sliding window approach
        while (j < arr.length) {
            // Add the current element to the sum
            sum += arr[j];

            // If sum exceeds k, move the left pointer to shrink the window
            while (i <= j && sum > k) {
                sum -= arr[i];
                i++; // Shrink the window from the left
            }

            // If the sum is exactly equal to k, update the maxLength
            if (sum == k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }

            // Move the right pointer
            j++;
        }

        return maxLength; // Return the result
    }

    /**
     * Approach 4: Optimized for Positive Numbers (Two Pointers - Simplified)
     * 
     * Intuition:
     * - This approach is similar to Approach 3 but with slightly simplified code and conditions.
     * - The left pointer is moved to shrink the window only when the sum exceeds `k`, ensuring an efficient solution.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We iterate over the array with two pointers.
     * 
     * Space Complexity:
     * - O(1), no extra space is required.
     * 
     * @param arr The input array of positive integers.
     * @param k The target sum.
     * @return The length of the longest subarray whose sum equals `k`.
     */
    public static int longestSubarraySumKEfficientForPositive3(int[] arr, int k) {
        int i = 0; // Left pointer
        int j = 0; // Right pointer
        int sum = 0; // To track the sum of the current window
        int maxLength = 0; // To track the length of the longest subarray with sum exactly equal to k

        // Sliding window approach
        while (j < arr.length) {
            // Add the current element to the sum
            sum += arr[j];

            // Shrink the window if sum exceeds k
            if (i <= j && sum > k) {
                sum -= arr[i];
                i++; // Shrink from the left
            }

            // If the sum is exactly equal to k, update the maxLength
            if (sum == k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }

            // Move the right pointer
            j++;
        }

        return maxLength; // Return the result
    }
}
