import java.util.Scanner;

/**
 * Problem Statement:
 * 
 *     Given an array `candies` where `candies[i]` represents the number of
 *     candies in the i-th pile, and an integer `k` representing the number of
 *     children, find the maximum number of candies each child can get if we
 *     distribute the candies such that each child gets the same number of
 *     candies and we distribute as many candies as possible.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `candies` of size `n` where each element is a positive integer.
 *     - A long integer `k` (1 <= k <= 10^9), representing the number of children.
 * 
 * Output:
 *     - An integer representing the maximum number of candies each child can get.
 * 
 * Example:
 *     Input:
 *     n = 5
 *     candies = [5, 8, 6, 4, 7]
 *     k = 3
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The maximum number of candies each child can get is 5.
 */

public class j11MaxCandiesAllocatedToKChildren {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of elements in the array
        int[] candies = new int[n];

        for (int i = 0; i < n; i++) {
            candies[i] = in.nextInt();
        }
        long k = in.nextLong(); // Number of children

        // Output the result of the approach
        System.out.println(maximumCandies(candies, k));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The maximum
     *   number of candies each child can get must be at least 1, and at most the
     *   maximum number of candies in any pile.
     * - We perform binary search within this range to find the maximum number of
     *   candies each child can get.
     * 
     * Time Complexity:
     * - O(n log(max(candies))). This is because we perform binary search on the
     *   range [1, max(candies)], and for each mid value, we iterate through the
     *   array to calculate the number of children that can be satisfied.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param candies The input array of candies.
     * @param k The number of children.
     * @return The maximum number of candies each child can get.
     */
    public static int maximumCandies(int[] candies, long k) {
        long maxCandies = 0;
        for (int i = 0; i < candies.length; i++) {
            maxCandies = Math.max(maxCandies, candies[i]);
        }
        long min = 1;
        long max = maxCandies;
        while (min <= max) {
            long mid = min + (max - min) / 2;
            if (isPossible(candies, k, mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return (int) max;
    }

    /**
     * Helper method to check if it is possible to distribute the given number of
     * candies to the given number of children.
     * 
     * @param candies The input array of candies.
     * @param k The number of children.
     * @param mid The number of candies to check feasibility.
     * @return True if it is possible to distribute the given number of candies to
     *         the given number of children, otherwise false.
     */
    public static boolean isPossible(int[] candies, long k, long mid) {
        long count = 0;
        for (int i = 0; i < candies.length; i++) {
            count += candies[i] / mid;
        }
        return count >= k;
    }
}