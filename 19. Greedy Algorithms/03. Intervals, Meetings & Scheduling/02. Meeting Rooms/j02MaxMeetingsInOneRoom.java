/**
 * GeeksForGeeks: Maximum Meetings in One Room
 * 
 * Problem Statement:
 *     There is one meeting room in a firm. There are N meetings in the form of
 *     (start[i], end[i]) where start[i] is start time of meeting i and end[i] is
 *     finish time of meeting i. What is the maximum number of meetings that can
 *     be accommodated in the meeting room when only one meeting can be held in
 *     the meeting room at a particular time?
 * 
 * Input:
 *     - start[] (int[]): Array of start times of meetings
 *     - end[] (int[]): Array of end times of meetings
 * 
 * Output:
 *     - int: Maximum number of meetings that can be held
 * 
 * Example:
 *     Input: start[] = {1, 3, 0, 5, 8, 5}, end[] = {2, 4, 6, 7, 9, 9}
 *     Output: 4
 * 
 *     Explanation:
 *     Meetings that can be held: {1,2}, {3,4}, {5,7}, {8,9}
 *     Visual representation:
 *     [1,2]    |--|
 *     [3,4]       |--|
 *     [0,6]   |------|
 *     [5,7]          |--|
 *     [8,9]             |--|
 *     [5,9]          |------|
 */

import java.util.Arrays;

public class j02MaxMeetingsInOneRoom {

    /**
     * Approach: Greedy with Sorting by Start Time
     * 
     * Intuition:
     * - Sort meetings by start time (and end time if starts are equal)
     * - Process meetings from right to left
     * - Keep track of the earliest start time seen so far
     * - If current meeting's end is less than earliest start, it can be scheduled
     * 
     * Why Process from Right to Left?
     * - When we process from right to left, we always know the earliest start
     * time of all meetings that come after the current one
     * - This allows us to make a greedy decision: if current meeting's end is
     * less than earliest start, we can schedule it
     * - Processing from left to right would be more complex as we'd need to
     * track multiple potential conflicts with future meetings
     * 
     * Explanation:
     * - Step 1: Convert start and end arrays to 2D array of meetings
     * - Step 2: Sort meetings by start time (and end time if starts are equal)
     * - Step 3: Process meetings from right to left:
     * * If current meeting's end < earliest start seen, increment count
     * * Update earliest start to current meeting's start
     * - Step 4: Return count of meetings that can be scheduled
     * 
     * Time Complexity: O(N log N) for sorting, where N is number of meetings
     * Space Complexity: O(N) for storing meetings array
     * 
     * @param start Array of start times of meetings
     * @param end   Array of end times of meetings
     * @return Maximum number of meetings that can be held
     */
    public static int maxMeetingsSortingByStartTime(int start[], int end[]) {
        // Convert start and end arrays to 2D array of meetings
        int[][] meetings = new int[start.length][2];
        for (int i = 0; i < start.length; i++) {
            meetings[i] = new int[] { start[i], end[i] };
        }

        // Sort meetings by start time (and end time if starts are equal)
        Arrays.sort(meetings, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        // Initialize variables for tracking
        int ans = 0;
        int i = start.length - 1;
        int lastMeetingStart = Integer.MAX_VALUE;

        // Process meetings from right to left
        while (i >= 0) {
            // If current meeting's end < earliest start seen, it can be scheduled
            if (meetings[i][1] < lastMeetingStart) {
                ans++;
                lastMeetingStart = meetings[i][0];
            }
            i--;
        }

        return ans;
    }

    /**
     * Approach: Greedy with Sorting by End Time
     * 
     * Intuition:
     * - The key insight is to prioritize meetings that end earlier
     * - By sorting meetings by end time, we can maximize the number of meetings
     * - This ensures we don't block time slots that could be used for other meetings
     * 
     * Explanation:
     * 1. Create a 2D array of meetings combining start and end times
     * 2. Sort meetings by end time in ascending order
     * 3. Keep track of the end time of the last scheduled meeting
     * 4. For each meeting:
     *    - If current meeting starts after last meeting ends, schedule it
     *    - Update last meeting end time and increment count
     *    - Skip meetings that overlap with the last scheduled meeting
     * 
     * Time Complexity: O(n log n) where:
     *                  - n is the number of meetings
     *                  - Sorting takes O(n log n)
     *                  - Single pass through array takes O(n)
     * 
     * Space Complexity: O(n) where:
     *                   - n for storing meetings array
     * 
     * @param start  Array of meeting start times
     * @param end    Array of meeting end times
     * @return      Maximum number of meetings that can be scheduled
     */
    public static int maxMeetingsSortingByEndTime(int start[], int end[]) {
        // Create array of meetings with start and end times
        int[][] meetings = new int[start.length][2];
        for (int i = 0; i < start.length; i++) {
            meetings[i] = new int[] {
                    start[i], end[i]
            };
        }

        // Sort meetings by end time in ascending order
        Arrays.sort(meetings, (a, b) -> {
            return a[1] - b[1];
        });

        int ans = 0;  // Count of meetings that can be scheduled

        int i = 0;    // Current meeting index
        int lastMeetingEnd = Integer.MIN_VALUE;  // End time of last scheduled meeting

        // Process each meeting
        while (i < meetings.length) {
            // If current meeting starts after last meeting ends, schedule it
            if (lastMeetingEnd < meetings[i][0]) {
                ans++;  // Increment count of scheduled meetings
                lastMeetingEnd = meetings[i][1];  // Update last meeting end time
            }
            i++;
        }

        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic overlapping meetings
        System.out.println("\nBasic Test Cases:");
        int[] start1 = { 1, 3, 0, 5, 8, 5 };
        int[] end1 = { 2, 4, 6, 7, 9, 9 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start1));
        System.out.println("Input: end = " + java.util.Arrays.toString(end1));
        System.out.println("Output: " + maxMeetingsSortingByStartTime(start1, end1));
        System.out.println("Expected: 4");

        // Test Case 2: No overlapping meetings
        System.out.println("\nNo Overlap Cases:");
        int[] start2 = { 1, 3, 5 };
        int[] end2 = { 2, 4, 6 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start2));
        System.out.println("Input: end = " + java.util.Arrays.toString(end2));
        System.out.println("Output: " + maxMeetingsSortingByStartTime(start2, end2));
        System.out.println("Expected: 3");

        // Test Case 3: All meetings overlap
        System.out.println("\nAll Overlap Cases:");
        int[] start3 = { 1, 2, 3 };
        int[] end3 = { 4, 4, 4 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start3));
        System.out.println("Input: end = " + java.util.Arrays.toString(end3));
        System.out.println("Output: " + maxMeetingsSortingByEndTime(start3, end3));
        System.out.println("Expected: 1");

        // Test Case 4: Empty input
        System.out.println("\nEdge Cases:");
        int[] start4 = {};
        int[] end4 = {};
        System.out.println("Input: start = " + java.util.Arrays.toString(start4));
        System.out.println("Input: end = " + java.util.Arrays.toString(end4));
        System.out.println("Output: " + maxMeetingsSortingByEndTime(start4, end4));
        System.out.println("Expected: 0");

        // Test Case 5: Single meeting
        System.out.println("\nSingle Meeting Case:");
        int[] start5 = { 1 };
        int[] end5 = { 2 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start5));
        System.out.println("Input: end = " + java.util.Arrays.toString(end5));
        System.out.println("Output: " + maxMeetingsSortingByEndTime(start5, end5));
        System.out.println("Expected: 1");
    }
}
