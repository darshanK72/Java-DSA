/**
 * Problem Statement:
 *
 *     Given a collection of intervals, merge all overlapping intervals and return the result as a list of non-overlapping intervals.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 100), representing the number of intervals.
 *     - A 2D array `intervals` of size `n` where each element is an interval [start, end] (0 <= start <= end <= 100).
 *
 * Output:
 *     - A 2D array representing the merged non-overlapping intervals.
 *
 * Example:
 *     Input:
 *     4
 *     1 3
 *     2 4
 *     5 7
 *     6 8
 *     Output:
 *     [[1, 4], [5, 8]]
 *
 *     Explanation:
 *     The first two intervals (1, 3) and (2, 4) overlap, so they are merged into (1, 4). Similarly, (5, 7) and (6, 8) overlap and are merged into (5, 8).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j16MergeIntervals {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of intervals
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt(); // Input: the start of the interval
            arr[i][1] = in.nextInt(); // Input: the end of the interval
        }

        // Merge intervals and print the result using two different methods
        System.out.println(Arrays.deepToString(mergeIntervals1(arr)));
        System.out.println(Arrays.deepToString(mergeIntervals2(arr)));

        in.close();
    }

    /**
     * Approach: Merging Intervals (O(n log n))
     *
     * Intuition:
     * - First, we sort the intervals by their start time. If two intervals have the same start time, we sort by their end time.
     * - We then iterate through the sorted intervals, and for each interval, we check if it overlaps with the previous merged interval.
     * - If it does, we merge them by updating the end of the merged interval. If not, we add the current interval to the result.
     * 
     * Time Complexity:
     * - Sorting the intervals takes O(n log n), and iterating over the sorted intervals takes O(n), so the overall time complexity is O(n log n).
     *
     * Space Complexity:
     * - O(n) for the space used to store the result.
     *
     * @param intervals The input array of intervals.
     * @return The merged intervals.
     */
    public static int[][] mergeIntervals1(int[][] intervals) {
        // Sort intervals by start time, and then by end time
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1]; // If start times are the same, sort by end time
            }
            return a[0] - b[0]; // Sort by start time
        });

        // List to store the merged intervals
        ArrayList<Integer[]> list = new ArrayList<>();

        // Iterate over sorted intervals
        for (int i = 0; i < intervals.length; i++) {
            int first = intervals[i][0];
            int second = intervals[i][1];
            int j = i + 1;
            // Merge overlapping intervals
            while (j < intervals.length && intervals[j][0] <= second) {
                second = Math.max(second, intervals[j][1]);
                j++;
            }
            // Add the merged interval to the list
            Integer[] temp = new Integer[] { first, second };
            list.add(temp);
            i = j - 1; // Skip the merged intervals
        }

        // Convert the list of Integer[] to int[][]
        int[][] out = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            out[i] = new int[] { list.get(i)[0], list.get(i)[1] };
        }
        return out;
    }

    /**
     * Alternative Approach: Merging Intervals with ArrayList of int[] (O(n log n))
     * 
     * Intuition:
     * - This method uses an ArrayList of primitive int arrays instead of Integer arrays to store the result.
     * - The overall approach and time complexity are the same as in the first method.
     *
     * @param intervals The input array of intervals.
     * @return The merged intervals.
     */
    public static int[][] mergeIntervals2(int[][] intervals) {
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
            int first = intervals[i][0];
            int second = intervals[i][1];
            int j = i + 1;
            // Merge overlapping intervals
            while (j < intervals.length && intervals[j][0] <= second) {
                second = Math.max(second, intervals[j][1]);
                j++;
            }
            // Add the merged interval to the list
            int[] temp = new int[] { first, second };
            list.add(temp);
            i = j - 1; // Skip the merged intervals
        }

        // Convert the ArrayList to a 2D array and return
        return list.toArray(new int[list.size()][]);
    }
}
