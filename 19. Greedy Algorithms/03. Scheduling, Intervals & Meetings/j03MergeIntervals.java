/**
 * LeetCode 56 - Merge Intervals
 * 
 * Problem Statement:
 *     Given an array of intervals where intervals[i] = [starti, endi], merge all
 *     overlapping intervals, and return an array of the non-overlapping intervals
 *     that cover all the intervals in the input.
 * 
 * Input:
 *     - intervals[][]: 2D array where intervals[i] = [starti, endi]
 *     - Constraints: 1 ≤ intervals.length ≤ 10^4, intervals[i].length == 2
 * 
 * Output:
 *     - Array of merged intervals where no intervals overlap
 * 
 * Example:
 *     Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *     Output: [[1,6],[8,10],[15,18]]
 * 
 *     Explanation:
 *     Since intervals [1,3] and [2,6] overlap, merge them into [1,6]
 *     Intervals [8,10] and [15,18] are non-overlapping, so they remain unchanged
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class j03MergeIntervals {
    /**
     * Approach 1: Sorting
     * 
     * Intuition:
     * - Sort intervals by start time to process them in order
     * - If current interval overlaps with previous, merge them
     * - If no overlap, add current interval to result
     * 
     * Explanation:
     * 1. Sort intervals by start time (and end time for ties)
     * 2. For each interval:
     *    - If it overlaps with previous, merge them
     *    - If no overlap, add to result
     * 3. Return merged intervals
     * 
     * Time Complexity: O(N log N) where N is number of intervals
     * - Sorting: O(N log N)
     * - Merging: O(N)
     * 
     * Space Complexity: O(N)
     * - ArrayList for storing merged intervals
     * - Output array
     * 
     * @param intervals    2D array of intervals [start, end]
     * @return            Array of merged intervals
     */
    public static int[][] mergeSorting(int[][] intervals) {
        // Sort intervals by start time, and then by end time
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1]; // If start times are the same, sort by end time
            }
            return a[0] - b[0]; // Sort by start time
        });

        // List to store the merged intervals
        ArrayList<int[]> list = new ArrayList<>();

        // Iterate over sorted intervals
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            int j = i + 1;
            // Merge overlapping intervals
            while (j < intervals.length && intervals[j][0] <= end) {
                end = Math.max(end, intervals[j][1]);
                j++;
            }
            // Add the merged interval to the list
            list.add(new int[] { start, end });
            i = j - 1; // Skip the merged intervals
        }

        // Convert the list of Integer[] to int[][]
        int[][] out = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            out[i] = list.get(i);
        }
        return out;
    }

    /**
     * Approach 2: Priority Queue
     * 
     * Intuition:
     * - Use min-heap to process intervals in order of start time
     * - Similar to sorting approach but uses heap for ordering
     * - Useful when intervals are coming in stream
     * 
     * Explanation:
     * 1. Add all intervals to min-heap sorted by start time
     * 2. While heap is not empty:
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
     * @param intervals    2D array of intervals [start, end]
     * @return            Array of merged intervals
     */
    public static int[][] mergePriorityQueue(int[][] intervals) {
        // Create min-heap sorted by start time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        // Add all intervals to heap
        for (int i = 0; i < intervals.length; i++) {
            pq.add(intervals[i]);
        }

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
        int[][] intervals1 = {{1,3}, {2,6}, {8,10}, {15,18}};
        System.out.println("Input: " + Arrays.deepToString(intervals1));
        System.out.println("Output (Sorting): " + Arrays.deepToString(mergeSorting(intervals1)));
        System.out.println("Output (Priority Queue): " + Arrays.deepToString(mergePriorityQueue(intervals1)));

        // Test Case 2: All intervals overlap
        System.out.println("\nAll Overlapping Test:");
        int[][] intervals2 = {{1,4}, {2,5}, {3,6}, {4,7}};
        System.out.println("Input: " + Arrays.deepToString(intervals2));
        System.out.println("Output (Sorting): " + Arrays.deepToString(mergeSorting(intervals2)));
        System.out.println("Output (Priority Queue): " + Arrays.deepToString(mergePriorityQueue(intervals2)));

        // Test Case 3: No overlapping intervals
        System.out.println("\nNo Overlapping Test:");
        int[][] intervals3 = {{1,2}, {3,4}, {5,6}, {7,8}};
        System.out.println("Input: " + Arrays.deepToString(intervals3));
        System.out.println("Output (Sorting): " + Arrays.deepToString(mergeSorting(intervals3)));
        System.out.println("Output (Priority Queue): " + Arrays.deepToString(mergePriorityQueue(intervals3)));

        // Test Case 4: Single interval
        System.out.println("\nSingle Interval Test:");
        int[][] intervals4 = {{1,5}};
        System.out.println("Input: " + Arrays.deepToString(intervals4));
        System.out.println("Output (Sorting): " + Arrays.deepToString(mergeSorting(intervals4)));
        System.out.println("Output (Priority Queue): " + Arrays.deepToString(mergePriorityQueue(intervals4)));
    }
}
