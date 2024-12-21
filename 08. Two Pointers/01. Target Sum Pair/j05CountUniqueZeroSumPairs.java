/**
 * Problem Statement:
 * 
 *     Given an array of integers, find all unique pairs in the array whose sum is zero.
 *     Each pair should be returned only once, and the pairs should be sorted in ascending order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A list of lists where each inner list contains two integers that sum to zero.
 * 
 * Example:
 *     Input:
 *     5
 *     -1 0 1 2 -1
 *     Output:
 *     [[-1, 1], [-1, 1]]
 * 
 *     Explanation:
 *     The unique pairs whose sum is zero are [-1, 1].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j05CountUniqueZeroSumPairs {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution method
        System.out.println(getPairs(arr));

        in.close();
    }

    /**
     * Approach: Two Pointer Technique
     * 
     * Intuition:
     * - The problem requires us to find pairs in the array whose sum is zero.
     * - We can efficiently find these pairs using the two-pointer technique after sorting the array.
     * - The idea is to start with two pointers: one at the beginning and the other at the end.
     * - We check the sum of the two elements, and based on the sum, we move the pointers inward to try to find a zero sum.
     * - Sorting helps because it allows us to skip duplicate elements efficiently and prevents checking the same pair multiple times.
     * 
     * Time Complexity:
     * - O(n log n) for sorting the array.
     * - O(n) for iterating through the array with the two pointers.
     * - Overall time complexity is O(n log n).
     * 
     * Space Complexity:
     * - O(n) for storing the output list of pairs.
     * 
     * @param arr The input array of integers.
     * @return A list of lists containing pairs whose sum is zero.
     */
    public static ArrayList<ArrayList<Integer>> getPairs(int[] arr) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        Arrays.sort(arr); // Sort the array to use two pointers technique

        int s = 0; // Start pointer
        int e = arr.length - 1; // End pointer

        // Iterate with two pointers
        while (s < e) {
            int sum = arr[s] + arr[e];

            // If sum is zero, add the pair to the result list
            if (sum == 0) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(arr[s]);
                temp.add(arr[e]);
                out.add(temp);

                // Skip duplicate elements
                while (s < e && arr[s] == arr[s + 1])
                    s++;
                while (s < e && arr[e] == arr[e - 1])
                    e--;

                // Move pointers inward
                s++;
                e--;
            } else if (sum > 0) {
                e--; // Reduce sum by moving the end pointer left
            } else {
                s++; // Increase sum by moving the start pointer right
            }
        }

        return out; // Return the list of unique pairs
    }
}
