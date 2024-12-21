/**
 * Problem Statement:
 * 
 *     You are given a binary array `nums[]`. The task is to find the minimum number of swaps required to group all the `1`s together, considering a circular array.
 *     The challenge is that the array is circular, meaning the end of the array is connected to the beginning.
 * 
 *     You can perform swaps to move `1`s next to each other with the least number of swaps.
 *
 * Input:
 *     - An integer `n` representing the size of the array `nums[]`.
 *     - A binary array `nums[]` of size `n` where each element is either `0` or `1`.
 *
 * Output:
 *     - An integer representing the minimum number of swaps required to group all `1`s together in the circular array.
 *
 * Example:
 *     Input:
 *     7
 *     1 0 1 0 1 0 1
 *     Output:
 *     2
 *
 *     Explanation:
 *     The minimum number of swaps required to group all `1`s together in the circular array is `2`. The optimal swaps can be made by swapping the first `1` with the second `0`, and the second `1` with the third `0`.
 */

import java.util.Scanner;

public class j07MinSwapsToGroup1sTogetherII {
    public static void main(String args[]) {
        // Reading the input values
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Elements of the array
        }

        // Calling the function to calculate the minimum number of swaps for circular
        // array
        System.out.println(minSwapCircular(nums));
        in.close();
    }

    /**
     * Approach:
     *     - First, we count the total number of `1`s in the array, which determines the window size for grouping the `1`s.
     *     - We then use a sliding window technique to find the minimum number of `0`s inside the window, similar to the previous problem. The challenge here is to account for the circular nature of the array.
     *     - The window will wrap around to the beginning of the array when it reaches the end, which is handled using modulo arithmetic.
     *     - We track the minimum number of `0`s within the window as we slide it across the array, considering the circular nature.
     *
     * Time Complexity: O(n), where `n` is the size of the array. We iterate through the array to count `1`s and slide the window.
     * Space Complexity: O(1), as we use a few variables to keep track of the required values.
     *
     * @param nums The input binary array.
     * @return The minimum number of swaps required to group all `1`s together in the circular array.
     */
    public static int minSwapCircular(int[] nums) {
        int n = nums.length;
        int windowSize = 0;

        // Count the total number of 1's in the array, which determines the window size
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1)
                windowSize++;
        }

        // Calculate the number of 0's in the first window of size `windowSize`
        int currZeros = 0;
        for (int i = 0; i < windowSize; i++) {
            if (nums[i] == 0)
                currZeros++;
        }

        // Set the minimum number of zeros in the window to be the count in the first
        // window
        int minZeros = currZeros;

        // Slide the window through the circular array, using modulo to handle the
        // wraparound
        for (int i = windowSize; i < (n + windowSize - 1); i++) {
            // Remove the leftmost element of the previous window
            if (nums[i - windowSize] == 0)
                currZeros--;

            // Add the new element to the right of the window, considering the circular
            // nature using modulo
            if (nums[i % n] == 0)
                currZeros++;

            // Track the minimum number of zeros in any window
            minZeros = Math.min(minZeros, currZeros);
        }

        // The minimum number of swaps is the minimum number of zeros found in the
        // window
        return minZeros;
    }
}
