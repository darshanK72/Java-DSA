
/**
 * Problem Statement:
 * 
 *     Given an array `nums` and an integer `threshold`, find the smallest
 *     divisor such that the sum of the division results is less than or equal
 *     to the threshold.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element is a positive integer.
 *     - An integer `threshold` (1 <= threshold <= 10^6), representing the
 *       threshold value.
 * 
 * Output:
 *     - An integer representing the smallest divisor such that the sum of the
 *       division results is less than or equal to the threshold.
 * 
 * Example:
 *     Input:
 *     n = 5
 *     nums = [1, 2, 5, 9]
 *     threshold = 6
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The smallest divisor such that the sum of the division results is less
 *     than or equal to the threshold is 5.
 */

import java.util.Scanner;

public class j08SmallestDivisorGivenThreshold {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of elements in the array
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int threshold = in.nextInt(); // Threshold value

        // Output the result of the approach
        System.out.println(smallestDivisor(nums, threshold));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The smallest
     *   divisor must be at least 1, and at most the maximum element in the array.
     * - We perform binary search within this range to find the smallest divisor
     *   that allows the sum of the division results to be less than or equal to
     *   the threshold.
     * 
     * Time Complexity:
     * - O(n log(max(nums))). This is because we perform binary search on the range
     *   [1, max(nums)], and for each mid value, we iterate through the array to
     *   calculate the sum of the division results.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param nums The input array of numbers.
     * @param threshold The threshold value.
     * @return The smallest divisor such that the sum of the division results is
     *         less than or equal to the threshold.
     */
    public static int smallestDivisor(int[] nums, int threshold) {
        int s = 1;
        int e = 0;
        for (int i = 0; i < nums.length; i++)
            e = Math.max(e, nums[i]);
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (isPossible(nums, threshold, mid)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return s;
    }

    /**
     * Helper method to check if it is possible to have the sum of the division
     * results be less than or equal to the threshold with the given divisor.
     * 
     * @param nums The input array of numbers.
     * @param threshold The threshold value.
     * @param divisor The divisor to check feasibility.
     * @return True if it is possible to have the sum of the division results be
     *         less than or equal to the threshold with the given divisor,
     *         otherwise false.
     */
    public static boolean isPossible(int[] nums, int threshold, int divisor) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += Math.ceil((double) nums[i] / divisor);
        }
        return sum <= threshold;
    }
}