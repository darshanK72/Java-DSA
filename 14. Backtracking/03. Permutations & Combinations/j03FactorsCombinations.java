/**
 * Problem Statement:
 * 
 *     Given an integer `n`, generate all possible unique combinations of its factors 
 *     (excluding 1 and `n` itself) such that the product of numbers in each combination 
 *     equals `n`. The order of numbers in each combination should be non-decreasing.
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^6), representing the number to be factorized.
 * 
 * Output:
 *     - A list of lists, where each list contains a valid factor combination of `n`.
 * 
 * Example:
 *     Input:
 *     12
 * 
 *     Output:
 *     [[2, 2, 3], [2, 6], [3, 4]]
 * 
 *     Explanation:
 *     - 12 can be factorized as:
 *       - 2 × 2 × 3
 *       - 2 × 6
 *       - 3 × 4
 *     - The factors in each combination are in non-decreasing order.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class j03FactorsCombinations {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Generating factor combinations
        ArrayList<ArrayList<Integer>> set = new ArrayList<>();
        generateFactorCombinations(n, 2, new ArrayList<>(), set);

        // Sorting to maintain lexicographical order
        Collections.sort(set, (a, b) -> {
            int minSize = Math.min(a.size(), b.size());
            for (int i = 0; i < minSize; i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return Integer.compare(a.get(i), b.get(i));
                }
            }
            return Integer.compare(a.size(), b.size());
        });
        
        // Printing the result
        System.out.println(set);

        in.close();
    }

    /**
     * Helper Function: Backtracking with Factorization
     * 
     *  Intuition:
     * - We generate factor combinations by recursively dividing `n` into its factors.
     * - Start dividing from `2` (smallest possible factor) to ensure non-decreasing order.
     * - When a valid factorization is found, store it in the result list.
     * - Ensure that factors are in sorted order to prevent duplicate sequences.
     * 
     * Explanation:
     * - Start with `n` and a factor `start = 2`.
     * - Iterate over possible factors from `start` to `n / i`:
     *   1. If `i` is a factor of `n`, add `i` to the current list.
     *   2. Recursively compute factors of `n / i`, ensuring factors remain in non-decreasing order.
     *   3. Remove the last added factor to explore other possibilities (backtracking).
     * - If we reach a valid decomposition (i.e., remaining value is >1 and we have factors), 
     *   we add the current sequence to the result.
     * - The sorting step ensures the result follows lexicographical order.
     * 
     * Time Complexity:
     * - O(2^(log n)) in the worst case (similar to the number of divisors of `n`).
     * 
     * Space Complexity:
     * - O(log n) due to recursive call stack.
     * 
     * - Recursively finds factors of `n` while ensuring a non-decreasing order.
     * - If a valid decomposition is found, it is stored in `set`.
     */
    public static void generateFactorCombinations(int n, int start, ArrayList<Integer> factors,
            ArrayList<ArrayList<Integer>> set) {
        if (!factors.isEmpty()) {
            factors.add(n);
            set.add(new ArrayList<>(factors));
            factors.remove(factors.size() - 1);
        }

        for (int i = start; i <= n / i; i++) {
            if (n % i == 0) {
                factors.add(i);
                generateFactorCombinations(n / i, i, factors, set);
                factors.remove(factors.size() - 1);
            }
        }
    }
}
