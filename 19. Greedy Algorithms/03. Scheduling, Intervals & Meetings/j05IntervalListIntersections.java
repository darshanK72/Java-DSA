/**
 * LeetCode 986. Interval List Intersections
 * 
 * Problem Statement:
 *     You are given two lists of closed intervals, firstList and secondList, where
 *     firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list
 *     of intervals is pairwise disjoint and in sorted order. Return the
 *     intersection of these two interval lists.
 * 
 * Input:
 *     - firstList (int[][]): Array of intervals [starti, endi] in sorted order
 *     - secondList (int[][]): Array of intervals [startj, endj] in sorted order
 * 
 * Output:
 *     - int[][]: Array of intersection intervals
 * 
 * Example:
 *     Input: firstList = [[1,3],[5,9]], secondList = [[2,5],[7,10]]
 *     Output: [[2,3],[5,5],[7,9]]
 * 
 *     Explanation:
 *     [1,3] ∩ [2,5] = [2,3]
 *     [5,9] ∩ [2,5] = [5,5]
 *     [5,9] ∩ [7,10] = [7,9]
 */

import java.util.ArrayList;

public class j05IntervalListIntersections {

    /**
     * Approach: Two Pointer Technique
     * 
     * Intuition:
     * - Since both lists are sorted, we can use two pointers to track current
     *   intervals in both lists
     * - For any two intervals, their intersection is the maximum of starts and
     *   minimum of ends
     * - We move the pointer of the interval that ends first, as it cannot
     *   intersect with any other intervals in the other list
     * 
     * Explanation:
     * - Step 1: Initialize two pointers i and j for both lists
     * - Step 2: For each pair of intervals:
     *   * Calculate potential intersection using max of starts and min of ends
     *   * If start <= end, we have a valid intersection
     *   * Move the pointer of the interval that ends first
     * - Step 3: Convert ArrayList to required output format
     * 
     * Time Complexity: O(M + N) where M and N are lengths of input lists
     * Space Complexity: O(M + N) for storing intersection intervals
     * 
     * @param firstList    First list of intervals in sorted order
     * @param secondList   Second list of intervals in sorted order
     * @return            Array of intersection intervals
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // Initialize pointers and result list
        int i = 0;
        int j = 0;
        ArrayList<int[]> list = new ArrayList<>();

        // Process intervals while both lists have elements
        while (i < firstList.length && j < secondList.length) {
            // Calculate potential intersection
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);

            // If valid intersection exists, add to result
            if (start <= end) {
                list.add(new int[] { start, end });
            }

            // Move pointer of the interval that ends first
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        // Convert ArrayList to required output format
        int[][] out = new int[list.size()][2];
        int k = 0;
        for (int[] interval : list) {
            out[k++] = interval;
        }

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic overlapping intervals
        System.out.println("\nBasic Test Cases:");
        int[][] firstList1 = {{1,3}, {5,9}};
        int[][] secondList1 = {{2,5}, {7,10}};
        int[][] result1 = intervalIntersection(firstList1, secondList1);
        System.out.println("Input: firstList = [[1,3],[5,9]], secondList = [[2,5],[7,10]]");
        System.out.println("Output: " + java.util.Arrays.deepToString(result1));

        // Test Case 2: No overlapping intervals
        System.out.println("\nNo Overlap Cases:");
        int[][] firstList2 = {{1,2}, {3,4}};
        int[][] secondList2 = {{5,6}, {7,8}};
        int[][] result2 = intervalIntersection(firstList2, secondList2);
        System.out.println("Input: firstList = [[1,2],[3,4]], secondList = [[5,6],[7,8]]");
        System.out.println("Output: " + java.util.Arrays.deepToString(result2));

        // Test Case 3: Empty input
        System.out.println("\nEdge Cases:");
        int[][] firstList3 = {};
        int[][] secondList3 = {{1,2}};
        int[][] result3 = intervalIntersection(firstList3, secondList3);
        System.out.println("Input: firstList = [], secondList = [[1,2]]");
        System.out.println("Output: " + java.util.Arrays.deepToString(result3));

        // Test Case 4: Complete overlap
        System.out.println("\nComplete Overlap Cases:");
        int[][] firstList4 = {{1,5}};
        int[][] secondList4 = {{2,3}};
        int[][] result4 = intervalIntersection(firstList4, secondList4);
        System.out.println("Input: firstList = [[1,5]], secondList = [[2,3]]");
        System.out.println("Output: " + java.util.Arrays.deepToString(result4));
    }
}
