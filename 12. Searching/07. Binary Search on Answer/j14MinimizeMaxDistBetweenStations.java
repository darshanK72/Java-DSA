/**
 * Problem Statement:
 * 
 *     Given an array `stations` where `stations[i]` represents the position of
 *     the i-th station along a highway, and an integer `k` representing the
 *     number of additional stations to be added, find the smallest possible
 *     value of the maximum distance between adjacent stations after adding the
 *     `k` additional stations.
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^5), representing the size of the array.
 *     - An array `stations` of size `n` where each element is a positive integer
 *       representing the position of a station.
 *     - An integer `k` (1 <= k <= 10^9), representing the number of additional
 *       stations to be added.
 * 
 * Output:
 *     - A double representing the smallest possible value of the maximum distance
 *       between adjacent stations after adding the `k` additional stations.
 * 
 * Example:
 *     Input:
 *     n = 5
 *     stations = [1, 2, 3, 4, 5]
 *     k = 2
 *     Output:
 *     1.0
 * 
 *     Explanation:
 *     The smallest possible value of the maximum distance between adjacent
 *     stations after adding 2 additional stations is 1.0.
 */
import java.util.Scanner;

public class j14MinimizeMaxDistBetweenStations {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of elements in the array
        int[] stations = new int[n];

        for (int i = 0; i < n; i++) {
            stations[i] = in.nextInt();
        }
        int k = in.nextInt(); // Number of additional stations

        // Output the result of the approach
        System.out.printf("Smallest Maximum Distance: %.6f\n", findSmallestMaxDist(stations, k));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The smallest
     *   possible value of the maximum distance between adjacent stations must be
     *   at least 0, and at most the distance between the first and last station.
     * - We perform binary search within this range to find the smallest possible
     *   value of the maximum distance that allows us to add the required number of
     *   additional stations.
     * 
     * Time Complexity:
     * - O(n log(max(stations) - min(stations))). This is because we perform binary
     *   search on the range [0, max(stations) - min(stations)], and for each mid
     *   value, we iterate through the array to calculate the number of additional
     *   stations needed.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param stations The input array of station positions.
     * @param k The number of additional stations to be added.
     * @return The smallest possible value of the maximum distance between adjacent
     *         stations after adding the `k` additional stations.
     */
    public static double findSmallestMaxDist(int stations[], int k) {
        int n = stations.length;
        double s = 0, e = stations[n - 1] - stations[0];
        while ((e - s) > 1e-6) {
            double mid = (s + e) / 2.0;
            if (isPossible(stations, k, mid)) {
                e = mid;
            } else {
                s = mid;
            }
        }
        return e;
    }

    /**
     * Helper method to check if it is possible to add the required number of
     * additional stations such that the maximum distance between adjacent stations
     * is less than or equal to the given distance.
     * 
     * @param stations The input array of station positions.
     * @param k The number of additional stations to be added.
     * @param dist The maximum distance to check feasibility.
     * @return True if it is possible to add the required number of additional
     *         stations such that the maximum distance between adjacent stations is
     *         less than or equal to the given distance, otherwise false.
     */
    public static boolean isPossible(int stations[], int k, double dist) {
        int placed = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            int stationsAdded = (int) ((stations[i + 1] - stations[i]) / dist);
            placed += stationsAdded;
        }

        return (placed <= k);
    }
}