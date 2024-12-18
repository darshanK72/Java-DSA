/**
 * Problem Statement:
 *
 *     Given an integer array `arr`, we need to find the length of the largest subarray where the adjacent 
 *     elements alternate between odd and even numbers.
 *
 *     There are two main approaches:
 *     - A **naive** approach where we check all subarrays.
 *     - An **efficient** approach where we keep track of the length of the current alternating subarray as
 *       we iterate through the array.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^3 <= arr[i] <= 10^3).
 *
 * Output:
 *     - The length of the largest subarray where the adjacent elements alternate between odd and even numbers.
 *
 * Example:
 *     Input:
 *         7
 *         1 2 3 4 5 6 7
 *     Output:
 *         7
 * 
 * Explanation:
 *     The entire array alternates between odd and even numbers, so the length of the largest subarray is 7.
 */

import java.util.Scanner;

public class j32OddEvenSubarray {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the different methods to compute the largest alternating subarray length
        System.out.println(largestOddEvenSubarrayLengthNive(arr)); // Naive Approach
        System.out.println(largestOddEvenSubarrayLengthEfficient(arr)); // Efficient Approach

        in.close();
    }

    /**
     * Approach: Naive Approach (O(n^2))
     * 
     * Intuition:
     *     - The idea is to check every possible subarray and see if it alternates between odd and even numbers.
     *     - For each starting index, we extend the subarray by increasing the end index while checking if 
     *       adjacent elements alternate.
     * 
     * Time Complexity: O(n^2), as we generate and check all subarrays using two nested loops.
     * 
     * Space Complexity: O(1), as we only use a few integer variables.
     *
     * @param arr The input array.
     * @return The length of the largest alternating odd-even subarray.
     */
    public static int largestOddEvenSubarrayLengthNive(int[] arr) {
        int ans = 0; // Initialize the answer to track the largest subarray length
        for (int i = 0; i < arr.length; i++) {
            int l = 1; // Subarray length starting from index i
            for (int j = i + 1; j < arr.length; j++) {
                // Check if adjacent elements alternate between odd and even
                if ((arr[j] % 2 == 0 && arr[j - 1] % 2 == 1) || (arr[j] % 2 == 1 && arr[j - 1] % 2 == 0)) {
                    l++; // Increase length of subarray if alternating
                } else {
                    break; // Break the loop if the sequence is no longer alternating
                }
            }
            ans = Math.max(ans, l); // Update the maximum length found so far
        }
        return ans; // Return the largest alternating subarray length
    }

    /**
     * Approach: Efficient Approach (O(n))
     * 
     * Intuition:
     *     - Instead of checking every possible subarray, we can keep track of the current length of the 
     *       alternating subarray as we iterate through the array.
     *     - If the adjacent elements alternate, we increase the length, and if they don't, we reset the length.
     * 
     * Time Complexity: O(n), as we make only one pass through the array.
     * 
     * Space Complexity: O(1), as we only use a few integer variables.
     *
     * @param arr The input array.
     * @return The length of the largest alternating odd-even subarray.
     */
    public static int largestOddEvenSubarrayLengthEfficient(int[] arr) {
        int ans = 0; // Initialize the answer to track the largest subarray length
        int l = 1; // Current length of the alternating subarray
        for (int i = 0; i < arr.length - 1; i++) {
            // Check if adjacent elements alternate between odd and even
            if ((arr[i] + arr[i + 1]) % 2 == 1) {
                l++; // Increase length if alternating
            } else {
                ans = Math.max(l, ans); // Update the maximum length found so far
                l = 1; // Reset length if sequence stops alternating
            }
        }
        return Math.max(l, ans); // Return the largest length after finishing the iteration
    }
}
