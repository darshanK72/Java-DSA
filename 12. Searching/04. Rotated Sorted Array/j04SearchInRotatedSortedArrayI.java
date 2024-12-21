/*-
 * Problem Statement:
 *
 *     Given a rotated sorted array, search for a target element in the array.
 *     The array is initially sorted, but then rotated at some pivot. 
 *     You need to return the index of the target element if found, otherwise return -1.
 *
 *     The array may contain duplicates, which complicates the binary search process.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `target` (1 <= target <= 10^5), representing the target value to search for in the array.
 *
 * Output:
 *     - The index of the target element in the array if found, otherwise return -1.
 *
 * Example:
 *     Input:
 *     7
 *     4 5 6 7 0 1 2
 *     0
 *     
 *     Output:
 *     4
 *
 *     Explanation:
 *     The target `0` is found at index 4 in the rotated sorted array.
 */

import java.util.Scanner;

public class j04SearchInRotatedSortedArrayI {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int target = in.nextInt(); // Input: target value to search
        System.out.println(search1(arr, target)); // Solution 1: Searching in rotated sorted array
        System.out.println(search2(arr, target)); // Solution 2: Optimized solution handling duplicates
        System.out.println(search3(arr, target)); // Solution 3: Standard binary search for rotated sorted array
        in.close();
    }

    /*-
     * Approach 1: Rotated Search with Binary Search
     * 
     * Intuition:
     * - The first step is to determine which side of the array is rotated and then apply 
     *   binary search on the appropriate side.
     * - If the left side is sorted, then we can apply binary search there; if not, we know 
     *   the right side is sorted and apply binary search on the right side.
     * - Once we know which side is sorted, we can narrow our search to that side.
     * - If the array is rotated and the target is present, we will find the target via the 
     *   binary search in either side.
     * 
     * Time Complexity:
     * - O(log n), where n is the size of the array, as binary search is applied.
     * 
     * Space Complexity:
     * - O(1), as we are only using constant space.
     * 
     * @param nums The input array of numbers.
     * @param target The value we are searching for.
     * @return The index of the target if found, otherwise -1.
     */
    public static int search1(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;

        // Find the pivot where the array is rotated
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] >= nums[0]) {
                s = mid + 1; // Target is on the right side
            } else {
                e = mid - 1; // Target is on the left side
            }
        }

        // Search in the appropriate side of the rotated array
        if (s == nums.length) {
            return binarySearch(nums, 0, nums.length - 1, target); // Search entire array
        } else if (target >= nums[0]) {
            return binarySearch(nums, 0, e, target); // Search in the left half
        } else {
            return binarySearch(nums, s, nums.length - 1, target); // Search in the right half
        }
    }

    /*-
     * Approach 2: Optimized Binary Search with Duplicates Handling
     * 
     * Intuition:
     * - In this approach, we handle duplicates that could be present in the array. 
     * - When duplicates are encountered, we cannot make definitive decisions based 
     *   on comparisons between mid and right (or mid and left), so we reduce the search space 
     *   by moving the right pointer (`e--`) when duplicates are found.
     * 
     * Time Complexity:
     * - O(log n) in the best case and O(n) in the worst case due to handling duplicates.
     * 
     * Space Complexity:
     * - O(1), as we are only using constant space.
     * 
     * @param nums The input array of numbers.
     * @param target The value we are searching for.
     * @return The index of the target if found, otherwise -1.
     */
    public static int search2(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;

        // Binary search with duplicate handling
        while (s < e) {
            int mid = s + (e - s) / 2;

            if (nums[mid] > nums[e]) {
                s = mid + 1; // The minimum is in the right half
            } else if (nums[mid] < nums[e]) {
                e = mid; // The minimum is in the left half, including mid
            } else {
                e--; // Handle duplicates
            }
        }

        // Search in the appropriate half based on the target's value
        if (target > nums[nums.length - 1]) {
            return binarySearch(nums, 0, e, target); // Search in the left half
        } else {
            return binarySearch(nums, s, nums.length - 1, target); // Search in the right half
        }
    }

    /*-
     * Approach 3: Standard Binary Search for Rotated Sorted Array
     * 
     * Intuition:
     * - In this approach, we apply the standard binary search for a rotated sorted array.
     * - We compare the target with the current middle element and adjust the search space 
     *   based on the ordering of the elements in the array.
     * - If the array is sorted on one side, we use that property to reduce the search space.
     * 
     * Time Complexity:
     * - O(log n), where n is the size of the array, due to binary search.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space.
     * 
     * @param nums The input array of numbers.
     * @param target The value we are searching for.
     * @return The index of the target if found, otherwise -1.
     */
    public static int search3(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;

        // Apply binary search for rotated sorted array
        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            if (nums[s] <= nums[mid]) {
                if (nums[s] <= target && nums[mid] >= target) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            } else {
                if (nums[mid] <= target && nums[e] >= target) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
        }
        return -1;
    }

    /*-
     * Helper function: Binary Search
     * 
     * Intuition:
     * - Standard binary search to find a target in a sorted array.
     * 
     * Time Complexity:
     * - O(log n), where n is the size of the array.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space.
     * 
     * @param nums The input array of numbers.
     * @param s The starting index of the search range.
     * @param e The ending index of the search range.
     * @param target The value to search for.
     * @return The index of the target if found, otherwise -1.
     */
    public static int binarySearch(int[] nums, int s, int e, int target) {
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return -1;
    }
}
