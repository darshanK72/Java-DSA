/**
 * Problem Statement:
 * 
 *     Given a binary array `arr` and an integer `k`, you can flip at most `k` zeros in the array to ones. 
 *     Find the length of the longest contiguous subarray that contains only ones after performing at most `k` flips.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (0 <= arr[i] <= 1).
 *     - An integer `k` (1 <= k <= n), representing the maximum number of zeros you can flip.
 * 
 * Output:
 *     - Return an integer representing the length of the longest contiguous subarray with only ones after flipping at most `k` zeros.
 * 
 * Example:
 *     Input:
 *     7
 *     1 0 1 0 1 1 0
 *     2
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The longest contiguous subarray with all ones after flipping at most 2 zeros is [1, 0, 1, 1].
 */

import java.util.Scanner;

public class j01MaximumConsecutiveOnesIII {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the number of flips allowed

        // Call the solution methods
        System.out.println(maxConsOnesAfterKFlips(arr, k)); // Brute force solution
        System.out.println(maxConsOnesAfter1Flips(arr, k)); // Solution for flipping at most 1 zero
        System.out.println(maxConsOnesAfterKFlipsEfficient(arr, k)); // Optimized solution

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - In this approach, we explore every subarray starting from index `i` and ending at index `j`.
     * - For each subarray, we count the number of zeros. If the number of zeros exceeds `k`, we stop the inner loop.
     * - Otherwise, we calculate the length of the current subarray and track the maximum length.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the number of elements in the array. This approach involves checking all subarrays.
     * 
     * Space Complexity:
     * - O(1), no additional space is required other than variables used for the calculations.
     * 
     * @param arr The input array of 0's and 1's.
     * @param k The number of zeros we can flip.
     * @return The maximum length of the subarray with at most `k` zeros flipped to ones.
     */
    public static int maxConsOnesAfterKFlips(int[] arr, int k) {
        int maxL = 0; // Initialize the maximum length
        for (int i = 0; i < arr.length; i++) {
            int zeros = 0; // Count the zeros in the current subarray
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == 0)
                    zeros++; // Increment zeros if we encounter a zero
                if (zeros > k)
                    break; // Break the loop if we have more than `k` zeros
                else {
                    maxL = Math.max(maxL, j - i + 1); // Update the maximum length
                }
            }
        }
        return maxL; // Return the result
    }

    /**
     * Approach 2: Sliding Window with One Zero Flip
     * 
     * Intuition:
     * - This approach uses a sliding window technique with two pointers `i` and `j`.
     * - We maintain a count of zeros within the window and ensure that we only have at most 1 zero.
     * - The window expands by moving `i` forward, and if the zero count exceeds 1, we shrink the window by moving `j`.
     * - This approach is specifically designed for flipping only 1 zero.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of elements in the array. Both pointers `i` and `j` move forward only once.
     * 
     * Space Complexity:
     * - O(1), no additional space is needed.
     * 
     * @param arr The input array of 0's and 1's.
     * @param k The number of zeros we can flip (1 in this case).
     * @return The maximum length of the subarray with at most 1 zero flipped to one.
     */
    public static int maxConsOnesAfter1Flips(int[] arr, int k) {
        int maxL = 0; // Initialize the maximum length
        int zeros = 0; // Track the number of zeros in the window
        int j = 0; // Left pointer of the sliding window

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeros++; // Increment zero count when encountering a zero
            }
            while (zeros > 1) { // If more than 1 zero is in the window, shrink the window
                if (arr[j] == 0)
                    zeros--; // Decrement zero count when shrinking from left
                j++; // Move the left pointer to the right
            }
            maxL = Math.max(maxL, i - j + 1); // Update the maximum length of the window
        }
        return maxL; // Return the result
    }

    /**
     * Approach 3: Optimized Sliding Window (For K Flips)
     * 
     * Intuition:
     * - This approach is similar to Approach 2, but generalized to allow flipping at most `k` zeros.
     * - We use a sliding window with two pointers, `i` and `j`, and maintain a count of zeros in the window.
     * - If the number of zeros exceeds `k`, we shrink the window from the left by moving `j`.
     * - This approach ensures we efficiently find the longest subarray with at most `k` zeros flipped.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of elements in the array, as both pointers move forward only once.
     * 
     * Space Complexity:
     * - O(1), no extra space is used besides the variables for zero count and the two pointers.
     * 
     * @param arr The input array of 0's and 1's.
     * @param k The number of zeros we can flip.
     * @return The maximum length of the subarray with at most `k` zeros flipped to ones.
     */
    public static int maxConsOnesAfterKFlipsEfficient(int[] arr, int k) {
        int maxL = 0; // Initialize the maximum length
        int zeros = 0; // Track the number of zeros in the window
        int j = 0; // Left pointer of the sliding window

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeros++; // Increment zero count when encountering a zero
            }
            while (zeros > k) { // If more than `k` zeros are in the window, shrink the window
                if (arr[j] == 0)
                    zeros--; // Decrement zero count when shrinking from left
                j++; // Move the left pointer to the right
            }
            maxL = Math.max(maxL, i - j + 1); // Update the maximum length of the window
        }
        return maxL; // Return the result
    }
}
