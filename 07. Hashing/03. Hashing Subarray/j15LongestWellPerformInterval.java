/**
 * Problem Statement:
 *
 *     Given an array `hours` of integers representing the number of hours worked each day, the task is to find the length of the
 *     longest interval such that the number of days where hours worked are greater than 8 is strictly greater than the number of
 *     days where hours worked are less than or equal to 8.
 *
 * Input:
 *     - An integer `n` representing the number of days (1 <= n <= 10^5).
 *     - An integer array `hours` of size `n`, where each element `hours[i]` is the number of hours worked on the `i-th` day (0 <= hours[i] <= 24).
 *
 * Output:
 *     - An integer representing the length of the longest well-performing interval.
 *
 * Example:
 *     Input:
 *     5
 *     9 7 9 9 7
 *
 *     Output:
 *     3
 *     
 *     Explanation:
 *     The longest well-performing interval is the subarray [9, 7, 9], which has more days with hours > 8 (2 days) than days with hours <= 8 (1 day).
 */

import java.util.HashMap;
import java.util.Scanner;

public class j15LongestWellPerformInterval {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the hours array
        int[] hours = new int[n]; // Array initialization
        for (int i = 0; i < n; i++) {
            hours[i] = in.nextInt(); // Input: hours worked each day
        }
        System.out.println(longestWPI(hours)); // Call the O(N^2) solution (brute force)
        System.out.println(longestWPIHashMap(hours)); // Call the O(N) optimized solution
        in.close();
    }

    /**
     * Approach 1: O(N^2) Brute Force Approach
     *
     * Intuition:
     * - We need to find the longest subarray where the number of days with hours > 8 is strictly greater than the number of days
     *   with hours <= 8.
     * - In this approach, for each subarray, we calculate the number of days with hours > 8 and compare it to the number of days 
     *   with hours <= 8.
     * - If the number of days with hours > 8 is strictly greater, we update the maximum length of the subarray found.
     * 
     * Time Complexity:
     * - O(N^2): We iterate over all pairs of subarray start and end indices.
     * 
     * Space Complexity:
     * - O(1): Constant space used for variables.
     *
     * @param hours The input array representing hours worked each day.
     * @return The length of the longest well-performing interval.
     */
    public static int longestWPI(int[] hours) {
        int maxL = 0; // Variable to store the maximum length of valid subarrays
        for (int i = 0; i < hours.length; i++) {
            int count = 0; // Variable to count days with hours > 8 (positive) and hours <= 8 (negative)
            for (int j = i; j < hours.length; j++) {
                count += hours[j] > 8 ? 1 : -1; // Increment for hours > 8, decrement for hours <= 8
                if (count > 0) { // Check if there are more days with hours > 8
                    maxL = Math.max(maxL, j - i + 1); // Update max length if a longer valid subarray is found
                }
            }
        }
        return maxL; // Return the maximum length of valid subarrays found
    }

    /**
     * Approach 2: O(N) Optimized Approach Using HashMap
     *
     * Intuition:
     * - Instead of iterating over all subarrays, we can use a prefix sum approach to track the difference between the number of days
     *   with hours > 8 and the number of days with hours <= 8.
     * - We maintain a running count of the difference between the days with hours > 8 and hours <= 8.
     * - Whenever this difference (let's call it "count") reaches a value we've seen before, it indicates that the subarray between those
     *   two positions has more days with hours > 8 than hours <= 8.
     * - We use a HashMap to store the first occurrence of each value of `count`.
     * - If `count > 0`, the subarray from the beginning of the array to the current index is valid, and we update the maximum length.
     * - If `count` has been seen before, we calculate the length of the subarray between the first occurrence and the current index.
     * 
     * Time Complexity:
     * - O(N): We traverse the array once, and each operation with the HashMap (get and put) is O(1) on average.
     * 
     * Space Complexity:
     * - O(N): The space is used by the HashMap to store the running counts.
     *
     * @param hours The input array representing hours worked each day.
     * @return The length of the longest well-performing interval.
     */
    public static int longestWPIHashMap(int[] hours) {
        int count = 0; // Running count of the difference between days with hours > 8 and hours <= 8
        int maxL = 0; // Variable to store the maximum length of valid subarrays
        HashMap<Integer, Integer> map = new HashMap<>(); // HashMap to store the first occurrence of each running count

        for (int i = 0; i < hours.length; i++) {
            count += hours[i] > 8 ? 1 : -1; // Increment for hours > 8, decrement for hours <= 8

            // If the running count is positive, the subarray from the beginning to the
            // current index is valid
            if (count > 0) {
                maxL = i + 1; // Update max length if the subarray is valid
            } else {
                // If this running count has been seen before, the subarray between those two
                // positions is valid
                if (map.containsKey(count - 1)) {
                    maxL = Math.max(maxL, i - map.get(count - 1)); // Update max length if a longer valid subarray is
                                                                   // found
                }

                // Store the first occurrence of the current running count
                if (!map.containsKey(count)) {
                    map.put(count, i);
                }
            }
        }
        return maxL; // Return the maximum length of valid subarrays found
    }
}
