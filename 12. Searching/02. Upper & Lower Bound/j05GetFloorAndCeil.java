/*-
 * Problem Statement:
 * 
 *     Given a sorted array of integers `a[]` and a target value `x`, we need to find:
 *     - **Floor**: The largest number in the array that is less than or equal to `x`.
 *     - **Ceiling**: The smallest number in the array that is greater than or equal to `x`.
 * 
 *     If no such values are present, return `-1` for the respective value (floor or ceiling).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `target` (1 <= target <= 10^5), representing the target value.
 * 
 * Output:
 *     - An array of two integers: the first element is the floor and the second element is the ceiling.
 *       If either is not found, return -1 for that value.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 8 10 12
 *     5
 *     
 *     Output:
 *     Floor: 2
 *     Ceil: 8
 * 
 * Explanation:
 *     The floor of 5 is 2 (largest element less than or equal to 5) and the ceil of 5 is 8 (smallest element greater than or equal to 5).
 */

import java.util.Scanner;

public class j05GetFloorAndCeil {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        int[] out = getFloorAndCeil(arr, n, target);
        System.out.println("Floor : " + out[0]);
        System.out.println("Ceil : " + out[1]);
        in.close();
    }

    /*-
     * Approach:
     * 
     * - We will use binary search to efficiently find the floor and ceiling.
     * - The floor is the largest element that is less than or equal to the target.
     * - The ceiling is the smallest element that is greater than or equal to the target.
     * 
     * Time Complexity:
     * - O(log n), as we are performing binary search to find the floor and ceiling.
     * 
     * Space Complexity:
     * - O(1), no additional space is used except for a few variables.
     * 
     * @param a The input array of numbers.
     * @param n The size of the array.
     * @param x The target value.
     * @return An array with the floor and ceil values.
     */
    public static int[] getFloorAndCeil(int[] a, int n, int x) {
        int s = 0;
        int e = n - 1;
        int floor = -1;
        int ceil = -1;

        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (a[mid] == x) {
                return new int[] { x, x }; // Return x if it's found.
            }

            if (a[mid] < x) {
                floor = a[mid]; // Update floor as a[mid] is less than x.
                s = mid + 1; // Search in the right half.
            } else {
                ceil = a[mid]; // Update ceil as a[mid] is greater than x.
                e = mid - 1; // Search in the left half.
            }
        }

        return new int[] { floor, ceil }; // Return the floor and ceil values found.
    }
}
