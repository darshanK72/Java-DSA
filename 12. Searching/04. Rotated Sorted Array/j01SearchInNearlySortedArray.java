/*-
 * Problem Statement:
 *
 *     Given a nearly sorted (also called "almost sorted") array of integers, 
 *     where each element is either at its correct position or one position away from 
 *     its correct position, find the index of a target element in the array.
 *     The array may contain duplicates.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `target` representing the element to search in the array.
 *
 * Output:
 *     - The index of the target element if found, otherwise return -1.
 *
 * Example:
 *     Input:
 *     5
 *     10 20 30 40 50
 *     30
 *     
 *     Output:
 *     2
 *
 *     Explanation:
 *     The element 30 is found at index 2 in the array.
 */

import java.util.Scanner;

public class j01SearchInNearlySortedArray {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int target = in.nextInt(); // Input: the target element
        System.out.println(searchInNearlySorted(arr, target)); // Call solution method
        in.close();
    }

    /*-
     * Approach 1: Linear Search (Brute Force Approach)
     * 
     * Intuition:
     * - A simple brute force approach can be used to search for the target element.
     * - We can iterate through the array and check if each element matches the target.
     * - Since the array is nearly sorted, elements may be shifted by at most 1 position.
     * - This approach is guaranteed to work, but it is inefficient for large arrays.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array.
     * - We may need to check each element of the array to find the target.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param arr The input array of numbers.
     * @param target The target element to search for.
     * @return The index of the target element, or -1 if not found.
     */
    public static int bruteForceSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Target found
            }
        }
        return -1; // Target not found
    }

    /*-
     * Approach 2: Optimized Search in Nearly Sorted Array (Binary Search Approach)
     * 
     * Intuition:
     * - The nearly sorted array allows us to perform a binary search with some adjustments.
     * - In each step, we check if the middle element is equal to the target.
     * - If not, we check the adjacent elements (left and right of the middle) to see if the target 
     *   exists there due to the "nearly sorted" nature of the array.
     * - This reduces the search space and significantly improves the performance compared to brute force.
     * 
     * Time Complexity:
     * - O(log n), where n is the size of the array.
     * - Each iteration of the binary search cuts the search space in half.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space.
     * 
     * @param arr The input array of numbers.
     * @param target The target element to search for.
     * @return The index of the target element, or -1 if not found.
     */
    public static int searchInNearlySorted(int[] arr, int target) {
        int s = 0;
        int e = arr.length - 1;

        // Binary search with adjustments for nearly sorted array
        while (s <= e) {
            int mid = s + (e - s) / 2;

            // If the mid element is the target
            if (arr[mid] == target) {
                return mid;
            }
            // Check if the target is on the left of mid
            else if (mid - 1 >= s && arr[mid - 1] == target) {
                return mid - 1;
            }
            // Check if the target is on the right of mid
            else if (mid + 1 <= e && arr[mid + 1] == target) {
                return mid + 1;
            }

            // Standard binary search narrowing down the search space
            if (arr[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return -1; // Target not found
    }
}
