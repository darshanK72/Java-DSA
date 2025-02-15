/**
 * Problem Statement:
 * 
 *      Given an array of distinct integers `candidates` and a target integer `target`,
 *      return a list of all unique combinations of `candidates` where the chosen numbers
 *      sum to `target`. You may return the combinations in any order. The same number
 *      may be chosen from `candidates` an unlimited number of times. Two combinations
 *      are unique if the frequency of at least one of the chosen numbers is different.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 30), representing the size of the array.
 *     - An array `candidates` of size `n` where each element satisfies (1 <= candidates[i] <= 200).
 *     - An integer `target` (1 <= target <= 500).
 * 
 * Output:
 *     - A list of all unique combinations of `candidates` where the numbers sum to `target`.
 *       Each combination should be a list of integers.
 * 
 * Example:
 *     Input:
 *         4
 *         2 3 6 7
 *         7
 *     Output:
 *         [[2, 2, 3], [7]]
 * 
 *     Explanation:
 *         The unique combinations where the numbers sum to 7 are:
 *         - [2, 2, 3]
 *         - [7]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class j08CombinationSumI {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();

        System.out.println(combinationSum(arr, target));

        in.close();
    }

    /**
     * Approach: Backtracking with Pruning
     * 
     * Intuition:
     * - The problem requires finding all unique combinations of numbers that sum up to a given target.
     * - Since each number can be used multiple times, we can explore each number as many times as needed.
     * - We use backtracking to explore all possible combinations. At each step, we have two choices:
     *     1. Include the current number and continue exploring with the same index (allowing repetition).
     *     2. Exclude the current number and move to the next index.
     * - To avoid unnecessary computations:
     *     - If the current sum exceeds the target, we stop exploring further.
     *     - If the current sum equals the target, we add the current combination to the result.
     * - Sorting the array helps in early termination of loops when the remaining numbers are too large.
     * 
     * Time Complexity:
     * - Sorting the array takes O(n log n).
     * - The backtracking process explores potential combinations, leading to a time complexity of O(2^t),
     *   where t is the target value. This is because, in the worst case, we might explore all combinations
     *   of numbers that sum up to the target.
     * - Overall, the complexity is O(n log n + 2^t).
     * 
     * Space Complexity:
     * - O(t) due to the recursion stack and space used to store the current combination.
     * 
     * @param candidates The input array of candidate numbers.
     * @param target The target sum for the combinations.
     * @return A list of all unique combinations that sum up to the target.
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // Sort to facilitate early termination
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking Method
     * 
     * Intuition:
     * - This method recursively explores all possible subsets that can sum up to the target.
     * - The `start` index ensures that we do not reuse the same element in the same position.
     * - We maintain a `current` list to track the combination we are currently forming.
     * - The recursive call explores deeper combinations, and when a valid one is found,
     *   it is added to the result list.
     * 
     * Explanation:
     * - Base Case:
     *     - If `target == 0`, we found a valid combination. We add it to `result` and return.
     * - Loop through the sorted array starting from `start` index:
     *     - If `candidates[i] > target`, break the loop (as further elements will also be too large).
     *     - Otherwise, add `candidates[i]` to `current` and recursively call the method with updated target.
     *     - After returning from recursion, remove the last added element (`backtracking` step).
     * 
     * Time Complexity:
     * - The backtracking process is O(2^t) in the worst case, as we explore all subsets that sum up to the target.
     * 
     * Space Complexity:
     * - O(t) due to recursion stack and storage for the current combination.
     * 
     * @param candidates The input array of candidate numbers.
     * @param target The target sum.
     * @param start The index from which to consider elements.
     * @param current The current subset being formed.
     * @param result The list storing all valid unique subsets.
     */
    private static void backtrack(int[] candidates, int target, int start, List<Integer> current,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current)); // Found a valid combination
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // If the element is greater than the remaining target, stop exploring further
            if (candidates[i] > target) {
                break;
            }
            // Include the current element and explore further
            current.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, current, result);
            // Backtrack: remove the last element to try new possibilities
            current.remove(current.size() - 1);
        }
    }
}
