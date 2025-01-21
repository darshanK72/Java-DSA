/*-
 * Problem Statement:
 * 
 *     Given an array of weights and a number of days, find the minimum capacity
 *     of a ship to ship all the packages within the given days.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `weights` of size `n` where each element satisfies (1 <=
 *       weights[i] <= 10^5).
 *     - An integer `days` (1 <= days <= n), representing the number of days
 *       within which all packages must be shipped.
 * 
 * Output:
 *     - An integer representing the minimum capacity of the ship required to
 *       ship all packages within the given days.
 * 
 * Example:
 *     Input:
 *     weights = [1,2,3,4,5,6,7,8,9,10]
 *     days = 5
 *     Output:
 *     15
 * 
 *     Explanation:
 *     The minimum capacity of the ship required to ship all packages within 5
 *     days is 15.
 */

import java.util.Scanner;


public class j04CapacityToShipPackages {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt(); // Input: elements of the array
        }
        int days = in.nextInt(); // Input: number of days

        // Call your solution
        System.out.printf("Minimum Capacity of Ship: %d\n", shipWithinDays(weights, days));

        in.close();
    }

    /*-
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The minimum
     *   capacity of the ship must be at least the maximum weight in the array, and
     *   at most the sum of all weights.
     * - We perform binary search within this range to find the minimum capacity
     *   that allows us to ship all packages within the given days.
     * 
     * Time Complexity:
     * - O(n log(sum(weights) - max(weights))). This is because we perform binary
     *   search on the range [max(weights), sum(weights)], and for each mid value,
     *   we iterate through the array to check if the capacity is feasible.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param weights The input array of weights.
     * @param days The number of days within which all packages must be shipped.
     * @return The minimum capacity of the ship required to ship all packages
     *         within the given days.
     */
    public static int shipWithinDays(int[] weights, int days) {
        long minWeight = 0, maxWeight = 0;
        for (int weight : weights) {
            minWeight = Math.max(minWeight, weight);
            maxWeight += weight;
        }

        while (minWeight < maxWeight) {
            long mid = (minWeight + maxWeight) / 2;
            if (isPossible(weights, days, mid)) {
                maxWeight = mid - 1;
            } else {
                minWeight = mid + 1;
            }
        }
        return (int) minWeight;
    }

    /**
     * Helper method to check if it is possible to ship all packages within the
     * given days with the given ship capacity.
     * 
     * @param weights The input array of weights.
     * @param days    The number of days within which all packages must be shipped.
     * @param weight  The capacity of the ship.
     * @return True if it is possible to ship all packages within the given days
     *         with the given ship capacity, otherwise false.
     */
    public static boolean isPossible(int[] weights, int days, long weight) {
        int daysRequired = 1;
        int currentWeight = 0;
        for (int i = 0; i < weights.length; i++) {
            if (currentWeight + weights[i] <= weight) {
                currentWeight += weights[i];
            } else {
                daysRequired++;
                currentWeight = weights[i];
            }
        }

        return (daysRequired <= days);
    }
}