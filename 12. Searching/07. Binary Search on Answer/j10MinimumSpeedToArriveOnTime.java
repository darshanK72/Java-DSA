
/**
 * Problem Statement:
 * 
 *     Given an array `dist` where `dist[i]` represents the distance of the i-th
 *     segment of a journey, and a floating-point number `hour` representing the
 *     total time available to complete the journey, find the minimum speed
 *     required to complete the journey within the given time.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `dist` of size `n` where each element is a positive integer.
 *     - A floating-point number `hour` (1 <= hour <= 10^7), representing the
 *       total time available to complete the journey.
 * 
 * Output:
 *     - An integer representing the minimum speed required to complete the
 *       journey within the given time.
 * 
 * Example:
 *     Input:
 *     n = 3
 *     dist = [1, 3, 2]
 *     hour = 2.7
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The minimum speed required to complete the journey within 2.7 hours is 3.
 */

import java.util.Scanner;

public class j10MinimumSpeedToArriveOnTime {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of elements in the array
        int[] dist = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = in.nextInt();
        }
        double hour = in.nextDouble(); // Total hours

        // Output the result of the approach
        System.out.println(minSpeedOnTime(dist, hour));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The minimum
     *   speed required must be at least 1, and at most a large value (e.g., 10^7).
     * - We perform binary search within this range to find the minimum speed that
     *   allows us to complete the journey within the given time.
     * 
     * Time Complexity:
     * - O(n log(maxSpeed)). This is because we perform binary search on the range
     *   [1, maxSpeed], and for each mid value, we iterate through the array to
     *   calculate the total time required.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param dist The input array of distances.
     * @param hour The total time available to complete the journey.
     * @return The minimum speed required to complete the journey within the given
     *         time.
     */
    public static int minSpeedOnTime(int[] dist, double hour) {
        int minSpeed = 1;
        int maxSpeed = (int) 1e7;
        int ans = -1;
        while (minSpeed <= maxSpeed) {
            int midSpeed = minSpeed + (maxSpeed - minSpeed) / 2;
            if (isPossible(dist, hour, midSpeed)) {
                ans = midSpeed;
                maxSpeed = midSpeed - 1;
            } else {
                minSpeed = midSpeed + 1;
            }
        }
        return ans;
    }

    /**
     * Helper method to check if it is possible to complete the journey within the
     * given time with the given speed.
     * 
     * @param dist The input array of distances.
     * @param hour The total time available to complete the journey.
     * @param speed The speed to check feasibility.
     * @return True if it is possible to complete the journey within the given time
     *         with the given speed, otherwise false.
     */
    public static boolean isPossible(int[] dist, double hour, int speed) {
        double totalTime = 0.0;
        for (int i = 0; i < dist.length; i++) {
            if (i == dist.length - 1) {
                totalTime += (double) dist[i] / speed;
            } else {
                totalTime += Math.ceil((double) dist[i] / speed);
            }
        }
        return totalTime <= hour;
    }
}