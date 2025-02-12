/*-
 * Problem Statement:
 * 
 *      Find all valid combinations of `k` numbers that sum up to `n` such that the following conditions are true:
 *      - Only numbers 1 through 9 are used.
 *      - Each number is used at most once.
 * 
 *      Return a list of all possible valid combinations. The list must not contain the same combination twice,
 *      and the combinations may be returned in any order.
 * 
 * Input:
 *     - An integer `k` (1 <= k <= 9), representing the number of numbers to be used in each combination.
 *     - An integer `n` (1 <= n <= 45), representing the target sum.
 * 
 * Output:
 *     - A list of all unique combinations of `k` numbers that sum to `n`.
 * 
 * Example:
 *     Input:
 *         3
 *         7
 *     Output:
 *         [[1, 2, 4]]
 * 
 *     Explanation:
 *         The only combination of 3 numbers that add up to 7 is [1, 2, 4].
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j09CombinationsSumIII {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int n = in.nextInt();

        System.out.println(combinationSum3(k, n));

        in.close();
    }

    /*-
     * Approach: Backtracking
     * 
     * Intuition:
     * - The problem requires finding all unique combinations of `k` distinct numbers (from 1 to 9) that sum up to `n`.
     * - We can use backtracking to explore all possible combinations. At each step, we have two choices:
     *     1. Include the current number in the combination and move to the next number.
     *     2. Exclude the current number and move to the next number.
     * - To ensure each number is used at most once and to avoid duplicate combinations, we:
     *     - Start the next recursive call with the next number.
     *     - Maintain a list of current numbers in the combination.
     * - If the current combination's length equals `k` and its sum equals `n`, we add it to the result list.
     * - If the sum exceeds `n` or the combination's length exceeds `k`, we backtrack.
     * 
     * Time Complexity:
     * - The backtracking process explores potential combinations, leading to a time complexity of O(2^9),
     *   since there are 9 numbers (1 through 9) and each can either be included or excluded.
     * 
     * Space Complexity:
     * - O(k) due to the recursion stack and space used to store the current combination.
     * 
     * @param k The number of numbers to be used in each combination.
     * @param n The target sum for the combinations.
     * @return A list of all unique combinations that sum up to the target.
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    /*-
     * Backtracking Method
     * 
     * Intuition:
     * - This method recursively explores all possible subsets of numbers from 1 to 9 that can sum up to `n`.
     * - The `start` parameter ensures that we do not reuse the same element in the same position.
     * - We maintain a `current` list to track the combination we are currently forming.
     * - The recursive call explores deeper combinations, and when a valid one is found,
     *   it is added to the result list.
     * 
     * Explanation:
     * - Base Case:
     *     - If `n == 0` and `k == 0`, we found a valid combination. We add it to `result` and return.
     * - Loop through numbers from `start` to 9:
     *     - If the current number is greater than `n`, break the loop (as further numbers will also be too large).
     *     - Otherwise, add the current number to `current` and recursively call the method with updated `k` and `n`.
     *     - After returning from recursion, remove the last added element (`backtracking` step).
     * 
     * Time Complexity:
     * - The backtracking process is O(2^9) in the worst case, as we explore all subsets of numbers from 1 to 9.
     * 
     * Space Complexity:
     * - O(k) due to recursion stack and storage for the current combination.
     * 
     * @param k The number of numbers to be used in each combination.
     * @param n The target sum.
     * @param start The starting number for the current combination.
     * @param current The current subset being formed.
     * @param result The list storing all valid unique subsets.
     */
    private static void backtrack(int k, int n, int start, List<Integer> current, List<List<Integer>> result) {
        if (n == 0 && k == 0) {
            result.add(new ArrayList<>(current)); // Found a valid combination
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i > n) {
                break; // No need to continue if the current number exceeds the remaining sum
            }
            current.add(i);
            backtrack(k - 1, n - i, i + 1, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}
