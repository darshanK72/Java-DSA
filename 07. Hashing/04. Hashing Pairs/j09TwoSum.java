/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums` and an integer `target`, return indices of the two numbers 
 *     such that they add up to the target. Assume each input would have exactly one solution, 
 *     and you may not use the same element twice.
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (-10^9 <= nums[i] <= 10^9).
 *     - An integer `target` (-10^9 <= target <= 10^9).
 * 
 * Output:
 *     - An array of two integers, representing the indices of the numbers that add up to the target.
 * 
 * Example:
 *     Input:
 *         4
 *         2 7 11 15
 *         9
 *     Output:
 *         [0, 1]
 * 
 *     Explanation:
 *         nums[0] + nums[1] = 2 + 7 = 9. Thus, the output is [0, 1].
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j09TwoSum {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();

        // Output the result using efficient solution
        System.out.println(Arrays.toString(twoSumEfficient(arr, target)));
        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - Iterate through all possible pairs of indices `(i, j)` where `i < j`.
     * - For each pair, check if `nums[i] + nums[j] == target`.
     * - Return the indices of the first pair that satisfies the condition.
     * 
     * Explanation:
     * - Use two nested loops to evaluate all possible combinations of indices.
     * - Once a pair is found, break the loop and return the indices.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the size of the array (due to the nested loops).
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from the result array.
     * 
     * @param nums The input array of numbers.
     * @param target The target sum.
     * @return An array of two integers representing the indices of the numbers.
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

    /**
     * Approach 2: HashMap for Complement Lookup (Efficient Solution)
     * 
     * Intuition:
     * - Use a HashMap to store the value-to-index mapping as you traverse the array.
     * - For each number, calculate its complement (`target - nums[i]`) and check if it exists in the HashMap.
     * - If the complement exists, the current index and the stored index form the desired pair.
     * 
     * Explanation:
     * - Traverse the array while maintaining a HashMap of previously seen numbers and their indices.
     * - For each number, check if its complement is in the HashMap.
     * - If found, return the current index and the stored index as the result.
     * - This approach eliminates the need for nested loops by leveraging the HashMap for O(1) lookups.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array (each element is processed once).
     * 
     * Space Complexity:
     * - O(n), for storing the elements in the HashMap.
     * 
     * @param nums The input array of numbers.
     * @param target The target sum.
     * @return An array of two integers representing the indices of the numbers.
     */
    public static int[] twoSumEfficient(int[] nums, int target) {
        int[] ans = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                ans[0] = map.get(complement);
                ans[1] = i;
                return ans;
            }
            map.put(nums[i], i);
        }
        return ans;
    }
}
