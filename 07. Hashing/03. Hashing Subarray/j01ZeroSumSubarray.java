/**
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to determine if there exists a subarray whose sum is zero. 
 *     A subarray is defined as a contiguous part of the array, and we need to check if any such subarray exists.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (−10^5 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - Return `true` if there exists a subarray with a sum of zero; otherwise, return `false`.
 * 
 * Example:
 *     Input:
 *     5
 *     3 4 -7 2 -3
 *     
 *     Output:
 *     true
 * 
 *     Explanation:
 *     A subarray from index 1 to 3 (i.e., [4, -7, 2]) has a sum of 0, so the output is `true`.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j01ZeroSumSubarray {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n]; // Array to hold the input elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the sum checking methods
        System.out.println(findSum(arr, n)); // Brute force approach
        System.out.println(findSumHashMap(arr, n)); // Optimized approach using HashMap
        in.close(); // Close the scanner
    }

    /**
     * Approach 1: Brute Force (Nested Loops)
     * 
     * Intuition:
     * - The brute force approach checks every possible subarray by iterating through 
     *   all starting and ending indices using two loops. For each pair of starting 
     *   and ending indices, we calculate the sum of the subarray and check if it equals zero.
     * 
     * Time Complexity:
     * - O(N^2), where N is the size of the array. This is because we have two nested loops 
     *   to check all possible subarrays.
     * 
     * Space Complexity:
     * - O(1), as no additional space is required besides a few variables for iteration and sum.
     * 
     * @param arr The input array of numbers.
     * @param n The size of the array.
     * @return true if there exists a subarray with sum zero, false otherwise.
     */
    public static boolean findSum(int arr[], int n) {
        // Traverse all possible subarrays
        for (int i = 0; i < n; i++) {
            int s = 0; // Initialize the sum for the current subarray
            for (int j = i; j < n; j++) {
                s += arr[j]; // Add the current element to the sum
                if (s == 0) // If the sum becomes zero, return true
                    return true;
            }
        }
        return false; // If no subarray with sum zero is found, return false
    }

    /**
     * Approach 2: Optimized Solution using HashMap
     * 
     * Intuition:
     * - The optimized approach uses the concept of prefix sum and a HashMap. The idea is that if 
     *   the sum of elements from index `i` to `j` is zero, then the prefix sum up to index `j` must 
     *   be equal to the prefix sum up to index `i`. By storing the cumulative sum in a HashMap, we can 
     *   check if any prefix sum repeats, which indicates a zero sum subarray.
     * - Additionally, we check if the sum is zero from the start by keeping track of the cumulative sum.
     * 
     * Time Complexity:
     * - O(N), where N is the size of the array. We traverse the array only once, and each HashMap operation 
     *   (insertion/check) takes O(1) time.
     * 
     * Space Complexity:
     * - O(N), as we store the prefix sums in the HashMap.
     * 
     * @param arr The input array of numbers.
     * @param n The size of the array.
     * @return true if there exists a subarray with sum zero, false otherwise.
     */
    public static boolean findSumHashMap(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>(); // HashMap to store prefix sums
        int sum = arr[0]; // Initialize the prefix sum with the first element

        for (int i = 1; i < n; i++) {
            // Check if the current sum is zero or if the sum already exists in the map
            if (map.containsKey(sum) || sum == 0)
                return true;
            // Add the current sum to the HashMap
            map.put(sum, i);
            sum += arr[i]; // Update the prefix sum
        }

        return false; // If no subarray with sum zero is found, return false
    }
}
