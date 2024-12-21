/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` and an integer `k`, you need to find the number of subarrays that contain exactly `k`
 *     distinct integers. A subarray is a contiguous part of the array.
 * 
 *     Return the number of subarrays that have exactly `k` distinct integers.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 2 * 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^9).
 *     - An integer `k` (1 <= k <= n), representing the number of distinct integers required.
 * 
 * Output:
 *     - An integer representing the number of subarrays that contain exactly `k` distinct integers.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 1 2
 *     2
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The subarrays with exactly 2 distinct integers are:
 *     [1, 2], [2, 1], [1, 2], [2, 1]
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j11SubarraysWithDifferentKIntegers {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the number of distinct integers required

        // Call your solution
        System.out.println(subarraysWithKDistinct(arr, k)); // Brute force approach
        System.out.println(subarraysWithKDistinctEfficient(arr, k)); // Optimized approach

        in.close();
    }

    /**
     * Approach: Brute Force Approach (Naive Approach)
     * 
     * Intuition:
     * - The brute force approach involves checking each subarray of `nums` and counting how many distinct integers are 
     *   present in each subarray. If the number of distinct integers is exactly `k`, we increase the result count.
     * - This approach uses a HashSet to track the distinct integers in each subarray. It checks every subarray starting at 
     *   each index `i` and extending up to the end of the array.
     * 
     * Time Complexity:
     * - O(n^2) where n is the length of the array. This is because we check each subarray for distinct elements, leading 
     *   to quadratic complexity.
     * 
     * Space Complexity:
     * - O(k) where k is the number of distinct elements in the subarray. This space is used by the HashSet to store the 
     *   distinct elements.
     * 
     * @param nums The input array of integers.
     * @param k The number of distinct integers required in the subarray.
     * @return The number of subarrays with exactly `k` distinct integers.
     */
    public static int subarraysWithKDistinct(int[] nums, int k) {
        int ans = 0;
        // Check each subarray starting from index i
        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> set = new HashSet<>();
            // For each subarray starting from i, check distinct elements
            for (int j = i; j < nums.length; j++) {
                set.add(nums[j]);
                if (set.size() == k) {
                    ans++; // If the subarray has exactly k distinct elements, increment the result
                }
            }
        }
        return ans;
    }

    /**
     * Approach: Optimized Sliding Window Approach
     * 
     * Intuition:
     * - The optimized approach leverages the sliding window technique to efficiently count the number of subarrays with 
     *   exactly `k` distinct integers. Instead of iterating through all possible subarrays, we compute the number of subarrays 
     *   that have at most `k` distinct integers and subtract the number of subarrays that have at most `k-1` distinct integers. 
     *   This gives the exact count of subarrays with exactly `k` distinct integers.
     * 
     * Time Complexity:
     * - O(n) where n is the number of elements in the array. The two sliding windows traverse the array once, making the 
     *   approach linear in time.
     * 
     * Space Complexity:
     * - O(k) where k is the number of distinct elements in the current window. This is used by the HashMap to store the 
     *   count of each element in the window.
     * 
     * @param nums The input array of integers.
     * @param k The number of distinct integers required in the subarray.
     * @return The number of subarrays with exactly `k` distinct integers.
     */
    public static int subarraysWithKDistinctEfficient(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    /**
     * Helper Function: Count Subarrays with At Most K Distinct Integers
     * 
     * Intuition:
     * - This helper function counts how many subarrays in the input array have at most `k` distinct integers.
     * - We maintain a sliding window using two pointers, `i` and `j`, and a HashMap to track the count of each element in the 
     *   window. We expand the window by moving `i` and shrink it by moving `j` when the number of distinct elements exceeds `k`.
     * 
     * Time Complexity:
     * - O(n) where n is the length of the array. The sliding window traverses the array only once.
     * 
     * Space Complexity:
     * - O(k) due to the space used by the HashMap to store the distinct elements in the current window.
     * 
     * @param nums The input array of integers.
     * @param k The number of distinct integers required in the subarray.
     * @return The number of subarrays with at most `k` distinct integers.
     */
    public static int atMostKDistinct(int[] nums, int k) {
        int ans = 0;
        int j = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // Add the current element to the map and increase its count
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            // Shrink the window if the number of distinct elements exceeds k
            while (map.size() > k) {
                map.put(nums[j], map.get(nums[j]) - 1);
                if (map.get(nums[j]) == 0) {
                    map.remove(nums[j]);
                }
                j++; // Move the left pointer to reduce the number of distinct elements
            }

            // Add the number of subarrays ending at i with at most k distinct integers
            ans += (i - j + 1);
        }
        return ans;
    }
}
