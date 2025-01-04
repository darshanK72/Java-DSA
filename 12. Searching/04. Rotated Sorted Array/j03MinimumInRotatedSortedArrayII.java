/**
 * Problem Statement:
 *
 *     Given a rotated sorted array with possible duplicates, find the minimum element in the array.
 *     A rotated sorted array is an array that has been shifted at some pivot point. For example:
 *     [4, 5, 6, 7, 0, 1, 2] is a rotated version of [0, 1, 2, 4, 5, 6, 7].
 *     The array can contain duplicates, which means some elements might be equal.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *
 * Output:
 *     - The minimum element in the rotated sorted array, even if duplicates are present.
 *
 * Example:
 *     Input:
 *     7
 *     2 2 2 0 1 1 2
 *     
 *     Output:
 *     0
 *
 *     Explanation:
 *     The minimum element in the rotated sorted array is 0.
 */

import java.util.Scanner;

public class j03MinimumInRotatedSortedArrayII {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Calling the solution method
        System.out.println(findMin2(arr)); // Solution for finding the minimum element
        in.close();
    }

    /**
     * Approach: Optimized Binary Search with Handling Duplicates
     * 
     * Intuition:
     * - In a rotated sorted array with duplicates, we cannot directly rely on the fact that 
     *   the rightmost element is always greater than or equal to the minimum element, as 
     *   duplicates might be present.
     * - When we encounter duplicates, the usual binary search logic (deciding which half to 
     *   discard based on the comparison between the middle and right elements) breaks down.
     * - We handle this situation by reducing the search space even when the middle element is 
     *   equal to the rightmost element.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array.
     * - In the worst case, when there are many duplicates, we might have to reduce the search 
     *   space by one element at a time.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @return The minimum element in the rotated sorted array.
     */
    public static int findMin2(int[] nums) {
        int s = 0;
        int e = nums.length - 1;

        // Binary search for the minimum element, with handling for duplicates
        while (s < e) {
            int mid = s + (e - s) / 2;

            // If the middle element is greater than the rightmost element, the minimum is
            // in the right half
            if (nums[mid] > nums[e]) {
                s = mid + 1;
            }
            // If the middle element is smaller than the rightmost element, the minimum is
            // in the left half, including mid
            else if (nums[mid] < nums[e]) {
                e = mid;
            }
            // If the middle element is equal to the rightmost element, we can't determine
            // which half contains the minimum, so we reduce the search space by moving the
            // right pointer to the left
            else {
                e--;
            }
        }

        return nums[s]; // The minimum element will be at index s after the loop ends
    }
}
