/**
 * Problem Statement:
 * 
 *     Given an array `nums` of `n` integers, find the `k`-th smallest distance between all the possible 
 *     pairs `(nums[i], nums[j])`, where `0 <= i < j < n`. The distance of a pair is defined as 
 *     `|nums[i] - nums[j]|` (the absolute difference between the two elements).
 * 
 * Input:
 *     - An array `nums` of size `n` (1 <= n <= 10^4), where each element satisfies (0 <= nums[i] <= 10^6).
 *     - An integer `k` (1 <= k <= n * (n - 1) / 2), representing the rank of the smallest distance to find.
 * 
 * Output:
 *     - The `k`-th smallest distance between all pairs in the array.
 * 
 * Example:
 *     Input:
 *     nums = [1, 3, 1]
 *     k = 1
 * 
 *     Output:
 *     0
 * 
 *     Explanation:
 *     The possible pairwise distances are [0, 2, 2]. The 1st smallest distance is 0.
 */

import java.util.Arrays;

public class j10FindKthSmallestPairDistance {

    public static void main(String[] args) {
        // Example Input
        int[] nums = { 1, 3, 1 };
        int k = 1;

        // Print result
        System.out.println("The " + k + "-th smallest pair distance is: "
                + smallestDistancePair(nums, k));
    }

    /**
     * Approach: Binary Search with Sliding Window
     * 
     * Intuition:
     * - The problem asks for the `k`-th smallest pairwise distance, which suggests that we can use 
     *   binary search over the range of possible distances.
     * - Let `d` be the target distance. For a given `d`, count how many pairs `(nums[i], nums[j])`
     *   have `|nums[i] - nums[j]| <= d`. This count can be computed efficiently using a sliding 
     *   window approach on a sorted array.
     * - Adjust the search space based on the value of `count`. If `count < k`, increase `d` to search
     *   for larger distances; otherwise, decrease `d`.
     * 
     * Steps:
     * 1. Sort the input array `nums`.
     * 2. Use binary search to find the smallest distance `d` such that the count of pairs with 
     *    distance `<= d` is at least `k`.
     * 3. Use a sliding window approach to efficiently count the number of pairs with distance `<= d`.
     * 
     * Time Complexity:
     * - O(n log n) for sorting + O(n log(max_distance)) for binary search + O(n) per distance check.
     * - Overall: O(n log n + n log(max_distance)).
     * 
     * Space Complexity:
     * - O(1), as the solution operates in-place after sorting.
     * 
     * @param nums The input array of integers.
     * @param k The rank of the smallest distance to find.
     * @return The `k`-th smallest pairwise distance.
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums); // Step 1: Sort the array
        int n = nums.length;
        int l = 0; // Minimum possible distance
        int r = nums[n - 1] - nums[0]; // Maximum possible distance
        int result = 0;

        // Step 2: Binary search over the range of distances
        while (l <= r) {
            int mid = (l + r) / 2; // Midpoint of the current range
            int count = slidingWindowCount(nums, mid); // Count pairs with distance <= mid

            if (count < k) {
                l = mid + 1; // Search for larger distances
            } else {
                result = mid; // Record the candidate distance
                r = mid - 1; // Search for smaller distances
            }
        }

        return result; // Return the `k`-th smallest distance
    }

    /**
     * Helper Function: Count Pairs with Distance <= k
     * 
     * Intuition:
     * - Given a sorted array, the pairwise distances between elements increase as the indices 
     *   of the elements increase.
     * - Use a sliding window to count the number of pairs `(nums[i], nums[j])` where 
     *   `nums[j] - nums[i] <= k`. For each `j`, maintain a pointer `i` such that all pairs 
     *   `(i, j)` within the window satisfy the condition.
     * - For each `j`, the number of valid pairs is `(j - i)`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param nums The sorted input array.
     * @param k The maximum distance to consider.
     * @return The count of pairs with distance <= k.
     */
    private static int slidingWindowCount(int[] nums, int k) {
        int count = 0; // Initialize the count of pairs
        int n = nums.length;
        int i = 0; // Left pointer of the sliding window

        // Iterate over the right pointer of the window
        for (int j = 1; j < n; j++) {
            // Move the left pointer to maintain the distance condition
            while (nums[j] - nums[i] > k) {
                i++;
            }
            count += j - i; // Add the count of valid pairs ending at `j`
        }

        return count; // Return the total count of valid pairs
    }
}
