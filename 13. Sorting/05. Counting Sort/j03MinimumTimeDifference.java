/**
 * Problem Statement:
 * 
 *     Given a list of 24-hour formatted time points, find the minimum difference
 *     between any two time points when viewed as minutes within a single day.
 *     The time points wrap around midnight, so the difference is calculated
 *     cyclically (i.e., considering 00:00 as the start and 23:59 as the end).
 * 
 * Input:
 *     - A list of strings `timePoints`, where each string represents a time in 
 *       "HH:MM" format (00:00 <= HH:MM <= 23:59).
 *     - The list contains at least two and at most `10^4` time points.
 * 
 * Output:
 *     - An integer representing the minimum time difference in minutes between 
 *       any two time points.
 * 
 * Example:
 *     Input:
 *         timePoints = ["23:59", "00:00", "12:34"]
 *     Output:
 *         1
 * 
 *     Explanation:
 *         - The time points are converted to minutes: [1439, 0, 754].
 *         - The sorted list: [0, 754, 1439].
 *         - The differences: 754 - 0 = 754, 1439 - 754 = 685, and 
 *           cyclic difference: (24 * 60 + 0) - 1439 = 1.
 *         - The minimum difference is 1.
 */

import java.util.Arrays;
import java.util.List;

public class j03MinimumTimeDifference {
    public static void main(String[] args) {
        List<String> timePoints1 = Arrays.asList("23:59", "00:00", "12:34");
        List<String> timePoints2 = Arrays.asList("01:01", "02:01", "03:00");
        List<String> timePoints3 = Arrays.asList("05:31", "22:08", "00:35");

        System.out.println("Minimum time difference: " + findMinDifferenceBruitForce(timePoints1));
        System.out.println("Minimum time difference: " + findMinDifferenceCountingSort(timePoints2));
        System.out.println("Minimum time difference: " + findMinDifferenceCountingSort(timePoints3));
    }

    /**
     * Approach 1: Brute Force (Sorting + Linear Scan)
     * 
     * Intuition:
     * - Convert all time points into minutes (from "HH:MM" format).
     * - Sort the list to simplify the calculation of consecutive differences.
     * - Compute the difference between adjacent time points in the sorted list.
     * - Account for the circular nature by considering the difference between
     *   the last and first time points.
     * 
     * Time Complexity:
     * - O(n log n) due to sorting, followed by O(n) linear scanning.
     * - Overall: O(n log n).
     * 
     * Space Complexity:
     * - O(n) for storing converted time points.
     * 
     * @param timePoints List of time points in "HH:MM" format.
     * @return The minimum time difference in minutes.
     */
    public static int findMinDifferenceBruitForce(List<String> timePoints) {
        int n = timePoints.size();
        int[] minutes = new int[n];
        for (int i = 0; i < n; i++) {
            minutes[i] = getTimeInMinutes(timePoints.get(i));
        }

        Arrays.sort(minutes);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            minDiff = Math.min(minDiff, minutes[i + 1] - minutes[i]);
        }

        // Consider the cyclic difference (between the last and first time point)
        return Math.min(minDiff, 24 * 60 - minutes[n - 1] + minutes[0]);
    }

    /**
     * Approach 2: Optimized Approach using Counting Sort
     * 
     * Intuition:
     * - The problem constraints ensure a limited range (1440 possible time points).
     * - Instead of sorting, use a boolean array (`count[]`) to track encountered time points.
     * - Iterate once through `count[]` to find the minimum adjacent difference.
     * - This avoids O(n log n) sorting overhead, reducing time complexity to O(n).
     * 
     * Explanation:
     * - Convert each time point into minutes and mark its presence in a boolean array.
     * - If a duplicate time point exists, return 0 (as the min difference must be 0).
     * - Iterate through the boolean array to find the smallest difference.
     * - Handle cyclic difference by computing the difference between the last and first valid time.
     * 
     * Time Complexity:
     * - O(n) since we traverse the input once and iterate through a fixed-size array.
     * 
     * Space Complexity:
     * - O(1440) = O(1) since the count array has a fixed size.
     * 
     * @param timePoints List of time points in "HH:MM" format.
     * @return The minimum time difference in minutes.
     */
    public static int findMinDifferenceCountingSort(List<String> timePoints) {
        boolean[] count = new boolean[24 * 60];
        for (String time : timePoints) {
            int tm = getTimeInMinutes(time);
            if (count[tm]) {
                return 0; // If a time is duplicated, the minimum difference is 0.
            }
            count[tm] = true;
        }

        int prevTime = -1;
        int firstTime = -1;
        int minDiff = Integer.MAX_VALUE;
        for (int currTime = 0; currTime < 24 * 60; currTime++) {
            if (count[currTime]) {
                if (prevTime == -1) {
                    firstTime = currTime;
                    prevTime = currTime;
                } else {
                    minDiff = Math.min(minDiff, currTime - prevTime);
                    prevTime = currTime;
                }
            }
        }

        // Consider cyclic difference (wrap-around case)
        minDiff = Math.min(minDiff, 24 * 60 + firstTime - prevTime);
        return minDiff;
    }

    /**
     * Helper Method: Convert Time String to Minutes
     * 
     * Intuition:
     * - Convert "HH:MM" to total minutes by multiplying hours by 60 and adding minutes.
     * - Example: "12:30" -> 12 * 60 + 30 = 750 minutes.
     * 
     * Time Complexity:
     * - O(1) per call.
     * 
     * Space Complexity:
     * - O(1) since it only uses a few integer variables.
     * 
     * @param timeStamp Time in "HH:MM" format.
     * @return The equivalent time in minutes.
     */
    public static int getTimeInMinutes(String timeStamp) {
        String[] time = timeStamp.split(":");
        int mins = Integer.parseInt(time[0]) * 60;
        mins += Integer.parseInt(time[1]);
        return mins;
    }
}
