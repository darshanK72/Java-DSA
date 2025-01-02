/**
 * Problem Statement:
 * 
 *     You are given an array `arr[]` of size `n` consisting of only 0s and 1s. 
 *     You need to find the **transition point** in the array, which is the index of the first `1` 
 *     after all the `0`s. If the array contains only `0`s, return `-1`. 
 *     If the array starts with `1`, the transition point is the first element.
 * 
 *     - If the array is [0, 0, 0, 1, 1], the transition point is index `3` (the first `1`).
 *     - If the array is [0, 0, 0, 0], the transition point is `-1`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element is either `0` or `1`.
 * 
 * Output:
 *     - The index of the transition point if found, otherwise `-1`.
 * 
 * Example:
 *     Input:
 *     5
 *     0 0 0 1 1
 *     Output:
 *     3
 *     
 *     Explanation:
 *     The transition point is at index `3`, where the first `1` appears after all `0`s.
 */

import java.util.Scanner;

public class j02FindTransitionPoint {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        System.out.println(transitionPoint(arr, n)); // Output from transition point search
        System.out.println(transitionPointEfficient(arr, n)); // Output from efficient transition point search
        in.close();
    }

    /**
     * Approach 1: Transition Point (Basic Binary Search)
     * 
     * Intuition:
     * - The goal is to find the index of the first `1` in the array. We use binary search to 
     *   efficiently find the transition point.
     * - If the last element is `0`, the transition point doesn't exist, so return `-1`.
     * - If the first element is `1`, return `0` because the first element is already the transition point.
     * - For other cases, we perform binary search to locate the transition point, adjusting the 
     *   search space based on whether the middle element is `0` or `1`.
     * 
     * Time Complexity:
     * - O(log n), since we are performing binary search.
     * 
     * Space Complexity:
     * - O(1), as we are only using a few extra variables.
     * 
     * @param arr The input array of numbers.
     * @param n The size of the array.
     * @return The index of the transition point, or -1 if no such point exists.
     */
    public static int transitionPoint(int arr[], int n) {
        if (arr[n - 1] == 0) {
            return -1; // No transition point if the last element is 0
        }
        if (arr[0] == 1) {
            return 0; // The first element is already the transition point
        }
        int s = 0; // Start index
        int e = n - 1; // End index
        while (s <= e) {
            int mid = (s + e) / 2; // Calculate mid index
            if (arr[mid] == 0) {
                s = mid + 1; // Adjust search space to the right if mid is 0
            } else if (arr[mid - 1] == 1) {
                e = mid - 1; // Adjust search space to the left if the previous element is 1
            } else {
                return mid; // Return the transition point if mid is the first 1
            }
        }
        return -1; // Return -1 if no transition point is found
    }

    /**
     * Approach 2: Efficient Transition Point Search (Optimized Binary Search)
     * 
     * Intuition:
     * - This approach uses a more efficient binary search to directly locate the transition point 
     *   by focusing on the first occurrence of `1`.
     * - The binary search checks the middle element and adjusts the search space to the right or left 
     *   depending on whether the element is `0` or `1`.
     * - This approach works without needing to check for the previous element explicitly as in Approach 1.
     * 
     * Time Complexity:
     * - O(log n), since we still perform binary search.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param arr The input array of numbers.
     * @param n The size of the array.
     * @return The index of the transition point, or -1 if no such point exists.
     */
    public static int transitionPointEfficient(int arr[], int n) {
        if (arr[n - 1] == 0) {
            return -1; // No transition point if the last element is 0
        }
        int s = 0; // Start index
        int e = n - 1; // End index
        while (s <= e) {
            int mid = (s + e) / 2; // Calculate mid index
            if (arr[mid] == 0) {
                s = mid + 1; // Adjust search space to the right if mid is 0
            } else {
                e = mid - 1; // Adjust search space to the left if mid is 1
            }
        }
        return s; // The first `1` will be at index `s` after binary search
    }
}
