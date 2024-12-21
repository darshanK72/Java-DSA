/**
 * Problem Statement:
 *
 *     You are given a binary array `nums[]`. The task is to find the minimum number of swaps required to group all the `1`s together in the array.
 *     You are allowed to swap any two elements of the array, but the goal is to perform the minimum number of swaps.
 *
 * Input:
 *     - An integer `n` representing the size of the array `nums[]`.
 *     - A binary array `nums[]` of size `n` where each element is either `0` or `1`.
 *
 * Output:
 *     - An integer representing the minimum number of swaps required to group all `1`s together.
 *
 * Example:
 *     Input:
 *     7
 *     1 0 1 0 1 0 1
 *     Output:
 *     2
 *
 *     Explanation:
 *     The minimum number of swaps required to group all `1`s together is `2`. We can swap the first `1` with the second `0`, and the second `1` with the third `0`.
 */

import java.util.Scanner;

public class j06MinSwapsToGroup1sTogetherI {
    public static void main(String args[]) {
        // Reading the input values
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Elements of the array
        }

        // Calling the function to calculate the minimum number of swaps
        System.out.println(minSwap(nums));
        in.close();
    }

    /**
     * Approach:
     *     - First, we calculate the size of the window of `1`s. The number of `1`s in the array determines the window size.
     *     - We then slide a window of this size over the array and count the number of `0`s inside the window. The goal is to minimize the number of `0`s in the window, as swapping a `0` with a `1` will reduce the number of `1`s in the wrong position.
     *     - The number of `0`s inside the window represents the number of swaps needed to group all `1`s together.
     * 
     * Time Complexity: O(n), where `n` is the size of the array. We only loop through the array a couple of times.
     * Space Complexity: O(1), as we are only using a few variables for calculation.
     *
     * @param nums The input binary array.
     * @return The minimum number of swaps required to group all `1`s together.
     */
    public static int minSwap(int[] nums) {
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

        // Slide the window through the array and update the count of zeros
        for (int i = windowSize; i < n; i++) {
            if (nums[i - windowSize] == 0)
                currZeros--; // Remove the leftmost element of the previous window
            if (nums[i] == 0)
                currZeros++; // Add the new element to the right of the window
            minZeros = Math.min(minZeros, currZeros); // Track the minimum number of zeros in the window
        }

        // The minimum number of swaps is the minimum number of zeros found in the
        // window
        return minZeros;
    }
}
