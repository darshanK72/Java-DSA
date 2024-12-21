/**
 * Problem Statement:
 * 
 *     Given an integer array `arr`, and two integers `left` and `right`, find the number of subarrays where the maximum element is between `left` and `right` (inclusive).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - Two integers `left` and `right` (1 <= left <= right <= 10^5), representing the bounds of the maximum element in the subarrays.
 * 
 * Output:
 *     - An integer representing the number of subarrays where the maximum element is between `left` and `right` (inclusive).
 * 
 * Example:
 *     Input:
 *     5
 *     2 1 3 4 5
 *     2
 *     4
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The subarrays with maximum element between `2` and `4` are:
 *     [2], [3], [3, 4]
 */

import java.util.Scanner;

public class j08CountSubarraysMaxBounded {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int left = in.nextInt(); // Input: the lower bound for the subarray max
        int right = in.nextInt(); // Input: the upper bound for the subarray max

        // Call your solution
        System.out.println(numSubarrayBoundedMax1(arr, left, right));
        System.out.println(numSubarrayBoundedMax2(arr, left, right));

        in.close();
    }

    /**
     * Approach 1: Sliding Window with Two Pointers
     * 
     * Intuition:
     * - Traverse through the array using a sliding window technique, where `i` and `j` represent the left and right pointers of the window.
     * - We maintain a variable `current` to track the number of valid subarrays where the maximum element is between `left` and `right` inclusive.
     * - If we encounter an element greater than `right`, we reset the subarray count since such elements cannot contribute to valid subarrays.
     * - If the element is within the range, we increase the subarray count.
     * 
     * Time Complexity:
     * - O(n) where n is the number of elements in the array. We traverse the array once.
     * 
     * Space Complexity:
     * - O(1) since we are only using a few extra variables.
     * 
     * @param nums The input array of integers.
     * @param left The lower bound for the subarray maximum.
     * @param right The upper bound for the subarray maximum.
     * @return The number of subarrays with maximum element between `left` and `right`.
     */
    public static int numSubarrayBoundedMax1(int[] nums, int left, int right) {
        int i = 0; // Left pointer
        int j = 0; // Right pointer
        int ans = 0; // To store the result
        int current = 0; // To store the current count of subarrays with valid max

        while (i < nums.length) {
            if (nums[i] > right) {
                current = 0; // Reset current subarray count if element exceeds `right`
                j = i + 1; // Move left pointer to the next position after `i`
            } else if (nums[i] >= left) {
                current = i - j + 1; // Count subarrays ending at `i` with valid max
            }

            ans += current; // Add the number of valid subarrays to the result
            i++; // Move the right pointer
        }

        return ans;
    }

    /**
     * Approach 2: Counting Subarrays with Max Less Than K
     * 
     * Intuition:
     * - We can compute the number of subarrays with the maximum element less than `right` and subtract the number of subarrays where the maximum element is less than `left`.
     * - This gives the number of subarrays where the maximum element is between `left` and `right`.
     * 
     * Time Complexity:
     * - O(n) where n is the number of elements in the array. We perform two passes over the array: one for `right` and one for `left - 1`.
     * 
     * Space Complexity:
     * - O(1) since we are only using a few extra variables.
     * 
     * @param nums The input array of integers.
     * @param left The lower bound for the subarray maximum.
     * @param right The upper bound for the subarray maximum.
     * @return The number of subarrays with maximum element between `left` and `right`.
     */
    public static int numSubarrayBoundedMax2(int[] nums, int left, int right) {
        return countSubarrayMaxLessThanK(nums, right) - countSubarrayMaxLessThanK(nums, left - 1);
    }

    /**
     * Helper Method: Count Subarrays with Maximum Less Than K
     * 
     * Intuition:
     * - This method counts the number of subarrays where the maximum element is strictly less than a given value `k`.
     * 
     * Time Complexity:
     * - O(n), as we are traversing the array once.
     * 
     * Space Complexity:
     * - O(1), since we are using only a few extra variables.
     * 
     * @param arr The input array of integers.
     * @param k The threshold value for the maximum element.
     * @return The number of subarrays where the maximum element is less than `k`.
     */
    public static int countSubarrayMaxLessThanK(int arr[], int k) {
        int ans = 0; // To store the result
        int current = 0; // To store the current number of valid subarrays

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < k) {
                current++; // If element is less than `k`, increase the subarray count
            } else {
                current = 0; // Reset if element is equal to or greater than `k`
            }
            ans += current; // Add the valid subarrays ending at this element
        }

        return ans;
    }
}
