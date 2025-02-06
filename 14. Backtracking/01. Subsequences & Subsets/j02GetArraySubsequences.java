/**
 * Problem Statement:
 * 
 *     Given an array of integers, return all possible subsequences of the array.
 *     A subsequence is a sequence derived by deleting some or no elements from 
 *     the original array without changing the order of the remaining elements.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 20), representing the size of the array.
 *     - An array `arr` of size `n`, where each element satisfies (-10^3 <= arr[i] <= 10^3).
 * 
 * Output:
 *     - A list of all possible subsequences of `arr`.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     Output:
 *     [[], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]]
 * 
 *     Explanation:
 *     The possible subsequences of [1, 2, 3] include the full array, single elements,
 *     and all valid combinations of its elements in order.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j02GetArraySubsequences {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Approach 1: Print subsequences
        printArraySubsequences(arr, 0, new ArrayList<>());

        // Approach 2: Store subsequences and return
        System.out.println(getArraySubsequences(arr, 0, new ArrayList<>()));

        in.close();
    }

    /**
     * Approach 1: Recursive Generation (Print Only)
     * 
     * Intuition:
     * - The idea is to generate subsequences by making two choices for each element:
     *   1. Include the current element in the subsequence.
     *   2. Exclude the current element from the subsequence.
     * - This follows a binary decision tree where at each step, we either pick
     *   or skip the element.
     * 
     * Explanation:
     * - Consider the input `[1, 2]`:
     *   - Start with an empty list `[]`.
     *   - Include `1`: `[1]`, Exclude `1`: `[]`
     *   - Include `2`: `[1, 2]`, `[1]`, `[2]`, `[]`
     * 
     * Execution tree:
     * 
     *                  []
     *                /    \
     *             [1]      []
     *            /   \    /   \
     *       [1,2]   [1]  [2]   []
     * 
     * Time Complexity:
     * - O(2^n), since for each element we make two recursive calls.
     * 
     * Space Complexity:
     * - O(n), due to recursive call stack depth.
     * 
     * @param arr The input array.
     * @param index The current index in the array.
     * @param set The current subsequence being formed.
     */
    public static void printArraySubsequences(int[] arr, int index, ArrayList<Integer> set) {
        if (index == arr.length) {
            System.out.println(set);
            return;
        }
        set.add(arr[index]);
        printArraySubsequences(arr, index + 1, set);
        set.remove(set.size() - 1);
        printArraySubsequences(arr, index + 1, set);
    }

    /**
     * Approach 2: Recursive Generation (Return List)
     * 
     * Intuition:
     * - Similar to Approach 1, but instead of printing, we store results in a list.
     * - The recursion structure remains the same:
     *   1. Include the current element in subsequences.
     *   2. Exclude the current element from subsequences.
     * - The base case occurs when the index reaches the end of the array.
     * 
     * Explanation:
     * - Consider input `[1, 2]`:
     *   - Subsequences of `[2]`: `[[2], []]`
     *   - Adding `1` results in: `[[1, 2], [1], [2], []]`
     * 
     * Time Complexity:
     * - O(2^n), as each element leads to two recursive calls.
     * 
     * Space Complexity:
     * - O(2^n), since we store all subsequences.
     * 
     * @param arr The input array.
     * @param index The current index in the array.
     * @param set The current subsequence being formed.
     * @return A list of all subsequences.
     */
    public static ArrayList<ArrayList<Integer>> getArraySubsequences(int[] arr, int index, ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        if (index == arr.length) {
            output.add(new ArrayList<>(set));
            return output;
        }
        set.add(arr[index]);
        output.addAll(getArraySubsequences(arr, index + 1, set));
        set.remove(set.size() - 1);
        output.addAll(getArraySubsequences(arr, index + 1, set));
        return output;
    }

    /**
     * Approach 3: Optimized Recursive Subset Generation
     * 
     * Intuition:
     * - This approach optimizes the recursion by using an explicit `index` parameter
     *   to track position instead of modifying the list.
     * - Using an external list reference (`set`), we avoid redundant list operations.
     * - At each step, we either:
     *   1. Include `nums[index]` in the current subset.
     *   2. Exclude `nums[index]` from the current subset.
     * 
     * Explanation:
     * - Consider `[1, 2, 3]`:
     *   - Start at index `0` with an empty subset.
     *   - At each step, decide whether to include `nums[index]` or not.
     *   - When index reaches `nums.length`, store the subset.
     * 
     * Execution tree:
     * 
     *                  []
     *                /    \
     *             [1]      []
     *            /   \    /   \
     *       [1,2]   [1]  [2]   []
     *      /   \    / \   / \   / \
     * [1,2,3] [1,2] [1,3] [1] [2,3] [2] [3] []
     * 
     * Time Complexity:
     * - O(2^n), since every element results in two recursive calls.
     * 
     * Space Complexity:
     * - O(2^n), since all subsets are stored.
     * 
     * @param nums The input array.
     * @param index The current index in the array.
     * @param curr The currently built subset.
     * @param set The list storing subsets.
     */
    public static void generateSubsets(int[] nums, int index, ArrayList<Integer> curr, List<List<Integer>> set) {
        if (index == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }
        curr.add(nums[index]);
        generateSubsets(nums, index + 1, curr, set);
        curr.remove(curr.size() - 1);
        generateSubsets(nums, index + 1, curr, set);
    }
}
