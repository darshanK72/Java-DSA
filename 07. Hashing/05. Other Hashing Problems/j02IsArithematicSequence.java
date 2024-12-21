/**
 * Problem Statement:
 * 
 *     Given an array `arr[]` of integers, the task is to determine if the array can be rearranged to form an 
 *     arithmetic sequence. An arithmetic sequence is a sequence of numbers in which the difference between any 
 *     two consecutive elements is constant.
 *     Return `true` if the array can be rearranged into an arithmetic sequence, otherwise return `false`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the size of the array `arr`.
 *     - An array `arr[]` of integers, where each element satisfies (-10^6 <= arr[i] <= 10^6).
 * 
 * Output:
 *     - A boolean value: `true` if the array can be rearranged into an arithmetic sequence, otherwise `false`.
 * 
 * Example:
 *     Input:
 *         5
 *         1 5 3 7 9
 *     Output:
 *         true
 * 
 *     Explanation:
 *         The array can be rearranged as [1, 3, 5, 7, 9], which forms an arithmetic sequence with a common 
 *         difference of 2.
 */

import java.util.HashSet;
import java.util.Scanner;

public class j02IsArithematicSequence {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Output the result
        System.out.println(isArithmaticSequence(arr));
        in.close();
    }

    /**
     * Approach: Using HashSet to Check Arithmetic Sequence
     * 
     * Intuition:
     * - The key observation is that an arithmetic sequence has a constant difference between consecutive terms.
     * - To determine if the array can form such a sequence, first find the minimum and maximum values of the array.
     * - The difference `d` between consecutive terms in the arithmetic sequence can be computed as `(max - min) / (n - 1)`.
     * - If the difference is not an integer, the sequence cannot form an arithmetic progression.
     * - After computing `d`, we need to check if the array contains all the terms of the form `min + i * d` for `i` 
     *   from `0` to `n-1`.
     * - We use a `HashSet` to efficiently check if each term exists in the array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array. We iterate over the array to compute the min, max, and populate 
     *   the set, which takes linear time.
     * 
     * Space Complexity:
     * - O(n), for storing the elements of the array in the HashSet.
     * 
     * @param arr The input array of integers.
     * @return `true` if the array can form an arithmetic sequence, otherwise `false`.
     */
    public static boolean isArithmaticSequence(int[] arr) {
        // If the array has 1 or fewer elements, it's trivially an arithmetic sequence
        if (arr.length <= 1)
            return true;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();

        // Find the max, min, and populate the set with array elements
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            set.add(arr[i]);
        }

        // Calculate the common difference of the arithmetic sequence
        int d = (max - min) / (arr.length - 1);

        // Check if the array can form an arithmetic sequence by verifying all terms
        for (int i = 0; i < arr.length; i++) {
            int ele = min + i * d;
            if (!set.contains(ele)) {
                return false; // If any term is missing, it's not an arithmetic sequence
            }
        }

        return true; // All terms are present, it's a valid arithmetic sequence
    }
}
