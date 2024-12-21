/**
 * Problem Statement:
 *     Given an integer array `nums` and an integer `k`, determine if there are two distinct indices `i` and `j` in the array such that:
 *     1. `nums[i] == nums[j]`
 *     2. The absolute difference between `i` and `j` is at most `k` (i.e., `|i - j| <= k`).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of integers with size `n`, where each element satisfies (-10^9 <= arr[i] <= 10^9).
 *     - An integer `k` (0 <= k <= 10^5) representing the maximum allowable index difference between duplicate values.
 * 
 * Output:
 *     - A boolean value: true if there exists any pair of indices `i` and `j` such that the elements are equal and the absolute difference of indices is at most `k`, false otherwise.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 1 2
 *     2
 *     Output:
 *     true
 * 
 *     Input:
 *     6
 *     1 2 3 1 2 3
 *     2
 *     Output:
 *     true
 * 
 *     Explanation:
 *     In the first example, `nums[0] == nums[3]` and `|0 - 3| = 3 <= 2`. Hence, the output is true.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j05ContainsDuplicatesII {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }
        int k = in.nextInt(); // Input: maximum index difference for duplicates

        System.out.println(containsNearbyDuplicate(arr, k)); // Output the result of the containsNearbyDuplicate method
        in.close();
    }

    /**
     * Approach 1: Using HashMap to store the element's most recent index
     * 
     * Intuition:
     * - We can use a HashMap to store the most recent index of each element.
     * - While iterating through the array, we check if the element has been seen before.
     * - If the element has been seen before, we calculate the difference between the current index and the previously stored index.
     * - If the difference is less than or equal to `k`, we return `true`. Otherwise, update the index of that element.
     * - If no such pair is found after iterating through the entire array, we return `false`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We are iterating through the array once, and HashMap operations (get, put) are O(1) on average.
     * 
     * Space Complexity:
     * - O(n), as we store each element and its most recent index in the HashMap.
     * 
     * @param nums The input array of integers.
     * @param k The maximum allowable index difference between duplicate elements.
     * @return A boolean indicating if there exists any pair of indices such that the absolute difference between the indices is at most `k`.
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Create a HashMap to store the most recent index of each
                                                         // element
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) { // If the element has been seen before
                if (i - map.get(nums[i]) <= k) // Check if the index difference is within the allowed range
                    return true;
                else
                    map.put(nums[i], i); // Update the index of the current element
            } else {
                map.put(nums[i], i); // If the element hasn't been seen, store its index
            }
        }
        return false; // Return false if no such pair is found
    }

    /**
     * Approach 2: Using Sliding Window and HashSet
     * 
     * Intuition:
     * - The sliding window technique is used here. We maintain a window of size `k`.
     * - We use a HashSet to store the elements within this window.
     * - For each new element in the array, we check if it is already present in the window (i.e., in the set).
     * - If it is, return `true` immediately.
     * - If the window size exceeds `k`, we remove the element that falls outside the window from the set.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We iterate through the array once, and HashSet operations (add, remove, contains) are O(1) on average.
     * 
     * Space Complexity:
     * - O(min(n, k)), as the HashSet stores at most `k` elements at any time (size of the window).
     * 
     * @param nums The input array of integers.
     * @param k The maximum allowable index difference between duplicate elements.
     * @return A boolean indicating if there exists any pair of indices such that the absolute difference between the indices is at most `k`.
     */
    public static boolean containsNearbyDuplicateSlidingWindow(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>(); // Create a HashSet to store elements within the sliding window
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) // If the current element is already in the window
                return true;
            set.add(nums[i]); // Add the current element to the window
            if (set.size() > k) { // If the window size exceeds `k`, remove the element that falls outside the
                                  // window
                set.remove(nums[i - k]);
            }
        }
        return false; // Return false if no duplicate is found within the allowed index range
    }
}
