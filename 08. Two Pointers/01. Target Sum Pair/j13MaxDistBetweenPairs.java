/**
 * Problem Statement:
 * 
 *     Given two arrays of integers `nums1` and `nums2`, the task is to find the 
 *     maximum distance `j - i` such that `nums1[i] <= nums2[j]` and `i < j`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of both arrays.
 *     - An array `nums1` of size `n` where each element satisfies (1 <= nums1[i] <= 10^5).
 *     - An array `nums2` of size `n` where each element satisfies (1 <= nums2[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the maximum distance `j - i` such that `nums1[i] <= nums2[j]`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 3 5 2 8
 *     3 6 7 1 2
 * 
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The maximum distance is between `nums1[1]` (3) and `nums2[4]` (2), so the answer is 3.
 */

import java.util.Scanner;

public class j13MaxDistBetweenPairs {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the arrays
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt(); // Input: elements of the first array
        }
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextInt(); // Input: elements of the second array
        }
        System.out.println(maxDistance(arr1, arr2)); // Output: brute force solution
        System.out.println(maxDistanceEfficient(arr1, arr2)); // Output: efficient solution
        in.close();
    }

    /**
     * Approach 1: Brute-force approach.
     * 
     * Intuition:
     * - We use two nested loops to compare each element in `nums1` with every 
     *   element in `nums2`. If `nums1[i] <= nums2[j]`, we calculate the distance `j - i`.
     * - We keep track of the maximum distance encountered.
     * 
     * Time Complexity:
     * - O(n^2), where n is the size of the arrays. We are checking all pairs of indices.
     * 
     * Space Complexity:
     * - O(1), we are using only a few variables to track the maximum distance.
     * 
     * @param nums1 The first input array.
     * @param nums2 The second input array.
     * @return The maximum distance between valid pairs.
     */
    public static int maxDistance(int[] nums1, int[] nums2) {
        int maxDist = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i; j < nums2.length; j++) {
                if (nums1[i] <= nums2[j]) {
                    maxDist = Math.max(maxDist, j - i);
                }
            }
        }
        return maxDist;
    }

    /**
     * Approach 2: Efficient approach using a two-pointer technique.
     * 
     * Intuition:
     * - By sorting the arrays and using two pointers (`i` and `j`), we can efficiently find
     *   the maximum distance between `i` and `j` where `nums1[i] <= nums2[j]`.
     * - The idea is to move `j` forward whenever we find a valid pair, and move `i` forward
     *   when we can't find a valid pair.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the arrays. This approach scans through both arrays linearly.
     * 
     * Space Complexity:
     * - O(1), as we are only using two pointers and a few additional variables.
     * 
     * @param nums1 The first input array.
     * @param nums2 The second input array.
     * @return The maximum distance between valid pairs.
     */
    public static int maxDistanceEfficient(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int maxDist = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++; // Move `j` forward to find a larger valid `nums2[j]`
            } else {
                i++; // Move `i` forward to find a larger `nums1[i]`
            }
        }
        return maxDist;
    }
}
