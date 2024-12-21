/**
 * Problem Statement:
 *     Given an integer array `nums`, an integer `indexDiff`, and an integer `valueDiff`, determine if there exist two distinct indices `i` and `j` such that:
 *     1. The absolute difference between the indices is at most `indexDiff` (i.e., `|i - j| <= indexDiff`).
 *     2. The absolute difference between the values at those indices is at most `valueDiff` (i.e., `|nums[i] - nums[j]| <= valueDiff`).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr[]` of integers of size `n`, where each element satisfies (-10^9 <= arr[i] <= 10^9).
 *     - Two integers `indexDiff` and `valueDiff` (0 <= indexDiff <= 10^5, 0 <= valueDiff <= 10^9).
 * 
 * Output:
 *     - A boolean value: true if there exist two indices `i` and `j` such that the absolute differences between the indices and values are both within the given bounds, false otherwise.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 1 2
 *     2 1
 *     Output:
 *     true
 * 
 *     Input:
 *     6
 *     1 5 9 1 5 9
 *     2 3
 *     Output:
 *     false
 * 
 *     Explanation:
 *     In the first example, `nums[0] == nums[3]` and `|0 - 3| = 3 <= 2`, `|1 - 1| = 0 <= 1`, hence the output is `true`.
 */

import java.util.Scanner;
import java.util.TreeSet;

public class j06ContainsDuplicatesIII {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }
        int indexDiff = in.nextInt(); // Input: maximum index difference
        int valueDiff = in.nextInt(); // Input: maximum value difference

        System.out.println(containsNearbyAlmostDuplicate(arr, indexDiff, valueDiff)); // Output the result
        in.close();
    }

    /**
     * Approach: Using TreeSet to maintain a sliding window
     * 
     * Intuition:
     * - We use a `TreeSet` to maintain a sorted window of elements within the current sliding window.
     * - For each element `nums[i]`, we check if there is any element `x` in the window such that the absolute difference between `x` and `nums[i]` is less than or equal to `valueDiff`.
     * - We also ensure that the window has at most `indexDiff` elements by removing elements that fall out of the window.
     * - We utilize the `floor` and `ceiling` methods of the `TreeSet` to efficiently check if there is any element close enough in value.
     * 
     * Time Complexity:
     * - O(n log n), where `n` is the size of the array. We perform a constant number of operations on the `TreeSet` for each element.
     * 
     * Space Complexity:
     * - O(n), as we are maintaining a sliding window of size at most `indexDiff`.
     * 
     * @param nums The input array of integers.
     * @param indexDiff The maximum allowable index difference.
     * @param valueDiff The maximum allowable value difference.
     * @return A boolean indicating if there exist two indices such that the absolute differences between the indices and values are within the given bounds.
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>(); // Create a TreeSet to maintain the sliding window

        for (int i = 0; i < nums.length; i++) {
            // If the window size exceeds `indexDiff`, remove the element that falls outside
            // the window
            if (i - indexDiff - 1 >= 0) {
                set.remove((long) nums[i - indexDiff - 1]);
            }

            // Find the closest smaller or equal element (floor) and the closest larger or
            // equal element (ceiling) in the window
            Long floor = set.floor((long) nums[i]);
            Long ceil = set.ceiling((long) nums[i]);

            // Check if the absolute difference between the current element and the floor
            // element is within the allowed `valueDiff`
            if (floor != null && (long) nums[i] - floor <= valueDiff)
                return true;

            // Check if the absolute difference between the current element and the ceil
            // element is within the allowed `valueDiff`
            if (ceil != null && ceil - (long) nums[i] <= valueDiff)
                return true;

            // Add the current element to the set (as we are keeping a sorted sliding
            // window)
            set.add((long) nums[i]);
        }
        return false; // Return false if no such pair is found
    }
}
