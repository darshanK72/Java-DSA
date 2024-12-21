/**
 * Problem Statement:
 * 
 *     Given four integer arrays `nums1`, `nums2`, `nums3`, and `nums4`, the task is to find 
 *     how many tuples (i, j, k, l) such that:
 *     - 0 <= i < nums1.length
 *     - 0 <= j < nums2.length
 *     - 0 <= k < nums3.length
 *     - 0 <= l < nums4.length
 *     and nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0.
 * 
 * Input:
 *     - Four integer arrays `nums1`, `nums2`, `nums3`, and `nums4` each of size `n` 
 *       (1 <= n <= 1000) where each element satisfies (-10^9 <= nums[i] <= 10^9).
 * 
 * Output:
 *     - An integer `count` representing the number of tuples where the sum of four 
 *       integers equals zero.
 * 
 * Example:
 *     Input:
 *     nums1 = {1, 2}
 *     nums2 = {-2, -1}
 *     nums3 = {-1, 2}
 *     nums4 = {0, 2}
 *     
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The two tuples that sum to zero are:
 *     (1, -2, -1, 2) and (2, -1, -1, 0).
 */

import java.util.HashMap;
import java.util.Scanner;

public class j03FourSumII {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int[] arr3 = new int[n];
        int[] arr4 = new int[n];

        // Read the input arrays
        for (int j = 0; j < n; j++) {
            arr1[j] = in.nextInt();
        }
        for (int j = 0; j < n; j++) {
            arr2[j] = in.nextInt();
        }
        for (int j = 0; j < n; j++) {
            arr3[j] = in.nextInt();
        }
        for (int j = 0; j < n; j++) {
            arr4[j] = in.nextInt();
        }

        // Call and print results of both methods
        System.out.println(fourSumCount(arr1, arr2, arr3, arr4));
        System.out.println(fourSumCountHashMap(arr1, arr2, arr3, arr4));

        in.close();
    }

    /**
     * Approach 1: Brute Force Solution
     * 
     * Intuition:
     * - The brute force solution uses four nested loops to iterate through all combinations 
     *   of elements from the four arrays and checks if their sum equals zero.
     * - This solution has high time complexity because it evaluates all possible combinations 
     *   in a brute-force manner.
     * 
     * Time Complexity:
     * - O(n^4), where `n` is the length of the arrays. This is because there are four nested 
     *   loops, each running `n` times.
     * 
     * Space Complexity:
     * - O(1), as the only extra space used is for the `count` variable.
     * 
     * @param nums1 The first input array.
     * @param nums2 The second input array.
     * @param nums3 The third input array.
     * @param nums4 The fourth input array.
     * @return The number of tuples where the sum of four elements is zero.
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Optimized HashMap Solution
     * 
     * Intuition:
     * - The optimized approach reduces the problem by using a HashMap to store the sums 
     *   of pairs from `nums1` and `nums2`. It then looks for the complementary sums from 
     *   `nums3` and `nums4` that complete the sum to zero.
     * - This significantly reduces the number of computations by avoiding four nested loops.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the arrays. We iterate over all pairs from 
     *   `nums1` and `nums2` to build the map, and then check for corresponding pairs in 
     *   `nums3` and `nums4`.
     * 
     * Space Complexity:
     * - O(n^2), as we store the sums of pairs from `nums1` and `nums2` in the HashMap.
     * 
     * @param nums1 The first input array.
     * @param nums2 The second input array.
     * @param nums3 The third input array.
     * @param nums4 The fourth input array.
     * @return The number of tuples where the sum of four elements is zero.
     */
    public static int fourSumCountHashMap(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        // Store all sums of pairs from nums1 and nums2 in the HashMap
        for (int a : nums1) {
            for (int b : nums2) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }

        // For each pair from nums3 and nums4, check for complementary sums in the
        // HashMap
        for (int a : nums3) {
            for (int b : nums4) {
                int target = -(a + b);
                count += map.getOrDefault(target, 0);
            }
        }
        return count;
    }
}
