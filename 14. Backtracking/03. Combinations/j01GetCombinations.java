/**
 * Problem Statement:
 * 
 *     Given an integer `n`, representing the size of a set {1, 2, ..., n}, and an integer `k`, 
 *     generate all possible combinations of `k` numbers chosen from the set.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the set.
 *     - An integer `k` (1 <= k <= n), representing the number of elements in each combination.
 * 
 * Output:
 *     - A list of lists, where each list contains a valid combination of `k` elements from {1, 2, ..., n}.
 * 
 * Example:
 *     Input:
 *     4
 *     2
 * 
 *     Output:
 *     [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
 * 
 *     Explanation:
 *     - The combinations of 2 elements chosen from {1, 2, 3, 4} are listed above.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j01GetCombinations {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        List<List<Integer>> set1 = new ArrayList<>();
        generateCombinations1(n, k, new ArrayList<>(), set1);
        System.out.println("Approach 1 Output: " + set1);

        List<List<Integer>> set2 = new ArrayList<>();
        generateCombinations2(1, n, k, new ArrayList<>(), set2);
        System.out.println("Approach 2 Output: " + set2);

        in.close();
    }

    /**
     * Approach 1: Backtracking using Decrementing `n`
     * 
     * Intuition:
     * - We explore combinations by either including or excluding the current number `n`.
     * - If we include `n`, we reduce `k` and recurse with `n-1`.
     * - If we exclude `n`, we simply recurse with `n-1`.
     * 
     * Explanation:
     * - If `k == 0`, we have selected `k` numbers and add the combination to the result.
     * - If `n == 0` or `k > n`, we stop as no valid combination exists.
     * - We make two recursive calls:
     *   1. Include `n` in the combination and recurse with `n-1, k-1`.
     *   2. Exclude `n` and recurse with `n-1, k`.
     * 
     * Time Complexity:
     * - O(2^n) in the worst case.
     * 
     * Space Complexity:
     * - O(k) due to recursion depth.
     */
    public static void generateCombinations1(int n, int k, ArrayList<Integer> curr, List<List<Integer>> set) {
        if (k == 0) {
            set.add(new ArrayList<>(curr));
            return;
        }
        if (n == 0 || k > n) {
            return;
        }

        // Include `n`
        curr.add(n);
        generateCombinations1(n - 1, k - 1, curr, set);
        curr.remove(curr.size() - 1);

        // Exclude `n`
        generateCombinations1(n - 1, k, curr, set);
    }

    /**
     * Approach 2: Backtracking using Increasing Start Index
     * 
     * Intuition:
     * - We start from `1` and generate combinations by choosing `k` elements.
     * - This ensures that we only move forward, preventing duplicate combinations.
     * 
     * Explanation:
     * - If `k == 0`, we have found a valid combination.
     * - We iterate from `s` to `n`, adding each number to the current combination.
     * - We recursively explore the next numbers with `i+1` to maintain order.
     * - After recursion, we remove the last element to backtrack.
     * 
     * Time Complexity:
     * - O(C(n, k)) = O(n! / (k!(n-k)!)) (binomial coefficient).
     * 
     * Space Complexity:
     * - O(k) due to recursion depth.
     */
    public static void generateCombinations2(int s, int n, int k, ArrayList<Integer> curr, List<List<Integer>> set) {
        if (k == 0) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = s; i <= n; i++) {
            curr.add(i);
            generateCombinations2(i + 1, n, k - 1, curr, set);
            curr.remove(curr.size() - 1);
        }
    }
}
