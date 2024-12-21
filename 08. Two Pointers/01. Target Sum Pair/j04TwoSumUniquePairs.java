/**
 * Problem Statement:
 * 
 *     Given an array `nums` of integers and a target value `k`, find the number of unique pairs
 *     (i, j) such that `nums[i] + nums[j] == k` and `i < j`. A unique pair is defined such that
 *     the pair of numbers should not repeat in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array `nums`.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `k` (2 <= k <= 2 * 10^5), representing the target sum.
 * 
 * Output:
 *     - The number of unique pairs (i, j) such that `nums[i] + nums[j] == k`.
 * 
 * Example:
 *     Input:
 *     6
 *     1 2 3 4 3 5
 *     6
 *     Output:
 *     2
 *     
 *     Explanation:
 *     The valid unique pairs are (1, 5) and (2, 4), both of which sum to 6.
 */

import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;

public class j04TwoSumUniquePairs {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }
        int target = in.nextInt(); // Input: the target sum

        // Call your solution
        System.out.println(countUniquePairs(nums, target));

        // Call the HashMap-based solution
        System.out.println(countUniquePairsHashMap(nums, target));

        // Call the optimized efficient solution
        System.out.println(countUniquePairsEfficient(nums, target));

        in.close();
    }

    /**
     * Approach 1: Brute Force with HashSet to Ensure Uniqueness
     * 
     * Intuition:
     * - The brute force approach checks all pairs from the array to find pairs whose sum equals the target.
     * - We use a HashSet to store each unique pair by concatenating the pair elements as a string and ensuring
     *   no duplicate pairs are counted.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the number of elements in the array. We are checking all pairs of elements.
     * 
     * Space Complexity:
     * - O(n), for storing the HashSet to track unique pairs.
     * 
     * @param nums The input array of numbers.
     * @param k The target sum.
     * @return The number of unique pairs whose sum equals `k`.
     */
    public static int countUniquePairs(int[] nums, int k) {
        int count = 0;
        HashSet<String> set = new HashSet<>(); // Set to track unique pairs
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == k && !set.contains(nums[i] + "#" + nums[j])) {
                    count++; // Increment count if a valid pair is found
                    set.add(nums[i] + "#" + nums[j]); // Add the pair to the set to ensure uniqueness
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Using HashMap to Track Seen and Used Numbers
     * 
     * Intuition:
     * - This approach uses two HashSets: one (`seen`) to store elements that have been visited so far,
     *   and another (`used`) to track numbers that have already been part of a valid pair.
     * - For each element in `nums`, we check if the complementary value (`k - num`) has been seen already,
     *   and if neither number has been used in a valid pair. If so, we increment the count and mark both numbers as used.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We loop through the array once and perform constant-time set operations.
     * 
     * Space Complexity:
     * - O(n), for the two HashSets used to store the numbers.
     * 
     * @param nums The input array of numbers.
     * @param k The target sum.
     * @return The number of unique pairs whose sum equals `k`.
     */
    public static int countUniquePairsHashMap(int[] nums, int k) {
        HashSet<Integer> seen = new HashSet<>(); // Set to track seen numbers
        HashSet<Integer> used = new HashSet<>(); // Set to track used numbers
        int count = 0;

        // Iterate through the array
        for (int num : nums) {
            int target = k - num; // Calculate the complement

            // Check if the complement exists in seen and has not been used
            if (seen.contains(target) && !used.contains(num) && !used.contains(target)) {
                count++; // Increment count if a valid pair is found
                used.add(num); // Mark the current number as used
                used.add(target); // Mark the complement as used
            }

            // Add the current number to the seen set
            seen.add(num);
        }
        return count;
    }

    /**
     * Approach 3: Optimized Two-Pointer Technique
     * 
     * Intuition:
     * - First, sort the array. Then, use two pointers: one starting at the beginning (`i`) and the other at the end (`j`).
     * - If the sum of the elements at the pointers equals the target, increment the count and move both pointers inward.
     * - If the sum is less than the target, move the left pointer rightward to increase the sum.
     * - If the sum is greater than the target, move the right pointer leftward to decrease the sum.
     * - This approach avoids checking all pairs and only considers valid ones, making it more efficient.
     * 
     * Time Complexity:
     * - O(n log n), where `n` is the size of the array, due to the sorting step. The two-pointer traversal is O(n).
     * 
     * Space Complexity:
     * - O(1), since we do not use any extra space apart from a few variables.
     * 
     * @param nums The input array of numbers.
     * @param k The target sum.
     * @return The number of unique pairs whose sum equals `k`.
     */
    public static int countUniquePairsEfficient(int[] nums, int k) {
        int count = 0;
        int i = 0;
        int j = nums.length - 1;

        // Sort the array first
        Arrays.sort(nums);

        // Use two pointers to find valid pairs
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == k) {
                count++; // Increment the count if the sum is equal to the target
                i++; // Move the left pointer to the right
                j--; // Move the right pointer to the left

                // Skip duplicates to ensure unique pairs
                while (i < j && nums[i] == nums[i - 1])
                    i++;
                while (i < j && nums[j] == nums[j + 1])
                    j--;
            } else if (sum > k) {
                j--; // Decrease the sum by moving the right pointer to the left
            } else {
                i++; // Increase the sum by moving the left pointer to the right
            }
        }
        return count;
    }
}