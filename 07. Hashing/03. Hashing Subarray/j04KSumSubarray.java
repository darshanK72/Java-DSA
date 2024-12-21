/**
 * Problem Statement:
 * 
 *     Given an array of integers and an integer `k`, find the starting and ending positions (1-based index) of the first subarray
 *     whose elements sum up to `k`. If no such subarray exists, return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 *     - An integer `k` (-10^9 <= k <= 10^9), the target sum.
 * 
 * Output:
 *     - A list containing two integers representing the starting and ending positions of the subarray (1-based index),
 *       or -1 if no such subarray exists.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [1, 2, 3, 7, 5]
 *         k = 12
 *     Output:
 *         [2, 4]
 * 
 *     Explanation:
 *         The subarray [2, 3, 7] has a sum equal to 12, and its indices are 2 to 4 (1-based).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j04KSumSubarray {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Elements of the array
        }
        int k = in.nextInt(); // Target sum

        // Approach 1: Brute Force
        System.out.println(subarraySumK(arr, n, k));

        // Approach 2: HashMap
        System.out.println(subarraySumKHashMap(arr, n, k));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Check all possible subarrays and calculate their sum.
     * - If the sum equals `k`, return the start and end indices of that subarray.
     * 
     * Time Complexity:
     * - O(N^2), as we calculate the sum for every subarray.
     * 
     * Space Complexity:
     * - O(1), as we use constant extra space.
     * 
     * @param arr The input array.
     * @param n The size of the array.
     * @param k The target sum.
     * @return A list containing the 1-based starting and ending indices of the subarray, or -1 if no such subarray exists.
     */
    public static ArrayList<Integer> subarraySumK(int[] arr, int n, int k) {
        for (int i = 0; i < n; i++) {
            int s = 0; // Initialize sum for the subarray starting at index i
            for (int j = i; j < n; j++) {
                s += arr[j]; // Add the current element to the sum
                if (s == k) {
                    return new ArrayList<>(Arrays.asList(i + 1, j + 1)); // Return 1-based indices
                }
            }
        }
        return new ArrayList<>(Arrays.asList(-1)); // Return -1 if no subarray is found
    }

    /**
     * Approach 2: HashMap
     * 
     * Intuition:
     * - Use a prefix sum approach combined with a HashMap to store the first occurrence of each prefix sum.
     * - If the difference between the current prefix sum and `k` exists in the HashMap,
     *   then the subarray between those indices has a sum equal to `k`.
     * - If the prefix sum equals `k` at any point, the subarray from the start has a sum equal to `k`.
     * 
     * Time Complexity:
     * - O(N), as we traverse the array once and perform constant-time HashMap operations.
     * 
     * Space Complexity:
     * - O(N), due to the storage of prefix sums in the HashMap.
     * 
     * @param arr The input array.
     * @param n The size of the array.
     * @param k The target sum.
     * @return A list containing the 1-based starting and ending indices of the subarray, or -1 if no such subarray exists.
     */
    public static ArrayList<Integer> subarraySumKHashMap(int[] arr, int n, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store prefix sums and their first occurrences
        int s = 0; // Cumulative sum

        for (int i = 0; i < n; i++) {
            s += arr[i]; // Update the cumulative sum

            if (s == k) {
                return new ArrayList<>(Arrays.asList(1, i + 1)); // Subarray from the start to the current index
            }

            if (map.containsKey(s - k)) {
                return new ArrayList<>(Arrays.asList(map.get(s - k) + 1, i + 1)); // Subarray found using the prefix sum
                                                                                  // difference
            }

            map.put(s, i + 1); // Store the first occurrence of the cumulative sum
        }

        return new ArrayList<>(Arrays.asList(-1)); // Return -1 if no subarray is found
    }
}
