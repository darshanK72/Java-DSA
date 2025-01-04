/**
 * Problem Statement:
 * 
 *     A peak element in an array is an element that is strictly greater than its neighbors. 
 *     For example, in the array `[1, 3, 20, 4, 1]`, the element `20` is a peak element.
 *     Given an array of integers `nums`, find a peak element and return its index.
 *     If the array contains multiple peaks, return the index of any one of the peaks.
 *     You may assume that the array is non-empty, and there will always be at least one peak.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^6).
 * 
 * Output:
 *     - The index of any peak element in the array.
 * 
 * Example:
 *     Input:
 *     5
 *     1 3 20 4 1
 *     Output:
 *     2
 *     Explanation: The peak element is `20`, and its index is `2`.
 * 
 *     Input:
 *     4
 *     1 2 3 1
 *     Output:
 *     2
 *     Explanation: The peak element is `3`, and its index is `2`.
 */

import java.util.Scanner;

public class j03FindPeakElement {

    public static void main(String args[]) {
        // Reading the input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // The size of the array
        int[] arr = new int[n];

        // Reading the array elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input each element of the array
        }

        System.out.println(findPeakElement(arr)); // Call the function to find peak element
        in.close();
    }

    /**
     * Approach 1: Binary Search for Peak Element
     * 
     * Intuition:
     * - A peak element is an element that is greater than its neighbors. We can use a binary 
     *   search to find the peak element in O(log n) time.
     * - If the middle element is smaller than the next element, we know the peak lies to the 
     *   right, so we move the search to the right half. Similarly, if the middle element is 
     *   smaller than the previous element, the peak lies to the left.
     * - If the middle element is greater than both its neighbors, it is the peak element.
     * 
     * Time Complexity:
     * - O(log n), as we are using binary search.
     * 
     * Space Complexity:
     * - O(1), using constant space for the operations.
     * 
     * @param nums The input array of numbers.
     * @return The index of any peak element.
     */
    public static int findPeakElement(int[] nums) {
        int n = nums.length;

        // Handle base cases
        if (n == 1)
            return 0; // Single element is always a peak
        if (nums[0] > nums[1])
            return 0; // First element is a peak
        if (nums[n - 1] > nums[n - 2])
            return n - 1; // Last element is a peak

        int s = 0;
        int e = n - 1;

        // Binary search to find the peak
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] < nums[mid + 1]) {
                s = mid + 1; // Peak is to the right
            } else if (nums[mid] < nums[mid - 1]) {
                e = mid - 1; // Peak is to the left
            } else {
                return mid; // mid is the peak element
            }
        }
        return -1; // Return -1 if no peak is found, though it should never reach here
    }
}
