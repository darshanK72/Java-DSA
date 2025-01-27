/**
 * Problem Statement:
 *
 *     Given an array `nums` of integers, find the next lexicographically greater permutation of the 
 *     numbers. If such a permutation is not possible (i.e., the array is sorted in descending order), 
 *     the function should rearrange the array into the lowest possible order (ascending).
 *
 * Input:
 *     - An integer `n` (1 <= n <= 100), representing the size of the array.
 *     - An array `nums[]` of size `n` where each element is an integer (0 <= nums[i] <= 100).
 *
 * Output:
 *     - The array `nums[]` is modified to represent the next permutation.
 *
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     Output:
 *     1 3 2
 *
 *     Explanation:
 *     The next permutation of 1 2 3 is 1 3 2.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j18NextPermutation {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Find and print the next permutation
        nextPermutation(arr);
        System.out.println(Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach: Next Permutation Algorithm (O(n))
     *
     * Intuition:
     * - The idea is to find the rightmost pair of consecutive numbers (nums[i], nums[i-1]) where nums[i] 
     *   is greater than nums[i-1]. This point marks the beginning of the part that needs to be rearranged.
     * - We then find the smallest number to the right of nums[i-1] that is larger than nums[i-1], swap them,
     *   and finally reverse the part of the array to the right of the swapped element to ensure the next 
     *   permutation is the smallest possible permutation.
     * - If no such pair exists, the array is sorted in descending order, and we simply reverse the whole array
     *   to return the smallest permutation.
     *
     * Time Complexity:
     * - O(n), where n is the length of the array, since we only iterate over the array a few times.
     *
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space (for the swap operation).
     *
     * @param nums The input array of integers.
     */
    public static void nextPermutation(int[] nums) {
        int breakPoint = -1;
        int n = nums.length;
        // Find the first element that is smaller than the next element from the end
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                breakPoint = i - 1;
                break;
            }
        }
        if (breakPoint == -1) {
            // If no such element is found, reverse the entire array
            reverse(nums, 0, nums.length - 1);
            return;
        }
        // Find the smallest element that is greater than nums[breakPoint]
        for (int j = n - 1; j > breakPoint; j--) {
            if (nums[j] > nums[breakPoint]) {
                // Swap nums[breakPoint] and nums[j]
                int temp = nums[breakPoint];
                nums[breakPoint] = nums[j];
                nums[j] = temp;
                break;
            }
        }
        // Reverse the part of the array after breakPoint to get the next smallest
        // permutation
        reverse(nums, breakPoint + 1, nums.length - 1);
    }

    /**
     * Helper Method: reverse (O(n))
     * 
     * Intuition:
     * - This method is used to reverse a section of the array. It ensures that the numbers after the 
     *   breakPoint are in the smallest lexicographical order after the swap.
     *
     * Time Complexity:
     * - O(n), where n is the number of elements to be reversed.
     *
     * Space Complexity:
     * - O(1), as no additional space is used.
     *
     * @param nums The input array of integers.
     * @param s The start index of the section to be reversed.
     * @param e The end index of the section to be reversed.
     */
    public static void reverse(int[] nums, int s, int e) {
        while (s < e) {
            // Swap nums[s] and nums[e]
            int temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;
            s++;
            e--;
        }
    }
}
