/**
 * Problem Statement:
 * 
 *     You are given an array `arr[]` of size `n`, and a number `k`. The task is to find the minimum number of swaps required to bring all elements less than or equal to `k` together in the array.
 *     A swap consists of swapping two elements in the array, and we need to bring all elements that are `<= k` together with the least number of swaps.
 * 
 * Input:
 *     - An integer `n`, representing the size of the array `arr[]`.
 *     - A binary array `arr[]` of size `n` containing integer elements.
 *     - An integer `k` representing the threshold value to group elements less than or equal to `k`.
 *
 * Output:
 *     - An integer representing the minimum number of swaps required to bring all elements less than or equal to `k` together in the array.
 *
 * Example:
 *     Input:
 *     7
 *     2 1 5 6 3 7 4
 *     4
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The array after 3 swaps: [2, 1, 3, 4, 5, 6, 7].
 */

import java.util.Scanner;

public class j08MinSwapsAndKTogether {
    public static void main(String args[]) {
        // Reading the input values
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Elements of the array
        }
        int k = in.nextInt(); // Threshold value for grouping elements
        System.out.println(minSwap(nums, n, k)); // Calculate and print the result
        in.close();
    }

    /**
     * Approach:
     *     - First, count how many elements in the array are less than or equal to `k`. This count determines the window size.
     *     - Use a sliding window of size equal to the number of elements less than or equal to `k`. The goal is to maximize the count of elements less than or equal to `k` within the window.
     *     - We count the number of elements less than or equal to `k` in the first window. Then, slide the window across the array, adjusting the count for each new element that enters or leaves the window.
     *     - The minimum number of swaps required is the number of elements that are greater than `k` inside the window.
     *
     * Time Complexity: O(n), where `n` is the size of the array. We count elements and slide the window.
     * Space Complexity: O(1), as we use a few variables for tracking the counts.
     *
     * @param arr The input array.
     * @param n The size of the array.
     * @param k The threshold value to group elements less than or equal to `k`.
     * @return The minimum number of swaps required to group elements <= k together.
     */
    public static int minSwap(int arr[], int n, int k) {
        int countMins = 0; // Count of elements <= k in the window
        int windowSize = 0; // Size of the window
        int maxInWindow = 0; // Maximum count of elements <= k in any window

        // Count the number of elements <= k to determine the window size
        for (int i = 0; i < n; i++) {
            if (arr[i] <= k)
                windowSize++;
        }

        // Count the number of elements <= k in the first window of size `windowSize`
        for (int i = 0; i < windowSize; i++) {
            if (arr[i] <= k)
                countMins++;
        }

        // Initialize the maximum count of elements <= k in the window
        maxInWindow = countMins;

        // Slide the window across the array
        for (int i = windowSize; i < n; i++) {
            // Remove the element going out of the window
            int last = arr[i - windowSize];
            if (last <= k)
                countMins--;

            // Add the element coming into the window
            int next = arr[i];
            if (next <= k)
                countMins++;

            // Track the maximum count of elements <= k in the window
            maxInWindow = Math.max(maxInWindow, countMins);
        }

        // The minimum number of swaps is the number of elements greater than `k` in the
        // window
        return windowSize - maxInWindow;
    }
}
