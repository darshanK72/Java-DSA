/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` of size `n` where 1 ≤ nums[i] ≤ n, some elements appear twice 
 *     and others appear once. Find all the elements that appear twice in the array.
 *     You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 * 
 * Input:
 *     - An integer `n` (1 ≤ n ≤ 10^5), the size of the array.
 *     - An array `nums` of size `n`, containing integers in the range `[1, n]`.
 * 
 * Output:
 *     - A list of integers representing all the numbers that appear twice in the array.
 * 
 * Example:
 *     Input:
 *         n = 8, nums = [4,3,2,7,8,2,3,1]
 *     Output:
 *         [2, 3]
 * 
 *     Explanation:
 *         - The numbers `2` and `3` appear twice in the array.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j05FindAllDuplicates {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        // Output the list of duplicates
        System.out.println(findDuplicates(arr));
        in.close();
    }

    /**
     * Approach: Index-Based Hashing
     * 
     * Intuition:
     * - We can use the input array itself to keep track of element frequencies by treating the numbers as indices.
     * - By modifying the array in place, we can detect duplicate elements.
     * 
     * Explanation:
     * - For each number in the array, calculate the index using `nums[i] % (nums.length + 1)` to retrieve the original value.
     * - Increment the value at this index by `(nums.length + 1)`. This marks the index as visited.
     * - After processing the entire array, any index whose value divided by `(nums.length + 1)` is greater than 1 indicates a duplicate.
     * 
     * Time Complexity:
     * - O(n): We traverse the array twice, resulting in linear time complexity.
     * 
     * Space Complexity:
     * - O(1): The algorithm modifies the input array directly without using additional space.
     * 
     * @param nums The input array of numbers.
     * @return A list of integers representing all duplicate elements.
     */
    public static List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> out = new ArrayList<>(); // List to store duplicate numbers

        // Step 1: Mark indices based on array values
        for (int i = 0; i < nums.length; i++) {
            int original = nums[i] % (nums.length + 1); // Retrieve original number
            nums[original - 1] += (nums.length + 1); // Increment the value at the corresponding index
        }

        // Step 2: Identify duplicates
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] / (nums.length + 1) > 1) { // Check if index value is incremented more than once
                out.add(i + 1); // Add the duplicate number to the output list
            }
        }

        // Return the list of duplicates
        return out;
    }
}
