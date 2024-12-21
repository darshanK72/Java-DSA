/**
 * Problem Statement:
 * 
 *     Given an array of integers, find the length of the longest subarray with a sum equal to zero.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the length of the longest subarray with a sum equal to zero.
 * 
 * Example:
 *     Input:
 *         n = 8
 *         arr = [1, 2, -3, 3, 1, -4, 2, 2]
 *     Output:
 *         5
 * 
 *     Explanation:
 *         The subarray [2, -3, 3, 1, -4] has a sum equal to zero and length 5.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j03LongestZeroSumSubarray {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Elements of the array
        }

        // Approach 1: Brute Force
        System.out.printf("Approach 1: %d\n", maxLen(arr, n));

        // Approach 2: HashMap (Basic)
        System.out.printf("Approach 2: %d\n", maxLenHashMap1(arr, n));

        // Approach 3: HashMap (Optimized Initialization)
        System.out.printf("Approach 3: %d\n", maxLenHashMap2(arr, n));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Iterate through all possible subarrays of the given array.
     * - Calculate the sum for each subarray.
     * - If the sum is zero, update the maximum length of the subarray found so far.
     * 
     * Time Complexity:
     * - O(N^2), as we iterate over all subarray ranges and calculate their sums.
     * 
     * Space Complexity:
     * - O(1), as we use constant extra space.
     * 
     * @param arr The input array.
     * @param n The size of the array.
     * @return The length of the longest subarray with sum equal to zero.
     */
    public static int maxLen(int arr[], int n) {
        int maxL = 0; // Initialize the maximum length to zero
        for (int i = 0; i < n; i++) {
            int s = 0; // Initialize sum for the subarray starting at index i
            for (int j = i; j < n; j++) {
                s += arr[j]; // Add the current element to the sum
                if (s == 0)
                    maxL = Math.max(maxL, j - i + 1); // Update maximum length if sum is zero
            }
        }
        return maxL;
    }

    /**
     * Approach 2: HashMap (Basic)
     * 
     * Intuition:
     * - Use a prefix sum approach combined with a HashMap to store the first occurrence of each prefix sum.
     * - If the same prefix sum is encountered again, it means the subarray between these indices has a sum of zero.
     * - Additionally, if the prefix sum becomes zero at any point, the subarray from the start has a sum of zero.
     * 
     * Time Complexity:
     * - O(N), as we traverse the array once and perform constant-time HashMap operations.
     * 
     * Space Complexity:
     * - O(N), due to the storage of prefix sums in the HashMap.
     * 
     * @param arr The input array.
     * @param n The size of the array.
     * @return The length of the longest subarray with sum equal to zero.
     */
    public static int maxLenHashMap1(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store prefix sums and their first occurrences
        int sum = 0; // Cumulative sum
        int maxL = 0; // Maximum length of zero-sum subarray

        for (int i = 0; i < n; i++) {
            sum += arr[i]; // Update the cumulative sum

            if (sum == 0) {
                maxL = i + 1; // Update maxL if the entire subarray from the start has sum zero
            } else if (map.containsKey(sum)) {
                maxL = Math.max(maxL, i - map.get(sum)); // Calculate the length of the subarray
            } else {
                map.put(sum, i); // Store the first occurrence of the cumulative sum
            }
        }
        return maxL;
    }

    /**
     * Approach 3: HashMap (Optimized Initialization)
     * 
     * Intuition:
     * - Similar to Approach 2 but pre-initialize the HashMap with a base case where the prefix sum is zero.
     * - This eliminates the need for a separate check for the sum being zero, streamlining the code.
     * 
     * Time Complexity:
     * - O(N), as we traverse the array once and perform constant-time HashMap operations.
     * 
     * Space Complexity:
     * - O(N), due to the storage of prefix sums in the HashMap.
     * 
     * @param arr The input array.
     * @param n The size of the array.
     * @return The length of the longest subarray with sum equal to zero.
     */
    public static int maxLenHashMap2(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store prefix sums and their first occurrences
        int sum = 0; // Cumulative sum
        int maxL = 0; // Maximum length of zero-sum subarray
        map.put(sum, -1); // Base case: prefix sum of 0 at index -1

        for (int i = 0; i < n; i++) {
            sum += arr[i]; // Update the cumulative sum

            if (!map.containsKey(sum)) {
                map.put(sum, i); // Store the first occurrence of the cumulative sum
            }

            maxL = Math.max(maxL, i - map.get(sum)); // Update the maximum length
        }
        return maxL;
    }
}
