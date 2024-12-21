/*-
 * Problem Statement:
 *
 *     Given a list of houses and a list of heaters, each represented by their positions on a number line,
 *     determine the minimum radius of a heater such that every house is heated. The radius is defined as 
 *     the maximum distance between a house and its closest heater.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the number of houses.
 *     - An array `houses` of size `n` where each element satisfies (1 <= houses[i] <= 10^9).
 *     - An integer `m` (1 <= m <= 10^4), representing the number of heaters.
 *     - An array `heaters` of size `m` where each element satisfies (1 <= heaters[i] <= 10^9).
 *
 * Output:
 *     - An integer, the minimum radius such that every house is heated.
 *
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     2
 *     2 3
 *     Output:
 *     1
 * 
 *     Explanation:
 *     The closest heaters to houses are:
 *     - House 1: closest heater is at position 2 with a distance of 1.
 *     - House 2: closest heater is at position 2 with a distance of 0.
 *     - House 3: closest heater is at position 3 with a distance of 0.
 *     Hence, the minimum radius is 1, the maximum distance between a house and its closest heater.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j10MinRadiousToHeatHouses {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of houses
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = in.nextInt(); // Input: positions of the houses
        }
        int m = in.nextInt(); // Input: the number of heaters
        int[] heaters = new int[m];
        for (int i = 0; i < m; i++) {
            heaters[i] = in.nextInt(); // Input: positions of the heaters
        }
        System.out.println(findRadius(houses, heaters)); // Output: minimum radius
        in.close();
    }

    /*-
     * Approach: Binary Search and Distance Calculation
     * 
     * Intuition:
     * - Since the positions of heaters are fixed and sorted, we can use binary search to find the closest heater 
     *   to each house. For each house, we calculate the distance to the nearest heater using binary search.
     * - After finding the closest heater for each house, we take the maximum of these distances. 
     *   This value represents the minimum radius needed to heat all the houses.
     * 
     * Time Complexity:
     * - O(n * log m), where `n` is the number of houses and `m` is the number of heaters.
     *   We perform binary search for each house, which takes `O(log m)` time.
     * 
     * Space Complexity:
     * - O(1), since we use a constant amount of extra space for the search operations.
     * 
     * @param houses The positions of the houses.
     * @param heaters The positions of the heaters.
     * @return The minimum radius required to heat all houses.
     */
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters); // Sort heaters to apply binary search
        int minRadius = 0;
        int n = houses.length;

        // For each house, find the closest heater and update the maximum radius
        for (int i = 0; i < n; i++) {
            minRadius = Math.max(minRadius, findClosestHeater(houses[i], heaters));
        }

        return minRadius;
    }

    /*-
     * Helper Function: Find the closest heater for a given house
     * 
     * Intuition:
     * - Perform binary search to find the closest heater for the current house.
     * - The closest heater could be the one just before or after the house. We calculate the distances to both 
     *   and return the smaller distance.
     * 
     * Time Complexity:
     * - O(log m), where `m` is the number of heaters, due to the binary search.
     * 
     * Space Complexity:
     * - O(1), since we use a constant amount of extra space for the search operation.
     * 
     * @param house The position of the house.
     * @param heaters The positions of the heaters.
     * @return The distance to the closest heater.
     */
    public static int findClosestHeater(int house, int[] heaters) {
        int s = 0;
        int e = heaters.length - 1;

        // Perform binary search to find the closest heater
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (heaters[mid] > house) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        // Calculate the distance to the closest heater (floor and ceil)
        int floorDist = e == -1 ? Integer.MAX_VALUE : house - heaters[e];
        int ceilDist = s == heaters.length ? Integer.MAX_VALUE : heaters[s] - house;

        // Return the minimum distance to the closest heater
        return Math.min(floorDist, ceilDist);
    }
}
