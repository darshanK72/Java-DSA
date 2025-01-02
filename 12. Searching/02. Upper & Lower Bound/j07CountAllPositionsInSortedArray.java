/**
 * Problem Statement:
 *
 *     Given a sorted array `arr` of integers and a target integer `target`, 
 *     find the number of occurrences of `target` in the array. 
 *     If the target is not found, return 0.
 *     The array is sorted in non-decreasing order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `target` (1 <= target <= 10^5).
 *
 * Output:
 *     - The number of occurrences of `target` in the array.
 *
 * Example:
 *     Input:
 *     6
 *     1 2 3 3 3 4
 *     3
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The target `3` occurs 3 times in the array `[1, 2, 3, 3, 3, 4]`.
 */

import java.util.Scanner;

public class j07CountAllPositionsInSortedArray {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int target = in.nextInt(); // Input: the target value
        System.out.println(countAll(arr, n, target)); // Output: count of target occurrences
        in.close();
    }

    /**
     * Approach: Binary Search for First and Last Occurrences
     * 
     * Intuition:
     * - The array is sorted, so we can use binary search to efficiently find the first 
     *   and last occurrences of the target.
     * - First, we find the left-most position where the target appears using binary search.
     * - Then, we find the right-most position where the target appears.
     * - The count of occurrences of the target is simply the difference between these two 
     *   positions, plus one.
     * 
     * Time Complexity:
     * - O(log n), as we are performing binary search twice.
     * 
     * Space Complexity:
     * - O(1), since we are using a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @param n The size of the array.
     * @param target The target value.
     * @return The number of occurrences of the target.
     */
    public static int countAll(int[] nums, int n, int target) {
        int first = -1;
        int last = -1;
        int s = 0;
        int e = nums.length - 1;

        // Find the first occurrence using binary search
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        first = s;

        s = 0;
        e = nums.length - 1;

        // Find the last occurrence using binary search
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        last = e;

        // If the first index is greater than the last, no target found
        if (first > last) {
            return 0;
        }
        return last - first + 1;
    }
}
