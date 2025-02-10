/**
 * Problem Statement:
 * 
 *     Given an integer `n` and an array `arr` of size `n`, the task is to generate and print all possible permutations 
 *     of the given array. There are multiple methods to generate the permutations, including backtracking, 
 *     recursion, and swapping. The output should display the generated permutations in different formats using 
 *     different approaches.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A list of all possible permutations of the array `arr`.
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
 *     The output shows all possible permutations of the array [1, 2, 3] using different methods.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j01GetPermutations {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Approach 1: Backtracking with visited array
        printArrayPermutations(arr, new ArrayList<>(), new boolean[n]);

        // Approach 2: Backtracking with list for tracking current permutations
        List<List<Integer>> set1 = new ArrayList<>();
        generatePermutations1(arr, new ArrayList<>(), set1);
        System.out.println(set1);

        // Approach 3: Backtracking with visited array
        List<List<Integer>> set2 = new ArrayList<>();
        generatePermutations2(arr, new boolean[arr.length], new ArrayList<>(), set2);
        System.out.println(set2);

        // Approach 4: Swapping approach to generate permutations
        List<List<Integer>> set3 = generatePermutations3(arr, 0);
        System.out.println(set3);

        in.close();
    }

    /**
     * Approach 1: Backtracking with a visited array
     * 
     * Intuition:
     * - The idea is to use backtracking with a visited array to keep track of which elements 
     *   have been used in the current permutation.
     * - We recursively add elements to the current permutation and backtrack when we reach 
     *   the end of a valid permutation.
     * 
     * Time Complexity:
     * - O(n! * n), where n is the length of the array.
     * - We generate n! permutations and for each permutation, we perform O(n) operations 
     *   to create the permutation list.
     * 
     * Space Complexity:
     * - O(n), for the recursion stack and the temporary visited array.
     * 
     * @param arr The input array.
     * @param ans The current permutation.
     * @param visited Array to track visited elements.
     */
    public static void printArrayPermutations(int[] arr, List<Integer> ans, boolean[] visited) {
        if (ans.size() == arr.length) {
            System.out.print(ans + " ");
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                ans.add(arr[i]);
                visited[i] = true;
                printArrayPermutations(arr, ans, visited);
                ans.remove(ans.size() - 1);
                visited[i] = false;
            }
        }
    }

    /**
     * Approach 2: Backtracking with current list tracking
     * 
     * Intuition:
     * - Similar to Approach 1, but instead of using a visited array, we directly keep track of the 
     *   current permutation in a list. We check if the current element is already present in the list 
     *   before adding it to avoid duplicates.
     * 
     * Time Complexity:
     * - O(n! * n), where n is the length of the array.
     * - Generating n! permutations with each permutation requiring O(n) time to process.
     * 
     * Space Complexity:
     * - O(n), for the recursion stack and storing permutations.
     * 
     * @param nums The input array.
     * @param curr The current permutation.
     * @param set The list to store the permutations.
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
     * Approach 3: Backtracking with a visited array for tracking
     * 
     * Intuition:
     * - This approach is similar to Approach 1 but uses a visited array to track the used elements 
     *   during the recursion. This ensures that each element can only be used once per permutation.
     * 
     * Time Complexity:
     * - O(n! * n), where n is the length of the array.
     * 
     * Space Complexity:
     * - O(n), for recursion stack and visited array.
     * 
     * @param nums The input array.
     * @param visited The visited array for tracking used elements.
     * @param curr The current permutation being formed.
     * @param set The list to store the permutations.
     */
    public static void generatePermutations2(int[] nums, boolean[] visited, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                curr.add(nums[i]);
                visited[i] = true;
                generatePermutations2(nums, visited, curr, set);
                visited[i] = false;
                curr.remove(curr.size() - 1);
            }
        }
    }

    /**
     * Approach 4: Swapping approach
     * 
     * Intuition:
     * - This approach generates permutations by swapping elements at each position, then recursively 
     *   generating permutations for the rest of the array. After the recursion call, it swaps back 
     *   to restore the array state.
     * - This avoids the need for extra space to track visited elements.
     * 
     * Time Complexity:
     * - O(n! * n), where n is the length of the array.
     * 
     * Space Complexity:
     * - O(n), for recursion stack.
     * 
     * @param arr The input array.
     * @param index The current index being processed.
     * @return List of all permutations.
     */
    public static List<List<Integer>> generatePermutations3(int[] arr, int index) {
        ArrayList<List<Integer>> output = new ArrayList<List<Integer>>();
        if (index == arr.length) {
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i : arr) {
                ans.add(i);
            }
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

    /**
     * Helper method to swap elements in the array
     * 
     * @param nums The input array.
     * @param i The index of the first element to swap.
     * @param j The index of the second element to swap.
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
