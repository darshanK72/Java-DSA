/**
 * Problem Statement:
 *
 *     Given an integer array `arr`, the task is to find the largest sum subarray. The subarray can be of any length, and we need to
 *     find the maximum sum of any contiguous subarray within the given array.
 *
 *     There are multiple ways to solve this problem, and we'll look at the naive approach (brute force), the efficient approach using Kadane's algorithm,
 *     and a variation that also returns the subarray with the largest sum.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^3 <= arr[i] <= 10^3).
 *
 * Output:
 *     - The largest sum of any contiguous subarray.
 *     - Additionally, we can return the subarray itself that gives the largest sum.
 *
 * Example:
 *     Input:
 *         5
 *         -2 1 -3 4 -1 2 1 -5 4
 *     Output:
 *         6
 *         6
 *         6
 *         [4, -1, 2, 1]
 *
 * Explanation:
 *     The subarray [4, -1, 2, 1] has the largest sum of 6, which is the answer.
 *     We will demonstrate multiple methods to find the largest sum, including the naive and efficient ones.
 */

import java.util.*;

public class j31LargestSumSubarray {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the different methods to compute the largest sum subarray
        System.out.println(getLargestSubarraySumNive(arr)); // Naive Approach
        System.out.println(getLargestSubarraySumEfficient1(arr)); // Efficient Approach (Kadane's Algorithm)
        System.out.println(getLargestSubarraySumEfficient2(arr)); // Efficient Approach (Kadane's Algorithm variation)
        System.out.println(Arrays.toString(getSubarrayWithLargestSum(arr))); // Subarray with largest sum

        in.close();
    }

    /**
     * Approach: Naive Approach (O(n^2))
     * 
     * Intuition:
     *     - The idea here is to check all possible subarrays and calculate their sums.
     *     - We iterate over all starting points of subarrays, and for each starting point,
     *       we extend the subarray by increasing the end index, calculating the sum and comparing it to the current maximum sum.
     * 
     * Time Complexity: O(n^2), as we have two nested loops: one for the start index and one for the end index.
     * 
     * Space Complexity: O(1), as we only use a few integer variables.
     *
     * @param arr The input array.
     * @return The largest sum of any subarray.
     */
    public static int getLargestSubarraySumNive(int[] arr) {
        int maxSum = Integer.MIN_VALUE; // Initialize with a very small number
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSum)
                    maxSum = sum;
            }
        }
        return maxSum;
    }

    /**
     * Approach: Kadane's Algorithm (O(n))
     * 
     * Intuition:
     *     - Kadane's algorithm is an efficient approach to solving the maximum subarray sum problem.
     *     - We iterate through the array, keeping track of the maximum sum ending at the current index.
     *     - If adding the current element decreases the sum, we start a new subarray from the current element.
     * 
     * Time Complexity: O(n), as we only make one pass through the array.
     * 
     * Space Complexity: O(1), as we only use a few integer variables.
     *
     * @param arr The input array.
     * @return The largest sum of any subarray.
     */
    public static int getLargestSubarraySumEfficient1(int[] arr) {
        int maxSum = Integer.MIN_VALUE; // Initialize with a very small number
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > maxSum) {
                maxSum = sum;
            }
            if (sum < 0) {
                sum = 0; // Reset the sum if it is negative
            }
        }
        return maxSum;
    }

    /**
     * Approach: Kadane's Algorithm (Simplified) (O(n))
     * 
     * Intuition:
     *     - This is another variation of Kadane’s algorithm, but instead of keeping track of the current sum separately,
     *       we use the maximum sum at each index.
     *     - For each element, we either add it to the previous subarray or start a new subarray from that element.
     * 
     * Time Complexity: O(n), as we only make one pass through the array.
     * 
     * Space Complexity: O(1), as we only use a few integer variables.
     *
     * @param arr The input array.
     * @return The largest sum of any subarray.
     */
    public static int getLargestSubarraySumEfficient2(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int maxSumBefore = 0;
        for (int i = 0; i < arr.length; i++) {
            maxSumBefore = Math.max(maxSumBefore + arr[i], arr[i]);
            maxSum = Math.max(maxSum, maxSumBefore);
        }
        return maxSum;
    }

    /**
     * Approach: To get the Subarray with Largest Sum (O(n))
     * 
     * Intuition:
     *     - In addition to the largest sum, we also want to return the subarray itself.
     *     - We track the start and end indices of the subarray with the largest sum and return it.
     * 
     * Time Complexity: O(n), as we only make one pass through the array.
     * 
     * Space Complexity: O(n), to store the subarray.
     *
     * @param arr The input array.
     * @return The subarray with the largest sum.
     */
    public static int[] getSubarrayWithLargestSum(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            if (sum == 0) {
                start = i;
            }
            sum += arr[i];
            if (sum > maxSum) {
                maxSum = sum;
                startIndex = start;
                endIndex = i;
            }
            if (sum < 0) {
                sum = 0;
            }
        }

        int s = endIndex - startIndex + 1;
        int[] result = new int[s];
        for (int i = 0; i < s; i++) {
            result[i] = arr[startIndex + i];
        }
        return result;
    }
}
