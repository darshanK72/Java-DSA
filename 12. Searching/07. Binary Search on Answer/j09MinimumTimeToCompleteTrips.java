
/**
 * Problem Statement:
 * 
 *     Given an array `time` where `time[i]` represents the time taken by the
 *     i-th bus to complete one trip, and an integer `totalTrips`, find the
 *     minimum time required to complete `totalTrips` trips.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `time` of size `n` where each element is a positive integer.
 *     - An integer `totalTrips` (1 <= totalTrips <= 10^7), representing the
 *       total number of trips.
 * 
 * Output:
 *     - A long integer representing the minimum time required to complete
 *       `totalTrips` trips.
 * 
 * Example:
 *     Input:
 *     n = 3
 *     time = [1, 2, 3]
 *     totalTrips = 5
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The minimum time required to complete 5 trips is 3 units of time.
 */

import java.util.Scanner;

public class j09MinimumTimeToCompleteTrips {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of elements in the array
        int[] time = new int[n];

        for (int i = 0; i < n; i++) {
            time[i] = in.nextInt();
        }
        int totalTrips = in.nextInt(); // Total trips

        // Output the result of the approach
        System.out.println(minimumTime(time, totalTrips));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The minimum
     *   time required must be at least the minimum time in the array, and at most
     *   the minimum time multiplied by the total number of trips.
     * - We perform binary search within this range to find the minimum time that
     *   allows us to complete the required number of trips.
     * 
     * Time Complexity:
     * - O(n log(max(time) * totalTrips)). This is because we perform binary search
     *   on the range [min(time), min(time) * totalTrips], and for each mid value,
     *   we iterate through the array to calculate the number of trips possible.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param time The input array of times.
     * @param totalTrips The total number of trips.
     * @return The minimum time required to complete the required number of trips.
     */
    public static long minimumTime(int[] time, int totalTrips) {
        long s = Integer.MAX_VALUE;
        for (int i = 0; i < time.length; i++) {
            s = Math.min(s, time[i]);
        }
        long e = s * totalTrips;
        while (s <= e) {
            long mid = s + (e - s) / 2;
            if (isPossible(time, totalTrips, mid)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return s;
    }

    /**
     * Helper method to check if it is possible to complete the required number of
     * trips within the given time.
     * 
     * @param time The input array of times.
     * @param totalTrips The total number of trips.
     * @param mid The time to check feasibility.
     * @return True if it is possible to complete the required number of trips
     *         within the given time, otherwise false.
     */
    public static boolean isPossible(int[] time, int totalTrips, long mid) {
        long trips = 0;
        for (int i = 0; i < time.length; i++) {
            trips += mid / time[i];
        }
        return trips >= totalTrips;
    }
}