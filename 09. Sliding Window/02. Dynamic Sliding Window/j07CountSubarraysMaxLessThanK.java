/**
 * Problem Statement:
 * 
 *     Given an array of integers `arr` of size `n` and an integer `k`, find the number of subarrays where the maximum element in each subarray is strictly less than `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), the threshold for the maximum element in the subarrays.
 * 
 * Output:
 *     - A long integer representing the count of subarrays where the maximum element is strictly less than `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     4
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The subarrays where the maximum element is less than 4 are:
 *     [1], [2], [3], [1, 2], [2, 3], [1, 2, 3]
 */

import java.util.Scanner;

public class j07CountSubarraysMaxLessThanK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the threshold value

        // Call your solution
        System.out.println(countSubarrayMaxLessThanK(arr, n, k));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - For each element in the array, count how many subarrays end at that element where the maximum element of the subarray is strictly less than `k`.
     * - If the element exceeds `k`, the current subarray can't be included in the count, and the subarray counting for all subsequent elements should reset.
     * 
     * Time Complexity:
     * - O(n) where n is the number of elements in the array. We iterate through the array only once.
     * 
     * Space Complexity:
     * - O(1) since we are using only a few extra variables for the computation.
     * 
     * @param arr The input array of integers.
     * @param n The size of the array.
     * @param k The threshold value for the maximum element in the subarrays.
     * @return The number of subarrays where the maximum element is less than `k`.
     */
    public static long countSubarrayMaxLessThanK(int arr[], int n, int k) {
        long ans = 0; // To store the count of valid subarrays
        long current = 0; // To track the current number of valid subarrays ending at the current element

        for (int i = 0; i < n; i++) {
            // If current element exceeds k, reset the count of subarrays
            if (arr[i] >= k) {
                current = 0;
            } else {
                current++; // Otherwise, count the subarrays ending at this element
            }

            ans += current; // Add the count of subarrays ending at this element to the total
        }

        return ans;
    }
}
