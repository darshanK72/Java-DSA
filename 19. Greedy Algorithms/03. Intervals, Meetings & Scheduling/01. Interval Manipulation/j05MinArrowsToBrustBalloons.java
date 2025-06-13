/**
 * LeetCode 452. Minimum Number of Arrows to Burst Balloons
 * 
 * Problem Statement:
 *     There are some spherical balloons taped onto a flat wall that represents the XY-plane. 
 *     The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] 
 *     denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not 
 *     know the exact y-coordinates of the balloons. Arrows can be shot up directly vertically 
 *     (in the positive y-direction) from different points along the x-axis. A balloon with 
 *     xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit 
 *     to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, 
 *     bursting any balloons in its path. Given the array points, return the minimum number of 
 *     arrows that must be shot to burst all balloons.
 * 
 * Input:
 *     - points: 2D integer array where points[i] = [xstart, xend]
 *     - 1 <= points.length <= 10^5
 *     - -2^31 <= xstart < xend <= 2^31 - 1
 * 
 * Output:
 *     - Return the minimum number of arrows needed to burst all balloons
 * 
 * Example:
 *     Input: points = [[10,16],[2,8],[1,6],[7,12]]
 *     Output: 2
 * 
 *     Explanation:
 *     The balloons can be burst by 2 arrows:
 *     - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6]
 *     - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12]
 */

import java.util.Arrays;

public class j05MinArrowsToBrustBalloons {
    /**
     * Approach: Greedy with Sorting
     * 
     * Intuition:
     * - The key insight is that we want to find overlapping intervals and shoot arrows
     *   at points where maximum balloons can be burst
     * - By sorting the balloons by their start points, we can process them in order
     * - For overlapping balloons, we can shoot one arrow at the minimum end point
     *   of the overlapping group
     * 
     * Explanation:
     * 1. Sort the balloons by their start points (and end points if starts are equal)
     * 2. Keep track of the previous end point
     * 3. For each balloon:
     *    - If current start > previous end, we need a new arrow
     *    - If overlapping, update the previous end to minimum of current end and previous end
     * 
     * Time Complexity: O(n log n) where n is the number of balloons
     *                  - Sorting takes O(n log n)
     *                  - Single pass through array takes O(n)
     * 
     * Space Complexity: O(1) as we only use a few variables
     * 
     * @param points    2D array representing balloon intervals [xstart, xend]
     * @return         Minimum number of arrows needed to burst all balloons
     */
    public static int findMinArrowShots(int[][] points) {
        // Sort balloons by start points (and end points if starts are equal)
        Arrays.sort(points, (a, b) -> {
            if (a[0] > b[0])
                return 1;
            if (a[0] < b[0])
                return -1;
            else {
                return a[1] - b[1];
            }
        });

        int arrows = 0;  // Count of arrows needed
        int i = 0;       // Current balloon index
        long prevEnd = Long.MIN_VALUE;  // Track end of previous overlapping group
        
        // Process each balloon
        while (i < points.length) {
            // If current balloon starts after previous end, we need a new arrow
            if (points[i][0] > prevEnd) {
                arrows++;
                prevEnd = points[i][1];  // Update end to current balloon's end
            } else {
                // For overlapping balloons, update end to minimum of current and previous
                prevEnd = Math.min(prevEnd, points[i][1]);
            }
            i++;
        }

        return arrows;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic overlapping balloons
        System.out.println("\nBasic Test Cases:");
        int[][] test1 = {{10,16}, {2,8}, {1,6}, {7,12}};
        System.out.println("Input: [[10,16],[2,8],[1,6],[7,12]], Expected: 2, Output: " + 
                         findMinArrowShots(test1));

        // Test Case 2: Non-overlapping balloons
        System.out.println("\nNon-overlapping Cases:");
        int[][] test2 = {{1,2}, {3,4}, {5,6}, {7,8}};
        System.out.println("Input: [[1,2],[3,4],[5,6],[7,8]], Expected: 4, Output: " + 
                         findMinArrowShots(test2));

        // Test Case 3: All overlapping balloons
        System.out.println("\nAll Overlapping Cases:");
        int[][] test3 = {{1,6}, {2,8}, {7,12}, {10,16}};
        System.out.println("Input: [[1,6],[2,8],[7,12],[10,16]], Expected: 2, Output: " + 
                         findMinArrowShots(test3));

        // Test Case 4: Single balloon
        System.out.println("\nEdge Cases:");
        int[][] test4 = {{1,2}};
        System.out.println("Input: [[1,2]], Expected: 1, Output: " + 
                         findMinArrowShots(test4));
    }
}
