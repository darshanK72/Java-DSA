/**
 * Problem Statement:
 * 
 *     Given a list of song durations `time`, return the number of pairs of songs 
 *     where the total duration in each pair is divisible by 60. A pair `(i, j)` 
 *     is valid if `i < j`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the number of songs.
 *     - An array `time` of size `n` where each element satisfies (1 <= time[i] <= 500).
 * 
 * Output:
 *     - An integer representing the number of pairs whose total duration is divisible by 60.
 * 
 * Example:
 *     Input:
 *     5
 *     30 20 150 100 40
 * 
 *     Output:
 *     3
 * 
 *     Explanation:
 *     - The valid pairs are: (30, 150), (20, 100), and (30, 40).
 *       In each case, the sum of durations is divisible by 60.
 */

import java.util.Scanner;

public class j21PairSumDivisibleBy60 {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of songs
        int[] time = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = in.nextInt(); // Input: duration of each song
        }

        // Output result
        System.out.println("Number of Pairs Divisible by 60: " + countPairsDivisibleBy60(time));

        in.close();
    }

    /**
     * Approach: Hashing with Remainders
     * 
     * Intuition:
     * - For any song duration `t`, we calculate its remainder when divided by 60 (`t % 60`).
     * - To form a valid pair where the sum is divisible by 60, we need to find a complement 
     *   such that `(remainder + complement) % 60 == 0`.
     *   - If the remainder is 0, the complement is also 0.
     *   - Otherwise, the complement is `60 - remainder`.
     * - We maintain an array `count[]` of size 60 to keep track of the frequency of each remainder.
     * - As we iterate through the array, we check how many complements exist for the current song.
     *   - Add the count of complements to the result.
     * - Update the remainder frequency in the array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We iterate through the array once.
     * 
     * Space Complexity:
     * - O(60) ~ O(1), as we use a fixed-size array to store remainder frequencies.
     * 
     * @param time The input array containing song durations.
     * @return The number of pairs whose sum is divisible by 60.
     */
    public static int countPairsDivisibleBy60(int[] time) {
        int[] count = new int[60]; // Array to store remainder frequencies
        int result = 0; // Initialize the count of valid pairs

        for (int t : time) {
            int remainder = t % 60; // Calculate remainder
            int complement = (remainder == 0) ? 0 : 60 - remainder; // Find complement remainder
            result += count[complement]; // Add the count of complements found so far
            count[remainder]++; // Update the remainder frequency
        }

        return result;
    }
}
