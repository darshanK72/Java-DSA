/**
 * Problem Statement:
 * 
 *     You are given a sorted array of integers. A fixed point is an index `i` such that `arr[i] = i`.
 *     Your task is to find if there exists such an index `i` where the value at that index is equal to the index itself.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the size of the array.
 *     - A sorted array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the index `i` such that `arr[i] = i`. If no such index exists, return `-1`.
 * 
 * Example:
 *     Input:
 *     5
 *     -10 -5 0 3 7
 *     Output:
 *     3
 *     
 *     Explanation:
 *     In the array `[-10, -5, 0, 3, 7]`, the value at index 3 is `3`, which is equal to the index. Hence, the output is `3`.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j06IndexFixedPoint {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt()); // Input: elements of the sorted array
        }
        System.out.println(equalIndex(arr, n)); // Output: the index where arr[i] = i
        in.close();
    }

    /**
     * Approach 1: Binary Search for Fixed Point
     * 
     * Intuition:
     * - A fixed point is an index `i` such that `arr[i] = i`. 
     * - Since the array is sorted, we can use binary search to efficiently find the fixed point.
     * - The idea is to start with the middle element of the array:
     *     - If `arr[mid] == mid`, then we've found the fixed point, so return `mid`.
     *     - If `arr[mid] > mid`, this means the fixed point must lie to the left of `mid` (since the array is sorted and we know that the values are getting larger as the index increases). Hence, we search the left half.
     *     - If `arr[mid] < mid`, the fixed point must lie to the right of `mid`, so we search the right half.
     * - This approach works in `O(log n)` time, which is optimal for this problem.
     * 
     * Time Complexity:
     * - O(log n), because we are performing a binary search.
     * 
     * Space Complexity:
     * - O(1), as we are only using a few variables for the search.
     * 
     * @param arr The input array of integers.
     * @param n The size of the array.
     * @return The index where arr[i] = i, or -1 if no such index exists.
     */
    public static int equalIndex(ArrayList<Integer> arr, int n) {
        int s = 0; // Start index
        int e = n - 1; // End index
        while (s <= e) {
            int mid = (s + e) / 2; // Calculate middle index
            if (arr.get(mid) == mid) {
                return mid; // Found the fixed point
            } else if (arr.get(mid) > mid) {
                e = mid - 1; // Search left half if arr[mid] > mid
            } else {
                s = mid + 1; // Search right half if arr[mid] < mid
            }
        }
        return -1; // No fixed point found
    }
}
