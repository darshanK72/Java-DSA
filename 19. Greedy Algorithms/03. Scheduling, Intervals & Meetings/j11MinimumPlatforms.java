/**
 * GeeksForGeeks: Minimum Platforms
 * 
 * Problem Statement:
 *     Given arrival and departure times of all trains that reach a railway station,
 *     find the minimum number of platforms required for the railway station so that
 *     no train waits. We are given two arrays that represent arrival and departure
 *     times of trains that stop.
 * 
 * Input:
 *     - arr[] (int[]): Array of arrival times of trains
 *     - dep[] (int[]): Array of departure times of trains
 * 
 * Output:
 *     - int: Minimum number of platforms required
 * 
 * Example:
 *     Input: arr[] = {900, 940, 950, 1100, 1500, 1800}
 *            dep[] = {910, 1200, 1120, 1130, 1900, 2000}
 *     Output: 3
 * 
 *     Explanation:
 *     We need 3 platforms:
 *     Platform 1: [900,910]
 *     Platform 2: [940,1200], [1500,1900]
 *     Platform 3: [950,1120], [1100,1130], [1800,2000]
 *     Visual representation:
 *     [900,910]    |--|
 *     [940,1200]      |----------------|
 *     [950,1120]        |------------|
 *     [1100,1130]              |--|
 *     [1500,1900]                    |----------------|
 *     [1800,2000]                                |------------|
 */

import java.util.Arrays;

public class j11MinimumPlatforms {

    /**
     * Approach: Chronological Ordering
     * 
     * Intuition:
     * - Sort arrival and departure times separately
     * - Use two pointers to track current arrival and departure times
     * - When a train arrives, we need a new platform
     * - When a train departs, we can reuse that platform
     * - Keep track of maximum platforms needed at any point
     * 
     * Explanation:
     * - Step 1: Sort arrival and departure times separately
     * - Step 2: Use two pointers (i for arrivals, j for departures):
     *   * If current arrival time <= current departure time:
     *     - We need a new platform (increment platforms)
     *     - Update max platforms if needed
     *     - Move arrival pointer
     *   * Else:
     *     - A train has departed, we can reuse that platform (decrement platforms)
     *     - Move departure pointer
     * - Step 3: Return maximum number of platforms needed
     * 
     * Time Complexity: O(N log N) for sorting, where N is number of trains
     * Space Complexity: O(1) as we only use a constant amount of extra space
     * 
     * @param arr Array of arrival times of trains
     * @param dep Array of departure times of trains
     * @return   Minimum number of platforms required
     */
    static int findPlatform(int arr[], int dep[]) {
        // Sort arrival and departure times separately
        Arrays.sort(arr);
        Arrays.sort(dep);

        // Initialize variables for tracking
        int platforms = 0;
        int maxPlatforms = 0;

        // Use two pointers to track current arrival and departure times
        int i = 0;
        int j = 0;

        // Process all trains
        while (i < arr.length && j < arr.length) {
            if (arr[i] <= dep[j]) {
                // New train arrives before any train departs
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                i++;
            } else {
                // A train has departed, we can reuse that platform
                platforms--;
                j++;
            }
        }
        return maxPlatforms;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic overlapping trains
        System.out.println("\nBasic Test Cases:");
        int[] arr1 = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep1 = { 910, 1200, 1120, 1130, 1900, 2000 };
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr1));
        System.out.println("Input: dep = " + java.util.Arrays.toString(dep1));
        System.out.println("Output: " + findPlatform(arr1, dep1));
        System.out.println("Expected: 3");

        // Test Case 2: No overlapping trains
        System.out.println("\nNo Overlap Cases:");
        int[] arr2 = { 900, 1000, 1100 };
        int[] dep2 = { 930, 1030, 1130 };
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr2));
        System.out.println("Input: dep = " + java.util.Arrays.toString(dep2));
        System.out.println("Output: " + findPlatform(arr2, dep2));
        System.out.println("Expected: 1");

        // Test Case 3: All trains overlap
        System.out.println("\nAll Overlap Cases:");
        int[] arr3 = { 900, 910, 920 };
        int[] dep3 = { 1000, 1000, 1000 };
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr3));
        System.out.println("Input: dep = " + java.util.Arrays.toString(dep3));
        System.out.println("Output: " + findPlatform(arr3, dep3));
        System.out.println("Expected: 3");

        // Test Case 4: Empty input
        System.out.println("\nEdge Cases:");
        int[] arr4 = {};
        int[] dep4 = {};
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr4));
        System.out.println("Input: dep = " + java.util.Arrays.toString(dep4));
        System.out.println("Output: " + findPlatform(arr4, dep4));
        System.out.println("Expected: 0");

        // Test Case 5: Single train
        System.out.println("\nSingle Train Case:");
        int[] arr5 = { 900 };
        int[] dep5 = { 1000 };
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr5));
        System.out.println("Input: dep = " + java.util.Arrays.toString(dep5));
        System.out.println("Output: " + findPlatform(arr5, dep5));
        System.out.println("Expected: 1");

        // Test Case 6: Trains with same arrival time
        System.out.println("\nSame Arrival Time Cases:");
        int[] arr6 = { 900, 900, 900 };
        int[] dep6 = { 1000, 1100, 1200 };
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr6));
        System.out.println("Input: dep = " + java.util.Arrays.toString(dep6));
        System.out.println("Output: " + findPlatform(arr6, dep6));
        System.out.println("Expected: 3");
    }
}
