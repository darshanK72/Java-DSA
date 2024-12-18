/**
 * Problem Statement:
 *     Given an integer array `arr` of size `n`, determine if the array can be made non-decreasing 
 *     by modifying at most one element. A non-decreasing array means `arr[i] <= arr[i+1]` for all `i`.
 *
 * Input:
 *     - An integer array `arr` where (1 <= arr.length <= 10^4) and (-10^5 <= arr[i] <= 10^5).
 *
 * Output:
 *     - A boolean value `true` if the array can be made non-decreasing by modifying at most one element,
 *       otherwise return `false`.
 *
 * Example:
 *     Input:
 *         arr = [4, 2, 3]
 *     Output:
 *         true
 *
 *     Explanation:
 *         You can modify the first `4` to `2` to get the array [2, 2, 3], which is non-decreasing.
 *
 *     Input:
 *         arr = [4, 2, 1]
 *     Output:
 *         false
 *
 *     Explanation:
 *         No single modification can make the array non-decreasing.
 */

import java.util.*;

public class j12NonDecreasingArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt(); // Input: array elements
        }

        System.out.printf("Approach: %b\n", checkNonDecreasing(arr));
        System.out.printf("Optimized Solution: %b\n", checkNonDecreasingEfficient(arr));

        sc.close();
    }

    /**
     * Approach:
     *     This approach attempts to fix any violation of the non-decreasing order by modifying one element.
     *     - It iterates through the array and checks if `arr[i] > arr[i+1]`.
     *     - If such a pair is found, two possible modifications are considered:
     *         1. Modify `arr[i]` to `arr[i+1]`
     *         2. Modify `arr[i+1]` to `arr[i]`
     *     - After each modification, it checks whether the array is sorted.
     * 
     * Intuition:
     *     The goal is to ensure the array remains non-decreasing with at most one modification. By testing
     *     both possibilities (modifying the current or next element), we can determine if a solution exists.
     *
     * Time Complexity:
     *     O(n^2) in the worst case because of repeated calls to the `isSorted` function.
     *
     * Space Complexity:
     *     O(1), as no additional data structures are used.
     *
     * @param arr The input array.
     * @return true if the array can be made non-decreasing; false otherwise.
     */
    public static boolean checkNonDecreasing(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int current = arr[i];
            int next = arr[i + 1];
            if (current > next) {
                // Test modifying arr[i]
                arr[i] = next;
                if (isSorted(arr)) {
                    return true;
                }
                // Revert and test modifying arr[i+1]
                arr[i] = current;
                arr[i + 1] = current;
                return isSorted(arr);
            }
        }
        return true;
    }

    /**
     * Helper Function: Check if the array is sorted in non-decreasing order.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums The input array.
     * @return true if the array is sorted; false otherwise.
     */
    private static boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Optimized Solution:
     *     This approach eliminates the need for repeated calls to the `isSorted` function.
     *     - It keeps track of the number of modifications needed to maintain the non-decreasing order.
     *     - If more than one modification is required, return `false`.
     *     - For any violation `arr[i] > arr[i+1]`, decide which element to adjust:
     *         1. If `arr[i-1] <= arr[i+1]` (safe to reduce `arr[i]`), modify `arr[i]`.
     *         2. Otherwise, increase `arr[i+1]` to match `arr[i]`.
     * 
     * Intuition:
     *     We only need to consider at most one violation of the non-decreasing condition. By choosing
     *     the optimal modification (adjusting `arr[i]` or `arr[i+1]`), we can solve the problem efficiently.
     *
     * Time Complexity:
     *     O(n), as we traverse the array only once.
     *
     * Space Complexity:
     *     O(1), as no additional data structures are used.
     *
     * @param arr The input array.
     * @return true if the array can be made non-decreasing; false otherwise.
     */
    public static boolean checkNonDecreasingEfficient(int[] arr) {
        int count = 0; // Track the number of modifications
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) { // Violation detected
                if (count > 0) {
                    return false; // More than one modification needed
                }
                count++;

                // Adjust arr[i] or arr[i+1] depending on the previous element
                if (i == 0 || arr[i - 1] <= arr[i + 1]) {
                    arr[i] = arr[i + 1];
                } else {
                    arr[i + 1] = arr[i];
                }
            }
        }
        return true;
    }
}
