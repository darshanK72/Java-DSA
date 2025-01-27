/**
 * Problem Statement:
 *
 *     Given a set of intervals, insert a new interval into the set and merge any overlapping intervals.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 100), representing the number of existing intervals.
 *     - A 2D array `intervals` of size `n`, where each element is an interval [start, end] (0 <= start <= end <= 100).
 *     - A 1D array `newInterval` representing the new interval to be inserted, in the form [start, end].
 *
 * Output:
 *     - A 2D array of merged intervals after inserting the new interval.
 *
 * Example:
 *     Input:
 *     4
 *     1 3
 *     6 9
 *     10 12
 *     14 16
 *     7 10
 *     Output:
 *     [[1, 3], [6, 12], [14, 16]]
 *
 *     Explanation:
 *     After inserting the new interval [7, 10], the intervals (6, 9) and (10, 12) merge into (6, 12).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j20InsertInterval {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of existing intervals
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt(); // Input: the start of the interval
            arr[i][1] = in.nextInt(); // Input: the end of the interval
        }

        // Input: the new interval to be inserted
        int[] newInterval = new int[2];
        newInterval[0] = in.nextInt(); // Start of the new interval
        newInterval[1] = in.nextInt(); // End of the new interval

        // Call the function to insert the new interval and merge intervals
        System.out.println(Arrays.deepToString(insertInterval(arr, newInterval)));

        in.close();
    }

    /**
     * Approach: Inserting an Interval and Merging Overlapping Intervals (O(n))
     *
     * Intuition:
     * - We need to find the correct position to insert the new interval while merging it with existing overlapping intervals.
     * - First, we add all intervals that are completely to the left of the new interval (no overlap).
     * - Then, we merge the new interval with overlapping intervals.
     * - Finally, we add all intervals that are completely to the right of the new interval (no overlap).
     * 
     * Time Complexity:
     * - We iterate over the intervals once, so the time complexity is O(n), where n is the number of intervals.
     *
     * Space Complexity:
     * - O(n) for the result array to store the merged intervals.
     *
     * @param intervals The input array of intervals.
     * @param newInterval The new interval to be inserted.
     * @return The intervals after inserting the new interval and merging any overlaps.
     */
    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        // If no intervals exist, return just the new interval
        if (intervals.length == 0) {
            return new int[][] { newInterval };
        }

        // List to store the result intervals
        ArrayList<int[]> list = new ArrayList<>();

        int i = 0;

        // Add all intervals that are before the new interval
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        // Merge all overlapping intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add the merged new interval
        list.add(newInterval);

        // Add all intervals that are after the new interval
        while (i < intervals.length) {
            list.add(intervals[i]);
            i++;
        }

        // Convert the list back to a 2D array and return
        return list.toArray(new int[list.size()][]);
    }
}
