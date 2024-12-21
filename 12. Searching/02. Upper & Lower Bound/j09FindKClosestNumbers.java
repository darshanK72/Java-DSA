/*-
 * Problem Statement:
 *
 *     Given a sorted array `arr` of integers and a target integer `x`, find the `k` closest integers 
 *     to `x` in the array. If there are multiple answers, return the smallest `k` integers. 
 *     The answer should be sorted by the array's order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= n), the number of closest integers to find.
 *     - An integer `x` (1 <= x <= 10^5), the target value.
 *
 * Output:
 *     - A list of `k` integers closest to `x`, in sorted order.
 *
 * Example:
 *     Input:
 *     5
 *     1 3 5 7 9
 *     3
 *     5
 *     Output:
 *     [3, 5, 7]
 * 
 *     Explanation:
 *     The closest 3 numbers to `5` in the array `[1, 3, 5, 7, 9]` are `3`, `5`, and `7`.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j09FindKClosestNumbers {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: number of closest elements to find
        int target = in.nextInt(); // Input: the target value
        System.out.println(findClosestElements(arr, k, target)); // Output: closest numbers
        in.close();
    }

    /*-
     * Approach: Binary Search and Two-Pointer Technique
     * 
     * Intuition:
     * - The array is sorted, so we can use binary search to efficiently find the position where 
     *   the target `x` would be inserted. This helps us identify the starting point for finding 
     *   the closest elements.
     * - Once we find the position, we can use a two-pointer technique to expand left and right from 
     *   the target and collect the `k` closest elements.
     * - We compare the distances of the numbers on the left and right of the target and choose the 
     *   closer one to add to the result list. We repeat this process until we've collected `k` numbers.
     * 
     * Time Complexity:
     * - O(log n + k), where `log n` is the time complexity of the binary search, and `k` is the time complexity 
     *   for collecting the closest elements.
     * 
     * Space Complexity:
     * - O(k), as we store the `k` closest numbers in the result list.
     * 
     * @param arr The input array of numbers.
     * @param k The number of closest elements to find.
     * @param x The target value.
     * @return The list of `k` closest numbers.
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        ArrayList<Integer> out = new ArrayList<>();
        int s = 0;
        int e = arr.length - 1;

        // Binary search to find the position where target `x` would be inserted
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] >= x) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        // Use two pointers to expand around the target and find `k` closest elements
        while (k > 0) {
            int ceilDist = s == arr.length ? Integer.MAX_VALUE : arr[s] - x;
            int floorDist = e == -1 ? Integer.MAX_VALUE : x - arr[e];
            if (floorDist <= ceilDist) {
                e--;
            } else {
                s++;
            }
            k--;
        }

        // Collect the closest elements
        for (int i = e + 1; i < s; i++) {
            out.add(arr[i]);
        }
        return out;
    }
}
