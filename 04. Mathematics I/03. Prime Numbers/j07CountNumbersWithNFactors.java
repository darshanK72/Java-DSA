/**
 * Problem Statement:
 * 
 *     Given multiple queries, each consisting of a range `[s, e]` and a number `n`, find the count of numbers in the range `[s, e]` that have exactly `n` distinct prime factors.
 * 
 * Input:
 *     - An integer `q` (1 <= q <= 10^4), representing the number of queries.
 *     - A list of `q` queries, where each query consists of three integers: 
 *         - `s` (start of the range),
 *         - `e` (end of the range),
 *         - `n` (the exact number of distinct prime factors).
 * 
 * Output:
 *     - For each query, output the number of integers in the range `[s, e]` that have exactly `n` distinct prime factors.
 * 
 * Example:
 *     Input:
 *     2
 *     1 10 2
 *     10 20 3
 *     Output:
 *     4
 *     2
 * 
 *     Explanation:
 *     For the first query, the numbers with exactly 2 distinct prime factors in the range [1, 10] are [6, 10, 14, 15].
 *     For the second query, the numbers with exactly 3 distinct prime factors in the range [10, 20] are [30, 42].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j07CountNumbersWithNFactors {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);

        // Read the number of queries
        int q = in.nextInt();
        ArrayList<ArrayList<Integer>> quries = new ArrayList<>();

        // Read each query and store it in the queries list
        for (int i = 1; i <= q; i++) {
            int s = in.nextInt(); // Start of the range
            int e = in.nextInt(); // End of the range
            quries.add(new ArrayList<>(Arrays.asList(s, e)));
        }

        // Call the solution and print the result
        System.out.println(getNfactor(quries));

        in.close(); // Close the scanner to release resources
    }

    /**
     * Approach: Iterating Over Each Range and Counting Numbers with Exact Prime Factors
     * 
     * Intuition:
     * - For each query, iterate over the range [s, e] and check how many numbers in this range have exactly `n` distinct prime factors.
     * - The function `isNUniqueFactors` checks whether a given number has exactly `n` distinct prime factors.
     * 
     * Time Complexity:
     * - O(q * (e - s) * log x) where q is the number of queries, and x is the number being checked for distinct prime factors. This is due to the loop checking for divisibility up to the number itself.
     * 
     * Space Complexity:
     * - O(q) for storing the queries.
     * 
     * @param queries The list of queries where each query contains [s, e, n].
     * @return A list of results where each entry corresponds to the result of the respective query.
     */
    public static ArrayList<Integer> getNfactor(ArrayList<ArrayList<Integer>> queries) {
        ArrayList<Integer> output = new ArrayList<>();
        for (ArrayList<Integer> query : queries) {
            int c = 0;
            // Iterate through each number in the range [s, e] and check if it has exactly
            // `n` distinct prime factors
            for (int i = query.get(0); i <= query.get(1); i++) {
                if (isNUniqueFactors(i, query.get(2))) {
                    c++;
                }
            }
            output.add(c); // Store the result for the query
        }
        return output; // Return the list of results for all queries
    }

    /**
     * Helper function to check if a number has exactly `n` distinct prime factors.
     * 
     * Intuition:
     * - For a given number `x`, we divide it by each prime factor starting from 2. If a number divides `x`, it's a prime factor.
     * - Count the number of distinct prime factors. If the count matches `n`, return true, otherwise false.
     * 
     * Time Complexity:
     * - O(log x) where x is the number being checked, as we divide `x` by its factors.
     * 
     * Space Complexity:
     * - O(1) as we only use a few variables for counting and checking divisibility.
     * 
     * @param x The number to check for distinct prime factors.
     * @param n The exact number of distinct prime factors we want.
     * @return true if `x` has exactly `n` distinct prime factors, otherwise false.
     */
    public static boolean isNUniqueFactors(int x, int n) {
        int c = 0; // Counter for distinct prime factors
        for (int i = 2; i <= x; i++) {
            if (x % i == 0) { // If `i` is a factor of `x`
                c++; // Increment the counter for distinct prime factors
                while (x % i == 0) { // Remove all factors of `i`
                    x /= i;
                }
            }
            if (c > n) {
                return false; // If the number of distinct prime factors exceeds `n`, return false
            }
        }
        return c == n; // Return true if exactly `n` distinct prime factors are found
    }
}
