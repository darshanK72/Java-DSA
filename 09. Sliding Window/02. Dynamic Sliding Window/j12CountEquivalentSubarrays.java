/**
 * Problem Statement:
 * 
 *     Given an integer array, the task is to find the number of contiguous subarrays that contain exactly the same set of distinct integers.
 *     A subarray is a contiguous part of the array. A set of integers is considered the same if it contains exactly the same distinct integers, irrespective of their order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the number of subarrays that contain exactly the same set of distinct integers as the original array.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 1 2
 *     Output:
 *     4
 * 
 *     Explanation:
 *     There are 4 subarrays that contain exactly the same distinct integers {1, 2}.
 *     These subarrays are: [1, 2], [2, 1], [1, 2], [2].
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j12CountEquivalentSubarrays {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call your solution
        System.out.println(countDistinctSubarray(arr, n));

        // Call optimized solution
        System.out.println(countDistinctSubarrayEfficient(arr, n));
        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - We can check all possible subarrays of the given array and count how many contain exactly the same distinct elements as the original array.
     * - To do this, for each subarray starting from index `i`, check how many distinct elements are in that subarray.
     * - If the number of distinct elements matches the total number of distinct elements in the array, we count that subarray.
     * 
     * Time Complexity:
     * - O(n^2), where n is the size of the array. This is because we generate all possible subarrays, and for each subarray, we check its distinct elements.
     * 
     * Space Complexity:
     * - O(n), used by the HashSet to store distinct elements of the subarray.
     * 
     * @param arr The input array of integers.
     * @param n The size of the array.
     * @return The count of subarrays that have exactly the same set of distinct integers as the original array.
     */
    public static int countDistinctSubarray(int arr[], int n) {
        // Create a set to store distinct integers in the original array
        HashSet<Integer> set = new HashSet<>();
        for (int e : arr) {
            set.add(e); // Fill the set with distinct elements from the array
        }

        // Get the number of distinct elements
        int k = set.size();
        int ans = 0;

        // Iterate through all subarrays
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set1 = new HashSet<>();
            for (int j = i; j < n; j++) {
                set1.add(arr[j]); // Add the current element to the set
                if (set1.size() == k) { // Check if the subarray has exactly the same distinct elements
                    ans++; // Increment the answer if it matches
                }
            }
        }
        return ans; // Return the final count of subarrays
    }

    /**
     * Approach 2: Optimized Sliding Window Approach
     * 
     * Intuition:
     * - Instead of checking each subarray individually, we can use the sliding window technique to count subarrays with at most `k` distinct elements.
     * - We use the helper function `atMostKDistinct` to count the number of subarrays with at most `k` distinct elements and subtract those with at most `k-1` distinct elements.
     * - The result will give us the number of subarrays with exactly `k` distinct elements.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array. This is because we only traverse the array once with the sliding window.
     * 
     * Space Complexity:
     * - O(n), used by the HashMap to store the frequency of elements in the window.
     * 
     * @param arr The input array of integers.
     * @param n The size of the array.
     * @return The count of subarrays that have exactly the same set of distinct integers as the original array.
     */
    public static int countDistinctSubarrayEfficient(int arr[], int n) {
        // Create a set to store distinct integers in the original array
        HashSet<Integer> set = new HashSet<>();
        for (int e : arr) {
            set.add(e); // Fill the set with distinct elements from the array
        }

        // Get the number of distinct elements
        int k = set.size();

        // Use the helper function to find subarrays with at most k distinct elements
        return atMostKDistinct(arr, k) - atMostKDistinct(arr, k - 1);
    }

    /**
     * Helper function to count the number of subarrays with at most k distinct elements.
     * 
     * Intuition:
     * - We use a sliding window approach with two pointers (`i` and `j`).
     * - Expand the window by adding elements until we exceed `k` distinct elements.
     * - Shrink the window by moving the left pointer `j` until the window contains at most `k` distinct elements.
     * - The count of valid subarrays is incremented by the size of the window.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array. Each element is added and removed from the window at most once.
     * 
     * Space Complexity:
     * - O(k), used by the HashMap to store the frequency of elements in the window.
     * 
     * @param arr The input array of integers.
     * @param k The number of distinct elements allowed in the subarray.
     * @return The count of subarrays with at most k distinct elements.
     */
    public static int atMostKDistinct(int[] arr, int k) {
        int ans = 0;
        int j = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1); // Add the element to the window
            // Shrink the window if it has more than k distinct elements
            while (map.size() > k) {
                map.put(arr[j], map.get(arr[j]) - 1);
                if (map.get(arr[j]) == 0) {
                    map.remove(arr[j]);
                }
                j++; // Shrink the window by moving the left pointer
            }
            ans += (i - j + 1); // Add the number of valid subarrays ending at i
        }
        return ans; // Return the total count
    }
}
