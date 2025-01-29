/**
 * Problem Statement:
 *
 *     Given an array `nums` consisting of 0, 1, and 2, sort the array in-place such that all 
 *     0's are followed by all 1's, and all 1's are followed by all 2's.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums[]` of size `n` where each element is either 0, 1, or 2.
 *
 * Output:
 *     - The array `nums[]` sorted in-place in non-decreasing order.
 *
 * Example:
 *     Input:
 *     6
 *     2 0 2 1 1 0
 *     Output:
 *     0 0 1 1 2 2
 *
 *     Explanation:
 *     The sorted array contains all 0's followed by all 1's and then all 2's.
 */

import java.util.Scanner;

public class j04SortColors {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the efficient sorting method
        sortColorsEfficient(arr);

        // Output the sorted array
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        in.close();
    }

    /**
     * Approach 1: Counting Sort (O(n))
     *
     * Intuition:
     * - We count the occurrences of 0's, 1's, and 2's in the array.
     * - Then, based on the counts, we place the respective values at the correct positions.
     * - This approach is simple and effective when we know the range of possible values in the array.
     *
     * Time Complexity:
     * - O(n), where n is the size of the array, because we traverse the array twice (once for counting 
     *   and once for placing the values).
     *
     * Space Complexity:
     * - O(1), as we use a constant amount of space for storing the counts.
     *
     * @param nums The input array of integers.
     */
    public static void sortColors(int[] nums) {
        int cZero = 0; // Count of 0's
        int cOne = 0; // Count of 1's
        int cTwo = 0; // Count of 2's
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                cZero++;
            } else if (nums[i] == 1) {
                cOne++;
            } else {
                cTwo++;
            }
        }

        int k = 0;
        while (cZero > 0) {
            nums[k] = 0; // Place all 0's
            k++;
            cZero--;
        }

        while (cOne > 0) {
            nums[k] = 1; // Place all 1's
            k++;
            cOne--;
        }

        while (cTwo > 0) {
            nums[k] = 2; // Place all 2's
            k++;
            cTwo--;
        }
    }

    /**
     * Approach 2: Dutch National Flag Algorithm (O(n))
     *
     * Intuition:
     * - This approach uses three pointers: `s` for the start, `m` for the middle, and `e` for the end.
     * - The algorithm processes the array in one pass, efficiently moving the 0's, 1's, and 2's to their 
     *   correct positions.
     * - The `m` pointer is used to scan through the array, while `s` and `e` are used to swap elements 
     *   around the boundaries of 0's and 2's.
     *
     * Time Complexity:
     * - O(n), where n is the size of the array, because we traverse the array once with the `m` pointer.
     *
     * Space Complexity:
     * - O(1), as we use a constant amount of space for the pointers and swapping elements in place.
     *
     * @param nums The input array of integers.
     */
    public static void sortColorsEfficient(int[] nums) {
        int s = 0; // Pointer for the start (0's region)
        int m = 0; // Pointer for the middle (to scan the array)
        int e = nums.length - 1; // Pointer for the end (2's region)

        while (m <= e) {
            if (nums[m] == 0) {
                swap(nums, s, m); // Move 0 to the start
                s++;
                m++;
            } else if (nums[m] == 2) {
                swap(nums, e, m); // Move 2 to the end
                e--;
            } else {
                m++; // If it's 1, just move the middle pointer
            }
        }
    }

    /**
     * Helper Method: Swap Elements
     *
     * Intuition:
     * - This method swaps two elements in the array.
     * - It is used to move the 0's and 2's to their correct regions in the Dutch National Flag Algorithm.
     *
     * Time Complexity:
     * - O(1), as it's a constant time operation.
     *
     * Space Complexity:
     * - O(1), as it uses a constant amount of space for swapping.
     *
     * @param nums The input array of integers.
     * @param s The index of the first element to swap.
     * @param e The index of the second element to swap.
     */
    public static void swap(int[] nums, int s, int e) {
        int temp = nums[s];
        nums[s] = nums[e];
        nums[e] = temp;
    }
}
