/**
 * Problem Statement:
 * 
 *     Given an array of integers `arr` and an integer `k`, check if it is possible to 
 *     pair all elements in the array such that the sum of each pair is divisible by `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^9 <= arr[i] <= 10^9).
 *     - An integer `k` (1 <= k <= 10^9).
 * 
 * Output:
 *     - A boolean value `true` if it is possible to pair the elements as described, or `false` otherwise.
 * 
 * Example:
 *     Input:
 *     4
 *     9 7 5 3
 *     6
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - Pairs can be formed as (9, 3) and (7, 5). The sums are 12 and 12, both divisible by 6.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j05CheckPairsDivisibleByK {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: divisor k

        // Call the solution
        System.out.println(checkPairsDivisibleByK(arr, k));

        in.close();
    }

    /**
     * Approach: Frequency Mapping and Remainder Matching
     * 
     * Intuition:
     * - For two numbers to form a pair whose sum is divisible by `k`, the remainders of these 
     *   numbers when divided by `k` must satisfy certain conditions:
     *   1. If the remainder of one number is `r`, the remainder of the other must be `k - r`.
     *   2. Special case: If the remainder is 0, the frequency of such numbers must be even, as 
     *      they can only pair among themselves.
     * 
     * Explanation:
     * - First, calculate the remainder of each element in the array when divided by `k` and 
     *   count the frequency of each remainder using a HashMap.
     * - Iterate through the array again and check:
     *   1. For elements with a remainder of 0, their frequency must be even.
     *   2. For elements with a remainder `r`, their frequency must match the frequency of elements
     *      with a remainder of `k - r`.
     * - If any condition is violated, return `false`.
     * 
     * Time Complexity:
     * - O(N), where `N` is the size of the array. We iterate through the array twice.
     * 
     * Space Complexity:
     * - O(K), where `K` is the divisor, as we store frequencies of at most `K` remainders.
     * 
     * @param arr The input array of integers.
     * @param k The divisor.
     * @return `true` if pairs can be formed as described; `false` otherwise.
     */
    public static boolean checkPairsDivisibleByK(int[] arr, int k) {
        // Map to store frequencies of remainders
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            // Calculate remainder and normalize it to a positive value
            int rem = (arr[i] % k + k) % k;
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            int rem = (arr[i] % k + k) % k;

            if (rem == 0) {
                // Special case: remainder is 0
                if (map.get(rem) % 2 != 0) {
                    return false;
                }
            } else {
                // Check if frequencies of `rem` and `k - rem` match
                int count1 = map.get(rem);
                int count2 = map.getOrDefault(k - rem, 0);
                if (count1 != count2) {
                    return false;
                }
            }
        }

        // All conditions satisfied, return true
        return true;
    }
}
