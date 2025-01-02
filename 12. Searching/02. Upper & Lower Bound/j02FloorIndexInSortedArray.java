/**
 * Problem Statement:
 * 
 *     Given a sorted array of distinct integers and a target value `x`, 
 *     find the index of the largest element that is less than or equal to `x`. 
 *     This index is referred to as the "floor index" of the target.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the size of the array.
 *     - A sorted array `arr` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 *     - A target value `x` (1 <= x <= 10^5).
 * 
 * Output:
 *     - The index of the largest element that is less than or equal to `x`. 
 *       If no such element exists, return `-1`.
 * 
 * Example:
 *     Input:
 *     6
 *     1 3 8 10 12 15
 *     9
 *     Output:
 *     3
 *     
 *     Explanation:
 *     In the array `[1, 3, 8, 10, 12, 15]`, the largest element that is less than or equal to `9` is `8`, 
 *     and its index is `3`. Hence, the output is `3`.
 */

import java.util.Scanner;

public class j02FloorIndexInSortedArray {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        long[] arr = new long[n]; // Array of long integers
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong(); // Input: sorted array elements
        }
        long target = in.nextInt(); // Input: target value to find the floor index of
        System.out.println(findFloor(arr, n, target)); // Output: floor index of the target
        in.close();
    }

    /**
     * Approach 1: Binary Search for Floor Index
     * 
     * Intuition:
     * - The goal is to find the largest element that is less than or equal to the target.
     * - A binary search is effective for this problem because the array is sorted. 
     * - The search continues by comparing the middle element to the target:
     *     - If the middle element is greater than the target, the floor element must be in the left half.
     *     - If the middle element is less than or equal to the target, we continue searching the right half to find a potentially larger floor.
     * - The result will be the rightmost element that is smaller than or equal to the target.
     * 
     * Time Complexity:
     * - O(log n), because we perform a binary search.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables to perform the binary search.
     * 
     * @param arr The input sorted array.
     * @param n The size of the array.
     * @param x The target value for which the floor index is to be found.
     * @return The index of the largest element that is <= target, or -1 if no such element exists.
     */
    public static int findFloor(long arr[], int n, long x) {
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
        return e; // Return the index of the floor element (largest <= target)
    }
}
