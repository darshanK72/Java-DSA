/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` and an integer `target`, the task is to find all unique 
 *     combinations of `k` numbers in the array that sum up to the `target`.
 * 
 * Input:
 *     - An integer array `nums` of size `n` (1 <= n <= 1000) where each element satisfies 
 *       (1 <= nums[i] <= 10^5).
 *     - An integer `target` (-10^9 <= target <= 10^9), representing the target sum.
 *     - An integer `k` (2 <= k <= n), representing the number of elements to be selected for 
 *       the sum.
 * 
 * Output:
 *     - A list of lists, where each list contains exactly `k` numbers that sum up to `target`.
 * 
 * Example:
 *     Input:
 *     nums = {1, 0, -1, 0, -2, 2}
 *     target = 0
 *     k = 4
 *     
 *     Output:
 *     [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
 * 
 *     Explanation:
 *     The unique combinations of four numbers in the array that sum to the target 0 are:
 *     [-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class j02KSum {

    public static void main(String[] args) {
        // Test case
        int[] nums = { 1, 0, -1, 0, -2, 2 };
        int target = 0;
        int k = 4;

        // Call kSum method and get the result
        List<List<Integer>> result = kSum(nums, target, k);

        // Print the result
        System.out.println("Combinations that sum to " + target + ":");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    /**
     * Approach 1: Brute Force with Recursion (k-Sum approach)
     * 
     * Intuition:
     * - The brute force solution utilizes recursion to explore all possible combinations 
     *   of `k` elements that sum up to the target.
     * - The function reduces the problem size by considering fewer elements in each recursion.
     * - For k = 2, it delegates the problem to the twoSum helper function.
     * 
     * Time Complexity:
     * - O(n^k), where `n` is the length of the array and `k` is the number of elements 
     *   to select. This is because for each recursive call, we consider `k` elements.
     * 
     * Space Complexity:
     * - O(k), as we store intermediate results in the recursive calls.
     * 
     * @param nums The input array of numbers.
     * @param target The target sum.
     * @param k The number of elements to select.
     * @return List of lists with combinations summing up to the target.
     */
    public static List<List<Integer>> kSum(int[] nums, int target, int k) {
        Arrays.sort(nums); // Sort the array to handle duplicates and make the solution efficient
        return kSumHelper(nums, 0, target, k);
    }

    /**
     * Approach 2: Optimized Two-Sum Helper Function
     * 
     * Intuition:
     * - The twoSum function is used to find pairs that sum up to the target. It uses a 
     *   two-pointer approach to reduce the time complexity of finding pairs in a sorted 
     *   array. This approach eliminates duplicate pairs efficiently.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of elements in the array, as we scan through the 
     *   array once with two pointers.
     * 
     * Space Complexity:
     * - O(1), as we only store the pairs that sum up to the target.
     * 
     * @param nums The input array of numbers.
     * @param s The starting index for the current sum.
     * @param target The target sum for the pair.
     * @return A list of pairs that sum up to the target.
     */
    public static ArrayList<List<Integer>> twoSum(int[] nums, int s, long target) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        int e = nums.length - 1;
        while (s < e) {
            long sum = (long) nums[s] + (long) nums[e];
            if (sum == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[s]);
                temp.add(nums[e]);
                out.add(temp);
                s++;
                e--;
                while (s < e && nums[s] == nums[s - 1])
                    s++; // skip duplicates
                while (s < e && nums[e] == nums[e + 1])
                    e--; // skip duplicates
            } else if (sum > target) {
                e--;
            } else {
                s++;
            }
        }
        return out;
    }

    /**
     * Approach 3: Recursive Helper for k-Sum
     * 
     * Intuition:
     * - This is a recursive helper function that reduces the problem by one element at 
     *   each step. It calls the twoSum function when `k` becomes 2.
     * - For higher values of `k`, it recursively finds `k-1` combinations and adds the 
     *   current element to each valid combination.
     * 
     * Time Complexity:
     * - O(n^(k-1)), where `n` is the number of elements and `k` is the number of 
     *   elements we need to select. The recursion branches for each value of `k`.
     * 
     * Space Complexity:
     * - O(k), due to the recursive call stack.
     * 
     * @param nums The input array of numbers.
     * @param s The starting index for the current sum.
     * @param target The target sum for the current recursion.
     * @param k The number of elements left to select.
     * @return A list of lists with valid combinations summing up to the target.
     */
    public static ArrayList<List<Integer>> kSumHelper(int[] nums, int s, long target, int k) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        if (k == 2) {
            return twoSum(nums, s, target);
        } else {
            for (int i = s; i < nums.length - (k - 1); i++) {
                if (i > s && nums[i] == nums[i - 1])
                    continue; // skip duplicates
                ArrayList<List<Integer>> kList = kSumHelper(nums, i + 1, target - nums[i], k - 1);
                for (List<Integer> lst : kList) {
                    lst.add(nums[i]);
                    out.add(lst);
                }
            }
        }
        return out;
    }
}
