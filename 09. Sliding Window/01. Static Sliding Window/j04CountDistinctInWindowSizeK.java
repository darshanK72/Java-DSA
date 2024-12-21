/**
 * Problem Statement:
 *
 *     Given an array of integers `A[]` and an integer `k`, the task is to count the number of distinct elements in every subarray of size `k` in the array.
 *
 * Input:
 *     - An integer `n` representing the size of the array `A[]`.
 *     - An integer array `A[]` of size `n` containing the elements of the array.
 *     - An integer `k` representing the size of the subarray.
 *
 * Output:
 *     - A list of integers representing the number of distinct elements in each subarray of size `k`.
 *
 * Example:
 *     Input:
 *     7
 *     1 2 1 3 2 3 3
 *     3
 *     Output:
 *     [3, 2, 3, 2, 2]
 *
 *     Explanation:
 *     The subarrays of size 3 are:
 *     - [1, 2, 1] -> 2 distinct elements: 1, 2
 *     - [2, 1, 3] -> 3 distinct elements: 2, 1, 3
 *     - [1, 3, 2] -> 3 distinct elements: 1, 3, 2
 *     - [3, 2, 3] -> 2 distinct elements: 3, 2
 *     - [2, 3, 3] -> 2 distinct elements: 2, 3
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j04CountDistinctInWindowSizeK {
    public static void main(String args[]) {
        // Reading the input values
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Elements of the array
        }
        int k = in.nextInt(); // Size of the subarray

        // Call the function to count distinct elements in every subarray of size k
        System.out.println(countDistinct(nums, n, k));
        in.close();
    }

    /**
     * Solution to count the distinct elements in each subarray of size `k` in the array.
     *
     * Intuition:
     * - We use a sliding window of size `k`. We maintain a frequency map of the elements in the window.
     * - For each new window, we remove the element that is sliding out and add the element that is sliding in.
     * - The size of the frequency map will give the count of distinct elements in the current window.
     *
     * Time Complexity: O(N), where `N` is the size of the array.
     * Space Complexity: O(K) for the frequency map.
     *
     * @param A The input array.
     * @param n The size of the array.
     * @param k The size of the subarray.
     * @return The list of counts of distinct elements in each subarray.
     */
    public static ArrayList<Integer> countDistinct(int A[], int n, int k) {
        // Frequency map to store counts of elements in the current window
        HashMap<Integer, Integer> map = new HashMap<>();

        // Initialize the map with the first window (first `k` elements)
        for (int i = 0; i < k; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }

        // Result array to store the count of distinct elements for each window
        ArrayList<Integer> out = new ArrayList<>();
        out.add(map.size()); // Add the distinct count for the first window

        // Slide the window and update the map
        for (int i = k; i < n; i++) {
            // Remove the element that is sliding out of the window
            int val = map.get(A[i - k]);
            if (val == 1)
                map.remove(A[i - k]);
            else
                map.put(A[i - k], val - 1);

            // Add the new element that is sliding into the window
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);

            // Add the count of distinct elements for the current window
            out.add(map.size());
        }

        // Return the result list
        return out;
    }
}
