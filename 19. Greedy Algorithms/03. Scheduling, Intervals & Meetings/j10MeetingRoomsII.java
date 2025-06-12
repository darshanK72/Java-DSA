/**
 * LeetCode 253. Meeting Rooms II
 * 
 * Problem Statement:
 *     Given an array of meeting time intervals where intervals[i] = [starti, endi],
 *     return the minimum number of conference rooms required. A meeting room can
 *     only be used for one meeting at a time.
 * 
 * Input:
 *     - start (int[]): Array of start times of meetings
 *     - end (int[]): Array of end times of meetings
 * 
 * Output:
 *     - int: Minimum number of conference rooms required
 * 
 * Example:
 *     Input: start = [0, 5, 15], end = [30, 10, 20]
 *     Output: 2
 * 
 *     Explanation:
 *     We need two meeting rooms:
 *     Room 1: [0,30]
 *     Room 2: [5,10], [15,20]
 *     Visual representation:
 *     [0,30]   |------------------------|
 *     [5,10]      |--|
 *     [15,20]          |--|
 */

import java.util.Arrays;

public class j10MeetingRoomsII {

    /**
     * Approach: Chronological Ordering
     * 
     * Intuition:
     * - Sort start and end times separately
     * - Use two pointers to track current start and end times
     * - When a meeting starts, we need a new room
     * - When a meeting ends, we can reuse that room
     * - Keep track of maximum rooms needed at any point
     * 
     * Explanation:
     * - Step 1: Sort start and end times separately
     * - Step 2: Use two pointers (i for start times, j for end times):
     *   * If current start time < current end time:
     *     - We need a new room (increment rooms)
     *     - Update max rooms if needed
     *     - Move start pointer
     *   * Else:
     *     - A meeting has ended, we can reuse that room (decrement rooms)
     *     - Move end pointer
     * - Step 3: Return maximum number of rooms needed
     * 
     * Time Complexity: O(N log N) for sorting, where N is number of meetings
     * Space Complexity: O(1) as we only use a constant amount of extra space
     * 
     * @param start Array of start times of meetings
     * @param end   Array of end times of meetings
     * @return     Minimum number of conference rooms required
     */
    public int minMeetingRooms(int[] start, int[] end) {
        // Sort start and end times separately
        Arrays.sort(start);
        Arrays.sort(end);

        // Initialize variables for tracking
        int rooms = 0;
        int maxRooms = 0;

        // Use two pointers to track current start and end times
        int i = 0;
        int j = 0;

        // Process all meetings
        while (i < start.length) {
            if (start[i] < end[j]) {
                // New meeting starts before any meeting ends
                rooms++;
                maxRooms = Math.max(maxRooms, rooms);
                i++;
            } else {
                // A meeting has ended, we can reuse that room
                rooms--;
                j++;
            }
        }

        return maxRooms;
    }

    public static void main(String[] args) {
        j10MeetingRoomsII solution = new j10MeetingRoomsII();

        // Test Case 1: Basic overlapping meetings
        System.out.println("\nBasic Test Cases:");
        int[] start1 = { 0, 5, 15 };
        int[] end1 = { 30, 10, 20 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start1));
        System.out.println("Input: end = " + java.util.Arrays.toString(end1));
        System.out.println("Output: " + solution.minMeetingRooms(start1, end1));
        System.out.println("Expected: 2");

        // Test Case 2: No overlapping meetings
        System.out.println("\nNo Overlap Cases:");
        int[] start2 = { 0, 5, 10 };
        int[] end2 = { 4, 9, 15 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start2));
        System.out.println("Input: end = " + java.util.Arrays.toString(end2));
        System.out.println("Output: " + solution.minMeetingRooms(start2, end2));
        System.out.println("Expected: 1");

        // Test Case 3: All meetings overlap
        System.out.println("\nAll Overlap Cases:");
        int[] start3 = { 0, 1, 2 };
        int[] end3 = { 5, 5, 5 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start3));
        System.out.println("Input: end = " + java.util.Arrays.toString(end3));
        System.out.println("Output: " + solution.minMeetingRooms(start3, end3));
        System.out.println("Expected: 3");

        // Test Case 4: Empty input
        System.out.println("\nEdge Cases:");
        int[] start4 = {};
        int[] end4 = {};
        System.out.println("Input: start = " + java.util.Arrays.toString(start4));
        System.out.println("Input: end = " + java.util.Arrays.toString(end4));
        System.out.println("Output: " + solution.minMeetingRooms(start4, end4));
        System.out.println("Expected: 0");

        // Test Case 5: Single meeting
        System.out.println("\nSingle Meeting Case:");
        int[] start5 = { 0 };
        int[] end5 = { 5 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start5));
        System.out.println("Input: end = " + java.util.Arrays.toString(end5));
        System.out.println("Output: " + solution.minMeetingRooms(start5, end5));
        System.out.println("Expected: 1");

        // Test Case 6: Meetings with same start time
        System.out.println("\nSame Start Time Cases:");
        int[] start6 = { 0, 0, 0 };
        int[] end6 = { 5, 10, 15 };
        System.out.println("Input: start = " + java.util.Arrays.toString(start6));
        System.out.println("Input: end = " + java.util.Arrays.toString(end6));
        System.out.println("Output: " + solution.minMeetingRooms(start6, end6));
        System.out.println("Expected: 3");
    }
}
