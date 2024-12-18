/**
 * Problem Statement:
 * 
 *     Given a circular array of integers, you are tasked with finding the maximum sum of a subarray, where the subarray can be non-contiguous and wrap around the circular array.
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The maximum sum of a circular subarray.
 * 
 * Example:
 *     Input:
 *     5
 *     8 -8 9 -9 10
 *     
 *     Output:
 *     18
 * 
 *     Explanation:
 *     The maximum subarray sum in a circular manner is achieved by summing 9, -9, and 10 (wraps around), which equals 18.
 */

import java.util.Scanner;

public class j34LargestCircularSubarraySum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call your solution methods
        System.out.println(getMaxCircularSubarraySumNive(arr));
        System.out.println(getMaxCircularSubarraySumBetter(arr));
        System.out.println(getMaxCircularSubarraySumEfficient(arr));

        in.close();
    }

    /**
     * Approach: Brute Force (O(n^3))
     * 
     * Intuition:
     * - The idea here is to consider all possible subarrays by shifting the starting point and varying the length of the subarrays.
     * - For each subarray, we calculate its sum and keep track of the maximum sum found.
     * - While this method works, it is inefficient because it involves iterating over all possible subarrays, resulting in a time complexity of O(n^3).
     * 
     * Time Complexity:
     * - O(n^3), due to nested loops iterating through all possible subarrays and calculating their sums.
     * 
     * Space Complexity:
     * - O(1), since we only store a few variables.
     * 
     * @param arr The input array of integers.
     * @return The maximum sum of a circular subarray.
     */
    public static int getMaxCircularSubarraySumNive(int[] arr) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int len = 1; len <= arr.length; len++) {
                int temp = 0;
                for (int j = 0; j < len; j++) {
                    temp += arr[(i + j) % arr.length];
                }
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }

    /**
     * Approach: Improved Brute Force (O(n^2))
     * 
     * Intuition:
     * - This approach is similar to the previous one but optimizes the calculation by reducing the nested loop depth.
     * - For each possible starting point, we accumulate the sum for subarrays of increasing lengths. This reduces unnecessary recalculation.
     * - This approach works better than the previous one but is still inefficient with a time complexity of O(n^2).
     * 
     * Time Complexity:
     * - O(n^2), due to iterating through all possible starting points and incrementally calculating subarray sums.
     * 
     * Space Complexity:
     * - O(1), as we only store a few variables.
     * 
     * @param arr The input array of integers.
     * @return The maximum sum of a circular subarray.
     */
    public static int getMaxCircularSubarraySumBetter(int[] arr) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int tempSum = 0;
            for (int j = 0; j < arr.length; j++) {
                tempSum += arr[(i + j) % arr.length];
                ans = Math.max(ans, tempSum);
            }
        }
        return ans;
    }

    /**
     * Approach: Optimized (O(n))
     * 
     * Intuition:
     * - The efficient solution involves the use of Kadane's Algorithm to find the maximum subarray sum in the normal (non-circular) array.
     * - To handle the circular nature, we also compute the sum of the entire array and subtract the minimum subarray sum from it.
     * - If the minimum subarray sum is equal to the total array sum, it means all elements are negative, and the maximum circular sum is simply the result from Kadane's algorithm.
     * - This solution leverages the properties of subarrays and uses two passes over the array to compute the maximum sum, resulting in O(n) time complexity.
     * 
     * Time Complexity:
     * - O(n), as we perform two linear scans of the array.
     * 
     * Space Complexity:
     * - O(1), as we only store a few variables.
     * 
     * @param arr The input array of integers.
     * @return The maximum sum of a circular subarray.
     */
    public static int getMaxCircularSubarraySumEfficient(int[] arr) {
        int maxNormalSum = maxSubarraySum(arr);
        int minSum = minSubarraySum(arr);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int maxCircularSum = sum - minSum;
        if (maxCircularSum == 0) {
            return maxNormalSum;
        }
        return Math.max(maxNormalSum, maxCircularSum);
    }

    /**
     * Helper Function: Kadane's Algorithm to find the maximum subarray sum (O(n))
     * 
     * Intuition:
     * - Kadane's algorithm helps us find the maximum sum subarray in a linear scan.
     * - We maintain a running sum and keep track of the maximum encountered sum.
     * 
     * Time Complexity:
     * - O(n), as we scan the array once.
     * 
     * Space Complexity:
     * - O(1), since we only need a couple of variables.
     * 
     * @param arr The input array of integers.
     * @return The maximum sum of a subarray.
     */
    public static int maxSubarraySum(int[] arr) {
        int sum = arr[0];
        int tempSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            tempSum = Math.max(tempSum + arr[i], arr[i]);
            sum = Math.max(sum, tempSum);
        }
        return sum;
    }

    /**
     * Helper Function: Kadane's Algorithm to find the minimum subarray sum (O(n))
     * 
     * Intuition:
     * - Similar to the maximum subarray sum, but here we keep track of the minimum sum encountered.
     * 
     * Time Complexity:
     * - O(n), as we scan the array once.
     * 
     * Space Complexity:
     * - O(1), as we only need a couple of variables.
     * 
     * @param arr The input array of integers.
     * @return The minimum sum of a subarray.
     */
    public static int minSubarraySum(int[] arr) {
        int sum = arr[0];
        int tempSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            tempSum = Math.min(tempSum + arr[i], arr[i]);
            sum = Math.min(sum, tempSum);
        }
        return sum;
    }
}
