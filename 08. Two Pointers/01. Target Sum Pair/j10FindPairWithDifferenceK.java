/**
 * Problem Statement:
 * 
 *     Given an array of integers, you are tasked with finding a pair of numbers whose difference is exactly `k`. 
 *     You need to return the first such pair found.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), the target difference.
 * 
 * Output:
 *     - If a pair is found, return an array of two integers representing the pair.
 *     - If no such pair exists, return an empty array.
 * 
 * Example:
 *     Input:
 *     5
 *     1 8 12 5 6
 *     7
 *     Output:
 *     [1, 8]
 *     
 *     Explanation:
 *     The pair (1, 8) has a difference of 7, which is equal to the target `k = 7`.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class j10FindPairWithDifferenceK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of elements in the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: the array elements
        }
        int k = in.nextInt(); // Input: the target difference

        // Call the brute force solution
        System.out.printf("Brute Force Solution: %s\n", Arrays.toString(findPairDifferenceK(arr, k)));

        // Call the HashMap solution
        System.out.printf("HashMap Solution: %s\n", Arrays.toString(findPairDifferenceKHashMap(arr, k)));

        // Call the optimized solution
        System.out.printf("Optimized Solution: %s\n", Arrays.toString(findPairDifferenceKEfficient(arr, k)));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - In this approach, we check every pair of elements in the array and calculate the difference.
     * - If the difference between any two elements equals the target value `k`, we return that pair.
     * - Since we check every possible pair, this approach is very straightforward but inefficient for large arrays.
     * 
     * Time Complexity:
     * - O(n^2), where n is the number of elements in the array, as we use nested loops to check every pair.
     * 
     * Space Complexity:
     * - O(1), as we do not use any extra space apart from the pair storage.
     * 
     * @param arr The input array of numbers.
     * @param k The target difference.
     * @return A pair of integers with the given difference, or an empty array if no such pair exists.
     */
    public static int[] findPairDifferenceK(int[] arr, int k) {
        int n = arr.length;
        // Check every pair of elements in the array
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] - arr[i] == k) {
                    return new int[] { arr[i], arr[j] };
                }
            }
        }
        return new int[] {}; // Return empty array if no pair is found
    }

    /**
     * Approach 2: HashMap Approach
     * 
     * Intuition:
     * - We can use a HashMap to store each element as we iterate through the array.
     * - For each element `x`, we check if `x + k` or `x - k` exists in the map.
     * - If either of these conditions is true, then we have found a pair with the required difference.
     * - This approach efficiently checks for pairs using the HashMap's constant-time lookups.
     * 
     * Time Complexity:
     * - O(n), where n is the number of elements, because we traverse the array once and perform constant-time operations.
     * 
     * Space Complexity:
     * - O(n), as we use a HashMap to store the array elements.
     * 
     * @param nums The input array of numbers.
     * @param target The target difference.
     * @return A pair of integers with the given difference, or an empty array if no such pair exists.
     */
    public static int[] findPairDifferenceKHashMap(int[] nums, int target) {
        int[] indices = new int[2]; // To store the result pair
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return indices; // Return empty array for invalid cases
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            // Check if nums[i] + target or nums[i] - target exists in the map
            if (map.containsKey(nums[i] + target)) {
                indices[0] = map.get(nums[i] + target) + 1; // 1-based index
                indices[1] = i + 1; // 1-based index
                return indices;
            }
            if (map.containsKey(nums[i] - target)) {
                indices[0] = map.get(nums[i] - target) + 1; // 1-based index
                indices[1] = i + 1; // 1-based index
                return indices;
            }
            map.put(nums[i], i); // Store the index of the current number in the map
        }
        return indices; // Return empty array if no pair is found
    }

    /**
     * Approach 3: Optimized Approach (Two Pointer Technique)
     * 
     * Intuition:
     * - First, we sort the array to enable the two-pointer technique.
     * - The left pointer starts from the beginning, and the right pointer starts from the next element.
     * - We calculate the difference between the two elements pointed to by the pointers. If the difference matches the target, we return the pair.
     * - If the difference is smaller than the target, we move the right pointer forward to increase the difference.
     * - If the difference is larger than the target, we move the left pointer forward to decrease the difference.
     * 
     * Time Complexity:
     * - O(n log n), where n is the number of elements due to the sorting step. The two-pointer traversal takes O(n).
     * 
     * Space Complexity:
     * - O(1), as we do not use any extra space other than the pointers and a few variables.
     * 
     * @param arr The input array of numbers.
     * @param k The target difference.
     * @return A pair of integers with the given difference, or an empty array if no such pair exists.
     */
    public static int[] findPairDifferenceKEfficient(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr); // Sort the array to use two-pointer technique
        int left = 0, right = 1; // Initialize the pointers
        while (right < n) {
            int diff = arr[right] - arr[left];
            if (diff == k && left != right) {
                return new int[] { arr[left], arr[right] }; // Return the pair if the difference matches
            }
            if (diff < k) {
                right++; // Move the right pointer forward to increase the difference
            } else {
                left++; // Move the left pointer forward to decrease the difference
            }
            if (left == right) {
                right++; // Ensure the right pointer is always ahead of the left pointer
            }
        }
        return new int[] {}; // Return empty array if no pair is found
    }
}
