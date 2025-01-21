/**
 * Problem Statement:
 * 
 *     Given `n` stalls and `k` cows, place the cows in the stalls such that the
 *     minimum distance between any two cows is maximized.
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^5), representing the number of stalls.
 *     - An integer `k` (2 <= k <= n), representing the number of cows.
 *     - An array `stalls` of size `n` where each element represents the position
 *       of a stall.
 * 
 * Output:
 *     - An integer representing the largest minimum distance between any two cows.
 * 
 * Example:
 *     Input:
 *     n = 5
 *     k = 3
 *     stalls = [1, 2, 8, 4, 9]
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The largest minimum distance between any two cows is 3.
 */

import java.util.Arrays;
import java.util.Scanner; 

public class j06AggressiveCows {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of stalls
        int[] stalls = new int[n];

        // Reading the position of each stall
        for (int i = 0; i < n; i++) {
            stalls[i] = in.nextInt();
        }

        int k = in.nextInt(); // Number of cows

        // Output the result of the approach
        System.out.println(aggressiveCows(stalls, k));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The minimum
     *   distance between any two cows must be at least 1, and at most the distance
     *   between the farthest stalls.
     * - We perform binary search within this range to find the largest minimum
     *   distance that allows us to place all cows in the stalls.
     * 
     * Time Complexity:
     * - O(n log(max(stalls) - min(stalls))). This is because we perform binary
     *   search on the range [1, max(stalls) - min(stalls)], and for each mid value,
     *   we iterate through the array to check if the distance is feasible.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param stalls The input array of stall positions.
     * @param k The number of cows.
     * @return The largest minimum distance between any two cows.
     */
    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int minDist = 1;
        int maxDist = stalls[stalls.length - 1] - stalls[0];
        while (minDist <= maxDist) {
            int midDist = minDist + (maxDist - minDist) / 2;
            if (isPossible(stalls, k, midDist)) {
                minDist = midDist + 1;
            } else {
                maxDist = midDist - 1;
            }
        }
        return maxDist;
    }

    /**
     * Helper method to check if it is possible to place all cows in the stalls
     * with at least the given minimum distance.
     * 
     * @param stalls The input array of stall positions.
     * @param k The number of cows.
     * @param dist The minimum distance to check feasibility.
     * @return True if it is possible to place all cows with at least the given
     *         minimum distance, otherwise false.
     */
    public static boolean isPossible(int[] stalls, int k, int dist) {
        int count = 1;
        int lastPosition = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPosition >= dist) {
                count++;
                lastPosition = stalls[i];
                if (count == k) {
                    return true;
                }
            }
        }
        return false;
    }
}