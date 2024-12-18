/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums` containing `n` numbers, where each element is in the range [1, n],
 *     find all the numbers in the range [1, n] that are missing from the array.
 *     The array may contain duplicates, and the goal is to return a list of all the missing numbers.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= n).
 * 
 * Output:
 *     - A list of integers representing the missing numbers in the array.
 * 
 * Example:
 *     Input:
 *         n = 6, arr = [4,3,2,7,8,2,3,1]
 *     Output:
 *         [5, 6]
 * 
 *     Explanation:
 *         - The range [1, 6] contains {1, 2, 3, 4, 5, 6}.
 *         - The numbers 5 and 6 are missing from the array.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j02FindAllDisappeared {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        // Calling the method to find missing numbers
        System.out.println(findDisappearedNumbers(arr)); // Calling the method to find missing numbers
        in.close();
    }

    /**
     * Approach: Index-Based Hashing
     * 
     * Intuition:
     * - The goal is to identify which numbers in the range [1, n] are missing in the array.
     * - We can use the array itself as a "hashmap" by marking the presence of numbers.
     * - We can achieve this by modifying the values at the indices corresponding to the numbers we encounter in the array.
     * - The modification can be done using modulo and division, so we don't need extra space.
     * 
     * Explanation:
     * - We iterate through the array and, for each element, we treat the value as an index. 
     * - By marking the corresponding index (in the array) with a modification, we track the presence of each number.
     * - After the marking step, any number whose corresponding index remains unchanged will be missing from the array.
     * 
     * Time Complexity:
     * - O(n), as the array is traversed twice: once for marking the presence and once for identifying missing numbers.
     * 
     * Space Complexity:
     * - O(1), since no extra space is used apart from the input array.
     * 
     * @param nums The input array.
     * @return A list of integers representing the missing numbers.
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> out = new ArrayList<Integer>();

        // Step 1: Mark the presence of numbers by modifying the array itself
        for (int i = 0; i < nums.length; i++) {
            int original = nums[i] % (nums.length + 1); // Modifying the element to ensure we can track presence
            nums[original - 1] += (nums.length + 1); // Mark the index corresponding to the number as visited
        }

        // Step 2: Identify the missing numbers based on the array
        for (int i = 0; i < nums.length; i++) {
            // If the number is not marked (i.e., the division by (n + 1) is 0), it means
            // the number is missing
            if (nums[i] / (nums.length + 1) == 0) {
                out.add(i + 1); // The missing number is the index + 1
            }
        }

        return out;
    }
}
