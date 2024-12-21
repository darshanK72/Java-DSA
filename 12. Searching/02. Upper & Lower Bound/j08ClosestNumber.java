/*-
 * Problem Statement:
 *
 *     Given a sorted array `arr` of integers and a target integer `k`, find the closest 
 *     number to `k` in the array. If there are two numbers equally close to `k`, 
 *     return the smaller number.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), the target value.
 *
 * Output:
 *     - The closest number in the array to the target `k`.
 *
 * Example:
 *     Input:
 *     5
 *     1 3 5 7 9
 *     6
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The closest number to `6` in the array `[1, 3, 5, 7, 9]` is `5` because 
 *     the distance between `6` and `5` (1) is smaller than the distance between 
 *     `6` and `7` (1).
 */

import java.util.Scanner;

public class j08ClosestNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int target = in.nextInt(); // Input: the target value
        System.out.println(findClosest(n, target, arr)); // Output: closest number to target
        in.close();
    }

    /*-
     * Approach: Binary Search for Closest Number
     * 
     * Intuition:
     * - Since the array is sorted, we can use binary search to find the closest number efficiently.
     * - The idea is to first find the closest element that is smaller than or equal to the target (floor).
     * - Then, check the closest element that is greater than or equal to the target (ceil).
     * - The answer will be the number that has the smaller distance to the target.
     * - If both distances are equal, return the smaller of the two numbers.
     * 
     * Time Complexity:
     * - O(log n), since we are performing binary search to find the closest number.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space.
     * 
     * @param n The size of the array.
     * @param k The target value.
     * @param arr The input array of numbers.
     * @return The closest number to the target.
     */
    public static int findClosest(int n, int k, int[] arr) {
        int s = 0;
        int e = n - 1;

        // Binary search to find the closest value
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == k) {
                return arr[mid]; // Exact match found
            } else if (arr[mid] > k) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        // Now, s points to the first element greater than k, and e points to the last
        // element less than k
        int floorDist = e == -1 ? Integer.MAX_VALUE : k - arr[e];
        int ceilDist = s == n ? Integer.MAX_VALUE : arr[s] - k;

        // Return the closest number
        if (floorDist < ceilDist) {
            return arr[e]; // The floor value is closer
        } else {
            return arr[s]; // The ceil value is closer
        }
    }
}
