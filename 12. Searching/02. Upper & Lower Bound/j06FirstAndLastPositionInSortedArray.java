/*-
 * Problem Statement:
 * 
 *     Given a sorted array `nums[]` and a target value `target`, we need to find:
 *     - The **first position** of the target in the array (i.e., the index of the first occurrence of `target`).
 *     - The **last position** of the target in the array (i.e., the index of the last occurrence of `target`).
 *     
 *     If the target is not found, return `-1` for both positions.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `target` (1 <= target <= 10^5), representing the target value.
 * 
 * Output:
 *     - An array of two integers: the first element is the first position, and the second element is the last position.
 *       If either position is not found, return `-1` for that value.
 * 
 * Example:
 *     Input:
 *     7
 *     5 7 7 8 8 10 10
 *     8
 *     
 *     Output:
 *     First : 3
 *     Last : 4
 * 
 * Explanation:
 *     The first position of 8 is at index 3 and the last position of 8 is at index 4.
 */

import java.util.Scanner;

public class j06FirstAndLastPositionInSortedArray {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        int[] out = searchRange1(arr, target);
        System.out.println("First : " + out[0]);
        System.out.println("Last : " + out[1]);
        in.close();
    }

    /*-
     * Approach 1: Searching first and last position separately.
     * 
     * - First, we find the first occurrence of the target.
     * - Then, we find the last occurrence of the target.
     * - If the first index is greater than the last, it means the target is not present, so we return [-1, -1].
     * 
     * Time Complexity: O(log n) for each search (two binary searches), so O(log n) in total.
     * Space Complexity: O(1), as we are using constant extra space.
     */
    public static int[] searchRange1(int[] nums, int target) {
        int first = -1;
        int last = -1;

        // Find the first occurrence of the target.
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        first = s;

        // Find the last occurrence of the target.
        s = 0;
        e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        last = e;

        // If first is greater than last, the target is not in the array.
        if (first > last) {
            return new int[] { -1, -1 };
        }
        return new int[] { first, last };
    }

    /*-
     * Approach 2: Using a helper function to find first and last positions.
     * 
     * - We reuse the binary search approach for both the first and last occurrence by passing a flag `findFirst`.
     * - When `findFirst` is true, we search for the first occurrence, and when false, for the last occurrence.
     * 
     * Time Complexity: O(log n) for each search (two binary searches), so O(log n) in total.
     * Space Complexity: O(1), as we are using constant extra space.
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] ans = new int[] { -1, -1 };
        ans[0] = findPosition(nums, target, true); // Find the first position.
        ans[1] = findPosition(nums, target, false); // Find the last position.
        return ans;
    }

    /*-
     * Helper function for binary search.
     * - `findFirst`: If true, find the first occurrence, otherwise find the last occurrence.
     */
    public static int findPosition(int[] nums, int target, boolean findFirst) {
        int s = 0;
        int e = nums.length - 1;
        int pos = -1;

        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] < target) {
                s = mid + 1;
            } else if (nums[mid] > target) {
                e = mid - 1;
            } else {
                pos = mid;
                if (findFirst) {
                    e = mid - 1; // Search for the first occurrence.
                } else {
                    s = mid + 1; // Search for the last occurrence.
                }
            }
        }
        return pos;
    }
}
