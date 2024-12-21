/**
 * Problem Statement:
 * 
 *     Given an array of integers `arr` and an integer `k`, the task is to find how many contiguous subarrays
 *     of the array have a product strictly less than `k`.
 * 
 *     A subarray is a contiguous part of the array.
 * 
 * Input:
 *     - An integer `n` (1 ≤ n ≤ 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 ≤ arr[i] ≤ 1000).
 *     - An integer `k` (1 ≤ k ≤ 10^6), the threshold for the product of the subarray.
 * 
 * Output:
 *     - Return the total number of subarrays whose product is strictly less than `k`.
 * 
 * Example:
 *     Input:
 *     4
 *     10 5 2 6
 *     100
 *     Output:
 *     8
 *     
 *     Explanation:
 *     The subarrays whose product is less than 100 are: 
 *     [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 *     Hence, the output is 8.
 */

import java.util.Scanner;

public class j06SubarrayProductLessThanK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the threshold product

        // Call the solution method
        System.out.println(numSubarrayProductLessThanK(arr, k)); // Output the result

        in.close();
    }

    /**
     * Approach: Sliding Window (Two Pointers)
     * 
     * Intuition:
     * - The task is to find the number of contiguous subarrays whose product is strictly less than `k`.
     * - To solve this, we can use a sliding window (or two-pointer) approach. We maintain a window `[j, i]` where:
     *   - `j` is the start of the window and `i` is the end of the window.
     *   - As we expand the window by moving `i`, we multiply the elements in the current window.
     *   - If the product of the window exceeds or equals `k`, we move the start pointer `j` to shrink the window until the product is less than `k`.
     *   - For each position of `i`, we count all the valid subarrays ending at `i`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. Both pointers traverse the array once.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space for variables to track the product and pointers.
     * 
     * @param nums The input array of integers.
     * @param k The threshold for the product.
     * @return The total number of subarrays whose product is strictly less than `k`.
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) // If k is 0, no product will be less than 0, so return 0.
            return 0;

        int currentProd = 1; // To track the product of the current window
        int j = 0; // Left pointer of the sliding window
        int ans = 0; // To store the total count of valid subarrays

        // Iterate through the array with the right pointer `i`
        for (int i = 0; i < nums.length; i++) {
            currentProd *= nums[i]; // Multiply the current element to the product

            // Shrink the window from the left if the product exceeds or equals `k`
            while (currentProd >= k && j < i) {
                currentProd /= nums[j]; // Remove the element at the left pointer
                j++; // Move the left pointer to shrink the window
                if (j == nums.length) // If `j` exceeds the array length, return the answer
                    return ans;
            }

            // If the product is valid (less than `k`), count the subarrays
            if (currentProd < k)
                ans += (i - j + 1); // The number of subarrays ending at `i` is (i - j + 1)
        }

        return ans; // Return the total count of valid subarrays
    }
}
