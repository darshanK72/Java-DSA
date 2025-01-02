/**
 * Problem Statement:
 *
 *     Koko loves bananas, and she has `n` piles of bananas in front of her. Each pile has a certain number of bananas, 
 *     and she needs to eat all the bananas within `h` hours. Koko can eat at a constant rate of `k` bananas per hour 
 *     from any pile. If a pile has more bananas than Koko can eat in one hour, she needs to continue eating the same pile 
 *     in the next hour.
 *
 *     Find the minimum integer `k` (Koko's eating speed in bananas per hour) such that Koko can eat all the bananas 
 *     within `h` hours.
 *
 * Input:
 *     - An integer `n` representing the number of piles.
 *     - An array `piles` of size `n`, where each element represents the number of bananas in a pile.
 *     - An integer `h`, representing the number of hours available for Koko to eat all bananas.
 * 
 * Output:
 *     - Return the minimum integer value of `k` such that Koko can finish the bananas in `h` hours.
 *
 * Example:
 *     Input:
 *     4
 *     3 6 7 11
 *     8
 *     Output:
 *     4
 *
 *     Explanation:
 *     Koko can eat at a speed of 4 bananas per hour, and she will finish in 8 hours.
 */

import java.util.Scanner;

public class j01KokoEatingBananas {
    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of piles
        int[] arr = new int[n];

        // Reading the number of bananas in each pile
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int hours = in.nextInt(); // Available hours for eating bananas

        // Output the result of both approaches
        System.out.println(minEatingSpeed(arr, hours));
        System.out.println(minEatingSpeedEfficient(arr, hours));

        // Closing the input scanner
        in.close();
    }

    /**
     * Brute force approach to find the minimum eating speed.
     * We try each possible speed from 1 to a very large value, checking if Koko can finish all the bananas in `h` hours.
     *
     * @param piles Array of banana piles.
     * @param h Number of available hours.
     * @return The minimum speed at which Koko can eat the bananas in `h` hours.
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int max = (int) 1e9; // The maximum possible eating speed

        // Try all possible speeds from 1 to max
        for (int ans = 1; ans <= max; ans++) {
            if (isPossible(piles, ans, h)) {
                return ans; // If it's possible to finish in `h` hours with this speed, return this speed
            }
        }

        return -1; // If no such speed exists
    }

    /**
     * Optimized approach using binary search to find the minimum eating speed.
     * The idea is to use binary search on possible eating speeds between 1 and the maximum pile size.
     *
     * @param piles Array of banana piles.
     * @param h Number of available hours.
     * @return The minimum speed at which Koko can eat the bananas in `h` hours.
     */
    public static int minEatingSpeedEfficient(int[] piles, int h) {
        int max = piles[0];

        // Find the maximum number of bananas in a pile
        for (int pile : piles) {
            if (pile > max) {
                max = pile; // Update the maximum pile size
            }
        }

        int min = 1; // Minimum speed is 1 banana per hour

        // Perform binary search on possible speeds
        while (min <= max) {
            int mid = min + (max - min) / 2; // Middle speed

            // Check if it's possible to finish the bananas with this speed
            boolean isPossible = isPossible(piles, mid, h);

            if (isPossible) {
                max = mid - 1; // Try a smaller speed
            } else {
                min = mid + 1; // Try a larger speed
            }
        }

        return min; // The minimum speed at which Koko can finish the bananas
    }

    /**
     * Helper function to check if Koko can finish all bananas with a given eating speed.
     *
     * @param piles Array of banana piles.
     * @param speed The eating speed (bananas per hour).
     * @param hours Number of hours available.
     * @return True if Koko can finish all bananas in `hours` hours at the given speed, otherwise false.
     */
    public static boolean isPossible(int[] piles, int speed, int hours) {
        long ans = 0; // To store the total hours needed

        // Calculate the total hours needed to eat all the bananas
        for (int i = 0; i < piles.length; i++) {
            ans += (piles[i] / speed); // Full hours spent on this pile

            // If there are remaining bananas, an additional hour is required
            if (piles[i] % speed != 0) {
                ans++;
            }
        }

        // Return true if the total hours needed is less than or equal to the available
        // hours
        return (ans <= hours);
    }
}
