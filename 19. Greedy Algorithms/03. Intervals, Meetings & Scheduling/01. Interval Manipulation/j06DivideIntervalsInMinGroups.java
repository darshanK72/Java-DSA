
/**
 * LeetCode 2406. Divide Intervals Into Minimum Number of Groups
 * 
 * Problem Statement:
 *     You are given a 2D integer array intervals where intervals[i] = [starti, endi]
 *     represents the start and end of the ith interval. You need to divide the
 *     intervals into one or more groups such that each interval is in exactly one
 *     group, and no two intervals in the same group intersect. Return the minimum
 *     number of groups needed.
 * 
 * Input:
 *     - intervals (int[][]): Array of intervals where intervals[i] = [starti, endi]
 * 
 * Output:
 *     - int: Minimum number of groups needed to divide intervals without
 *            intersection within groups
 * 
 * Example:
 *     Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 *     Output: 3
 * 
 *     Explanation:
 *     Group 1: [1,5], [6,8]
 *     Group 2: [2,3], [5,10]
 *     Group 3: [1,10]
 *     Visual representation:
 *     [1,5]    |--------|
 *     [2,3]       |--|
 *     [5,10]          |--------|
 *     [6,8]              |--|
 *     [1,10]   |------------------|
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class j06DivideIntervalsInMinGroups {

    /**
     * Approach 1: Greedy with ArrayList
     * 
     * Intuition:
     * - Sort intervals by start time (and end time if starts are equal)
     * - For each interval, try to find a group where it can be placed
     * - A group can accept an interval if the interval's start is greater than
     * the group's last end time
     * - If no such group exists, create a new group
     * 
     * Explanation:
     * - Step 1: Sort intervals by start time (and end time if starts are equal)
     * - Step 2: For each interval:
     * * Try to find a group where interval's start > group's last end time
     * * If found, update group's end time to current interval's end
     * * If not found, create new group with current interval's end time
     * - Step 3: Return number of groups created
     * 
     * Time Complexity: O(N²) where N is number of intervals
     * Space Complexity: O(N) for storing groups
     * 
     * @param intervals Array of intervals where intervals[i] = [starti, endi]
     * @return Minimum number of groups needed
     */
    public static int minGroups(int[][] intervals) {
        // Initialize list to store end times of each group
        ArrayList<Integer> groups = new ArrayList<>();

        // Sort intervals by start time (and end time if starts are equal)
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        // Process each interval
        for (int[] interval : intervals) {
            boolean foundGroup = false;
            // Try to find a suitable group
            for (int i = 0; i < groups.size(); i++) {
                if (interval[0] > groups.get(i)) {
                    // Update group's end time
                    groups.set(i, interval[1]);
                    foundGroup = true;
                    break;
                }
            }
            // If no suitable group found, create new group
            if (!foundGroup) {
                groups.add(interval[1]);
            }
        }

        return groups.size();
    }

    /**
     * Approach 2: Greedy with Priority Queue
     * 
     * Intuition:
     * - Sort intervals by start time (and end time if starts are equal)
     * - Use min heap to track end times of groups
     * - For each interval, if its start time is greater than the minimum end time
     * in heap, we can reuse that group
     * - Otherwise, create a new group
     * 
     * Explanation:
     * - Step 1: Sort intervals by start time (and end time if starts are equal)
     * - Step 2: For each interval:
     * * If heap is empty or interval's start <= min end time, create new group
     * * Otherwise, remove min end time and add current interval's end time
     * - Step 3: Return size of heap (number of groups)
     * 
     * Time Complexity: O(N log N) where N is number of intervals
     * Space Complexity: O(N) for storing groups in heap
     * 
     * @param intervals Array of intervals where intervals[i] = [starti, endi]
     * @return Minimum number of groups needed
     */
    public static int minGroupsPriorityQueue(int[][] intervals) {
        // Initialize min heap to store end times of groups
        PriorityQueue<Integer> groups = new PriorityQueue<>();

        // Sort intervals by start time (and end time if starts are equal)
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        // Process each interval
        for (int[] interval : intervals) {
            if (groups.isEmpty()) {
                // Create first group
                groups.add(interval[1]);
            } else {
                int min = groups.peek();
                if (min < interval[0]) {
                    // Reuse existing group
                    groups.poll();
                }
                // Add current interval's end time
                groups.add(interval[1]);
            }
        }

        return groups.size();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic overlapping intervals
        System.out.println("\nBasic Test Cases:");
        int[][] intervals1 = { { 5, 10 }, { 6, 8 }, { 1, 5 }, { 2, 3 }, { 1, 10 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals1));
        System.out.println("Output (ArrayList): " + minGroups(intervals1));
        System.out.println("Output (PriorityQueue): " + minGroupsPriorityQueue(intervals1));
        System.out.println("Expected: 3");

        // Test Case 2: No overlapping intervals
        System.out.println("\nNo Overlap Cases:");
        int[][] intervals2 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals2));
        System.out.println("Output (ArrayList): " + minGroups(intervals2));
        System.out.println("Output (PriorityQueue): " + minGroupsPriorityQueue(intervals2));
        System.out.println("Expected: 1");

        // Test Case 3: All intervals overlap
        System.out.println("\nAll Overlap Cases:");
        int[][] intervals3 = { { 1, 4 }, { 2, 3 }, { 3, 4 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals3));
        System.out.println("Output (ArrayList): " + minGroups(intervals3));
        System.out.println("Output (PriorityQueue): " + minGroupsPriorityQueue(intervals3));
        System.out.println("Expected: 3");

        // Test Case 4: Empty input
        System.out.println("\nEdge Cases:");
        int[][] intervals4 = {};
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals4));
        System.out.println("Output (ArrayList): " + minGroups(intervals4));
        System.out.println("Output (PriorityQueue): " + minGroupsPriorityQueue(intervals4));
        System.out.println("Expected: 0");

        // Test Case 5: Single interval
        System.out.println("\nSingle Interval Case:");
        int[][] intervals5 = { { 1, 2 } };
        System.out.println("Input: " + java.util.Arrays.deepToString(intervals5));
        System.out.println("Output (ArrayList): " + minGroups(intervals5));
        System.out.println("Output (PriorityQueue): " + minGroupsPriorityQueue(intervals5));
        System.out.println("Expected: 1");
    }
}
