/**
 * Problem Statement:
 *
 *     Given an array of binary values (0s and 1s), count the number of contiguous subarrays where the number of 1s
 *     is equal to the number of 0s. 
 *     A subarray is defined as a contiguous part of the array.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the binary array.
 *     - An array `arr` of size `n` where each element is either `0` or `1`.
 *
 * Output:
 *     - An integer representing the number of subarrays where the number of 1s is equal to the number of 0s.
 *
 * Example:
 *     Input:
 *     6
 *     1 0 1 0 1 0
 *
 *     Output:
 *     9
 *
 *     Explanation:
 *     The subarrays with equal numbers of 0s and 1s are:
 *     [1, 0], [0, 1], [1, 0], [0, 1], [1, 0], [0, 1], [1, 0, 1, 0], [0, 1, 0, 1], [1, 0, 1, 0].
 */

import java.util.HashMap;
import java.util.Scanner;

public class j11CountEqualBinarySubarrays {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the binary array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the binary array
        }

        // Call the solution method
        System.out.println(countSubarrWithEqualZeroAndOne(arr, n));

        in.close();
    }

    /**
     * Approach: Optimized Approach Using HashMap and Prefix Sum
     * 
     * Intuition:
     * - The key observation here is that we can transform the problem of counting subarrays with an equal number of 0s and 1s 
     *   into a problem of finding subarrays with a sum of 0. This is done by transforming the 0s in the array into -1s. 
     *   Thus, the problem becomes one of finding subarrays whose sum is 0.
     * - We maintain a running sum as we iterate through the array. Each time the sum reaches a particular value, we check how many 
     *   times this sum has occurred before using a HashMap. If the sum has occurred before, it means there is a subarray (from the 
     *   previous occurrence to the current index) whose sum is 0, i.e., the subarray has an equal number of 0s and 1s.
     * 
     * Time Complexity:
     * - O(n): We iterate through the array once, and each operation with the HashMap (get and put) is O(1) on average.
     * 
     * Space Complexity:
     * - O(n): We store the cumulative sum occurrences in the HashMap, so the space complexity is proportional to the number of unique sums.
     * 
     * Explanation of Key Step:
     * - We convert each `1` in the array to `1`, and each `0` is treated as `-1`. Now, the task is to find subarrays where the sum 
     *   of the elements is 0. 
     * - The HashMap is used to store how many times each sum has occurred. Whenever a sum is encountered again, it means there is 
     *   a subarray between the previous occurrence and the current index that has a sum of 0, so we increase the count.
     * 
     * @param arr The input binary array.
     * @param n The size of the array.
     * @return The number of subarrays with an equal number of 0s and 1s.
     */
    public static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0; // Initialize the count of subarrays with equal 0s and 1s
        int sum = 0; // Initialize the cumulative sum

        // Put 0 in the map with count 1 to handle the case where the sum itself is 0 at
        // some point
        map.put(0, 1);

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {
            // Update the cumulative sum: treat 1 as +1 and 0 as -1
            sum += arr[i] == 1 ? 1 : -1;

            // If the current sum has been seen before, it means there are subarrays between
            // the previous occurrences
            // and the current index whose sum is 0 (equal number of 0s and 1s)
            count += map.getOrDefault(sum, 0);

            // Store the current sum in the map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
