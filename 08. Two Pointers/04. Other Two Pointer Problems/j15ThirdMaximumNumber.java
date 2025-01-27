/**
 * Problem Statement:
 *
 *     Given an integer array `nums`, find the third maximum number in the array. If it does not 
 *     exist, return the maximum number.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums[]` of size `n` where each element satisfies (-10^5 <= nums[i] <= 10^5).
 *
 * Output:
 *     - An integer representing the third maximum number in the array, or the maximum if the 
 *       third does not exist.
 *
 * Example:
 *     Input:
 *     5
 *     3 2 1 4 5
 *     Output:
 *     3
 *
 *     Explanation:
 *     The third maximum number is 3, as the numbers in sorted order are [1, 2, 3, 4, 5], and 
 *     the third maximum is 3.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j15ThirdMaximumNumber {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution
        System.out.println(thirdMax(arr));

        in.close();
    }

    /**
     * Approach 1: Sorting Approach (O(n log n))
     *
     * Intuition:
     * - We first sort the array in ascending order.
     * - Then we iterate from the largest element and check if we can find the third unique 
     *   maximum number by skipping duplicates.
     * - If we can't find the third unique number, we return the largest number in the array.
     *
     * Time Complexity:
     * - O(n log n) due to the sorting step.
     *
     * Space Complexity:
     * - O(1), as we are not using any extra space other than the input array.
     *
     * @param nums The input array of integers.
     * @return The third maximum number in the array.
     */
    public static int thirdMax(int[] nums) {
        Arrays.sort(nums); // Sort the array in ascending order
        int i = nums.length - 1; // Start from the last element (maximum element)
        int j = 0; // Counter to track the number of unique elements we have seen
        while (j < 2) { // Loop until we find the third unique maximum
            j++;
            while (i > 0 && nums[i - 1] == nums[i]) // Skip duplicates
                i--;
            i--;
        }
        if (i < 0) // If no third unique element exists
            return nums[nums.length - 1]; // Return the largest element
        return nums[i]; // Return the third maximum number
    }
}
