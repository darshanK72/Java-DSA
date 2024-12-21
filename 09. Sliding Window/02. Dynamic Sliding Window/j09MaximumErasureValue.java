/**
 * Problem Statement:
 * 
 *     Given an integer array `nums`, find the maximum sum of a subarray that contains only unique elements.
 *     A subarray is a contiguous part of an array. The task is to find the maximum sum of all possible subarrays where all elements are distinct.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^4).
 * 
 * Output:
 *     - An integer representing the maximum sum of a subarray with all distinct elements.
 * 
 * Example:
 *     Input:
 *     5
 *     4 2 4 5 6
 *     Output:
 *     17
 * 
 *     Explanation:
 *     The maximum sum subarray with distinct elements is [4, 2, 5, 6] which gives a sum of 17.
 */

import java.util.HashSet;
import java.util.Scanner;

public class j09MaximumErasureValue {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call your solution
        System.out.println(maximumUniqueSubarray(arr)); // Brute force approach
        System.out.println(maximumUniqueSubarrayEfficient(arr)); // Optimized approach

        in.close();
    }

    /**
     * Approach: Brute Force Approach (Naive Approach)
     * 
     * Intuition:
     * - In this approach, we explore all possible subarrays and check if all elements are distinct.
     * - For each subarray, we calculate the sum and keep track of the maximum sum encountered.
     * - This approach uses a nested loop to explore all subarrays, and for each subarray, we use a HashSet to ensure that elements are unique.
     * 
     * Time Complexity:
     * - O(n^2) where n is the number of elements in the array. We are checking all subarrays with a nested loop.
     * 
     * Space Complexity:
     * - O(n) due to the usage of the HashSet to store distinct elements in each subarray.
     * 
     * @param nums The input array of integers.
     * @return The maximum sum of a subarray with distinct elements.
     */
    public static int maximumUniqueSubarray(int[] nums) {
        int ans = 0; // Initialize the result variable
        for (int i = 0; i < nums.length; i++) {
            int sum = 0; // Initialize the sum for each subarray starting at index `i`
            HashSet<Integer> set = new HashSet<>(); // Set to store unique elements in the subarray
            for (int j = i; j < nums.length; j++) {
                if (set.contains(nums[j])) {
                    break; // If the element is repeated, break out of the loop
                } else {
                    sum += nums[j]; // Add the element to the sum
                    set.add(nums[j]); // Add the element to the set
                }
            }
            ans = Math.max(sum, ans); // Update the maximum sum found so far
        }
        return ans; // Return the result
    }

    /**
     * Approach: Optimized Sliding Window with Two Pointers
     * 
     * Intuition:
     * - We use the sliding window technique to efficiently find the maximum sum of a subarray with distinct elements.
     * - The idea is to maintain a window with a left pointer (`j`) and a right pointer (`i`) that expands as long as we encounter unique elements.
     * - If we encounter a duplicate element, we move the left pointer to the right of the first occurrence of the duplicate and adjust the window to contain only unique elements.
     * - The sum of elements in the current window is maintained dynamically, so no recalculations are needed.
     * 
     * Time Complexity:
     * - O(n) where n is the number of elements in the array. Each element is added and removed from the set at most once.
     * 
     * Space Complexity:
     * - O(n) due to the usage of the HashSet to store unique elements in the current window.
     * 
     * @param nums The input array of integers.
     * @return The maximum sum of a subarray with distinct elements.
     */
    public static int maximumUniqueSubarrayEfficient(int[] nums) {
        int ans = 0; // Initialize the result variable
        int sum = 0; // Initialize the sum of the current window
        int j = 0; // Left pointer of the sliding window
        HashSet<Integer> set = new HashSet<>(); // Set to store unique elements in the current window

        for (int i = 0; i < nums.length; i++) {
            // If the element is already in the set, move the left pointer to the right of
            // the first occurrence of the duplicate
            while (set.contains(nums[i])) {
                set.remove(nums[j]);
                sum -= nums[j];
                j++;
            }

            // Add the current element to the window and update the sum
            set.add(nums[i]);
            sum += nums[i];
            ans = Math.max(sum, ans); // Update the maximum sum found so far
        }

        return ans; // Return the result
    }
}
