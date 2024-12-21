/*-
 * Problem Statement:
 * 
 *     You are given a rotated sorted array `arr`. The array is initially sorted in ascending order 
 *     but is then rotated at an unknown pivot. The task is to find the index of the minimum element 
 *     in the array, which corresponds to the number of rotations.
 * 
 *     The array can contain distinct or duplicate elements.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - A list `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the number of rotations (index of the smallest element).
 * 
 * Example:
 *     Input:
 *     5
 *     6 7 9 15 19
 *     Output:
 *     0
 *     Explanation: The array is not rotated.
 *     
 *     Input:
 *     5
 *     15 19 6 7 9
 *     Output:
 *     2
 *     Explanation: The array has been rotated 2 times.
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j06CountRotations {

    public static void main(String args[]) {
        // Reading the input from the user
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // The size of the array
        ArrayList<Integer> arr = new ArrayList<>();

        // Reading the elements of the array
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt()); // Input each element of the array
        }

        // Call the solution methods for unique and duplicate cases
        System.out.println(findKRotationUnique(arr));
        System.out.println(findKRotationDuplicates(arr));
        in.close();
    }

    /*-
     * Approach 1: Binary Search for Unique Elements
     * 
     * Intuition:
     * - The problem boils down to finding the index where the rotation has occurred. In a rotated
     *   sorted array, the minimum element will always be the point where the array has been rotated.
     * - Binary search is applied to find this point efficiently.
     * - We check the middle element (`mid`). If `arr[mid]` is greater than `arr[e]`, it means the 
     *   minimum element lies in the right half, otherwise it lies in the left half.
     * - This is a classical approach where we only consider distinct elements in the array.
     * 
     * Time Complexity:
     * - O(log n), as we perform binary search, halving the search space at each step.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space.
     * 
     * @param arr The input list of numbers.
     * @return The number of rotations or the index of the minimum element.
     */
    public static int findKRotationUnique(List<Integer> arr) {
        int s = 0;
        int e = arr.size() - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;

            // Check which half is sorted
            if (arr.get(mid) > arr.get(e)) {
                // The minimum element is in the right half
                s = mid + 1;
            } else {
                // The minimum element is in the left half, including mid
                e = mid;
            }
        }
        return s; // The index of the minimum element is the number of rotations
    }

    /*-
     * Approach 2: Binary Search with Duplicates
     * 
     * Intuition:
     * - When duplicates are present, the binary search cannot decide which side to discard 
     *   based on comparisons between `arr[mid]` and `arr[e]`. If they are equal, we reduce the search space 
     *   by decrementing `e` instead of directly shifting `s` or `e`.
     * - The logic remains similar to Approach 1, but with additional handling for duplicate elements.
     * 
     * Time Complexity:
     * - O(n) in the worst case, when duplicates are present and we might need to reduce the search space one element at a time.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space.
     * 
     * @param arr The input list of numbers.
     * @return The number of rotations or the index of the minimum element.
     */
    public static int findKRotationDuplicates(List<Integer> arr) {
        int s = 0;
        int e = arr.size() - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;

            // Check which half is sorted
            if (arr.get(mid) > arr.get(e)) {
                // The minimum element is in the right half
                s = mid + 1;
            } else if (arr.get(mid) < arr.get(e)) {
                // The minimum element is in the left half, including mid
                e = mid;
            } else {
                // If elements are equal, we reduce the search space one element at a time
                e--;
            }
        }
        return s; // The index of the minimum element is the number of rotations
    }
}
