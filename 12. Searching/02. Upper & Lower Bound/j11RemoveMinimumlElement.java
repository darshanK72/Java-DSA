/*-
 * Problem Statement:
 *
 *     Given an array of integers, you are required to remove the minimum number of elements
 *     such that every remaining element in the array is less than or equal to twice of any other element.
 *     In other words, we want to find the largest subset of the array such that for every pair of elements
 *     in the subset, the larger element is at most twice the smaller element. 
 *     The output should be the minimum number of elements to be removed to achieve this condition.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the minimum number of elements to be removed.
 *
 * Example:
 *     Input:
 *     5
 *     3 5 10 20 30
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The largest subset of numbers satisfying the condition is [3, 5, 10, 20].
 *     So, we need to remove the element 30. The minimum number of elements to remove is 1.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j11RemoveMinimumlElement {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        System.out.println(minRemoval(arr)); // Output: minimum number of elements to be removed
        in.close();
    }

    /*-
     * Approach: Sorting and Binary Search
     * 
     * Intuition:
     * - The key idea is to find the longest subsequence where for every pair of elements, the larger one is at most
     *   twice the smaller one. 
     * - We sort the array and for each element `arr[i]`, we use binary search to find the largest `arr[j]` such that
     *   `arr[j] <= 2 * arr[i]`. This helps us find the largest valid subset starting at `arr[i]`.
     * - We continue this for all elements and calculate the largest valid subset, then return the difference between
     *   the total number of elements and the size of the largest subset.
     * 
     * Time Complexity:
     * - Sorting the array takes `O(n log n)`.
     * - For each element in the array, the binary search takes `O(log n)`. So the overall time complexity is `O(n log n)`.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space apart from the input array.
     * 
     * @param arr The input array of integers.
     * @return The minimum number of elements to be removed.
     */
    public static int minRemoval(int[] arr) {
        Arrays.sort(arr); // Sort the array
        int maxL = Integer.MIN_VALUE; // Variable to store the size of the largest valid subset
        for (int i = 0; i < arr.length; i++) {
            int j = ceilIndex(i, arr, 2 * arr[i]); // Find the largest `arr[j]` such that `arr[j] <= 2 * arr[i]`
            maxL = Math.max(maxL, j - i + 1); // Update the maximum size of the valid subset
        }
        return arr.length - maxL; // Return the number of elements to remove
    }

    /*-
     * Helper Function: Binary Search for the ceiling index
     * 
     * Intuition:
     * - This function performs a binary search to find the index of the first element in the array that is greater
     *   than a given target. In this case, the target is `2 * arr[i]`, so we are finding the largest `arr[j]` such that
     *   `arr[j] <= 2 * arr[i]`.
     * 
     * Time Complexity:
     * - O(log n), as we are performing binary search on the sorted array.
     * 
     * Space Complexity:
     * - O(1), as the function only uses a constant amount of space.
     * 
     * @param s The starting index for the search.
     * @param arr The input array of integers.
     * @param target The target value to compare against.
     * @return The index of the largest element that is less than or equal to the target.
     */
    public static int ceilIndex(int s, int[] arr, int target) {
        int e = arr.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] > target) {
                e = mid - 1; // Target is smaller, look left
            } else {
                s = mid + 1; // Target is larger or equal, look right
            }
        }
        return e; // Return the index of the last valid element
    }
}
