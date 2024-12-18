/**
 * Problem Statement:
 * 
 *     Given an array `arr` of integers, the task is to calculate the sum of all odd-length subarrays of the array.
 *     A subarray is defined as a contiguous portion of the array. The length of the subarray is odd, and we need to 
 *     calculate the sum for each odd-length subarray and add them together.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 1000).
 * 
 * Output:
 *     - Return the sum of all odd-length subarrays.
 * 
 * Example:
 *     Input:
 *         5
 *         1 2 3 4 5
 *     Output:
 *         57
 * 
 *     Explanation:
 *         Odd-length subarrays for input `[1, 2, 3, 4, 5]`:
 *         - Length 1 subarrays: (1) + (2) + (3) + (4) + (5) = 15
 *         - Length 3 subarrays: (1 + 2 + 3) + (2 + 3 + 4) + (3 + 4 + 5) = 6 + 9 + 12 = 27
 *         - Length 5 subarrays: (1 + 2 + 3 + 4 + 5) = 15
 *         
 *         The total sum is: 15 + 27 + 15 = 57.
 */

import java.util.Scanner;

public class j30SumOfAllOddLenthSubarrays {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the method and print the result
        System.out.println(sumOfOddLengthSubarrays(arr));

        in.close();
    }

    /**
     * Approach: Sum of Odd-Length Subarrays
     * 
     * Intuition:
     *     - We iterate over all possible subarrays of the input array.
     *     - For each subarray, we check if its length is odd. If it is, we add the sum of that subarray to the result.
     *     - We calculate the sum of all odd-length subarrays using two nested loops: one to select the starting index and one to select the ending index.
     *     - The inner loop accumulates the sum of the elements in the subarray, and if the subarray has an odd length, its sum is added to the answer.
     * 
     * Time Complexity:
     *     - O(n^2), where `n` is the size of the array. The outer loop iterates over all possible starting points of the subarrays, and the inner loop iterates over all possible ending points, making the complexity quadratic.
     * 
     * Space Complexity:
     *     - O(1), as we do not use any extra space other than the input array and a few integer variables.
     * 
     * @param arr The input array.
     * @return The sum of all odd-length subarrays.
     */
    public static int sumOfOddLengthSubarrays(int[] arr) {
        int answer = 0;
        // Outer loop for the start of the subarray
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            // Inner loop for the end of the subarray
            for (int j = i; j < arr.length; j++) {
                sum += arr[j]; // Add current element to the subarray sum
                // Check if the length of the subarray (j - i + 1) is odd
                if ((j - i + 1) % 2 == 1) {
                    answer += sum; // If the length is odd, add the sum to the answer
                }
            }
        }
        return answer;
    }
}
