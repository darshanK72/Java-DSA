/**
 * Problem Statement:
 * 
 *     Given an array of integers, determine if there exists a pair of integers whose absolute difference 
 *     is exactly `x`. If such a pair exists, return 1; otherwise, return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `x` (1 <= x <= 10^5), the target absolute difference.
 * 
 * Output:
 *     - Return 1 if there exists a pair whose absolute difference is `x`.
 *     - Return -1 if no such pair exists.
 * 
 * Example:
 *     Input:
 *     5
 *     1 5 3 4 2
 *     2
 *     Output:
 *     1
 *     
 *     Explanation:
 *     The pair (1, 3) has an absolute difference of 2, which is equal to the target `x = 2`.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class j11FindPairsWithAbsDifferenceK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of elements in the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: the array elements
        }
        int x = in.nextInt(); // Input: the target absolute difference

        // Call the brute force solution
        System.out.printf("Brute Force Solution: %d\n", findPair(n, x, arr));

        // Call the HashSet solution
        System.out.printf("HashSet Solution: %d\n", countKDifferenceHashSet(arr, x));

        // Call the optimized solution
        System.out.printf("Optimized Solution: %d\n", findPairEfficient(n, x, arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - In this approach, we iterate through all pairs of elements in the array.
     * - We calculate the absolute difference between each pair and check if it matches the target `x`.
     * - If we find such a pair, we return 1; otherwise, we return -1.
     * - This approach is simple but inefficient for large arrays as it checks all pairs.
     * 
     * Time Complexity:
     * - O(n^2), where n is the number of elements in the array, as we check all pairs using nested loops.
     * 
     * Space Complexity:
     * - O(1), as we do not use any extra space other than the pair checking.
     * 
     * @param n The size of the array.
     * @param x The target absolute difference.
     * @param arr The input array of numbers.
     * @return 1 if a pair is found, otherwise -1.
     */
    public static int findPair(int n, int x, int[] arr) {
        // Check every pair for absolute difference equal to x
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(arr[i] - arr[j]) == x) {
                    return 1; // Return 1 if the absolute difference matches x
                }
            }
        }
        return -1; // Return -1 if no such pair is found
    }

    /**
     * Approach 2: HashSet Approach
     * 
     * Intuition:
     * - We use a HashSet to store elements as we iterate through the array.
     * - For each element `x`, we check if either `x + k` or `x - k` is already in the set.
     * - If either condition is true, then we have found a pair with the required absolute difference.
     * - This method avoids the need to check all pairs, making it more efficient than the brute force approach.
     * 
     * Time Complexity:
     * - O(n), where n is the number of elements, because we traverse the array once and perform constant-time operations for each element.
     * 
     * Space Complexity:
     * - O(n), as we use a HashSet to store the elements.
     * 
     * @param nums The input array of numbers.
     * @param k The target absolute difference.
     * @return 1 if a pair is found, otherwise -1.
     */
    public static int countKDifferenceHashSet(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        // Traverse the array to check for each element's pair
        for (int i = 0; i < nums.length; i++) {
            // Check if the pair exists in the set
            if (set.contains(nums[i] - k) || set.contains(nums[i] + k)) {
                return 1; // Return 1 if the pair with the required difference is found
            }
            set.add(nums[i]); // Add the current element to the set
        }
        return -1; // Return -1 if no such pair is found
    }

    /**
     * Approach 3: Optimized Approach (Two-pointer technique after sorting)
     * 
     * Intuition:
     * - First, we sort the array to enable the use of a two-pointer technique.
     * - The left pointer starts from the beginning, and the right pointer starts from the next element.
     * - We calculate the absolute difference between the two elements pointed to by the pointers. 
     * - If the difference matches the target `x`, we return the pair.
     * - If the difference is smaller than `x`, we move the right pointer forward to increase the difference.
     * - If the difference is larger than `x`, we move the left pointer forward to decrease the difference.
     * - This approach efficiently finds the pair with a time complexity of O(n log n) due to sorting.
     * 
     * Time Complexity:
     * - O(n log n), where n is the number of elements due to the sorting step. The two-pointer traversal takes O(n).
     * 
     * Space Complexity:
     * - O(1), as we only use the pointers and a few variables.
     * 
     * @param n The size of the array.
     * @param x The target absolute difference.
     * @param arr The input array of numbers.
     * @return 1 if a pair is found, otherwise -1.
     */
    public static int findPairEfficient(int n, int x, int[] arr) {
        Arrays.sort(arr); // Sort the array to use the two-pointer technique
        int left = 0, right = 1; // Initialize the pointers
        while (right < n) {
            int diff = Math.abs(arr[right] - arr[left]);
            if (diff == x && left != right) {
                return 1; // Return 1 if the absolute difference matches x
            }
            if (diff < x) {
                right++; // Move the right pointer to increase the difference
            } else {
                left++; // Move the left pointer to decrease the difference
            }
            if (left == right) {
                right++; // Ensure the right pointer is ahead of the left pointer
            }
        }
        return -1; // Return -1 if no such pair is found
    }
}
