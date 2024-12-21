/**
 * Problem Statement:
 *
 *     Given a sorted array `nums` of integers, find all unique pairs of indices `(s, e)` such that
 *     `nums[s] + nums[e] = target`. The array is sorted by absolute values, so pairs will be explored
 *     efficiently from both ends.
 *
 * Input:
 *     - An integer `n` (the size of the array).
 *     - A sorted array `nums` of size `n`.
 *     - An integer `target`.
 *
 * Output:
 *     - A list of all pairs of indices `(s, e)` such that `nums[s] + nums[e] = target`.
 *
 * Example:
 *     Input:
 *     6
 *     -3 -1 0 2 4 6
 *     3
 *
 *     Output:
 *     [[0, 3], [1, 2]]
 *
 *     Explanation:
 *     In the example, the pairs `(s, e)` that satisfy the condition `nums[s] + nums[e] = 3` are:
 *     - Pair `(0, 3)` because `nums[0] + nums[3] = -3 + 2 = 3`.
 *     - Pair `(1, 2)` because `nums[1] + nums[2] = -1 + 0 = 3`.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j14TwoSumAbsValSortedArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input the size of the array
        int[] nums = new int[n]; // Initialize the array
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input elements of the array
        }
        int target = in.nextInt(); // Input the target value
        System.out.println(twoSumVII(nums, target)); // Output all valid pairs
        in.close();
    }

    /**
     * Approach: Two-pointer approach with handling of positive and negative segments.
     * 
     * Intuition:
     * - Since the array is sorted by absolute value, we can use two pointers, one starting from the
     *   beginning and the other from the end, to find pairs that add up to the target value.
     * - Move the pointers toward each other while adjusting the pointers based on the sum of the values.
     * 
     * Time Complexity:
     * - O(n), as we scan the array with two pointers.
     * 
     * Space Complexity:
     * - O(n), due to the use of a list to store pairs.
     * 
     * @param nums The sorted array of integers.
     * @param target The target sum to find pairs for.
     * @return A list of pairs (as lists of integers) satisfying the condition.
     */
    public static List<List<Integer>> twoSumVII(int[] nums, int target) {
        List<List<Integer>> out = new ArrayList<>();
        int e = getEnd(nums, nums.length);
        int s = getStart(nums, nums.length);
        while (s >= 0 && e >= 0) {
            int sum = nums[s] + nums[e];
            if (sum == target) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(s);
                temp.add(e);
                out.add(temp);
                s = getStart(nums, s);
                e = getEnd(nums, e);
            } else if (sum > target) {
                e = getEnd(nums, e);
            } else {
                s = getStart(nums, s);
            }
        }
        return out;
    }

    /**
     * Helper function to find the start of the valid range of `s`.
     * 
     * @param nums The sorted array.
     * @param e Current end index.
     * @return The start index `s`.
     */
    public static int getStart(int[] nums, int e) {
        int j = e - 1;
        for (; j >= 0; j--) {
            if (nums[j] < 0)
                break;
        }
        if (j >= 0)
            return j;
        j = 0;
        for (; j < nums.length; j++) {
            if (nums[j] >= 0)
                break;
        }
        return j;
    }

    /**
     * Helper function to find the end of the valid range of `e`.
     * 
     * @param nums The sorted array.
     * @param e Current index.
     * @return The end index `e`.
     */
    public static int getEnd(int[] nums, int e) {
        int j = e - 1;
        for (; j >= 0; j--) {
            if (nums[j] >= 0)
                break;
        }
        if (j >= 0)
            return j;
        for (; j < nums.length; j++) {
            if (nums[j] < 0)
                break;
        }
        return j;
    }
}
