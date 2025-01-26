/**
 * Problem Statement:
 * 
 *     Given an integer array `nums`, you need to find the length of the shortest subarray that, when sorted, makes the whole array sorted.
 *     In other words, identify the smallest subarray that needs to be sorted in order to sort the entire array.
 * 
 * Input:
 *     - An integer array `nums` of length `n` (1 <= n <= 10^4).
 * 
 * Output:
 *     - The length of the shortest subarray which, when sorted, will make the whole array sorted.
 * 
 * Example:
 *     Input:
 *     nums = [2, 6, 4, 8, 10, 9, 15]
 *     
 *     Output:
 *     5
 *     
 * Explanation:
 *     The shortest subarray that needs to be sorted is `[6, 4, 8, 10, 9]` which has a length of 5.
 * 
 * Approach:
 *     1. Traverse from the left to find the rightmost position where the array is out of order (end).
 *     2. Traverse from the right to find the leftmost position where the array is out of order (start).
 *     3. If `start` and `end` are valid indices, return the length of the subarray between them.
 *     4. If the array is already sorted, return 0.
 */

import java.util.Scanner;

public class j10ShortestUnsortedContinuousArray {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        // Input array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Find the length of the shortest unsorted subarray
        System.out.println(findUnsortedSubarray(arr));
        in.close();
    }

    /**
     * Approach:
     * 1. Traverse the array from left to right to find the `end` index (the rightmost element that is out of order).
     * 2. Traverse the array from right to left to find the `start` index (the leftmost element that is out of order).
     * 3. If `start` and `end` are valid, return the length of the subarray between them.
     * 4. If no such subarray exists, return 0, which means the array is already sorted.
     *
     * Time Complexity: O(n) where n is the length of the array, as we traverse the array twice.
     * Space Complexity: O(1), as no extra space is used other than the given input array.
     */
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int end = -1;
        int maxTill = nums[0];

        // Find the rightmost element which is out of order
        for (int i = 1; i < n; i++) {
            if (nums[i] < maxTill) {
                end = i; // Update the end of the subarray
            } else {
                maxTill = nums[i];
            }
        }

        int start = 0;
        int minTill = nums[n - 1];

        // Find the leftmost element which is out of order
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > minTill) {
                start = i; // Update the start of the subarray
            } else {
                minTill = nums[i];
            }
        }

        // If no such subarray exists, return 0 (the array is already sorted)
        if (end == -1) {
            return 0;
        }

        // Return the length of the unsorted subarray
        return end - start + 1;
    }
}
