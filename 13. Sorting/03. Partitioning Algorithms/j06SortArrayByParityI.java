/**
 * Problem Statement:
 * 
 *     Given an integer array `nums`, move all even numbers to the beginning 
 *     followed by all odd numbers while No need to maintaining relative order.
 * 
 * Input:
 *     - An integer array `nums` of size `n` (1 <= n <= 10^4), 
 *       where each element satisfies (0 <= nums[i] <= 10^4).
 * 
 * Output:
 *     - The modified array where all even numbers appear before odd numbers.
 * 
 * Example:
 *     Input:
 *     [3, 1, 2, 4]
 * 
 *     Output:
 *     [2, 4, 3, 1]
 * 
 *     Explanation:
 *     - Even numbers `[2, 4]` are placed at the beginning.
 *     - Odd numbers `[3, 1]` follow.
 *     - The relative order of evens and odds is preserved.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j06SortArrayByParityI {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = in.nextInt(); // Input: size of the array

        int[] nums = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }

        // Calling the sorting method
        int[] result = sortArrayByParity(nums);

        // Output the rearranged array
        System.out.println("Sorted array: " + Arrays.toString(result));

        in.close();
    }

    /**
     * Approach: Two-Pointer Partitioning
     * 
     * Intuition:
     * - We use two pointers (`i` and `j`) to rearrange elements in a single traversal:
     *   - `i` tracks the position where the next even number should be placed.
     *   - `j` iterates through the array looking for even numbers.
     * - If `nums[j]` is even, swap it with `nums[i]` and move both pointers forward.
     * - Otherwise, just move `j` forward.
     * - This ensures that even numbers move to the front while odd numbers shift to the end.
     * 
     * Why This Works:
     * - The in-place swaps efficiently rearrange elements.
     * - Even numbers are moved to the front without extra memory usage.
     * 
     * Time Complexity:
     * - O(n): Single traversal of the array.
     * 
     * Space Complexity:
     * - O(1): In-place sorting without extra memory.
     * 
     * @param nums The input array.
     * @return The modified array with even numbers before odd numbers.
     */
    public static int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;

        while (j < n) {
            if (nums[j] % 2 == 0) {
                swap(nums, j, i);
                i++;
            }
            j++;
        }
        return nums;
    }

    /**
     * Helper Function: Swap
     * 
     * Intuition:
     * - This function swaps two elements in the given array.
     * - It is used to rearrange elements during partitioning.
     * 
     * Time Complexity:
     * - O(1): Single operation to swap two elements.
     * 
     * @param arr The input array.
     * @param i The index of the first element to be swapped.
     * @param j The index of the second element to be swapped.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
