/**
 * LeetCode 57 - Insert Interval
 * 
 * Problem Statement:
 *     You are given an array of non-overlapping intervals intervals where
 *     intervals[i] = [starti, endi] represent the start and end of the ith
 *     interval and intervals is sorted in ascending order by starti. You are
 *     also given an interval newInterval = [start, end] that represents the
 *     start and end of another interval. Insert newInterval into intervals such
 *     that intervals is still sorted in ascending order by starti and intervals
 *     still does not have any overlapping intervals (merge overlapping intervals
 *     if necessary).
 * 
 * Input:
 *     - intervals[][]: 2D array of non-overlapping intervals sorted by start time
 *     - newInterval[]: Array of size 2 representing the interval to insert
 *     - Constraints: 0 ≤ intervals.length ≤ 10^4, intervals[i].length == 2
 * 
 * Output:
 *     - Array of intervals after inserting and merging newInterval
 * 
 * Example:
 *     Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 *     Output: [[1,5],[6,9]]
 * 
 *     Explanation:
 *     Since intervals [1,3] and [2,5] overlap, merge them into [1,5]
 *     Interval [6,9] remains unchanged as it doesn't overlap
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class j04InsertInterval {
    /**
     * Approach 1: Iterative
     * 
     * Intuition:
     * - Since intervals are already sorted, we can process them in order
     * - Add all intervals before newInterval
     * - Merge newInterval with any overlapping intervals
     * - Add all intervals after newInterval
     * 
     * Explanation:
     * 1. Handle empty intervals case
     * 2. Add all intervals that end before newInterval starts
     * 3. Merge newInterval with any overlapping intervals
     * 4. Add all remaining intervals
     * 
     * Time Complexity: O(N) where N is number of intervals
     * - Single pass through intervals array
     * 
     * Space Complexity: O(N)
     * - ArrayList for storing result intervals
     * - Output array
     * 
     * @param intervals    2D array of non-overlapping intervals
     * @param newInterval  Array of size 2 representing interval to insert
     * @return            Array of intervals after insertion and merging
     */
    public static int[][] insertIntervalIterative(int[][] intervals, int[] newInterval) {
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

    /**
     * Approach 2: Priority Queue
     * 
     * Intuition:
     * - Use min-heap to process all intervals in order
     * - Similar to merge intervals problem
     * - Useful when intervals are not pre-sorted
     * 
     * Explanation:
     * 1. Add all intervals and newInterval to min-heap
     * 2. Process intervals in order:
     *    - Remove interval with earliest start time
     *    - Merge with next intervals if they overlap
     *    - Add merged interval to result
     * 
     * Time Complexity: O(N log N) where N is number of intervals
     * - Building heap: O(N log N)
     * - Processing intervals: O(N log N)
     * 
     * Space Complexity: O(N)
     * - Priority queue: O(N)
     * - Result list: O(N)
     * 
     * @param intervals    2D array of intervals
     * @param newInterval  Array of size 2 representing interval to insert
     * @return            Array of intervals after insertion and merging
     */
    public static int[][] insertIntervalPriorityQueue(int[][] intervals, int[] newInterval) {
        // Create min-heap sorted by start time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        // Add all intervals and newInterval to heap
        for (int i = 0; i < intervals.length; i++) {
            pq.add(intervals[i]);
        }
        pq.add(newInterval);

        // Process intervals and merge overlapping ones
        ArrayList<int[]> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] interval = pq.remove();
            int start = interval[0];
            int end = interval[1];
            // Merge with overlapping intervals
            while (!pq.isEmpty() && pq.peek()[0] <= end) {
                end = Math.max(end, pq.remove()[1]);
            }
            result.add(new int[] { start, end });
        }

        // Convert result list to array
        int[][] out = new int[result.size()][2];
        int i = 0;
        for (int[] interval : result) {
            out[i++] = interval;
        }
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[][] intervals1 = {{1,3}, {6,9}};
        int[] newInterval1 = {2,5};
        System.out.println("Input: intervals = " + Arrays.deepToString(intervals1) + 
                         ", newInterval = " + Arrays.toString(newInterval1));
        System.out.println("Output (Iterative): " + 
                         Arrays.deepToString(insertIntervalIterative(intervals1, newInterval1)));
        System.out.println("Output (Priority Queue): " + 
                         Arrays.deepToString(insertIntervalPriorityQueue(intervals1, newInterval1)));

        // Test Case 2: No overlap
        System.out.println("\nNo Overlap Test:");
        int[][] intervals2 = {{1,2}, {3,4}, {6,7}};
        int[] newInterval2 = {5,5};
        System.out.println("Input: intervals = " + Arrays.deepToString(intervals2) + 
                         ", newInterval = " + Arrays.toString(newInterval2));
        System.out.println("Output (Iterative): " + 
                         Arrays.deepToString(insertIntervalIterative(intervals2, newInterval2)));
        System.out.println("Output (Priority Queue): " + 
                         Arrays.deepToString(insertIntervalPriorityQueue(intervals2, newInterval2)));

        // Test Case 3: Empty intervals
        System.out.println("\nEmpty Intervals Test:");
        int[][] intervals3 = {};
        int[] newInterval3 = {1,2};
        System.out.println("Input: intervals = " + Arrays.deepToString(intervals3) + 
                         ", newInterval = " + Arrays.toString(newInterval3));
        System.out.println("Output (Iterative): " + 
                         Arrays.deepToString(insertIntervalIterative(intervals3, newInterval3)));
        System.out.println("Output (Priority Queue): " + 
                         Arrays.deepToString(insertIntervalPriorityQueue(intervals3, newInterval3)));

        // Test Case 4: Overlap with all intervals
        System.out.println("\nOverlap All Test:");
        int[][] intervals4 = {{1,2}, {3,4}, {5,6}};
        int[] newInterval4 = {0,7};
        System.out.println("Input: intervals = " + Arrays.deepToString(intervals4) + 
                         ", newInterval = " + Arrays.toString(newInterval4));
        System.out.println("Output (Iterative): " + 
                         Arrays.deepToString(insertIntervalIterative(intervals4, newInterval4)));
        System.out.println("Output (Priority Queue): " + 
                         Arrays.deepToString(insertIntervalPriorityQueue(intervals4, newInterval4)));
    }
}
