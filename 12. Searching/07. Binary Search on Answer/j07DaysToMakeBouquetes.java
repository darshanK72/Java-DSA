/**
 * Problem Statement:
 * 
 *     Given an array `bloomDay` representing the days on which each flower
 *     blooms, and integers `m` and `k`, find the minimum number of days
 *     required to make `m` bouquets, each containing consecuative `k` flowers.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `bloomDay` of size `n` where each element represents the day
 *       a flower blooms.
 *     - An integer `m` (1 <= m <= 10^4), representing the number of bouquets.
 *     - An integer `k` (1 <= k <= n), representing the number of flowers in
 *       each bouquet.
 * 
 * Output:
 *     - An integer representing the minimum number of days required to make
 *       `m` bouquets.
 * 
 * Example:
 *     Input:
 *     n = 7
 *     bloomDay = [1, 10, 3, 10, 2, 5, 7]
 *     m = 3
 *     k = 2
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The minimum number of days required to make 3 bouquets, each containing
 *     2 flowers, is 5 days.
 */

import java.util.Scanner;


public class j07DaysToMakeBouquetes {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of bloomDay
        int[] bloomDay = new int[n];

        // Reading the bloom days of each flower
        for (int i = 0; i < n; i++) {
            bloomDay[i] = in.nextInt();
        }
        int m = in.nextInt(); // Number of bouquets
        int k = in.nextInt(); // Number of flowers in each bouquet

        // Output the result of the approach
        System.out.println(minDays(bloomDay, m, k));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The minimum
     *   number of days required must be at least the minimum bloom day, and at
     *   most the maximum bloom day.
     * - We perform binary search within this range to find the minimum number of
     *   days that allows us to make the required number of bouquets.
     * 
     * Time Complexity:
     * - O(n log(max(bloomDay) - min(bloomDay))). This is because we perform binary
     *   search on the range [min(bloomDay), max(bloomDay)], and for each mid value,
     *   we iterate through the array to check if the number of days is feasible.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param bloomDay The input array of bloom days.
     * @param m The number of bouquets.
     * @param k The number of flowers in each bouquet.
     * @return The minimum number of days required to make the required number of
     *         bouquets.
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length)
            return -1;
        int s = 0;
        int e = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            s = Math.min(bloomDay[i], s);
            e = Math.max(bloomDay[i], e);
        }
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (isPossible(bloomDay, m, k, mid)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return s;
    }

    /**
     * Helper method to check if it is possible to make the required number of
     * bouquets within the given number of days.
     * 
     * @param bloomDay The input array of bloom days.
     * @param m The number of bouquets.
     * @param k The number of flowers in each bouquet.
     * @param day The number of days to check feasibility.
     * @return True if it is possible to make the required number of bouquets
     *         within the given number of days, otherwise false.
     */
    public static boolean isPossible(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0;
        int flowers = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= day) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }

        return (bouquets >= m);
    }
}