/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` and an integer `x`, you need to find the minimum number of operations required
 *     to reduce `x` to zero by removing some elements from the array. In one operation, you can remove an element from
 *     the front or the back of the array, and the sum of the remaining elements should be equal to `x`.
 * 
 *     Return the minimum number of operations required to reduce `x` to zero. If it's not possible to do so, return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^4).
 *     - An integer `x` (1 <= x <= 10^9), representing the target sum to reduce to zero.
 * 
 * Output:
 *     - An integer representing the minimum number of operations required to reduce `x` to zero, or -1 if it's not possible.
 * 
 * Example:
 *     Input:
 *     5
 *     1 1 4 2 3
 *     5
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The subarray [4, 1] sums up to 5, and we can remove it from either side to achieve the result.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j10MinOpsToReduceXToZero {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the target sum to reduce to zero

        // Call your solution
        System.out.println(minOperations(arr, k)); // Brute force approach
        System.out.println(minOperationsEfficient(arr, k)); // Optimized approach

        in.close();
    }

    /**
     * Approach: Brute Force Approach (Naive Approach)
     * 
     * Intuition:
     * - The brute force approach involves computing all possible subarrays with sum equal to `target - x` (i.e., the sum
     *   of the remaining elements after removal). We maintain a hashmap that stores the prefix sum and check if the difference
     *   between the current sum and `target - x` is already present.
     * - This approach runs in O(n) time, iterating over the array and checking each possible subarray using the hashmap.
     * 
     * Time Complexity:
     * - O(n) where n is the number of elements in the array. We traverse the array once while updating the hashmap.
     * 
     * Space Complexity:
     * - O(n) due to the usage of the HashMap to store prefix sums.
     * 
     * @param nums The input array of integers.
     * @param x The target sum to reduce to zero.
     * @return The minimum number of operations, or -1 if it is not possible.
     */
    public static int minOperations(int[] nums, int x) {
        int target = 0;
        for (int n : nums)
            target += n; // Calculate the sum of the entire array
        int k = target - x; // Subtract x from the total sum to get the target sum of remaining elements
        if (k == 0)
            return nums.length; // If target sum is zero, the entire array is the answer
        if (k < 0)
            return -1; // If the target sum is negative, it's not possible to achieve the sum x

        HashMap<Integer, Integer> map = new HashMap<>();
        int s = 0;
        int maxL = -1;
        map.put(0, -1); // Initialize hashmap with base case
        for (int i = 0; i < nums.length; i++) {
            s += nums[i]; // Update the prefix sum
            if (map.containsKey(s - k)) {
                maxL = Math.max(maxL, i - map.get(s - k)); // Update max length of subarray
            }
            if (!map.containsKey(s)) {
                map.put(s, i); // Store the current prefix sum with its index
            }
        }

        return maxL == -1 ? maxL : nums.length - maxL; // If no solution found, return -1
    }

    /**
     * Approach: Optimized Sliding Window Approach
     * 
     * Intuition:
     * - This approach uses the sliding window (two-pointer) technique to dynamically track a subarray whose sum equals the target.
     * - We maintain a window where we expand the right pointer `i` to increase the sum and shrink the left pointer `j` when the sum
     *   exceeds the target.
     * - If we find a valid subarray whose sum equals `k`, we calculate the length of the window and update the maximum length found.
     * 
     * Time Complexity:
     * - O(n) where n is the number of elements in the array. The left and right pointers move through the array only once.
     * 
     * Space Complexity:
     * - O(1) since only a few variables are used, and no extra space is required besides the input.
     * 
     * @param nums The input array of integers.
     * @param x The target sum to reduce to zero.
     * @return The minimum number of operations, or -1 if it is not possible.
     */
    public static int minOperationsEfficient(int[] nums, int x) {
        int target = 0;
        for (int n : nums)
            target += n; // Calculate the sum of the entire array
        int k = target - x; // Subtract x from the total sum to get the target sum of remaining elements
        if (k == 0)
            return nums.length; // If target sum is zero, the entire array is the answer
        if (k < 0)
            return -1; // If the target sum is negative, it's not possible to achieve the sum x

        int j = 0;
        int sum = 0;
        int maxL = -1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Add the current element to the sum
            while (sum > k && j <= i) {
                sum -= nums[j]; // Shrink the window from the left
                j++;
            }
            if (sum == k) {
                maxL = Math.max(maxL, i - j + 1); // Update the max length of subarray with sum k
            }
        }

        return maxL == -1 ? -1 : nums.length - maxL; // Return the result
    }
}
