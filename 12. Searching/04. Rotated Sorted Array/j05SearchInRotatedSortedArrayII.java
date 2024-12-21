/*-
 * Problem Statement:
 * 
 *     Given an integer array `nums` sorted in non-decreasing order, it is rotated at an unknown pivot index. 
 *     (i.e., [1, 2, 3, 4, 5] might become [4, 5, 1, 2, 3]). 
 *     Return `true` if `target` is in the array and `false` if it is not.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `target` representing the target number to search for.
 * 
 * Output:
 *     - A boolean value, `true` if the target exists in the array, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     5
 *     2 5 6 0 0
 *     0
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The target 0 is present in the array.
 * 
 */

import java.util.Scanner;

public class j05SearchInRotatedSortedArrayII {

    public static void main(String args[]) {
        // Reading the input from the user
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // The size of the array
        int[] arr = new int[n];

        // Reading the elements of the array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input each element of the array
        }

        int target = in.nextInt(); // The target element to search for

        // Call the search method and print the result
        System.out.println(search(arr, target));
        in.close();
    }

    /*-
     * Approach: Binary Search with Handling of Duplicates
     * 
     * Intuition:
     * - The array is rotated and contains duplicates. We will perform a modified binary search
     *   to search for the target. The challenge here is to handle the presence of duplicates,
     *   which makes it difficult to decide the side to eliminate.
     * - The main idea is to compare the middle element with the target, and decide whether to
     *   move the left or right pointer. If duplicates are encountered at both ends, we shrink
     *   the search space by incrementing `s` and decrementing `e`.
     * 
     * Time Complexity:
     * - O(n) in the worst case when there are many duplicates (since the search space may shrink
     *   one element at a time).
     * 
     * Space Complexity:
     * - O(1) as we are using a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @param target The target value to search for.
     * @return boolean indicating whether the target is found or not.
     */
    public static boolean search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;

            // If we find the target at mid, return true
            if (nums[mid] == target) {
                return true;
            }

            // If there are duplicates at both ends, we cannot decide the direction,
            // so we reduce the search space by incrementing s and decrementing e.
            if (nums[mid] == nums[s] && nums[mid] == nums[e]) {
                s++;
                e--;
                continue;
            }

            // Determine which half is sorted
            if (nums[s] <= nums[mid]) {
                // Left half is sorted
                if (nums[s] <= target && nums[mid] >= target) {
                    e = mid - 1; // target is in the left half
                } else {
                    s = mid + 1; // target is in the right half
                }
            } else {
                // Right half is sorted
                if (nums[mid] <= target && nums[e] >= target) {
                    s = mid + 1; // target is in the right half
                } else {
                    e = mid - 1; // target is in the left half
                }
            }
        }
        return false; // If we exit the loop, target is not found
    }
}
