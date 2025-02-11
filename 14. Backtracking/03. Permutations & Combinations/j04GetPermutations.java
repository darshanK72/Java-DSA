/**
 * Problem Statement:
 * 
 * Given an array of distinct integers, generate all possible permutations 
 * of the array. A permutation is an arrangement of all elements of the 
 * array in any order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A list of lists containing all possible permutations of the array.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 * 
 *     Output:
 *     [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
 * 
 *     Explanation:
 *     - The possible ways to arrange {1, 2, 3} are listed above.
 *     - Since order matters in permutations, each unique arrangement is counted.
 *     - The total number of permutations for `n` elements is `n!` (factorial of n).
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j04GetPermutations {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Approach 1: Print permutations directly
        printArrayPermutations(arr, new ArrayList<>(), new boolean[n]);

        // Approach 2: Brute Force - Using an extra list to check existence
        List<List<Integer>> set1 = new ArrayList<>();
        generatePermutations1(arr, new ArrayList<>(), set1);
        System.out.println(set1);

        // Approach 3: Backtracking with a boolean visited array
        List<List<Integer>> set2 = new ArrayList<>();
        generatePermutations2(arr, new boolean[arr.length], new ArrayList<>(), set2);
        System.out.println(set2);

        // Approach 4: Optimized in-place swapping approach
        List<List<Integer>> set3 = generatePermutations3(arr, 0);
        System.out.println(set3);

        in.close();
    }

    /**
     * Approach 1: Directly Printing Permutations (Brute Force)
     * 
     * Intuition:
     * - To generate all permutations, we need to explore all possible orders of elements.
     * - We maintain a list to store the current permutation being built.
     * - A boolean array is used to track which elements are already included.
     * - We recursively try adding each element that hasn’t been used yet.
     * - If the list reaches the size of the input array, we print it as a valid permutation.
     * 
     * Explanation:
     * - We start with an empty list and a boolean array marking all elements as unvisited.
     * - At each recursive step, we iterate over the array:
     *   1. If an element is not visited, we mark it as visited.
     *   2. Add it to the current permutation.
     *   3. Recur to build further permutations.
     *   4. After returning, we backtrack by unmarking the element and removing it from the list.
     * - This ensures all possible arrangements are explored.
     * 
     * Time Complexity:
     * - O(n!) since we generate all possible permutations.
     * 
     * Space Complexity:
     * - O(n) due to recursion stack and boolean array.
     * 
     * @param arr The input array.
     * @param ans The current permutation being formed.
     * @param visited A boolean array to track visited elements.
     */
    public static void printArrayPermutations(int[] arr, List<Integer> ans, boolean[] visited) {
        if (ans.size() == arr.length) {
            System.out.print(ans + " ");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans.add(arr[i]);
                printArrayPermutations(arr, ans, visited);
                ans.remove(ans.size() - 1);
                visited[i] = false;
            }
        }
    }

    /**
     * Approach 2: Brute Force using an Extra List
     * 
     * Intuition:
     * - Instead of a boolean array, we check if an element exists in the list before adding it.
     * - This avoids using an extra boolean array but increases lookup time.
     * - The backtracking process remains the same as in the previous approach.
     * 
     * Time Complexity:
     * - O(n!) for generating permutations.
     * - Extra O(n) lookup per permutation, making it inefficient.
     * 
     * Space Complexity:
     * - O(n) recursion stack.
     */
    public static void generatePermutations1(int[] nums, ArrayList<Integer> curr, List<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!curr.contains(nums[i])) {
                curr.add(nums[i]);
                generatePermutations1(nums, curr, set);
                curr.remove(curr.size() - 1);
            }
        }
    }

    /**
     * Approach 3: Optimized Backtracking using Boolean Array
     * 
     * Intuition:
     * - Instead of checking if an element exists in the list, we use a boolean array.
     * - This reduces lookup time to O(1).
     * - Otherwise, it follows the same recursive backtracking logic.
     * 
     * Time Complexity:
     * - O(n!) for generating permutations.
     * - Improved lookup to O(1), making it faster than Approach 2.
     * 
     * Space Complexity:
     * - O(n) due to recursion stack and boolean array.
     */
    public static void generatePermutations2(int[] nums, boolean[] visited, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                curr.add(nums[i]);
                generatePermutations2(nums, visited, curr, set);
                visited[i] = false;
                curr.remove(curr.size() - 1);
            }
        }
    }

    /**
     * Approach 4: Optimized In-Place Swapping (Best Approach)
     * 
     * Intuition:
     * - Instead of using extra space for tracking, we swap elements in the array itself.
     * - This allows us to generate permutations in-place without additional lists.
     * - The swap ensures that each recursive call works with a new order of elements.
     * 
     * Explanation:
     * - We start with the first index and swap it with every subsequent index.
     * - Recursively generate permutations for the remaining elements.
     * - Swap back to restore the original array order (backtracking).
     * - This minimizes memory usage while still generating all permutations.
     * 
     * Time Complexity:
     * - O(n!) for generating permutations.
     * - No extra lookup overhead, making it the most efficient.
     * 
     * Space Complexity:
     * - O(n) due to recursion stack, but no extra arrays used.
     */
    public static List<List<Integer>> generatePermutations3(int[] arr, int index) {
        ArrayList<List<Integer>> output = new ArrayList<>();
        if (index == arr.length) {
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i : arr)
                ans.add(i);
            output.add(ans);
            return output;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            output.addAll(generatePermutations3(arr, index + 1));
            swap(arr, index, i);
        }
        return output;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
