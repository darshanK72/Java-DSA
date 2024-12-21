/**
 * Problem Statement:
 * 
 *     Given an array of integers, count the number of subarrays with a sum equal to zero.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the count of subarrays having a sum equal to zero.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [1, -1, 3, -2, 2]
 *     Output:
 *         2
 * 
 *     Explanation:
 *         The subarrays [1, -1] and [3, -2, 2] have a sum equal to zero.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j02CountZeroSumSubarrays {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong(); // Elements of the array
        }

        // Approach 1: Brute Force
        System.out.printf("Approach 1: %d\n", countSubarrays(arr, n));

        // Approach 2: HashMap for Optimization
        System.out.printf("Approach 2: %d\n", countSubarraysHashMap(arr, n));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Iterate through all possible subarrays of the given array.
     * - Calculate the sum for each subarray.
     * - If the sum is zero, increment the count.
     * 
     * Time Complexity:
     * - O(N^2), as we iterate over all subarray ranges and calculate their sums.
     * 
     * Space Complexity:
     * - O(1), as we use constant extra space.
     * 
     * @param arr The input array.
     * @param n The size of the array.
     * @return The count of subarrays with sum equal to zero.
     */
    public static int countSubarrays(long arr[], int n) {
        int count = 0; // Initialize count of zero-sum subarrays
        for (int i = 0; i < n; i++) {
            long s = 0; // Initialize sum for the subarray starting at index i
            for (int j = i; j < n; j++) {
                s += arr[j]; // Add the current element to the sum
                if (s == 0)
                    count++; // Increment count if the sum becomes zero
            }
        }
        return count;
    }

    /**
     * Approach 2: Using HashMap for Optimization
     * 
     * Intuition:
     * - Use a prefix sum approach combined with a HashMap to store the frequency of sums encountered.
     * - The idea is that if the cumulative sum at two different indices is the same, then the subarray
     *   between these indices has a sum of zero.
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
     * @return The count of subarrays with sum equal to zero.
     */
    public static long countSubarraysHashMap(long[] arr, int n) {
        HashMap<Long, Long> map = new HashMap<>(); // Map to store prefix sums and their frequencies
        long sum = 0; // Cumulative sum
        int count = 0; // Count of zero-sum subarrays
        map.put(0L, 1L); // Base case: a prefix sum of zero means a subarray from the start has a sum of
                         // zero

        for (int i = 0; i < n; i++) {
            sum += arr[i]; // Update the cumulative sum

            // If the current sum has been seen before, add the frequency of this sum to the
            // count
            count += map.getOrDefault(sum, 0L);

            // Update the frequency of the current sum in the map
            map.put(sum, map.getOrDefault(sum, 0L) + 1);
        }
        return count;
    }
}
