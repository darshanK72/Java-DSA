/*-
 * Problem Statement:
 * 
 *     Given an arithmetic progression (AP) sequence of integers with exactly one missing element, find the missing element.
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` representing the arithmetic progression, with exactly one missing element.
 *       (Each element satisfies |arr[i]| <= 10^9).
 * 
 * Output:
 *     - An integer representing the missing element in the AP sequence.
 * 
 * Example:
 *     Input:
 *     5
 *     2 4 8 10 12
 * 
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The given array represents an AP with a common difference of 2, but the element `6` is missing between `4` and `8`.
 */

import java.util.Scanner;

public class j07MissingElementInAP {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the method to find the missing element
        System.out.println("Missing element in the AP sequence: " + findMissing(arr));

        in.close();
    }

    /*-
     * Approach: Binary Search to Find the Missing Element in AP
     * 
     * Intuition:
     * - The difference between consecutive elements in an AP sequence is constant.
     * - If we divide the array into two halves, one of the halves will be "incomplete"
     *   because it contains the missing element.
     * - Using binary search, we can repeatedly halve the array to narrow down the missing element.
     * 
     * Steps:
     * 1. Calculate the common difference `d` using `(arr[n-1] - arr[0]) / n`.
     * 2. Perform binary search:
     *    - If the difference between `arr[mid + 1]` and `arr[mid]` is not equal to `d`, 
     *      the missing element is `arr[mid] + d`.
     *    - If `arr[mid] - arr[0] == mid * d`, the missing element is in the right half; move `s` to `mid + 1`.
     *    - Otherwise, it's in the left half; move `e` to `mid - 1`.
     * 3. Return the missing element when identified.
     * 
     * Time Complexity:
     * - O(log n), as we use binary search to narrow down the search space.
     * 
     * Space Complexity:
     * - O(1), as we use only a constant amount of additional space.
     * 
     * @param arr The input array representing the AP sequence.
     * @return The missing element in the AP sequence.
     */
    public static int findMissing(int[] arr) {
        int n = arr.length; // Length of the array
        int s = 0; // Start of the search range
        int e = n - 1; // End of the search range
        int d = (arr[n - 1] - arr[0]) / n; // Common difference of the AP sequence

        // Perform binary search
        while (s <= e) {
            int mid = s + (e - s) / 2; // Midpoint of the current range

            // Check if the missing element is after `mid`
            if (arr[mid + 1] - arr[mid] != d) {
                return arr[mid] + d;
            }

            // Check if the missing element is in the left or right half
            if (arr[mid] - arr[0] == mid * d) {
                s = mid + 1; // Missing element is in the right half
            } else {
                e = mid - 1; // Missing element is in the left half
            }
        }

        return -1; // Should never reach here if input is valid
    }
}
