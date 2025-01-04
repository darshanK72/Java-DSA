/**
 * Problem Statement:
 * 
 *     You are given an integer `n`, the size of the array, and an array of `n` integers. 
 *     You are also given an integer `k`, and your task is to find the index of `k` in the array 
 *     using two different binary search algorithms:
 *     1. **binarySearch**: It assumes the array is sorted in ascending order.
 *     2. **binarySearchAnyOrder**: It can handle arrays that may be sorted either in ascending or descending order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k`, the target element to be searched in the array.
 * 
 * Output:
 *     - The index of `k` in the array. If `k` is not found, return `-1`.
 * 
 * Example:
 *     Input:
 *     6
 *     1 3 5 7 9 11
 *     5
 *     Output:
 *     2
 *     
 *     Explanation:
 *     For the `binarySearch`, the number `5` is found at index `2`.
 *     For the `binarySearchAnyOrder`, if the array were sorted in descending order, 
 *     it would find `5` at index `2` as well.
 */

import java.util.Scanner;

public class j01BinarySearch {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the target value to search for
        System.out.println(binarySearch(arr, k)); // Output from binary search assuming ascending order
        System.out.println(binarySearchAnyOrder(arr, k)); // Output from binary search for any order
        in.close();
    }

    /**
     * Approach 1: Binary Search (Ascending Order)
     * 
     * Intuition:
     * - The binary search assumes that the array is sorted in ascending order.
     * - We repeatedly divide the search space in half. At each step, compare the middle element 
     *   to the target. If the middle element matches, return the index. If the target is smaller,
     *   adjust the search space to the left half; otherwise, adjust to the right half.
     * 
     * Time Complexity:
     * - O(log n), since we halve the search space at each step.
     * 
     * Space Complexity:
     * - O(1), since we only use a few extra variables.
     * 
     * @param nums The input array of numbers.
     * @param target The target value to search for.
     * @return The index of the target value if found, otherwise -1.
     */
    public static int binarySearch(int[] nums, int target) {
        int s = 0; // Start index
        int e = nums.length - 1; // End index
        while (s <= e) {
            int mid = (s + e) / 2; // Calculate mid index
            if (nums[mid] == target) {
                return mid; // Return index if target is found
            } else if (nums[mid] > target) {
                e = mid - 1; // Adjust search space to the left
            } else {
                s = mid + 1; // Adjust search space to the right
            }
        }
        return -1; // Return -1 if target is not found
    }

    /**
     * Approach 2: Binary Search for Any Order (Ascending or Descending)
     * 
     * Intuition:
     * - This approach is an extension of binary search to handle both ascending and descending sorted arrays.
     * - We first check the order of the array by comparing the first and last elements. 
     *   If the first element is smaller than the last, the array is ascending. 
     *   Otherwise, it's descending. Then, the logic adjusts based on the array order.
     * 
     * Time Complexity:
     * - O(log n), since we still halve the search space at each step.
     * 
     * Space Complexity:
     * - O(1), as the space used is constant.
     * 
     * @param nums The input array of numbers.
     * @param target The target value to search for.
     * @return The index of the target value if found, otherwise -1.
     */
    public static int binarySearchAnyOrder(int[] nums, int target) {
        int s = 0; // Start index
        int e = nums.length - 1; // End index
        boolean isAscending = nums[s] < nums[e]; // Check if array is in ascending order
        while (s <= e) {
            int mid = (s + e) / 2; // Calculate mid index
            if (nums[mid] == target) {
                return mid; // Return index if target is found
            }
            if (isAscending) {
                if (nums[mid] > target) {
                    e = mid - 1; // Adjust search space to the left in ascending array
                } else {
                    s = mid + 1; // Adjust search space to the right in ascending array
                }
            } else {
                if (nums[mid] > target) {
                    s = mid + 1; // Adjust search space to the right in descending array
                } else {
                    e = mid - 1; // Adjust search space to the left in descending array
                }
            }
        }
        return -1; // Return -1 if target is not found
    }
}
