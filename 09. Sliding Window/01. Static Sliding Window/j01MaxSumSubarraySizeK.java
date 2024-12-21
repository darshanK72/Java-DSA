/**
 * Problem Statement:
 *
 *     Given an array of integers, find the maximum sum of a subarray of size `k`.
 *
 * Input:
 *     - An integer `N` representing the size of the array (1 <= N <= 10^5).
 *     - An array `Arr` of size `N` where each element is an integer.
 *     - An integer `k` representing the size of the subarray (1 <= k <= N).
 *
 * Output:
 *     - The maximum sum of a subarray of size `k`.
 *
 * Example:
 *     Input:
 *     5
 *     2 1 5 1 3
 *     3
 *     Output:
 *     9
 *
 *     Explanation:
 *     The maximum sum subarray of size 3 is [5, 1, 3], which sums to 9.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j01MaxSumSubarraySizeK {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Input: the size of the array
        ArrayList<Integer> Arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Arr.add(in.nextInt()); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the size of the subarray

        // Call the function to find maximum sum subarray of size k using different
        // approaches
        System.out.println(maximumSumSubarray(k, Arr, N)); // Brute Force Approach
        System.out.println(maximumSumSubarrayEfficient1(k, Arr, N)); // Sliding Window Approach 1
        System.out.println(maximumSumSubarrayEfficient2(k, Arr, N)); // Sliding Window Approach 2
        in.close();
    }

    /**
     * Brute Force Approach (O(N^2)):
     *
     * Intuition:
     * - The idea is to check all possible subarrays of size k and compute the sum.
     * - We use a nested loop to generate each subarray of size k and calculate the sum.
     * - This approach takes O(N^2) time because we generate each subarray, and for each subarray, we compute the sum.
     *
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     *
     * @param k The size of the subarray.
     * @param Arr The input array.
     * @param N The size of the array.
     * @return The maximum sum of a subarray of size k.
     */
    public static long maximumSumSubarray(int k, ArrayList<Integer> Arr, int N) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += Arr.get(j);
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * Sliding Window Approach 1 (O(N)):
     *
     * Intuition:
     * - We compute the sum of the first `k` elements.
     * - Then we slide the window one element at a time, subtracting the first element of the previous window and adding the next element of the array.
     * - This approach ensures that we calculate the sum of each subarray of size `k` in constant time after the initial sum computation.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     *
     * @param k The size of the subarray.
     * @param Arr The input array.
     * @param N The size of the array.
     * @return The maximum sum of a subarray of size k.
     */
    public static long maximumSumSubarrayEfficient1(int k, ArrayList<Integer> Arr, int N) {
        long maxSum = 0;
        long sum = 0;

        // Calculate the sum of the first k elements
        for (int i = 0; i < k; i++) {
            sum += Arr.get(i);
        }
        maxSum = sum;

        // Slide the window over the array
        for (int i = k; i < N; i++) {
            sum -= Arr.get(i - k); // Remove the first element of the previous window
            sum += Arr.get(i); // Add the next element of the current window
            maxSum = Math.max(maxSum, sum); // Update max sum if the current sum is larger
        }
        return maxSum;
    }

    /**
     * Sliding Window Approach 2 (O(N)):
     *
     * Intuition:
     * - Similar to Approach 1, but the sliding of the window is managed using two pointers (i and j) and the sum is updated in-place.
     * - As the window moves, we subtract the element that exits the window and add the element that enters the window.
     * - This is another efficient way to calculate the sum of subarrays of size `k`.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     *
     * @param k The size of the subarray.
     * @param Arr The input array.
     * @param N The size of the array.
     * @return The maximum sum of a subarray of size k.
     */
    public static long maximumSumSubarrayEfficient2(int k, ArrayList<Integer> Arr, int N) {
        long sum = 0;
        long maxSum = 0;
        int j = 0;

        // Iterate over the array and manage the sliding window
        for (int i = 0; i < N; i++) {
            sum += Arr.get(i);

            // Once we have a window of size k, update the max sum and slide the window
            if (i - j + 1 == k) {
                maxSum = Math.max(maxSum, sum);
                sum -= Arr.get(j); // Remove the first element of the current window
                j++; // Move the start of the window forward
            }
        }
        return maxSum;
    }
}
