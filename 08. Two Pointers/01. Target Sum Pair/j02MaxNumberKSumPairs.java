/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums` and an integer `k`, you need to find the maximum number of 
 *     pairs of numbers from the array whose sum is equal to `k`. Each number in the array can only be 
 *     used once in a pair. After a number is used in a pair, it cannot be reused.
 *     
 *     Your task is to find the total number of such valid pairs.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `k` (2 <= k <= 2 * 10^5) representing the target sum for the pairs.
 * 
 * Output:
 *     - The number of valid pairs whose sum is equal to `k`.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     5
 *     Output:
 *     2
 *     
 *     Explanation:
 *     The valid pairs are (1, 4) and (2, 3), both of which sum to 5.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j02MaxNumberKSumPairs {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }
        int target = in.nextInt(); // Input: the target sum

        // Call your solution
        System.out.println(maxOperations(nums, target));

        // Call optimized solution (if applicable)
        System.out.println(maxOperationsEfficient(nums, target));

        in.close();
    }

    /**
     * Approach: Brute Force Solution
     * 
     * Intuition:
     * - The brute force solution checks every pair in the array to see if their sum equals `k`.
     * - If a valid pair is found, both numbers are marked as used (by setting them to `-1`).
     * - This method doesn't utilize any optimizations, such as sorting or using a hash map.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the array. This is due to the use of nested loops to check all pairs.
     * 
     * Space Complexity:
     * - O(1), since no extra space is used besides a few variables.
     * 
     * @param nums The input array of numbers.
     * @param k The target sum to find pairs for.
     * @return The total number of pairs whose sum equals `k`.
     */
    public static int maxOperations(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) // Skip already used elements
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == -1) // Skip already used elements
                    continue;
                if (nums[i] + nums[j] == k) {
                    nums[i] = -1; // Mark the element as used
                    nums[j] = -1; // Mark the element as used
                    count++; // Increment the count of pairs
                    break; // Move to the next element
                }
            }
        }
        return count;
    }

    /**
     * Approach: Optimized Two-Pointer Solution
     * 
     * Intuition:
     * - First, we sort the array so that we can efficiently find pairs using two pointers.
     * - We initialize one pointer at the beginning (`s`) and the other at the end (`e`) of the array.
     * - If the sum of the elements at the two pointers equals `k`, we have found a valid pair.
     * - If the sum is less than `k`, we move the left pointer to the right to increase the sum.
     * - If the sum is greater than `k`, we move the right pointer to the left to decrease the sum.
     * - This approach avoids checking every pair, making it more efficient than the brute force approach.
     * 
     * Time Complexity:
     * - O(n log n), where `n` is the length of the array. The sorting step takes O(n log n), 
     *   and the two-pointer traversal takes O(n).
     * 
     * Space Complexity:
     * - O(1), since we are using constant extra space apart from the input array.
     * 
     * @param nums The input array of numbers.
     * @param k The target sum to find pairs for.
     * @return The total number of pairs whose sum equals `k`.
     */
    public static int maxOperationsEfficient(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array first
        int s = 0;
        int e = nums.length - 1;
        int count = 0;

        // Use two pointers to find valid pairs
        while (s < e) {
            if (nums[s] + nums[e] == k) {
                nums[s] = -1; // Mark the element as used
                nums[e] = -1; // Mark the element as used
                s++; // Move the left pointer to the right
                e--; // Move the right pointer to the left
                count++; // Increment the count of pairs
            } else if (nums[s] + nums[e] > k) {
                e--; // Decrease the sum by moving the right pointer to the left
            } else {
                s++; // Increase the sum by moving the left pointer to the right
            }
        }

        return count;
    }
}
