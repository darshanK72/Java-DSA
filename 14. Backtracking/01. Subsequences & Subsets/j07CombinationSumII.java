/*-
 * Problem Statement:
 * 
 *      Given a collection of candidate numbers (`candidates`) and a target number (`target`), 
 *      find all unique combinations in `candidates` where the candidate numbers sum to `target`. 
 *      Each number in `candidates` may only be used once in the combination.
 * 
 * Note: The solution set must not contain duplicate combinations.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100), representing the size of the array.
 *     - An array `candidates` of size `n` where each element satisfies (1 <= candidates[i] <= 50).
 *     - An integer `target` (1 <= target <= 30).
 * 
 * Output:
 *     - A list of all unique combinations of `candidates` where the numbers sum to `target`. 
 *       Each combination should be a list of integers.
 * 
 * Example:
 *     Input:
 *         4
 *         10 1 2 7 6 1 5
 *         8
 *     Output:
 *         [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
 * 
 *     Explanation:
 *         The unique combinations where the numbers sum to 8 are:
 *         - [1, 1, 6]
 *         - [1, 2, 5]
 *         - [1, 7]
 *         - [2, 6]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class j07CombinationSumII {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int target = in.nextInt();

        System.out.println(combinationSum2(arr, target));

        in.close();
    }

    /*-
     * Approach: Backtracking with Sorting
     * 
     * Intuition:
     * - The problem requires us to find all unique combinations where numbers sum up to a given target.
     * - Since numbers can be repeated in the input but must be used at most once per combination, 
     *   we need to be careful to avoid duplicate combinations.
     * - Sorting the input array helps in two ways:
     *     1. It allows us to efficiently skip duplicate elements and avoid redundant work.
     *     2. It ensures that when we explore candidates, we do so in a structured order.
     * - We explore possible solutions using **backtracking**, which is a depth-first approach to 
     *   generate all possible valid subsets.
     * - At each step:
     *     - We include a number in our combination and recursively try to reach the target.
     *     - We backtrack by removing the last added number to explore other possibilities.
     *     - We skip duplicate numbers to ensure uniqueness in the result set.
     * 
     * Explanation:
     * - We start by sorting the `candidates` array.
     * - We use a helper method (`backtrack`) to explore all possible combinations:
     *     1. If `target == 0`, we found a valid combination, so we add it to the result list.
     *     2. We iterate through the remaining numbers in the sorted array.
     *     3. If a number is greater than `target`, we break early (as adding it would exceed the sum).
     *     4. If a number is the same as the previous one, we skip it to avoid duplicates.
     *     5. We include the number in the current combination and recursively explore further.
     *     6. We backtrack by removing the last added number and try the next possibility.
     * 
     * Time Complexity:
     * - Sorting takes **O(n log n)**.
     * - The backtracking process explores subsets, leading to a worst-case time complexity of **O(2^n)**.
     * - Overall, the complexity is **O(n log n + 2^n)**.
     * 
     * Space Complexity:
     * - **O(n)** due to the recursion stack and space used to store the current combination.
     * 
     * @param candidates The input array of candidate numbers.
     * @param target The target sum for the combinations.
     * @return A list of all unique combinations that sum up to the target.
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // Sort to handle duplicates efficiently
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /*-
     * Backtracking Method
     * 
     * Intuition:
     * - This method recursively explores all possible subsets that can sum up to the target.
     * - The `start` index ensures that we do not reuse the same element.
     * - We maintain a `current` list to track the combination we are currently forming.
     * - We check for duplicate elements by skipping repeated numbers.
     * - The recursive call explores deeper combinations, and when a valid one is found, 
     *   it is added to the result list.
     * 
     * Explanation:
     * - Base Case:
     *     - If `target == 0`, we found a valid combination. We add it to `result` and return.
     * - Loop through the sorted array:
     *     - If `i > start` and `candidates[i] == candidates[i - 1]`, skip this element to avoid duplicates.
     *     - If `candidates[i] > target`, break the loop (as further elements will also be too large).
     *     - Otherwise, add `candidates[i]` to `current` and recursively call the method with updated target.
     *     - After returning from recursion, remove the last added element (`backtracking` step).
     * 
     * Time Complexity:
     * - The backtracking process is **O(2^n)** in the worst case, as we explore all subsets.
     * 
     * Space Complexity:
     * - **O(n)** due to recursion stack and storage for the current combination.
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
            // Skip duplicate elements to avoid duplicate combinations
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            // If the element is greater than the remaining target, stop exploring further
            if (candidates[i] > target) {
                break;
            }
            // Include the current element and explore further
            current.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, current, result);
            // Backtrack: remove the last element to try new possibilities
            current.remove(current.size() - 1);
        }
    }
}
