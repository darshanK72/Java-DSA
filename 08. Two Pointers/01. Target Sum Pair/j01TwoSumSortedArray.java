/**
 * Problem Statement:
 * 
 *     Given a sorted array of integers, `numbers`, and a target integer `target`, 
 *     return the 1-based indices of the two numbers such that they add up to the given target.
 *     If no such pair exists, return [-1, -1].
 *     
 *     Your solution should consider the fact that the array is sorted, and use that information
 *     to potentially optimize the solution.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `numbers` of size `n` where each element satisfies (1 <= numbers[i] <= 10^5).
 *     - An integer `target` (2 <= target <= 2 * 10^5) representing the target sum.
 * 
 * Output:
 *     - An array of two integers representing the 1-based indices of the two numbers 
 *       whose sum is equal to the target. If no such pair exists, return [-1, -1].
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     9
 *     Output:
 *     [4, 5]
 *     
 *     Explanation:
 *     The pair of numbers at indices 4 and 5 (1-based) are 4 and 5, and their sum equals the target 9.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j01TwoSumSortedArray {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = in.nextInt(); // Input: elements of the array
        }
        int target = in.nextInt(); // Input: the target sum

        // Call your solution
        System.out.println(Arrays.toString(twoSum(numbers, target)));

        // Call optimized solution (if applicable)
        System.out.println(Arrays.toString(twoSumEfficient(numbers, target)));

        in.close();
    }

    /**
     * Approach: Brute Force Solution
     * 
     * Intuition:
     * - Iterate through every possible pair of numbers in the array, check if their sum equals the target.
     * - This approach does not take advantage of the fact that the array is sorted.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of the input array. We are using two nested loops to check each pair.
     * 
     * Space Complexity:
     * - O(1), since we are only using a few variables to track the indices.
     * 
     * @param numbers The input sorted array of numbers.
     * @param target The target sum to find.
     * @return The indices of the pair that adds up to the target sum.
     */
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target)
                    return new int[] { i + 1, j + 1 };
                else if (numbers[i] + numbers[j] > target)
                    break;
            }
        }
        return new int[] { -1, -1 };
    }

    /**
     * Approach: Two-Pointer Optimized Solution
     * 
     * Intuition:
     * - Since the array is sorted, we can use two pointers: one at the start (s) and one at the end (e) of the array.
     * - If the sum of the elements at the two pointers equals the target, return their indices.
     * - If the sum is less than the target, move the left pointer to the right to increase the sum.
     * - If the sum is greater than the target, move the right pointer to the left to decrease the sum.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the input array. We only need to traverse the array once using the two-pointer technique.
     * 
     * Space Complexity:
     * - O(1), since we are only using a constant amount of extra space.
     * 
     * @param numbers The input sorted array of numbers.
     * @param target The target sum to find.
     * @return The indices of the pair that adds up to the target sum.
     */
    public static int[] twoSumEfficient(int[] numbers, int target) {
        int s = 0;
        int e = numbers.length - 1;

        while (s < e) {
            if (numbers[s] + numbers[e] == target)
                return new int[] { s + 1, e + 1 };
            else if (numbers[s] + numbers[e] > target)
                e--;
            else
                s++;
        }

        return new int[] { -1, -1 };
    }
}
