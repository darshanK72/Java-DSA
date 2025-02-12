/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` that may contain duplicates, return all possible subsets (the power set).
 *     The solution set must not contain duplicate subsets. The subsets can be returned in any order.
 * 
 * Input:
 *     - An array `nums[]` of size `n` where each element satisfies (-10 <= nums[i] <= 10).
 * 
 * Output:
 *     - A list of lists, where each inner list represents a unique subset of `nums[]`.
 * 
 * Constraints:
 *     - 1 <= nums.length <= 10
 *     - -10 <= nums[i] <= 10
 * 
 * Example:
 *     Input:
 *     nums = [1, 2, 2]
 *     
 *     Output:
 *     [[], [1], [2], [1,2], [2,2], [1,2,2]]
 * 
 *     Explanation:
 *     - The power set includes all possible subsets.
 *     - Since duplicates exist, we ensure that subsets are unique.
 */

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class j06UniqueSubsetsII {

    public static void main(String[] args) {
        // Test case 1: nums with duplicates
        int[] nums = { 1, 2, 2 };


        // Testing Approach 1: Using HashSet for Unique Subsets
        HashSet<List<Integer>> set1 = new HashSet<>();
        Arrays.sort(nums); // Sort to handle duplicates
        generateSubsets1(nums, 0, new ArrayList<Integer>(), set1);
        System.out.println("Approach 1 (Using HashSet):");
        for (List<Integer> subset : set1) {
            System.out.println(subset);
        }

        // Testing Approach 2: Using List Without HashSet
        Arrays.sort(nums); // Sort to handle duplicates
        List<List<Integer>> set2 = new ArrayList<>();
        generateSubsets2(nums, 0, new ArrayList<Integer>(), set2);
        System.out.println("\nApproach 2 (Using List without HashSet):");
        for (List<Integer> subset : set2) {
            System.out.println(subset);
        }

        // Testing Approach 3: Optimized Backtracking Without Extra Data Structures
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort to handle duplicates
        generateSubsets3(nums, 0, new ArrayList<>(), result);
        System.out.println("\nApproach 3 (Optimized Backtracking):");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    /**
     * Approach 1: Using HashSet to Store Unique Subsets
     * 
     * Intuition:
     * - Since the array may contain duplicates, we need to ensure that duplicate subsets are not included.
     * - A HashSet is used to store subsets, ensuring uniqueness.
     * - We generate all subsets using recursion, and store them in a HashSet before converting them into a list.
     * 
     * Explanation:
     * - First, sort the input array to ensure duplicate elements are adjacent.
     * - Use recursion to generate subsets:
     *   - At each index, we either include the current element or exclude it.
     *   - Base case: If we reach the end of the array, store the subset in a HashSet.
     * - Finally, convert the HashSet into a list and return it.
     * 
     * Time Complexity:
     * - O(2^n * n) due to subset generation and storage in HashSet.
     * 
     * Space Complexity:
     * - O(2^n) for storing subsets.
     * 
     * 
     * @param nums The input array of integers.
     * @param index The current index in the array.
     * @param curr The current subset being generated.
     * @param set The HashSet to store unique subsets.
     */
    public static void generateSubsets1(int[] nums, int index, ArrayList<Integer> curr, HashSet<List<Integer>> set) {
        if (index == nums.length) {
            set.add(new ArrayList<>(curr)); // Add subset to HashSet
            return;
        }
        // Include current element
        curr.add(nums[index]);
        generateSubsets1(nums, index + 1, curr, set);

        // Exclude current element
        curr.remove(curr.size() - 1);
        generateSubsets1(nums, index + 1, curr, set);
    }

    /**
     * Approach 2: Using List Without HashSet
     * 
     * Intuition:
     * - Instead of using a HashSet, we directly use a list to store subsets.
     * - We must handle duplicate elements manually.
     * - The goal is to ensure that for any duplicate number, we only include it in certain recursive calls.
     * 
     * Explanation:
     * - First, sort the array to group duplicates together.
     * - Use recursion to generate subsets:
     *   - Include the current element.
     *   - Exclude the current element.
     *   - If the previous element is the same as the current one and has been excluded before, skip it to avoid duplication.
     * - Store all unique subsets in a list.
     * 
     * Time Complexity:
     * - O(2^n) due to subset generation.
     * 
     * Space Complexity:
     * - O(2^n) for storing subsets.
     * 
     * @param nums The input array of integers.
     * @param index The current index in the array.
     * @param curr The current subset being generated.
     * @param set The list to store unique subsets.
     */
    public static void generateSubsets2(int[] nums, int index, ArrayList<Integer> curr, List<List<Integer>> set) {
        if (index == nums.length) {
            set.add(new ArrayList<>(curr)); // Add subset to list
            return;
        }
        // Include current element
        curr.add(nums[index]);
        generateSubsets2(nums, index + 1, curr, set);

        // Exclude current element
        curr.remove(curr.size() - 1);

        // Skip duplicate elements to avoid duplicate subsets
        if (curr.size() > 0 && curr.get(curr.size() - 1) == nums[index])
            return;
        generateSubsets2(nums, index + 1, curr, set);
    }

    /**
     * Approach 3: Optimized Backtracking Without Extra Data Structures
     * 
     * Intuition:
     * - Instead of using HashSet or manually handling duplicates, we use a cleaner recursive approach.
     * - The idea is to avoid duplicate subsets naturally by skipping duplicate elements when generating subsets.
     * - This ensures that each subset is included only once.
     * 
     * Explanation:
     * - First, sort the array to ensure duplicate elements appear consecutively.
     * - Use recursion with backtracking to explore all subsets.
     * - When adding elements to the subset, if the current element is the same as the previous one, we skip it unless it is the first occurrence at this level.
     * - This prevents duplicate subsets from being generated.
     * - The result is stored directly in a list.
     * 
     * Time Complexity:
     * - O(2^n) due to subset generation.
     * 
     * Space Complexity:
     * - O(2^n) for storing subsets.
     * 
     * @param nums The input array of integers.
     * @param index The current index in the array.
     * @param curr The current subset being generated.
     * @param result The list to store unique subsets.
     */
    public static void generateSubsets3(int[] nums, int index, List<Integer> curr, List<List<Integer>> result) {
        result.add(new ArrayList<>(curr)); // Store current subset

        for (int i = index; i < nums.length; i++) {
            // Skip duplicate elements to prevent duplicate subsets
            if (i > index && nums[i] == nums[i - 1])
                continue;

            curr.add(nums[i]); // Include element
            generateSubsets3(nums, i + 1, curr, result);
            curr.remove(curr.size() - 1); // Remove last element (backtracking)
        }
    }
}
