/**
 * LeetCode 1353. Maximum Number of Events That Can Be Attended
 * 
 * Problem Statement:
 *     You are given an array of events where events[i] = [startDayi, endDayi].
 *     Every event i starts at startDayi and ends at endDayi. You can attend an
 *     event i at any day d where startDayi <= d <= endDayi. You can only attend
 *     one event at a time. Return the maximum number of events you can attend.
 * 
 * Input:
 *     - events (int[][]): Array of events where events[i] = [startDayi, endDayi]
 * 
 * Output:
 *     - int: Maximum number of events that can be attended
 * 
 * Example:
 *     Input: events = [[1,2],[2,3],[3,4]]
 *     Output: 3
 * 
 *     Explanation:
 *     You can attend all the three events:
 *     Day 1: [1,2]
 *     Day 2: [2,3]
 *     Day 3: [3,4]
 *     Visual representation:
 *     [1,2]    |--|
 *     [2,3]       |--|
 *     [3,4]          |--|
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class j10MaxEventsCanBeAttended {

    /**
     * Approach: Greedy with Priority Queue
     * 
     * Intuition:
     * - Sort events by start day (and end day if starts are equal)
     * - For each day, process all events that start on that day
     * - Use min heap to track end days of available events
     * - Always attend the event that ends earliest
     * - Remove events that have already ended
     * 
     * Explanation:
     * - Step 1: Sort events by start day (and end day if starts are equal)
     * - Step 2: Find the range of days (first start day to last end day)
     * - Step 3: For each day in the range:
     *   * Remove events that have already ended (end day < current day)
     *   * Add all events that start on current day to the heap
     *   * If there are available events, attend the one ending earliest
     * - Step 4: Return count of events attended
     * 
     * Time Complexity: O(D log N) where D is number of days and N is number of events
     * Space Complexity: O(N) for storing events in heap
     * 
     * @param events Array of events where events[i] = [startDayi, endDayi]
     * @return      Maximum number of events that can be attended
     */
    public static int maxEvents(int[][] events) {
        // Sort events by start day (and end day if starts are equal)
        Arrays.sort(events, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        // Initialize min heap to store end days of available events
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Find the range of days
        int lastDay = 0;
        int day = Integer.MAX_VALUE;
        for (int[] event : events) {
            day = Math.min(day, event[0]);
            lastDay = Math.max(lastDay, event[1]);
        }

        // Process each day in the range
        int count = 0;
        int i = 0;
        while (day <= lastDay) {
            // Remove events that have already ended
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }

            // Add all events that start on current day
            while (i < events.length && events[i][0] == day) {
                pq.add(events[i][1]);
                i++;
            }

            // Attend the event that ends earliest
            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }
            day++;
        }

        return count;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic overlapping events
        System.out.println("\nBasic Test Cases:");
        int[][] events1 = { { 1, 2 }, { 2, 3 }, { 3, 4 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(events1));
        System.out.println("Output: " + maxEvents(events1));
        System.out.println("Expected: 3");

        // Test Case 2: No overlapping events
        System.out.println("\nNo Overlap Cases:");
        int[][] events2 = { { 1, 1 }, { 2, 2 }, { 3, 3 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(events2));
        System.out.println("Output: " + maxEvents(events2));
        System.out.println("Expected: 3");

        // Test Case 3: All events overlap
        System.out.println("\nAll Overlap Cases:");
        int[][] events3 = { { 1, 4 }, { 2, 4 }, { 3, 4 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(events3));
        System.out.println("Output: " + maxEvents(events3));
        System.out.println("Expected: 4");

        // Test Case 4: Empty input
        System.out.println("\nEdge Cases:");
        int[][] events4 = {};
        System.out.println("Input: " + java.util.Arrays.deepToString(events4));
        System.out.println("Output: " + maxEvents(events4));
        System.out.println("Expected: 0");

        // Test Case 5: Single event
        System.out.println("\nSingle Event Case:");
        int[][] events5 = { { 1, 2 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(events5));
        System.out.println("Output: " + maxEvents(events5));
        System.out.println("Expected: 1");

        // Test Case 6: Events with same start day
        System.out.println("\nSame Start Day Cases:");
        int[][] events6 = { { 1, 2 }, { 1, 3 }, { 1, 4 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(events6));
        System.out.println("Output: " + maxEvents(events6));
        System.out.println("Expected: 3");
    }
}
