/**
 * Problem Statement:
 *
 *     Given an array `arr` consisting of elements 0, 1, and 2, the task is to find the length of the longest contiguous subarray 
 *     such that the number of 0's, 1's, and 2's in the subarray are equal.
 *
 * Input:
 *     - An integer `n` representing the size of the array (1 <= n <= 10^5).
 *     - An integer array `arr` of size `n`, where each element is either 0, 1, or 2.
 *
 * Output:
 *     - An integer representing the length of the longest contiguous subarray with equal number of 0's, 1's, and 2's.
 *
 * Example:
 *     Input:
 *     6
 *     0 1 2 0 1 2
 *
 *     Output:
 *     6
 *     
 *     Explanation:
 *     The entire array [0, 1, 2, 0, 1, 2] is the longest subarray where the number of 0's, 1's, and 2's are equal.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j14LongestEqualZeroOneTwoSubarray {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n]; // Array initialization
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        System.out.println(longestEqual012(arr)); // Call the solution method
        in.close();
    }

    /**
     * Approach: Optimized Approach Using HashMap for Storing Differences
     *
     * Intuition:
     * - We need to find the longest subarray where the count of 0's, 1's, and 2's are equal. 
     * - To do this, we track the difference between the counts of 0's and 1's, and the difference between the counts of 1's and 2's. 
     * - The key idea is that if the difference between the count of 0's and 1's, and the difference between the count of 1's and 2's,
     *   are the same at two different positions in the array, then the subarray between those two positions has equal counts of 0's, 1's, and 2's.
     * 
     * - We use a HashMap to store the frequency of each unique pair `(zeros - ones, ones - twos)`. 
     * - For each index in the array, we calculate the current differences `(zeros - ones, ones - twos)`, and check if this pair has been encountered before.
     * - If it has, we calculate the length of the subarray between the previous occurrence of this pair and the current index.
     * - This approach ensures we find the longest subarray efficiently.
     * 
     * Time Complexity:
     * - O(n): We traverse the array once, and each operation with the HashMap (get and put) is O(1) on average.
     * 
     * Space Complexity:
     * - O(n): The space is used by the HashMap to store unique pairs of differences.
     * 
     * Explanation:
     * - The key to the solution is tracking the differences `(zeros - ones, ones - twos)` as we traverse the array.
     * - When we encounter the same pair of differences at two different indices, it indicates that the subarray between those indices 
     *   has an equal number of 0's, 1's, and 2's.
     * 
     * @param arr The input array consisting of 0's, 1's, and 2's.
     * @return The length of the longest subarray where the count of 0's, 1's, and 2's are equal.
     */
    public static int longestEqual012(int[] arr) {
        int zeros = 0; // Count of 0's
        int ones = 0; // Count of 1's
        int twos = 0; // Count of 2's
        int maxL = -1; // Variable to store the maximum length of valid subarrays
        HashMap<String, Integer> map = new HashMap<>(); // HashMap to store frequency of (zeros - ones, ones - twos)
                                                        // pairs
        map.put("0,0", -1); // Initialize with the base case (0, 0) to handle substrings starting from index
                            // 0

        // Traverse through the array
        for (int i = 0; i < arr.length; i++) {
            // Update counts based on the current element
            if (arr[i] == 0) {
                zeros++;
            } else if (arr[i] == 1) {
                ones++;
            } else {
                twos++;
            }

            // Calculate the current differences
            String key = (zeros - ones) + "," + (ones - twos);

            // If this key has been seen before, it means there is a valid subarray between
            // those indices
            if (map.containsKey(key)) {
                maxL = Math.max(maxL, i - map.get(key)); // Update max length if a longer subarray is found
            } else {
                map.put(key, i); // Store the current index as the first occurrence of this key
            }
        }
        return maxL; // Return the maximum length of valid subarrays found
    }
}
