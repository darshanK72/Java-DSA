/**
 * Problem Statement:
 * 
 *     Given a sorted array of integers and a target value `x`, 
 *     find the index of the first element strictly greater than `x`. 
 *     This index is referred to as the "upper bound" of the target.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the size of the array.
 *     - A sorted array `arr` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 *     - A target value `x` (1 <= x <= 10^5).
 * 
 * Output:
 *     - The index of the first element strictly greater than `x`. 
 *       If no such element exists, return `n` (i.e., the size of the array).
 * 
 * Example:
 *     Input:
 *     6
 *     1 3 8 10 12 15
 *     9
 *     Output:
 *     4
 *     
 *     Explanation:
 *     In the array `[1, 3, 8, 10, 12, 15]`, the first element strictly greater than `9` is `10`, 
 *     and its index is `4`. Hence, the output is `4`.
 */

import java.util.Scanner;

public class j03UpperBound {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n]; // Array of integers
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: sorted array elements
        }
        int target = in.nextInt(); // Input: target value to find the upper bound of
        System.out.println(getUpperBound(arr, n, target)); // Output: upper bound index of the target
        in.close();
    }

    /**
     * Approach 1: Binary Search for Upper Bound
     * 
     * Intuition:
     * - The goal is to find the first element that is strictly greater than the target `x`.
     * - Binary search works efficiently for this problem because the array is sorted.
     * - The process works as follows:
     *     - If the middle element is greater than `x`, we move the search to the left half, 
     *       as the upper bound could be on the left side.
     *     - If the middle element is less than or equal to `x`, the upper bound must be on the right half, 
     *       so we continue the search in the right side.
     * - When the search finishes, the index `s` will point to the first element that is strictly greater than `x`.
     * 
     * Time Complexity:
     * - O(log n), because we perform a binary search, halving the search space with each iteration.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables to perform the binary search.
     * 
     * @param arr The input sorted array.
     * @param n The size of the array.
     * @param x The target value for which the upper bound is to be found.
     * @return The index of the first element strictly greater than `x`, or `n` if no such element exists.
     */
    public static int getUpperBound(int arr[], int n, int x) {
        int s = 0; // Start index
        int e = n - 1; // End index
        while (s <= e) {
            int mid = s + (e - s) / 2; // Calculate middle index
            if (arr[mid] > x) {
                e = mid - 1; // Search in the left half if arr[mid] > target
            } else {
                s = mid + 1; // Search in the right half if arr[mid] <= target
            }
        }
        return s; // Return the index of the first element strictly greater than target
    }
}
