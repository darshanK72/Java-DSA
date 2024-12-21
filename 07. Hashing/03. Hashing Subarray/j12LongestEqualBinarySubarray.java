/**
 * Problem Statement:
 *
 *     Given an array containing binary values (0s and 1s), find the length of the longest contiguous subarray where 
 *     the number of 1s is equal to the number of 0s.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the binary array.
 *     - An array `arr` of size `n` where each element is either `0` or `1`.
 *
 * Output:
 *     - An integer representing the length of the longest subarray where the number of 1s is equal to the number of 0s.
 *
 * Example:
 *     Input:
 *     6
 *     1 0 1 0 1 0
 *
 *     Output:
 *     6
 *
 *     Explanation:
 *     The entire array itself is a subarray with an equal number of 1s and 0s. Hence, the longest subarray length is 6.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j12LongestEqualBinarySubarray {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the binary array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the binary array
        }

        // Call the solution method
        System.out.println(longestContineousBinarySubarray(arr));

        in.close();
    }

    /**
     * Approach: Optimized Approach Using HashMap and Prefix Sum
     * 
     * Intuition:
     * - The key observation here is that we can transform the problem of finding the longest subarray with an equal number of 
     *   1s and 0s into a problem of finding subarrays with a sum of 0. This is done by transforming the 0s in the array into -1s. 
     *   Thus, the problem becomes one of finding the longest subarray whose sum is 0.
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
     * Explanation:
     * - We convert each `1` in the array to `1`, and each `0` is treated as `-1`. Now, the task is to find the longest subarray 
     *   where the sum of elements is 0. 
     * - The HashMap is used to store how many times each sum has occurred. Whenever a sum is encountered again, it means there is 
     *   a subarray between the previous occurrence and the current index that has a sum of 0 (equal number of 0s and 1s).
     * 
     * @param arr The input binary array.
     * @return The length of the longest subarray with an equal number of 0s and 1s.
     */
    public static int longestContineousBinarySubarray(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // We initialize with sum 0 at index -1 to handle subarrays starting from index
                        // 0
        int ans = 0; // To store the length of the longest subarray
        int sum = 0; // Running sum which will accumulate the values (1 as 1, 0 as -1)

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {
            // Update the running sum: treat 1 as +1 and 0 as -1
            sum += arr[i] == 1 ? 1 : -1;

            // Check if the current sum has been encountered before
            // If it has, calculate the length of the subarray from the previous index where
            // the sum was seen
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum)); // Update the answer with the maximum length
            } else {
                // Otherwise, store the current sum and its index
                map.put(sum, i);
            }
        }

        return ans; // Return the maximum length of subarray
    }
}
