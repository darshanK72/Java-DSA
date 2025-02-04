/*-
 * Problem Statement:
 * 
 *     Given a sorted array `arr[]` of distinct integers, transform the array 
 *     into a wave-like array in place. A wave-like array is defined as:
 *         arr[1] >= arr[2] <= arr[3] >= arr[4] <= arr[5].....
 * 
 *     If there are multiple solutions, return the lexicographically 
 *     smallest one.
 * 
 *     Note: The given array is initially sorted in ascending order.
 * 
 * Input:
 *     - An integer `N` (1 <= N <= 10^6), representing the size of the array.
 *     - A sorted array `arr` of size `N` with distinct elements 
 *       (-10^6 <= arr[i] <= 10^6).
 * 
 * Output:
 *     - The array modified in place to follow the wave pattern.
 * 
 * Example 1:
 *     Input:
 *         N = 5
 *         arr[] = [1, 2, 3, 4, 5]
 *     Output:
 *         2 1 4 3 5
 *     Explanation:
 *         After rearranging, the array follows wave pattern:
 *         arr[1] >= arr[2] <= arr[3] >= arr[4] <= arr[5]
 * 
 * Example 2:
 *     Input:
 *         N = 6
 *         arr[] = [2, 4, 6, 8, 10, 12]
 *     Output:
 *         4 2 8 6 12 10
 *     Explanation:
 *         After rearranging, the array follows wave pattern:
 *         arr[1] >= arr[2] <= arr[3] >= arr[4] <= arr[5] >= arr[6]
 */

import java.util.Scanner;

public class j06WaveSortLexographically {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the wave sorting function
        convertToWave(arr);

        // Print the modified array
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        in.close();
    }

    /*-
     * Approach: Swap Adjacent Elements
     * 
     * Intuition:
     * - Since the input array is already sorted in ascending order, 
     *   we can achieve the wave pattern by swapping adjacent elements.
     * - The first element should be greater than the second, 
     *   the third should be greater than the fourth, and so on.
     * - Swapping pairs ensures the lexicographically smallest wave-like array.
     * 
     * Time Complexity:
     * - O(N) because we traverse the array once, swapping adjacent elements.
     * 
     * Space Complexity:
     * - O(1) as we modify the array in place.
     * 
     * @param arr The input array.
     */
    public static void convertToWave(int[] arr) {
        for (int i = 0; i < arr.length - 1; i += 2) {
            // Swap adjacent elements to create a wave pattern
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
    }
}
