/**
 * Problem Statement:
 * 
 *     Given a sorted array of distinct integers and a target value, return the index if the target is found. 
 *     If not, return the index where it would be if it were inserted in order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the size of the array.
 *     - A sorted array `arr` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `target` (1 <= target <= 10^5).
 * 
 * Output:
 *     - The index at which the target is or would be inserted while maintaining the sorted order.
 * 
 * Example:
 *     Input:
 *     5
 *     1 3 5 6 8
 *     5
 *     Output:
 *     2
 *     
 *     Explanation:
 *     In the array `[1, 3, 5, 6, 8]`, the target `5` is found at index `2`. Hence, the output is `2`.
 */

import java.util.Scanner;

public class j01SearchInsertPosition {

    // Lower Bound: Finds the index of the first element that is greater than or
    // equal to the target.
    // Ceiling Index: Finds the index of the smallest element greater than or equal
    // to the target.
    // Insert Index: This is where the target would be inserted while maintaining
    // sorted order,
    // which is exactly the position of the first element greater than or equal to
    // the target.

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the sorted array
        }
        int target = in.nextInt(); // Input: target value to search for or insert
        System.out.println(searchInsert(arr, target)); // Output: index where the target is or should be inserted
        in.close();
    }

    /**
     * Approach 1: Binary Search to Find Insert Position
     * 
     * Intuition:
     * - We are given a sorted array, and we need to find the position where the target should be inserted to maintain the sorted order.
     * - A binary search is perfect for this problem because it allows us to quickly narrow down the search space.
     * - If the target is found, we return the index of the target. If the target is not found, we return the index where it should be inserted.
     * - The binary search works by repeatedly dividing the search range into two halves and checking the middle element.
     * 
     * Time Complexity:
     * - O(log n), because the binary search reduces the problem size by half in each step.
     * 
     * Space Complexity:
     * - O(1), as we are only using a few variables to perform the binary search.
     * 
     * @param nums The input array of integers.
     * @param target The target value to search for or insert.
     * @return The index where the target is or should be inserted.
     */
    public static int searchInsert(int[] nums, int target) {
        int s = 0; // Start index
        int e = nums.length - 1; // End index
        while (s <= e) {
            int mid = (s + e) / 2; // Calculate middle index
            if (nums[mid] < target) {
                s = mid + 1; // Search the right half if nums[mid] < target
            } else {
                e = mid - 1; // Search the left half if nums[mid] >= target
            }
        }
        return s; // Return the insert position, which is the first element >= target
    }
}
