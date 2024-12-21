/**
 * Problem Statement:
 * 
 *     Given an array of integers and a target value `k`, count the number of pairs in the array whose sum is less than or equal to `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), representing the target sum.
 * 
 * Output:
 *     - The number of pairs whose sum is less than or equal to `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     6
 *     Output:
 *     10
 * 
 *     Explanation:
 *     The pairs whose sum is less than or equal to 6 are:
 *     [1, 2], [1, 3], [1, 4], [1, 5], [2, 3], [2, 4], [2, 5], [3, 4], [3, 5], [4, 5].
 */

import java.util.Arrays;
import java.util.Scanner;

public class j07CountPairsLessOrEqualTarget {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the target sum
        System.out.println(twoSumLessOrEqualTarget(arr, k)); // Call the solution method

        // Call the optimized solution method (if applicable)
        System.out.println(twoSumLessOrEqualTargetEfficient(arr, k)); // Call optimized solution

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - The brute force approach checks every possible pair of elements in the array.
     * - For each pair (i, j), we check if the sum of the elements is less than or equal to the target `k`.
     * - If it is, we increment the count.
     * 
     * Time Complexity:
     * - O(n^2) since we are iterating over all pairs of elements in the array.
     * 
     * Space Complexity:
     * - O(1) for space as no additional space is used except for a counter.
     * 
     * @param nums The input array of integers.
     * @param target The target sum to compare the pair sums against.
     * @return The number of pairs whose sum is less than or equal to the target.
     */
    public static int twoSumLessOrEqualTarget(int[] nums, int target) {
        int count = 0; // Initialize the count variable
        // Iterate over all pairs of elements
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] <= target) // If the sum is less than or equal to target
                    count++; // Increment the count
            }
        }
        return count; // Return the total count
    }

    /**
     * Approach 2: Two Pointer Technique (Optimized)
     * 
     * Intuition:
     * - The array is sorted first. Then, we use the two-pointer technique to efficiently find pairs whose sum is less than or equal to the target `k`.
     * - The idea is to use one pointer at the start (`s`) and the other at the end (`e`) of the sorted array.
     * - If the sum of the elements at these pointers is less than or equal to `k`, we know that all pairs from `s` to `e` will have a sum less than or equal to `k`.
     * - This is because if `arr[s] + arr[e] <= k`, then all pairs with `arr[s]` and elements between `arr[s]` and `arr[e]` will also satisfy the condition.
     * - We then increment `s` to check the next potential pair.
     * - If the sum exceeds `k`, we decrement `e` to reduce the sum.
     * 
     * Time Complexity:
     * - O(n log n) for sorting the array.
     * - O(n) for the two-pointer traversal.
     * - Overall time complexity is O(n log n).
     * 
     * Space Complexity:
     * - O(1) as no extra space is used except for the counter.
     * 
     * @param nums The input array of integers.
     * @param target The target sum to compare the pair sums against.
     * @return The number of pairs whose sum is less than or equal to the target.
     */
    public static int twoSumLessOrEqualTargetEfficient(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array
        int count = 0; // Initialize the count variable
        int s = 0; // Start pointer
        int e = nums.length - 1; // End pointer

        // Use two-pointer technique to count valid pairs
        while (s < e) {
            int sum = nums[s] + nums[e]; // Calculate the sum of the pair
            if (sum <= target) {
                count += (e - s); // If sum is valid, add all pairs between s and e
                s++; // Move the start pointer inward
            } else {
                e--; // If sum exceeds target, move the end pointer inward
            }
        }
        return count; // Return the total count of pairs
    }
}
