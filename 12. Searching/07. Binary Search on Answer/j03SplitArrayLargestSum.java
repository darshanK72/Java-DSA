/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums` and an integer `k`, you need to divide the array into `k` non-empty
 *     continuous subarrays to minimize the largest sum of any subarray.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^9).
 *     - An integer `k` (1 <= k <= n), representing the number of subarrays.
 * 
 * Output:
 *     - An integer representing the minimized largest sum of any subarray.
 * 
 * Example:
 *     Input:
 *     5
 *     7 2 5 10 8
 *     2
 *     Output:
 *     18
 * 
 *     Explanation:
 *     There are four ways to split nums into two subarrays.
 *     The best way is to split it into [7, 2, 5] and [10, 8], where the largest sum among the two subarrays is minimized.
 */

import java.util.Scanner;

public class j03SplitArrayLargestSum {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of books
        int[] arr = new int[n];

        // Reading the number of pages in each book
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int k = in.nextInt(); // Number of students

        // Output the result of the approach
        System.out.println(splitArray(arr, k));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. We need to find the minimum
     *   largest sum of any subarray when the array is split into `k` subarrays.
     * - We start with the minimum possible sum as the maximum number in the array and the maximum
     *   possible sum as the sum of all elements in the array.
     * - We perform binary search to find the optimal split.
     * 
     * Time Complexity:
     * - O(n log(sum(arr))). The binary search runs in O(log(sum(arr))) and for each iteration.
     * 
     * Space Complexity:
     * - O(1). We are using a constant amount of extra space.
     * 
     * @param arr The input array of integers.
     * @param k The number of subarrays.
     * @return The minimized largest sum of any subarray.
     */
    public static int splitArray(int[] arr, int k) {
        if (k > arr.length)
            return -1;
        long minSum = 0;
        long maxSum = (long) 1e11;
        for (int i = 0; i < arr.length; i++) {
            minSum = Math.max(minSum, arr[i]);
            maxSum += arr[i];
        }
        while (minSum <= maxSum) {
            long mid = minSum + (maxSum - minSum) / 2;
            if (isPossible(arr, mid, k) == true) {
                maxSum = mid - 1;
            } else {
                minSum = mid + 1;
            }
        }
        return (int) (minSum);
    }

    /**
     * Helper method to check if the array can be split into `k` subarrays such that the largest sum
     * of any subarray is less than or equal to `x`.
     * 
     * @param nums The input array of integers.
     * @param sum The largest sum to check.
     * @param k The number of subarrays.
     * @return True if the array can be split, otherwise false.
     */
    public static boolean isPossible(int[] nums, long sum, int k) {
        int currentSum = 0;
        int splittedArrays = 1;
        for (int num : nums) {
            if (num > sum)
                return false;
            if ((currentSum + num) <= sum) {
                currentSum += num;
            } else {
                splittedArrays++;
                currentSum = num;
            }
        }

        return (splittedArrays <= k);
    }
}
