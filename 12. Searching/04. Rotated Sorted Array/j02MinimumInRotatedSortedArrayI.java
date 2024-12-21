/*-
 * Problem Statement:
 *
 *     Given a rotated sorted array of distinct integers, find the minimum element in the array.
 *     A rotated sorted array is an array that has been shifted at some pivot point. For example:
 *     [4, 5, 6, 7, 0, 1, 2] is a rotated version of [0, 1, 2, 4, 5, 6, 7].
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *
 * Output:
 *     - The minimum element in the rotated sorted array.
 *
 * Example:
 *     Input:
 *     7
 *     4 5 6 7 0 1 2
 *     
 *     Output:
 *     0
 *
 *     Explanation:
 *     The minimum element in the rotated sorted array is 0.
 */

import java.util.Scanner;

public class j02MinimumInRotatedSortedArrayI {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Calling all solution methods
        System.out.println(findMin1(arr)); // Solution 1
        System.out.println(findMin2(arr)); // Solution 2
        System.out.println(findMin3(arr)); // Solution 3
        in.close();
    }

    /*-
     * Approach 1: Linear Search (Brute Force Approach)
     * 
     * Intuition:
     * - In a rotated sorted array, the smallest element is the one where the rotation 
     *   occurred. If we traverse the array linearly, we can identify the minimum element.
     * - This approach doesn't take advantage of the sorted nature of the array, so it is 
     *   inefficient for large arrays.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array.
     * - We may need to check each element to find the minimum.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @return The minimum element in the rotated sorted array.
     */
    public static int findMin1(int[] nums) {
        int s = 0;
        int e = nums.length - 1;

        // Linear search for the minimum element
        while (s <= e) {
            int mid = s + (e - s) / 2;

            // If the array is sorted, the minimum element is at the first position
            if (nums[mid] >= nums[0]) {
                s = mid + 1; // Move to the right half
            } else {
                e = mid - 1; // Move to the left half
            }
        }

        // If the rotation point is at the end, the first element is the minimum
        if (s == nums.length) {
            return nums[0];
        }
        return nums[s]; // Return the smallest element found
    }

    /*-
     * Approach 2: Optimized Binary Search
     * 
     * Intuition:
     * - We can optimize the search by using binary search.
     * - The key idea is to observe that the rightmost element is always greater than or equal 
     *   to the minimum element. We use this property to adjust the search space.
     * - This approach eliminates half of the array at each step, making it more efficient than 
     *   a linear search.
     * 
     * Time Complexity:
     * - O(log n), where n is the size of the array.
     * - Each iteration of binary search cuts the search space in half.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @return The minimum element in the rotated sorted array.
     */
    public static int findMin2(int[] nums) {
        int s = 0;
        int e = nums.length - 1;

        // Binary search for the minimum element
        while (s < e) {
            int mid = s + (e - s) / 2;

            // If the middle element is greater than the rightmost element, the minimum
            // must be in the right half of the array
            if (nums[mid] > nums[e]) {
                s = mid + 1;
            } else {
                e = mid; // The minimum could be at mid, so we include mid in the search
            }
        }

        return nums[s]; // The minimum element will be at index s after the loop ends
    }

    /*-
     * Approach 3: Optimized Binary Search with Comparison
     * 
     * Intuition:
     * - In this approach, we optimize binary search even further by keeping track of the 
     *   minimum value during each step.
     * - We compare the middle element with the start element and use the result to update 
     *   the search space. We also update the minimum value encountered during the search.
     * 
     * Time Complexity:
     * - O(log n), where n is the size of the array.
     * - Each iteration of binary search narrows down the search space logarithmically.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @return The minimum element in the rotated sorted array.
     */
    public static int findMin3(int[] nums) {
        int s = 0;
        int e = nums.length - 1;
        int ans = Integer.MAX_VALUE; // Initialize the minimum value to a very large number

        // Binary search with minimum value tracking
        while (s <= e) {
            int mid = s + (e - s) / 2;

            // Update the minimum value encountered so far
            if (nums[s] <= nums[mid]) {
                ans = Math.min(ans, nums[s]);
                s = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                e = mid - 1;
            }
        }

        return ans; // Return the minimum value found during the search
    }
}
