/**
 * Problem Statement:
 * 
 *     Given a binary array `nums` and an integer `goal`, you need to count the number of subarrays that sum up to `goal`.
 *     A subarray is a contiguous part of the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element is either 0 or 1.
 *     - An integer `goal` (0 <= goal <= n).
 * 
 * Output:
 *     - The number of subarrays that sum up to `goal`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 0 1 0 1
 *     2
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The subarrays with sum 2 are:
 *     [1, 0, 1], [0, 1, 0], [1, 0, 1], and [1, 0, 1] (sum of 2).
 */

import java.util.HashMap;
import java.util.Scanner;

public class j14CountBinarySubarraysWithSumK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the binary array
        }
        int k = in.nextInt(); // Input: the target sum
        System.out.println(numSubarraysWithSumHashMap(arr, k)); // Using HashMap method
        System.out.println(numSubarraysWithSum(arr, k)); // Using sliding window method
        in.close();
    }

    /**
     * Approach 1: Using HashMap to count subarrays with sum equal to k
     * 
     * Intuition:
     * - We use the concept of prefix sums, where we maintain a running sum of the array elements as we iterate.
     * - For each element, we update the running sum and check how many times the difference `sum - goal` has appeared in the HashMap.
     * - This difference indicates that a subarray with sum equal to `goal` exists.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array. We iterate over the array once and perform constant-time operations (HashMap lookups and updates) for each element.
     * 
     * Space Complexity:
     * - O(n), where `n` is the length of the array, due to storing prefix sums in the HashMap.
     * 
     * @param nums The input array of binary numbers.
     * @param goal The target sum for the subarrays.
     * @return The number of subarrays that sum up to the target.
     */
    public static int numSubarraysWithSumHashMap(int[] nums, int goal) {
        int sum = 0; // Running sum
        int count = 0; // Result count
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: There's one way to have a sum of 0 before starting the iteration

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Update running sum
            count += map.getOrDefault(sum - goal, 0); // Add number of subarrays found with sum equal to goal
            map.put(sum, map.getOrDefault(sum, 0) + 1); // Store the running sum in the HashMap
        }

        return count;
    }

    /**
     * Approach 2: Using Sliding Window to count subarrays with sum equal to k
     * 
     * Intuition:
     * - The idea is to count subarrays with sums less than or equal to `goal` and then use this to compute the count of subarrays equal to `goal`.
     * - We count subarrays with sum less than or equal to `goal` and subtract the count of subarrays with sum less than or equal to `goal-1`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array. The sliding window moves through the array in linear time.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used apart from the input array.
     * 
     * @param nums The input array of binary numbers.
     * @param goal The target sum for the subarrays.
     * @return The number of subarrays that sum up to the target.
     */
    public static int numSubarraysWithSum(int[] nums, int goal) {
        return subarraysLessThanGoal(nums, goal) - subarraysLessThanGoal(nums, goal - 1);
    }

    /**
     * Helper Function: Count subarrays with sum less than or equal to a given goal
     * 
     * Intuition:
     * - The sliding window approach is used here: We maintain a window of elements whose sum is less than or equal to the `goal`.
     * - If the sum exceeds the `goal`, we shrink the window from the left to make it valid again.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array, as both pointers `i` and `j` move at most `n` times.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param nums The input array of binary numbers.
     * @param goal The target sum for the subarrays.
     * @return The number of subarrays whose sum is less than or equal to the target.
     */
    public static int subarraysLessThanGoal(int[] nums, int goal) {
        int ans = 0;
        int j = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Update the running sum
            while (sum > goal && j <= i) { // Shrink the window if the sum exceeds the goal
                sum -= nums[j];
                j++;
            }
            ans += i - j + 1; // Add the number of subarrays ending at index `i` that are valid
        }

        return ans;
    }
}
