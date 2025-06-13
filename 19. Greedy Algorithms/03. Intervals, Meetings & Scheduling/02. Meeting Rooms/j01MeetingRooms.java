/**
 * LeetCode 252. Meeting Rooms
 * 
 * Problem Statement:
 *     Given an array of meeting time intervals where intervals[i] = [starti, endi],
 *     determine if a person could attend all meetings. A person can attend all
 *     meetings if no two meetings overlap.
 * 
 * Input:
 *     - arr (int[][]): Array of intervals where intervals[i] = [starti, endi]
 * 
 * Output:
 *     - boolean: true if a person could attend all meetings, false otherwise
 * 
 * Example:
 *     Input: intervals = [[0,30],[5,10],[15,20]]
 *     Output: false
 * 
 *     Explanation:
 *     [0,30] overlaps with [5,10] and [15,20]
 *     Visual representation:
 *     [0,30]   |------------------------|
 *     [5,10]      |--|
 *     [15,20]          |--|
 */

import java.util.Arrays;

public class j01MeetingRooms {

    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - Sort intervals by start time (and end time if starts are equal)
     * - Check adjacent intervals for overlap
     * - If any two adjacent intervals overlap, return false
     * - If no overlaps found, return true
     * 
     * Explanation:
     * - Step 1: Sort intervals by start time (and end time if starts are equal)
     * - Step 2: Iterate through sorted intervals:
     *   * Compare current interval's start with previous interval's end
     *   * If current start < previous end, there is an overlap
     * - Step 3: Return true if no overlaps found
     * 
     * Time Complexity: O(N log N) for sorting, where N is number of intervals
     * Space Complexity: O(1) as we only use a constant amount of extra space
     * 
     * @param arr Array of intervals where intervals[i] = [starti, endi]
     * @return   true if a person could attend all meetings, false otherwise
     */
    public static boolean canAttend(int[][] arr) {
        // Sort intervals by start time (and end time if starts are equal)
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        // Check adjacent intervals for overlap
        for (int i = 1; i < arr.length; i++) {
            // If current interval's start < previous interval's end, there is an overlap
            if (arr[i - 1][1] > arr[i][0])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Overlapping meetings
        System.out.println("\nBasic Test Cases:");
        int[][] intervals1 = { { 0, 30 }, { 5, 10 }, { 15, 20 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals1));
        System.out.println("Output: " + canAttend(intervals1));
        System.out.println("Expected: false");

        // Test Case 2: No overlapping meetings
        System.out.println("\nNo Overlap Cases:");
        int[][] intervals2 = { { 0, 5 }, { 6, 10 }, { 11, 15 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals2));
        System.out.println("Output: " + canAttend(intervals2));
        System.out.println("Expected: true");

        // Test Case 3: Meetings with same start time
        System.out.println("\nSame Start Time Cases:");
        int[][] intervals3 = { { 0, 5 }, { 0, 10 }, { 15, 20 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals3));
        System.out.println("Output: " + canAttend(intervals3));
        System.out.println("Expected: false");

        // Test Case 4: Empty input
        System.out.println("\nEdge Cases:");
        int[][] intervals4 = {};
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals4));
        System.out.println("Output: " + canAttend(intervals4));
        System.out.println("Expected: true");

        // Test Case 5: Single meeting
        System.out.println("\nSingle Meeting Case:");
        int[][] intervals5 = { { 0, 5 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals5));
        System.out.println("Output: " + canAttend(intervals5));
        System.out.println("Expected: true");

        // Test Case 6: Meetings with same end time
        System.out.println("\nSame End Time Cases:");
        int[][] intervals6 = { { 0, 5 }, { 6, 10 }, { 10, 15 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals6));
        System.out.println("Output: " + canAttend(intervals6));
        System.out.println("Expected: true");
    }
}
