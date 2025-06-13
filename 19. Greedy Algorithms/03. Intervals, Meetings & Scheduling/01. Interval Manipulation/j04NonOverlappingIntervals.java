/**
 * LeetCode 435. Non-overlapping Intervals
 * 
 * Problem Statement:
 *     Given an array of intervals where intervals[i] = [starti, endi], return the
 *     minimum number of intervals you need to remove to make the rest of the
 *     intervals non-overlapping.
 * 
 * Input:
 *     - intervals (int[][]): Array of intervals where intervals[i] = [starti, endi]
 * 
 * Output:
 *     - int: Minimum number of intervals to remove to make all intervals
 *            non-overlapping
 * 
 * Example:
 *     Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 *     Output: 1
 * 
 *     Explanation:
 *     [1,3] can be removed and the rest of the intervals are non-overlapping.
 *     Visual representation:
 *     [1,2]    |----|
 *     [2,3]       |----|
 *     [3,4]          |----|
 *     [1,3]    |--------|  (remove this)
 */

import java.util.Arrays;

public class j04NonOverlappingIntervals {

    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - Sort intervals by start time (and end time if starts are equal)
     * - Process intervals from right to left
     * - Keep track of the earliest start time seen so far
     * - If current interval's end is greater than earliest start, it overlaps
     *   and needs to be removed
     * 
     * Why Process from Right to Left?
     * - When we process from right to left, we always know the earliest start
     *   time of all intervals that come after the current one
     * - This allows us to make a greedy decision: if current interval's end
     *   overlaps with any future interval (end > earliest start), we must remove
     *   the current interval
     * - Processing from left to right would be more complex as we'd need to
     *   track multiple potential overlaps with future intervals
     * - Right to left processing ensures we make optimal decisions by always
     *   having complete information about future intervals
     * 
     * Explanation:
     * - Step 1: Sort intervals by start time (and end time if starts are equal)
     * - Step 2: Process intervals from right to left:
     *   * If current interval's end > earliest start seen, increment count
     *   * Otherwise, update earliest start to current interval's start
     * - Step 3: Return count of intervals to remove
     * 
     * Time Complexity: O(N log N) for sorting, where N is number of intervals
     * Space Complexity: O(1) as we only use a constant amount of extra space
     * 
     * @param intervals    Array of intervals where intervals[i] = [starti, endi]
     * @return            Minimum number of intervals to remove
     */
    public static int eraseOverlapIntervalsStartTimeSorting(int[][] intervals) {
        // Sort intervals by start time (and end time if starts are equal)
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        // Initialize variables for tracking
        int ans = 0;
        int i = intervals.length - 1;
        int prevStart = Integer.MAX_VALUE;

        // Process intervals from right to left
        while (i >= 0) {
            // If current interval's end > earliest start seen, it overlaps
            if (intervals[i][1] > prevStart)
                ans++;
            else
                // Update earliest start to current interval's start
                prevStart = intervals[i][0];
            i--;
        }

        return ans;
    }

    /**
     * Approach: Greedy with End Time Sorting
     * 
     * Intuition:
     * - Sort intervals by end time (and start time if ends are equal)
     * - Keep track of the last valid end time
     * - If current interval starts after or at the last end time, it's valid
     * - Count valid intervals and subtract from total to get overlapping ones
     * 
     * Explanation:
     * - Step 1: Sort intervals by end time (and start time if ends are equal)
     * - Step 2: Initialize count of valid intervals and last end time
     * - Step 3: For each interval:
     *   * If current interval starts after or at last end time, it's valid
     *   * Update last end time to current interval's end
     *   * Increment valid interval count
     * - Step 4: Return total intervals minus valid intervals
     * 
     * Time Complexity: O(N log N) for sorting
     * Space Complexity: O(1) for in-place sorting
     * 
     * @param intervals Array of intervals where intervals[i] = [starti, endi]
     * @return         Number of intervals to remove to make non-overlapping
     */
    public static int eraseOverlapIntervalsEndTimeSorting(int[][] intervals) {
        // Sort intervals by end time (and start time if ends are equal)
        Arrays.sort(intervals,(a,b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            else return a[0] - b[0];
        });

        // Initialize count of valid intervals and last end time
        int ans = 0;
        int i = 0;
        int prevEnd = Integer.MIN_VALUE;

        // Process each interval
        while(i < intervals.length){
            // If current interval starts after or at last end time, it's valid
            if(intervals[i][0] >= prevEnd){
                ans++;
                prevEnd = Math.max(prevEnd,intervals[i][1]);
            }
            i++;
        }

        // Return number of intervals to remove
        return intervals.length - ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic overlapping intervals
        System.out.println("\nBasic Test Cases:");
        int[][] intervals1 = {{1,2}, {2,3}, {3,4}, {1,3}};
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals1));
        System.out.println("Output: " + eraseOverlapIntervalsStartTimeSorting(intervals1));
        System.out.println("Expected: 1");

        // Test Case 2: No overlapping intervals
        System.out.println("\nNo Overlap Cases:");
        int[][] intervals2 = {{1,2}, {3,4}, {5,6}};
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals2));
        System.out.println("Output: " + eraseOverlapIntervalsStartTimeSorting(intervals2));
        System.out.println("Expected: 0");

        // Test Case 3: All intervals overlap
        System.out.println("\nAll Overlap Cases:");
        int[][] intervals3 = {{1,4}, {2,3}, {3,4}};
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals3));
        System.out.println("Output: " + eraseOverlapIntervalsStartTimeSorting(intervals3));
        System.out.println("Expected: 2");

        // Test Case 4: Empty input
        System.out.println("\nEdge Cases:");
        int[][] intervals4 = {};
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals4));
        System.out.println("Output: " + eraseOverlapIntervalsStartTimeSorting(intervals4));
        System.out.println("Expected: 0");

        // Test Case 5: Single interval
        System.out.println("\nSingle Interval Case:");
        int[][] intervals5 = {{1,2}};
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals5));
        System.out.println("Output: " + eraseOverlapIntervalsStartTimeSorting(intervals5));
        System.out.println("Expected: 0");
    }
}
