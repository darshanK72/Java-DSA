/**
 * LeetCode 253. Meeting Rooms II
 * 
 * Problem Statement:
 *     Given an array of meeting time intervals where intervals[i] = [starti, endi], return the
 *     minimum number of conference rooms required. A meeting room can only be used for one
 *     meeting at a time. If a meeting starts at time t, it must end before time t+1.
 * 
 * Input:
 *     - intervals: 2D array where intervals[i] = [starti, endi]
 *     - 1 <= intervals.length <= 10^4
 *     - 0 <= starti < endi <= 10^6
 * 
 * Output:
 *     - Minimum number of conference rooms required
 * 
 * Example:
 *     Input: intervals = [[0,30],[5,10],[15,20]]
 *     Output: 2
 * 
 *     Explanation:
 *     We need two meeting rooms:
 *     Room 1: [0,30]
 *     Room 2: [5,10], [15,20]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class j08MinMeetingRooms {
    /**
     * Approach: Chronological Ordering
     * 
     * Intuition:
     * - The key insight is to track the number of meetings happening at any time
     * - We can do this by processing start and end times separately
     * - When a meeting starts, we need a new room
     * - When a meeting ends, we can reuse that room
     * 
     * Explanation:
     * 1. Separate start and end times into two arrays
     * 2. Sort both arrays
     * 3. Use two pointers to track:
     *    - Current meeting start (i)
     *    - Current meeting end (j)
     * 4. For each time point:
     *    - If a meeting starts before another ends, we need a new room
     *    - If a meeting ends, we can reuse that room
     *    - Keep track of maximum rooms needed
     * 
     * Time Complexity: O(n log n) where:
     *                  - n is the number of meetings
     *                  - Sorting takes O(n log n)
     *                  - Single pass through arrays takes O(n)
     * 
     * Space Complexity: O(n) where:
     *                   - n for storing start and end arrays
     * 
     * @param intervals  2D array of meeting times [start, end]
     * @return          Minimum number of rooms required
     */
    public static int minRooms(int[][] intervals) {
        int n = intervals.length;
        // Separate start and end times
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        // Sort start and end times
        Arrays.sort(start);
        Arrays.sort(end);

        int rooms = 1;     // Current number of rooms in use
        int maxRooms = 1;  // Maximum rooms needed
        int i = 1;         // Pointer for start times
        int j = 0;         // Pointer for end times

        // Process all meetings
        while (i < n && j < n) {
            // If a meeting starts before another ends, we need a new room
            if (start[i] < end[j]) {
                i++;
                rooms++;
                maxRooms = Math.max(maxRooms, rooms);
            } else {
                // If a meeting ends, we can reuse that room
                j++;
                rooms--;
            }
        }

        return maxRooms;
    }

    /**
     * Approach: Event-based Chronological Ordering
     * 
     * Intuition:
     * - The key insight is to treat each meeting as two events: start and end
     * - By processing all events in chronological order, we can track room usage
     * - When a meeting starts, we need a new room
     * - When a meeting ends, we can reuse that room
     * - For same time events, end events are processed before start events
     * 
     * Explanation:
     * 1. Create a list of events, where each event is [time, type]
     *    - type 0: start event
     *    - type 1: end event
     * 2. Sort events by time
     *    - If times are equal, end events come before start events
     * 3. Process events in order:
     *    - For start events: increment room count
     *    - For end events: decrement room count
     *    - Track maximum rooms needed
     * 
     * Time Complexity: O(n log n) where:
     *                  - n is the number of meetings
     *                  - Creating and sorting events takes O(n log n)
     *                  - Processing events takes O(n)
     * 
     * Space Complexity: O(n) where:
     *                   - n for storing events list
     * 
     * @param intervals  2D array of meeting times [start, end]
     * @return          Minimum number of rooms required
     */
    public static int minRoomsThroughEvents(int[][] intervals) {
        // Create list to store all events (start and end times)
        ArrayList<int[]> events = new ArrayList<>();
        
        // Convert each meeting into start and end events
        for(int i = 0; i < intervals.length; i++) {
            // Add start time with type 0 (start)
            events.add(new int[]{intervals[i][0], 0});
            // Add end time with type 1 (end)
            events.add(new int[]{intervals[i][1], 1});
        }
        
        // Sort events by time
        // If times are equal, end events come before start events
        Collections.sort(events, (a, b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        int rooms = 0;     // Current number of rooms in use
        int maxRooms = 0;  // Maximum rooms needed
        
        // Process all events in chronological order
        for(int i = 0; i < events.size(); i++) {
            if(events.get(i)[1] == 0) { // Start event
                rooms++;  // Need a new room
                maxRooms = Math.max(maxRooms, rooms);
            } else { // End event
                rooms--;  // Free up a room
            }
        }
        
        // Return 1 if no meetings (maxRooms = 0), otherwise return maxRooms
        return maxRooms != 0 ? maxRooms : 1;
    }

    public static void main(String[] args) {
        // Test Case 1: Overlapping meetings requiring multiple rooms
        System.out.println("\nTest Case 1:");
        int[][] intervals1 = {{0,30}, {5,10}, {15,20}};
        System.out.println("Input: " + Arrays.deepToString(intervals1));
        System.out.println("Output: " + minRooms(intervals1));
        System.out.println("Expected: 2");

        // Test Case 2: Non-overlapping meetings
        System.out.println("\nTest Case 2:");
        int[][] intervals2 = {{7,10}, {2,4}};
        System.out.println("Input: " + Arrays.deepToString(intervals2));
        System.out.println("Output: " + minRooms(intervals2));
        System.out.println("Expected: 1");

        // Test Case 3: All meetings overlap
        System.out.println("\nTest Case 3:");
        int[][] intervals3 = {{0,30}, {5,10}, {15,20}, {10,40}};
        System.out.println("Input: " + Arrays.deepToString(intervals3));
        System.out.println("Output: " + minRooms(intervals3));
        System.out.println("Expected: 3");
    }
}
