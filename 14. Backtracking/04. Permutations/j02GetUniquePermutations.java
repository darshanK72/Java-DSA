/**
 * Problem Statement:
 * 
 *     Given an integer array `nums`, the task is to generate all possible unique permutations of the array 
 *     considering the potential presence of duplicate elements. The result should contain only unique permutations, 
 *     i.e., no duplicates in the list of permutations.
 * 
 * Input:
 *     - An integer array `nums` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - A list of all unique permutations of the array `nums`.
 * 
 * Example:
 *     Input:
 *     [1, 1, 2]
 *     
 *     Output:
 *     [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
 * 
 *     Explanation:
 *     The output shows all possible unique permutations of the array [1, 1, 2].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class j02GetUniquePermutations {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        HashSet<List<Integer>> set1 = new HashSet<>();
        generateUniquePermutations1(nums, new boolean[nums.length], new ArrayList<>(), set1);
        System.out.println(set1);

        List<List<Integer>> set2 = new ArrayList<>();
        Arrays.sort(nums);
        generatePermutations2(nums, new boolean[nums.length], new ArrayList<>(), set2);
        System.out.println(set2);

        in.close();
    }

    /**
     * Approach 1: Backtracking with a visited array and a HashSet to store unique permutations
     * 
     * Intuition:
     * - The approach uses backtracking to explore all possible permutations, with the addition of a visited 
     *   array to keep track of the elements that have been used in the current permutation.
     * - A `HashSet` is used to store the permutations, ensuring that only unique permutations are stored 
     *   (since HashSet automatically handles duplicates).
     * 
     * Time Complexity:
     * - O(n! * n), where n is the length of the array.
     * - The algorithm generates n! permutations, and for each permutation, we perform O(n) operations 
     *   to create and check the permutation.
     * 
     * Space Complexity:
     * - O(n), for recursion stack and storage of unique permutations.
     * 
     * @param nums The input array.
     * @param visited Array to track visited elements.
     * @param curr The current permutation being formed.
     * @param set The set to store unique permutations.
     */
    public static void generateUniquePermutations1(int[] nums, boolean[] visited, ArrayList<Integer> curr,
            HashSet<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                curr.add(nums[i]);
                visited[i] = true;
                generateUniquePermutations1(nums, visited, curr, set);
                visited[i] = false;
                curr.remove(curr.size() - 1);
            }
        }
    }

    /**
     * Approach 2: Backtracking with sorting and a visited array to avoid duplicates
     * 
     * Intuition:
     * - In this approach, the input array is first sorted to ensure that duplicate elements are adjacent to each 
     *   other. During the backtracking process, we skip over duplicate elements to avoid generating duplicate 
     *   permutations.
     * - We use a visited array to ensure each element is used only once per permutation.
     * 
     * Time Complexity:
     * - O(n! * n), where n is the length of the array.
     * - Sorting the array takes O(n log n), and generating permutations takes O(n! * n).
     * 
     * Space Complexity:
     * - O(n), for recursion stack and storage of permutations.
     * 
     * @param nums The input array.
     * @param visited Array to track visited elements.
     * @param curr The current permutation being formed.
     * @param set The list to store unique permutations.
     */
    public static void generatePermutations2(int[] nums, boolean[] visited, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            if (i > 0 && !visited[i - 1] && nums[i - 1] == nums[i])
                continue;
            curr.add(nums[i]);
            visited[i] = true;
            generatePermutations2(nums, visited, curr, set);
            visited[i] = false;
            curr.remove(curr.size() - 1);
        }
    }
}
